package com.example.myvue.dao;

import com.example.myvue.model.PersonToCompany;
import org.springframework.stereotype.Service;


public interface PersonToCompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    int insert(PersonToCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    int insertSelective(PersonToCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    PersonToCompany selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    int updateByPrimaryKeySelective(PersonToCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_to_company
     *
     * @mbggenerated Sun Apr 22 17:06:00 CST 2018
     */
    int updateByPrimaryKey(PersonToCompany record);
}