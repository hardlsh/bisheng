/**
 * @(#)BaseService.java, 2016年1月28日.
 * <p/>
 * Copyright (c) 2015, Youjie8 and/or its affiliates. All rights reserved.
 * Youjie8 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.bisheng.core.framework.service;

import com.bisheng.apps.BaseQuery;
import com.github.pagehelper.PageHelper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 所有Service的基类，用来注入sqlSession
 *
 * @author bingfeng.xie
 *         <p/>
 *         2016年1月28日
 */
public abstract class BaseService {

    /**
     * sqlSession
     */
    @Autowired
    @Qualifier("sessionTemplate")
    protected SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    protected void setStartPage(BaseQuery query) {
        int startNo = query.getStart() != null ? query.getStart() : 0;
        int pageSize = query.getLength() != null ? query.getLength() : 10;
        int pageNum = startNo / pageSize + 1;
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 分页插入
     *
     * @param objList
     */
    protected <T> void pageInsert(List<T> objList, InsertBatchCallBack<T> callBack) {
        int size = objList.size();
        int pageSize = (size - 1) / BaseConstant.PAGE_SIZE + 1;
        int pageStart;
        int pageEnd;
        for (int i = 0; i < pageSize; i++) {
            pageStart = i * BaseConstant.PAGE_SIZE;
            pageEnd = (i + 1) * BaseConstant.PAGE_SIZE;
            if (size < pageEnd) {
                pageEnd = size;
            }
            // 插入数据
            callBack.insertBatch(objList.subList(pageStart, pageEnd));
        }
    }

}
