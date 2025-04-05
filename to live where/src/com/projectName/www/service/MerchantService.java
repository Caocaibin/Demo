package com.projectName.www.service;

import com.projectName.www.dao.MerchantDao;
import com.projectName.www.po.Merchant;
import com.projectName.www.constant.RoleConstants;

import java.util.Date;
import java.util.List;

/**
 * 商户业务逻辑处理类，处理商户相关的业务逻辑
 */
public class MerchantService {
    private MerchantDao merchantDao = new MerchantDao();

    /**
     * 商户登录
     * @param username 用户名
     * @param password 密码
     * @return 商户对象，如果登录失败返回 null
     */
    public Merchant login(String username, String password) {
        // 这里需要根据实际情况实现商户登录逻辑
        return null;
    }

    /**
     * 商户注册
     * @param merchantName 商户名称
     * @param merchantAddress 商户地址
     * @param merchantPhoneNumber 商户联系电话
     * @param keywords 关键词
     * @param merchantState 商户状态
     * @param merchantApplyState 商户申请状态
     * @return 如果注册成功返回 true，否则返回 false
     */
    public boolean register(String merchantName, String merchantAddress, String merchantPhoneNumber, String keywords, String merchantState, String merchantApplyState) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantName);
        merchant.setMerchantAddress(merchantAddress);
        merchant.setMerchantPhoneNumber(merchantPhoneNumber);
        merchant.setKeywords(keywords);
        merchant.setMerchantState(merchantState);
        merchant.setMerchantApplyState(merchantApplyState);
        merchant.setMerchantSales(0.0);
        merchant.setCreateTime(new Date());
        return merchantDao.registerMerchant(merchant);
    }

    /**
     * 修改商户信息
     * @param merchantId 商户 ID
     * @param merchantName 商户名称
     * @param merchantAddress 商户地址
     * @param merchantPhoneNumber 商户联系电话
     * @param keywords 关键词
     * @param merchantState 商户状态
     * @param merchantApplyState 商户申请状态
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateMerchantInfo(int merchantId, String merchantName, String merchantAddress, String merchantPhoneNumber, String keywords, String merchantState, String merchantApplyState) {
        Merchant merchant = merchantDao.findMerchantById(merchantId);
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
     * 获取所有商户信息
     * @return 商户列表
     */
    public List<Merchant> getAllMerchants() {
        return merchantDao.getAllMerchants();
    }

    /**
     * 根据商户 ID 查询商户信息
     * @param merchantId 商户 ID
     * @return 商户对象，如果未找到则返回 null
     */
    public Merchant findMerchantById(int merchantId) {
        return merchantDao.findMerchantById(merchantId);
    }
}