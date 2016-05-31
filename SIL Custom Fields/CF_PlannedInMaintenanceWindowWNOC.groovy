string startdate;
string enddate;
string result;

startdate = customfield_10716;
enddate = customfield_10717;


if ( ( dayOfWeek(startdate) == "Wed" || dayOfWeek(startdate) == "Thu" ) && dayOfWeek(enddate) == "Thu")
{
  if ( ( dayOfWeek(startdate) == "Wed" &&  hour(startdate) > "0") || ( ( dayOfWeek(startdate) == "Thu" && hour(startdate) < "7") && hour(enddate) >= "5" ) )
  {
      //if endtime is 5:00 then 'NO'
    if ( hour(enddate) == "5" && minute(enddate) == "0")
    {
      result = "No";
    }

    else
    {
      result = "Yes";
    }
  }
  // days are correct but times arent
  else
  {
      result = "No";
  }
}

//if not wednesday or thursday
else
{
      result = "No";
}

return result;