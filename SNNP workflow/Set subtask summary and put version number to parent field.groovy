import com.atlassian.jira.component.ComponentAccessor
//import com.atlassian.jira.issue.index.IssueIndexingService
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.index.IssueIndexManager
import com.atlassian.jira.util.ImportUtils
import com.atlassian.jira.issue.ModifiedValue
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.util.IssueChangeHolder

ComponentManager componentManager = ComponentManager.getInstance()
IssueChangeHolder changeHolder = new DefaultIssueChangeHolder();

def muIssue = issue;
def parentIssue = muIssue.getParentObject()

def customFieldManager = ComponentAccessor.getCustomFieldManager();
def parentcf = customFieldManager.getCustomFieldObject("customfield_10724")
def cf = customFieldManager.getCustomFieldObject("customfield_10711")
def version = issue.getCustomFieldValue(cf)

//set summary of subtask
issue.summary = issue.summary + ' -  v' + version

parentcf.updateValue(null, parentIssue, new ModifiedValue(parentIssue.getCustomFieldValue(parentcf), version), changeHolder);
