$(function() {   
  // portlet
  $( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
    .find( ".portlet-header" )
    .addClass( "ui-widget-header ui-corner-all" )
    .prepend( "<span class='ui-icon ui-icon-plusthick'></span>")
    .end()
    .find( ".portlet-content" )
    .hide();

  $( ".portlet-header .ui-icon" ).click(function() {
	    $( this ).toggleClass( "ui-icon-plusthick" ).toggleClass( "ui-icon-minusthick" );
	    $( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
	  });
  
  $( ".column" ).disableSelection();
  
  // menu
  var timeout = 200;
  var closetimer = 0;
  var ddmenuitem = 0;

  function umenu_open() {
	  umenu_canceltimer();
	  umenu_close();
	  ddmenuitem = $(this).find('ul').css('visibility', 'visible');
  }

  function umenu_close() {
	  if (ddmenuitem) ddmenuitem.css('visibility', 'hidden');
  }

  function umenu_timer() {
	  closetimer = window.setTimeout(umenu_close, timeout);
  }

  function umenu_canceltimer() {
	  if(closetimer) {
		  window.clearTimeout(closetimer);
		  closetimer = null;
	  }
  }

  $(document).ready(function() {
	  $('#umenu > li').bind('mouseover', umenu_open);
	  $('#umenu > li').bind('mouseout',  umenu_timer);
  });

  document.onclick = umenu_close;
  
  // button
  //// event button
  function showEventDetail(eid) {
	  $('.showNow')
	  	.removeClass('showNow')
	  	.fadeOut(100, function() {$('.event-detail').addClass('showNow').fadeIn(300, null);});
	  return false;
  }
  $('.eventDetailButton')
  	.button({icons: {secondary: "ui-icon-circle-triangle-e"}})
  	.click(function(){showEventDetail(1)});
  
  ////arrange button
  function showArrangeDetail(eid) {
	  $('.showNow')
	  	.removeClass('showNow')
	  	.fadeOut(100, function() {$('.arrange-detail').addClass('showNow').fadeIn(300, null);});
	  return false
  }
  $('.arrangeDetailButton')
  	.button({icons: {secondary: "ui-icon-circle-triangle-e"}})
  	.click(function(){showArrangeDetail(1)});
  
  //// public button
  function showPublicDetail(eid) {
	  $('.showNow')
	  	.removeClass('showNow')
	  	.fadeOut(100, function() {$('.public-detail').addClass('showNow').fadeIn(300, null);});
	  return false;
  }
  $('.publicDetailButton')
  	.button({icons: {secondary: "ui-icon-circle-triangle-e"}})
  	.click(function(){showPublicDetail(1)});
  
  //// attend button
  $('.attendbutton').button({icons: {secondary: "ui-icon-star"}});
  
  //// arrange button
  function arrangeEvent(eid) {
	  $('.showNow')
	  	.removeClass('showNow')
	  	.fadeOut(100, function() {$('.arrangeEvent').addClass('showNow').fadeIn(300, null);})
  }
  $('.arrangebutton')
  	.button({icons: {secondary: "ui-icon-star"}})
  	.click(function(){arrangeEvent(1);});

  //// absent button
  function absentEvent(eid) {
	 alert("欠席されたイベントを欠席イベントにします。"); 
  }
  $('.absentbutton')
  	.button({icons: {secondary: "ui-icon-closethick"}})
  	.click(function(){absentEvent(1);});

  //// invited friends button
  function showFriendsList(eid) {
	  alert("show friends!");
  }
  $('.invitedFriendsButton')
  	.button({icons: {}})
  	.click(function(){showFriendsList(1);});
  	
  //// cancel button
  function cancelArrange() {
	  $('.showNow')
	  	.removeClass('showNow')
	  	.fadeOut(100, function() {$('.arrange-detail').addClass('showNow').fadeIn(300, null);});
  }
  $('.cancelbutton')
  	.button({icons: {secondary: "ui-icon-closethick"}})
  	.click(function(){cancelArrange();});
  
  //// reply button
  function replyArrange(eid) {
	  alert("調整を反映しました。");
  }
  $('.replybutton')
  	.button({icons: {secondary: "ui-icon-mail-open"}})
  	.click(function(){replyArrange(1);});
  
});