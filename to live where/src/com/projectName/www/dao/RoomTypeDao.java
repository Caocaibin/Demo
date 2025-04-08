package com.projectName.www.dao;

import com.projectName.www.po.RoomType;
import com.projectName.www.util.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao {
    /**
     * 根据商户 ID 查询房型
     * @param merchantId 商户 ID
     * @return 房型列表
     */
    public List<RoomType> findRoomTypesByMerchantId(String merchantId) {
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT * FROM room_type WHERE merchantId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, merchantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setRoomTypeId(rs.getString("roomTypeId"));
                    roomType.setMerchantId(rs.getString("merchantId"));
                    roomType.setBedType(rs.getString("bedType"));
                    roomType.setPrice(rs.getDouble("price"));
                    roomType.setKeywords(rs.getString("keywords"));
                    roomType.setStock(rs.getInt("stock"));
                    roomType.setAlreadySale(rs.getInt("alreadySale"));
                    roomType.setDescription(rs.getString("description"));
                    roomType.setCreateTime(rs.getTimestamp("createTime"));
                    roomTypes.add(roomType);
                }
            }
        } catch (SQLException e) {
            System.err.println("根据商户 ID 查询房型时发生 SQL 异常，商户 ID: " + merchantId + ", 异常信息: " + e.getMessage());
        }
        return roomTypes;
    }

    /**
     * 添加房型
     * @param roomType 房型对象
     * @return 如果添加成功返回 true，否则返回 false
     */
    public boolean addRoomType(RoomType roomType) {
        String sql = "INSERT INTO room_type (roomTypeId, merchantId, bedType, price, keywords, stock, alreadySale, description, createTime) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomType.getRoomTypeId());
            pstmt.setString(2, roomType.getMerchantId());
            pstmt.setString(3, roomType.getBedType());
            pstmt.setDouble(4, roomType.getPrice());
            pstmt.setString(5, roomType.getKeywords());
            pstmt.setInt(6, roomType.getStock());
            pstmt.setInt(7, roomType.getAlreadySale());
            pstmt.setString(8, roomType.getDescription());
            pstmt.setTimestamp(9, new java.sql.Timestamp(roomType.getCreateTime().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("添加房型时发生 SQL 异常，房型 ID: " + roomType.getRoomTypeId() + ", 异常信息: " + e.getMessage());
        }
        return false;
    }

    /**
     * 修改房型信息
     * @param roomType 房型对象
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateRoomType(RoomType roomType) {
        String sql = "UPDATE room_type SET bedType = ?, price = ?, keywords = ?, stock = ?, alreadySale = ?, description = ? WHERE roomTypeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomType.getBedType());
            pstmt.setDouble(2, roomType.getPrice());
            pstmt.setString(3, roomType.getKeywords());
            pstmt.setInt(4, roomType.getStock());
            pstmt.setInt(5, roomType.getAlreadySale());
            pstmt.setString(6, roomType.getDescription());
            pstmt.setString(7, roomType.getRoomTypeId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("修改房型信息时发生 SQL 异常，房型 ID: " + roomType.getRoomTypeId() + ", 异常信息: " + e.getMessage());
        }
        return false;
    }

    /**
     * 删除房型
     * @param roomTypeId 房型 ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteRoomType(String roomTypeId) {
        String sql = "DELETE FROM room_type WHERE roomTypeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomTypeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("删除房型时发生 SQL 异常，房型 ID: " + roomTypeId + ", 异常信息: " + e.getMessage());
        }
        return false;
    }
}