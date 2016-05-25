number index;
number strLength;
number impact;
number urgency;
string swPriority;
string impactStr;
string urgencyStr;
number result;

if (customfield_12003 != "" || isNotNull(customfield_12003))
{
    swPriority = customfield_12003;
}

index = indexOf(swPriority, "|");
strLength = length(swPriority);

if (index != -1)
{
    impactStr = substring(swPriority, 0, index);
    urgencyStr = substring(swPriority, index + 1, strLength);
    impact = substring(impactStr, 0, 1);
    urgency = substring(urgencyStr, 0, 1);
}

if(isNotNull(impact) && isNotNull(urgency) && index != -1)
{
    result = impact * urgency;
}
else result = 0;

return result;