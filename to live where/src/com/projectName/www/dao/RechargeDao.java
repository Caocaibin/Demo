package com.projectName.www.dao;

import com.projectName.www.po.Recharge;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 充值数据访问对象类，处理充值相关的数据库操作
 */
public class RechargeDao {

    /**
     * 根据充值记录 ID 查找充值记录
     * @param rechargeId 充值记录 ID
     * @return 找到返回充值记录对象，未找到返回 null
     */
    public Recharge findRechargeById(int rechargeId) {
        String sql = "SELECT * FROM recharge WHERE rechargeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rechargeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Recharge recharge = new Recharge();
                    recharge.setRechargeId(rs.getInt("rechargeId"));
                    // 其他属性设置
                    return recharge;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean agreeRecharge(int rechargeId) {
        String sql = "UPDATE recharge SET status = ? WHERE rechargeId = ?";
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "已通过");
            pstmt.setInt(2, rechargeId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("审核成功");
                return true;
            }
            else {
                System.out.println("审核失败");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}