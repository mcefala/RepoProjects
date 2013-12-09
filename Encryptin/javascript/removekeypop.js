document.getElementById("yes").onclick = yes;

function yes(){
	localStorage.removeItem('chiave');
	window.location.href="../html/main.html";
	}