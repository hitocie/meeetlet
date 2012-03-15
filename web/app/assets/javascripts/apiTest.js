// global or local storage
var g_me = null;
var g_friends = null;

//master data
var g_genres = null;
var g_budgets = null;
var g_cities = null;
var g_prefs = null;
var g_trains = null;
var g_stations = null;
var g_shops = null;
function load_master() {
	// common
	get_all_genres(function(genres) {
		g_genres = genres;
		for (var i = 0; i < genres.length; i++) {
			if (i > 2) break;
			var v = genres[i];
			console.log('[Genre] ' + JSON.stringify(v));
		}
	});
	get_all_budgets(function(budgets) {
		g_budgets = budgets;
		for (var i = 0; i < budgets.length; i++) {
			if (i > 2) break;
			var v = budgets[i];
			console.log('[Budget] ' + JSON.stringify(v));
		}
	});
	get_all_prefectures(function(prefs) {
		g_prefs = prefs;
		for (var i = 0; i < prefs.length; i++) {
			if (i > 2) break;
			var v = prefs[i];
			console.log('[Prefecture] ' + JSON.stringify(v));
		}
	});
	get_cities(13, function(cities) {
		g_cities = cities;
		for (var i = 0; i < cities.length; i++) {
			if (i > 2) break;
			var v = cities[i];
			console.log('[City] ' + JSON.stringify(v));
		}
	});
	find_cities('新', 13, function(cities) {
		//g_citites = cities;
		for (var i = 0; i < cities.length; i++) {
			if (i > 2) break;
			var v = cities[i];
			console.log('[City] ' + JSON.stringify(v));
		}
	});
	get_trains(13, function(trains) {
		g_trains = trains;
		for (var i = 0; i < trains.length; i++) {
			if (i > 2) break;
			var v = trains[i];
			console.log('[Train] ' + JSON.stringify(v));
		}
	});
	get_stations(100, function(stations) {
		g_stations = stations;
		for (var i = 0; i < stations.length; i++) {
			if (i > 2) break;
			var v = stations[i];
			console.log('[Station] ' + JSON.stringify(v));
		}
	});
	find_stations('新', function(stations) {
		// g_stations = stations;
		for (var i = 0; i < stations.length; i++) {
			if (i > 2) break;
			var v = stations[i];
			console.log('[Station] ' + JSON.stringify(v));
		}
	});
	find_shops('あ', 13, 658, 2734, -1/*No use*/, function(shops) {
		g_shops = shops
		for (var i = 0; i < shops.length; i++) {
			if (i > 2) break;
			var v = shops[i];
			console.log('[Shop] ' + JSON.stringify(v));
		}
	});
}

// loaded
$(window).load(function(){
	console.log('Loaded test/index.html');
	is_login(function(r) {
		if (r) {
			get_me(function(me) {
				g_me = me
				get_my_friends(function(friends) {
					g_friends = friends;
					for (var i in friends) {
						var f = friends[i];
						$('.friends').append('<img src="https://graph.facebook.com/' + f.uid + '/picture"/>');
					}
				});
				$('.me').append('<img src="https://graph.facebook.com/' + me.id + '/picture" />' + me.name);
			});
		}
		console.log('login?=' + r);
		load_master();
	});
});


function _n(n) {
	return 1 + Math.floor(Math.random() * n);
}

// event handlers
$(function() {
	$('#test1').click(function() {
		post_news('2012/09/10', 'News1だよん。');
		post_news('2012/09/11', 'News2だよん。');
		post_news('2012/09/12', 'News3だよん。');
		get_news(function(news) {
			for (var i in news) {
				var v = news[i];
				console.log('[News] ' + JSON.stringify(v));
			}
		});
	});
});

$(function() {
	$('#test2').click(function() {

		var e = create_event(
				'おもしろイベント開催', // title
				'2012-12-03 10:10:10', // date
				_n(1800), // city
				_n(10000), // station
				_n(15), // budget
				_n(11), // genre
				g_shops[_n(10)].name, // shop
				'いけているよ〜', // comment
				_n(10), // max number
				'2012-04-04 10:10:10', // deadline
				true, // private?
				[g_friends[0], g_friends[1]] // participants
		);
		reply_event(e.id, 0, '参加したいですね。');
		invite_friends(e.id, [g_friends[2]]);
		
		var pe = create_pre_event(
				'同窓会', // title
				['2012-09-10 20:00:00', '2012-12-12 20:00:00'], // dates
				[g_cities[_n(10)], g_cities[_n(10)], g_cities[_n(10)]], // city objects from get/find_cities function.
				[g_stations[_n(10)], g_stations[_n(10)]], // station objects from get/find_stations function.
				[g_budgets[_n(15)]], // budget objects from get_all_genres function.
				[g_genres[_n(11)], g_genres[_n(11)]], // genre objects from get_all_genres
				[g_shops[_n(10)], g_shops[_n(10)], g_shops[_n(10)]], // TODO: shops
				'イベント調整しています。', // comment
				-1, // max number
				'9999-12-31 00:00:00', // deadline(=forever)
				true, // private?
				[g_friends[3], g_friends[4], g_friends[5]] // participants
		);
		reply_pre_event(pe.id, [1,1], [0,0,0], [1, 0], [0], [0,0], [0,0,0], '是非開催しよう！');
	});
});

$(function() {
	$('#test3').click(function() {
		// get_my_events or find_my_events
		get_my_events(false, false, function(events) {
			for (var i in events) {
				var e = events[i];
				get_event(e.id, function(event) {
					console.log('[Event=' + event.id + '] title=' + event.title + ' owner=' + event.owner.name + ' date=' + event.date);
					//console.log('[Event] ' + JSON.stringify(e));
					console.log('  +details: comment=' + event.comment + ' shop=' + event.shop + ' private?=' + event.privateOnly);
					//console.log(' [Event detail] ' + JSON.stringify(event));
				});
			}
		});
	});
});

$(function() {
	$('#test4').click(function() {
		get_my_events(true, true, function(events) {
			for (var i in events) {
				var e = events[i];
				delete_event(e.id);
			}
		});
	});
});

$(function() {
	$('#logout').click(function() {
		logout(function() {
			console.log('logout!!');
			is_login(function(r) {
				console.log('login?=' + r);
			});
		});
	});
});
