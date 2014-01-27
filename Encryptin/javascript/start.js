document.getElementById('start').onclick = start;

function start(){
chrome.windows.create({url: "../html/welcome.html" , type:"popup", width : 400, height : 400, top : (screen.height/2-(400/2)), left: (screen.width/2-(400/2)) },function(w){
		chrome.windows.update(w.id,{drawAttention: true});
		}
		);
}