document.getElementById("yes").onclick = yes;

function yes(){
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