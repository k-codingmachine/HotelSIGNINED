<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/QnAview.js"></script>
</head>
<body>
<div align="center">
		<h1>관리자 비밀번호 확인</h1>
		<form action="HotelServlet" name="frm" method="post">
			<input type="hidden" name="command" value="QnA_admin_check_pass" />
			<input type="hidden" name="num" value="${param.num}" />

			<table style="width: 80%">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pass" size="20" /></td>
				</tr>
			</table>
			<br /> <input type="submit" value="확인" onclick="return passCheck()" />
		</form>
		${message}
	</div>
</body>
</html>