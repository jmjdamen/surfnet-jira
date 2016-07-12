string [] issueSubtasks = subtasks(key);
string [][] items;
boolean invalidStatus = false;
string[] declinedIds;
string[] acceptedCancelledIds;
boolean diffCheck = false;
string result = "No";
int index = 0;

string cabApproved = customfield_11100;

for(string subtask in issueSubtasks){
    if(subtask.issueTypeId == "10801" || subtask.issueTypeId == "10802" || subtask.issueTypeId == "10803"){
        items[index]["key"] = subtask.key;
        items[index]["id"] = subtask.issueTypeId;
        items[index]["status"] = subtask.status;
        items[index]["resolution"] = subtask.resolution;
        index++;
    }
}

for(number i = 0; i < arraySize(items); i = i + 1){
    if(items[i]["status"] != "Closed"){
        invalidStatus = true;
    }

    if(items[i]["resolution"] == "Accepted" || items[i]["resolution"] == "Cancelled"){
    acceptedCancelledIds = acceptedCancelledIds + items[i]["id"];
    }

    if(items[i]["resolution"] == "Declined"){
    declinedIds = declinedIds + items[i]["id"];
}
}

if(arrayDiff(declinedIds, acceptedCancelledIds) == ""){
    diffCheck = true;
}

if(arraySize(issueSubtasks) != 0 && invalidStatus == false && diffCheck == true && cabApproved == "No"){
    result = "Yes";
}

return result;
