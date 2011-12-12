$(window).load(function() {
	// HACK! facebook seems to add '#_=_' to redirect url.
	if (window.location.hash = '#_=_')
		window.location.hash = ''; // removed
});
