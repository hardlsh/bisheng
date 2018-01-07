package com.bisheng.core.framework.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Copyright (c) 2016, shao.liu@mljr.com All Rights Reserved.
 * date: 16/9/15 13:43 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

}
