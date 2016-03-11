jQuery(document).ready(function($) {
JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
	colorComments();
    });
console.log("Color Comments JS loaded");
var refresh = setInterval(function() {colorComments()}, 500);

function colorComments(){
    $(".activity-comment:even").css("background-color","#E5E5E5"); 
    $(".activity-comment:odd").css("background-color","#FFFFFF");
};

});



