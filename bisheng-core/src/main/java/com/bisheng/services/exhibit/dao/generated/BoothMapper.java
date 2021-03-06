package com.bisheng.services.exhibit.dao.generated;

import com.bisheng.services.exhibit.model.generated.Booth;
import com.bisheng.services.exhibit.model.generated.BoothExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoothMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int countByExample(BoothExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int deleteByExample(BoothExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int deleteByPrimaryKey(Long boothId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int insert(Booth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int insertSelective(Booth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    List<Booth> selectByExample(BoothExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    Booth selectByPrimaryKey(Long boothId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int updateByExampleSelective(@Param("record") Booth record, @Param("example") BoothExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int updateByExample(@Param("record") Booth record, @Param("example") BoothExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int updateByPrimaryKeySelective(Booth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_booth
     *
     * @mbggenerated Mon Sep 11 15:06:22 CST 2017
     */
    int updateByPrimaryKey(Booth record);
}