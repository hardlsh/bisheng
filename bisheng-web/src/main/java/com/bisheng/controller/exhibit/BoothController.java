package com.bisheng.controller.exhibit;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bisheng.apps.exhibit.business.BoothBusiness;
import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.DateUtil;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.core.framework.exception.BusinessException;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.enums.BoothStatusEnum;
import com.bisheng.services.exhibit.enums.BoothWordSignEnum;
import com.bisheng.services.exhibit.model.customized.BoothModel;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.exhibit.model.generated.Booth;
import com.bisheng.services.exhibit.service.BoothService;
import com.bisheng.util.ExcelManager;
import com.bisheng.util.ExportExcelUtil;
import com.bisheng.util.LogUtil;
import com.bisheng.util.QRCode.QRCodeManager;
import com.bisheng.util.constant.QRConstants;
import com.bisheng.util.constant.WEBConstants;
import com.bisheng.util.exportModel.ExcelBoothExportModel;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**
 * 展位控制类
 * @author lihao
 *
 */
@Controller
@RequestMapping("booth")
public class BoothController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BoothController.class);

    @Autowired
    private BoothBusiness boothBusiness;
    @Autowired
    private BoothService boothService;
    @Autowired
    private ExhibitBusiness exhibitBusiness;
    
    private ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
    
    private Gson gson = new Gson();
    
    @RequestMapping("/toBooth")
    public ModelAndView toBooth() {
    	ModelAndView mav = new ModelAndView("exhibit/booth/booth");
    	mav.addObject("boothStatusEnum",EnumJsonConverter.buildEnumJson(BoothStatusEnum.class));
    	mav.addObject("boothWordSignEnum",EnumJsonConverter.buildEnumJson(BoothWordSignEnum.class));
        return mav;
    }
    @RequestMapping("/toAddBooth")
    public ModelAndView toAddBooth() {
    	ModelAndView mav = new ModelAndView("exhibit/booth/addBooth");
    	mav.addObject("boothStatusEnum",EnumJsonConverter.buildEnumJson(BoothStatusEnum.class));
        return mav;
    }
    @RequestMapping("/toUpdateBooth")
    public ModelAndView toUpdateBooth(String boothId) {
    	ModelAndView mav = new ModelAndView("exhibit/booth/updateBooth");
    	ExhibitQueryParam param = new ExhibitQueryParam();
    	param.setBoothId(Long.valueOf(boothId));
    	param.setUserId(getCurrentUserId());
    	List<BoothModel> boothList = boothBusiness.queryBoothListByParam(param);
    	mav.addObject("booth", boothList.get(0));
        return mav;
    }
    
    /**
     * 获取展位列表,只能看到有对应权限展馆下的展位
     * 包含所有状态
     */
    @RequestMapping("/getBoothList")
    public void getBoothList(HttpServletResponse response, ExhibitQueryParam param) {
    	logger.info("【展位管理】查询展位_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			convertParam(param);
			PageInfo<BoothModel> pageInfo = boothBusiness.queryPagedBoothByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<BoothModel>> result = PaginationResult.newInstance(pageInfo.getList());
			logger.debug("【展位管理】获取展馆列表长度为:" + total);
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.debug("【展位管理】查询展位_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【展位管理】查询展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
    }
    
    /**
	 * 新增展位 保存
	 */
	@RequestMapping("/addSaveBooth")
	@ResponseBody
	public Object addSaveBooth(ExhibitQueryParam param) {
		logger.info("【展位管理】新增展位_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			if (null == param.getExhibitId()) {
				logger.error("【展位管理】新增展位_异常,操作人:"+LogUtil.getCurrentUserName()+",展馆信息不能为空");
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("展馆信息不能为空");
				return res;
			}
			param.setBoothStatus(BoothStatusEnum.MAINTAIN.getKey());//默认状态
			param.setCreateByUser(getCurrentUserName());
			param.setUpdateByUser(getCurrentUserName());
			boothBusiness.addBooth(param);
			logger.info("【展位管理】新增展位,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (BusinessException be) {
			logger.error("【展位管理】新增展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", be);
			res = new ALMResponse(RetCode.FAILURE);
			res.setResultMsg(be.getMessage());
		} catch (Exception e) {
			logger.error("【展位管理】新增展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("【展位管理】新增展位_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
	/**
	 * 修改展位 保存
	 */
	@RequestMapping("/updateSaveBooth")
	@ResponseBody
	public Object updateSaveBooth(ExhibitQueryParam param) {
		logger.info("【展位管理】修改展位_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			boothBusiness.updateBooth(param);
			logger.info("【展位管理】修改展位,成功");
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			logger.error("【展位管理】修改展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
			res = new ALMResponse(RetCode.FAILURE);
		}
		logger.info("【展位管理】修改展位_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	
	/**
	 * 生成二维码
	 */
	@RequestMapping(value="/toCreateCode",method = { RequestMethod.POST, RequestMethod.GET })  
	@ResponseBody
	public Object toCreateCode(ExhibitQueryParam param, HttpServletRequest request, HttpServletResponse response) {
		logger.info("【展位管理】生成二维码_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			String contents = getContents(param);
			String imageBase64QRCode = QRCodeManager.createLogoQRCode(request,contents,contents);
			res = new ALMResponse(RetCode.SUCCESS);
			res.setData(imageBase64QRCode);
		} catch (Exception e) {
			logger.error("生成二维码异常:" + e.getMessage(), e);
			res = new ALMResponse(RetCode.FAILURE);
		} 
		return res;
	}
	private String getContents(ExhibitQueryParam param) {
		List<ExhibitModel> exhibitList = exhibitBusiness.queryAllExhibitByParam(param);
		if (null == exhibitList || exhibitList.size() !=1) {
			logger.info("【展位管理】生成二维码_查询展馆异常");
		}
		ExhibitModel exhibitModel = exhibitList.get(0);
		String areaId = exhibitModel.getAreaId();
		String exhibitId = exhibitModel.getExhibitId().toString();
		String boothId = param.getBoothId().toString();
		int differ = QRConstants.EXHIBIT_ID_LENGTH -  exhibitId.length();
		if (differ > 0) {
			for (int i=0; i<differ; i++) {
				exhibitId = "0" + exhibitId;
			}
		}
		differ = QRConstants.BOOTH_ID_LENGTH -  boothId.length();
		if (differ > 0) {
			for (int i=0; i<differ; i++) {
				boothId = "0" + boothId;
			}
		}
		return areaId + exhibitId + boothId;
	}
	
	/**
	 * 根据用户id 和展馆,关联查询用户拥有权限的展位
	 * 用于展位标签
	 */
	@RequestMapping("/getAuthBoothForTag")
	@ResponseBody
	public Object getAuthBoothForTag(ExhibitQueryParam param) {
		logger.info("【展位管理】查询有权限的展位_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try {
			param.setUserId(LogUtil.getCurrentUserId());
			param.setBoothStatus(BoothStatusEnum.NORMAL.getKey());
			List<BoothModel> boothList = boothBusiness.queryBoothListByParam(param);
			if (boothList != null && !boothList.isEmpty()) {
				res = new ALMResponse(RetCode.SUCCESS);
				res.setData(boothList);
			} else {
				res = new ALMResponse(RetCode.FAILURE);
			}
		} catch (Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【展位管理】查询有权限的展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
		logger.debug("【展位管理】查询有权限的展位_结束,操作人:"+LogUtil.getCurrentUserName());
		return res;
	}
	/**
	 * 导出数据
	 */
	@RequestMapping("/exportBooth")
	public void exportBooth(HttpServletRequest request, HttpServletResponse response, ExhibitQueryParam param){
		logger.info("【展位管理】导出数据_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		OutputStream os = null;
		try {
			EXPORT_NAME = DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_展位明细.xlsx";
			param.setUserId(LogUtil.getCurrentUserId());
			List<BoothModel> boothModelList = boothBusiness.queryBoothListByParam(param);
			processResponse(response);
			List<ExcelBoothExportModel> exportBooth = new ArrayList<ExcelBoothExportModel>();
			if (null != boothModelList && !boothModelList.isEmpty()) {
				ExcelBoothExportModel model;
				for (BoothModel booth : boothModelList) {
					model = new ExcelBoothExportModel();
					model.setBoothId(booth.getBoothId());
					model.setBoothName(booth.getBoothName());
					model.setExhibitName(booth.getExhibitName());
					model.setSequence(booth.getSequence());
					model.setxCount(booth.getxCount());
					model.setyCount(booth.getyCount());
					model.setStatus(BoothStatusEnum.getDescName(booth.getStatus()));
					model.setWordSign(BoothWordSignEnum.getDescName(booth.getWordSign()));
					model.setWordContent(booth.getWordContent());
					exportBooth.add(model);
				}
			}
			logger.info("【展位管理】导出数据长度:"+exportBooth.size()+",操作人："+LogUtil.getCurrentUserName());
			os = new BufferedOutputStream(response.getOutputStream());
			ExcelManager.exportExcelByMould(os, 2, exportBooth, "BoothExportExcel", ExcelBoothExportModel.class);
			os.flush();
		} catch (Exception e) {
			logger.error("【展位管理】导出数据_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		} finally {
			IOUtils.closeQuietly(os);// 关闭流
		}
	}
	
	/**
	 * 查看导入内容
	 */
	@RequestMapping("/queryWordContent")
	@ResponseBody
	public Object queryWordContent(ExhibitQueryParam param) {
		logger.info("【展位管理】查看导入内容_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		ALMResponse res = null;
		try{
			if (param.getBoothId() == null) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("页面传参有误！");
				return res;
			}
			Booth booth = boothService.queryBoothById(param.getBoothId());
			String[] wordContentArr = booth.getWordContent().split(",");
			String[] lineArr = null;
			StringBuilder show = new StringBuilder("<table border='1'>");
			// 第一行
			show.append("<tr> <td></td>");
			for (int j = WEBConstants.ROW_START_NUM; j < booth.getyCount() + WEBConstants.ROW_START_NUM; j++) {
				show.append("<td align='center' width=25 height=25 >");
				show.append(j);
				show.append("</td>");
			}
			show.append("</tr>");
			for (int i=0; i<booth.getxCount(); i++) {
				lineArr = wordContentArr[i].split("");
				show.append("<tr> <td align='center' width=25 height=25 >" + (i+1) + "</td>");
				for (int j=0; j< lineArr.length; j++) {
					show.append("<td align='center' width=25 height=25 >");
					if (!WEBConstants.REPLACE_SIGN.equals(lineArr[j])) {
						show.append(lineArr[j]);
					}
					show.append("</td>");
				}
				show.append("</tr>");
			}
			show.append("</table>");
			res = new ALMResponse(RetCode.SUCCESS);
			res.setData(show.toString());
		}catch(Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【展位管理】查看导入内容异常:"+e.getMessage(), e);
		}
		return res;
	}
	
	/**
	 * 下载模板
	 */
	@RequestMapping(value = "/downloadTemplet")
	public void downloadTemplet(HttpServletResponse response, ExhibitQueryParam param){
		logger.info("【展位管理】下载模板_开始,入参:" + gson.toJson(param));
		try{
			String fileName = param.getBoothName() + param.getxCount() + WEBConstants.EXCEL_SPACER + param.getyCount()
					+ WEBConstants.EXCEL_SUFFIX;
			XSSFWorkbook workbook = exportExcelUtil.createSheet(param.getBoothName());
			
			excelSettings(param, workbook);
			
			//导出excel
			exportExcelUtil.exportExcel(response, fileName);
		} catch(Exception e){
			logger.error("【展位管理】下载模板_异常:" + e.getMessage(), e);
		}
	}
	/**
	 * 设置Excel样式
	 */
	@SuppressWarnings("deprecation")
	private void excelSettings(ExhibitQueryParam param, XSSFWorkbook workbook) throws Exception {
		// 设置表头
		Map<Integer, String> h1Map = new HashMap<Integer, String>();
		h1Map.put(0, "");
		for (int i=1; i<=param.getyCount(); i++) {
			h1Map.put(i, i+"");
		}
		exportExcelUtil.addHeader(0, h1Map);
		
		// 填充数据
		Map<Integer, String> rowMap = new HashMap<Integer, String>();
		rowMap.put(0, "");
		for (int i=1; i<=param.getxCount(); i++) {
			rowMap.put(i, i+"");
		}
		exportExcelUtil.fillRowTitle(WEBConstants.ROW_START_NUM, rowMap);
		
		// 设置列宽
		Map<Integer, Integer> columnMap = new HashMap<Integer, Integer>();
		for (int i=0; i<=param.getyCount(); i++) {
			columnMap.put(i, WEBConstants.COLUMN_WIDTH);
		}
		exportExcelUtil.setColumnWidth(columnMap);
		
		// 设置单元格格式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(WEBConstants.FONT_HEIGHT);
		font.setFontName(WEBConstants.FONT_NAME_KAI);
		font.setBold(false);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框   
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框   
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框   
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框   
		cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());// 背景颜色
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		int[] dataCell = { 0, param.getxCount()+1, 0, param.getyCount()+1 };//设置填充单元格格式的单元格位置
		exportExcelUtil.setCellStyle(dataCell, cellStyle);
	}
	/**
	 * 导入文字模板
	 *
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/importTempletNew")
	@ResponseBody
	public Object importTempletNew(@RequestParam(value = "multipartFile", required = false) MultipartFile file,HttpServletRequest request, 
			HttpServletResponse response, BoothModel boothModel) {
		response.setContentType("text/html;charset=UTF-8");
		ALMResponse res = null;
		InputStream inputStream = null;
		String fileName = file.getOriginalFilename();
		int endIndex = fileName.indexOf(".");
		fileName = fileName.substring(0, endIndex);
		logger.info("【展位管理】导入文字模板_开始,文件名:["+fileName+"],操作人:"+ LogUtil.getCurrentUserName());
		try {
			inputStream = file.getInputStream();
			String message = importTempletHandle(boothModel, inputStream, fileName);
			if (StringUtils.isNotBlank(message)) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg(message);
			} else {
				res = new ALMResponse(RetCode.SUCCESS);
			}
		} catch (Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【导入文字模板】失败，操作人：" + LogUtil.getCurrentUserName(), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return res;
	}
	/**
	 * 导入文字模板处理
	 * 模板中的空格，用英文.表示，判断和入库的时候，需要注意
	 */
	@SuppressWarnings("resource")
	private String importTempletHandle(BoothModel booth, InputStream inputStream, String fileName) throws Exception{
		String message = null;
		//解析excel
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int lastRowNum = sheet.getLastRowNum()+1;
		if ((WEBConstants.ROW_START_NUM + booth.getxCount()) == lastRowNum) {
			Row row = null;
			List<String> lineList = new ArrayList<String>();
			StringBuilder line = null;
			for (int r = WEBConstants.ROW_START_NUM; r < WEBConstants.ROW_START_NUM + booth.getxCount(); r++) {
				row = sheet.getRow(r);
				String word = null;
				line = new StringBuilder();
				for (int c = WEBConstants.ROW_START_NUM; c < WEBConstants.ROW_START_NUM + booth.getyCount(); c++) {
					logger.info("第" + r + "行,第" + c + "列");
					word = ExcelManager.getCellValue(row.getCell(c));
					if (StringUtils.isBlank(word)) {
						word = WEBConstants.REPLACE_SIGN;
					}
					line.append(word);
				}
				lineList.add(line.toString());
			}
			
			if (null == lineList || lineList.isEmpty()) {
				message = "导入模板有问题,没有读取到内容";
				logger.error(message);
				return message;
			}
			String checkResult = checkHandle(lineList);
			if (StringUtils.isNotBlank(checkResult)) {
				message = "导入模板有问题,有重复字 '" + checkResult + "' 出现";
				logger.error(message);
				return message;
			}
			ExhibitQueryParam queryParam = paramHandle(booth, lineList, fileName);
			queryParam.setUpdateByUser(LogUtil.getCurrentUserName());
			// 保存数据
			boothBusiness.batchInsertBoothWord(queryParam);
		} else {
			message = "模板指定范围外有其他数据";
			logger.error(message);
		}
		return message;
	}
	/**
	 * 导入文字校验
	 * 不能用重复字出现
	 * 排除空格替代符
	 */
	private String checkHandle(List<String> lineList){
		Set<String> set = new HashSet<String>();
		String oneLine;
		String[] oneLineArr = null;
		for (int i = 0; i < lineList.size(); i++) {
			oneLine = lineList.get(i).replace(WEBConstants.REPLACE_SIGN, "");
			oneLineArr = oneLine.split("");
			for (int j = 0; j < oneLineArr.length; j++) {
				if (set.contains(oneLineArr[j])) {
					return oneLineArr[j];
				}
				set.add(oneLineArr[j]);
			}
		}
		return null;
	}
	/**
	 * 展位文字参数处理
	 */
	private ExhibitQueryParam paramHandle(BoothModel booth, List<String> lineList, String fileName) {
		ExhibitQueryParam queryParam = new ExhibitQueryParam();
		MBeanUtil.copyProperties(booth, queryParam);
		List<BoothWordModel> boothWordList = new ArrayList<>();
		BoothWordModel boothWordModel = null;
		String[] oneLineArr = null;
		StringBuilder content = new StringBuilder();
		for (int i=0; i<lineList.size(); i++) {
			oneLineArr = lineList.get(i).split("");
			for (int j=0; j<oneLineArr.length; j++) {
				if (WEBConstants.REPLACE_SIGN.equals(oneLineArr[j])) {
					continue;
				}
				boothWordModel = new BoothWordModel();
				boothWordModel.setBoothId(booth.getBoothId());
				boothWordModel.setBoothName(booth.getBoothName());
				boothWordModel.setTempletName(fileName);
				boothWordModel.setxAxis(i+1);
				boothWordModel.setyAxis(j+1);
				boothWordModel.setNumber((i + 1) * (j + 1));
				boothWordModel.setWord(oneLineArr[j]);
				boothWordList.add(boothWordModel);
			}
			content.append(lineList.get(i)+",");
		}
		queryParam.setWordContent(content.toString());
		queryParam.setBoothWordList(boothWordList);
		return queryParam;
	}
}
