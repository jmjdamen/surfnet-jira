import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.MutableIssue
import com.opensymphony.workflow.InvalidInputException
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Category

ComponentManager componentManager = ComponentManager.getInstance()
MutableIssue muIssue = issue as MutableIssue
MutableIssue parentIssue = muIssue.getParentObject() as MutableIssue
def parentStatus = (String) parentIssue.getStatus().name
String parent_type = parentIssue.getIssueTypeId()

if(!(parentStatus != "Resolved" && parentStatus != "Closed"))
{
     invalidInputException = new InvalidInputException("Project is not in a valid status, it's Resolved or Closed!")
}

if(parent_type != "10300")
{
	invalidInputException = new InvalidInputException("Fiber Construction can only be created under projects!")
}