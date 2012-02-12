$(function() {
  // dropdown
  $('.dropdown-toggle').dropdown();
  // datepicker
  $('#event-date').datepicker({minDate:new Date()});
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
});