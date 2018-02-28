package com.bisheng.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bisheng.util.constant.WEBConstants;

/**
 * Excel 导出工具类
 * 
 * @author lihao
 */
public class ExcelManager{
	protected static Logger logger = LoggerFactory.getLogger(ExcelManager.class);
	
	/**
	 * 检查空行
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkRowNull(Row row) {
		if(row == null){
			logger.error("处理excel中存在空行，即将跳出循环......");
			return true;
        }
		Cell cell = row.getCell(0);
		if(cell ==null || cell.equals("")|| cell.getCellType() == Cell.CELL_TYPE_BLANK){
			logger.error("处理excel中存在空单元格，即将跳出循环......");
			return true;
        }
		return false;
	}
	
	/**
	 * 获取单元格的内容
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (null == cell) {
			return cellValue;
		}
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            cellValue = cell.getRichStringCellValue().getString().trim();
            cellValue = cellValue.replace(" ", "");
            break;
        case Cell.CELL_TYPE_NUMERIC:
            cellValue = String.valueOf(cell.getNumericCellValue());
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
            break;
        case Cell.CELL_TYPE_FORMULA:
            cellValue = cell.getCellFormula();
            break;
        default:
            cellValue = "";
        }
        return cellValue;
    }

	/**
	 * 根据模板导出Excel
	 */
	@SuppressWarnings({ "static-access", "deprecation", "resource" })
	public static <T> void exportExcelByMould(OutputStream os, Integer startRow, List<T> resultList, String mode, Class<T> clazz){
		try {
			List<CellEntity> cellEntityList = CommonUtil.getcellEntityList(mode, clazz);
			InputStream is = ExcelManager.class.getClassLoader().getResourceAsStream(mode + ".xlsx");
			XSSFWorkbook _workbook = new XSSFWorkbook(is);
			XSSFCellStyle cellStyle = exportExcelSetting(_workbook);
			SXSSFWorkbook workbook = new SXSSFWorkbook(_workbook,1000);
			Sheet sheet = workbook.getSheetAt(0);
			for(int i=0; i<resultList.size(); i++){
				T entity = resultList.get(i);
				Row row = sheet.createRow(startRow);
				row.setHeight((short) (17*20));
				int index = 0;
				for(CellEntity cellEntity : cellEntityList){
					row.getCell(index, row.CREATE_NULL_AS_BLANK).setCellStyle(cellStyle);
					Cell cell = row.createCell(cellEntity.cell.Index());
					cellEntity.getField().setAccessible(true);
					Object value = cellEntity.getField().get(entity);
					if(value == null){
						value = "";
					}
					if(value instanceof Integer || value instanceof Long){
						double val = Double.parseDouble(value+"");
						cell.setCellValue(val); 
					}else if(value instanceof BigDecimal){
						cell.setCellValue(((BigDecimal) value).toString());
					}else if(value instanceof Date){
						cell.setCellValue((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((Date)value));
					}else{
						MethodUtils.invokeExactMethod(cell, "setCellValue", value);
					}
//					MethodUtils.invokeExactMethod(cell, "setCellValue", value);
					index ++ ;
				}
				 startRow++;
			}
			//FileOutputStream fos = new FileOutputStream(file);  
			workbook.write(os);  
			os.close();
//			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 设置导出Excel单元格格式
	 * TODO:单元格属性设置没有起作用
	 */
	@SuppressWarnings({ "deprecation" })
	private static XSSFCellStyle exportExcelSetting(XSSFWorkbook wookbook) {
		XSSFCellStyle cellStyle = wookbook.createCellStyle();
		XSSFFont font = wookbook.createFont();
		// 设置单元格格式
		font.setFontName(WEBConstants.FONT_NAME_KAI);
		font.setBold(false);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		cellStyle.setWrapText(true);// 自动换行
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	public static <T> void exportSXSSFExcel(List<T> resultList, String mode,
											Class<T> clazz,XSSFParams xssfParams){
		try {
			List<CellEntity> cellEntityList = CommonUtil.getcellEntityList(mode, clazz);
			Sheet sheet = xssfParams.getWorkbook().getSheetAt(xssfParams.getSheetNow());
			for(int i=0; i<resultList.size(); i++){
				T entity = resultList.get(i);
				Row row = sheet.createRow(xssfParams.getNextNum());
				row.setHeight((short) (17*20));
				for(CellEntity cellEntity : cellEntityList){
					Cell cell = row.createCell(cellEntity.cell.Index());
					cellEntity.getField().setAccessible(true);
					Object value = cellEntity.getField().get(entity);
					if(value == null){
						value = "";
					}
					if(value instanceof Integer || value instanceof Long){
						double val = Double.parseDouble(value+"");
						cell.setCellValue(val);
					}else if(value instanceof BigDecimal){
						cell.setCellValue((((BigDecimal) value).setScale(cellEntity.cell.scale(),BigDecimal.ROUND_HALF_EVEN)).toString());
					}else if(value instanceof Date){
						cell.setCellValue(  (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format((Date)value)         );
					}else{
						MethodUtils.invokeExactMethod(cell, "setCellValue", value);
					}
				}
				xssfParams.setNextNum(xssfParams.getNextNum()+1);
				if(xssfParams.getNextNum()%1000000==0){
					xssfParams.setNextNum(2);
					xssfParams.setSheetNow(xssfParams.getSheetNow()+1);
					sheet = xssfParams.getWorkbook().getSheetAt(xssfParams.getSheetNow());
					//防止出现数组越界
					xssfParams.getWorkbook().createSheet();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
