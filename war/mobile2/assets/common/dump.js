
function dump_user(user, tag) {
	if (tag == undefined)
		tag = '[User]';
	console.log(
			tag +
			' id=' + user.id + 
			' name=' + user.name + 
			//' token=' + user.token + 
			' timestamp=' + user.timestamp
	);
}

function dump_event(event, tag) {
	if (tag == undefined)
		tag = '[Event]';
	console.log(
			tag +
			' id= ' + event.id + 
			' subject=' + event.subject +
			' place=' + event.place +
			' eventDate=' + event.eventDate +
			' registeredDate=' + event.registeredDate +
			' expiredDate=' + event.expiredDate +
			' number=' + event.number +
			' comment=' + event.comment +
			' owner=' + event.owner.name +
			' participants=' + JSON.stringify(event.participants) +
			' timestamp=' + event.timestamp 
	);
}
