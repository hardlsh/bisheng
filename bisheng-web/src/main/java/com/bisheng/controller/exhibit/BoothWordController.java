package com.bisheng.controller.exhibit;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bisheng.apps.exhibit.business.BoothBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.controller.BaseController;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.exhibit.model.customized.BoothWordModel;
import com.bisheng.util.LogUtil;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**
 * 展位文字控制类
 * 
 * @author lihao
 */
@Controller
@RequestMapping("boothWord")
public class BoothWordController extends BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BoothWordController.class);

    @Autowired
    private BoothBusiness boothBusiness;
    
    private Gson gson = new Gson();
    
    @RequestMapping("/toBoothWord")
    public ModelAndView toBoothWord() {
    	ModelAndView mav = new ModelAndView("exhibit/booth/boothWord");
        return mav;
    }
    
    /**
     * 获取展位文字列表
     */
    @RequestMapping("/getBoothWordList")
    public void getBoothWordList(HttpServletResponse response, ExhibitQueryParam param) {
    	logger.info("【展位文字管理】查询展位_开始,操作人:"+LogUtil.getCurrentUserName()+",入参:"+gson.toJson(param));
		try {
			if (StringUtils.isNotBlank(param.getWordStr())) {
				param.setWordList(Arrays.asList(param.getWordStr().split("")));
			}
			PageInfo<BoothWordModel> pageInfo = boothBusiness.queryPagedBoothWordByParam(param);
			int total = (int) pageInfo.getTotal();
			PaginationResult<List<BoothWordModel>> result = PaginationResult.newInstance(pageInfo.getList());
			result.setiTotalRecords(total);
			result.setiTotalDisplayRecords(total);
			actionResult4Json(result.json(), response);
			logger.debug("【展位文字管理】查询展位_结束,操作人:"+LogUtil.getCurrentUserName());
		} catch (Exception e) {
			logger.error("【展位文字管理】查询展位_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
		}
    }
    
}
