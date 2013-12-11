var name = localStorage.username;

document.getElementById('name').innerHTML = "<nobr>Hello " 
		+ name+"</nobr>";
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('dropbox').onclick = dropboxmenu;
document.getElementById('set').onclick=set;
document.getElementById('lcl').onclick = localfile;
document.getElementById('help').onclick=help;
document.getElementById('nokey').style.display = "none";
document.getElementById('new').onclick=gkey;

	var nk = "<nobr>&nbspNo key was found</nobr>";
 	
if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	var password = localStorage.password1;
	
	}

if (typeof(localStorage.chiave) == 'undefined'){
		document.getElementById('ok').style.fontSize="medium";
		document.getElementById('ok').style.color ="red";
		document.getElementById('ok').innerHTML=nk;
		document.getElementById('nokey').style.display="inline";
		document.getElementById('main').style.display = "none";
		
		}

 
function localfile(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href="../html/localfile.html";
	}
	}

function dropboxmenu(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	if(typeof(localStorage.access_token)!='undefined'){
		chrome.windows.create({url: "https://www.dropbox.com/m" , type:"popup", width : 750, height : 650, top : (screen.height/2-(750/2)), left: (screen.width/2-(650/2)) },function(w){
		chrome.windows.update(w.id,{drawAttention: true});
			}
		);
		}
		else{
			
			chrome.tabs.create({url: "https://www.dropbox.com/1/oauth2/authorize?response_type=token&client_id=y2vkop71q1htxeo&redirect_uri=https://www.google.com&state=whoa"});
			
				passok();
			
		}	
		}
		}
 
 function set(){
	 passverification();
		if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	 window.location.href="../html/options.html";
	
		}
 }
 function help(){
	chrome.tabs.create({url:"../help/help.html"}); 
 	}
 

function gkey(){
	localStorage.chiave=key();
	window.location.href="main.html";
	}


