
// loaded
$(window).load(function(){
	console.log('Loaded test/index.html');
	is_login(function(r) {
		console.log('login?=' + r);
	});
});

// event handlers
$(function() {
	$('#test1').click(function() {
		get_me(function(me) {
			console.log(JSON.stringify(me));
		});
		get_my_friends(function(friends) {
			console.log(JSON.stringify(friends));
		});
		
		var e = create_event(
				'イベントです。',
				'2012-01-03',
				'Tokyo',
				'$2000', 
				'chinese', 
				'ABC',
				'comments',
				['300', '400']
		);
		console.log(e);
		reply_event(e.id, 0, 'comment');
		invite_friends(e.id, ['400']);
		
		var pe = create_pre_event(
				'title_pre_1',
				['2012-09-10', '2012-12-12'],
				['Chiba', '東京', '京都'],
				['2000円'],
				['AA', 'BB'],
				['aa','bb','cc'],
				'あああああああ',
				['100', '200', '300']
		);
		reply_pre_event(pe.id, [1,1], [0,0,0], [0], [0,0], [0,0,0], 'comment');
		
		find_events('title_pre', function(events) {
			//console.log(JSON.stringify(events))
			for (var i in events) {
				var e = events[i];
				console.log('[Event]' + e.id + ' ' 
						+ e.title + ' ' 
						+ e.date + ' '
						+ e.comment + ' '
						+ e.user + ' '
						+ e.preEvent + ' '
						+ e.participants);
				delete_event(e.id);
			}
		});
	});
});

