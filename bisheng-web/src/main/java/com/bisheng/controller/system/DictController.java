package com.bisheng.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bisheng.apps.system.business.DictBusiness;
import com.bisheng.apps.system.param.DictItemQuery;
import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.controller.BaseController;
import com.bisheng.core.framework.pager.PaginationResult;
import com.bisheng.services.system.model.customized.DictItemModel;
import com.bisheng.services.system.model.generated.Dict;
import com.bisheng.services.system.model.generated.DictItem;
import com.bisheng.util.LogUtil;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;

@Controller
@RequestMapping("dict")
public class DictController extends BaseController {
	
    protected static final Logger logger = LoggerFactory.getLogger(DictController.class);

    @Resource
    DictBusiness dictBusiness;
    
    @RequestMapping("/list")
    public String goRoleList() {
        return "/system/dict/dictList";
    }

	@RequestMapping("/getDictList")
    public void getDictList(HttpServletResponse response, DictQuery dictQuery) {
    	logger.info("查询开始：param:{},操作人：{}",JSONObject.toJSONString(dictQuery), LogUtil.getCurrentUserName());
        PaginationResult<List<Dict>> result = dictBusiness.queryDictList(dictQuery);
        actionResult4Json(result.json(), response);
    	logger.info("查询结束：param:{},操作人：{}",JSONObject.toJSONString(dictQuery), LogUtil.getCurrentUserName());
    }

    @RequestMapping("/addDict")
    @ResponseBody
    public Object saveDict(Dict dict) {
    	logger.info("保存开始：param:{},操作人：{}",JSONObject.toJSONString(dict), LogUtil.getCurrentUserName());

        ALMResponse res = null;
        try {
            if (StringUtils.isBlank(dict.getDictCode())
                    || StringUtils.isBlank(dict.getDictName())) {
                return new ALMResponse(RetCode.INVALID_ARGS);
            }
            DictQuery dictQuery = new DictQuery();
            dictQuery.setDictCode(dict.getDictCode());
            PaginationResult<List<Dict>> pageInfo = dictBusiness.queryDictList(dictQuery);
            if (pageInfo != null && pageInfo.getData() != null && pageInfo.getData().size() > 0) {
                return new ALMResponse(RetCode.INVALID_ARGS,
                        "字典编号重复，请重新输入字典编号");
            }
            dictBusiness.addDict(dict);
            res = new ALMResponse(RetCode.SUCCESS);
        } catch (Exception e) {
        	logger.error("[字典管理]新增字典项_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE);
        }
    	logger.info("保存结束：param:{},操作人：{}",JSONObject.toJSONString(dict), LogUtil.getCurrentUserName());
        return res;
    }
    
    @RequestMapping("/getDict")
    @ResponseBody
    public Object getDic(HttpServletResponse response, String dictCode) {
    	logger.info("查询开始：param:{},操作人：{}",JSONObject.toJSONString(dictCode), LogUtil.getCurrentUserName());
        ALMResponse res = null;
        try {
        	DictQuery dictQuery = new DictQuery();
        	dictQuery.setDictCode(dictCode);
            PaginationResult<List<Dict>> pageInfo = dictBusiness.queryDictList(dictQuery);
            if (pageInfo != null && pageInfo.getData() != null && pageInfo.getData().size() > 0) {
                res = new ALMResponse(RetCode.SUCCESS);
                res.setData(pageInfo.getData().get(0));
            } else {
                res = new ALMResponse(RetCode.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("[字典管理]查询字典项_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE);
        }
    	logger.info("查询结束：param:{},操作人：{}",JSONObject.toJSONString(dictCode), LogUtil.getCurrentUserName());
        return res;
    }
    
    @RequestMapping("/updateDict")
    @ResponseBody
    public Object updateDic(Dict dict) {
    	logger.info("更新开始：param:{},操作人：{}",JSONObject.toJSONString(dict), LogUtil.getCurrentUserName());
        ALMResponse res = null;
        try {
        	dictBusiness.updateDict(dict);
            res = new ALMResponse(RetCode.SUCCESS);
        } catch (Exception e) {
        	logger.error("[字典管理]修改字典项_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE, e.getMessage());
        }
    	logger.info("更新结束：param:{},操作人：{}",JSONObject.toJSONString(dict), LogUtil.getCurrentUserName());
        return res;
    }
    
    @RequestMapping("/dictItem")
    public String goDicItemList(Model model, String dictCode) {
    	logger.info("查询开始：param:{},操作人：{}",JSONObject.toJSONString(dictCode), LogUtil.getCurrentUserName());

    	DictItemQuery query = new DictItemQuery();
    	query.setDictCode(dictCode);
        List<DictItem> list = dictBusiness.queryDictItemList(query);
        model.addAttribute("dictItems", list);
        model.addAttribute("dictCode", dictCode);
    	logger.info("查询结束：param:{},操作人：{}",JSONObject.toJSONString(dictCode), LogUtil.getCurrentUserName());

        return "/system/dict/dictItem";
    }
    
    @RequestMapping("/addDictItem")
    @ResponseBody
    public Object saveDicItem(DictItem dictItem) {
    	logger.info("保存开始：param:{},操作人：{}",JSONObject.toJSONString(dictItem), LogUtil.getCurrentUserName());

    	dictBusiness.addDictItem(dictItem);
    	
    	logger.info("保存结束：param:{},操作人：{}",JSONObject.toJSONString(dictItem), LogUtil.getCurrentUserName());
        return new ALMResponse(RetCode.SUCCESS);
    }
    
    @RequestMapping("/updateDictItem")
    @ResponseBody
    public Object updateDicItem(DictItemModel dictItemModel) {
    	logger.info("更新开始：param:{},操作人：{}",JSONObject.toJSONString(dictItemModel), LogUtil.getCurrentUserName());

        dictBusiness.updateDictItem(dictItemModel);

    	logger.info("更新结束：param:{},操作人：{}",JSONObject.toJSONString(dictItemModel), LogUtil.getCurrentUserName());
        return new ALMResponse(RetCode.SUCCESS);
    }
    
    @RequestMapping("/getDictItem")
    @ResponseBody
    public Object getDicItem(String dictCode, String itemCode) {
    	logger.info("查询开始：param:{},操作人：{}",JSONObject.toJSONString(itemCode), LogUtil.getCurrentUserName());
        ALMResponse res = null;
        try {
        	DictItemQuery query = new DictItemQuery();
            query.setDictCode(dictCode);
            query.setItemCode(itemCode);
            List<DictItem> list = dictBusiness.queryDictItemList(query);
            if (list != null && list.size() > 0) {
                res = new ALMResponse(RetCode.SUCCESS);
                res.setData(list.get(0));
            } else {
                res = new ALMResponse(RetCode.NOT_FOUND);
            }
        } catch (Exception e) {
        	logger.error("[字典管理]查询字典明细_异常,操作人:"+LogUtil.getCurrentUserName()+",异常原因:", e);
            res = new ALMResponse(RetCode.FAILURE);
        }
    	logger.info("查询结束：param:{},操作人：{}",JSONObject.toJSONString(itemCode), LogUtil.getCurrentUserName());
        return res;
    }
    
}
