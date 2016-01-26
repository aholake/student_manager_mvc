<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.tanloc.model.StudentDAO"%>
<%@page import="com.tanloc.model.Student"%>
<%@page import="java.util.List"%>
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

<%
	StudentDAO studentDao = new StudentDAO();

	List<Student> students = null;

	String searchKey = request.getParameter("key");
	if (searchKey != null && searchKey != "") {
		students = studentDao.searchStudent(searchKey);
	} else {
		students = studentDao.getStudentList();
	}
%>
<body>
	<h1 class="text-center">Welcome to Student Management</h1>

	<div class="container">
		<form class="form-horizontal col-md-6 col-md-offset-3" action="home"
			method="get">
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" class="form-control" placeholder="Search..."
						required name="key">
				</div>
				<div class="col-sm-4">
					<button class="btn btn-primary" type="submit">Tìm kiếm</button>
				</div>
			</div>
		</form>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Birth Date</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					if (students.size() > 0) {
						for (Student student : students) {
				%>
				<tr>
					<td><%=student.getId()%></td>
					<td><%=student.getFirstName()%></td>
					<td><%=student.getLastName()%></td>
					<td><%=new SimpleDateFormat("dd/MM/yyyy").format(student.getBirthDate())%></td>
					<td class="text-center"><a
						href="delete?id=<%=student.getId()%>"
						class="glyphicon glyphicon-remove"></a> <a
						href="edit?id=<%=student.getId()%>"
						class="glyphicon glyphicon-pencil"></a></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
		<ul class="pagination">
			<li><a href="#">1</a></li>
			<li class="active"><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
		<div class="row">
			<form class="form col-md-6 col-md-offset-3" action="addStudent"
				method="post">
				<div class="form-group">
					<label>First Name: </label> <input class="form-control" type="text"
						required name="firstName">
				</div>
				<div class="form-group">
					<label>Last Name: </label> <input class="form-control" type="text"
						required name="lastName">
				</div>
				<div class="form-group">
					<label>Birth Date:</label> <input name="birthDate" type="text"
						class="form-control" required id="datepicker">
				</div>
				<div class="form-group-btn">
					<button class="btn btn-primary" type="submit">Add</button>
					<button class="btn" type="reset">Clear</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$("#datepicker").datepicker({
			format : 'dd/mm/yyyy'
		});
	</script>
</body>
</html>