package com.example.myvue.dao;

import com.example.myvue.model.UserPersonObject;

public interface UserPersonObjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    int insert(UserPersonObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    int insertSelective(UserPersonObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    UserPersonObject selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    int updateByPrimaryKeySelective(UserPersonObject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_person_object
     *
     * @mbggenerated Sun Apr 22 17:07:01 CST 2018
     */
    int updateByPrimaryKey(UserPersonObject record);
}