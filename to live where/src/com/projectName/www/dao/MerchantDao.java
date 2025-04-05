package com.projectName.www.dao;

import com.projectName.www.po.Merchant;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户数据访问对象，负责与数据库中的商户表进行交互
 */
public class MerchantDao {
    /**
     * 根据 ID 查询商户
     * @param id 商户 ID
     * @return 商户对象，如果未找到则返回 null
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
                    merchant.setMerchantName(rs.getString("merchantName"));
                    merchant.setMerchantAddress(rs.getString("merchantAddress"));
                    merchant.setMerchantPhoneNumber(rs.getString("merchantPhoneNumber"));
                    merchant.setKeywords(rs.getString("keywords"));
                    merchant.setMerchantState(rs.getString("merchantState"));
                    merchant.setMerchantApplyState(rs.getString("merchantApplyState"));
                    merchant.setMerchantSales(rs.getDouble("merchantSales"));
                    merchant.setCreateTime(rs.getTimestamp("createTime"));
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
     * @return 如果注册成功返回 true，否则返回 false
     */
    public boolean registerMerchant(Merchant merchant) {
        String sql = "INSERT INTO merchant (merchantName, merchantAddress, merchantPhoneNumber, keywords, merchantState, merchantApplyState, merchantSales, createTime) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, merchant.getMerchantName());
            pstmt.setString(2, merchant.getMerchantAddress());
            pstmt.setString(3, merchant.getMerchantPhoneNumber());
            pstmt.setString(4, merchant.getKeywords());
            pstmt.setString(5, merchant.getMerchantState());
            pstmt.setString(6, merchant.getMerchantApplyState());
            pstmt.setDouble(7, merchant.getMerchantSales());
            pstmt.setTimestamp(8, new java.sql.Timestamp(merchant.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改商户信息
     * @param merchant 商户对象
     * @return 如果修改成功返回 true，否则返回 false
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
     * 获取所有商户信息
     * @return 商户列表
     */
    public List<Merchant> getAllMerchants() {
        List<Merchant> merchants = new ArrayList<>();
        String sql = "SELECT * FROM merchant ORDER BY merchantSales DESC, createTime DESC";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setId(rs.getInt("id"));
                merchant.setMerchantName(rs.getString("merchantName"));
                merchant.setMerchantAddress(rs.getString("merchantAddress"));
                merchant.setMerchantPhoneNumber(rs.getString("merchantPhoneNumber"));
                merchant.setKeywords(rs.getString("keywords"));
                merchant.setMerchantState(rs.getString("merchantState"));
                merchant.setMerchantApplyState(rs.getString("merchantApplyState"));
                merchant.setMerchantSales(rs.getDouble("merchantSales"));
                merchant.setCreateTime(rs.getTimestamp("createTime"));
                merchants.add(merchant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return merchants;
    }
}