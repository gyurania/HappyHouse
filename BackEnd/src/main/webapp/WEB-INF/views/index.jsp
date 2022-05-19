<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Happy House | 김규란_김승준</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	
<%@ include file="./dongCode/sigungudongjs.jsp" %>
<%@ include file="./map.jsp" %>
<script type="text/javascript">
function changeDong(){
	var formData = document.getElementById("frm");
	formData.setAttribute('method', 'get');
	formData.action='http://localhost:8080/dong/';
	formData.submit();
}
</script>

<body onload="makeMap('')">
	<%@ include file="./template/header.jsp" %> <br>
    <main>
        <%@ include file="./dongCode/sigungudongbody.jsp" %>
        <div class="container row mt-5 mb-5" style="float: none; margin:0 auto;">
                <div id="map" style="width:100%;height:500px; display: inline-block;"></div>
        </div>
    </main>
