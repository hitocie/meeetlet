
// common
var error_handler = function(data, status) {
	var e = new Error();
	e.name = 'MeeetletNetworkError';
	e.message = "error!! " + JSON.stringify(data);
	throw e;
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
	}).responseText;
	
	return JSON.parse(response);
}


// auth
function is_login() {
	return sync_request({
		url: '/api/v1/auth/facebook/check?service=login'
	});
}


// me
// NOTE: The way to get picture: https://graph.facebook.com/<uid>/picture 
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
// TODO: "offset", "limit" support
function get_my_events(p) {
	async_request({
			url: '/api/v1/event/get?service=my_events',
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function find_my_events(keyword, p) {
	async_request({
			url: '/api/v1/event/get?service=my_events_by_keyword',
			data: {keyword: keyword},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function create_pre_event(
		title, 
		eventDates,
		places, 
		budgets,
		genres,
		shops,
		participants,
		comment) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=create_pre_event',
			data: {
				title: title,
				eventDates: JSON.stringify(eventDates),
				places: JSON.stringify(places),
				budgets: JSON.stringify(budgets),
				genres: JSON.stringify(genres),
				shops: JSON.stringify(shops),
				participants: JSON.stringify(participants), 
				comments: comment}
	});
	return response;
}
function create_event(
		title, 
		eventDate,
		place, 
		budget,
		genre,
		shop,
		participants,
		comment) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=create_event',
			data: {
				title: title,
				eventDate: eventDate,
				place: place,
				budget: budget,
				genre: genre,
				shop: shop,
				participants: JSON.stringify(participants), 
				comment: comment}
	});
	return response;
}
function delete_event(eventid) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=delete_event',
			data: {eventid: eventid}
	});
	return response;
}

var Response = {OK: 0, NG: 1, Pending: 2};
function reply_pre_event(
		eventid,
		eventDates,
		places, 
		budgets,
		genres,
		shops,
		comment) {
	
	var response = sync_request({
			url: '/api/v1/event/update?service=reply_pre_event',
			data: {
				eventid: eventid,
				eventDates: JSON.stringify(eventDates),
				places: JSON.stringify(places),
				budgets: JSON.stringify(budgets),
				genres: JSON.stringify(genres),
				shops: JSON.stringify(shops),
				comment: comment}
	});
	return response;
}
function reply_event(eventid, attend, comment) {
	
	var response = sync_request({
		url: '/api/v1/event/update?service=reply_event',
		data: {eventid: eventid, attend: attend, comment: comment}
	});
	return response;
}
function invite_friends(eventid, friends) {
	
	var response = sync_request({
		url: '/api/v1/event/update?service=invite_friends',
		data: {eventid: eventid, friends: JSON.stringify(friends)}
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

function get_all_prefectures(p) {
	async_request({
		url: '/api/v1/common/area?service=all_prefectures',
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function get_cities(prefecture, p) {
	async_request({
		url: '/api/v1/common/area?service=cities_by_prefecture',
		data: {prefecture: prefecture},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_cities(name, p) {
	async_request({
		url: '/api/v1/common/area?service=cities_by_name',
		data: {name: name},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function get_stations(prefecture, p) {
	async_request({
		url: '/api/v1/common/area?service=stations_by_prefecture',
		data: {prefecture: prefecture},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_stations(name, p) {
	async_request({
		url: '/api/v1/common/area?service=stations_by_name',
		data: {name: name},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
