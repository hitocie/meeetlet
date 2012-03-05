$(function() {
  if (typeof sessionStorage == 'undefined') {
	  // use hash?
  } else {
	  var sstr = sessionStorage;
	  if (sstr.length == 0) {
      // data loading.
		  // category
		  get_all_genres(function(data) {
			  sstr.setItem("categories", JSON.stringify(data));
		  });
		  
		  // budgets
		  get_all_budgets(function(data){
			 sstr.setItem("budgets", JSON.stringify(data)); 
		  });
		  
		  // prefectures
		  get_all_prefectures(function(data) {
			 sstr.setItem("prefectures", JSON.stringify(data)); 
		  });
		  
		  // news
		  get_news(function(data) {
			 sstr.setItem("news", JSON.stringify(data));
		  });

		  // mydata
		  get_me(function(data) {
			 sstr.setItem("me", JSON.stringify(data)); 
		  });
		  
		  // friends
		  get_my_friends(function(data) {
			  sstr.setItem("friends", JSON.stringify(data));
		  });
	  } else {
	  // if data has already loaded, do nothing.
	  }
  }
});
