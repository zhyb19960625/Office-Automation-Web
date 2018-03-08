package org.csta.taoke.oa.test;

import java.io.File;
import java.sql.Connection;

import org.csta.taoke.oa.database.DataBaseConnector;
import org.csta.taoke.oa.importer.StudentInfoImporter;
/**
 * 功能：
 * 想测试什么，就写在这里吧～～～
 * 
 * 修订版本：
 * 此文件无需编写修订版本
 * 
 * @author 路伟饶
 *
 */
@Deprecated
public class TestMain {

	public static void main(String[] args) throws Exception {
		Connection connection = DataBaseConnector.getCSTADBConnection();
		StudentInfoImporter importer=new StudentInfoImporter(connection, new File("testExcels.xlsx"));
		importer.importData();
	}

}
