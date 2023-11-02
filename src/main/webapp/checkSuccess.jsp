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
		if (window.name == "view") {
			window.opener.parent.location.href = "HotelServlet?command=QnA_view&num=${param.num}";

		} else if (window.name == "delete") {
			window.opener.parent.location.href = "HotelServlet?command=QnA_delete&num=${param.num}";

		}
		window.close();
	</script>
</body>
</html>
