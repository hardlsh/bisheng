package com.bisheng.controller.exhibit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisheng.apps.exhibit.business.WordBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.common.util.EnumJsonConverter;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.enums.WordOperateTypeEnum;
import com.bisheng.services.exhibit.model.customized.WordModel;
import com.bisheng.services.exhibit.model.generated.Exhibit;
import com.bisheng.services.exhibit.service.ExhibitService;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**
 * 文字存量控制类
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
    @RequestMapping("/toWordOperate")
    public ModelAndView toWordOperate() {
    	ModelAndView mav = new ModelAndView("exhibit/word/wordOperate");
    	mav.addObject("wordOperateTypeEnum", EnumJsonConverter.buildEnumJson(WordOperateTypeEnum.class));
        return mav;
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
	 * 批量修改文字存量
	 * @param param
	 * @return
	 */
	@RequestMapping("/batchUpdateWord")
	@ResponseBody
	public ALMResponse batchUpdateWord(ExhibitQueryParam param) {
		logger.info("【文字存量管理】批量修改_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		ALMResponse res;
		try{
			if (StringUtils.isBlank(param.getWordStr())) {
				res = new ALMResponse(RetCode.FAILURE);
				res.setResultMsg("请勾选要修改的文字之后,再进行操作");
				logger.error("【文字存量管理】批量修改_异常,没有勾选要操作的文字,操作人:" + LogUtil.getCurrentUserName());
				return res;
			}
			List<Long> wordIdList = new ArrayList<Long>();
			for (String wordId : param.getWordStr().split(",")) {
				wordIdList.add(Long.valueOf(wordId));
			}
			param.setWordIdList(wordIdList);
			param.setUpdateByUser(LogUtil.getCurrentUserName());
			ExhibitQueryParam.convertDate(param);// 转换参数
			wordBusiness.batchUpdateWord(param);
			res = new ALMResponse(RetCode.SUCCESS);
		} catch (Exception e) {
			res = new ALMResponse(RetCode.FAILURE);
			logger.error("【文字存量管理】批量修改_异常,操作人:" + LogUtil.getCurrentUserName(), e);
			return res;
		}
		logger.info("【文字存量管理】批量修改_结束,操作人:" + LogUtil.getCurrentUserName());
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
	
	/**
	 * 查询文字操作汇总记录
	 * @param param
	 */
	@RequestMapping("/getWordOperateList")
	public void getWordOperateList(HttpServletResponse response, ExhibitQueryParam param){
		logger.info("【文字存量管理】查询文字操作汇总_开始,操作人:" + LogUtil.getCurrentUserName() + ",入参:" + gson.toJson(param));
		try {
			ExhibitQueryParam.convertDate(param);
			if (StringUtils.isNotBlank(param.getWordStr())) {
				List<String> wordList = new ArrayList<String>();
				for (String word : param.getWordStr().split(",")) {
					wordList.add(word);
				}
				param.setWordList(wordList);
			}
			PageInfo<WordModel> pageInfo = wordBusiness.queryPagedWordOperateCount(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<WordModel>> result = PaginationResult.newInstance(pageInfo.getList());
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.info("【文字存量管理】查询文字操作汇总_结束,操作人:" + LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【文字存量管理】查询文字操作汇总_异常,操作人:" + LogUtil.getCurrentUserName(), e);
		}
	}
	
	
}
