import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.security.roles.ProjectRole
import com.atlassian.jira.security.roles.ProjectRoleManager
import com.opensymphony.workflow.InvalidInputException

ComponentManager componentManager = ComponentManager.getInstance()
def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
//customfield 'Expert Group' , 12201 jira-test
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_11900") //jira-test cfid: 12201

//if assignee is not Unassigned and Expert Group is not set
if (issue.assignee != null && cfExpertgroup != null){

  ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
  ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cfExpertgroup))

  if(projectRoleManager.isUserInProjectRole(currentUser, userRole, issue.projectObject)){
    log.error ("Current user IS in Expert Group!")
    return true
  }
  else{
        invalidInputException = new InvalidInputException("Current user NOT in Expert Group!")
        log.error ("Current user NOT in Expert Group!")
        return false
    }
}
