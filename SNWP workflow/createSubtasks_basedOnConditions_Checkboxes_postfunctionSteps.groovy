Customfield: Subtasks to Create (Classification Phase)
Transition: Accept
Screen: SNWP Change Accepted Transition Screen
--------
cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Functional Design")
issue.summary = 'Functional Design - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Technical Design")
issue.summary = 'Technical Design - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Category and Risk Assessment")
issue.summary = 'Category and Risk Assessment - ' + sourceIssue.summary
issue.assignee = null


Customfield: Subtasks to Create (Execution Phase)
Transition: Start Progress
Screen: SNWP Change In Progress Transition Screen
--------
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Test Scenarios")
issue.summary = 'Test Scenarios - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Release and Deployment Plan")
issue.summary = 'Release and Deployment Plan - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Build")
issue.summary = 'Build - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Functional Documentation")
issue.summary = 'Functional Documentation - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Technical Documentation")
issue.summary = 'Technical Documentation - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Set up Test Environment")
issue.summary = 'Set up Test Environment - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Execute Tests")
issue.summary = 'Execute Tests - ' + sourceIssue.summary
issue.assignee = null
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Implementation")
issue.summary = 'Implementation - ' + sourceIssue.summary
issue.assignee = null
