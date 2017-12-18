package org.csta.taoke.oa.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 功能：
 * 导入导出Excel文件
 * 
 * 修订版本：
 * 2017-12-18 首次编写
 * 
 * @author 路伟饶
 *
 */
public class ExcelFileSolver {
	private File targetFile;
	
	public ExcelFileSolver(File file) {
		this.targetFile = file;
	}
	
	public void writeData(Vector<Vector<String>> data) throws InvalidFormatException, IOException {
		if (targetFile.getName().endsWith(".xlsx")) {
			XSSFWorkbook xlsx = new XSSFWorkbook();
			XSSFSheet sheet = xlsx.createSheet();
			int i = 0;
			for (Iterator<Vector<String>> rowIterator = data.iterator(); rowIterator.hasNext();++i) {
				Vector<String> vector = (Vector<String>) rowIterator.next();
				XSSFRow row = sheet.createRow(i);
				int j = 0;
				for (Iterator<String> colIterator = vector.iterator(); colIterator.hasNext();++j) {
					String string = (String) colIterator.next();
					XSSFCell cell = row.createCell(j);
					cell.setCellValue(string);
				}
			}
			FileOutputStream fos = new FileOutputStream(targetFile);
			xlsx.write(fos);
			xlsx.close();
		}
		else if (targetFile.getName().endsWith(".xls")) {
			HSSFWorkbook xls = new HSSFWorkbook();
			HSSFSheet sheet = xls.createSheet();
			int i = 0;
			for ( Iterator<Vector<String>> rowIterator = data.iterator(); rowIterator.hasNext();++i) {
				Vector<String> vector = (Vector<String>) rowIterator.next();
				HSSFRow row = sheet.createRow(i);
				int j = 0;
				for (Iterator<String> colIterator = vector.iterator(); colIterator.hasNext();++j) {
					String string = (String) colIterator.next();
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(string);
				}
			}
			FileOutputStream fos = new FileOutputStream(targetFile);
			xls.write(fos);
			xls.close();
		}
		else {
			throw new IllegalArgumentException("不是Excel文件后缀");
		}
	}
	public Vector<Vector<String>> readData() throws IllegalArgumentException, InvalidFormatException, IOException {
		if (targetFile.getName().endsWith(".xlsx")) {
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			XSSFWorkbook xlsx = new XSSFWorkbook(targetFile);
			XSSFSheet sheet = xlsx.getSheetAt(0);
			for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
				if (i < sheet.getFirstRowNum()) {
					data.add(null);
					continue;
				}
				XSSFRow row = sheet.getRow(i);
				Vector<String> rowData = new Vector<String>();
				for (int j = 0; j <= row.getLastCellNum(); ++j) {
					if (j < row.getFirstCellNum()) {
						rowData.add(null);
						continue;
					}
					XSSFCell cell = row.getCell(j);
					if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						rowData.add(cell.getStringCellValue());
					}
					else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						rowData.add(Double.toString(cell.getNumericCellValue()));
					}
					else {
						rowData.clear();
						data.clear();
						xlsx.close();
						throw new InvalidFormatException("");
					}
				}
				data.add(rowData);
			}
			xlsx.close();
			return data;
		}
		else if (targetFile.getName().endsWith(".xls")) {
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			POIFSFileSystem fileSystem = new POIFSFileSystem(targetFile, true);
			HSSFWorkbook xls = new HSSFWorkbook(fileSystem);
			HSSFSheet sheet = xls.getSheetAt(0);
			for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
				if (i < sheet.getFirstRowNum()) {
					data.add(null);
					continue;
				}
				HSSFRow row = sheet.getRow(i);
				Vector<String> rowData = new Vector<String>();
				for (int j = 0; j <= row.getLastCellNum(); ++j) {
					if (j < row.getFirstCellNum()) {
						rowData.add(null);
						continue;
					}
					HSSFCell cell = row.getCell(j);
					if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						rowData.add(cell.getStringCellValue());
					}
					else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						rowData.add(Double.toString(cell.getNumericCellValue()));
					}
					else {
						rowData.clear();
						data.clear();
						xls.close();
						throw new InvalidFormatException("");
					}
				}
				data.add(rowData);
			}
			xls.close();
			return data;
		}
		else {
			throw new IllegalArgumentException("不是Excel文件后缀");
		}
	}
	public static void main(String[] args) {
		System.out.println("Testing Import and Export Excel File ");
	}
}

