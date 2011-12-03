
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
				var v = document.getElementById('result');
				v.innerText = v.innerText + ' ' + f.name
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

		var event = create_event(
				'Great Event2011', // title
				'2011-12-25 10:30:00', // event date
				'Tokyo, Japan', // place
				"2000 - 3000", // budget
				"Lunch", // genre
				["100000052301176", "696550103"], // participants
				'This is a great of 2011 party.' // comment
		);
		dump_event(event, '[Created event!]');

		// join Event.
		event = join_event(
				event.id,
				'This is a comment1.'
		); // 1
		event = join_event(
				event.id,
				'これは日本語コメントです。'
		); // 2
		dump_event(event, '[Joined event!]');
	});	
});

$(function() {
	$('#create_pre_event').click(function() {

		var event = create_pre_event(
				'Pre Event2011', // title
				['2011-10-10 10:35:00', '2011-11-11 11:35:00'], // event date
				['Tokyo, Japan', 'Ginza', 'Shinjuku'], // place
				['2000 - 3000', '3000 - 4000', '4000 - 5000'], // budget
				['Seafood', 'Japanese', 'Chinese'], // genre
				['100000052301176', '100003143705292', '100001911674442'], // participants
				['Comment1', 'Comment2'] // comment
		);
		dump_event(event, '[Created Pre Event!]');
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
