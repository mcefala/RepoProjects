var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('out').style.visibility="hidden";
document.getElementById('out').onclick = slog;
document.getElementById('test').onclick = test;
document.getElementById('adv').onclick = advanced;

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

function advanced(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href="../html/advanced.html";
	}
	}

	function test(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href="../html/test.html";
	}
	}
	
	
