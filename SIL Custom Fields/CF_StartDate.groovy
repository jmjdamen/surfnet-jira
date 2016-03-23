string datetime;
string day;
string month;
string year;
string result;

datetime = customfield_10716;

day = day(datetime);
month = month(datetime);
year = year(datetime);

result = day + "-" + month + "-" + year;

return result;
