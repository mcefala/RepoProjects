var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('new').onclick = keygen;
document.getElementById('imp').onclick = imp;
document.getElementById('exp').onclick = exp;
document.getElementById('rem').onclick = removekey;



if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	var password = localStorage.password1;
	}

	
function passok(){
	document.getElementById('ok').style.color="green";
	document.getElementById('ok').innerHTML = "Ok!";
	}

	
function wait(){
	document.getElementById('ok').style.color="red";
	document.getElementById('ok').innerHTML = "Please Wait...";
}


function back(){
	window.location.href = "../html/main.html";
	}

	
function passverification () {
	var sessionPwbox = document.getElementById('sessionPw');
	var sessionPw = sessionPwbox.value;
	var words  = CryptoJS.enc.Utf16.parse(sessionPw);
	var hash = CryptoJS.SHA1(words);
	sessionStorage.pass = hash; 
	}
	

function keygen(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		wait();
	if (localStorage.chiave != undefined){
		window.location.href="../html/kp.html";
	}
	else {
		localStorage.chiave = key();
		passok();
	}	
	}
	}
    
function exp(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		
		 var blob = new Blob([localStorage.chiave], {type: 'text/plain'});
		 saveAs(blob, "key.txt");
	}
	}


 function imp(){
	 passverification();
		if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href='../html/import.html';
		}
 	}
 
 function removekey(){
	 passverification();
		if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		window.location.href="../html/rkp.html"
		}
	 }
 
function popupwindow(url, title, w, h) {
  var left = (screen.width/2)-(w/2);
  var top = (screen.height/2)-(h/2);
  return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
} 
