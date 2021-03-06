function logoutEvent() {
	bootbox.confirm(
			"Logout! are you sure?", 
			function(result){ 
				if (!result) {
					return;
				}
				window.location = "./logout.do";
			}
	);	
}

function showPleaseWait() {
	$('#myPleaseWait').modal('show');
}
function hidePleaseWait() {
	$('#myPleaseWait').modal('hide');
}

function changePage(url) {
	if ( url.indexOf("?") > -1 ) {
		url += '&' + _m_PAGE_CHANGE_URL_PARAM + '=Y&isPageRefresh=' + guid();
	} else {
		url += '?' + _m_PAGE_CHANGE_URL_PARAM + '=Y&isPageRefresh=' + guid();
	}
	$("#mainFrame").attr('src', url);
}

/* 
 * http://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript 
 */
function guid() {
	
	  function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	  }
	  
	  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
	  
}

function toastrInfo(message) {
	toastr.info( message );
}

function toastrWarning(message) {
	toastr.warning( message );
}
