function readSingleFile(evt) {
    var f = evt.target.files[0]; 
    var r = new FileReader();
    if (f) {
      r.onload = function(e) {  
	      localStorage.chiave=r.result;
	      window.location.href='../html/welcome.html';
      };
      r.readAsText(f);
      } else { 
      alert("Failed to load file");
    }
    }
  

	
  document.getElementById('fileinput').addEventListener('change', readSingleFile, false);