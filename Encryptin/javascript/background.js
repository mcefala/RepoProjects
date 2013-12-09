chrome.tabs.onUpdated.addListener(function(tabid,changeinfo,tab){

if ((changeinfo.status == "complete") && (tab.url.indexOf("#access_token") != -1) && (tab.url.indexOf("state=whoa"))) {	
				var access_url = tab.url;
				var utot=access_url.split("#");
				var tokens = utot[1].split("&");	
				localStorage.access_token = tokens[0];
				localStorage.token_type= tokens[1];
				localStorage.uid = tokens[2];
				localStorage.state = tokens[3];
};
});
