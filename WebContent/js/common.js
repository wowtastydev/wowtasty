 
document.onkeydown = function() {
	// Disable F5 keydown
	if(event.keyCode == "116") {
		event.returnValue = false;
		event.keyCode = 0;
	}
	
};

//UnblockUI after submit
window.onload = function() {
	$.unblockUI;
};

// Password minmum length
var pwdMinLen = 8;
// Password check flag : true-OK, false-Error
var isPwdOK = false;

//Password validation checker
//pwd: password
function isvalid_password(pwd) {
	// Check password
	if( pwd.length < pwdMinLen){
		return false;
	}
	if(!pwd.match(/[a-zA-Z]/) && !pwd.match(/[!,@,#,$,%,^,&,*,?,_,~]/)){
		return false;
	}
	return true;
}
