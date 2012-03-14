var DATE_TABLE_SIZE = 14;

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
  // text in alert.
  $('#date-table-alert').text(DATE_TABLE_SIZE+'日以上は選択できません。');
  // dropdown
  $('.dropdown-toggle').dropdown();
  // datepicker
  $('#event-date').datepicker({
	  minDate:new Date(),
	  dateFormat: 'yy年m月d日'
  });
  $('#date-choice').datepicker({
	  minDate:new Date(),
	  dateFormat: 'yymmdd',
	  altFormat: 'yy年m月d日',
	  altField: '#altDate',
	  numberOfMonths: 3
  });
  var dates = $( "#from, #to" ).datepicker({
	    minDate: new Date(),
	    dateFormat: 'yy年m月d日',
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

  /// for data
  // all prefectures
  if (sessionStorage != 'undefined') {
	  var sstr = sessionStorage;
	  var prefs = JSON.parse(sstr.getItem("prefectures"));
	  if (prefs != null) {
		  for (var i in prefs) {
			  $('#prefecture-select').append('<option value="'+prefs[i].id+'">'+prefs[i].name+'</option>');
		  }
	  } else {
		  get_all_prefectures(function(data) {
			  for (var i in data) {
				  $('#prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			  }; 
		  });	
	  }
  } else {
	  get_all_prefectures(function(data) {
		  for (var i in data) {
			  $('#prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  }; 
	  });	  
  }
  
  // category
  if (sessionStorage != 'undefined') {
	var sstr = sessionStorage;
	var categories = JSON.parse(sstr.getItem("categories"));
	if (categories != null) {
		for (var i in categories) {
			$('#category-select').append('<option value="'+categories[i].id+'">'+categories[i].name+'</option>');
		}
	} else {
		get_all_genres(function(data) {
			$('#category-select').append('<option value="'+categories[i].id+'">'+categories[i].name+'</option>');
		});
	}
  } else {
	  get_all_genres(function(data) {
		  $('#category-select').append('<option value="'+categories[i].id+'">'+categories[i].name+'</option>');
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
  
  /// for service api
  // event confirm btn
  $('#confirm-btn').click(function() {
	  $('.control-group').removeClass('error');
	  
	  var this_title = $('#event-title').val();
	  var this_date = $('#event-date').val();
	  
	  // verify all required items.
	  if (this_title == "" || this_date == "") {
		  if (this_title == "") {
			  $('#title-control').addClass('error');
		  }
		  
		  if (this_date == "") {
			  $('#date-control').addClass('error');
		  }
		  return false;
	  }
	  
	  // set 'omakase' if the value is not set.
	  var this_pref = $('#event-prefecture').val();
	  if (this_pref == "") {
		  this_pref = "おまかせ";
	  }
	  
	  var this_station = $('#event-station').val();
	  if (this_station == "") {
		  this_station = "おまかせ";
	  }
	  
	  var this_category = $('#event-category').val();
	  if (this_category == "") {
		  this_category = "おまかせ";
	  }
	  
	  $('#ctitle').text(this_title);
	  $('#cdate').text(this_date);
	  $('#cpref').text(this_pref);
	  $('#cstation').text(this_station)
	  $('#ccategory').text(this_category);
	  $('#crestaurant').text($('#event-venue').val());
	  $('#cmax').text($('#event-max').val());
	  $('#ccomment').text($('#event-comment').val());
	  
	  $('#confirm-event').modal('show'); 
  });
  
  // prefecture select change.
  $('#prefecture-select').change(function() {
	  $('#train-select').html("<option></option>");
	  get_trains($('#prefecture-select').val(), function(data) {
		  for (var i in data) {
			  $('#train-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  }; 
	  });
	  
	  $('#train-control').removeClass('warning');
	  $('#station-control').addClass('warning');
	  $('#station-select').html('').attr('disabled', 'disabled');
	  $('#train-select').removeAttr('disabled');
  });
  
  // train select change.
  $('#train-select').change(function() {
	  $('#station-select').html('<option></option>');
	  get_stations($('#train-select').val(), function(data) {
		  for (var i in data) {
			  $('#station-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  };
	  });
	  $('#station-control').removeClass('warning');
	  $('#station-select').removeAttr('disabled');
  });

  // show prefecture modal
  $('#prefecture-modal-btn').click(function() {
	  $('#station-table').find('tbody').html($('#station-list-table').find('tbody').html());
	  $(".station-del").click(function() {
		  $(this).parents("tr").remove();
	  });
	  $('#prefecture-modal').modal('show');
  });
  
  // add station button
  $('#add-station-btn').click(function() {
	  if ($('#station-select').attr('disabled')) {
		  return;
	  }
	  if ($('#station-select option:selected').text() == "") {
		  return;
	  }
	  if ($("#"+$('#station-select option:selected').val()).attr("id")) {
		  return;
	  }
	  var tb = $('#station-table').find('tbody');
	  tb.append(
			  '<tr id="'+$('#station-select option:selected').val()+'"><td>'
			  +$('#prefecture-select option:selected').text()+'</td>'
			  +'<td>'+$('#train-select option:selected').text()+'</td>'
			  + '<td>'+$('#station-select option:selected').text()+'</td>'
			  + '<td><a href="#" class="btn btn-danger btn-mini station-del">削除</a></td></tr>'
	  );
	  $(".station-del").click(function() {
		  $(this).parents("tr").remove();
	  });
  });
  
  $('#set-station-btn').click(function() {
	  $('#station-list-table').find('tr').remove();
	  $('#station-list-table').append($('#station-table').find('tbody').html());
	  $(".station-del").click(function() {
		  $(this).parents("tr").remove();
	  });
	  $('#prefecture-modal').modal('hide');
  });
 
  // show date modal
  $('#date-modal-btn').click(function() {
	  $('#date-table').find('tbody').html($('#date-list-table').find('tbody').html());
	  $(".date-del").click(function() {
		  $(this).parents("tr").remove();
		  if ($("#date-table").find('tr').length < DATE_TABLE_SIZE) {
			  $('#date-table-alert').hide();
		  }
	  });
	  $('#date-modal').modal('show');
  });
  
  // add choice date button
  $('#add-choice-date-btn').click(function() {
	  if ($("#"+$('#date-choice').datepicker('option', "dateFormat", 'yymmdd').val()).attr("id")) {
		  return;
	  }
	  if ($("#date-table").find('tr').length >= DATE_TABLE_SIZE) {
		  $('#date-table-alert').show();
		  return;
	  }
	  $('#date-table-alert').hide();
	  $('#date-table').find('.fromto').remove();
	  var tb = $('#date-table').find('tbody');
	  tb.append(
			  '<tr id="'+$('#date-choice').datepicker('option', "dateFormat", 'yymmdd').val()+'">'
			  + '<td>'+$('#date-choice').datepicker('option', "dateFormat", 'yy年m月d日').val()+'</td>'
			  + '<td><a href="#" class="btn btn-danger btn-mini date-del">削除</a></td></tr>'
	  );
	  $(".date-del").click(function() {
		  $(this).parents("tr").remove();
		  if ($("#date-table").find('tr').length < DATE_TABLE_SIZE) {
			  $('#date-table-alert').hide();
		  }
	  });
  });
  
  // add period date button
  $('#add-period-date-btn').click(function() {
	  var delta = (($('#to').datepicker('getDate'))-($('#from').datepicker('getDate')))/24/60/60/1000 + 1;
	  if (delta > DATE_TABLE_SIZE) {
		  $('#date-table-alert').show();
		  return;
	  }
	  $('#date-table-alert').hide();
	  var tb = $('#date-table').find('tbody').html('');
	  tb.append(
			  '<tr class="fromto" id="'+$('#from').datepicker('option', "dateFormat", 'yymmdd').val()+'-'+$('#to').datepicker('option', "dateFormat", 'yymmdd').val()+'">'
			  + '<td>'+$('#from').datepicker('option', "dateFormat", 'yy年m月d日').val()+'から'+$('#to').datepicker('option', "dateFormat", 'yy年m月d日').val()+'まで'+'</td></tr>'
	  );
  });
  
  // set date button.
  $('#set-date-btn').click(function() {
	  $('#date-list-table').find('tr').remove();
	  $('#date-list-table').append($('#date-table').find('tbody').html());
	  $(".date-del").click(function() {
		  $(this).parents("tr").remove();
		  if ($("#date-table").find('tr').length < DATE_TABLE_SIZE) {
			  $('#date-table-alert').hide();
		  }
	  });
	  $('#date-modal').modal('hide');
  });
  
  // set category button.
  $('#set-category-btn').click(function() {
     $('#category-modal').modal('hide');
	 $('#category-list-table').find('tbody').remove();
	 var tblstr = "";
	 $('#category-select option:selected').each(function() {
		 tblstr = tblstr+'<tr id="'+$(this).val()+'"><td>'+$(this).text()+'</td></tr>';
	 });
	 $('#category-list-table').append('<tbody>'+tblstr+'</tbody>');
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
  
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
  
});