$(function() {
  // get me.
  if (typeof sessionStorage != 'undefined') {
    var sstr = sessionStorage;
    var me = JSON.parse(sstr.getItem("me"));
    if (me != null) {
      $('#myname').html(me.name);
    } else {
      get_me(function(me) {
        $('#myname').html(me.name);
      });		  
    }
  } else {
    get_me(function(me) {
      alert(me.name);
      $('#myname').html(me.name);
    });
  };
	  
  /// for web ui
  // dropdown
  $('.dropdown-toggle').dropdown();
  // arrange-btn
  $('#arrange-btn').click(function() {
	  $('#arrange-reply-modal').on('shown', function(){
		  $('#arrange-modal').off('hidden'); 
	  });
	  $('#arrange-modal').on('hidden', function() {
		  $('#arrange-reply-modal').modal({backdrop:false}).modal('show');
	  });
	  $('#arrange-modal').modal({backdrop:false}).modal('hide');
  });
  // close-back
  $('.close-back').click(function() {
	  $('#back-modal').modal('hide'); 
  });
  // arrange-reply-cancel-btn
  $('#arrange-reply-cancel-btn').click(function() {
	  $('#arrange-modal').on('shown', function(){
		  $('#arrange-reply-modal').off('hidden'); 
	  });
	  $('#arrange-reply-modal').on('hidden', function() {
		  $('#arrange-modal').modal({backdrop:false}).modal('show');
	  });
	  $('#arrange-reply-modal').modal('hide');
  });
  // datepicker
  $('#event-date').datepicker({minDate:new Date()});
  $('.date-choice').datepicker({minDate:new Date()});
  var dates = $( "#from, #to" ).datepicker({
	    minDate: new Date(),
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
  // popover for cdate-item
  $('.cdate-item').popover({
	  title: '参加できそうな人',
	  content: '<img src="images/friend1.png" width="30px"/><img src="images/friend1.png" width="30px"/><img src="images/friend1.png" width="30px"/><img src="images/friend1.png" width="30px"/>'
  });

  /// for server api
  // logout
  $('#logout').click(function() {
	  logout(function() {
		  document.location.href="index.html";
	  })
  });
 
  // get my event list. 
  get_my_events(true, true, function(data) {
	  if (data == null) {
		  return;
	  }
	  $('#event-table').html("");
	  var trstr = "";
	  for (var i in data) {
		  var event = data[i];
		  if (event.preEvent == null) {
			  trstr += '<tr><td><a id="'+event.id+'" href="#" class="invite-modal-btn" data-toggle="modal">'+event.title+'</a></td>'
			  + '<td>'+event.date+'</td><td>'+event.comment+'</td>'
			  + '<td><a href="#" rel="tooltip" title="'+event.owner.name+'"><img src="https://graph.facebook.com/'+event.owner.uid+'/picture" /></a></td>'
			  + '<td><a href="#" class="btn btn-small btn-info  invite-friends-btn"　data-toggle="modal">お誘い中</a></td></tr>';
		  } else {
			  trstr += '<tr><td><a id="'+event.id+'" href="#" class="arrange-modal-btn" data-toggle="modal">'+event.title+'</a></td>'
			  + '<td>'+ (event.date == null ? "未定" : event.date) +'</td><td>'+event.comment+'</td>'
			  + '<td><a href="#" rel="tooltip" title="'+event.owner.name+'"><img src="https://graph.facebook.com/'+event.owner.uid+'/picture" /></a></td>'
			  + '<td><a href="#" class="btn btn-small btn-info arrange-friends-btn" data-toggle="modal">お誘い中</a></td></tr>';
		  }
	  }
	  $('#event-table').append(trstr);
	  
	  // tooltip
	  $('a[rel="tooltip"]').tooltip({'placement':'right'});
	  
	  // invite-modal-btn
	  $('.invite-modal-btn').click(function() {
		  if (typeof sessionStorage != 'undefined') {
			  var sstr = sessionStorage;
			  var eid = $(this).attr('id');
			  var event = JSON.parse(sstr.getItem("event-"+eid));
			  if (event == null) {
				  get_event(eid, function(e) {
					  if (e.title == null) {
						  return;
					  }
					  setInviteModal(e);
					  $('#back-modal').modal('show');
					  $('#invite-modal').modal({backdrop:false}).modal('show');
					  sstr.setItem("event-"+eid, JSON.stringify(e));
				  });
			  } else {
				  setInviteModal(event);
				  $('#back-modal').modal('show');
				  $('#invite-modal').modal({backdrop:false}).modal('show');
			  }
		  } else {
			  get_event(eid, function(e) {
				  if (e.title == null) {
					  return;
				  }
				  setInviteModal(e);
				  $('#back-modal').modal('show');
				  $('#invite-modal').modal({backdrop:false}).modal('show');
				  sstr.setItem("event-"+eid, JSON.stringify(e));
			  });
		  }
	  });
	  
	  // arrange-modal-btn
	  $('.arrange-modal-btn').click(function() {
		  if (typeof sessionStorage != 'undefined') {
			  var sstr = sessionStorage;
			  var eid = $(this).attr('id');
			  var event = JSON.parse(sstr.getItem("event-"+eid));
			  if (event == null) {
				  get_event(eid, function(e) {
					  if (e.title == null) {
						  return;
					  }
					  setArrangeModal(e);
					  $('#back-modal').modal('show');
					  $('#arrange-modal').modal({backdrop:false}).modal('show');
					  sstr.setItem("event-"+eid, JSON.stringify(e));
				  });				  
			  } else {
				  setArrangeModal(event);
				  $('#back-modal').modal('show');
				  $('#arrange-modal').modal({backdrop:false}).modal('show');
			  }
		  } else {
			  get_event(eid, function(e) {
				  if (e.title == null) {
					  return;
				  }
				  serArrangeModal(e);
				  $('#back-modal').modal('show');
				  $('#arrange-modal').modal({backdrop:false}).modal('show');
				  sstr.setItem("event-"+eid, JSON.stringify(e));
			  });
		  }
	  });
	  
	  // invite-friends-btn
	  $('.invite-friends-btn').click(function() {
		  if (typeof sessionStorage != 'undefined') {
			  var sstr = sessionStorage;
			  var eid = $(this).parents("tr").children(":first-child").children(":first-child").attr('id');
			  var event = JSON.parse(sstr.getItem("event-"+eid));
			  if (event == null) {
				  get_event(eid, function(e) {
					  if (e.title == null) {
						  return;
					  }
					  setInviteFriendModal(e);
					  $('#back-modal').modal('show');
					  $('#invite-friends-modal').modal({backdrop:false}).modal('show');
					  sstr.setItem("event-"+eid, JSON.stringify(e));
				  });
			  } else {
				  setInviteFriendModal(event);
				  $('#back-modal').modal('show');
				  $('#invite-friends-modal').modal({backdrop:false}).modal('show');
			  }
		  } else {
			  get_event(eid, function(e) {
				  if (e.title == null) {
					  return;
				  }
				  setInviteFriendModal(e);
				  sstr.setItem("event-"+eid, JSON.stringify(e));
				  $('#back-modal').modal('show');
				  $('#invite-friends-modal').modal({backdrop:false}).modal('show');
			  });
		  }
	  });
	  
	  // arrange-friends-btn
	  $('.arrange-friends-btn').click(function() {
		  if (typeof sessionStorage != 'undefined') {
			  var sstr = sessionStorage;
			  var eid = $(this).parents("tr").children(":first-child").children(":first-child").attr('id');
			  var event = JSON.parse(sstr.getItem("event-"+eid));
			  if (event == null) {
				  get_event(eid, function(e) {
					  if (e.title == null) {
						  return;
					  }
					  setArrangeFriendModal(e);
					  $('#back-modal').modal('show');
					  $('#arrange-friends-modal').modal({backdrop:false}).modal('show');
					  sstr.setItem("event-"+eid, JSON.stringify(e));
				  });
			  } else {
				  setArrangeFriendModal(event);
				  $('#back-modal').modal('show');
				  $('#arrange-friends-modal').modal({backdrop:false}).modal('show');
			  }
		  } else {
			  get_event(eid, function(e) {
				  if (e.title == null) {
					  return;
				  }
				  setArrangeFriendModal(e);
				  sstr.setItem("event-"+eid, JSON.stringify(e));
				  $('#back-modal').modal('show');
				  $('#arrange-friends-modal').modal({backdrop:false}).modal('show');
			  });
		  }
	  });
	  
  });
  
  // get news list.
  if (typeof sessionStorage != 'undefined') {
	var sstr = sessionStorage;
	var news = JSON.parse(sstr.getItem("news"));
	if (news != null) {
		for (var i in news) {
			$('#news').append('<p><span class="news-date">['+news[i].date+']</span> '+news[i].content+'</p>');
		}
	} else {
		get_news(function(data) {
			for (var i in data) {
				$('#news').append('<p><span class="news-date">['+data[i].date+']</span> '+data[i].content+'</p>');
			}; 
		});	
	}
  } else {
	  // may use hash?
	  get_news(function(data) {
		  for (var i in data) {
			  $('#news').append('<p><span class="news-date">['+data[i].date+']</span> '+data[i].content+'</p>');
		  }; 
	  });
  }
});

// private method.
function setInviteModal(event) {
	$('#invite-modal-title').html(event.title);
	$('#invite-modal-date').html(event.date == null ? "" : event.date);
	$('#invite-modal-station').html(event.station.name == null ? "" : event.station.name);
	$('#invite-modal-category').html(event.genre.name == null ? "" : event.genre.name);
	$('#invite-modal-restaurant').html(event.shop == null ? "" : event.shop);
	$('#invite-modal-owner').html(event.owner.name);
	$('#invite-modal-max').html(event.maxNumber <= 1 ? "" : event.maxNumber);
	$('#invite-modal-comment').html(event.comment);
}

function setArrangeModal(event) {
	var cands = event.preEvent;
	var cdates = cands.dates;
	var cplaces = cands.cities;
	var cstations = cands.stations;
	var ccategs = cands.genres;
	var crests = cands.shops;
	
	// dates
	var dateStr = "";
	if (cdates != null) {
		for (var i=0; i<cdates.length; i++) {
			dateStr += cdates[i] + ', ';
		}
		dateStr = dateStr.slice(0, -2);
	}
	// places
	var placeStr = "";
	if (cplaces != null) {
		for (var i=0; i<cplaces.length; i++) {
			placeStr += cplaces[i].pref.name + '&nbsp;' + cplaces[i].name + ', ';
		}
		placeStr = placeStr.slice(0, -2);
	}
	// stations
	var stationStr = "";
	if (cstations != null) {
		for (var i=0; i<cstations.length; i++) {
			stationStr += cstations[i].train.name + '&nbsp;' + cstations[i].name + ', ';
		}
		stationStr = stationStr.slice(0, -2);
	}
	// categories
	var categStr = "";
	if (ccategs != null) {
		for (var i=0; i<ccategs.length; i++) {
			categStr += ccategs[i].name + ', ';
		}
		categStr = categStr.slice(0, -2);
	}
	// restaurants
	var restStr = "";
	if (crests != null) {
		for (var i=0; i<crests.length; i++) {
			restStr += '<a target="_BLANK" href="'+crests[i].url+'">'+crests[i].name+'</a>' + ', ';
		}
		restStr = restStr.slice(0, -2);
	}
	
	$('#arrange-modal-title').html(event.title);
	$('#arrange-modal-date').html(dateStr == "" ? "調整しましょう" : dateStr);
	$('#arrange-modal-place').html(placeStr == "" ? "調整しましょう" : placeStr);
	$('#arrange-modal-station').html(stationStr == "" ? "調整しましょう" : stationStr);
	$('#arrange-modal-category').html(categStr == "" ? "調整しましょう" : categStr);
	$('#arrange-modal-restaurant').html(restStr == "" ? "調整しましょう" : restStr);
	$('#arrange-modal-owner').html(event.owner.name);
	$('#arrange-modal-max').html(event.maxNumber <= 1 ? "特になし" : event.maxNumber);
	$('#arrange-modal-comment').html(event.comment);
	
	setArrangeReplyModal(event);
}

function setInviteFriendModal(event) {
	var parts = event.participants;
	if (event.participants == null || typeof event.participants == 'undefined') {
		return;
	}
	$('#invite-friends-modal-body').html("");
	var fstr = "";
	for (var i=0; i<parts.length; i++) {
		  var f = parts[i].friend;
		  fstr += '<tr><td><img src="https://graph.facebook.com/'+f.uid+'/picture" /></td>'
		  	+ '<td>'+f.name+'</td>'
		  	+ '<td>'+(parts[i].comment == null ? "" : parts[i].comment)+'</td>';
		  var attend = parts[i].attend;
		  if (attend == null) {
			  fstr += '<td><span class="label">未回答</span></td>'
		  } else if (attend == 0) {
			  fstr += '<td><span class="label label-success">参加</span></td>';
		  } else if (attend == 1) {
			  fstr += '<td><span class="label label-important">欠席</span></td>';
		  } else {
			  return;
		  }
		  fstr += '</tr>';
	  }	
	  $('#invite-friends-modal-body').append(fstr);
}

function setArrangeFriendModal(event) {
	var parts = event.participants;
	if (event.participants == null || typeof event.participants == 'undefined') {
		return;
	}
	$('#arrange-friends-accordion').html("");
	var fstr = "";
	for (var i=0; i<parts.length; i++) {
		var f = parts[i].friend;
		fstr += '<div class="accordion-group"><div class="accordion-heading">'
			+ '<a class="accordion-toggle" data-toggle="collapse" data-parent="#arrange-friends-accordion" data-target="#friend'+f.id+'" style="text-decoration:none">'
			+ '<img src="https://graph.facebook.com/'+f.id+'/picture" />'
			+ '<span class="both-padding">'+f.name+'</span>';
		var attend = parts[i].attend;
		if (attend == null) {
			fstr += '<span class="label">未回答</span>';
		} else if (attend == 0) {
			fstr += '<span class="label label-success">参加</span>';
		} else if (attend == 1) {
			fstr += '<span class="label label-important">欠席</span>';
		} else {
			return;
		};
		fstr += '</a></div>';
		
		fstr += '<div id="friend'+f.id+'" class="accordion-body collapse">'
			+ '<div class="accordion-inner">';
		var prePart = parts[i].preParticipant;
		if (typeof prePart != 'undefined') {
			fstr += '<table class="table"><tbody>';
			// dates.
			var cdates = event.preEvent.dates;
			var rdates = prePart.dates;
			fstr += '<tr><td class="skyback">希望日程</td><td>';
			var dstr = "";
			for (var j=0; j<cdates.length; j++) {
				if (rdates[j] == 1) {dstr += cdates[j] + ', ';};
			}
			if (dstr.length > 0) {dstr = dstr.slice(0, -2);};
			fstr += dstr + '</td></tr>';
			// prefectures.
			var cprefs = event.preEvent.cities;
			var rprefs = prePart.cities;
			fstr += '<tr><td class="skyback">希望地域</td><td>';
			var pstr = "";
			for (var j=0; j<cprefs.length; j++) {
				if (rprefs[j] == 1) {pstr += cprefs[j].pref.name + '&nbsp;' + cprefs[j].name + ', ';};
			}
			if (pstr.length > 0) {pstr = pstr.slice(0, -2);};
			fstr += pstr + '</td></td>';
			// stations.
			var cstations = event.preEvent.stations;
			var rstations = prePart.stations;
			fstr += '<tr><td class="skyback">駅の希望</td><td>';
			var sstr = "";
			for (var j=0; j<cstations.length; j++) {
				if (rstations[j] == 1) {sstr += cstations[j].name + '[' + cstations[i].train.name + ']' + ', ';};
			}
			if (sstr.length > 0) {sstr = sstr.slice(0, -2);};
			fstr += sstr + '</td></td>';
			// categories.
			var ccategs = event.preEvent.genres;
			var rcategs = prePart.genres;
			fstr += '<tr><td class="skyback">希望ジャンル</td><td>';
			var cstr = "";
			for (var j=0; j<ccategs.length; j++) {
				if (rcategs[j] == 1) {cstr += ccategs[j].name + ', ';};
			}
			if (cstr.length > 0) {cstr = cstr.slice(0, -2);};
			fstr += cstr + '</td></td>';
			// restaurants.
			var crests = event.preEvent.shops;
			var rrests = prePart.shops;
			fstr += '<tr><td class="skyback">店の希望</td><td>';
			var rstr = "";
			for (var j=0; j<crests.length; j++) {
				if (rrests[j] == 1) {rstr = crests[j].name + ', ';}
			}
			if (rstr.length > 0) {rstr = rstr.slice(0, -2);};
			fstr += rstr + '</td></td>';
			// comment.
			fstr += '<tr><td class="skyback">コメント</td><td>'+prePart.comment+'</td></tr>';
			fstr += '</tbody></table>';			
		} else {
			fstr += '<span class="gray-comment">回答待ちです。</span>';
		}
		fstr += '</div></div></div>';
	};
	$('#arrange-friends-accordion').append(fstr);
}

function setArrangeReplyModal(event) {
	var cands = event.preEvent;
	var cdates = cands.dates;
	var cstations = cands.stations;
	var ccategs = cands.genres;
	var crests = cands.shops;
	
	// dates
	var dateStr = "";
	if (cdates != null) {
		for (var i=0; i<cdates.length; i++) {
			dateStr += cdates[i] + ', ';
		}
		dateStr = dateStr.slice(0, -2);
	}
	// stations
	var stationStr = "";
	if (cstations != null) {
		for (var i=0; i<cstations.length; i++) {
			stationStr += cstations[i].train.name + '&nbsp;' + cstations[i].name + ', ';
		}
		stationStr = stationStr.slice(0, -2);
	}
	// categories
	var categStr = "";
	if (ccategs != null) {
		for (var i=0; i<ccategs.length; i++) {
			categStr += ccategs[i].name + ', ';
		}
		categStr = categStr.slice(0, -2);
	}
	// restaurants
	var restStr = "";
	if (crests != null) {
		for (var i=0; i<crests.length; i++) {
			restStr += '<a target="_BLANK" href="'+crests[i].url+'">'+crests[i].name+'</a>' + ', ';
		}
		restStr = restStr.slice(0, -2);
	}
	
	$('#arrange-reply-title').html(event.title);
	$('#arrange-reply-date').html(dateStr == "" ? "調整しましょう" : dateStr);
	$('#arrange-reply-station').html(stationStr == "" ? "調整しましょう" : stationStr);
	$('#arrange-reply-category').html(categStr == "" ? "調整しましょう" : categStr);
	$('#arrange-reply-restaurant').html(restStr == "" ? "調整しましょう" : restStr);
	
	
	// date table.
	var dateItems = "";
	for (var i=0; i<cdates.length; i++) {
		dateItems += '<tr><td><span class="cdate-item">'+cdates[i]+'</span></td>'
			+ '<td><input type="radio" name="dradio'+i+'" id="OKdradio'+i+'" value="'+cdates[i]+'" checked /></td>'
			+ '<td><input type="radio" name="dradio'+i+'" id="NGdradio'+i+'" value="NG" /></td></tr>'	
	}
	$('#arrange-reply-date-body').html(dateItems);
	
	// station table.
	var stationItems = "";
	for (var i=0; i<cstations.length; i++) {
		stationItems += '<tr><td>'+cstations[i].name+'['+cstations[i].train.name+']</td>'
			+ '<td><input type="radio" name="station'+i+'" id="OKStation'+i+'" value="OK" checked /></td>'
			+ '<td><input type="radio" name="station'+i+'" id="NGStation'+i+'" value="NG" /></td></tr>'
	}
	$('#arrange-reply-station-body').html(stationItems);
	
	// category table.
	var categoryItems = "";
	for (var i=0; i<ccategs.length; i++) {
		categoryItems += '<tr><td>'+ccategs[i].name+'</td>'
			+ '<td><input type="radio" name="category'+i+'" id="OKCategory'+i+'" value="OK" checked /></td>'
			+ '<td><input type="radio" name="category'+i+'" id="NGCategory'+i+'" value="NG" /></td></tr>'
	}
    $('#arrange-reply-category-body').html(categoryItems);
    
    // restaurant table.
    var restaurantItems = "";
    for (var i=0; i<crests.length; i++) {
    	restaurantItems += '<tr><td>'+crests[i].name+'</td>'
    		+ '<td><div class="btn-group pull-right">'
    		+ '<a href="#" class="btn btn-info btn-mini" id="like-btn1">いいね！</a>'
    		+ '<a href="#" class="btn btn-mini" id="hate-btn1">いまいち！</a>'
    		+ '</div></td></tr>'
    }
    $('#arrange-reply-restaurant-body').html(restaurantItems);
}