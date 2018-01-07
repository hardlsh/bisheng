package com.bisheng.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 导出工具类
 * 
 * @author lihao
 */
public class ExportExcelUtil {
	protected static Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);
	
	ThreadLocal<XSSFWorkbook> wb = new ThreadLocal<XSSFWorkbook>();
	ThreadLocal<Sheet> sheet = new ThreadLocal<Sheet>();
	
	/**
	 * 为excel文件创建工作表
	 */
	public XSSFWorkbook createSheet(String sheetName){
		XSSFWorkbook tempWb = new XSSFWorkbook();
		Sheet tempSheet = tempWb.createSheet(sheetName);
		this.sheet.set(tempSheet);
		this.wb.set(tempWb);
		return tempWb;
	}
	
	/**
	 * 设置excel表格的标题，可以多次调用，然后按照行号设置每行对应的标题
	 * 多次调用可以设置复杂的标题，headerMap中key为列号、value为列头的文字
	 * @param rowNum 行号（标题的行号）
	 * @param headerMap 标题文字信息（Map[列号：列头文字]）
	 * @Description
	 */
	public void addHeader(int rowNum, Map<Integer, String> headerMap){
		if (null == headerMap || headerMap.isEmpty())
			return;
		rowNum = (rowNum < 0) ? 0 : rowNum;

		Row row = sheet.get().createRow(rowNum);
		Set<Integer> cols = headerMap.keySet();
		Iterator<Integer> ci = cols.iterator();
		while (ci.hasNext()) {
			int cn = ci.next();
			Cell cell = row.createCell(cn);
			cell.setCellValue(headerMap.get(cn));
		}
	}
	
	/**
	 * 填充行标题
	 */
	public void fillRowTitle(int rowStart, Map<Integer, String> rowMap) {
		if (null == rowMap || rowMap.isEmpty())
			return;
		rowStart = (rowStart < 0) ? 0 : rowStart;
		Row row = null;
		for (int i = rowStart; i <= rowMap.size()+rowStart; i++) {
			row = sheet.get().createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(rowMap.get(i));
		}
	}
	
	/**
	 * 设置excel表格的列宽
	 */
	public void setColumnWidth(Map<Integer, Integer> columnMap){
		for (Integer column : columnMap.keySet()){
			sheet.get().setColumnWidth(column, columnMap.get(column));
		}
	}
	
	/**
	 * 设置单元格格式
	 * @param cellSelect array[rowStart：起始行, rowLast：结束行, clumnStart：起始列, clumnLast：结束列 ]
	 * @param cellStyle 单元格格式
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public void setCellStyle(int[] cellSelect, XSSFCellStyle cellStyle) throws Exception {
		if (cellSelect.length != 4)
			throw new Exception("参数cellSelect数组只能设置4个数据且必须遵循格式：array[rowStart：起始行, rowLast：结束行, clumnStart：起始列, clumnLast：结束列 ]");

		//rs = row start,  rl = row last,  cs = column start,  cl= column last
		int rs = cellSelect[0], rl = cellSelect[1], cs = cellSelect[2], cl = cellSelect[3];
		Row row = null;
		for (int r = rs; r < rl; r++) {
			row = sheet.get().getRow(r);
			for (int c = cs; c < cl; c++) {
				row.getCell(c, row.CREATE_NULL_AS_BLANK).setCellStyle(cellStyle);
			}
		}
	}
	
	/**
	 * 导出excel表格
	 */
	public void exportExcel(HttpServletResponse rsp, String fileName) {
		OutputStream os = null;

		try {
			logger.info(new Date() + "，导出(For collect the user's export habit )：" + fileName);
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			rsp.setContentType("application/vnd.ms-excel;charset=UTF-8");
			rsp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			os = rsp.getOutputStream();
			wb.get().write(os);
		} catch (Exception e) {
			logger.error("导出"+fileName+"Excel表格异常:"+e.getMessage(), e);
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
					wb.get().close();
				} catch (Exception e1) {
				}
			}
			// 清空线程
			this.wb.remove();
			this.sheet.remove();
		}
	}
	
}
