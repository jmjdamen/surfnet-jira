import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.customfields.manager.*
import com.atlassian.jira.security.roles.ProjectRole
import com.atlassian.jira.security.roles.ProjectRoleManager
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.ModifiedValue
import com.opensymphony.workflow.WorkflowContext
import com.atlassian.jira.user.util.UserManager
import com.atlassian.jira.user.ApplicationUser

ComponentManager componentManager = ComponentManager.getInstance()
def userManager = ComponentAccessor.getUserManager()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_12201")
def cfExpertmail = customFieldManager.getCustomFieldObject("customfield_12202")
def cfExpertname = customFieldManager.getCustomFieldObject("customfield_12214")
def stringExpertgroup = (String) issue.getCustomFieldValue(cfExpertgroup)

ApplicationUser nocgroup = userManager.getUserByKey("noc_group_user")
ApplicationUser wnocgroup = userManager.getUserByKey("wnoc_group_user")
ApplicationUser kubusgroup = userManager.getUserByKey("kubus_group_user")

log.error stringExpertgroup
log.error issue.assignee

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cfExpertgroup))

if (stringExpertgroup != null){
//projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)

    if(stringExpertgroup == "NOC")
    {
      if(issue.assignee != null)
    	{
			     projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)
	    }
      else
      {
          issue.setAssignee(nocgroup);
      }

        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "noc@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet NOC")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder());
        cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder());
        log.error "NOC statement"
    }

    else if(stringExpertgroup == "WNOC")
    {
      if(issue.assignee != null)
    	{
			     projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)
	    }
      else
      {
          issue.setAssignee(wnocgroup);
      }

        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "wnoc@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet WNOC")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
        cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
        log.error "WNOC statement"
    }

    else if(stringExpertgroup == "KUBUS")
    {
      if(issue.assignee != null)
      {
           projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)
      }
      else
      {
          issue.setAssignee(kubusgroup);
      }

        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "kubus@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet KUBUS")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
  		  cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
        log.error "KUBUS statement"
    }

}
