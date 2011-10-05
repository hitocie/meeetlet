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
});