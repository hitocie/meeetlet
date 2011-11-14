
$('#list-ask').live('pageshow', function(event, ui) {
	var v = parse_query(location.search);
	var id = v.id;
	var kind = v.kind;
	$('#event-id').text('予定を教えて！ (id=' + id + ')');
});

$('#list-ask').live('pagehide', function(event, ui) {
	$('#list-ask').die('pageshow');
});

