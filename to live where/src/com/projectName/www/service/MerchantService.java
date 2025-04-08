package com.projectName.www.service;

import com.projectName.www.dao.MerchantDao;
import com.projectName.www.po.Merchant;
import com.projectName.www.po.RoomType;
import com.projectName.www.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 商户服务类，处理商户相关的业务逻辑
 */
public class MerchantService {
    private MerchantDao merchantDao = new MerchantDao();

    /**
     * 商户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回商户对象，失败返回 null
     */
    public Merchant login(String username, String password) {
        return merchantDao.findMerchantByUsernameAndPassword(username, password);
    }

    /**
     * 商户注册
     * @param merchantName 商户名称
     * @param merchantAddress 商户地址
     * @param merchantPhoneNumber 商户联系电话
     * @param keywords 关键词
     * @param merchantState 商户状态
     * @param merchantApplyState 商户申请状态
     * @param merchantSales 商户营业额
     * @param createTime 创建时间
     * @return 注册成功返回 true，失败返回 false
     */
    public boolean register(String merchantUsername, String merchantPassword, String merchantName, String merchantAddress, String merchantPhoneNumber,
                            String keywords, String merchantState, String merchantApplyState,
                            double merchantSales, Date createTime) {
        Merchant merchant = new Merchant();
        merchant.setUsername(merchantUsername);
        merchant.setPassword(merchantPassword);
        merchant.setMerchantName(merchantName);
        merchant.setMerchantAddress(merchantAddress);
        merchant.setMerchantPhoneNumber(merchantPhoneNumber);
        merchant.setKeywords(keywords);
        merchant.setMerchantState(merchantState);
        merchant.setMerchantApplyState(merchantApplyState);
        merchant.setMerchantSales(merchantSales);
        merchant.setCreateTime(createTime);
        return merchantDao.registerMerchant(merchant);
    }

    /**
     * 修改商户信息
     * @param id 商户 ID
     * @param merchantName 商户名称
     * @param merchantAddress 商户地址
     * @param merchantPhoneNumber 商户联系电话
     * @param keywords 关键词
     * @param merchantState 商户状态
     * @param merchantApplyState 商户申请状态
     * @return 修改成功返回 true，失败返回 false
     */
    public boolean updateMerchantInfo(int id, String merchantName, String merchantAddress,
                                      String merchantPhoneNumber, String keywords, String merchantState,
                                      String merchantApplyState) {
        Merchant merchant = merchantDao.findMerchantById(id);
        if (merchant != null) {
            merchant.setMerchantName(merchantName);
            merchant.setMerchantAddress(merchantAddress);
            merchant.setMerchantPhoneNumber(merchantPhoneNumber);
            merchant.setKeywords(keywords);
            merchant.setMerchantState(merchantState);
            merchant.setMerchantApplyState(merchantApplyState);
            return merchantDao.updateMerchant(merchant);
        }
        return false;
    }

    /**
     * 根据 ID 查找商户
     * @param id 商户 ID
     * @return 找到返回商户对象，未找到返回 null
     */
    public Merchant findMerchantById(int id) {
        return merchantDao.findMerchantById(id);
    }

    /**
     * 获取所有商户信息
     * @return 商户列表
     */
    public List<Merchant> getAllMerchants() {
        return merchantDao.getAllMerchants();
    }
    public boolean approveMerchant(int merchantId) {
        return merchantDao.agreeMerchant(merchantId);
    }
    public boolean addRoomType(int merchantId, String bedType, double price, String keywords, int stock, String description) {
        String sql = "INSERT INTO room_type (merchantid, bedtype, price, keywords, stock, description) VALUES (?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, merchantId);
            pstmt.setString(2, bedType);
            pstmt.setDouble(3, price);
            pstmt.setString(4, keywords);
            pstmt.setInt(5, stock);
            pstmt.setString(6, description);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateRoomType(int roomTypeId, String bedType, double price, String keywords, int stock, String description) {
        String sql = "UPDATE room_type SET bedtype = ?, price = ?, keywords = ?, stock = ?, description = ? WHERE room_type.roomTypeId = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bedType);
            pstmt.setDouble(2, price);
            pstmt.setString(3, keywords);
            pstmt.setInt(4, stock);
            pstmt.setString(5, description);
            pstmt.setInt(6, roomTypeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteRoomType(int roomTypeId) {
        return merchantDao.deleteRoomType(roomTypeId);
    }
    public List<RoomType> getRoomTypesByMerchantId(int merchantId) {
        return merchantDao.getRoomTypesByMerchantId(merchantId);
    }
}



