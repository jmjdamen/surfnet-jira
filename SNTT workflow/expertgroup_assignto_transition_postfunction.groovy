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
def cfExpertgroup = customFieldManager.getCustomFieldObject("customfield_12201") //jira-test cfid: 12201
def cfExpertmail = customFieldManager.getCustomFieldObject("customfield_12202") //jira-test cfid: 12202
def cfExpertname = customFieldManager.getCustomFieldObject("customfield_12214") //jira-test cfid: 12214

def stringExpertgroup = (String) issue.getCustomFieldValue(cfExpertgroup)
ApplicationUser nocgroup = userManager.getUserByName("noc_group_user")
ApplicationUser wnocgroup = userManager.getUserByName("wnoc_group_user")
ApplicationUser surfcumulusgroup = userManager.getUserByName("surfcumulus_group_user")

log.error "Expert Group:" + stringExpertgroup
log.error "Assignee:" + issue.assignee

//ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class) as ProjectRoleManager
//ProjectRole userRole = projectRoleManager.getProjectRole((String) issue.getCustomFieldValue(cfExpertgroup))

if (stringExpertgroup != null){

    // Based on the Expert Group set in the transition screen set the following:
    // If an assignee is chosen, check if that assignee is in the selected Expert Group
    // If assignee is not chosen and is set to the Default 'Unassigned', set assignee to functional GROUP user.
    // Set Expert Group Name, Expert Group Email and Issue Security Level

    if(stringExpertgroup == "NOC")
    {
	  if(issue.assignee == null)
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
      if(issue.assignee == null)
      {
          issue.setAssignee(wnocgroup);
      }
        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "wnoc@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFnet WNOC")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
        cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
	    	issue.setSecurityLevelId((Long) 10101)
        //issue.setAssignee(wnocgroup);
        log.error "WNOC statement"
    }

    else if(stringExpertgroup == "SURFcumulus")
    {
      if(issue.assignee == null)
      {
          issue.setAssignee(surfcumulusgroup);
      }
        ModifiedValue mValemail = new ModifiedValue(issue.getCustomFieldValue(cfExpertmail), "surfcumulus@surfnet.nl")
        ModifiedValue mValname = new ModifiedValue(issue.getCustomFieldValue(cfExpertname), "SURFcumulus")
        cfExpertmail.updateValue(null, issue, mValemail, new DefaultIssueChangeHolder())
  	  	cfExpertname.updateValue(null, issue, mValname, new DefaultIssueChangeHolder())
		    //issue.setSecurityLevelId((Long) securityid kubus)
        log.error "SURFcumulus statement"
    }
}
