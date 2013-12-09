  document.getElementById('filetodec').addEventListener('change', fileSelectDCR, false);
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

function fileSelectDCR(evt) {
	nokey();
	wait();
    var f = evt.target.files[0];
    if (f) {
      var r = new FileReader();
      r.onload = function(e) {
    	  var oldname = f.name;
    	  var name = oldname.toString().split(".");
    	  var newname= name[0]+"."+name[1];
    	  var iv  = CryptoJS.enc.Hex.parse('101112131415161718191a1b1c1d1e1f');
    	  var encryptedmsg = r.result;
    	  var decrypted = CryptoJS.AES.decrypt(encryptedmsg, localStorage.chiave,{iv : iv});
    	  var toarray = to8Arr(decrypted);
    	  var tofile = new Uint8Array(toarray,0);
    	  var blob = new Blob([tofile]);
	      saveAs(blob, newname);
	      passok()
	     
      };
      r.readAsText(f);
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

    	  
