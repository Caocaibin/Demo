package com.projectName.www.po;

public class Manager {
    private String username;
    private String password;

    public String getManagerName() {
        return username;
    }

    public void setManagerName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manager() {
    }

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
