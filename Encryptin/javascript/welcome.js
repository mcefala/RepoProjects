
document.getElementById('main').onclick = main;
document.getElementById('error').style.visibility='hidden';
document.getElementById('back').style.display="none";
document.getElementById('register').onclick = register;

	var unamebox = document.getElementById('1');
	var uname = unamebox.value;
	var pswbox = document.getElementById('2');
	var password = pswbox.value;
	
	
	

	
if (typeof(localStorage.username)!='undefined'){
	document.getElementById('register').style.display="none";
	document.getElementById("1").value=localStorage.username;
		if(localStorage.remember == 0){
			window.location.href='main.html';
		}
	}else {
		
		document.getElementById('main').style.display="none";
		}
		
		
function main(){
	var unamebox= document.getElementById('1');
	var uname = unamebox.value;
	var passbox = document.getElementById('2');
	var password = passbox.value;
	if ((uname!="")||(password!="")){
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
    
    if (localStorage.password == hash && localStorage.username == uname){
    	window.location.href='main.html';
    	}else {
    		document.getElementById('error').innerHTML= "Wrong Username or Password!";
    		document.getElementById('error').style.visibility='visible';
    		localStorage.remember = 1;
    		localStorage.removeItem('password1');		
    	}
    	
    	}
	else
	{
		document.getElementById('error').style.color="red";
		document.getElementById('error').innerHTML="<nobr>Username and Password<nobr><br>Required";
		document.getElementById('error').style.visibility="visible";
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

function register(){
	var unamebox = document.getElementById('1');
	var uname = unamebox.value;
	var pswbox = document.getElementById('2');
	var password = pswbox.value;
	if (password== "Password") localStorage.passcheck=1;
	localStorage.username = uname;
	var words  = CryptoJS.enc.Utf16.parse(password);
    var hash = CryptoJS.SHA1(words);
    localStorage.password = hash;
    localStorage.first=1;
    main();
}	

