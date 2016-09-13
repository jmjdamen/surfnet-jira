import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.util.ImportUtils

ComponentManager componentManager = ComponentManager.getInstance()

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def serverHostnameCF = customFieldManager.getCustomFieldObject("customfield_12304")
String serverHostname = issue.getCustomFieldValue(serverHostnameCF)

def serverGroupCF = customFieldManager.getCustomFieldObject("customfield_12303")
String serverGroup = issue.getCustomFieldValue(serverGroupCF)

def serverEnvironmentCF = customFieldManager.getCustomFieldObject("customfield_12302")
String serverEnvironment = issue.getCustomFieldValue(serverEnvironmentCF)

def serverOwnerCF = customFieldManager.getCustomFieldObject("customfield_12309")
String serverOwner = issue.getCustomFieldValue(serverOwnerCF)

def serverMacCF = customFieldManager.getCustomFieldObject("customfield_12306")
String serverMac = issue.getCustomFieldValue(macAddressCF)

def serverIpCF = customFieldManager.getCustomFieldObject("customfield_12305")
String serverIp = issue.getCustomFieldValue(ipAddressCF)

def serverCpuCF = customFieldManager.getCustomFieldObject("customfield_12301")
String serverCpu = issue.getCustomFieldValue(cpuCountCF)

def serverMemoryCF = customFieldManager.getCustomFieldObject("customfield_12307")
String serverMemory = issue.getCustomFieldValue(serverMemoryCF)

def serverOsCF = customFieldManager.getCustomFieldObject("customfield_12308")
String serverOs = issue.getCustomFieldValue(serverOsCF)

issue.description = "VM Details\n##########" + "Hostname: " + serverHostname + "\n" + "Group: " + serverGroup + "\n" + "Environment: " + serverEnvironment + "\n" +  "Owner: " serverOwner + "\n" + 
"MAC Address: " + serverMac + "\n" + "IP Address: " + serverIp + "\n" + "CPU count: " + serverCpu + "\n" + "Memory: " + serverMemory + "\n" + "OS: " + serverOs
