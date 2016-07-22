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
import com.atlassian.jira.workflow.WorkflowTransitionUtil;
import com.atlassian.jira.workflow.WorkflowTransitionUtilImpl;
import com.opensymphony.workflow.WorkflowContext;
import com.atlassian.jira.util.JiraUtils;

String currentUser = ((WorkflowContext) transientVars.get("context")).getCaller();
WorkflowTransitionUtil workflowTransitionUtil = (WorkflowTransitionUtil) JiraUtils.loadComponent(WorkflowTransitionUtilImpl.class);

def customFieldManager = ComponentAccessor.getCustomFieldManager();
def subTaskManager = ComponentAccessor.getSubTaskManager();
def subTasks = issue.getSubTaskObjects()

for(subtask in subTasks)
{
    String subtaskTypeId = subtask.getIssueTypeId();
    String subtaskStatus = subtask.getStatus().name
    MutableIssue subtaskIssue = subtask as MutableIssue

    if(subtaskStatus == "Resolved")
    { //no review workflow
      if(subtaskTypeId == "10803" || subtaskTypeId == "10804" || subtaskTypeId == "10805" || subtaskTypeId == "10808" || subtaskTypeId == "10809" || subtaskTypeId == "10810" || subtaskTypeId == "10811" || subtaskTypeId == "10900")
        {
            workflowTransitionUtil.setIssue(subtaskIssue);
            workflowTransitionUtil.setUserkey("jirasystem");
            workflowTransitionUtil.setAction(31);
            workflowTransitionUtil.validate();
            workflowTransitionUtil.progress();
        }
      //review workflow
      if(subtaskTypeId == "10801" || subtaskTypeId == "10802" || subtaskTypeId == "10806" || subtaskTypeId == "10807")
      {
            workflowTransitionUtil.setIssue(subtaskIssue);
            workflowTransitionUtil.setUserkey("jirasystem");
            workflowTransitionUtil.setAction(71);
            workflowTransitionUtil.validate();
            workflowTransitionUtil.progress();
      }
    }
}
