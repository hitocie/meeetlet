$(function() {
  // dropdown
  $('.dropdown-toggle').dropdown();
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
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
});