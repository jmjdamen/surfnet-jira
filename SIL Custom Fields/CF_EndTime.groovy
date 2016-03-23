date datetime;
number minute;
string minutetext;
string hour;
string result;

datetime = customfield_10717;

minute = minute(datetime);
hour = hour(datetime);

if(minute < 10)
{
  minutetext = "0" + minute;
  result = hour + ":" + minutetext;
}

if(minute > 9)
{
  minutetext = minute;
  result = hour + ":" + minutetext;
}

return result;
