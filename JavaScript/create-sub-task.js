  jQuery(document).ready(function($) {
    var execute_AR = true;
    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
      initAuto();
      callFunction();
    });

    callFunction();
    initAuto();

    function initAuto() {
      $('#issuetype-field').change(function() {
        initAuto();
      });

      $('#customfield_10722').change(function() {
        console.log('Housing Location');
        callFunction();
      });

      $('#customfield_10723').change(function() {
        console.log('Housing Address');
        callFunction();
      });

      $('#customfield_10716').change(function() {
        console.log('Start Date/Time');
        callFunction();
      });

      $('#customfield_10713-1').change(function() {
        console.log('Issue Type Field 1');
        callFunction();
      });

      $('#customfield_10713-2').change(function() {
        console.log('Issue Type Field 2');
        callFunction();
      });

      $('#customfield_11000').change(function() {
        execute_AR = true;
        console.log('Related Install/Config Work');
        callFunction();
        $('.item-delete').live('click',function() {
    	  execute_AR = true;
          console.log('Related Install/Config Work deletion');
          callFunction();
        });
      });
    }
    
    function callFunction(){
      var issuetypefield= document.getElementById('issuetype-field');
      var summaryfield= document.getElementById('summary');
      var pIssue= document.getElementsByName('parentIssueId');

      if (issuetypefield != null) {
        if (issuetypefield.value == "Design") {
          var versionfield= document.getElementById('customfield_10711');
          if(pIssue[0]) {
            var result = getPkeyIssue(pIssue[0].value);
            var summaryParent = result.fields.summary;
            var valueParent = result.fields.customfield_10724;
            summaryfield.value="DESIGN - " + summaryParent;
            versionfield.value=valueParent + 1;
          }
        } else if (issuetypefield.value == "Install/Config Work") {
          var worktypefield_1 = document.getElementById('customfield_10713-1');
          var worktypefield_2 = document.getElementById('customfield_10713-2');

          if(pIssue[0]) {
            var result = getPkeyIssue(pIssue[0].value);
            var summaryParent = result.fields.summary;
            var summaryPrefix = "";
            if (worktypefield_1.checked && !worktypefield_2.checked) summaryPrefix = "CONFIG";
            else if (worktypefield_2.checked && !worktypefield_1.checked) summaryPrefix = "INSTALL";
            else if (worktypefield_1.checked && worktypefield_2.checked) summaryPrefix = "INSTALL+CONFIG";
            summaryfield.value=summaryPrefix + " - " + summaryParent;
          }
        } else if (issuetypefield.value == "Fiber Construction") {
          console.log('Fiber Construction');
          if(pIssue[0]) {
            console.log('Fiber Construction - Parent found');
            var result = getPkeyIssue(pIssue[0].value);
            var summaryParent = result.fields.summary;
            var summaryPrefix = "FIBER";
            summaryfield.value=summaryPrefix + " - " + summaryParent;
          }
        } else if (issuetypefield.value == "Access Request") {
          var housinglocation= document.getElementById('customfield_10722');
          var housingaddress= document.getElementById('customfield_10723');
          var startdatetime= document.getElementById('customfield_10716');
          var stopdatetime= document.getElementById('customfield_10717');
          if(pIssue[0] && execute_AR) {
            execute_AR = false;
            var result = getPkeyIssue(pIssue[0].value);
            var startp = result.fields.customfield_10716;
            var stopp = result.fields.customfield_10717;
            if (startp != null && stopp != null) {
              startdatetime.value = parseDate(startp);
              stopdatetime.value = parseDate(stopp);
            } else {
    	      // geen parent met start + stop date
    	      var relatedwork = document.getElementById('customfield_11000');
    	      if (relatedwork.value != null && relatedwork.value != "") {
    	        var result = getPkeyIssue(relatedwork.value);
        	var startp = result.fields.customfield_10716;
        	var stopp = result.fields.customfield_10717;
        	if (startp != null && stopp != null) {
        	  startdatetime.value = parseDate(startp);
        	  stopdatetime.value = parseDate(stopp);
        	}
    	      }
    	    }
          }
          if (summaryfield != null) summaryfield.value="Toegangsaanvraag " + startdatetime.value + " - " + housinglocation.value + " - " + housingaddress.value;
        }
      }

    }

    function getPkeyIssue(issueId){
      AJS.$.ajax({
        url:contextPath+'/rest/api/2/issue/'+issueId,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function(data) {
            result = data;
        }       
      });
      return result;
    }

    function parseDate(d) {
      //var timestamp = Date.parse(d);
      var timestamp = Date.fromISO(d);
      var dv = new Date(timestamp);
      return addZero(dv.getDate()) + "-" + getMonthName(dv.getMonth()) + "-" + dv.getFullYear() + " " + addZero(dv.getHours()) + ":" + addZero(dv.getMinutes());
    }

    function addZero(i) {
      if (i < 10) {
        i = "0" + i;
      }
      return i;
    }

    function getMonthName(d) {
      var month = new Array();
      month[0] = "Jan";
      month[1] = "Feb";
      month[2] = "Mar";
      month[3] = "Apr";
      month[4] = "May";
      month[5] = "Jun";
      month[6] = "Jul";
      month[7] = "Aug";
      month[8] = "Sep";
      month[9] = "Oct";
      month[10] = "Nov";
      month[11] = "Dec";
      return month[d]; 
    }
    
    Date.fromISO= (function(){
	var testIso = '2011-11-24T09:00:27+0200';
	// Chrome
	var diso= Date.parse(testIso);
	if(diso===1322118027000) return function(s){
    	    return new Date(Date.parse(s));
	}
	// JS 1.8 gecko
	var noOffset = function(s) {
	    var day= s.slice(0,-5).split(/\D/).map(function(itm){
    		return parseInt(itm, 10) || 0;
    	    });
	    day[1]-= 1;
	    day= new Date(Date.UTC.apply(Date, day));  
	    var offsetString = s.slice(-5)
	    var offset = parseInt(offsetString,10)/100; 
	    if (offsetString.slice(0,1)=="+") offset*=-1;
	    day.setHours(day.getHours()+offset);
	    return day.getTime();
	}
	if (noOffset(testIso)===1322118027000) {
    	    return noOffset;
	}  
	return function(s){ // kennebec@SO + QTax@SO
    	    var day, tz, 
//        rx = /^(\d{4}\-\d\d\-\d\d([tT][\d:\.]*)?)([zZ]|([+\-])(\d{4}))?$/,
	    rx = /^(\d{4}\-\d\d\-\d\d([tT][\d:\.]*)?)([zZ]|([+\-])(\d\d):?(\d\d))?$/,
            
    	    p= rx.exec(s) || [];
    	    if(p[1]){
        	day= p[1].split(/\D/).map(function(itm){
            	    return parseInt(itm, 10) || 0;
        	});
        	day[1]-= 1;
        	day= new Date(Date.UTC.apply(Date, day));
        	if(!day.getDate()) return NaN;
        	if(p[5]){
            	    tz= parseInt(p[5], 10)/100*60;
            	    if(p[6]) tz += parseInt(p[6], 10);
            	    if(p[4]== "+") tz*= -1;
            	    if(tz) day.setUTCMinutes(day.getUTCMinutes()+ tz);
        	}
        	return day;
    	    }
    	    return NaN;
	}
    })()

  });