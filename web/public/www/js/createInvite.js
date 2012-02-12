$(function() {
  // dropdown
  $('.dropdown-toggle').dropdown();
  // datepicker
  $('#event-date').datepicker();
  // tooltip
  $('a[rel="tooltip"]').tooltip({'placement':'right'});
});