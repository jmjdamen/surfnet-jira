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
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_12201")
def cfExpertmail = customFieldManager.getCustomFieldObject("customfield_12202")
def userManager = ComponentAccessor.getUserManager()

Options options = optManager.getOptions(cfExpertgroup.getRelevantConfig(issue))

//jira-test: NOC: 11800, WNOC: 11801, KUBUS: 11823
Option nocOption = options.getOptionById((Long) 11800);
Option wnocOption = options.getOptionById((Long) 11801);
Option kubusOption = options.getOptionById((Long) 11823);

String currentuserTrans = ((WorkflowContext) transientVars.get("context")).getCaller()
ApplicationUser currentUser = userManager.getUserByKey(currentuserTrans)

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
def userprojectRoles = projectRoleManager.getProjectRoles(currentUser, issue.projectObject)
String [] stringRoles = userprojectRoles
log.error stringRoles

if (stringRoles.contains("NOC") && stringRoles.contains("WNOC")){
  log.error("Found NOC and Found WNOC, setting Expert Group to NOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), nocOption );
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder());
  issue.setCustomFieldValue(cfExpertmail, "noc@surfnet.nl")
}

else if (stringRoles.contains("NOC")){
  log.error("Found NOC, setting Expert Group to NOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), nocOption );
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder());
  issue.setCustomFieldValue(cfExpertmail, "noc@surfnet.nl")
}

else if (stringRoles.contains("WNOC")){
  log.error("Found WNOC, setting Expert Group to WNOC")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), wnocOption );
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder());
  issue.setCustomFieldValue(cfExpertmail, "wnoc@surfnet.nl")
}

else if (stringRoles.contains("KUBUS")){
  log.error("Found KUBUS, setting Expert Group to KUBUS")
  ModifiedValue mValgroup = new ModifiedValue(issue.getCustomFieldValue(cfExpertgroup), wnocOption );
  cfExpertgroup.updateValue(null, issue, mValgroup, new DefaultIssueChangeHolder());
  issue.setCustomFieldValue(cfExpertmail, "kubus@surfnet.nl")
}
