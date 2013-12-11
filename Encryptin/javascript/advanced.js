var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
	
document.getElementById('brw').onclick = removeme;
document.getElementById('koptions').onclick=koptions;


function removeme(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		window.location.href="../html/dmp.html";
	}
    }

    function koptions(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
	window.location.href = "../html/keyoptions.html";
	}
	}