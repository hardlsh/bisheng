package com.bisheng.controller.exhibit;

import com.bisheng.apps.exhibit.business.WordBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.DateUtil;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.bisheng.services.exhibit.service.ExhibitService;
import com.bisheng.util.ExcelManager;
import com.bisheng.util.LogUtil;
import com.bisheng.util.exportmodel.ExcelWordExportModel;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文字出入库控制类
 * 
 * @author lihao
 */
@Controller
@RequestMapping("word")
public class WordController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(WordController.class);

    @Autowired
    private WordBusiness wordBusiness;
    @Autowired
    private ExhibitService exhibitService;
    
    private Gson gson = new Gson();
    
    @RequestMapping("/toWord")
    public ModelAndView toWord() {
    	ModelAndView mav = new ModelAndView("exhibit/word/word");
        return mav;
    }

	@RequestMapping("/toWordIn")
	public ModelAndView toWordIn() {
		ModelAndView mav = new ModelAndView("exhibit/word/wordIn");
		return mav;
	}

	/**
	 * 查询文字入库信息
	 * @param response
	 * @param param
	 */
	@RequestMapping("/getWordInList")
	public void getWordInList(HttpServletResponse response, ExhibitQueryParam param) {
		logger.info("【文字存量管理】查询文字入库_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			String wordStr = param.getWordStr().replace(" ", "");
			if (wordStr.length() == 1) {
				param.setWord(param.getWordStr());
			}else if (wordStr.length() > 1) {
				param.setWordList(Arrays.asList(wordStr.split("")));
			}
			ExhibitQueryParam.convertDate(param);// 转换参数
			PageInfo<WordModel> pageInfo = wordBusiness.queryPagedWordInByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<WordModel>> result = PaginationResult.newInstance(pageInfo.getList());
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.debug("【文字存量管理】查询文字入库_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【文字存量管理】查询文字入库_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
	}
    
    /**
     * 查询文字存量信息
     * @param response
     * @param param
     */
    @RequestMapping("/getWordList")
    public void getWordList(HttpServletResponse response, ExhibitQueryParam param){
    	logger.info("【文字存量管理】查询文字存量_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
    	try {
    		String wordStr = param.getWordStr().replace(" ", "");
    		if (wordStr.length() == 1) {
    			param.setWord(param.getWordStr());
    		}else if (wordStr.length() > 1) {
    			param.setWordList(Arrays.asList(wordStr.split("")));
    		}
			ExhibitQueryParam.convertDate(param);// 转换参数
			PageInfo<WordModel> pageInfo = wordBusiness.queryPagedWordByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<WordModel>> result = PaginationResult.newInstance(pageInfo.getList());
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.debug("【文字存量管理】查询展位_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【文字存量管理】查询展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
    }

	/**
	 * 导出数据
	 */
	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response, ExhibitQueryParam param){
		logger.info("【文字存量管理】导出文字_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		OutputStream os = null;
		try {
			EXPORT_NAME = DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_文字存量明细.xlsx";
			String wordStr = param.getWordStr().replace(" ", "");
			if (wordStr.length() == 1) {
				param.setWord(param.getWordStr());
			}else if (wordStr.length() > 1) {
				param.setWordList(Arrays.asList(wordStr.split("")));
			}
			ExhibitQueryParam.convertDate(param);// 转换参数
			List<WordModel> wordModelList = wordBusiness.queryWordListByParam(param);
			processResponse(response);

			List<ExcelWordExportModel> exportWord = new ArrayList<>();
			if (null != wordModelList && !wordModelList.isEmpty()) {
				ExcelWordExportModel model;
				for (WordModel word : wordModelList) {
					model = new ExcelWordExportModel();
					model.setWord(word.getWord());
					model.setExhibitName(word.getExhibitName());
					model.setTotalCount(word.getTotalCount());
					model.setInTotalCount(word.getInTotalCount());
					model.setOutTotalCount(word.getOutTotalCount());
					model.setInDate(DateUtil.formatDateTime(word.getInDate()));
					model.setOutDate(DateUtil.formatDateTime(word.getOutDate()));
					exportWord.add(model);
				}
			}
			logger.info("【文字存量管理】导出数据长度:" + exportWord.size() + ",操作人：" + LogUtil.getCurrentUserName());
			os = new BufferedOutputStream(response.getOutputStream());
			ExcelManager.exportExcelByMould(os, 2, exportWord, "WordExportExcel", ExcelWordExportModel.class);
			os.flush();
		} catch (Exception e) {
			logger.error("【文字存量管理】导出数据_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		} finally {
			IOUtils.closeQuietly(os);// 关闭流
		}
	}
    
    /**
     * 查询展馆名称
     * @param param
     */
	@RequestMapping("/getExhibitName")
	@ResponseBody
	public ALMResponse getExhibitName(ExhibitQueryParam param) {
		logger.info("【文字存量管理】查询展馆名称_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		Exhibit exhibit = exhibitService.queryExhibitById(param.getExhibitId());
		ALMResponse res = new ALMResponse(RetCode.SUCCESS);
		res.setData(exhibit);
		logger.info("【文字存量管理】查询展馆名称_结束,操作人:" + LogUtil.getCurrentUserName());
		return res;
	}
    
	/**
	 * 文字批量入库
	 * @param param
	 * @return
	 */
	@RequestMapping("/batchInWord")
	@ResponseBody
	public ALMResponse batchInWord(ExhibitQueryParam param) {
		logger.info("【文字入库管理】批量入库_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		ALMResponse res;
		try{
			if (StringUtils.isBlank(param.getWordStr())) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("请勾选要入库的文字之后,再进行操作");
				logger.error("【文字入库管理】批量入库_异常,没有勾选要操作的文字,操作人:" + LogUtil.getCurrentUserName());
				return res;
			}
			List<Long> wordIdList = new ArrayList<Long>();
			for (String wordId : param.getWordStr().split(",")) {
				wordIdList.add(Long.valueOf(wordId));
			}
			param.setWordIdList(wordIdList);
			param.setUpdateByUser(LogUtil.getCurrentUserName());
			ExhibitQueryParam.convertDate(param);// 转换参数
			wordBusiness.newWordIn(param);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【文字入库管理】批量入库_异常,操作人:" + LogUtil.getCurrentUserName(), e);
			return res;
		}
		logger.info("【文字入库管理】批量入库_结束,操作人:" + LogUtil.getCurrentUserName());
		return res;
	}
	
	/**
	 * 新建文字存量
	 * @param param
	 * @return
	 */
	@RequestMapping("/batchCreateWord")
	@ResponseBody
	public ALMResponse batchCreateWord(ExhibitQueryParam param) {
		logger.info("【文字存量管理】批量新建_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		ALMResponse res;
		try{
			if (StringUtils.isBlank(param.getWordStr())) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("请输入要新创建的文字,再进行操作");
				logger.error("【文字存量管理】批量新建_异常,输入要新创建的文字,操作人:" + LogUtil.getCurrentUserName());
				return res;
			}
			List<String> wordList = new ArrayList<String>();
			for (String word : param.getWordStr().split(",")) {
				wordList.add(word);
			}
			param.setWordList(wordList);
			param.setUpdateByUser(LogUtil.getCurrentUserName());
			wordBusiness.batchCreateWord(param);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【文字存量管理】批量新建_异常,操作人:" + LogUtil.getCurrentUserName(), e);
			return res;
		}
		logger.info("【文字存量管理】批量修改_结束,操作人:" + LogUtil.getCurrentUserName());
		return res;
	}


}
