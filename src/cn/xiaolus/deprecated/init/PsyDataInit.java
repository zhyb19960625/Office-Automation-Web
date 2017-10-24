package cn.xiaolus.deprecated.init;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.xiaolus.deprecated.database.PsyDBService;

public class PsyDataInit {
	
	public static void initDatabaseFromExcel(File excelFile) throws Exception {
		PsyDBService service = new PsyDBService();
		Hashtable<String, String> table = service.getDBInfoFromProperties(new FileInputStream("./src/config/mariadb.properties"));
		service.connertDatabase(
			table.get("driver"), 
			"jdbc:mysql://db.cstacauc.cn/cspsy?useSSL=true",
			table.get("username"), 
			table.get("password").toCharArray()
		);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet=workbook.getSheetAt(0);
		for(int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();++i) {
			XSSFRow row = sheet.getRow(i);
			String schoolid = row.getCell(0).getStringCellValue();
			String name = row.getCell(1).getStringCellValue();
			String personid = row.getCell(2).getStringCellValue();
			String psypasswd = row.getCell(3).getStringCellValue();
//			System.out.println(schoolid+" "+name+" "+personid+" "+psypasswd);
//			service.initPsyInfo(schoolid, name, personid, psypasswd);
		}
		workbook.close();
	}
	
	public static void main(String[] args) throws Exception {
		PsyDataInit.initDatabaseFromExcel(new File("CoreData.xlsx"));
	}

}
