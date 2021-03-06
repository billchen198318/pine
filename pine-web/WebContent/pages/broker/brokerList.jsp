<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html>
<head>
<title>Pine</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../common-inc.jsp"></jsp:include>

<script type="text/javascript" src="<%=basePath%>/js/f.js"></script>

<style type="text/css">



</style>

<script type="text/javascript">

function restartBroker(oid) {
	bootbox.confirm(
			"Restart service?", 
			function(result){ 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./brokerStartJson.do',
						{'oid' : oid},
						function(data) { 
							if ('Y' != data.success) {
								parent.toastrWarning( data.message );
								return;
							}
							if ('Y' == data.success) {
								parent.toastrInfo( data.message );
								parent.changePage("./brokerList.do");
							}
						},
						function(){ parent.changePage("./brokerList.do"); });
			}
	);
}

function stopBroker(oid) {
	bootbox.confirm(
			"Stop service?", 
			function(result){ 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./brokerStopJson.do',
						{'oid' : oid},
						function(data) { 
							if ('Y' != data.success) {
								parent.toastrWarning( data.message );
								return;
							}							
							if ('Y' == data.success) {
								parent.toastrInfo( data.message );
								parent.changePage("./brokerList.do");
							}
						},
						function(){ parent.changePage("./brokerList.do"); });
			}
	);	
}

function deleteBroker(oid) {
	bootbox.confirm(
			"Delete?", 
			function(result){ 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./brokerDeleteJson.do',
						{'oid' : oid},
						function(data) { 
							if ('Y' != data.success) {
								parent.toastrWarning( data.message );
								return;
							}							
							if ('Y' == data.success) {
								parent.toastrInfo( data.message );
								parent.changePage("./brokerList.do");
							}
						},
						function(){ parent.changePage("./brokerList.do"); });
			}
	);	
}


function restartAllBroker() {
	bootbox.confirm(
			"Restart all service?", 
			function(result){ 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./brokerRestartAllJson.do',
						{ },
						function(data) { 
							if ('Y' != data.success) {
								parent.toastrWarning( data.message );
								return;
							}							
							if ('Y' == data.success) {
								parent.toastrInfo( data.message );
								parent.changePage("./brokerList.do");
							}
						},
						function(){ parent.changePage("./brokerList.do"); });
			}
	);	
}

function stopAllBroker() {
	bootbox.confirm(
			"Stop all service?", 
			function(result){ 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./brokerStopAllJson.do',
						{ },
						function(data) { 
							if ('Y' != data.success) {
								parent.toastrWarning( data.message );
								return;
							}							
							if ('Y' == data.success) {
								parent.toastrInfo( data.message );
								parent.changePage("./brokerList.do");
							}
						},
						function(){ parent.changePage("./brokerList.do"); });
			}
	);	
}

</script>

</head>

<body>

<jsp:include page="../top-bar-q.jsp">
	<jsp:param name="programName" value="Broker management"/>
	<jsp:param name="refreshUrl" value="./brokerList.do"/>
	<jsp:param name="createUrl" value="./brokerCreate.do"/>
</jsp:include>

<c:if test="${!empty brokers}">
<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>ID</th>
      <th>Name</th>
      <th>Description</th>      
      <th>Status</th>
      <th>Edit/Delete</th>      
    </tr>
  </thead>
  <tbody>
  
<c:forEach items="${brokers}" var="item" varStatus="myIndex">
    <tr>  
      <th scope="row">${myIndex.index+1}</th> 
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.description}</td>
      <td>
		<c:if test="${\"Y\".equals(item.start)}">
			<span class="badge badge-success">In service</span>
		</c:if>
		<c:if test="${!\"Y\".equals(item.start)}">
			<span class="badge badge-warning">Stop</span>
		</c:if>
		<c:if test="${!\"Y\".equals(item.found)}">
			<span class="badge badge-danger">Container not found broker</span>
		</c:if>
      </td>
      <td>
      
		<img alt="edit" title="Edit" src="./images/edit.png" onclick="parent.changePage('./brokerEdit.do?oid=${item.oid}');"/>      
		&nbsp;
		<img alt="service" title="Restart service" src="./images/service.png" onclick="restartBroker('${item.oid}');"/>     
		&nbsp;
		<img alt="stop" title="Stop service" src="./images/stop.png" onclick="stopBroker('${item.oid}');"/>     
		&nbsp;
		<img alt="delete" title="Delete" src="./images/delete.png" onclick="deleteBroker('${item.oid}');"/>   
   	
      </td>
    </tr>    
</c:forEach>  

  </tbody>
</table>
</c:if>

<c:if test="${!empty brokers}">
<table border="0" width="100%" cellspacing="2" cellpadding="2">
	<tr valign="top" align="left">
		<td align="left" width="100%">
			<div>

				<img alt="service" title="Restart all service" src="./images/service.png" onclick="restartAllBroker();"/>     
				&nbsp;
				<img alt="stop" title="Stop all service" src="./images/stop.png" onclick="stopAllBroker();"/>     
				&nbsp;
				
			</div>		
		</td>
	</tr>
</table>
</c:if>

<c:if test="${empty brokers}">
	<div class="alert alert-info" role="alert">
	  <strong>${pageMessage}</strong>
	</div>	
</c:if>
            
</body>
</html>
