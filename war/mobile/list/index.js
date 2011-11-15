
$('#list').live('pageshow', function(event, ui) {
	var lv = $('#events');
	lv.empty(); 
	for (var i = 0; i < 8; i++) {
		var dt = new Date();
		var dt_str = dt.getFullYear() + '年' + dt.getMonth() + '月' + dt.getDay() + '日';
		if (i % 3 == 0) {
			// TODO: 参加申請済みアイコン、本日開催！アイコン
			lv.append('<li><a href="event.html?id=' + i + '">イベント開催！！ ' + dt_str + '</a></li>');
		} else {
			lv.append('<li><a href="ask.html?id=' + i + '">イベントしようか？</a></li>');
		}
	}
	lv.listview('refresh');
});

$('#list').live('pagehide', function(event, ui) {
	$('#list').die('pageshow');
});

$('#cg1').click(function() {
	alert('Clicked control group1!!');
});
$('#cg2').click(function() {
	alert('Clicked control group2!!');
});
