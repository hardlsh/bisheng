package com.bisheng.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.bisheng.apps.exhibit.business.ExhibitBusiness;
import com.bisheng.apps.exhibit.param.ExhibitQueryParam;
import com.bisheng.core.framework.redis.SpringUtil;
import com.bisheng.services.exhibit.enums.ExhibitStatusEnum;
import com.bisheng.services.exhibit.model.customized.ExhibitModel;
import com.bisheng.services.system.model.generated.User;

/**
 * 展馆权限标签
 * 
 * @author: lihao
 */
@Service
public class AuthExhibitSelectTag extends TagSupport {
	
	private static final long serialVersionUID = 1976952891181535252L;

	private String id;

	private String name;

	private String classes;

	private String value;

	private String hasAll;

	private String placeholder;

	private String multiple;

	private String style;

	private String onchange;

	private Set<String> multValues = new HashSet<String>();

	public int doStartTag() throws JspException {
		ExhibitBusiness exhibitBusiness = (ExhibitBusiness) SpringUtil.getBean("exhibitBusinessImpl");
		List<ExhibitModel> exhibitList = exhibitBusiness.queryExhibitListByParam(convertParam());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<select ");
		if (StringUtils.isNotBlank(id)) {
			sb.append(" id=\"" + id + "\"");
		}
		if (StringUtils.isNotBlank(name)) {
			sb.append(" name=\"" + name + "\"");
		}
		if (StringUtils.isNotBlank(classes)) {
			sb.append(" class=\"" + classes + "\"");
		}
		if (StringUtils.isNotBlank(placeholder)) {
			sb.append(" placeholder=\"" + placeholder + "\"");
		}
		if (StringUtils.isNotBlank(multiple)) {
			sb.append(" multiple=\"" + multiple + "\"");
		}
		if (StringUtils.isNotBlank(style)) {
			sb.append(" style=\"" + style + "\"");
		}
		if (StringUtils.isNotBlank(onchange)) {
			sb.append(" onchange=\"" + onchange + "\"");
		}
		sb.append(" >");
		if (StringUtils.isNotBlank(placeholder)) {
            if (!(StringUtils.isNotBlank(hasAll) && hasAll.equals("false"))) {
                sb.append("<option value=\"\">" + placeholder + "</option>");
            }
        } else {
            if (!(StringUtils.isNotBlank(hasAll) && hasAll.equals("false"))) {
                sb.append("<option value=\"\">请选择</option>");
            }
        }
		
		doSetValue(value);
		if (exhibitList != null && exhibitList.size() > 0) {
			for (ExhibitModel exhibit : exhibitList) {
				if (multValues.size() > 0 && multValues.contains(String.valueOf(exhibit.getExhibitId()))) {
					sb.append("<option value=\"" + exhibit.getExhibitId() + "\" selected=\"selected\" >"
							+ exhibit.getExhibitName() + "</option>");
				} else {
					sb.append("<option value=\"" + exhibit.getExhibitId() + "\" title=\"" + exhibit.getExhibitName()
							+ "\" label=\"" + exhibit.getExhibitName() + "\">" + exhibit.getExhibitName() + "</option>");
				}
			}
		}
		sb.append(" </select>");

		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHasAll() {
		return hasAll;
	}

	public void setHasAll(String hasAll) {
		this.hasAll = hasAll;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Set<String> getMultValues() {
		return multValues;
	}

	public void setMultValues(Set<String> multValues) {
		this.multValues = multValues;
	}

	private void doSetValue(String value) {
		// 处理多选值
		multValues.clear();
		if (StringUtils.isNotBlank(value)) {
			String[] arrValue = value.split(",");
			for (String item : arrValue) {
				if (StringUtils.isNotBlank(item)) {
					multValues.add(item.trim());
				}
			}
		}
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	
	/**
	 * 参数转换
	 */
	private ExhibitQueryParam convertParam() {
		ExhibitQueryParam exhibitParam = new ExhibitQueryParam();
		// 获取当前操作用户id
		Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        
        List<Integer> exhibitStatusList = new ArrayList<Integer>();
        exhibitStatusList.add(ExhibitStatusEnum.START.getKey());
        exhibitStatusList.add(ExhibitStatusEnum.REST.getKey());
        
        exhibitParam.setUserId(user.getUserId());
        exhibitParam.setExhibitStatusList(exhibitStatusList);
        return exhibitParam;
	}
}
