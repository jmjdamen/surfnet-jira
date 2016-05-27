number index;
number strLength;
string impact;
string urgency;
string changePriority;
number impactScore;
number urgencyScore;
number changeScore;
string result;

if (customfield_12108 != "" || isNotNull(customfield_12108))
{
    changePriority = customfield_12108;

    index = indexOf(changePriority, "|");
    strLength = length(changePriority);
}
else result = 0;

if (index != -1)
{
    impact = substring(changePriority, 0, index);
    urgency = substring(changePriority, index + 1, strLength);

if(isNotNull(impact))
{
    if(impact == "Low")
    {
        impactScore = "1";
    }
    else if(impact == "Medium")
    {
        impactScore = "3";
    }
    else if (impact == "High")
    {
        impactScore = "5";
    }
}
    else impactScore = 0;
 
if(isNotNull(urgency))
{
    if(urgency == "Low")
    {
        urgencyScore = "1";
    }
    else if(urgency == "Medium")
    {
        urgencyScore = "3";
    }
    else if (urgency == "High")
    {
        urgencyScore = "5";
    }
}
    else urgencyScore = 0;  

if(impactScore != "0" && urgencyScore != "0")
{
changeScore = impactScore * urgencyScore;

    if(changeScore == 1)
    {
        result = "Low";
    }
    else if(changeScore >= 3 && changeScore <= 9)
    {
        result = "Medium";
    }
    else if(changeScore == 15)
    {
        result = "High";
    }
    else if(changeScore == 25)
    {
        result = "Emergency";
    }
    else result = 0;
}

}
else result = 0;

return result;