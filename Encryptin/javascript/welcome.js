document.getElementById('new').onclick = nuovo;
document.getElementById('main').onclick = main;
document.getElementById('error').style.visibility='hidden';
document.getElementById('back').style.display="none";



if (typeof(localStorage.username)!='undefined'){
	document.getElementById('benvenuto').style.color = "green";
	document.getElementById('benvenuto').innerHTML = 'Logged as '
	+ localStorage.username;
	document.getElementById('new').style.display="none";
		if(localStorage.remember == 0){
			window.location.href='main.html';
		}
	}else {
		
		document.getElementById('main').style.display="none";
		document.getElementById('pass').style.display="none";
		document.getElementById('passtx').style.display="none";	
		}
		
		

if((localStorage.username == "Username")||(typeof(localStorage.passcheck)!='undefined')){
	document.getElementById('benvenuto').style.color = "green";
	document.getElementById('benvenuto').innerHTML = 'Logged as '
	+ localStorage.username +"<br><br><span style='color:black'> the default password is : <br> Password</span>";
	}


function nuovo(){
	window.location.href="register.html";
}

function main(){
	
	var passbox = document.getElementById('pass');
	var password = passbox.value;
	var words  = CryptoJS.enc.Utf16.parse(password);
	var hash = CryptoJS.SHA1(words);
	
    if (localStorage.remember != 0){
    		if (document.getElementById('remember').checked){  			
    			localStorage.password1 = hash;  			
    			localStorage.remember = 0;   	
    		}else {
    			localStorage.remember = 1;
    			localStorage.removeItem('password1');
    		}
    }else{ 	
    	password = localStorage.password1;
    	}
    
    if (localStorage.password == hash){
    	window.location.href='main.html';
    	}else {
    		localStorage.remember = 1;
    		localStorage.removeItem('password1');
    		document.getElementById('error').style.visibility='visible';	
    	}
    	
    
}

if(typeof(localStorage.first)=='undefined'){
	chrome.tabs.getAllInWindow(function (tabs){
		var help = 0;
		for (var i = 0; i<tabs.length;i++){
			if (tabs[i].url.indexOf("help/help.html")!=-1){
				help = 1;
				}
		}
		if (help == 0){
			window.location.href="../html/shelp.html"; 
		}
		});	
		
	}


