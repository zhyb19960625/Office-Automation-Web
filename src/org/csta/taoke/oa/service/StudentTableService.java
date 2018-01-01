package org.csta.taoke.oa.service;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Vector;

import org.csta.taoke.oa.entity.Student;

public class StudentTableService {
	private Connection connection;
	private static Properties properties = new Properties();
	
	public StudentTableService(Connection connection) {
		this.connection = connection;
		try {
			properties.load(getClass().getResourceAsStream("/config/student.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertStudent(Student student) {
		try {
			PreparedStatement statement = connection.prepareStatement(("insert into office.student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
			for (int i = 1; i <= 21; i++) {
				String fieldName = properties.getProperty(Integer.toString(i));
				Method getMethod = student.getClass().getDeclaredMethod(new StringBuilder("get").append(fieldName).toString(), null);
				Object result = getMethod.invoke(student, null);
				statement.setObject(i, result);
			}
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	public static Student conventFromData(Vector<String> data) throws Exception{
		Student student = new Student();
		int i = 1;
		for (String string : data) {
			String fieldName = properties.getProperty(Integer.toString(i));
			Method setMethod = student.getClass().getDeclaredMethod(new StringBuilder("set").append(fieldName).toString(), String.class);
			setMethod.invoke(student	, string);
			i++;
		}
		return student;
	}
}
