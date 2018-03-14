package com.bisheng.services.exhibit.service;

import com.bisheng.services.exhibit.model.generated.WordIn;

import java.util.List;

/**
 * 文字入库
 *
 * @author lihao
 */
public interface WordInService {
    /**
     * 批量插入文字入库信息
     * @param wordInList
     */
    void batchInsert(List<WordIn> wordInList);

    /**
     * 删除文字入库
     * @param exhibitId
     */
    void deleteByExhibitId(Long exhibitId);
}
