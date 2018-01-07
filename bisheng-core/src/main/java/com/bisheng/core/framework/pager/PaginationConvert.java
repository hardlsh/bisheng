package com.bisheng.core.framework.pager;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Copyright (c) 2017, shao.liu@mljr.com All Rights Reserved.
 * date: 2017/2/22 11:12 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public class PaginationConvert {

    public static <T> PaginationResult<List<T>> buildPagination(PageInfo<T> pageInfo) {
        PaginationResult<List<T>> result = PaginationResult.newInstance(pageInfo.getList());
        result.setiTotalRecords((int) pageInfo.getTotal());
        result.setiTotalDisplayRecords((int) pageInfo.getTotal());
        return result;
    }

}
