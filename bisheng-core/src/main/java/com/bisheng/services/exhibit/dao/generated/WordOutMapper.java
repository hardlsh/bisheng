package com.bisheng.services.exhibit.dao.generated;

import com.bisheng.services.exhibit.model.generated.WordOut;
import com.bisheng.services.exhibit.model.generated.WordOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordOutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int countByExample(WordOutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int deleteByExample(WordOutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int deleteByPrimaryKey(Long outId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int insert(WordOut record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int insertSelective(WordOut record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    List<WordOut> selectByExample(WordOutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    WordOut selectByPrimaryKey(Long outId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int updateByExampleSelective(@Param("record") WordOut record, @Param("example") WordOutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int updateByExample(@Param("record") WordOut record, @Param("example") WordOutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int updateByPrimaryKeySelective(WordOut record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word_out
     *
     * @mbggenerated Mon Jan 29 19:34:56 CST 2018
     */
    int updateByPrimaryKey(WordOut record);
}