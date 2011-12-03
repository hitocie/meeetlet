
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
	
	return JSON.parse(response);
}


// auth
//function login_local(userid) {
//	// FIXME: this is a test. Should remove it.
//	async_request({
//			url: '/api/v1/test/?service=login&userid=' + userid,
//			success_handler: function(data, status) {
//				//alert(status);
//			}
//	});
//}
function isLogin() {
	return sync_request({
		url: '/api/v1/auth/facebook/check?service=login'
	});
}


// me
function get_me(p) {
	async_request({
			url: '/api/v1/me/get?service=me', 
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function get_my_friends(p) {
	async_request({
		url: '/api/v1/me/get?service=my_friends',
		success_handler: function(data, status) {
			p(data);
		}
	});
}

// event
function get_my_events(p) {
	async_request({
			url: '/api/v1/event/get?service=my_events',
			success_handler: function(data, status) {
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
		comment) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=create',
			data: {subject: subject, eventDate: eventDate, expiredDate: expiredDate, place: place, number: number, comment: comment}
	});
	return response;
}
function join_event(eventid, comment) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=join_event',
			data: {eventid: eventid, comment: comment}
	});
	return response;
}
function cancel_event(eventid) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=cancel_event',
			data: {eventid: eventid}
	});
	return response;
}
