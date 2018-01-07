<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%	
	String fileName = (String)request.getAttribute("excelFileName");
	HSSFWorkbook wb = (HSSFWorkbook)request.getAttribute("excelWorkBook");
	fileName = new String(fileName.getBytes(), "ISO_8859_1"); //文件名转码 
	// 保存文件
	response.setContentType("application/x-download");
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	response.setHeader("Content-Disposition","attachment;filename="+fileName);
	response.setHeader("Pragma","public");
	response.setHeader("Cache-Control","max-age=0");
	ServletOutputStream output =null;
	output = response.getOutputStream();
	wb.write(output);
	output.flush();
	out.clear(); 
    out = pageContext.pushBody();
%>