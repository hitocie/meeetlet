$(function() {
  $('#search').button();
  $('#search').click(function(){
    alert("検索");
  });
  
  $('#prevbutton').button({icons: {primary: "ui-icon-circle-triangle-w"}});
  $('#nextbutton').button({icons: {secondary: "ui-icon-circle-triangle-e"}});
  
  var dates = $( "#fromdate, #todate" ).datepicker({
    dateFormat: 'yy/mm/dd',
    numberOfMonths: 1,
    onSelect: function( selectedDate ) {
      var option = this.id == "fromdate" ? "minDate" : "maxDate",
        instance = $( this ).data( "datepicker" ),
        date = $.datepicker.parseDate(
          instance.settings.dateFormat || $.datepicker._defaults.dateFormat,
            selectedDate, instance.settings );
      dates.not( this ).datepicker( "option", option, date );
    }
  });

  // portlet
  $( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
    .find( ".portlet-header" )
    .addClass( "ui-widget-header ui-corner-all" )
    .prepend( "<span class='ui-icon ui-icon-plusthick'></span>")
    .end()
    .find( ".portlet-content" )
    .hide();
    
  $('.detailbutton').button({icons: {secondary: "ui-icon-circle-triangle-e"}});

  $( ".portlet-header .ui-icon" ).click(function() {
    $( this ).toggleClass( "ui-icon-plusthick" ).toggleClass( "ui-icon-minusthick" );
    $( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
  });

  $( ".column" ).disableSelection();
  
  // dialog
  $( "#detaildialog" ).dialog({
			autoOpen: false,
			height: 500,
			width: 500,
			modal: true,
			buttons: {
				"参加する！": function() {
      				alert("参加します");
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
  });
  $( ".detailbutton" ).button().click(function() {
    $( "#detaildialog" ).dialog( "open" );
  });
  
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

});