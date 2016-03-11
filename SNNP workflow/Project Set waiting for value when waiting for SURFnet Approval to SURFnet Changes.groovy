import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.comments.CommentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.util.ImportUtils
import com.atlassian.jira.user.util.UserManager
import com.atlassian.crowd.embedded.api.User
import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.component.ComponentAccessor
 
ComponentManager componentManager = ComponentManager.getInstance()
def userManager = ComponentAccessor.getUserManager()
MutableIssue issue = issue

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cf = customFieldManager.getCustomFieldObject("customfield_10114")
String waitingfor_value = issue.getCustomFieldValue(cf)
ApplicationUser snchanges = userManager.getUserByKey("surfnetchanges_group_user")
    
if(waitingfor_value == 'SURFnet Approval')
{
	issue.setAssignee(snchanges);
}