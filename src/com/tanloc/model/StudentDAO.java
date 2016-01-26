package com.tanloc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentDAO {
	// JDBC drivername and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/student";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "11221122";

	private Connection connection;

	public void openConnection() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected to DB successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Student> getStudentList() {
		List<Student> list = new ArrayList<>();
		try {
			openConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM student";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				Date birthDate = resultSet.getDate("birth_date");

				Student student = new Student(id, firstName, lastName, birthDate);
				list.add(student);
			}
			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Get Student List failed");
		}
		return list;
	}

	public List<Student> searchStudent(String keyword) {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM student WHERE CONCAT(first_name,' ',last_name) LIKE '%" + keyword + "%'";

		openConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt(1));
				student.setFirstName(resultSet.getString(2));
				student.setLastName(resultSet.getString(3));
				student.setBirthDate(resultSet.getDate(4));

				list.add(student);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Student getStudentById(int id) {
		try {
			openConnection();
			String sql = "SELECT * FROM student WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int sId = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				Date birthDate = resultSet.getDate("birth_date");

				return new Student(sId, firstName, lastName, birthDate);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteStudent(int id) {
		openConnection();
		try {
			String sql = "DELETE FROM student WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStudent(Student student) {
		openConnection();
		String sql = "INSERT INTO student(first_name, last_name, birth_date) VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setDate(3, student.getBirthDate());

			preparedStatement.execute();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateStudent(Student student) {
		openConnection();
		String sql = "UPDATE student SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setDate(3, student.getBirthDate());
			preparedStatement.setInt(4, student.getId());

			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(new StudentDAO().searchStudent("huy").size());
		;

	}
}
