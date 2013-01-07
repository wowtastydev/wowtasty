/***********************************************
* Floating Top Bar script
* Sliding routine
***********************************************/

var persistclose=0 //set to 0 or 1. 1 means once the bar is manually closed, it will remain closed for browser session
var startX = 845 //set x offset of bar in pixels
var startY = 120 //set y offset of bar in pixels
var startScrollY = 235;

function iecompattest(){
	return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

var verticalpos="fromtop";

function staticbar(){

    var ns = (navigator.appName.indexOf("Netscape") != -1);
    var d = document;
    function ml(id){
        var el=d.getElementById(id);
        el.style.visibility="visible";
        if(d.layers)el.style=el;
        el.sP=function(x,y){/*this.style.left=x+"px";*/this.style.top=y+"px";};
        el.x = startX;
        if (verticalpos=="fromtop") 
        el.y = startY; 
        else{
        el.y = ns ? pageYOffset + innerHeight : iecompattest().scrollTop + iecompattest().clientHeight;
        el.y -= startY;
        }
        return el;
    }
	
    window.stayTopLeft=function(){
        if (verticalpos=="fromtop"){
        	var pY = ns ? pageYOffset : iecompattest().scrollTop;
    		//ftlObj.y += (pY + startY - ftlObj.y)/10;
    		ftlObj.y += (pY + startY - ftlObj.y)/10;

        } else{
	        var pY = ns ? pageYOffset + innerHeight : iecompattest().scrollTop + iecompattest().clientHeight;
	        ftlObj.y += (pY - startY - ftlObj.y)/10;
		}
		// Stop panel at the bottom - Start : 2012/12/26 Hak Choi
		var bottom = document.getElementById("container-sub").scrollHeight;
		var panelHeight = document.getElementById("rightpanel-in").scrollHeight;
		
        if (bottom <=(ftlObj.y+panelHeight)){
			ftlObj.sP(ftlObj.x, bottom - panelHeight);
        } else if (startScrollY<=ftlObj.y) {
    		ftlObj.sP(ftlObj.x, ftlObj.y -startScrollY + startY);
    	}
		//if (startScrollY<=ftlObj.y) {
    	//	ftlObj.sP(ftlObj.x, ftlObj.y -startScrollY + startY);
    	//}
		// Stop panel at the bottom - End

        setTimeout("stayTopLeft()", 10);
    }
    ftlObj = ml("rightpanel-in");
    stayTopLeft();
}


if (window.addEventListener)
window.addEventListener("load", staticbar, false);
else if (window.attachEvent)
window.attachEvent("onload", staticbar);
else if (document.getElementById)
window.onload=staticbar;

