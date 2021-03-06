package com.bisheng.services.system.dao.generated;

import com.bisheng.services.system.model.generated.RoleResource;
import com.bisheng.services.system.model.generated.RoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int countByExample(RoleResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int deleteByExample(RoleResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int insert(RoleResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int insertSelective(RoleResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    List<RoleResource> selectByExample(RoleResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    RoleResource selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int updateByPrimaryKeySelective(RoleResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tp_sys_role_resource
     *
     * @mbggenerated Sun Aug 06 14:41:38 CST 2017
     */
    int updateByPrimaryKey(RoleResource record);
}