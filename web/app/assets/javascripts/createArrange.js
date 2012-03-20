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

  $('#event-deadline').datepicker({
	  minDate:new Date(),
	  dateFormat: 'yy年m月d日'
  });

  /// for data
  // all prefectures
  if (sessionStorage != 'undefined') {
	  var sstr = sessionStorage;
	  var prefs = JSON.parse(sstr.getItem("prefectures"));
	  if (prefs != null) {
		  for (var i in prefs) {
			  $('#prefecture-select').append('<option value="'+prefs[i].id+'">'+prefs[i].name+'</option>');
			  $('#city-prefecture-select').append('<option value="'+prefs[i].id+'">'+prefs[i].name+'</option>');
		  }
	  } else {
		  get_all_prefectures(function(data) {
			  for (var i in data) {
				  $('#prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
				  $('#city-prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			  }; 
		  });	
	  }
  } else {
	  get_all_prefectures(function(data) {
		  for (var i in data) {
			  $('#prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			  $('#city-prefecture-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
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
					'<tr><td><input type="checkbox" class="checkbox friend-cb" name="invited" value="'+friends[i].uid+'"/></td>'
					+ '<td><img src="https://graph.facebook.com/' + friends[i].uid + '/picture" /></td>'
					+ '<td class="fname">'+friends[i].name+'</td></tr>'
				);
			}
		} else {
			get_my_friends(function(data) {
				  for (i in data) {
			          $('#friends-table').append(
				        '<tr><td><input type="checkbox friend-cb" class="checkbox" name="invited" value="'+data[i].uid+'"/></td>'
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
			        '<tr><td><input type="checkbox" class="checkbox friend-cb" name="invited" value="'+data[i].uid+'"/></td>'
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
	  
	  //get date
	  var dlist = $('#date-list-table').find('tr');

	  var this_deadline = $('#event-deadline').val();
	  
	  // verify all required items.
	  if (this_title == "" || dlist.length == 0 || this_deadline == "") {
		  if (this_title == "") {
			  $('#title-control').addClass('error');
		  }
		  
		  if (dlist.length == 0) {
			  $('#date-control').addClass('error');
		  }
		  
		  if (this_deadline == "") {
			  $('#deadline-control').addClass('error');
		  }
		  return false;
	  }
	  
	  // date string.
	  var this_date = "";
	  var dvals = "";
	  for(var i=0; i<dlist.length; i++) {
		  this_date += dlist[i].childNodes[0].innerText + ", ";
		  dvals += '<input type="hidden" name="dvals" value="'+dlist[i].id+'" />';
	  }
	  this_date = this_date.slice(0, -2);

	  // city string.
	  var clist = $('#city-list-table').find('tr');
	  var this_city = "";
	  var cvals = "";
	  if (clist.length == 0) {
		  this_city = "おまかせ";
	  } else {
		  for (var i=0; i<clist.length; i++) {
			  this_city += clist[i].childNodes[0].innerText + " " + clist[i].childNodes[1].innerText + ", ";
			  cvals += '<input type="hidden" name="cvals" value="'+clist[i].id+'" />';
		  }
		  this_city = this_city.slice(0, -2);
	  }
	  
	  // station string.
	  var slist = $('#station-list-table').find('tr');
	  var this_station = "";
	  var svals = "";
	  if (slist.length == 0) {
		  this_station = "おまかせ";
	  } else {
		  for (var i=0; i<slist.length; i++) {
			  this_station += slist[i].childNodes[0].innerText + " " + slist[i].childNodes[1].innerText + " " + slist[i].childNodes[2].innerText + ", ";
			  svals += '<input type="hidden" name="svals" value="'+slist[i].id+'" />';
		  }
		  this_station = this_station.slice(0, -2);
	  }
	  
	  var catlist = $('#category-list-table').find('tr');
	  var this_category = "";
	  var catvals = "";
	  if (catlist.length == 0) {
		  this_category = "おまかせ";
	  } else {
		  for (var i=0; i<catlist.length; i++) {
			  this_category += catlist[i].childNodes[0].innerText + ", ";
			  catvals += '<input type="hidden" name="catvals" value="'+catlist[i].id+'" />';
		  }
		  this_category = this_category.slice(0, -2);
	  }
	  
	  $('#ctitle').text(this_title);
	  $('#cdate').text(this_date);
	  $('#cdate').append(dvals);
	  $('#ccity').text(this_city);
	  $('#ccity').append(cvals);
	  $('#cstation').text(this_station);
	  $('#cstation').append(svals);
	  $('#ccategory').text(this_category);
	  $('#ccategory').append(catvals);
	  $('#crestaurant').text($('#event-venue').val());
	  $('#cmax').text($('#event-max').val());
	  $('#cdeadline').text(this_deadline);
	  $('#ccomment').text($('#event-comment').val());
	  
	  $('#confirm-event').modal('show'); 
  });
  
  // city prefecture select change.
  $('#city-prefecture-select').change(function() {
	  $('#city-city-select').html("<option></option>");
	  get_cities($('#city-prefecture-select').val(), function(data) {
		  for (var i in data) {
			  $('#city-city-select').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		  }; 
	  });
	  
	  $('#city-city-control').removeClass('warning');
	  $('#city-city-select').removeAttr('disabled');
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

  // show city modal
  $('#city-modal-btn').click(function() {
	  $('#city-table').find('tbody').html($('#city-list-table').find('tbody').html());
	  $(".city-del").click(function() {
		  $(this).parents("tr").remove();
	  });
	  $('#city-modal').modal('show');
  });
 
  // add city button
  $('#add-city-btn').click(function() {
	  if ($('#city-city-select').attr('disabled')) {
		  return;
	  }
	  if ($('#city-city-select option:selected').text() == "") {
		  return;
	  }
	  if ($("#"+$('#city-city-select option:selected').val()).attr("id")) {
		  return;
	  }
	  var tb = $('#city-table').find('tbody');
	  tb.append(
			  '<tr id="'+$('#city-city-select option:selected').val()+'"><td>'
			  +$('#city-prefecture-select option:selected').text()+'</td>'
			  +'<td>'+$('#city-city-select option:selected').text()+'</td>'
			  + '<td><a href="#" class="btn btn-danger btn-mini city-del">削除</a></td></tr>'
	  );
	  $(".city-del").click(function() {
		  $(this).parents("tr").remove();
	  });
  });
  
  // set city btn.
  $('#set-city-btn').click(function() {
	  $('#city-list-table').find('tr').remove();
	  $('#city-list-table').append($('#city-table').find('tbody').html());
	  $(".city-del").click(function() {
		  $(this).parents("tr").remove();
	  });
	  $('#city-modal').modal('hide');
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
	  if ($("#"+$('#date-choice').datepicker('option', "dateFormat", 'yy-mm-dd').val()).attr("id")) {
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
			  '<tr id="'+$('#date-choice').datepicker('option', "dateFormat", 'yy-mm-dd').val()+'">'
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
			  '<tr class="fromto" id="'+$('#from').datepicker('option', "dateFormat", 'yy-mm-dd').val()+'-'+$('#to').datepicker('option', "dateFormat", 'yy-mm-dd').val()+'">'
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
			  var fuid = $(this).val();
			  $('#event-friends').append(
					   '<a href="#" rel="tooltip" title="'+fname+'">'
					   + '<img src="'+fsrc+'" /><input type="hidden" name="ifuid" value="'+fuid+'"/><input type="hidden" name="ifname" value="'+fname+'"/></a>'
			  );
		  }
	  });
	  $('a[rel="tooltip"]').tooltip({'placement':'right'});
	  $('#invite-friends-list').modal('hide');
  });
  
  // commit event btn.
  $('#commit-event-btn').click(function() {
	  var participants = new Array();
	  var flist = $('#event-friends').find('input[name="ifuid"]');
	  var fnlist = $('#event-friends').find('input[name="ifname"]');
	  for (var i=0; i<flist.length; i++) {
		  participants[i] = JSON.parse('{"uid":"'+flist[i].value+'", "name":"'+fnlist[i].value+'"}');
	  };
	  
	  var dates = new Array();
	  var dvals = $('input[name="dvals"]');
	  for (var i=0; i<dvals.length; i++) {
		  dates[i] = dvals[i].value;
	  }
	  
	  var cities = new Array();
	  var cvals = $('input[name="cvals"]');
	  for (var i=0; i<cvals.length; i++) {
		  cities[i] = cvals[i].value;
	  }
	  
	  var stations = new Array();
	  var svals = $('input[name="svals"]');
	  if (svals.length > 0) {
		  for (var i=0; i<svals.length; i++) {
			  stations[i] = svals[i].value;
		  }
	  }
	  
	  var categories = new Array();
	  var catvals = $('input[name="catvals"]');
	  if (catvals.length > 0) {
		  for (var i=0; i<catvals.length; i++) {
			  categories[i] = catvals[i].value;
		  }
	  }
	  
	  var pe = create_pre_event(
				$('#ctitle').text(),
				dates,
				cities,
				stations,
				[],
				categories,
				[$('#crestaurant').text()],
				$('#ccomment').text(),
				$('#cmax').text(),
				$('#cdeadline').text(),
				true,
				participants
	  );
	  alert(pe.id);
  });
  
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
  
});