var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('lclcr').onclick = localcr;
document.getElementById('lcldecr').onclick = localdecr;


if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	var password = localStorage.password1;
	}

function localcr(){
	 passverification();
		if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		window.location.href="../html/localcr.html";
		}
		}
	function localdecr(){
	 passverification();
		if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		window.location.href="../html/localdecr.html";
		}
		}
	