
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
			//console.log(JSON.stringify(friends));
			var e = create_event(
					'イベントです。', // title
					'2012-01-03 10:10:10', // date
					'Tokyo', // place
					'田町駅', // station
					'$2000', // budget
					'chinese', // genre
					'ABC', // shop
					'comments', // comment
					10, // max number
					'2012-04-04 10:10:10', // deadline
					true, // private?
					[friends[10], friends[20]] // participants
			);
			console.log(e);
			reply_event(e.id, 0, 'comment');
			invite_friends(e.id, [friends[7]]);
			
			var pe = create_pre_event(
					'title_pre_1', // title
					['2012-09-10 20:00:00', '2012-12-12 20:00:00'], // dates
					['Chiba', '東京', '京都'], // places
					['大手町', '新橋'], // stations
					['2000円'], // budgets
					['AA', 'BB'], // genres
					['aa','bb','cc'], // shops
					'あああああああ', // comment
					-1, // max number
					'9999-12-31 00:00:00', // deadline(=forever)
					true, // private?
					[friends[1], friends[2], friends[3]] // participants
			);
			reply_pre_event(pe.id, [1,1], [0,0,0], [1, 0] [0], [0,0], [0,0,0], 'comment');
			
			find_my_events('title_pre', function(events) {
				console.log(JSON.stringify(events))
				for (var i in events) {
					var e = events[i];
					console.log('[Event]' + e.id + ' ' 
							+ e.title + ' ' 
							+ e.date + ' '
							+ e.comment + ' '
							+ e.user + ' '
							+ e.preEvent + ' '
							+ e.maxNumber + ' '
							+ e.privateOnly + ' '
							+ e.participants);
					delete_event(e.id);
				}
			});
			
			// common
			//get_all_budgets(function(budgets) {
			//	console.log(JSON.stringify(budgets));
			//});
			//get_all_prefectures(function(prefs) {
			//	console.log(JSON.stringify(prefs));
			//});
			//get_cities(13, function(cities) {
			//	console.log(JSON.stringify(cities));
			//});
			//find_cities('やまちょう', function(cities) {
			//	console.log(JSON.stringify(cities));
			//});
			//get_trains(13, function(trains) {
			//	console.log(JSON.stringify(trains));
			//});
			//get_stations(100, function(stations) {
			//	console.log(JSON.stringify(stations));
			//});
			find_stations('しんばし', function(stations) {
				console.log(JSON.stringify(stations));
			});

		});
	});
});

