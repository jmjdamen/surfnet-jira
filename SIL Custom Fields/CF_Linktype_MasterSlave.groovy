string[] inward = linkedIssues(key,"Master/Slave",-1);
string[] outward = linkedIssues(key,"Master/Slave",1);

if(size(inward) > 0) {
string inwardstring = ((linkedIssues(key,"Master/Slave",-1)));
return "Slaves are " + inwardstring;
}

if(size(outward) > 0) {
string outwardstring = ((linkedIssues(key,"Master/Slave",1)));
return "Master is " + outwardstring;
}
