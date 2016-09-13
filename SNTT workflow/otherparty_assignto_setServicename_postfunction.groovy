import com.atlassian.jira.ComponentManager
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder
import com.atlassian.jira.issue.ModifiedValue

ComponentManager componentManager = ComponentManager.getInstance()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cfOtherPartyService = customFieldManager.getCustomFieldObject("customfield_10209")
String otherPartyService = issue.getCustomFieldValue(cfOtherPartyService)
def cfSurfnetService = customFieldManager.getCustomFieldObject("customfield_12401") //jira-test cfid: 12503

if(otherPartyService.contains('cumulus'))
{
    ModifiedValue mValservice = new ModifiedValue(issue.getCustomFieldValue(cfSurfnetService), "SURFcumulus")
    cfSurfnetService.updateValue(null, issue, mValservice, new DefaultIssueChangeHolder())
}
