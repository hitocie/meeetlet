$(function() {
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
});