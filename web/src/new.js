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
//// select Calendar type
$('#cal').click(function(){
	$('.showNow')
		.removeClass('showNow')
		.fadeOut(100, function() {$('#calCalendar').addClass('showNow').fadeIn(100, null);});
});
$('#multi').click(function(){
	$('.showNow')
		.removeClass('showNow')
		.fadeOut(100, function() {$('#multiCalendar').addClass('showNow').fadeIn(100, null);});
})
$('#fromTo').click(function(){
	$('.showNow')
		.removeClass('showNow')
		.fadeOut(100, function() {$('#fromToCalendar').addClass('showNow').fadeIn(100, null);});
})
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
	$("#mulitCalList").append(txt);
	alert($("#mulitCalList").html());
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
				$item.find( "a.ui-icon-trash" ).remove();
				$item.appendTo( $('.inviteList')).fadeIn(function() {
					$item
						.animate({ width: "48px" })
						.find( "img" )
							.animate({ height: "36px" });
				});
}

});