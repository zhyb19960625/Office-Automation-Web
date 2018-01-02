package org.csta.taoke.oa.importer;

import java.io.File;
import java.sql.Connection;
import java.util.Vector;

import org.csta.taoke.oa.database.DataBaseConnector;
import org.csta.taoke.oa.entity.Student;
import org.csta.taoke.oa.excel.ExcelFileSolver;
import org.csta.taoke.oa.service.StudentTableService;
/**
 * 功能：
 * 从Excel文件导入学生信息到数据库
 * 
 * 修订版本
 * 2018-01-01 首次编写
 * 
 * @author 路伟饶
 *
 */
public class StudentInfoImporter {
	private Connection connection;
	private ExcelFileSolver solver;
	/**
	 * 构造方法
	 * @param file 有学生信息的Excel文件，要求单元格都是文本型
	 */
	public StudentInfoImporter(File file) {
		try {
			connection = DataBaseConnector.getCSTADBConnection();
			solver = new ExcelFileSolver(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导入学生信息到数据库
	 * @throws Exception
	 */
	public void importData() throws Exception {
//		读取Excel文件信息
		Vector<Vector<String>> data = solver.readData();
		StudentTableService service = new StudentTableService(connection);
//		向量每行作为一个学生信息插入
		for (Vector<String> vector : data) {
			Student student = StudentTableService.conventFromData(vector);
			service.insertStudent(student);
		}
		connection.close();
	}
	/**
	 * 测试用方法，添加@SuppressWarnings
	 * @param data 元数据
	 */
	@SuppressWarnings("unused")
	private void printData(Vector<Vector<String>> data) {
		for (Vector<String> vector : data) {
			for (String string : vector) {
				System.out.print(string);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		new StudentInfoImporter(new File("testExcels.xlsx")).importData();
	}

}
