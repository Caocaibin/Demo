package com.projectName.www.dao;

import com.projectName.www.po.Manager;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao {
    private static final String DB_URL = "dbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Abc12345";

    public Manager findManagerByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM manager WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Manager manager = new Manager();
                    manager.setManagerName(rs.getString("username"));
                    manager.setPassword(rs.getString("password"));
                    return manager;
                }
            }
        } catch (SQLException e) {
            System.err.println("根据用户名和密码查询管理者时发生 SQL 异常: " + e.getMessage());
        }
        return null;
    }

}
