package com.example.myvue.dao;

import com.example.myvue.model.ProQuotePrice;
import org.springframework.stereotype.Service;


public interface ProQuotePriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    int insert(ProQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    int insertSelective(ProQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    ProQuotePrice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    int updateByPrimaryKeySelective(ProQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_quote_price
     *
     * @mbggenerated Wed Apr 25 08:32:38 CST 2018
     */
    int updateByPrimaryKey(ProQuotePrice record);
}