import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.security.roles.ProjectRole
import com.atlassian.jira.security.roles.ProjectRoleManager

ComponentManager componentManager = ComponentManager.getInstance()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
//customfield 'Expert Group' , 12201 jira-test
def cf = customFieldManager.getCustomFieldObject("customfield_12201")

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cf))

//if assignee is not Unassigned
if (issue.assignee != null){
projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject) }

else if (cf != "None") {
return true
}
