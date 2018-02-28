package com.bisheng.util;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by niuhonglei on 2017/6/14.
 */
public class XSSFParams implements Serializable {
    public XSSFParams(String fileName){
        InputStream is = ExcelManager.class.getClassLoader().getResourceAsStream(fileName);
        try {
            workbook = new SXSSFWorkbook(new XSSFWorkbook(is),1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Integer nextNum = 2;
    private Integer sheetNow = 0;
    private String fileName;
    private SXSSFWorkbook workbook;

    public Integer getNextNum() {
        return nextNum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SXSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(SXSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void setNextNum(Integer nextNum) {
        this.nextNum = nextNum;
    }

    public Integer getSheetNow() {
        return sheetNow;
    }

    public void setSheetNow(Integer sheetNow) {
        this.sheetNow = sheetNow;
    }
}
