document.getElementById('back').onclick = back;


if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.display = "none";
	var password = localStorage.password1;
	}

	
function passok(){
	document.getElementById('ok').style.fontSize="medium";
	document.getElementById('ok').style.color="green";
	document.getElementById('ok').innerHTML = "Ok!";
	}

	
function wait(){
	document.getElementById('ok').style.fontSize="medium";
	document.getElementById('ok').style.color="red";
	document.getElementById('ok').innerHTML = "Please Wait...";
}


function back(){
	window.location.href = "main.html";
	}

	
function passverification () {
	var sessionPwbox = document.getElementById('sessionPw');
	var sessionPw = sessionPwbox.value;
	var words  = CryptoJS.enc.Utf16.parse(sessionPw);
	var hash = CryptoJS.SHA1(words);
	sessionStorage.pass = hash; 
	}
	
function key(){
	var salt = CryptoJS.lib.WordArray.random(128/8); 
	var key = CryptoJS.PBKDF2(localStorage.username, salt, { keySize: 256/32, iterations: 1000 });
	document.getElementById('ok').style.color="green";
	document.getElementById('ok').innerHTML = "Key Generated";
	return key;
	}