package com.bisheng.util.exportmodel;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;

import java.io.Serializable;
import java.util.Date;

/**
 * 文字入库导出model
 * 对应WordInExportExcel
 * @author lihao
 */
@Report
public class ExcelWordInExportModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 0)
    })
    private String word;// 文字
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 1)
    })
    private String exhibitName;// 所属展馆
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 2)
    })
    private String boothName;// 所属展位
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 3)
    })
    private Long totalCount;// 现有库存
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 4)
    })
    private Long inNumber;// 入库数量
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 5)
    })
    private Date inDate;// 最近入库时间

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getInNumber() {
        return inNumber;
    }

    public void setInNumber(Long inNumber) {
        this.inNumber = inNumber;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
}
