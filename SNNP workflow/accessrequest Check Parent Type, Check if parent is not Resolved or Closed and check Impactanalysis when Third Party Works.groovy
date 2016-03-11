import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.MutableIssue
import com.opensymphony.workflow.InvalidInputException
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Category

ComponentManager componentManager = ComponentManager.getInstance()
MutableIssue muIssue = issue as MutableIssue
MutableIssue parentIssue = muIssue.getParentObject() as MutableIssue

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cf_impactanalysis = customFieldManager.getCustomFieldObject("customfield_10715")
String impactanalysis = parentIssue.getCustomFieldValue(cf_impactanalysis)
def parentStatus = (String) parentIssue.getStatus().name
String parent_type = parentIssue.getIssueTypeId();

if(!(parentStatus != "Resolved" && parentStatus != "Closed"))
{
     invalidInputException = new InvalidInputException("Project is not in a valid status, it's Resolved or Closed!")
}


if(parent_type == "10304" && impactanalysis == "Not Executed")
{
    invalidInputException = new InvalidInputException("Impactanalysis is Not Executed, make sure an Impactanalysis is performed first!")
}

if(parent_type != "10300" && parent_type != "10304")
{
	invalidInputException = new InvalidInputException("Access Requests can only be created under Projects and Third Party Works!")
}        