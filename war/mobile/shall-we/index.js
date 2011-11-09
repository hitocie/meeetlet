
$('#shall-we').live('pageshow', function(event, ui) {

	get_my_friends(function(friends) {
		var sc = $('#members');
		sc.empty();
		for (var i = 0 in friends) {
			var f = friends[i];
			sc.append('<option value="' + f.id + '">' + f.name + '</option>');
		}
		sc.selectmenu('refresh');
	});
	
});

$('#shall-we').live('pagehide', function(event, ui) {

	$('#shall-we').die('pageshow');
	
});

$('#send-everybody').click(function() {
	alert('Send everybody!!');
});