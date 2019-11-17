package jc.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * util of excel
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class PoiUtil {
	
	public static void main(String args[]) {
	     List<String[]> data = new ArrayList<String[]>();
	     String[] th1 = {"时间","性别","年龄","风格","id","行为"};
	     data.add(th1);
	     String[] td = {"时间1","性别1","年龄1","风格1","id1","行为1"};
	     data.add(td);
	     PoiUtil.writeExcel("/Users/Desktop/aa1/","test3", "测试", data);
	}
	
	public static void writeExcel(File file, String sheetName, List<String[]> data) {
        OutputStream out = null;
        try {
        	if(!file.exists()) {
        		file.createNewFile();
        	}
            // 创建 EXCEL 工作簿
        	HSSFWorkbook workBook = new HSSFWorkbook();
            // 创建 EXCEL Sheet 页
            HSSFSheet sheet = workBook.createSheet(sheetName);
            // 声明 EXCEL 行
            HSSFRow row;
            // 声明 EXCEL 单元格
            HSSFCell cell;

            // 迭代设置EXCEL每行数据
            int rowNo = 0; // 行号
            for (String[] objs : data) {
                row = sheet.createRow(rowNo++);
                // 迭代设置EXCEL当前行每个单元格数据
                int cellNo = 0; // 列号
                for (String obj : objs) {
                    cell = row.createCell(cellNo++);
                    cell.setCellValue(String.valueOf(obj));
                }
            }
            // 设置文件输出流，写入EXCEL数据
//            String excelName = StringUtils.join(filePath, FilenameUtils.getBaseName(fileName), Symbol.DOT.getSymbol().concat("xls"));
            out = new FileOutputStream(file);
            // 写入文件流
            workBook.write(out);
            workBook.close();
            return ;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ;
    }
	 /**
     * @Remark 将数据写入EXCEL
     * @Demo List<String[]> data = new ArrayList<String[]>();
     * @Demo String[] th1 = {"时间","性别","年龄","风格","id","行为"};
     * @Demo data.add(th1);
     * @Demo String[] td = {"时间1","性别1","年龄1","风格1","id1","行为1"};
     * @Demo data.add(td);
     * @Demo PoiUtil.writeExcel("/Users/zero/Desktop/test/", "test2", "测试", data);
     * @param dirPath   文件夹路径
     * @param fileName  EXCEL文件名称
     * @param sheetName EXCEL页名
     * @param data      待写入数据
     * @throws Exception
     */
	public static String writeExcel(String dirName, String fileName, String sheetName, List<String[]> data) {
        OutputStream out = null;
        File file = null;
        try {
            // 创建 EXCEL 工作簿
        	HSSFWorkbook workBook = new HSSFWorkbook();
            // 创建 EXCEL Sheet 页
            HSSFSheet sheet = workBook.createSheet(sheetName);
            // 声明 EXCEL 行
            HSSFRow row;
            // 声明 EXCEL 单元格
            HSSFCell cell;

            // 迭代设置EXCEL每行数据
            int rowNo = 0; // 行号
            for (String[] objs : data) {
                row = sheet.createRow(rowNo++);
                // 迭代设置EXCEL当前行每个单元格数据
                int cellNo = 0; // 列号
                for (String obj : objs) {
                    cell = row.createCell(cellNo++);
                    cell.setCellValue(String.valueOf(obj));
                }
            }

            // 创建文件所在目录
//            String filePath = FilenameUtils.getFullPath(fileName);
            File dir = new File(dirName);
            if(!dir.exists()) {
            	dir.mkdirs();
            }
            // 设置文件输出流，写入EXCEL数据
//            String excelName = StringUtils.join(filePath, FilenameUtils.getBaseName(fileName), Symbol.DOT.getSymbol().concat("xls"));
            String excelName = dirName + fileName + ".xls";
            file = new File(excelName);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(excelName);
            // 写入文件流
            workBook.write(out);
            workBook.close();
            return excelName;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getPath();
    }
    /**
     * 读取EXCEL中指定下标页的数据
     *
     * @param fileName
     * @param sheetIndex
     * @return
     */
	public static List<String[]> readExcel(String fileName, Integer sheetIndex) {
        List<String[]> list = null;
        InputStream in = null;
        try {
            // 获取文件输入流
//            String excelName = StringUtils.join(FilenameUtils.getFullPath(fileName), FilenameUtils.getBaseName(fileName), Symbol.DOT.getSymbol().concat("xls"));
            String excelName =  fileName + ".xls";
            in = new FileInputStream(excelName);
            // 创建 EXCEL 工作簿
            HSSFWorkbook workBook = new HSSFWorkbook(in);
            // 获取 EXCEL Sheet 页
            HSSFSheet sheet = workBook.getSheetAt(sheetIndex);

            list = new ArrayList<String[]>();
            String[] strArr = null;
            // 遍历每行记录
            for (Row row : sheet) {
                strArr = new String[row.getPhysicalNumberOfCells()];
                // 遍历每单元格记录
                for (Cell cell : row) {
                    // 根据单元格的类型获取不同数据类型的值
                    CellType cellType = cell.getCellTypeEnum();
                    if (CellType.NUMERIC.equals(cellType))
                        strArr[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
                    else if (CellType.STRING.equals(cellType))
                        strArr[cell.getColumnIndex()] = cell.getStringCellValue();
                }
                // 将数据放入集合
                list.add(strArr);
            }

            workBook.close();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        return list;
    }	
}
