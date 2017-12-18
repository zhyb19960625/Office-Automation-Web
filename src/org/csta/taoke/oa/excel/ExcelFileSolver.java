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
 * 重要声明：
 * 不支持WPS表格的专有格式！！！
 * 不支持WPS表格的专有格式！！！
 * 不支持WPS表格的专有格式！！！
 * 重要的注释写三遍
 * 
 * 修订版本：
 * 2017-12-18 首次编写
 * 
 * @author 路伟饶
 *
 */
public class ExcelFileSolver {
	private File targetFile;
	/**
	 * 构造方法
	 * @param file 用于操作的文件
	 */
	public ExcelFileSolver(File file) {
		this.targetFile = file;
	}
	/**
	 * 导出到Excel文件
	 * @param data 待写入的数据，二维向量类型
	 * @throws InvalidFormatException 文件不是Excel文件
	 * @throws IOException 文件I/O错误
	 */
	public void writeData(Vector<Vector<String>> data) throws InvalidFormatException, IOException {
//		选择保存的文件类型为新版Microsoft Excel文件，使用XSSF系列类型
		if (targetFile.getName().endsWith(".xlsx")) {
//			获得工作簿对象
			XSSFWorkbook xlsx = new XSSFWorkbook();
//			获得工作表对象
			XSSFSheet sheet = xlsx.createSheet();
			int i = 0;
//			迭代器，迭代数据行
			for (Iterator<Vector<String>> rowIterator = data.iterator(); rowIterator.hasNext();++i) {
//				获得一行数据
				Vector<String> vector = (Vector<String>) rowIterator.next();
//				创建行对象
				XSSFRow row = sheet.createRow(i);
				int j = 0;
//				迭代器，迭代一行数据的每一列
				for (Iterator<String> colIterator = vector.iterator(); colIterator.hasNext();++j) {
//					获得一个单元格的数据并写入数据
					String string = (String) colIterator.next();
					XSSFCell cell = row.createCell(j);
					cell.setCellValue(string);
				}
			}
//			输出到文件
			FileOutputStream fos = new FileOutputStream(targetFile);
			xlsx.write(fos);
			xlsx.close();
		}
//		选择保存的文件类型为旧版Microsoft Excel 1997-2003 文件，使用HSSF系列类型
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
//		其他后缀，不被允许
		else {
			throw new IllegalArgumentException("不是Excel文件后缀");
		}
	}
	/**
	 * 从Excel文件导入数据
	 * @return 返回的数据集合，是二维向量类型
	 * @throws IllegalArgumentException 文件不是Excel文件
	 * @throws InvalidFormatException Excel中含有不受支持的单元格，仅支持文本型和数字型
	 * @throws IOException 文件I/O错误
	 */
	public Vector<Vector<String>> readData() throws IllegalArgumentException, InvalidFormatException, IOException {
//		目标文件类型为新版Microsoft Excel文件，使用XSSF系列类型
		if (targetFile.getName().endsWith(".xlsx")) {
//			创建数据二维向量
			Vector<Vector<String>> data = new Vector<Vector<String>>();
//			打开目标工作簿
			XSSFWorkbook xlsx = new XSSFWorkbook(targetFile);
//			获得第一个工作表
			XSSFSheet sheet = xlsx.getSheetAt(0);
//			从0开始迭代行
//			从0开始的原因是，如果前面有空行，则存入向量之后空行效果将丢失
			for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
//				保留数据开始之前的空行
				if (i < sheet.getFirstRowNum()) {
					data.add(null);
					continue;
				}
//				获得行对象，迭代一行数据
				XSSFRow row = sheet.getRow(i);
				Vector<String> rowData = new Vector<String>();
//				从0开始迭代一行数据
//				从0开始的原因是，如果前面有空单元格，则存入向量之后将会错位
				for (int j = 0; j <= row.getLastCellNum(); ++j) {
//					保留数据开始之前的空单元格
					if (j < row.getFirstCellNum()) {
						rowData.add(null);
						continue;
					}
//					获得单元格对象
					XSSFCell cell = row.getCell(j);
//					判断单元格数据类型
//					只支持文本类型和数字类型
//					如果为文本类型单元格
					if (cell.getCellTypeEnum().equals(CellType.STRING)) {
						rowData.add(cell.getStringCellValue());
					}
//					数字类型单元格，也转化为文本类型存储
					else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
						rowData.add(Double.toString(cell.getNumericCellValue()));
					}
//					其他类型不被支持
					else {
						rowData.clear();
						data.clear();
						xlsx.close();
						throw new InvalidFormatException("不支持的单元格类型：仅支持文本和数字型");
					}
				}
//				将一行数据添加到向量中
				data.add(rowData);
			}
//			关闭文件并返回数据
			xlsx.close();
			return data;
		}
//		目标文件类型为旧版Microsoft Excel 1997-2003 文件，使用HSSF系列类型
		else if (targetFile.getName().endsWith(".xls")) {
			Vector<Vector<String>> data = new Vector<Vector<String>>();
//			HSSF系列类型中文件打开操作略有不同
//			需要使用POIFileSystem类打开文件再传入HSSFWorkbook
//			XSSF系列类型进行了简化，毕竟真的有点反人类......
//			再次证明一个道理：不要用97-03的格式，程序员写程序都更麻烦
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
						throw new InvalidFormatException("不支持的单元格类型：仅支持文本和数字型");
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
	/**
	 * 测试函数
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Testing Import and Export Excel File ");
	}
}

