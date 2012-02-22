
var root_url = '/api/v1/';

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
		url: root_url + 'auths/1',
		data: {service: 'is-login'},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
// logout
function logout(p) {
	async_request({
		url: root_url + 'auths/1',
		data: {service: 'logout'},
		success_handler: function(data, status) {
			p();
		}
	});
}
// user
// NOTE: The way to get picture: https://graph.facebook.com/<uid>/picture 
function get_me(p) {
	async_request({
		url: root_url + 'users/1',
		data: {service: 'get-me'},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function get_my_friends(p) {
	async_request({
		url: root_url + 'users/1',
		data: {service: 'get-my-friends'},
		success_handler: function(data, status) {
			p(data);
		}
	});
}


// event
function create_event(
		title,
		date,
		city,
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
			city: city,
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
		url: root_url + 'events',
		type: 'POST',
		data: JSON.stringify({service: 'create-event', event: e})
	});
	return response;
}
function create_pre_event(
		title,
		dates,
		cities,
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
			cities: cities,
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
		url: root_url + 'events',
		type: 'POST',
		data: JSON.stringify({service: 'create-pre-event', event: e})
	});
	return response;
}

function reply_pre_event(event_id, dates, cities, stations, budgets, genres, shops, comment) {
	var pp = {
			dates: dates,
			cities: cities,
			stations: stations,
			budgets: budgets,
			genres: genres,
			shops: shops,
			comment: comment
	};
	var response = sync_request({
		url: root_url + 'events/' + event_id,
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
		url: root_url + 'events/' + event_id,
		type: 'PUT',
		data: JSON.stringify({service: 'reply-event', participant: p})
	});
	return response;
}

function cancel_event(event_id) {
	var response = sync_request({
		url: root_url + 'events/' + event_id,
		type: 'DELETE',
		data: JSON.stringify({service: 'cancel-event'})
	});
	return response;	
}
function delete_event(event_id) {
	var response = sync_request({
		url: root_url + 'events/' + event_id,
		type: 'DELETE',
		data: JSON.stringify({service: 'delete-event'})
	});
	return response;
}
function invite_friends(event_id, friends) {
	var response = sync_request({
		url: root_url + 'events/' + event_id,
		type: 'PUT',
		data: JSON.stringify({service: 'invite-event', friends: friends})
	});
	return response;
}


function get_public_events(include_closed, include_history, p) {
	async_request({
			url: root_url + 'events',
			data: {service: 'public-events', include_closed: include_closed, include_history: include_history},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function get_my_events(include_closed, include_history, p) {
	async_request({
			url: root_url + 'events',
			data: {service: 'my-events', include_closed: include_closed, include_history: include_history},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function find_my_events(keyword, include_closed, include_history, p) {
	async_request({
			url: root_url + 'events',
			data: {service: 'find-events', keyword: keyword, include_closed: include_closed, include_history: include_history},
			success_handler: function(data, status) {
				p(data);
			}
	});
}
function get_event(id, p) {
	async_request({
		url: root_url + 'events/' + id,
		success_handler: function(data, status) {
			p(data);
		}
	});
}

// -- common services --
function get_all_genres(p) {
	async_request({
		url: root_url + 'genres',
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function get_all_budgets(p) {
	async_request({
		url: root_url + 'budgets',
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_all_prefectures(p) {
	async_request({
		url: root_url + 'prefectures',
		success_handler: function(data, status) {
			p(data);
		}
	});
}

function get_cities(prefecture_id, p) {
	async_request({
		url: root_url + 'cities',
		data: {service: "get-cities", prefecture_id: prefecture_id},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_cities(name, prefecture_id, p) {
	async_request({
		url: root_url + 'cities',
		data: {service: 'find-cities', name: name, prefecture_id: prefecture_id},
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
		url: root_url + 'stations',
		data: {service: 'get-stations', train_id: train_id},
		success_handler: function(data, status) {
			p(data);
		}
	});
}
function find_stations(name, p) {
	async_request({
		url: root_url + 'stations',
		data: {service: 'find-stations', name: name},
		success_handler: function(data, status) {
			p(data);
		}
	});
}

// TODO: Add lat and lng if necessary.
function find_shops(keyword, prefecture_id, city_id, station_id, genre_id, p) {
	async_request({
		url: root_url + 'shops',
		data: {service: 'find-shops', 
			   prefecture_id: prefecture_id, 
			   city_id: city_id,
			   station_id: station_id,
			   genre_id: genre_id,
			   keyword: keyword},
		success_handler: function(data, status) {
			p(data);
		}
	});
}

