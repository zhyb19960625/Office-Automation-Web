package org.csta.taoke.oa.excel;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 功能：
 * 导入导出Excel文件
 * 
 * 修订版本：
 * 2017-12-17 首次编写
 * 2017-12-18 00:25 写不动了......睡觉
 * 
 * @author 路伟饶
 *
 */
public class ExcelFileSolver {
	private File targetFile;
	
	public ExcelFileSolver(File file) {
		this.targetFile = file;
	}
	
	public void writeData(Vector<Vector<String>> data) {
		XSSFWorkbook xlsx = new XSSFWorkbook();
	}
	public Vector<Vector<String>> readData() throws IllegalArgumentException, InvalidFormatException, IOException {
		if (targetFile.getName().endsWith(".xlsx")) {
			XSSFWorkbook xlsx = new XSSFWorkbook(targetFile);
		}
		else if (targetFile.getName().endsWith(".xls")) {
			HSSFWorkbook xls = new HSSFWorkbook();
		
		}
		else {
			throw new IllegalArgumentException("不是Excel文件后缀");
		}
		return null;
	}
}







