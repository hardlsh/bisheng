package com.bisheng.services.exhibit.service;

import com.bisheng.services.exhibit.model.generated.WordOut;

import java.util.List;

/**
 * 文字出库
 *
 * @author lihao
 */
public interface WordOutService {
    /**
     * 批量插入文字入库信息
     * @param wordOutList
     */
    void batchInsert(List<WordOut> wordOutList);

    /**
     * 删除文字出库
     * @param exhibitId
     */
    void deleteByExhibitId(Long exhibitId);
}
