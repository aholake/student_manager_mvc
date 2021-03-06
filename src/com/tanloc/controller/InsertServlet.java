package com.tanloc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanloc.model.Student;
import com.tanloc.model.StudentDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/addStudent")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO studenDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		studenDao = new StudentDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDateStr = request.getParameter("birthDate");
		java.sql.Date birthDate = null;
		try {
			Date dateUtil = new SimpleDateFormat("dd/MM/YYYY").parse(birthDateStr);
			birthDate = new java.sql.Date(dateUtil.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setBirthDate(birthDate);

		studenDao.addStudent(student);
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
