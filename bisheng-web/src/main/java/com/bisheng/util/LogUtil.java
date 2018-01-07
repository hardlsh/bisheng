package com.bisheng.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.bisheng.services.system.model.generated.User;

/**
 * Created by niuhonglei on 2017/5/11.
 */
public class LogUtil {
   public static String getCurrentUserName(){
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
   
   public static Long getCurrentUserId(){
       Subject subject = SecurityUtils.getSubject();
       if(subject == null){
           return null;
       }
       User user = (User) subject.getPrincipal();
       if(user == null){
           return null;
       }
       return user.getUserId();
   }
}
