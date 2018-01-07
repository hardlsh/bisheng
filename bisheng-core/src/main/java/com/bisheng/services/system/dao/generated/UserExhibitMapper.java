package com.bisheng.services.system.dao.generated;

import com.bisheng.services.system.model.generated.UserExhibit;
import com.bisheng.services.system.model.generated.UserExhibitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExhibitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int countByExample(UserExhibitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int deleteByExample(UserExhibitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int insert(UserExhibit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int insertSelective(UserExhibit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    List<UserExhibit> selectByExample(UserExhibitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    UserExhibit selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int updateByExampleSelective(@Param("record") UserExhibit record, @Param("example") UserExhibitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int updateByExample(@Param("record") UserExhibit record, @Param("example") UserExhibitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int updateByPrimaryKeySelective(UserExhibit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_user_exhibit
     *
     * @mbggenerated Fri Aug 11 10:28:34 CST 2017
     */
    int updateByPrimaryKey(UserExhibit record);
}