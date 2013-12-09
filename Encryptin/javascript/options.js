var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('out').style.visibility="hidden";
document.getElementById('out').onclick = slog;
document.getElementById('brw').onclick = removeme;
document.getElementById('access').onclick = access;
document.getElementById('test').onclick = test;



if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	document.getElementById('out').style.visibility = "visible";
	var password = localStorage.password1;
	
	}

function slog(){	
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	localStorage.removeItem('password1');
	localStorage.removeItem('passcheck');
	localStorage.remember = 1;
	window.location.href='../html/welcome.html';
	}
}



function removeme(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
    localStorage.removeItem('password');	
    localStorage.removeItem('username');
    localStorage.removeItem('password1');
    localStorage.removeItem('chiave');
    localStorage.removeItem('access_token');
    localStorage.removeItem('token_type');
    localStorage.removeItem('uid');
    localStorage.removeItem('state');
    localStorage.removeItem('first');
    localStorage.removeItem('passcheck');
	localStorage.remember = 1;    
	window.location.href='../html/welcome.html';
	}
    }

function access(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	chrome.tabs.create({url: "https://www.dropbox.com/1/oauth2/authorize?response_type=token&client_id=y2vkop71q1htxeo&redirect_uri=https://www.dropbox.com&state=whoa"});
		}
	}

	function test(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href="../html/test.html";
	}
	}
