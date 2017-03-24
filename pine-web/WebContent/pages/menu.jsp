<%@page import="com.netsteadfast.base.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<script type="text/javascript">

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
		url += '&<%=Constants.PAGE_CHANGE_URL_PARAM%>=Y&isPageRefresh=' + guid();
	} else {
		url += '?<%=Constants.PAGE_CHANGE_URL_PARAM%>=Y&isPageRefresh=' + guid();
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

</script>

<!-- Modal Start here-->
<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="myPleaseWait">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="mySmallModalLabel">Please wait!</h4>
      </div>
      <div class="modal-body">
        <img alt="loading" src="./images/loadingAnimation.gif" border="0">
      </div>
    </div>
  </div>
</div>
<!-- Modal ends Here -->

<nav class="navbar navbar-toggleable-md navbar-light bg-faded navbar-inverse bg-inverse">

  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="./index.do"><img alt="Pine" src="./images/pine.png" border="0"></a>
  
  
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    
    <ul class="nav navbar-nav">
      <li class="nav-item dropdown active">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Application
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          
          <a class="dropdown-item" href="#">Publish jobs</a>
		  
		  <a class="dropdown-item" href="#" onclick="changePage('./brokerList.do');">Broker</a>
		  
		  <div class="dropdown-divider"></div>
		  <a class="dropdown-item" href="./index.do">Refresh</a>
          
        </div>
      </li>	
      <li class="nav-item">
        <a class="nav-link" href="./index.do"><b>Dashboard</b></a>
      </li>          
      <li class="nav-item">
        <a class="nav-link" href="#" onclick="changePage('./pages/about.html');"><b>About</b></a>
      </li>     
      <li class="nav-item">
        <a class="nav-link" href="#" onclick="logoutEvent();"><b>Logout</b></a>
      </li>   	  
    </ul>
    
  </div>  
  
</nav>