  var context = "link";
  chrome.contextMenus.removeAll();
  
  var down = chrome.contextMenus.create({"title": "Download", "contexts":[context]});
  
  var up = chrome.contextMenus.create({"title": "Upload", "contexts":[context]});                                                                            
  
  var Eup = chrome.contextMenus.create({"title": "Encrypt and upload","parentId": up, "contexts":[context],
                                       "onclick": uploadclick});
  var Edown = chrome.contextMenus.create({"title": "Download and decrypt","parentId" : down, "contexts":[context],
                                       "onclick": downloadclick});
  
  var jup = chrome.contextMenus.create({"title": "Upload","parentId": up, "contexts":[context],
                                       "onclick": juploadclick});
  var jdown = chrome.contextMenus.create({"title": "Download","parentId" : down, "contexts":[context],
                                       "onclick": jdownloadclick});
                                       
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

    	  
