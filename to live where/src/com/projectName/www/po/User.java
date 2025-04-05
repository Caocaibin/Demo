package com.projectName.www.po;

import java.util.Date;

/**
 * 用户实体类，包含用户的基本信息
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private double balance;
    private int role;
    private Date createTime;

    // Getters 和 Setters 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // 无参构造函数
    public User() {
    }

    // 有参构造函数
    public User(int id, String username, String password, String realName, String phone, double balance, int role, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
        this.balance = balance;
        this.role = role;
        this.createTime = createTime;
    }
}