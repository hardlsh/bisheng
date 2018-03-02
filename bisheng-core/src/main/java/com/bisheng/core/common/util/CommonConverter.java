/*
 * Copyright 2016 mljr.com All right reserved. This software is the
 * confidential and proprietary information of mljr.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with mljr.com .
 */
package com.bisheng.core.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author jian.li
 */
public class CommonConverter {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private static MapperFacade  mapperFacade  = null;

    static {
       CommonConverter.mapperFacade = CommonConverter.mapperFactory.getMapperFacade();
    }

    private CommonConverter() {

    }

    public static MapperFacade getConverter() {
        return CommonConverter.mapperFacade;
    }

}
