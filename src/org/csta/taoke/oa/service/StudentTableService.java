package org.csta.taoke.oa.service;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Vector;

import org.csta.taoke.oa.entity.Student;

/**
 * 功能：
 * 操控学生表的服务类型
 * 该类用于操作数据库中的学生表，如果需要写增删改查的操作，请在该类中扩展
 * 
 * 修订版本：
 * 2018-01-01 首次编写
 * 
 * @author 路伟饶
 *
 */
public class StudentTableService {
	private Connection connection;
//	配置文件，将表头写入了配置文件
	private static Properties properties = new Properties();
	/**
	 * 构造方法
	 * @param connection 数据库连接
	 */
	public StudentTableService(Connection connection) {
		this.connection = connection;
		try {
			properties.load(getClass().getResourceAsStream("/config/student.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 插入学生信息
	 * @param student 学生对象
	 * @return 数据库受影响的行数
	 */
	public int insertStudent(Student student) {
		try {
			PreparedStatement statement = connection.prepareStatement(("insert into office.student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
			for (int i = 1; i <= 21; i++) {
				String fieldName = properties.getProperty(Integer.toString(i));
//				反射中的警告，null参数应该替换为空Class类型
//				但是我之前用还有问题，先这样写，不影响运行
//				我再研究研究反射再说
				Method getMethod = student.getClass().getDeclaredMethod(new StringBuilder("get").append(fieldName).toString(), null);
//				通过反射调用get方法
				Object result = getMethod.invoke(student, null);
				statement.setObject(i, result);
			}
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	/**
	 * 将元数据转换为学生对象
	 * @param data 元数据
	 * @return 学生对象
	 * @throws Exception
	 */
	public static Student conventFromData(Vector<String> data) throws Exception{
		Student student = new Student();
		int i = 1;
		for (String string : data) {
//			读取表头
			String fieldName = properties.getProperty(Integer.toString(i));
//			通过反射调用set方法
			Method setMethod = student.getClass().getDeclaredMethod(new StringBuilder("set").append(fieldName).toString(), String.class);
			setMethod.invoke(student	, string);
			i++;
		}
		return student;
	}
}
