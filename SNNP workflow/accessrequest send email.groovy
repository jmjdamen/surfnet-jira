import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager

ComponentManager componentManager = ComponentManager.getInstance()

MutableIssue muIssue = issue;

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cf = customFieldManager.getCustomFieldObject("customfield_11606")
def counter = (double) issue.getCustomFieldValue(cf)
counter = counter + 1
//log.error version

issue.setCustomFieldValue(cf, counter)
