package com.projectName.www.po;

import java.util.Date;

/**
 * 商户实体类，包含商户的基本信息
 */
public class Merchant {
    private int id;
    private String merchantName;
    private String merchantAddress;
    private String merchantPhoneNumber;
    private String keywords;
    private String merchantState;
    private String merchantApplyState;
    private Double merchantSales;
    private Date createTime;

    // Getters 和 Setters 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantPhoneNumber() {
        return merchantPhoneNumber;
    }

    public void setMerchantPhoneNumber(String merchantPhoneNumber) {
        this.merchantPhoneNumber = merchantPhoneNumber;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMerchantState() {
        return merchantState;
    }

    public void setMerchantState(String merchantState) {
        this.merchantState = merchantState;
    }

    public String getMerchantApplyState() {
        return merchantApplyState;
    }

    public void setMerchantApplyState(String merchantApplyState) {
        this.merchantApplyState = merchantApplyState;
    }

    public Double getMerchantSales() {
        return merchantSales;
    }

    public void setMerchantSales(Double merchantSales) {
        this.merchantSales = merchantSales;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // 无参构造函数
    public Merchant() {
    }

    // 有参构造函数
    public Merchant(int id, String merchantName, String merchantAddress, String merchantPhoneNumber, String keywords, String merchantState, String merchantApplyState, Double merchantSales, Date createTime) {
        this.id = id;
        this.merchantName = merchantName;
        this.merchantAddress = merchantAddress;
        this.merchantPhoneNumber = merchantPhoneNumber;
        this.keywords = keywords;
        this.merchantState = merchantState;
        this.merchantApplyState = merchantApplyState;
        this.merchantSales = merchantSales;
        this.createTime = createTime;
    }
}