// loaded
$(window).load(function(){
	console.log('Loaded index.html');
	if (isLogin()) {
		console.log('You have already logged in.');
		//document.getElementById('login').innerText = "";
	} else {
		console.log('Cannot logged in.');
	}
});
