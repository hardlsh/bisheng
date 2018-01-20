package com.bisheng.util.exportmodel;

import java.io.Serializable;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;

/**
 * 展位导出model 
 * 	对应BoothExportExcel
 * @author lihao
 */
@Report
public class ExcelBoothExportModel implements Serializable{
	
	private static final long serialVersionUID = 1751628826401248726L;
	
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 0)
	})
	private Long boothId;// 展位ID
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 1)
	})
    private String boothName;// 展位名称
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 2)
	})
	private String exhibitName;// 展馆名称
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 3)
	})
	private Integer sequence;// 展位编号
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 4)
	})
	private Integer xCount;// x轴字数
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 5)
	})
	private Integer yCount;// y轴字数
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 6)
	})
    private String status;// 展位状态
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 7)
	})
	private String wordSign;// 导入标识
	@ReportField(Cells = {
			@Cell(Mode = "BoothExportExcel", Index = 8)
	})
	private String wordContent;// 模板导入内容
	
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public String getExhibitName() {
		return exhibitName;
	}
	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getxCount() {
		return xCount;
	}
	public void setxCount(Integer xCount) {
		this.xCount = xCount;
	}
	public Integer getyCount() {
		return yCount;
	}
	public void setyCount(Integer yCount) {
		this.yCount = yCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWordSign() {
		return wordSign;
	}
	public void setWordSign(String wordSign) {
		this.wordSign = wordSign;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	
}
