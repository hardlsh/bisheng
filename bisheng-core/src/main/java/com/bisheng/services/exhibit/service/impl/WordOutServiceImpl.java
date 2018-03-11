package com.bisheng.services.exhibit.service.impl;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.WordOutDao;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordOut;
import com.bisheng.services.exhibit.service.WordOutService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文字出库
 *
 * @author lihao
 */
@Service
public class WordOutServiceImpl extends BaseService implements WordOutService{

    private WordOutDao getWordOutDao() {
        return sqlSession.getMapper(WordOutDao.class);
    }

    @Override
    public void batchInsert(List<WordOut> wordOutList) {
        Map<String, Object> map = new HashMap<>();
        map.put("wordOutList", wordOutList);
        getWordOutDao().batchInsert(map);
    }

    @Override
    public void deleteByWord(Word word) {
        Word record = new Word();
        record.setWord(word.getWord());
        getWordOutDao().deleteByWord(record);
    }
}
