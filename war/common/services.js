
// common
var error_handler = function(data, status) {
	alert("error!! " + JSON.stringify(data));
};

function async_request(url, success_handler, data) {
	$.ajax({
		url: url,
		data: data,
		type: 'GET',
		dataType: 'json',
		cache: false,
		contentType: 'application/json; charset=utf-8',
		success: success_handler,
		error: error_handler
	});
}


// auth
function login(userid) {
	// FIXME: this is a test.
	async_request(
			'/api/v1/test/?service=login&userid=' + userid,
			function(data, status) {
				//alert(status);
			}
	);
}

// me
function get_me(p) {
	async_request(
			'/api/v1/me/get?service=me', 
			function(data, status) {
				//alert(JSON.stringify(data));
				p(data);
			}
	);
}


// event
function get_my_events(p) {
	async_request(
			'/api/v1/event/get?service=my_events',
			function(data, status) {
				//alert(JSON.stringify(data));
				p(data);
			}
	);
}
function create_event(
		subject, 
		eventDate,
		expiredDate,
		place, 
		number,
		comment,
		p) {
	
	async_request(
			'/api/v1/event/update?service=create',
			function(data, status) {
				p(data);
			},
			{subject: subject, eventDate: eventDate, expiredDate: expiredDate, place: place, number: number, comment: comment}
	);
}
function add_participant(eventid, p) {
	
	async_request(
			'/api/v1/event/update?service=add_participant',
			function(data, status) {
				p(data);
			},
			{eventid: eventid}
	);
}
