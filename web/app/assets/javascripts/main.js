$(function() {
  /// for web ui
  // dropdown
  $('.dropdown-toggle').dropdown();
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
  // arrange-modal-btn
  $('.arrange-modal-btn').click(function() {
	  $('#back-modal').modal('show');
	  $('#arrange-modal').modal({backdrop:false}).modal('show');
  });
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
  get_my_events(true, false, function(data) {
	  for (var i in data) {
		  var e = data[i];
		  get_event(e.id, function(event) {
			  alert(JSON.stringify(event));
		  });
	  }
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