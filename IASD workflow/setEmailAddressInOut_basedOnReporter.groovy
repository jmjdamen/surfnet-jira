import com.atlassian.jira.user.ApplicationUser
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.event.type.EventDispatchOption

def issueManager = ComponentAccessor.getIssueManager()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()

ApplicationUser reporter = issue.getReporter()
String reporterEmail = reporter.getEmailAddress()
String reporterKey = reporter.getKey()

if(reporterKey != "email handler ia helpdesk"){
    def cfEmailAddressInOut = customFieldManager.getCustomFieldObject("customfield_10123")
    issue.setCustomFieldValue(cfEmailAddressInOut, reporterEmail)
    issueManager.updateIssue(currentUser, issue, EventDispatchOption.DO_NOT_DISPATCH, false)
}
