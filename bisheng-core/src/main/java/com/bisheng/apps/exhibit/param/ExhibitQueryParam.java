package com.bisheng.apps.exhibit.param;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bisheng.apps.BaseQuery;
import com.bisheng.core.common.util.DateUtil;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;

/**
 * 展馆查询参数类
 * @author hp
 *
 */
@SuppressWarnings("serial")
public class ExhibitQueryParam extends BaseQuery {
	// 展馆查询使用
    private Long exhibitId;// 展馆ID
    private Long[] exhibitIdArr;
    private List<Long> exhibitIdList;
    private String exhibitName;// 展馆名称
    private String exhibitCode;// 展馆名称简拼,例如:SXWHG(绍兴文化馆)
    private String areaId;// 地区ID
    private String areaName;// 地区名称
    private String address;// 展馆地址
    private String phone;// 联系方式
    private Integer exhibitStatus;// 展馆开放状态(1:开业,2:休息,3:停业)
    private List<Integer> exhibitStatusList;
    private String startTime;// 展览开始时间
    private String endTime;// 展览结束时间
    private String remark;// 备注
    private String createByUser;// 创建用户
    private String updateByUser;// 修改用户
    
    // 数据权限使用
    private Long userId;// 当前用户ID
    
    // 展位查询使用
    private Long boothId;// 展位Id
    private List<Long> boothIdList;
    private String boothName;// 展位名称
    private Integer sequence;// 展位编号
    private Integer xCount;// x轴字数
    private Integer yCount;// y轴字数
    private Integer boothStatus;// 展位状态(1:正常,2:维护,3:关闭)
    private Integer wordSign;// 导入标识
    private String wordContent;// 导入内容
    private Long templateCount;// 模板入库数量
    
    // 展位文字
    private String word;// 文字
    private List<BoothWordModel> boothWordList;// 展位文字Model
    
    // 地区
    private String city;// 城市(对应地区名称)
    private Integer areaLevel;// 地区级别
    
    // 文字存量
    private String wordStr;// 文字传参
    private List<String> wordList;// 文字集合
    private List<Long> wordIdList;// 文字id集合
    private Integer operateType;// 操作类型
    private Long count;// 操作数量
	private Long totalCountMin;// 最小库存
	private Long totalCountMax;// 最大库存
	private String updateDateStr;// 修改时间
    private String updateDateStrMin;// 最小修改时间
    private String updateDateStrMax;// 最大修改时间
	private Date updateDate;// 修改时间
    private Date updateDateMin;// 最小修改时间
    private Date updateDateMax;// 最大修改时间
    
    // 日期转换
    public static void convertDate(ExhibitQueryParam param) throws ParseException{
    	if (StringUtils.isNotBlank(param.getUpdateDateStr())) {
    		param.setUpdateDate(DateUtil.parseDateTime(param.getUpdateDateStr() + " " + DateUtil.getCurrentHour()));
		}
    	if (StringUtils.isNotBlank(param.getUpdateDateStrMin())) {
        	param.setUpdateDateMin(DateUtil.parseDateTime(param.getUpdateDateStrMin() + " 00:00:00"));
    	}
    	if (StringUtils.isNotBlank(param.getUpdateDateStrMax())) {
        	param.setUpdateDateMax(DateUtil.parseDateTime(param.getUpdateDateStrMax() + " 23:59:59"));
    	}
    }

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
	public String getAddress() {
		return address;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public Integer getExhibitStatus() {
		return exhibitStatus;
	}
	public void setExhibitStatus(Integer exhibitStatus) {
		this.exhibitStatus = exhibitStatus;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateByUser() {
		return createByUser;
	}
	public void setCreateByUser(String createByUser) {
		this.createByUser = createByUser;
	}
	public String getUpdateByUser() {
		return updateByUser;
	}
	public void setUpdateByUser(String updateByUser) {
		this.updateByUser = updateByUser;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
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
	public Integer getBoothStatus() {
		return boothStatus;
	}
	public void setBoothStatus(Integer boothStatus) {
		this.boothStatus = boothStatus;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Integer getWordSign() {
		return wordSign;
	}
	public void setWordSign(Integer wordSign) {
		this.wordSign = wordSign;
	}
	public List<BoothWordModel> getBoothWordList() {
		return boothWordList;
	}
	public void setBoothWordList(List<BoothWordModel> boothWordList) {
		this.boothWordList = boothWordList;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	public List<Integer> getExhibitStatusList() {
		return exhibitStatusList;
	}
	public void setExhibitStatusList(List<Integer> exhibitStatusList) {
		this.exhibitStatusList = exhibitStatusList;
	}
	public Long[] getExhibitIdArr() {
		return exhibitIdArr;
	}
	public void setExhibitIdArr(Long[] exhibitIdArr) {
		this.exhibitIdArr = exhibitIdArr;
	}
	public List<Long> getExhibitIdList() {
		return exhibitIdList;
	}
	public void setExhibitIdList(List<Long> exhibitIdList) {
		this.exhibitIdList = exhibitIdList;
	}
	public List<Long> getBoothIdList() {
		return boothIdList;
	}
	public void setBoothIdList(List<Long> boothIdList) {
		this.boothIdList = boothIdList;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	public Long getTemplateCount() {
		return templateCount;
	}
	public void setTemplateCount(Long templateCount) {
		this.templateCount = templateCount;
	}
	public List<String> getWordList() {
		return wordList;
	}
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
	public String getWordStr() {
		return wordStr;
	}
	public void setWordStr(String wordStr) {
		this.wordStr = wordStr;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<Long> getWordIdList() {
		return wordIdList;
	}
	public void setWordIdList(List<Long> wordIdList) {
		this.wordIdList = wordIdList;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public String getUpdateDateStrMin() {
		return updateDateStrMin;
	}
	public void setUpdateDateStrMin(String updateDateStrMin) {
		this.updateDateStrMin = updateDateStrMin;
	}
	public String getUpdateDateStrMax() {
		return updateDateStrMax;
	}
	public void setUpdateDateStrMax(String updateDateStrMax) {
		this.updateDateStrMax = updateDateStrMax;
	}
	public Date getUpdateDateMin() {
		return updateDateMin;
	}
	public void setUpdateDateMin(Date updateDateMin) {
		this.updateDateMin = updateDateMin;
	}
	public Date getUpdateDateMax() {
		return updateDateMax;
	}
	public void setUpdateDateMax(Date updateDateMax) {
		this.updateDateMax = updateDateMax;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getTotalCountMin() {
		return totalCountMin;
	}

	public void setTotalCountMin(Long totalCountMin) {
		this.totalCountMin = totalCountMin;
	}

	public Long getTotalCountMax() {
		return totalCountMax;
	}

	public void setTotalCountMax(Long totalCountMax) {
		this.totalCountMax = totalCountMax;
	}
}
