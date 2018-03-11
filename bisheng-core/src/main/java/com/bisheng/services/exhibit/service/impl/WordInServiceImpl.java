package com.bisheng.services.exhibit.service.impl;

import com.bisheng.core.framework.service.BaseService;
import com.bisheng.services.exhibit.dao.customized.WordInDao;
import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordIn;
import com.bisheng.services.exhibit.service.WordInService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文字入库
 *
 * @author lihao
 */
@Service
public class WordInServiceImpl extends BaseService implements WordInService{

    private WordInDao getWordInDao(){
        return sqlSession.getMapper(WordInDao.class);
    }

    @Override
    public void batchInsert(List<WordIn> wordInList) {
        Map<String, Object> map = new HashMap<>();
        map.put("wordInList", wordInList);
        getWordInDao().batchInsert(map);
    }

    @Override
    public void deleteByWord(Word word) {
        Word record = new Word();
        record.setWord(word.getWord());
        getWordInDao().deleteByWord(record);
    }
}
