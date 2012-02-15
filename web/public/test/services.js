
var root_url = '/';

// common
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
		error: function(data, status) {
			var v = JSON.parse(data.responseText);
			if (v.failed) {
				var e = new Error();
				e.name = 'MeeetletNetworkError';
				e.message = JSON.stringify(v.message);
				throw e;
			}
		}
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
		contentType: 'application/json; charset=utf-8'
	}).responseText;

	// instead of "trim" method.
	if (response.replace(/^\s+|\s+$/g, "") != "") {
		var v = JSON.parse(response);
		if (v.failed) {
			var e = new Error();
			e.name = 'MeeetletNetworkError';
			e.message = JSON.stringify(v.message);
			throw e;
		}
		return v;
	}
	return {};
}


// login
function is_login(p) {
	async_request({
		url: root_url + 'auths/1.json',
		success_handler: function(data, status) {
			p(data);
		}
	});
}
// user
// NOTE: The way to get picture: https://graph.facebook.com/<uid>/picture 
function get_me(p) {
	async_request({
		url: root_url + 'users/1.json',
		data: {service: 'get-me'},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function get_my_friends(p) {
	async_request({
		url: root_url + 'users/1.json',
		data: {service: 'get-my-friends'},
		success_handler: function(data, status) {
			p(data);
		}
	});
}


function get_users(p) {
	async_request({
			url: root_url + 'users.json', 
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function create_user(uid, name) {
	var response = sync_request({
		url: root_url + 'users.json',
		type: 'POST',
		data: JSON.stringify({user: {uid: uid, name: name}})
	});
	return response;
}
function delete_user(uid) {
	sync_request({
		url: root_url + 'users/' + uid + '.json',
		type: 'DELETE'
	});
}


// event
function create_event(
		title,
		date,
		place,
		station,
		budget,
		genre,
		shop,
		comment,
		maxNumber,
		deadline,
		privateOnly,
		participants) {	
	var e = {
			title: title,
			date: date,
			place: place,
			station: station,
			budget: budget, 
			genre: genre,
			shop: shop,
			comment: comment,
			maxNumber: maxNumber,
			deadline: deadline,
			privateOnly: privateOnly,
			participants: participants
	};
	var response = sync_request({
		url: root_url + 'events.json',
		type: 'POST',
		data: JSON.stringify({service: 'create-event', event: e})
	});
	return response;
}
function create_pre_event(
		title,
		dates,
		places,
		stations,
		budgets,
		genres,
		shops,
		comment,
		maxNumber,
		deadline,
		privateOnly,
		participants) {	
	var e = {
			title: title,
			dates: dates,
			places: places,
			stations: stations,
			budgets: budgets, 
			genres: genres,
			shops: shops,
			comment: comment,
			maxNumber: maxNumber,
			deadline: deadline,
			privateOnly: privateOnly,
			participants: participants
	};
	var response = sync_request({
		url: root_url + 'events.json',
		type: 'POST',
		data: JSON.stringify({service: 'create-pre-event', event: e})
	});
	return response;
}

function reply_pre_event(event_id, dates, places, stations, budgets, genres, shops, comment) {
	var pp = {
			dates: dates,
			places: places,
			stations: stations,
			budgets: budgets,
			genres: genres,
			shops: shops,
			comment: comment
	};
	var response = sync_request({
		url: root_url + 'events/' + event_id + '.json',
		type: 'PUT',
		data: JSON.stringify({service: 'reply-pre-event', pre_participant: pp})
	});
	return response;
}
function reply_event(event_id, attend, comment) {
	var p = {
			attend: attend,
			comment: comment
	};
	var response = sync_request({
		url: root_url + 'events/' + event_id + '.json',
		type: 'PUT',
		data: JSON.stringify({service: 'reply-event', participant: p})
	});
	return response;
}

function cancel_event(event_id) {
	var response = sync_request({
		url: root_url + 'events/' + event_id + '.json',
		type: 'DELETE',
		data: JSON.stringify({service: 'cancel-event'})
	});
	return response;	
}
function delete_event(event_id) {
	var response = sync_request({
		url: root_url + 'events/' + event_id + '.json',
		type: 'DELETE',
		data: JSON.stringify({service: 'delete-event'})
	});
	return response;
}
function invite_friends(event_id, friends) {
	var response = sync_request({
		url: root_url + 'events/' + event_id + '.json',
		type: 'PUT',
		data: JSON.stringify({service: 'invite-event', friends: friends})
	});
	return response;
}


function get_all_events(p) {
	async_request({
			url: root_url + 'events.json', 
			data: {service: 'all-events'},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function get_my_events(p) {
	async_request({
			url: root_url + 'events.json',
			data: {service: 'my-events'},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function find_my_events(keyword, p) {
	async_request({
			url: root_url + 'events.json',
			data: {service: 'find-events', keyword: keyword},
			success_handler: function(data, status) {
				p(data);
			}
	});
}

// -- common services --
// budgets
function get_all_budgets(p) {
	async_request({
		url: root_url + 'budgets.json',
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_all_prefectures(p) {
	async_request({
		url: root_url + 'prefectures.json',
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_cities(prefecture_id, p) {
	async_request({
		url: root_url + 'cities.json',
		data: {prefecture_id: prefecture_id},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_cities(name, p) {
	async_request({
		url: root_url + 'cities.json',
		data: {name: name},
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_trains(prefecture_id, p) {
	async_request({
		url: root_url + 'trains.json',
		data: {prefecture_id: prefecture_id},
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_stations(train_id, p) {
	async_request({
		url: root_url + 'stations.json',
		data: {train_id: train_id},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_stations(name, p) {
	async_request({
		url: root_url + 'stations.json',
		data: {name: name},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
