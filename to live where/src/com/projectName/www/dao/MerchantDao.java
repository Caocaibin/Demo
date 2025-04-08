package com.projectName.www.dao;

import com.projectName.www.po.Merchant;
import com.projectName.www.po.RoomType;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户数据访问对象类，处理商户相关的数据库操作
 */
public class MerchantDao {

    /**
     * 根据用户名和密码查找商户
     * @param username 用户名
     * @param password 密码
     * @return 找到返回商户对象，未找到返回 null
     */
    public Merchant findMerchantByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM merchant WHERE username = ? AND password = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Merchant merchant = new Merchant();
                    merchant.setId(rs.getInt("id"));
                    merchant.setUsername(rs.getString("username"));
                    merchant.setPassword(rs.getString("password"));
                    // 其他属性设置
                    return merchant;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册新商户
     * @param merchant 商户对象
     * @return 注册成功返回 true，失败返回 false
     */
    public boolean registerMerchant(Merchant merchant) {
        String sql = "INSERT INTO merchant (username , password, merchantName, merchantAddress, merchantPhoneNumber, keywords, merchantState, merchantApplyState, merchantSales, createTime) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, merchant.getUsername());
            pstmt.setString(2, merchant.getPassword());
            pstmt.setString(3, merchant.getMerchantName());
            pstmt.setString(4, merchant.getMerchantAddress());
            pstmt.setString(5, merchant.getMerchantPhoneNumber());
            pstmt.setString(6, merchant.getKeywords());
            pstmt.setString(7, merchant.getMerchantState());
            pstmt.setString(8, merchant.getMerchantApplyState());
            pstmt.setDouble(9, merchant.getMerchantSales());
            pstmt.setTimestamp(10, new java.sql.Timestamp(merchant.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改商户信息
     * @param merchant 商户对象
     * @return 修改成功返回 true，失败返回 false
     */
    public boolean updateMerchant(Merchant merchant) {
        String sql = "UPDATE merchant SET merchantName = ?, merchantAddress = ?, merchantPhoneNumber = ?, keywords = ?, merchantState = ?, merchantApplyState = ?, merchantSales = ? WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, merchant.getMerchantName());
            pstmt.setString(2, merchant.getMerchantAddress());
            pstmt.setString(3, merchant.getMerchantPhoneNumber());
            pstmt.setString(4, merchant.getKeywords());
            pstmt.setString(5, merchant.getMerchantState());
            pstmt.setString(6, merchant.getMerchantApplyState());
            pstmt.setDouble(7, merchant.getMerchantSales());
            pstmt.setInt(8, merchant.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据 ID 查找商户
     * @param id 商户 ID
     * @return 找到返回商户对象，未找到返回 null
     */
    public Merchant findMerchantById(int id) {
        String sql = "SELECT * FROM merchant WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Merchant merchant = new Merchant();
                    merchant.setId(rs.getInt("id"));
                    // 其他属性设置
                    return merchant;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有商户信息
     * @return 商户列表
     */
    public List<Merchant> getAllMerchants() {
        List<Merchant> merchants = new ArrayList<>();
        String sql = "SELECT * FROM merchant";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setId(rs.getInt("id"));
                // 其他属性设置
                merchants.add(merchant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return merchants;
    }
    public boolean agreeMerchant(int merchantId) {
        String sql = "UPDATE merchant SET merchantApplyState = ? WHERE id = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "已通过");
            pstmt.setInt(2, merchantId);
            return pstmt.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteRoomType(int roomTypeId) {
        String sql = "DELETE FROM room_type WHERE roomTypeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomTypeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<RoomType> getRoomTypesByMerchantId(int merchantId) {
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT * FROM room_type WHERE merchantid = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, merchantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getInt("id"));
                    roomType.setMerchantId(rs.getString("merchantid"));
                    roomType.setBedType(rs.getString("bed_type"));
                    roomType.setPrice(rs.getDouble("price"));
                    roomType.setKeywords(rs.getString("keywords"));
                    roomType.setStock(rs.getInt("stock"));
                    roomType.setDescription(rs.getString("description"));
                    roomTypes.add(roomType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomTypes;
    }
}