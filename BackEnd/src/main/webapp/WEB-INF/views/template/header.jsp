<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
		<header id="index_header" class="jumbotron text-center mb-1">
			<a href="${root}/">
			<img id="logo" src="/img/happyhouse.png" width="400">
			</a>
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="${root}/">Home</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						동네 정보
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="http://localhost:8080/dong/">APT 매매</a>
<!-- 						<a class="dropdown-item" href="#">APT 전월세</a> -->
<!-- 						<a class="dropdown-item" href="#">주택 매매</a> -->
<!-- 						<a class="dropdown-item" href="#">주택 전월세</a> -->
<!-- 						<a class="dropdown-item" href="#">상권 정보</a> -->
<!-- 						<a class="dropdown-item" href="#">환경 정보</a> -->
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${root}/notice/list">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">News</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact</a>
				</li>
			</ul>
			
			<div id="nav-login" style="display: flex; float: right;">
				<c:if test="${empty userinfo}">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="${root}/user/login">login</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${root}/user/join">Sign up</a>
						</li>
					</ul>
				</c:if>
				
				<c:if test="${!empty userinfo}">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" style="color:white;">${userinfo.name}</a>
						</li>
		      			<li class="nav-item">
		      				<a class="nav-link" href="${root}/interest">관심지역</a>
		      			</li>
						<li class="nav-item">
							<a class="nav-link" href="${root}/user/mypage">my page</a>
						</li>
						<li class="nav-item">
		      				<a class="nav-link" href="${root}/user/logout">logout</a>
		      			</li>
					</ul>
	      		</c:if>
	      		
			</div>
		</nav>
</div>

</body>
</html>