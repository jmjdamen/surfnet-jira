import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.customfields.manager.*
import com.atlassian.jira.issue.customfields.option.*
import com.atlassian.jira.issue.customfields.manager.OptionsManager
import com.atlassian.jira.issue.ModifiedValue
import com.opensymphony.workflow.WorkflowContext
import com.atlassian.jira.user.util.UserManager
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.security.roles.ProjectRole
import com.atlassian.jira.security.roles.ProjectRoleManager
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.util.IssueChangeHolder
import org.apache.log4j.Category

ComponentManager componentManager = ComponentManager.getInstance()
OptionsManager optManager = ComponentAccessor.getOptionsManager()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_11900") //jira-test cfid: 12201
def cfExpertmail = customFieldManager.getCustomFieldObject("customfield_11901") //jira-test cfid: 12202
def cfExpertname = customFieldManager.getCustomFieldObject("customfield_11902") //jira-test cfid: 12214

def userManager = ComponentAccessor.getUserManager()

Options options = optManager.getOptions(cfExpertgroup.getRelevantConfig(issue))

//jira-test CF ids: NOC: 11800, WNOC: 11801, KUBUS: 11823
Option nocOption = options.getOptionById((Long) 11700) //jira-test cfid: 11800
Option wnocOption = options.getOptionById((Long) 11701) //jira-test cfid: 11801
Option kubusOption = options.getOptionById((Long) 11702) //jira-test cfid: 11823

String currentuserTrans = ((WorkflowContext) transientVars.get("context")).getCaller()
ApplicationUser currentUser = userManager.getUserByKey(currentuserTrans)

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
def userprojectRoles = projectRoleManager.getProjectRoles(currentUser, issue.projectObject)
String [] stringRoles = userprojectRoles
log.error stringRoles

// check in which role(s) the user is, based on the outcome set the following:
// set Expert Group (select list), Expert Group Name, Expert Group Email and Issue Security Level

if (stringRoles.contains("NOC") && stringRoles.contains("WNOC")){
  log.error("Found NOC and Found WNOC, setting Expert Group to NOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), nocOption );
  ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet NOC")
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder())
  cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
  issue.setCustomFieldValue(cfExpertmail, "noc@surfnet.nl")
  issue.setSecurityLevelId((Long) 10100)
}

else if (stringRoles.contains("NOC")){
  log.error("Found NOC, setting Expert Group to NOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), nocOption );
  ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet NOC")
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder())
  cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
  issue.setCustomFieldValue(cfExpertmail, "noc@surfnet.nl")
  issue.setSecurityLevelId((Long) 10100)
}

else if (stringRoles.contains("WNOC")){
  log.error("Found WNOC, setting Expert Group to WNOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), wnocOption );
  ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet WNOC")
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder())
  cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
  issue.setCustomFieldValue(cfExpertmail, "wnoc@surfnet.nl")
  issue.setSecurityLevelId((Long) 10101)
}

else if (stringRoles.contains("KUBUS")){
  log.error("Found KUBUS, setting Expert Group to KUBUS")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), kubusOption )
  ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet KUBUS")
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder())
  cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
  issue.setCustomFieldValue(cfExpertmail, "kubus@surfnet.nl")
  //issue.setSecurityLevelId((Long) securityid kubus)
}
