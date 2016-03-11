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
def cf = customFieldManager.getCustomFieldObject("customfield_12201")
def userManager = ComponentAccessor.getUserManager()

Options options = optManager.getOptions(cf.getRelevantConfig(issue))

//jira-test: NOC: 11800, WNOC: 11801
Option nocOption = options.getOptionById((Long) 11800);
Option wnocOption = options.getOptionById((Long) 11801);

String currentuserTrans = ((WorkflowContext) transientVars.get("context")).getCaller()
ApplicationUser currentUser = userManager.getUserByKey(currentuserTrans)

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
def userprojectRoles = projectRoleManager.getProjectRoles(currentUser, issue.projectObject)
String [] stringRoles = userprojectRoles
log.error stringRoles

if (stringRoles.contains("NOC") && stringRoles.contains("WNOC")){
  log.error("Found NOC and Found WNOC, setting Expert Group to NOC")
  ModifiedValue mVal = new ModifiedValue(issue.getCustomFieldValue(cf), nocOption );
  cf.updateValue(null, issue, mVal, new DefaultIssueChangeHolder());
}

else if (stringRoles.contains("NOC")){
  log.error("Found NOC, setting Expert Group to NOC")
  ModifiedValue mVal = new ModifiedValue(issue.getCustomFieldValue(cf), nocOption );
  cf.updateValue(null, issue, mVal, new DefaultIssueChangeHolder());
}

else if (stringRoles.contains("WNOC")){
  log.error("Found WNOC, setting Expert Group to WNOC")
  ModifiedValue mVal = new ModifiedValue(issue.getCustomFieldValue(cf), wnocOption );
  cf.updateValue(null, issue, mVal, new DefaultIssueChangeHolder());
}
