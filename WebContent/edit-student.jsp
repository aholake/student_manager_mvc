<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.tanloc.model.StudentDAO"%>
<%@page import="com.tanloc.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<!-- Date picker -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/css/bootstrap-datepicker.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/js/bootstrap-datepicker.js"></script>
</head>
<body>
	<%
		StudentDAO studentDao = new StudentDAO();
		Student student = null;
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			student = studentDao.getStudentById(id);
		}
	%>
	<%
		if (student != null) {
	%>
	<h1 class="text-center">Edit Student ID: <%=student.getId() %></h1>
	<form class="form col-md-6 col-md-offset-3" action="updateStudent"
		method="post">
		<input type="hidden" required name="id" value="<%=student.getId()%>">
		<div class="form-group">
			<label>First Name: </label> <input class="form-control" type="text"
				required name="firstName" value="<%=student.getFirstName()%>">
		</div>
		<div class="form-group">
			<label>Last Name: </label> <input class="form-control" type="text"
				required name="lastName" value="<%=student.getLastName()%>">
		</div>
		<div class="form-group">
			<label>Birth Date:</label> <input name="birthDate" type="text"
				class="form-control" required id="datepicker"
				value="<%=new SimpleDateFormat("dd/MM/YYYY").format(student.getBirthDate())%>">
		</div>
		<div class="form-group-btn">
			<button class="btn btn-primary" type="submit">Save</button>
		</div>
	</form>
	<%
		}
	%>

	<script type="text/javascript">
		$("#datepicker").datepicker({
			format : 'dd/mm/yyyy'
		});
	</script>
</body>
</html>