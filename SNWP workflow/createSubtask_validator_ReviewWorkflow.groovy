import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.MutableIssue
import com.opensymphony.workflow.InvalidInputException
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Category
import com.atlassian.jira.config.SubTaskManager

def customFieldManager = ComponentAccessor.getCustomFieldManager();
def muIssue = issue;
String issueTypeId = issue.getIssueTypeId()
def parentIssue = muIssue.getParentObject()
def parentStatus = (String) parentIssue.getStatus().name
def subTaskManager = ComponentAccessor.getSubTaskManager();
def subTasks = parentIssue.getSubTaskObjects()
def subtaskVersions = []

def cfCabApproved = customFieldManager.getCustomFieldObject("customfield_11100")
String CabApproved = parentIssue.getCustomFieldValue(cfCabApproved)

def cfVersion = customFieldManager.getCustomFieldObject("customfield_12300")
int Version = (int) muIssue.getCustomFieldValue(cfVersion);

if(issueTypeId == "10801" || issueTypeId == "10802")
{
    if((parentStatus != "Open" && parentStatus != "Accepted"))
    {
         invalidInputException = new InvalidInputException("Change is not in a valid status, it's " + parentStatus + ", creation of this subtask is only allowed while Change is 'Open' or 'Accepted'.")
    }
}

if(issueTypeId == "10806" || issueTypeId == "10807")
{
    if((parentStatus != "Accepted" && parentStatus != "In Progress"))
    {
         invalidInputException = new InvalidInputException("Change is not in a valid status, it's " + parentStatus + ", creation of this subtask is only allowed while Change is 'Accepted' or 'In Progress'.")
    }
    if(CabApproved == "No")
    {
         invalidInputException = new InvalidInputException("Change is not Approved by CAB.")
    }
}

for(subtask in subTasks)
{
    String subtaskTypeId = subtask.getIssueTypeId();
    if(subtaskTypeId == issueTypeId)
    {
        String subtaskStatus = subtask.getStatus().name;
        def subtaskVersion = subtask.getCustomFieldValue(cfVersion);
        subtaskVersions.push(subtaskVersion)
        if(subtaskStatus != "Resolved" && subtaskStatus != "Closed")
        {
            invalidInputException = new InvalidInputException("Can't create subtask, there already is a subtask of this type that is in status " + subtaskStatus)
        }
    }
}

if(Version <= (int) subtaskVersions.max())
{
	invalidInputException = new InvalidInputException("Can't create subtask, there already is a subtask of this type with the same version or there are higher versions.")
}
if((Version - (int) subtaskVersions.max()) > 1)
{
	invalidInputException = new InvalidInputException("Can't create subtask, there is a gap in version numbers.")
}
