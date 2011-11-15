
$('#list-event').live('pageshow', function(event, ui) {
	var v = parse_query(location.search);
	var id = v.id;
	var kind = v.kind;
	$('#event-id').text('イベントIDは「' + id + '」。');
	// TODO: イベント参加済みなら、参加取り消しボタンへ
	// TODO: 本日参加なら、予定通り行けそうにないかもボタンや、今どこボタン／現在状況を追加
});

$('#list-event').live('pagehide', function(event, ui) {
	$('#list-event').die('pageshow');
});

