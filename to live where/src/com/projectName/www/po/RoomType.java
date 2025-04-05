package com.projectName.www.po;

import java.util.Date;

/**
 * 房型实体类，包含房型的基本信息
 */
public class RoomType {
    private String roomTypeId;
    private String merchantId;
    private String bedType;
    private double price;
    private String keywords;
    private int stock;
    private int alreadySale;
    private String description;
    private Date createTime;

    // Getters 和 Setters 方法
    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAlreadySale() {
        return alreadySale;
    }

    public void setAlreadySale(int alreadySale) {
        this.alreadySale = alreadySale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // 无参构造函数
    public RoomType() {
    }

    // 有参构造函数
    public RoomType(String roomTypeId, String merchantId, String bedType, double price, String keywords, int stock, int alreadySale, String description, Date createTime) {
        this.roomTypeId = roomTypeId;
        this.merchantId = merchantId;
        this.bedType = bedType;
        this.price = price;
        this.keywords = keywords;
        this.stock = stock;
        this.alreadySale = alreadySale;
        this.description = description;
        this.createTime = createTime;
    }
}