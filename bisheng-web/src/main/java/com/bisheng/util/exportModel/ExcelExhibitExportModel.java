package com.bisheng.util.exportModel;

import java.io.Serializable;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;

/**
 * 展馆导出model 
 * 	对应ExhibitExportExcel
 * @author lihao
 */
@Report
public class ExcelExhibitExportModel implements Serializable{

	private static final long serialVersionUID = 10333621465017574L;
	
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 0)
	})
	private Long exhibitId;// 展馆ID
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 1)
	})
    private String exhibitName;// 展馆名称
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 2)
	})
    private String exhibitCode;// 展馆名称简拼
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 3)
	})
    private String areaName;// 地区名称
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 4)
	})
    private String address;// 展馆地址
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 5)
	})
    private String phone;// 联系方式
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 6)
	})
    private String status;// 展馆开放状态
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 7)
	})
    private String startTime;// 展览开始时间
	@ReportField(Cells = {
			@Cell(Mode = "ExhibitExportExcel", Index = 8)
	})
    private String endTime;// 展览结束时间
	
	public Long getExhibitId() {
		return exhibitId;
	}
	public void setExhibitId(Long exhibitId) {
		this.exhibitId = exhibitId;
	}
	public String getExhibitName() {
		return exhibitName;
	}
	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}
	public String getExhibitCode() {
		return exhibitCode;
	}
	public void setExhibitCode(String exhibitCode) {
		this.exhibitCode = exhibitCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
