document.getElementById('register').onclick = register;

function register(){
	var unamebox = document.getElementById('1');
	var uname = unamebox.value;
	var pswbox = document.getElementById('2');
	var password = pswbox.value;
	if (password== "Password") localStorage.passcheck=1;
	localStorage.username = uname;
	var words  = CryptoJS.enc.Utf16.parse(password);
    var hash = CryptoJS.SHA1(words);
    localStorage.password = hash;
    localStorage.first=1;
    welcome();
}	

function welcome(){
	window.location.href="../html/welcome.html";
	}
