<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원가입</title>
</head>
<body>
	<form action="signup.do" method="post">
		ID<input type="text" name="userID"><br>
		팀이름<input type="text" name="teamName"><br>
		password<input type="password" name="userPassword"><br>
		<input type="submit" value="가입 신청하기">
	</form>
	<br>
<a href="${pageContext.request.contextPath}/loginController/logout" onclick="myFunction()">돌아가기</a>
</body>
</html>