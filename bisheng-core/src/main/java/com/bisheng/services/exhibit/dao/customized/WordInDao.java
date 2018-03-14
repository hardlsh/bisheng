package com.bisheng.services.exhibit.dao.customized;

import com.bisheng.services.exhibit.dao.generated.WordInMapper;

import java.util.Map;

/**
 * 文字入库
 *
 * @author lihao
 */
public interface WordInDao extends WordInMapper {
    /**
     * 批量插入文字入库信息
     * @param map
     */
    void batchInsert(Map<String, Object> map);

    /**
     * 删除文字入库
     * @param exhibitId
     */
    void deleteByExhibitId(Long exhibitId);
}
