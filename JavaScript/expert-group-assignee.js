jQuery(document).ready(function($) {
  JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
    $('#customfield_12201').change(function() {
          callFunction();
    });
  });

  $('#customfield_12201').change(function() {
        callFunction();
  });

  function callFunction(){
    var expertgroupfield= document.getElementById('customfield_12201');
    var assigneefield= document.getElementById('assignee');

    if (expertgroupfield) {
      if (expertgroupfield.value == "NOC") {
          assigneefield.value = 'noc_group_user';
        }
      else if (expertgroupfield.value == "WNOC") {
          assigneefield.value = 'wnoc_group_user';
      }

    }
  }
});
