package com.bisheng.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bisheng.util.export.Cell;
import com.bisheng.util.export.Report;
import com.bisheng.util.export.ReportField;
public abstract class CommonUtil {
	public static Object convert(String value, @SuppressWarnings("rawtypes") Class clazz){
		Object obj = null;
		if(clazz == Integer.class || clazz == int.class){
			try {
				Integer data = Integer.valueOf(value);
				obj = data.intValue();
			} catch (NumberFormatException e) {
				obj=0;
			}
		}else if(clazz == Float.class || clazz==float.class){
			try {
				Float data = new Float(value);
				obj = data.toString();
			} catch (NumberFormatException e) {
				obj=0;
			}
		}else if(clazz == Short.class || clazz==short.class){
			try {
				Short data = new Short(value);
				obj = data.toString();
			} catch (NumberFormatException e) {
				obj=0;
			}
		}else if(clazz == Long.class || clazz==long.class){
			try {
				Long data = new Long(value);
				obj = data;
			} catch (NumberFormatException e) {
				obj=0;
			}
		}else if(clazz == Double.class || clazz==double.class){
			try {
				Double data = new Double(value);
				obj = data.toString();
			} catch (NumberFormatException e) {
				obj=0;
			}
		}else if(clazz == Date.class){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				obj = sdf.parse(value);
				Date create = sdf.parse("2014-12-01 00:00:00");
				if(create.compareTo((Date)obj)>0){
					obj = create;
				}
			} catch (Exception e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					obj = sdf.parse(value);
					Date create = sdf.parse("2014-12-01");
					if(create.compareTo((Date)obj)>0){
						obj = create;
					}
				} catch (ParseException e1) {
					//e1.printStackTrace();
					obj = null;
					System.out.println("******* parse full date ERROR! dateStr is [" + value + "]********");
				}
			}
		}else if(clazz == String.class){
			obj = value;
		}else if(clazz == BigDecimal.class){
			try {
				BigDecimal data = new BigDecimal(value);
				obj = data;
			} catch (NumberFormatException e) {
				obj=new BigDecimal("0");
			}
		}else{
			throw new RuntimeException("未知的异常");
		}
		return obj;
	}
	@SuppressWarnings("unchecked")
	public static <T> T to(Class<T> clazz){
		String beanName = clazz.getSimpleName();
		//beanName = beanName.replace(beanName.substring(0,1), beanName.substring(0,1).toLowerCase())+"Impl";
		beanName = beanName.substring(0,1).toLowerCase() + beanName.substring(1) + "Impl";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		Object obj = context.getBean(beanName,clazz);
		return (T) obj;
	}
	
	private static void setExcelColumnName(StringBuilder str, int col){
	    int tmp = col / 26;
	    if(tmp > 26){
	        setExcelColumnName(str, tmp - 1);
	    }else if(tmp > 0){
	        str.append((char)(tmp + 64));
	    }
	    str.append((char)(col % 26 + 65));
	}
	 
	public static String getExcelColumnName(int col) {
	    StringBuilder str = new StringBuilder(2);
	    setExcelColumnName(str, col);
	    return str.toString();
	}
	
	public static Boolean matches(String str,String regex){
		if(regex!=null && !"".equals(regex)){
			return str.matches(regex);
		}else{
			return true;
		}
	}
	/**
	 * 去除前缀0
	 * @param str
	 * @return
	 */
	public String cutPrefixZero(String str) {
		int strLength = str.length();
		int zeroLength =0;
		for(int i=0;i<strLength;i++){
			String head = str.substring(i, i+1);
			if(!"0".equals(head)){
				zeroLength = i;
				break;
			}
		}
		//根据0的个数截取后面几位
		return str.substring(zeroLength,strLength);
	}
	public static String getInOrgLeves(String key){
		Map<String,String> map = new HashMap<String,String>();
		map.put("0001", " '0001', '0002', '0003', '0004' ");
		map.put("0002", " '0002', '0003', '0004' ");
		map.put("0003", " '0003', '0004' ");
		map.put("0004", " '0004' ");
		return map.get(key);
	}

	public static String getLevel(int level)
    {
        StringBuilder sb = new StringBuilder();
        //sb.append("|--");
        for (int i = 1; i < level; i++)
        {
            if (i == level - 1)
            {
                sb.append("|--");
            }
            else
            {
                sb.insert(0, "&nbsp;&nbsp;&nbsp;");
                //sb.Insert(0, "   ");
            }
        }
        return sb.toString();
    }
	
 
	public static String replace(String str,String rstr,int startIndex){
		String preStr = str.substring(0, startIndex);
		String subStr = str.substring(startIndex+1);
		return preStr + rstr + subStr;
	}
	
	public static String obj2Str(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}
	public static String obj2Str(Cell cell){
		if(cell==null){
			return "";
		}else{
			return String.valueOf(cell).trim();
		}
	}
	/**
	 *14位的时间戳
	 * @return
	 */
	public static String getNowDateTime1(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//Long currentTimeMillis = System.currentTimeMillis();
		//Calendar nowCalendar = Calendar.getInstance();
		//nowCalendar.setTimeInMillis(currentTimeMillis);
		//String nowDate = sdf.format(nowCalendar.getTime());
		//String nowDate = sdf.format(new Date());
		return sdf.format(new Date());
	}
	
	
	public static String getNowDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Long currentTimeMillis = System.currentTimeMillis();
		//Calendar nowCalendar = Calendar.getInstance();
		//nowCalendar.setTimeInMillis(currentTimeMillis);
		//String nowDate = sdf.format(nowCalendar.getTime());
		//String nowDate = sdf.format(new Date());
		return sdf.format(new Date());
	}
	public static String getNowDayDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//Long currentTimeMillis = System.currentTimeMillis();
		//Calendar nowCalendar = Calendar.getInstance();
		//nowCalendar.setTimeInMillis(currentTimeMillis);
		//String nowDate = sdf.format(nowCalendar.getTime());
		//String nowDate = sdf.format(new Date());
		return sdf.format(new Date());
	}
	
	public static String getSearchDate(Date date,String... pattern){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	 
	
	/*public static Map<String,Object> getAnnotationMap(JoinPoint joinPoint) {    
    	Map<String,Object> map = new HashMap<String,Object>(); 
        try {
			String className = joinPoint.getTarget().getClass().getName();    
			String methodName = joinPoint.getSignature().getName();    
			Object[] arguments = joinPoint.getArgs();
			//Cotroller
			Class targetClass = Class.forName(className);    
			Method[] methods = targetClass.getMethods();    
			String description = "";    
			 for (Method method : methods) {    
			     if (method.getName().equals(methodName)) {    
			        Class[] clazzs = method.getParameterTypes();  
			        if(arguments.length!=1){
			        	throw new RuntimeException("Controll �������Ȳ�����1��");
			        }
			        //ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			        //HttpServletRequest request = requestAttributes.getRequest();
			        
			        ServletWebRequest  servletWebRequest = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
			        HttpServletRequest request = servletWebRequest.getRequest();
			        HttpServletResponse response = servletWebRequest.getResponse();
			        HttpSession session = request.getSession();
			        User user = (User) session.getAttribute("user");
				    
				    if(arguments[0] instanceof ActionEvent){
			        	BaseForm baseForm = (BaseForm) arguments[0];
				        ActionContext context = new ActionContext(null, null);
				        baseForm.setContext(context);
			        }
			        
			        
			         
			    }    
			}
		} catch (Exception e) {
			e.printStackTrace();
		}    
         return map;    
    } */
	
	public static String getCacheKey(JoinPoint pjp){
		//StringBuiter
		StringBuilder key = new StringBuilder();
		String packageName = pjp.getTarget().getClass().getName();
		key.append(packageName);
		String methodName = pjp.getSignature().getName();
		key.append(".").append(methodName);
		Object[] args = pjp.getArgs();
		ObjectMapper  om = new ObjectMapper();
		om.setSerializationInclusion(Inclusion.NON_NULL);
		for(Object arg : args){
			StringWriter str = new StringWriter(); 
			try {
				om.writeValue(str, arg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			key.append(".").append(str);
		}
		return key.toString();
	}
	
	 
	 public static <T> List<CellEntity> getcellEntityList(String mode,
				Class<T> clazz) {
			List<CellEntity> cellEntityList = new ArrayList<CellEntity>();
			if(clazz.isAnnotationPresent(Report.class)){
				for(Field field : clazz.getDeclaredFields()){
					ReportField poi = field.getAnnotation(ReportField.class);
					if(poi != null){
						for(Cell cell : poi.Cells()){
							if(mode.equals(cell.Mode())){
								CellEntity cellEntity = new CellEntity();
								cellEntity.setField(field);
								cellEntity.setCell(cell);
								cellEntityList.add(cellEntity);
							}
						}
					}
				} 
			}
			return cellEntityList;
		}
	 
	 
	 

}
