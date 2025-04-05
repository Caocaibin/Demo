package com.projectName.www.po;

import java.util.Date;

/**
 * 订单实体类，包含订单的基本信息
 */
public class Order {
    private int orderId;
    private String customerId;
    private String merchantId;
    private String roomTypeId;
    private String status;
    private double price;
    private Date createTime;
    private Date checkInTime;
    private Date checkOutTime;

    // Getters 和 Setters 方法
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    // 无参构造函数
    public Order() {
    }

    // 有参构造函数
    public Order(int orderId, String customerId, String merchantId, String roomTypeId, String status, double price, Date createTime, Date checkInTime, Date checkOutTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.roomTypeId = roomTypeId;
        this.status = status;
        this.price = price;
        this.createTime = createTime;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }
}