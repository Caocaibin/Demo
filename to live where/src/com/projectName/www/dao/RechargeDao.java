package com.projectName.www.dao;

import com.projectName.www.po.Recharge;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 充值数据访问对象，负责与数据库中的充值表进行交互
 */
public class RechargeDao {
    /**
     * 创建充值记录
     * @param recharge 充值对象
     * @return 如果创建成功返回 true，否则返回 false
     */
    public boolean createRecharge(Recharge recharge) {
        String sql = "INSERT INTO recharge (customerId, amount, status, createTime) VALUES (?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recharge.getCustomerId());
            pstmt.setDouble(2, recharge.getAmount());
            pstmt.setString(3, recharge.getStatus());
            pstmt.setTimestamp(4, new java.sql.Timestamp(recharge.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取所有待审核的充值记录
     * @return 充值记录列表
     */
    public List<Recharge> getPendingRecharges() {
        List<Recharge> recharges = new ArrayList<>();
        String sql = "SELECT * FROM recharge WHERE status = 'pending'";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Recharge recharge = new Recharge();
                recharge.setRechargeId(rs.getInt("rechargeId"));
                recharge.setCustomerId(rs.getString("customerId"));
                recharge.setAmount(rs.getDouble("amount"));
                recharge.setStatus(rs.getString("status"));
                recharge.setCreateTime(rs.getTimestamp("createTime"));
                recharges.add(recharge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recharges;
    }

    /**
     * 修改充值记录状态
     * @param rechargeId 充值记录 ID
     * @param status 充值记录状态
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateRechargeStatus(int rechargeId, String status) {
        String sql = "UPDATE recharge SET status = ? WHERE rechargeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, rechargeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}