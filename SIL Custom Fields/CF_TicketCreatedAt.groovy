string datetext;
string day;
string dayname;
string daynametext;
string month;
string monthtext;
string result;

if(created != ""){
day = day(created);

dayname =  dayOfWeek(created);

if (dayname == "Mon")
{daynametext = "maandag";}
if (dayname == "Tue")
{daynametext = "dinsdag";}
if (dayname == "Wed")
{daynametext = "woensdag";}
if (dayname == "Thu")
{daynametext = "donderdag";}
if (dayname == "Fri")
{daynametext = "vrijdag";}
if (dayname == "Sat")
{daynametext = "zaterdag";}
if (dayname == "Sun")
{daynametext = "zondag";}

month = monthName(created);

if (month == "Jan")
{monthtext = "januari";}
if (month == "Feb")
{monthtext = "februari";}
if (month == "Mar")
{monthtext = "Maart";}
if (month == "Apr")
{monthtext = "april";}
if (month == "May")
{monthtext = "mei";}
if (month == "Jun")
{monthtext = "juni";}
if (month == "Jul")
{monthtext = "juli";}
if (month == "Aug")
{monthtext = "augustus";}
if (month == "Sep")
{monthtext = "september";}
if (month == "Oct")
{monthtext = "oktober";}
if (month == "Nov")
{monthtext = "november";}
if (month == "Dec")
{monthtext = "december";}

result = daynametext + " " + day + " " + monthtext;

return result;
}
