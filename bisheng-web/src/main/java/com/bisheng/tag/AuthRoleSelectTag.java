package com.bisheng.tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bisheng.apps.system.business.RoleBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.framework.redis.SpringUtil;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.model.customized.RoleModel;

/**
 * @ClassName: AuthRoleSelectTag
 * @author: shihao.li
 * @Explain: 权限 角色选择下拉
 * @date: 2017年3月30日 下午3:39:15
 */
@Service
public class AuthRoleSelectTag extends TagSupport {

	private static final long serialVersionUID = -8355577843921778178L;

	private String id;

	private String name;

	private String classes;

	private String value;

	private String dictKey;

	private String hasAll;

	private String placeholder;

	private String multiple;

	private String style;

	private String onchange;

	private Set<String> multValues = new HashSet<String>();

	public int doStartTag() throws JspException {
		RoleBusiness roleBusiness = (RoleBusiness) SpringUtil.getBean("roleBusinessImpl");
		
		AuthParam param = new AuthParam();
		param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());
		List<RoleModel> roleList = roleBusiness.queryAllRoleByParam(param);
		
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
		doSetValue(value);
		if (roleList != null && roleList.size() > 0) {
			for (RoleModel role : roleList) {
				if (multValues.size() > 0 && multValues.contains(String.valueOf(role.getRoleId()))) {
					sb.append("<option value=\"" + role.getRoleId() + "\" selected=\"selected\" >"
							+ role.getRoleName() + "</option>");
				} else {
					sb.append("<option value=\"" + role.getRoleId() + "\" title=\"" + role.getRoleName()
							+ "\" label=\"" + role.getRoleName() + "\">" + role.getRoleName() + "</option>");
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

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
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
}
