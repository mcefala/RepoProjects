var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";
document.getElementById('testarea').style.color="red";

if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	var password = localStorage.password1;
	}

	
	function infotest(){
	passverification();
	if((sessionStorage.pass == localStorage.password)||(localStorage.password1 == localStorage.password)){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', "https://api.dropbox.com/1/account/info?"+localStorage.access_token,true);		
		xhr.responseType="text";	
		xhr.onload = function(e){
			var r1 = xhr.responseText.split('"');	
			if(r1[1]!="error"){
					var response= xhr.response.split(",");
					var nome = response[1].split('"');
					var email= response[8].split('"');
					document.getElementById('testarea').style.color="green";
					document.getElementById('testarea').innerHTML="Dropbox info : <br>"+nome[3]+"<br>"+email[3];
			}
					else
					{
						document.getElementById('testarea').innerHTML="<div align='center'><nobr>Couldn't connect<nobr><br> to Dropbox</div>";
						}
					
			}

		xhr.send();
		}
	}
	
	infotest();
	