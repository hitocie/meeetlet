/**
 * 
 */
var dateList = [];

$('#arrange_date_top').live('pageinit', function(event) {
	$("#append").live("click", function(event, ui) {
		var adate = $("#cal").val();
		if (adate == "") return false;
		if (dateList.indexOf(adate) >= 0) {
			return false;
		}
		dateList.push(adate);
		$("#datelist").append($('<li>'+adate+'</li>'));
		$("#datelist").listview('refresh');
	});
});