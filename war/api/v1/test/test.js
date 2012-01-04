
// loaded
$(window).load(function(){
	console.log('Loaded /api/v1/test/test.html');
});

// event handlers
$(function() {
	$('#me').click(function() {

		// get my info.
		get_me(function(user) {
			dump_user(user, '[me]');
		});
		
		// get my friends
		get_my_friends(function(friends) {
			for (var i in friends) {
				var f = friends[i];
				dump_user(f, '[friends]');
			}
		});
	});
});

$(function() {
	$('#get_my_events').click(function() {

		get_my_events(function(events) {
			for (var i in events) {
				var e = events[i];
				dump_event(e, '[my events]');
			}
		});
		
	});	
});

$(function() {
	$('#create_event').click(function() {
		
		get_my_friends(function(friends) {

			var event = create_event(
					'Great Event2011', // title
					'2011-12-25 10:30:00', // event date
					'Tokyo, Japan', // place
					'2000 - 3000', // budget
					'Lunch', // genre
					'B', // shop
					[ friends[0].id, friends[1].id ], // participants
					'This is a great of 2011 party.' // comment
			);
			dump_event(event, '[Created event!]');
			// invite friends
			invite_friends(event.id, [ friends[2].id ]);
			
			reply_event(event.id, Response.NG, 'Sorry!!');
			
		});
	});	
});

$(function() {
	$('#create_pre_event').click(function() {

		get_my_friends(function(friends) {

			var event = create_pre_event(
					'Pre Event2011', // title
					['2011-10-10 10:35:00', '2011-11-11 11:35:00'], // event date
					['Tokyo, Japan', 'Ginza', 'Shinjuku'], // place
					['2000 - 3000', '3000 - 4000', '4000 - 5000'], // budget
					['Seafood', 'Japanese', 'Chinese'], // genre
					['A', 'B', 'C'], // shop
					[ friends[4].id, friends[5].id, friends[6].id ], // participants
					['Comment1', 'Comment2'] // comment
			);
			dump_event(event, '[Created Pre Event!]');
			reply_pre_event(
					event.id, 
					[Response.OK, Response.NG],
					[Response.OK, Response.NG, Response.OK],
					[Response.Pending, Response.OK, Response.OK],
					[Response.OK, Response.Pending, Response.OK],
					[Response.OK, Response.Pending, Response.OK],
					'Like!!'
			);
			
		});	
	});	
});

$(function() {
	$('#delete_event').click(function() {

		get_my_events(function(events) {
			var e = events[0];
			delete_event(e.id);
		});
		
	});	
});



// internal use
function create_prefectures(prefs) {
	var response = sync_request({
		url: '/api/v1/internal/pref',
		data: JSON.stringify(prefs),
		type: 'POST'
	});
	return response;
}
function create_cities(cities) {
	var response = sync_request({
		url: '/api/v1/internal/city',
		data: JSON.stringify(cities),
		type: 'POST'
	});
	return response;
}
function create_stations(stations) {
	var response = sync_request({
		url: '/api/v1/internal/station',
		data: JSON.stringify(stations),
		type: 'POST'
	});
	return response;
}

$(function() {
	$('#create_prefs').click(function() {
		create_prefectures(null);
	});	
});
$(function() {
	$('#create_cities').click(function() {
		create_cities(cities);
	});	
});
$(function() {
	$('#create_stations').click(function() {
		create_stations(stations);
	});	
});
