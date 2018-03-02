package com.bisheng.util.exportmodel;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;

import java.io.Serializable;

/**
 * 文字出库导出model
 * 对应WordOutExportExcel
 * @author lihao
 */
@Report
public class ExcelWordOutExportModel implements Serializable {
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
    private Long outNumber;// 出库数量
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 5)
    })
    private String outDate;// 最近入库时间

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

    public Long getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Long outNumber) {
        this.outNumber = outNumber;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
