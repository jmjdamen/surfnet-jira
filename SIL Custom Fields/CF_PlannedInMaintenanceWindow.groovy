string startdate;
string enddate;
string result;

startdate = customfield_10716;
enddate = customfield_10717;


if ( ( dayOfWeek(startdate) == "Mon" || dayOfWeek(startdate) == "Tue" ) && dayOfWeek(enddate) == "Tue")
{
  if ( ( dayOfWeek(startdate) == "Mon" &&  hour(startdate) > "0") || ( ( dayOfWeek(startdate) == "Tue" && hour(startdate) < "7") && hour(enddate) >= "5" ) )
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

//if not monday or tuesday
else
{
      result = "No";
}

return result;
