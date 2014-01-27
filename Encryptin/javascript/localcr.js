  document.getElementById('file').addEventListener('change', fileSelectCR, false);
  var name = localStorage.username;

document.getElementById('name').innerHTML = "Hello " 
	+ name;
document.getElementById('ok').innerHTML = "Insert Password!";
document.getElementById('ok').style.color ="red";

if (localStorage.remember == 0) {
	passok();
	document.getElementById('sessionPw').style.visibility = "hidden";
	var password = localStorage.password1;
	}

function fileSelectCR(evt) {
	nokey();
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
	      saveAs(blob, f.name+".encrypted");
	      passok();	     
      };
      r.readAsArrayBuffer(f);
	window.location.href("welcome.html");
    } else { 
    	passok();
      alert("Failed to load file");
    }
  }

function to8Arr (wordArray) {
            // Shortcuts
            var words = wordArray.words;
            var sigBytes = wordArray.sigBytes;

            // Convert
            var u8 = new Uint8Array(sigBytes);
            for (var i = 0; i < sigBytes; i++) {
                var byte = (words[i >>> 2] >>> (24 - (i % 4) * 8)) & 0xff;
                u8[i]=byte;
            }

            return u8;
        }

        
function toWordArray (u8arr) {
            // Shortcut
            var len = u8arr.length;

            // Convert
            var words = [];
            for (var i = 0; i < len; i++) {
                words[i >>> 2] |= (u8arr[i] & 0xff) << (24 - (i % 4) * 8);
            }

            return CryptoJS.lib.WordArray.create(words, len);
        }

    	  
