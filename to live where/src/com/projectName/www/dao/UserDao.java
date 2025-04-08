package com.projectName.www.dao;

import com.projectName.www.po.User;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户数据访问对象类，处理用户相关的数据库操作
 */
public class UserDao {

    /**
     * 根据用户名和密码查找用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 找到返回用户对象，未找到返回 null
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
                    // 其他属性设置
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册新用户
     *
     * @param user 用户对象
     * @return 注册成功返回 true，失败返回 false
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (username, password, realName, phone, balance, createTime) VALUES (?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRealName());
            pstmt.setString(4, user.getPhone());
            pstmt.setDouble(5, user.getBalance());
            pstmt.setTimestamp(6, new java.sql.Timestamp(user.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改成功返回 true，失败返回 false
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

    /**
     * 根据 ID 查找用户
     *
     * @param id 用户 ID
     * @return 找到返回用户对象，未找到返回 null
     */
    public User findUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                // 其他属性设置
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
