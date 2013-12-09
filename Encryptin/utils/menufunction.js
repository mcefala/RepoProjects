document.getElementById('fileselect').addEventListener('change', upfile, false);
document.getElementById('fileselectj').addEventListener('change', upfilej, false);

var f = document.getElementById('fileselect');
var f1 = document.getElementById('fileselectj');
var blob;
var name;
var file;
var path;
function downloadclick(info) {
	nokey();
	wait();
  var xhr = new XMLHttpRequest();
  xhr.open("GET", info.linkUrl , true);
  xhr.onload = function loaded(){
	  var url = info.linkUrl.toString().split("?");
	  var path = decodeURI(url[0]);
	  var name = path.split("/");
	  var filename = name[name.length-1];
	  var oldname = filename;
	  var name = oldname.toString().split(".");
	  var newname= name[0]+"."+name[1];
	  var iv  = CryptoJS.enc.Hex.parse('101112131415161718191a1b1c1d1e1f');
	  var encryptedmsg = xhr.responseText;
	  var decrypted = CryptoJS.AES.decrypt(encryptedmsg, localStorage.chiave,{iv : iv});
	  var toarray = to8Arr(decrypted);
	  var tofile = new Uint8Array(toarray,0);
	  var blob = new Blob([tofile]);
	  saveAs(blob, newname);	  
	  passok();
	  focus();
		};
  xhr.send();
   }
  
   function jdownloadclick(info) {
	   wait();
  var xhr = new XMLHttpRequest();
  xhr.open("GET", info.linkUrl , true);
  xhr.responseType = 'arraybuffer';
  xhr.onload = function loaded(){
	  var url = info.linkUrl.toString().split("?");
	  var path = decodeURI(url[0]);
	  var name = path.split("/");
	  var filename = name[name.length-1];
	  var oldname = filename;
	  var name = oldname.toString().split(".");
	  var newname= name[0]+"."+name[1];
	  var blob = new Blob([xhr.response]);	 
	  saveAs(blob, newname);	  
	  passok();
	  focus();
		};
  xhr.send();
   }
  
 function juploadclick(info){
	path = topath(info);
	f1.click(path);
 	}
 
 function uploadclick(info){
	 path = topath(info);
	  f.click(path);	
	}
 
	
function upfile(evt) {
	nokey();
	var p = path;
	wait();
    var f = evt.target.files[0];
    if (f) {    
      var r = new FileReader();
      r.onload = function(e) {  
    	  var iv  = CryptoJS.enc.Hex.parse('101112131415161718191a1b1c1d1e1f');
    	  var content = new Uint8Array(r.result,0);
    	  var forwarr = toWordArray(content); 
    	  var encrypted = CryptoJS.AES.encrypt(forwarr, localStorage.chiave,{iv : iv});	  
    	  var blob = new Blob([encrypted]);
    	  name = f.name+".encrypted";
    	  var xhr = new XMLHttpRequest();
    	  xhr.open('POST', "https://api-content.dropbox.com/1/files_put/dropbox/"+p+"/"+f.name+".encrypted"+"?"+localStorage.access_token,true);		
    	  xhr.onload = function(e){  		  
    		  response = xhr.response.split('"');
    		  error(response);	
    		   focus();
    	  }
    	  xhr.send(blob);
      };
      r.readAsArrayBuffer(f);
    } else { 
      alert("Failed to load file");
      nofile();
      focus();
    }
  }

  
  function upfilej(evt) {
	var p = path;
	wait();
    var f = evt.target.files[0]; 
    if (f) {
      var r = new FileReader();
      r.onload = function(e) {   	  
    	  var xhr = new XMLHttpRequest();
    	  xhr.open('POST', "https://api-content.dropbox.com/1/files_put/dropbox/"+p+"/"+f.name+"?"+localStorage.access_token,true);		
    	  xhr.onload = function(e){
    		  response = xhr.response.split('"');
    		  error(response);
    		   focus();
    	  }
    	  xhr.send(f);
    	 
      };
      r.readAsArrayBuffer(f);
    } else { 
      alert("Failed to load file");
      nofile();
      focus();
    }
  }

  

function wait(){
	document.getElementById('ok').style.fontSize="medium";
	document.getElementById('ok').style.color="red";
	document.getElementById('ok').innerHTML = "Please Wait...";
}

function error(response){
	if(response[1]!="error"){
		document.getElementById('ok').style.fontSize="medium";
		document.getElementById('ok').style.color="green";
	document.getElementById('ok').innerHTML = response[21]+" Added" ;
		  }else{
				document.getElementById('ok').style.fontSize="medium";
			  document.getElementById('ok').style.color="red";
				document.getElementById('ok').innerHTML =response[3] ;}
	}
	

function focus(){
	chrome.windows.getLastFocused( function (w){
		chrome.windows.remove(w.id);
	  	});
	}
	
function topath(info){
		  var url = info.linkUrl.toString().split("?");
		  var dir = url[1].split("=");
		  var path = decodeURI(url[0]);
		  var rpath = "";
		  if (dir[0]=="path"){
			path = dir[1]+"/";
			return path;
		  	}else{
		  	var p1 = path.split("/");	
		  	for(var i=4; i< p1.length-1; i++){
		  	rpath = rpath + "/"+p1[i];	
		  	}	
		  	return rpath;
		  	}
	}

	function nofile(){
		document.getElementById('ok').style.color="red";
		document.getElementById('ok').innerHTML = "No such file";
		}
	

function passok(){
	document.getElementById('ok').style.fontSize="medium";
	document.getElementById('ok').style.color="green";
	document.getElementById('ok').innerHTML = "Ok!";
	}