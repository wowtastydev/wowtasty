var myMessages = ['info','warning','error','success']; // define the messages types		 
function hideAllMessages()
{
    var messagesHeights = new Array(); // this array will store height for each
	 
    for (i=0; i<myMessages.length; i++)
    {
        messagesHeights[i] = $('.' + myMessages[i]).outerHeight();
        $('.' + myMessages[i]).css('top', -messagesHeights[i]); //move element outside viewport	  
    }
    
    $('.message').click(function(){			  
        $(this).animate({
            top: -$(this).outerHeight()
        }, 500);
    });
}
            
