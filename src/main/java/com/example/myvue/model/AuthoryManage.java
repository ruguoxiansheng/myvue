package com.example.myvue.model;

public class AuthoryManage {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column authory_manage.id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column authory_manage.consumer_id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    private Long consumerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column authory_manage.authory_type
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    private Integer authoryType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column authory_manage.id
     *
     * @return the value of authory_manage.id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column authory_manage.id
     *
     * @param id the value for authory_manage.id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column authory_manage.consumer_id
     *
     * @return the value of authory_manage.consumer_id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public Long getConsumerId() {
        return consumerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column authory_manage.consumer_id
     *
     * @param consumerId the value for authory_manage.consumer_id
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column authory_manage.authory_type
     *
     * @return the value of authory_manage.authory_type
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public Integer getAuthoryType() {
        return authoryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column authory_manage.authory_type
     *
     * @param authoryType the value for authory_manage.authory_type
     *
     * @mbggenerated Sun Apr 22 17:05:35 CST 2018
     */
    public void setAuthoryType(Integer authoryType) {
        this.authoryType = authoryType;
    }
}