  jQuery(document).ready(function($) {
    if((document.getElementById('issuetype')) !== null && (document.getElementById('issuetype').value == "10300" || document.getElementById('issuetype').value == "10303")){
    callFunctionUOD();
    console.log("Inital Calculate UOD, issuetype detected, called function");
    }
    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
    console.log("JIRA.bind Calculate UOD");
    if((document.getElementById('issuetype')) !== null && (document.getElementById('issuetype').value == "10300" || document.getElementById('issuetype').value == "10303")){
    callFunctionUOD();
    }
    });

  function callFunctionUOD(){
  if((document.getElementById('issuetype')) !== null) {
  issueType = document.getElementById('issuetype').value;}
  if((document.getElementById('customfield_10714')) !== null){
  type = document.getElementById('customfield_10714');}
  current = moment().format('DD-MMM-YYYY HH:mm');
  
  target = document.getElementById('customfield_10785');
  if(issueType == "10300"){
 
  if (type){
  type.onchange=function() {
          // if project Type 1
          if (this.value == "10506") {
                        //check if day is not friday set uod today + 1 day
                        if(moment().isoWeekday() !== 5)
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(1, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                        //if current day is friday set uod on monday
                        else
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(3, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                     target.style.display = '';
                     target.value= uod;
                   }
                 
          // if project Type 2
          else if (this.value == "10507") {
                        //check if day is monday, set uod today + 4 days
                        if(moment().isoWeekday() == 1)
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(4, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                        //check if other day then monday, because of weekend skip, set uod today + 6 days
                        else
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(6, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                     target.style.display = '';
                     target.value= uod;
                  }
          // if project Type 3
          else {
                 target.style.display='';
                 target.value= "";
          }
      }
    }

  }

  if(issueType == "10303"){
            if(moment().isoWeekday() !== 4 && moment().isoWeekday() !== 5)
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(2, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                        //if current day is thursday or friday set uod on monday or tuesday
                        else
                        {
                        var uod = moment(current, 'DD-MMM-YYYY HH:mm').add(4, 'days').format('DD-MMM-YYYY HH:mm');
                        }
                     target.style.display = '';
                     target.value= uod;
                    }

}

});
