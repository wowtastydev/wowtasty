 
document.onkeydown = function() {
	alert("keyCode:" + event.keyCode);
	// Disable F5 keydown
	if(event.keyCode == "116") {
		event.returnValue = false;
		event.keyCode = 0;
	}
	
};

var clickCount = 0;
window.onload = function() {
	// Double click check
	if(clickCount == 0){
		clickCount = 1;
	}
};
