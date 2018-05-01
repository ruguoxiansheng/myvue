package com.example.myvue.model;

import java.util.List;

/**
 * 标底对象,包括应中标价格和前五的价格的id
 */
public class BaseTender {
    private  Long shouldPriceIdId;
    private List<ProQuotePrice> headFiveId;
}
