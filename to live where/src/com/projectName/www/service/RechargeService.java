package com.projectName.www.service;

import com.projectName.www.dao.RechargeDao;
import com.projectName.www.po.Recharge;

import java.util.Date;
import java.util.List;

/**
 * 充值业务逻辑处理类，处理充值相关的业务逻辑
 */
public class RechargeService {
    private RechargeDao rechargeDao = new RechargeDao();

    /**
     * 创建充值记录
     * @param customerId 用户 ID
     * @param amount 充值金额
     * @return 如果创建成功返回 true，否则返回 false
     */
    public boolean createRecharge(String customerId, double amount) {
        Recharge recharge = new Recharge();
        recharge.setCustomerId(customerId);
        recharge.setAmount(amount);
        recharge.setStatus("pending");
        recharge.setCreateTime(new Date());
        return rechargeDao.createRecharge(recharge);
    }

    /**
     * 获取所有待审核的充值记录
     * @return 充值记录列表
     */
    public List<Recharge> getPendingRecharges() {
        return rechargeDao.getPendingRecharges();
    }

    /**
     * 审核充值记录
     * @param rechargeId 充值记录 ID
     * @param status 审核状态
     * @return 如果审核成功返回 true，否则返回 false
     */
    public boolean approveRecharge(int rechargeId, String status) {
        return rechargeDao.updateRechargeStatus(rechargeId, status);
    }
}