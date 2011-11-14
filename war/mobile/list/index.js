
$('#list').live('pageshow', function(event, ui) {
	var lv = $('#events');
	lv.empty(); 
	for (var i = 0; i < 8; i++) {
		if (i % 3 == 0) {
			lv.append('<li><a href="event.html?id=' + i + '">イベント開催！！</a></li>');
		} else {
			lv.append('<li><a href="ask.html?id=' + i + '">イベントしようか？</a></li>');
		}
	}
	lv.listview('refresh');
});

$('#list').live('pagehide', function(event, ui) {
	$('#list').die('pageshow');
});

