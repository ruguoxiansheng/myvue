package com.example.myvue.service;

import com.example.myvue.calinterface.CalProQuotePrice;
import com.example.myvue.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
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
        //5.1、计算最低成本价 D1 D1=K×B×K1+C2×K2
        Double D1 = calCulateD1();
        //5.2、对 D1 进行修正，计算修正后的最低成本价 D2

      // 这里先把所有的数据求平均吧
        //  这里先假设返回前五个数值
        double sum = 0.00;
        List<CompanyQuotePrice> companyQuotePrices = quotePrice.getCompanyQuotePriceList();
        for (int i =0 ;i < companyQuotePrices.size();i++) {
            sum+=companyQuotePrices.get(i).getCompanyValue();
        }
        ShouldQuotePrice shouldQuotePrice = new ShouldQuotePrice();
        shouldQuotePrice.setProjectNumber(quotePrice.getProjectNumber());
        shouldQuotePrice.setShouldPrice(sum/companyQuotePrices.size());
        shouldQuotePrice.setConsumerId(quotePrice.getConsumerId());
        return shouldQuotePrice;
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

    public List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice  shouldQuotePrice,QuotePrice quotePrice) {
        // 先返回前五个数
        List<CompanyQuotePrice> companyQuotePrices = quotePrice.getCompanyQuotePriceList();
        List<ProQuotePrice> proQuotePrices = new ArrayList<>(5);
        for(int i=0;i<5;i++) {
            ProQuotePrice proQuotePrice = new ProQuotePrice();
            proQuotePrice.setQuoteId(companyQuotePrices.get(i).getQuoteId());
            proQuotePrice.setQuotePriceId(quotePrice.getQuotePriceId());
            proQuotePrices.add(proQuotePrice);
        }
        return proQuotePrices;
    }
}
