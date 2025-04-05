package com.projectName.www.dao;

import com.projectName.www.po.User;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户数据访问对象，负责与数据库中的用户表进行交互
 * @author YourName
 */
public class UserDao {
    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象，如果未找到则返回 null
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRealName(rs.getString("realName"));
                    user.setPhone(rs.getString("phone"));
                    user.setBalance(rs.getDouble("balance"));
                    user.setRole(rs.getInt("role"));
                    user.setCreateTime(rs.getTimestamp("createTime"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("查询用户时发生 SQL 异常: " + e.getMessage());
        }
        return null;
    }

    /**
     * 注册新用户
     * @param user 用户对象
     * @return 如果注册成功返回 true，否则返回 false
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (username, password, realName, phone, balance, role, createTime) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRealName());
            pstmt.setString(4, user.getPhone());
            pstmt.setDouble(5, user.getBalance());
            pstmt.setInt(6, user.getRole());
            pstmt.setTimestamp(7, new java.sql.Timestamp(user.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("注册用户时发生 SQL 异常: " + e.getMessage());
        }
        return false;
    }

    /**
     * 修改用户信息
     * @param user 用户对象
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET realName = ?, phone = ?, balance = ? WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getRealName());
            pstmt.setString(2, user.getPhone());
            pstmt.setDouble(3, user.getBalance());
            pstmt.setInt(4, user.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("修改用户信息时发生 SQL 异常: " + e.getMessage());
        }
        return false;
    }
}