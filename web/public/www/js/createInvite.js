$(function() {
  /// for web ui
  // dropdown
  $('.dropdown-toggle').dropdown();
  // datepicker
  $('#event-date').datepicker({
	  minDate:new Date(),
	  dateFormat: 'yy年m月d日'
  });
  
  /// for service api
  // prefecture
  if (sessionStorage != 'undefined') {
	  var sstr = sessionStorage;
	  var prefs = JSON.parse(sstr.getItem("prefectures"));
	  if (prefs != null) {
		  for (var i in prefs) {
			  $('#event-prefecture').append('<option value="'+prefs[i].id+'">'+prefs[i].name+'</option>');
		  }
	  } else {
		  get_all_prefectures(function(data) {
			  for (var i in data) {
				  $('#event-prefecture').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			  }; 
		  });	
	  }
  } else {
	  get_all_prefectures(function(data) {
		  for (var i in data) {
			  $('#event-prefecture').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  }; 
	  });	  
  }
  
  // category
  if (sessionStorage != 'undefined') {
	var sstr = sessionStorage;
	var categories = JSON.parse(sstr.getItem("categories"));
	if (categories != null) {
		for (var i in categories) {
			$('#event-category').append('<option>'+categories[i].name+'</option>');
		}
	} else {
		get_all_genres(function(data) {
			$('#event-category').append('<option>'+categories[i].name+'</option>');
		});
	}
  } else {
	  get_all_genres(function(data) {
		  $('#event-category').append('<option>'+categories[i].name+'</option>');
	  });
  }
  
  // me
  if (sessionStorage != 'undefined') {
	  var sstr = sessionStorage;
	  var mydata = JSON.parse(sstr.getItem("me"));
	  if (mydata != null) {
			   $('#event-friends').append(
					   '<a href="#" rel="tooltip" title="'+mydata.name+'">'
					   + '<img src="https://graph.facebook.com/' + mydata.id + '/picture" /></a>'
			   );
	  }
  }
  // friend
  if (sessionStorage != 'undefined') {
	  var sstr = sessionStorage;
		var friends = JSON.parse(sstr.getItem("friends"));
		if (friends != null) {
			for (var i in friends) {
				$('#friends-table').append(
					'<tr><td><input type="checkbox" class="checkbox friend-cb" name="invited"/></td>'
					+ '<td><img src="https://graph.facebook.com/' + friends[i].uid + '/picture" /></td>'
					+ '<td class="fname">'+friends[i].name+'</td></tr>'
				);
			}
		} else {
			get_my_friends(function(data) {
				  for (i in data) {
			          $('#friends-table').append(
				        '<tr><td><input type="checkbox friend-cb" class="checkbox" name="invited"/></td>'
					    + '<td><img src="https://graph.facebook.com/' + data[i].uid + '/picture" /></td>'
					    + '<td class="fname">'+data[i].name+'</td></tr>'
				      );
				  }
			  });
		}
	  } else {
		  get_my_friends(function(data) {
			  for (i in data) {
		          $('#friends-table').append(
			        '<tr><td><input type="checkbox" class="checkbox friend-cb" name="invited"/></td>'
				    + '<td><img src="https://graph.facebook.com/' + data[i].uid + '/picture" /></td>'
				    + '<td class="fname">'+data[i].name+'</td></tr>'
			      );
			  }
		  });
	  }

  // event confirm btn
  $('#confirm-btn').click(function() {
	  $('.control-group').removeClass('error');
	  
	  var this_title = $('#event-title').val();
	  var this_date = $('#event-date').val();
	  var this_pref = $('#event-prefecture option:selected').text();
	  var this_train = $('#event-train option:selected').text();
	  var this_station = $('#event-station option:selected').text();
	  
	  // verify all required items.
	  if (this_title == "" || this_date == "" || this_pref == "" || this_train == "" || this_station == "") {
		  if (this_title == "") {
			  $('#title-control').addClass('error');
		  }
		  
		  if (this_date == "") {
			  $('#date-control').addClass('error');
		  }
		  
		  if (this_pref == "") {
			  $('#prefecture-control').addClass('error');
		  }
		  
		  if (this_train == "") {
			  $('#train-control').addClass('error');
		  }
		  
		  if (this_station == "") {
			  $('#station-control').addClass('error');
		  }
		  return false;
	  }
	  
	  $('#ctitle').text(this_title);
	  $('#cdate').text(this_date);
	  var station_text = this_pref+' '+this_train+' '+this_station;
	  $('#cstation').text(station_text);
	  $('#ccategory').text($('#event-category').val());
	  $('#crestaurant').text($('#event-venue').val());
	  $('#cmax').text($('#event-max').val());
	  $('#ccomment').text($('#event-comment').val());
	  
	  $('#confirm-event').modal('show'); 
  });
  
  // friend select button.
  $('#friend-select-btn').click(function() {
	  var myname = $('#event-friends').find('a').attr('data-original-title');
	  var mysrc = $('#event-friends').find('img').attr('src');
	  $('#event-friends').html(
			  '<a href="#" rel="tooltip" title="'+myname+'">'
			  + '<img src="'+mysrc+'" /></a>'
	  );
	  $.each($('.friend-cb'), function(i) {
		  if ($(this).attr('checked')) {
			  var fsrc = $(this).parent().parent().find('img').attr('src');
			  var fname = $(this).parent().parent().find('.fname').text();
			  $('#event-friends').append(
					   '<a href="#" rel="tooltip" title="'+fname+'">'
					   + '<img src="'+fsrc+'" /></a>'
			  );
		  }
	  });
	  $('a[rel="tooltip"]').tooltip({'placement':'right'});
	  $('#invite-friends-list').modal('hide');
  });
  
  // prefecture select change.
  $('#event-prefecture').change(function() {
	  $('#event-train').html('<option></option>');
	  get_trains($('#event-prefecture').val(), function(data) {
		  for (var i in data) {
			  $('#event-train').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  }; 
	  });
	  $('#train-control').removeClass('warning');
	  $('#station-control').addClass('warning');
	  $('#event-station').html('<option>路線を選んでください</option>').attr('disabled', 'disabled');
	  $('#event-train').removeAttr('disabled');
  })
  
  // train select change.
  $('#event-train').change(function() {
	  $('#event-station').html('<option></option>');
	  get_stations($('#event-train').val(), function(data) {
		  for (var i in data) {
			  $('#event-station').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  };
	  });
	  $('#station-control').removeClass('warning');
	  $('#event-station').removeAttr('disabled');
  });
  
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
});