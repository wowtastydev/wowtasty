//this code handles the F5
document.onkeydown = function(e) {
    var keycode;
    if (window.event)
        keycode = window.event.keyCode;
    else if (e)
        keycode = e.which;

    // Mozilla firefox
    if ($.browser.mozilla) {
        //if (keycode == 116 ||(e.ctrlKey && keycode == 82)) {
    	if (keycode == 116) {
            if (e.preventDefault) {
                e.preventDefault();
                e.stopPropagation();
            }
        }
    } 
    // IE & Chrome
    else {
        //if (keycode == 116 || (window.event.ctrlKey && keycode == 82)) {
    	if (keycode == 116) {
            window.event.returnValue = false;
            window.event.keyCode = 0;
            window.status = "Refresh is disabled";
        }
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
