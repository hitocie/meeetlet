
// loaded
$(window).load(function(){
	console.log('Loaded /api/v1/test/test.html');

	// login
	login('100000');
	
});

// event handlers
$(function() {
	$('#get_me').click(function() {

		get_me(function(user) {
			console.log(
					' id=' + user.id + 
					' name=' + user.name + 
					' token=' + user.token + 
					' timestamp=' + user.timestamp
			);
		});

	});	
});

$(function() {
	$('#get_my_events').click(function() {

		get_my_events(function(events) {
			for (var i in events) {
				var e = events[i];
				console.log(
						' id= ' + e.id + 
						' subject=' + e.subject +
						' place=' + e.place +
						' eventDate=' + e.eventDate +
						' registeredDate=' + e.registeredDate +
						' expiredDate=' + e.expiredDate +
						' number=' + e.number +
						' comment=' + e.comment +
						' owner=' + e.owner.name +
						' participants=' + e.participants +
						' timestamp=' + e.timestamp 
				);

			}
		});
		
	});	
});

$(function() {
	$('#create_event').click(function() {

		create_event(
				'Great Event2011', // subject
				'2011-12-25 10:30', // event date
				'2011-12-20 19:30', // expired date
				'Tokyo, Japan', // place
				20, // max number of participants
				'This is a great of 2011 party.', // comment
				function(event) {
					alert('[created!]' + event.id + ' ' + event.subject);
					// add one participant.
					add_participant(event.id);
				}
		);
		
	});	
});

