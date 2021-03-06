package com.bisheng.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bisheng.apps.system.business.UserBusiness;
import com.bisheng.core.framework.redis.SpringUtil;
import com.bisheng.services.system.model.generated.User;

/**
 * Created by liuchao on 16/1/15.
 * 基础控制器类
 */
public abstract class BaseController {

    protected UserBusiness userBusiness = (UserBusiness) SpringUtil.getBean("userBusinessImpl");
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public  String EXPORT_NAME = "ExcelReport.xls";
    /**
     * 输出Json串到前端
     *
     * @param content Json串内容
     */
    public void actionResult4Json(String content, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void processResponse( HttpServletResponse response) throws UnsupportedEncodingException {
		String newName = EXPORT_NAME;
		response.reset();
		// 设定输出文件头,解决文件名中文乱码问题
		response.setHeader("Content-Disposition", "attachment;fileName="+ new String(newName.getBytes("gb2312"), "ISO8859-1"));
		response.setContentType("application/x-download");
	}
    /**
     * 将request.getParameterMap()转换成可操作的普通Map
     *
     * @param request
     * @param checkBoxes 复选框name
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Object> getParameterMap(HttpServletRequest request, String... checkBoxes) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        for (String checkBox : checkBoxes) {
            String[] checkValues = request.getParameterValues(checkBox);
            if (checkValues == null) {
                checkValues = request.getParameterValues(checkBox + "[]");
            }
            returnMap.put(checkBox, checkValues);
        }
        return returnMap;
    }

    /**
     * json返回消息
     *
     * @param isSuccess 是否成功
     * @param msg       消息
     * @return 表单提交后的响应
     */
    protected String josnResponse(boolean isSuccess, Object msg) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("success", isSuccess);
        resMap.put("msg", msg);
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	public String getEXPORT_NAME() {
		return EXPORT_NAME;
	}
	public void setEXPORT_NAME(String eXPORT_NAME) {
		EXPORT_NAME = eXPORT_NAME;
	}

    /**
     * 获取当前操作用户id
     */
    protected Long getCurrentUserId(){
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            return null;
        }
        User user = (User) subject.getPrincipal();
        return user == null ? null : user.getUserId();
    }
    
    /**
     * 获取当前操作用户名
     */
    protected String getCurrentUserName(){
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            return "无法获取当前操作人";
        }
        User user = (User) subject.getPrincipal();
        if(user == null){
            return "无法获取当前操作人";
        }
        return user.getUserName();
    }

    /**
     * 处理查询参数
     * @param param
     */
    protected void convertParam(ExhibitQueryParam param){
        param.setUserId(getCurrentUserId());
        String city = param.getCity();
        if (StringUtils.isNotBlank(city)) {
            city = city.replace(" ", "");
            // 模糊查询使用
            city = "%" + city + "%";
            param.setCity(city);
        }
    }
}
