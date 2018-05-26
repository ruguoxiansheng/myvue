package com.example.myvue.calinterface.impl;

import com.example.myvue.calinterface.CalProQuotePrice;
import com.example.myvue.model.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("method1")
public class CalProQuotePriceImp implements CalProQuotePrice {
    @Override
    public ShouldQuotePrice calShouldQuotePrice(QuotePrice quotePrice, ProjectObject projectObject) {

        //1、计算投标限价
        Double priceLimit = projectObject.getBudget() * projectObject.getRatioPrice();
        //2、计算预警价
        Double alertPriceLimit = projectObject.getBudget() * projectObject.getAlertRatioPrice();
        //3、计算投标报价平均值 C1
       Double C1 =  calCulateC1(quotePrice.getCompanyQuotePriceList(),priceLimit,alertPriceLimit);
       //4、计算修正后的投标报价平均值 C2
        Double C2 =  calCulateC2(quotePrice.getCompanyQuotePriceList(),C1,alertPriceLimit);

        //5、计算最低成本价
        //5.1、计算最低成本价 D1: D1=K×B×K1+C2×K2
        float K= quotePrice.getK()/1000+0.91f;
        float K2= quotePrice.getK2()/100+0.20f;
        float K1=1-K2;

        Double D1 =K * priceLimit*K1+C2 * K2;
        //5.2、对 D1 进行修正，计算修正后的最低成本价 D2
        // 判断是否需要修正D2
        Double D2 =D1;
        if (correctD1(quotePrice.getCompanyQuotePriceList(),D1)) {
            // 计算E2
           Double E1 =  calculateE1( quotePrice.getCompanyQuotePriceList(),D1);
           D2 = calculateD2(quotePrice.getCompanyQuotePriceList(), D1, E1);
        }

        ShouldQuotePrice shouldQuotePrice = new ShouldQuotePrice();
        shouldQuotePrice.setProjectNumber(quotePrice.getProjectNumber());
        shouldQuotePrice.setShouldPrice(D2);
        shouldQuotePrice.setConsumerId(quotePrice.getConsumerId());
        return shouldQuotePrice;
    }

    /**
     * 计算有效报价
     * @param companyQuotePriceList
     * @param d2
     * @return 有效报价
     */
    private List<CompanyQuotePrice> calculateUsableValue(List<CompanyQuotePrice> companyQuotePriceList, Double d2) {
        List<CompanyQuotePrice> usableValues = new ArrayList<>();
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();
            if (companyValue  > d2)
                usableValues.add(companyQuotePrice);
        }
        // 对有效的价格进行排序
        Collections.sort(usableValues);
        return usableValues;
    }

    /**
     * 计算D2
     * @param companyQuotePriceList
     * @param d1
     * @param e1
     * @return d2
     */
    private double calculateD2(List<CompanyQuotePrice> companyQuotePriceList, Double d1, Double e1) {
        Double sum = 0.00;
        int count = 0;
        Double d2 = 0.00;
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();

        //  所有低于 D1的投标报价中大于 E1×(1-3%)的投标报价的算术平均值
            if (companyValue < d1 && companyValue >e1*(1-0.03) ) {
                sum+=companyValue;
                count+=1;
            }

        }
        d2 = sum/count;
        return d2;
    }

    /**
     * 计算E1
     * @param companyQuotePriceList
     * @param d1
     */
    private Double calculateE1(List<CompanyQuotePrice> companyQuotePriceList, Double d1) {
        Double sum = 0.00;
        Double e1 = 0.00;
        int count = 0;
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();
            // 低于d1的算数平均值
            if (companyValue < d1) {
                sum+=companyValue;
                count+=1;
            }
        }
        e1=sum/count;
        return e1;
    }

    /**
     * 判断是否需要修正D1
     * @param companyQuotePriceList 总的人数
     * @param d1
     * @return true:需要，false:不需要
     */
    private boolean correctD1(List<CompanyQuotePrice> companyQuotePriceList, Double d1) {
     //   投标报价低于 D1 的投标人数
        int count = lessD1(companyQuotePriceList,d1);
//当投标报价低于 D1 的投标人数≥送交评委会评审的投标 人数的 1/3 时，则对 D1 进行修正
        return count >= companyQuotePriceList.size() / 3.0;

    }

    /**
     * 计算低于D1的人数
     * @param companyQuotePriceList
     * @param d1
     * @return count
     */
    private int lessD1(List<CompanyQuotePrice> companyQuotePriceList, Double d1) {
        int count = 0;
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();
            count =  companyValue < d1 ? count+1:count+0;
        }
        return count;
    }

    /**
     * 计算C2
     * @param companyQuotePriceList
     * @param c1
     * @param alertPriceLimit
     * @return C2
     */
    private Double calCulateC2(List<CompanyQuotePrice> companyQuotePriceList, Double c1, Double alertPriceLimit) {
        Double c2 =0.00;
        Double sum = 0.00;
        // 保留两位小数
        Double compareValue = c1 * (1+0.03);
        int count =0;
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();
            // 投标报价不高
            //于投标限价且不低于成本预警价的，以投标报价计算 C1；
            if (alertPriceLimit< companyValue && companyValue < compareValue) {
                sum+=companyValue;
                count+=1;
            } else if (alertPriceLimit > companyValue ) {
                //投标报价低于成本
                //预警价的，则以成本预警价计算 C1
                sum+=alertPriceLimit;
                count+=1;
            }
        }
        c2 = sum/count;
        return c2;
    }

    /**
     * 计算C1的价格
     * @param companyQuotePriceList
     * @param priceLimit 投标限价
     * @param alertPriceLimit 预警价
     * @return C1
     */
    private Double calCulateC1(List<CompanyQuotePrice> companyQuotePriceList, Double priceLimit, Double alertPriceLimit) {
        Double c1 =0.00;
        Double sum = 0.00;
        int count =0;
        Iterator<CompanyQuotePrice> it = companyQuotePriceList.iterator();
       while (it.hasNext()) {
           CompanyQuotePrice companyQuotePrice =   it.next();
           Double companyValue =companyQuotePrice.getCompanyValue();
           // 投标报价不高
           //于投标限价且不低于成本预警价的，以投标报价计算 C1；
           if (alertPriceLimit< companyValue && companyValue < priceLimit) {
               sum+=companyValue;
               count+=1;
           } else if (alertPriceLimit > companyValue ) {
               //投标报价低于成本
               //预警价的，则以成本预警价计算 C1
               sum+=alertPriceLimit;
               count+=1;
           }
       }

        c1 = sum/count;
        return c1;
    }

    /**
     * 计算应该前五名有效报价
     * @param shouldQuotePrice D2
     * @param quotePrice 提交的报价
     * @return 前五名有效报价
     */
    @Override
    public List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice  shouldQuotePrice,QuotePrice quotePrice) {
        List<ProQuotePrice> proQuotePrices = new ArrayList<>(5);
        // 对提交的数据进行比较，小于D2的为无效报价
        List<CompanyQuotePrice> usableValues = new ArrayList<>();
        Iterator<CompanyQuotePrice> it = quotePrice.getCompanyQuotePriceList().iterator();
        while (it.hasNext()) {
            CompanyQuotePrice companyQuotePrice =   it.next();
            Double companyValue =companyQuotePrice.getCompanyValue();
            if (companyValue  > shouldQuotePrice.getShouldPrice())
                usableValues.add(companyQuotePrice);
        }
        // 对有效的价格进行排序
        Collections.sort(usableValues);
        // 返回前五的价格
        for(int i=0;i<5;i++) {
            ProQuotePrice proQuotePrice = new ProQuotePrice();
            proQuotePrice.setQuoteId(usableValues.get(i).getQuoteId());
            proQuotePrice.setQuotePriceId(quotePrice.getQuotePriceId());
            proQuotePrices.add(proQuotePrice);
        }
        return proQuotePrices;
    }
}
