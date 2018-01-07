package com.bisheng.core.common.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author: lishihao
 * @date: 2016年11月22日 下午4:19:48
 */
public class MBeanUtil {
	
	/**
	 * 获取非空的属性
	 * 
	 * @Title: getNullPropertyNames 
	 * @Description: TODO
	 * @Ecplain: 
	 * @return: String[]
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	public static void copyProperties(Object source, Object target){
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}
	
	public static void copyProperties(Object source, Object target, String... ignoreProperties){
		BeanUtils.copyProperties(source, target, ignoreProperties);
	}

}
