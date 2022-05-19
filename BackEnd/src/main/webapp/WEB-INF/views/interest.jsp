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
<%@ include file="./interestjs.jsp" %>
<body>
<%@ include file="./template/header.jsp" %> <br>
<%@ include file="./dongCode/sigungudongbody.jsp" %>
<section class="main-container">

        <div class="container">
            <div class="row mt-5 ml-3">
                <aside class = "col-lg-3 order-lg-1">
                    <div class="sidebar">
                        <h3 class="title" style="display:inline">관심 지역</h3>
                        
						<select id="sort" onchange="sortMethod(this.value)">
						    <option value="default">오래된순</option>
						    <option value="new">최신순</option>
						</select>
						
                        <div id ="reload"></div>
                        <div class="line"><hr></div>
                        <div id="interest" name="interest"></div>
                    </div>
                </aside>
                <div class="mt-5" id="map" style="width:70%;height:500px;margin:20px"></div>
            </div>
        </div>
    </section>
