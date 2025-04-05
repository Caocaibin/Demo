package com.projectName.www.dao;

import com.projectName.www.po.Order;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单数据访问对象，负责与数据库中的订单表进行交互
 * @author YourName
 */
public class OrderDao {
    /**
     * 根据用户 ID 查询订单
     * @param customerId 用户 ID
     * @return 订单列表
     */
    public List<Order> findOrdersByCustomerId(String customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customerId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setCustomerId(rs.getString("customerId"));
                    order.setMerchantId(rs.getString("merchantId"));
                    order.setRoomTypeId(rs.getString("roomTypeId"));
                    order.setStatus(rs.getString("status"));
                    order.setPrice(rs.getDouble("price"));
                    order.setCreateTime(rs.getTimestamp("createTime"));
                    order.setCheckInTime(rs.getTimestamp("checkInTime"));
                    order.setCheckOutTime(rs.getTimestamp("checkOutTime"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            // 记录日志而不是简单打印堆栈信息，方便后续排查问题
            System.err.println("查询订单时发生 SQL 异常: " + e.getMessage());
        }
        return orders;
    }

    /**
     * 创建订单
     * @param order 订单对象
     * @return 如果创建成功返回 true，否则返回 false
     */
    public boolean createOrder(Order order) {
        String sql = "INSERT INTO orders (customerId, merchantId, roomTypeId, status, price, createTime, checkInTime, checkOutTime) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getCustomerId());
            pstmt.setString(2, order.getMerchantId());
            pstmt.setString(3, order.getRoomTypeId());
            pstmt.setString(4, order.getStatus());
            pstmt.setDouble(5, order.getPrice());
            pstmt.setTimestamp(6, new java.sql.Timestamp(order.getCreateTime().getTime()));
            pstmt.setTimestamp(7, new java.sql.Timestamp(order.getCheckInTime().getTime()));
            pstmt.setTimestamp(8, new java.sql.Timestamp(order.getCheckOutTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("创建订单时发生 SQL 异常: " + e.getMessage());
        }
        return false;
    }

    /**
     * 修改订单状态
     * @param orderId 订单 ID
     * @param status 订单状态
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE orderId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("修改订单状态时发生 SQL 异常: " + e.getMessage());
        }
        return false;
    }

    /**
     * 删除订单
     * @param orderId 订单 ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE orderId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("删除订单时发生 SQL 异常: " + e.getMessage());
        }
        return false;
    }
}