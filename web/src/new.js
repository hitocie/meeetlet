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

// date Calender
$("#dateCal").datepicker({minDate: new Date()});

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