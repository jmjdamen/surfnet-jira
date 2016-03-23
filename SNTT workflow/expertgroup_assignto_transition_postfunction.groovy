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
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_11900") //jira-test cfid: 12201
def cfExpertmail = customFieldManager.getCustomFieldObject("customfield_11901") //jira-test cfid: 12202
def cfExpertname = customFieldManager.getCustomFieldObject("customfield_11902") //jira-test cfid: 12214

def stringExpertgroup = (String) issue.getCustomFieldValue(cfExpertgroup)

ApplicationUser nocgroup = userManager.getUserByKey("noc_group_user")
ApplicationUser wnocgroup = userManager.getUserByKey("wnoc_group_user")
ApplicationUser kubusgroup = userManager.getUserByKey("kubus_group_user")

log.error stringExpertgroup
log.error issue.assignee

ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cfExpertgroup))

if (stringExpertgroup != null){

    // Based on the Expert Group set in the transition screen set the following:
    // If an assignee is chosen, check if that assignee is in the selected Expert Group
    // If assignee is not chosen and is set to the Default 'Unassigned', set assignee to functional GROUP user.
    // Set Expert Group Name, Expert Group Email and Issue Security Level

    if(stringExpertgroup == "NOC")
    {
<<<<<<< HEAD
      if(issue.assignee == null)
=======
    	if(issue.assignee != null)
    	{
    		projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)
	}
      else
>>>>>>> e803f42cb612bc1d3dc1de18296cac4eaf6531d9
      {
          issue.setAssignee(nocgroup);
      }
        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "noc@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet NOC")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder());
        cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder());
        issue.setSecurityLevelId((Long) 10100)
        log.error "NOC statement"
    }

    else if(stringExpertgroup == "WNOC")
    {
<<<<<<< HEAD
      if(issue.assignee == null)
=======
      if(issue.assignee != null)
      {
		projectRoleManager.isUserInProjectRole(issue.assignee, userRole, issue.projectObject)
      }
      else
>>>>>>> e803f42cb612bc1d3dc1de18296cac4eaf6531d9
      {
          issue.setAssignee(wnocgroup);
      }
        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "wnoc@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet WNOC")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
        cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
		    issue.setSecurityLevelId((Long) 10101)
        log.error "WNOC statement"
    }

    else if(stringExpertgroup == "KUBUS")
    {
      if(issue.assignee == null)
      {
          issue.setAssignee(kubusgroup);
      }
        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "kubus@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet KUBUS")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
<<<<<<< HEAD
  	  	cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
		    //issue.setSecurityLevelId((Long) securityid kubus)
=======
	cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
>>>>>>> e803f42cb612bc1d3dc1de18296cac4eaf6531d9
        log.error "KUBUS statement"
    }
}