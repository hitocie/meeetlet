
function dump_user(user, tag) {
	if (tag == undefined)
		tag = '[User]';
	console.log(
			tag +
			' id=' + user.id + 
			' name=' + user.name + 
			' token=' + user.token + 
			' timestamp=' + user.timestamp
	);
}

function dump_event(event, tag) {
	if (tag == undefined)
		tag = '[Event]';
	console.log(
			tag +
			' id= ' + event.id + 
			' title=' + event.title +
			' eventDate=' + event.eventDate +
			' place=' + event.place +
			' budget=' + event.budget +
			' genre=' + event.genre +
			' comment=' + event.comment +
			' owner=' + event.owner.name +
			' participants=' + JSON.stringify(event.participants) +
			' preEvent=' + JSON.stringify(event.preEvent) +
			' timestamp=' + event.timestamp 
	);
}
