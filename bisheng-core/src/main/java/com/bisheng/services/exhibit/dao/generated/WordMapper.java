package com.bisheng.services.exhibit.dao.generated;

import com.bisheng.services.exhibit.model.generated.Word;
import com.bisheng.services.exhibit.model.generated.WordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int countByExample(WordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int deleteByExample(WordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int deleteByPrimaryKey(Long wordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int insert(Word record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int insertSelective(Word record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    List<Word> selectByExample(WordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    Word selectByPrimaryKey(Long wordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int updateByExampleSelective(@Param("record") Word record, @Param("example") WordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int updateByExample(@Param("record") Word record, @Param("example") WordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int updateByPrimaryKeySelective(Word record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_word
     *
     * @mbggenerated Sun Dec 10 17:09:16 CST 2017
     */
    int updateByPrimaryKey(Word record);
}