package com.bisheng.services.exhibit.dao.customized;

import com.bisheng.services.exhibit.dao.generated.WordOutMapper;

import java.util.Map;

/**
 * 文字出库
 *
 * @author lihao
 */
public interface WordOutDao extends WordOutMapper{
    /**
     * 批量插入文字出库信息
     * @param map
     */
    void batchInsert(Map<String, Object> map);
}
