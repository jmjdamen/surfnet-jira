import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.MutableIssue
import com.opensymphony.workflow.InvalidInputException
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Category
import com.atlassian.jira.config.SubTaskManager

def muIssue = issue;
def parentIssue = muIssue.getParentObject()
def parentStatus = (String) parentIssue.getStatus().name
def subTaskManager = ComponentAccessor.getSubTaskManager();
def subTasks = parentIssue.getSubTaskObjects()

def customFieldManager = ComponentAccessor.getCustomFieldManager();
def parentcf = customFieldManager.getCustomFieldObject("customfield_10724")
def cf = customFieldManager.getCustomFieldObject("customfield_10711")
def version = issue.getCustomFieldValue(cf)
def latestVersion =  parentIssue.getCustomFieldValue(parentcf)
String parent_type = parentIssue.getIssueTypeId()

if(!(parentStatus != "Resolved" && parentStatus != "Closed"))
{
     invalidInputException = new InvalidInputException("Project is not in a valid status, it's Resolved or Closed!")
}

if(version <= latestVersion)
{
     invalidInputException = new InvalidInputException("Version already exists!")
}

if(parent_type != "10300")
{
	invalidInputException = new InvalidInputException("Designs can only be created under projects!")
}

for(subtask in subTasks) {
     String designid = subtask.getIssueTypeId();
     String status = subtask.getStatus().name
     if(designid == "10301" && (status != "Resolved" && status != "Closed")){
	invalidInputException = new InvalidInputException("There are is at least one design that's not resolved/closed!")
    }
}
