package com.example.myvue.dao;

import com.example.myvue.model.ShouldQuotePrice;
import org.springframework.stereotype.Service;


public interface ShouldQuotePriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    int deleteByPrimaryKey(Long shoulePriceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    int insert(ShouldQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    int insertSelective(ShouldQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    ShouldQuotePrice selectByPrimaryKey(Long shoulePriceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    int updateByPrimaryKeySelective(ShouldQuotePrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table should_quote_price
     *
     * @mbggenerated Wed Apr 25 08:41:56 CST 2018
     */
    int updateByPrimaryKey(ShouldQuotePrice record);
}