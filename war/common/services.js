
// common
var error_handler = function(data, status) {
	alert("error!! " + JSON.stringify(data));
};

function async_request(args) {
	
	var url = args.url;
	var success_handler = args.success_handler;
	var data = args.data;
	var type = args.type;
	if (type == undefined)
		type = 'GET';
	
	$.ajax({
		url: url,
		data: data,
		type: type,
		async: true,
		dataType: 'json',
		cache: false,
		contentType: 'application/json; charset=utf-8',
		success: success_handler,
		error: error_handler
	});
}

function sync_request(args) {
	var url = args.url;
	//var success_handler = args.success_handler;
	var data = args.data;
	var type = args.type;
	if (type == undefined)
		type = 'GET';
	
	var response = $.ajax({
		url: url,
		data: data,
		type: type,
		async: false,
		dataType: 'json',
		cache: false,
		contentType: 'application/json; charset=utf-8'//,
		//success: success_handler,
		//error: error_handler
	}).responseText;
	
	return response;
}

// auth
function login(userid) {
	// FIXME: this is a test.
	async_request({
			url: '/api/v1/test/?service=login&userid=' + userid,
			success_handler: function(data, status) {
				//alert(status);
			}
	});
}

// me
function get_me(p) {
	async_request({
			url: '/api/v1/me/get?service=me', 
			success_handler: function(data, status) {
				//alert(JSON.stringify(data));
				p(data);
			}
	});
}


// event
function get_my_events(p) {
	async_request({
			url: '/api/v1/event/get?service=my_events',
			success_handler: function(data, status) {
				//alert(JSON.stringify(data));
				p(data);
			}
	});
}
function create_event(
		subject, 
		eventDate,
		expiredDate,
		place, 
		number,
		comment,
		p) {
	
	async_request({
			url: '/api/v1/event/update?service=create',
			success_handler: function(data, status) {
				p(data);
			},
			data: {subject: subject, eventDate: eventDate, expiredDate: expiredDate, place: place, number: number, comment: comment}
	});
}
function add_participant(eventid, p) {
	
	async_request({
			url: '/api/v1/event/update?service=add_participant',
			success_handler: function(data, status) {
				p(data);
			},
			data: {eventid: eventid}
	});
}
