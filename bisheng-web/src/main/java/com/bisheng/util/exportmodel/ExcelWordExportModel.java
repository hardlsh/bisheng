package com.bisheng.util.exportmodel;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;

import java.io.Serializable;

/**
 * 展馆导出model
 * 对应WordExportExcel
 * @author lihao
 */
@Report
public class ExcelWordExportModel implements Serializable {
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
    private Long totalCount;// 现有库存
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 3)
    })
    private Long inTotalCount;// 入库数量
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 4)
    })
    private Long outTotalCount;// 出库数量
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 5)
    })
    private String inDate;// 最近入库时间
    @ReportField(Cells = {
            @Cell(Mode = "WordExportExcel", Index = 6)
    })
    private String outDate;// 最近出库时间

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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getInTotalCount() {
        return inTotalCount;
    }

    public void setInTotalCount(Long inTotalCount) {
        this.inTotalCount = inTotalCount;
    }

    public Long getOutTotalCount() {
        return outTotalCount;
    }

    public void setOutTotalCount(Long outTotalCount) {
        this.outTotalCount = outTotalCount;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
