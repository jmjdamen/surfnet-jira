Customfield: Subtasks to Create (Classification Phase)
Transition: Accept
Screen: SNWP Change Accepted Transition Screen
--------
cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Functional Design")
issue.summary = 'FUNCTIONAL DESIGN - ' + sourceIssue.summary

cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Technical Design")
issue.summary = 'TECHNICAL DESIGN - ' + sourceIssue.summary

cfValues['Subtasks to Create (Classification Phase)']*.value.contains("Category and Risk Assessment")
issue.summary = 'CATEGORY AND RISK ASSESSMENT - ' + sourceIssue.summary


Customfield: Subtasks to Create (Execution Phase)
Transition: Start Progress
Screen: SNWP Change In Progress Transition Screen
--------
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Test Scenarios")
issue.summary = 'TEST SCENARIOS - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Release and Deployment Plan")
issue.summary = 'RELEASE AND DEPLOYMENT PLAN - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Build")
issue.summary = 'BUILD - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Functional Documentation")
issue.summary = 'FUNCTIONAL DOCUMENTATION - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Technical Documentation")
issue.summary = 'TECHNICAL DOCUMENTATION - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Set up Test Environment")
issue.summary = 'SET UP TEST ENVIRONMENT - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Execute Tests")
issue.summary = 'EXECUTE TESTS - ' + sourceIssue.summary
*
cfValues['Subtasks to Create (Execution Phase)']*.value.contains("Implementation")
issue.summary = 'IMPLEMENTATION - ' + sourceIssue.summary







