$(function() {  
// menu
var timeout = 200;
var closetimer = 0;
var ddmenuitem = 0;

function umenu_open()
{  umenu_canceltimer();
   umenu_close();
   ddmenuitem = $(this).find('ul').css('visibility', 'visible');}

function umenu_close()
{  if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}

function umenu_timer()
{  closetimer = window.setTimeout(umenu_close, timeout);}

function umenu_canceltimer()
{  if(closetimer)
   {  window.clearTimeout(closetimer);
      closetimer = null;}}

$(document).ready(function()
{
   $('#umenu > li').bind('mouseover', umenu_open);
   $('#umenu > li').bind('mouseout',  umenu_timer);
	});

document.onclick = umenu_close;

// date Calendar
//// calendar setting
$(".dateCal").datepicker({minDate: new Date()});
var dates = $( "#from, #to" ).datepicker({
	onSelect: function( selectedDate ) {
		var option = this.id == "from" ? "minDate" : "maxDate",
			instance = $( this ).data( "datepicker" ),
			date = $.datepicker.parseDate(
				instance.settings.dateFormat ||
				$.datepicker._defaults.dateFormat,
				selectedDate, instance.settings );
		dates.not( this ).datepicker( "option", option, date );
	}
});

function addMultiCalendar() {
	var txt=$("<p><input type='text' id='multiCal6' class='dateCal'></p>");
	$("#multiCalList").append(txt);
}
$("#addCalendarButton")
	.button({icons: {secondary: "ui-icon-circle-plus"}})
	.click(function() {addMultiCalendar();});

// drag and drop
$('.fimg').draggable({
  opacity: 0.5,
  revert: "invalid",
  helper: "clone",
  cursor: "move",
  scroll: false
});

$('.inviteList').droppable({
  accept: ".fimg",
  activeClass: "ui-state-hover",
  hoverClass: "ui-state-active",
  drop: function(event, ui) {
    deleteImage(ui.draggable);
  }
});

function deleteImage( $item ) {
	if($item.hasClass('added')) return;
	$item.addClass('added');
	var clone = $item.clone();
	clone.appendTo( $('.inviteList')).dblclick(function() {$(this).remove();});
}

$('#initSelectButton')
	.button()
	.click(function() {
		$('.inviteList').empty();
	});

//create event button
$('#createEventButton')
	.button()
	.click(function() {
		var title = $('#titleText').val();
		var edate = $('#dateCal').val();
		var pref = $('#prefecture').val();
		var station = $('#station').val();
		var venue = $('#venue').val();
		var max = $('#max').val();
		var comment = $('#comment').val();
		var invList = $('.inviteList').children();
		var invIdList = "";
		for (var i=0; i<invList.length; i++) {
			invIdList += invList[i].id + ' ';
		}
		alert(title+' '+edate+' '+pref+' '+station+' '+venue+' '+max+' '+comment+' '+invIdList);
	});

});
