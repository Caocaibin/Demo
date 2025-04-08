package com.projectName.www.service;

import com.projectName.www.dao.ManagerDao;
import com.projectName.www.po.Manager;

public class ManagerService {
    private ManagerDao managerDao = new ManagerDao();

    /**
     * 管理者登录
     * @param username 用户名
     * @param password 密码
     * @return 管理者对象，如果登录失败返回 null
     */
    public Manager login(String username, String password) {
        return managerDao.findManagerByUsernameAndPassword(username, password);
    }

    /**
     * 审核商户入驻申请
     * @param merchantId 商户 ID
     * @return 如果审核成功返回 true，否则返回 false
     */
    public boolean approveMerchant(int merchantId) {
        MerchantService merchantService = new MerchantService();
        return merchantService.approveMerchant(merchantId);
    }

    /**
     * 封禁店铺
     * @param merchantId 商户 ID
     * @return 如果封禁成功返回 true，否则返回 false
     */
    public boolean banMerchant(int merchantId) {
        MerchantService merchantService = new MerchantService();
        return merchantService.approveMerchant(merchantId);
    }

    /**
     * 审核租户充值
     * @param rechargeId 充值记录 ID
     * @return 如果审核成功返回 true，否则返回 false
     */
    public boolean approveRecharge(int rechargeId) {
        RechargeService rechargeService = new RechargeService();
        return rechargeService.approveRecharge(rechargeId);
    }

}
