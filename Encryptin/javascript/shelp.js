document.getElementById("yes").onclick = yes;
document.getElementById("no").onclick = no;

function yes(){
	chrome.tabs.create({url:"../help/help.html"});
	}

function no(){
	window.location.href="../html/welcome.html";
	localStorage.first=1;
	}
