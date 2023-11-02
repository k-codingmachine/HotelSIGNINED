<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
	if (window.name == "admin"){
		if(${replyCheck} == 1){
			alert("이미 답변을 입력했습니다.");
			window.opener.parent.location.href = "HotelServlet?command=QnA_view&num=${param.num}";
		}else {
			window.opener.parent.location.href = "HotelServlet?command=QnA_reply_form_admin&num=${param.num}";
		}
	}
	window.close();
	</script>
</body>
</html>