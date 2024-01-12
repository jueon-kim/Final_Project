<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>강의평가 웹 사이트</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="./css/custom.css"><!-- 커스텀 -->
</head>
<body>
	
	
	<section class="container mt-3" style="max-width: 560px;">
		<form action="login.do" method="post">
			<div class="form-group">
				<label>이메일</label>
				<input type="text" name="email" class="form-control">
			</div>
			<div class="form-group">
				<label>비밀번호</label>
				<input type="password" name="password" class="form-control">
			</div>
			<button type="submit" class="btn btn-primary">로그인</button>
			<a class="btn btn-primary" href="../member/NewMemberRegistration.jsp">회원가입</a>
		</form>
	</section>


	<footer class="bg-dark mt-4 p-5 text-center" style="color: #ffffff;">
		Copyright &copy; 2024 최현우 All Rights Reserved.
	</footer>


<!-- 제이쿼리 자바스크립트 추가 -->
<script src="./js/jquery.min.js"></script>
<!-- 파퍼 -->
<script src="./js/popper.js"></script>
<!-- 부트스트랩 -->
<script src="./js/bootstrap.min.js"></script>

</body>
</html>
