import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.issue.comments.CommentManager;
import com.opensymphony.workflow.WorkflowContext;
import org.apache.log4j.Category;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.workflow.WorkflowTransitionUtil;
import com.atlassian.jira.workflow.WorkflowTransitionUtilImpl;
import com.atlassian.jira.util.JiraUtils;
import com.atlassian.jira.issue.MutableIssue;

def log = Category.getInstance("com.onresolve.jira.groovy.AutoCloseChildIssues");
String currentUser = ((WorkflowContext) transientVars.get("context")).getCaller();

WorkflowTransitionUtil workflowTransitionUtil = (WorkflowTransitionUtil) JiraUtils.loadComponent(WorkflowTransitionUtilImpl.class);
def parent = (MutableIssue) issue.getParentObject();

def parentStatus = (String) parent.getStatus().name

if(parentStatus == "Open")
{
     workflowTransitionUtil.setIssue(parent);
     workflowTransitionUtil.setUserkey("jirasystem");
     workflowTransitionUtil.setAction(11);
     workflowTransitionUtil.validate();
     workflowTransitionUtil.progress();
}