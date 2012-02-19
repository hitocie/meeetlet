
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
			// common
			//get_all_genres(function(genres) {
			//	console.log(JSON.stringify(genres));
			//});
			//get_all_budgets(function(budgets) {
			//	console.log(JSON.stringify(budgets));
			//});
			//get_all_prefectures(function(prefs) {
			//	console.log(JSON.stringify(prefs));
			//});
			//get_cities(13/*東京*/, function(cities) {
			//	console.log(JSON.stringify(cities));
			//});
			//find_cities('やま', 13, function(cities) {
			//	console.log(JSON.stringify(cities));
			//});
			//get_trains(13/*東京*/, function(trains) {
			//	console.log(JSON.stringify(trains));
			//});
			//get_stations(100, function(stations) {
			//	console.log(JSON.stringify(stations));
			//});
			//find_stations('しんばし', function(stations) {
			//	console.log(JSON.stringify(stations));
			//});
			find_shops('あ', 13/*東京都*/, 658/*東京都港区*/, 2734/*新橋駅*/, -1/*No use*/, function(shops) {
				console.log(JSON.stringify(shops));
			});

			var e = create_event(
					'イベントです。', // title
					'2012-01-03 10:10:10', // date
					10, // city
					100, // station
					3, // budget
					5, // genre
					'ABC', // shop
					'ビッグイベントです。', // comment
					10, // max number
					'2012-04-04 10:10:10', // deadline
					true, // private?
					[friends[10], friends[20]] // participants
			);
			reply_event(e.id, 0, '参加したいと思います。');
			invite_friends(e.id, [friends[7]]);
			
			var pe = create_pre_event(
					'こちらはイベント調整です。', // title
					['2012-09-10 20:00:00', '2012-12-12 20:00:00'], // dates
					[{id:13, name:'あいうえお市'}, {id:14, name:'かきくけこ市'}, {id:15, name:'さしすせそ市'}], // city objects from get/find_cities function.
					[{id:200, name:'A駅'}, {id:205, name:'B駅'}], // station objects from get/find_stations function.
					[{id:3, price:'¥1,000〜¥2,000'}], // budget objects from get_all_genres function.
					[{id:3, name:'中華料理'}, {id:4, name:'寿司'}], // genre objects from get_all_genres
					['aa','bb','cc'], // TODO: shops
					'あああああああ', // comment
					-1, // max number
					'9999-12-31 00:00:00', // deadline(=forever)
					true, // private?
					[friends[1], friends[2], friends[3]] // participants
			);
			reply_pre_event(pe.id, [1,1], [0,0,0], [1, 0] [0], [0,0], [0,0,0], '私の調整回答を送ります。');
			
			//find_my_events(false, false, 'イベント', function(events) {
			get_my_events(true, true, function(events) {
				for (var i in events) {
					var e = events[i];
					console.log('[Event] ' + JSON.stringify(e));
					get_event(e.id, function(event) {
						console.log('[Event detail] ' + JSON.stringify(event));
					});
					delete_event(e.id);
				}
			});
		});
	});
});

