import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.security.roles.ProjectRole
import com.atlassian.jira.security.roles.ProjectRoleManager
import com.opensymphony.workflow.InvalidInputException

ComponentManager componentManager = ComponentManager.getInstance()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_11900") //jira-test cfid: 12201
String Expertgroupstring = issue.getCustomFieldValue(cfExpertgroup)

log.error Expertgroupstring
log.error issue.assignee

//if assignee is not Unassigned
if (Expertgroupstring != null && issue.assignee != null){
ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cfExpertgroup))

	if (projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject) == false){
		invalidInputException = new InvalidInputException("Selected Assignee is not in selected Expert Group!")
    }
    else return true
}

if (Expertgroupstring == null){
		invalidInputException = new InvalidInputException("Selecting an Expert Group is mandatory!")
}
