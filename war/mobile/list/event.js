
$('#list-event').live('pageshow', function(event, ui) {
	var v = parse_query(location.search);
	var id = v.id;
	var kind = v.kind;
	$('#event-id').text('イベントIDは「' + id + '」。');
});

$('#list-event').live('pagehide', function(event, ui) {
	$('#list-event').die('pageshow');
});

