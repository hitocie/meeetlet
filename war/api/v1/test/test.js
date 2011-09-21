
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

		var event = create_event(
				'Great Event2011', // subject
				'2011-12-25 10:30', // event date
				'2011-12-20 19:30', // expired date
				'Tokyo, Japan', // place
				20, // max number of participants
				'This is a great of 2011 party.' // comment
		);
		dump_event(event, '[Created event!]');

		// add one participant.
		event = add_participant(
				event.id,
				'This is a comment1.'
		); // 1
		event = add_participant(
				event.id,
				'This is a comment2.'
		); // 2
		dump_event(event, '[Added participant!]');
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
