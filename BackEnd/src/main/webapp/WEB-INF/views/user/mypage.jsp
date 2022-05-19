<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내 정보</title>
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

<script type="text/javascript">
// 회원 정보 수정
 $(document).ready(function () {
            $("#updateBtn").click(function () {
                passCheck("update");
            });
            
            $("#deleteBtn").click(function () {
                passCheck("delete");
            });
        });
        
function update() {
	if (!$("#name").val()) {
        alert("이름 입력!!!");
        return;
    } else if (!$("#pass").val()) {
        alert("비밀번호 입력!!!");
        return;
    }  else {
    	let modifyinfo = JSON.stringify({
    		"id": $("#id").val(),
    		"pass":$("#pass").val(),
    		"name":$("#name").val(),
    		"email":$("#email").val(),
    		"phone":$("#phone").val()
    	});
    	console.log(modifyinfo);
    	$.ajax({
    		url:'${root}/user/mypage',
    		type:'PUT',
    		contentType:'application/json;charset=utf-8',
    		data: modifyinfo,
    		success:function(){
       	alert("정보수정 성공!");
      window.location.reload();
    		},error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
    	});
    }
}

// 회원탈퇴
function deleteUser(){
	if(confirm("정말 탈퇴를 원하시나요?")){
		let modifyinfo = JSON.stringify({
    		"id": $("#id").val(),
    		"pass":$("#pass").val(),
    		"name":$("#name").val(),
    		"email":$("#email").val(),
    		"phone":$("#phone").val()
    	});
    	console.log(modifyinfo);
    	$.ajax({
    		url:'${root}/user/mypage',
    		type:'DELETE',
    		contentType:'application/json;charset=utf-8',
    		data: modifyinfo,
    		success:function(){
       	alert("삭제 성공!");
      window.location.replace("${root}/");
    		},error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
    	});
	}
}
function passCheck(mod){
	let pass = prompt('현재 비밀번호를 입력해 주세요', 'password'); 
	if(pass==null) return;
	$.ajax({
		url:'${root}/user/mypage/passCheck',
		type:'POST',
		contentType:'application/json;charset=utf-8',
		data: pass,
		success:function(check){
			if(check=='true'){
				if(mod==="update") update();
				else deleteUser();
			}
			else{
				alert("비밀번호가 틀렸습니다!!");
				return;
			}
		},
		error: function(request, status, error) {
      		console.log("status : " + request.status + "\tmsg : " + error);
      	}
	});
} 
</script>
<body>
	<%@ include file="/WEB-INF/views/template/header.jsp"%>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="orange">내 정보</mark>
			</h2>
			<form id="memberform" class="text-left mb-3" method="post" action="">
				<div class="form-group">
					<label for="userid">아이디</label> <input type="text"
						class="form-control" id="id" name="id"
						value="${userinfo.id}" disabled/>
					<div id="idresult" class="mt-1"></div>
				</div>
				<div class="form-group">
					<label for="userpwd">비밀번호</label> <input type="password"
						class="form-control" id="pass" name="pass"
						placeholder="비밀번호입력...">
				</div>
		
				<div class="form-group">
					<label for="username">이름</label> <input type="text"
						class="form-control" id="name" name="name"
						value="${userinfo.name}">
				</div>
				<div class="form-group">
					<label for="email">이메일</label>
					<input type="email"
						class="form-control" id="email" name="email"
						value="${userinfo.email}">
				</div>
				<div class="form-group">
					<label for="phone">전화번호</label> <input type="text"
						class="form-control" id="phone" name="phone"
						value="${userinfo.phone}">
				</div>
				
				<div class="form-group text-center">
					<button type="button" id="updateBtn"
						class="btn btn-outline-primary">정보수정</button>
					<button type="button" id="deleteBtn" class="btn btn-outline-danger">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>