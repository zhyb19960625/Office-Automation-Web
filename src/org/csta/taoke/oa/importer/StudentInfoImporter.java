package org.csta.taoke.oa.importer;

import java.io.File;
import java.sql.Connection;
import java.util.Vector;

import org.csta.taoke.oa.database.DataBaseConnector;
import org.csta.taoke.oa.entity.Student;
import org.csta.taoke.oa.excel.ExcelFileSolver;
import org.csta.taoke.oa.service.StudentTableService;

public class StudentInfoImporter {
	private Connection connection;
	private ExcelFileSolver solver;
	public StudentInfoImporter(File file) {
		try {
			connection = DataBaseConnector.getCSTADBConnection();
			solver = new ExcelFileSolver(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void importData() throws Exception {
		Vector<Vector<String>> data = solver.readData();
		StudentTableService service = new StudentTableService(connection);
		for (Vector<String> vector : data) {
			Student student = StudentTableService.conventFromData(vector);
			service.insertStudent(student);
		}
		connection.close();
	}
	
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
		new StudentInfoImporter(new File("testExcels.xlsx")).importData();
	}

}
