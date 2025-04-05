package com.projectName.www.po;

import java.util.Date;

/**
 * 充值实体类，包含充值的基本信息
 */
public class Recharge {
    private int rechargeId;
    private String customerId;
    private double amount;
    private String status;
    private Date createTime;

    // Getters 和 Setters 方法
    public int getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(int rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // 无参构造函数
    public Recharge() {
    }

    // 有参构造函数
    public Recharge(int rechargeId, String customerId, double amount, String status, Date createTime) {
        this.rechargeId = rechargeId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.createTime = createTime;
    }
}