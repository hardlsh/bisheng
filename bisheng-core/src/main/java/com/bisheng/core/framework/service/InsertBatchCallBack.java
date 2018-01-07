package com.bisheng.core.framework.service;

import java.util.List;

/**
 * Copyright (c) 2016, shao.liu@mljr.com All Rights Reserved.
 * date: 2016/11/23 16:58 <br/>
 *
 * @author liushao
 * @version 1.0
 * @since JDK 1.8
 */
public interface InsertBatchCallBack<T> {
    /**
     * 插入回调
     *
     * @param list
     */
    void insertBatch(List<T> list);
}
