package com.bisheng.tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bisheng.apps.system.business.DictBusiness;
import com.bisheng.core.framework.redis.SpringUtil;
import com.bisheng.services.system.model.generated.DictItem;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字典选择下拉
 * 
 * @author lin.xiao
 */
@Service
public class MultipleSelectTag extends TagSupport {

    private static final long serialVersionUID = -532517444654109642L;

    private String id;

    private String name;

    private String classes;

    private String value;

    private String dictCode;

    private String hasAll;

    private String placeholder;
    
    private String multiple;
    
    private String style;
    
    private String onchange;
    
	private Set<String> multValues = new HashSet<String>();

    public int doStartTag() throws JspException {
    	DictBusiness dictBusiness = (DictBusiness) SpringUtil.getBean("dictBusinessImpl");
    	List<DictItem> dictList = dictBusiness.queryDictItemList(dictCode);
    	
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
        if (dictList != null && dictList.size() > 0) {
            for (DictItem dict: dictList) {
                if (StringUtils.isNotBlank(value)
                        && multValues.contains(dict.getItemCode())) {
                    sb.append("<option value=\"" + dict.getItemCode()
                            + "\" selected=\"selected\" >" + dict.getItemName()
                            + "</option>");
                } else {
                    sb.append("<option value=\"" + dict.getItemCode() + "\" title=\"" + dict.getItemName() + "\" label=\"" + dict.getItemName() + "\">"
                            + dict.getItemName() + "</option>");
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

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
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
			for(String item : arrValue) {
				if(StringUtils.isNotBlank(item)){
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
