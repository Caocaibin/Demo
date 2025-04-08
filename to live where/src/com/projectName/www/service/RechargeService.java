package com.projectName.www.service;

import com.projectName.www.dao.RechargeDao;
import com.projectName.www.po.Recharge;

/**
 * 充值服务类，处理充值相关的业务逻辑
 */
public class RechargeService {
    private RechargeDao rechargeDao = new RechargeDao();

    /**
     * 根据充值记录 ID 获取充值记录
     * @param rechargeId 充值记录 ID
     * @return 充值记录对象，如果未找到返回 null
     */
    public Recharge getRechargeById(int rechargeId) {
        // 这里需要实现根据 ID 查询充值记录的逻辑
        return rechargeDao.findRechargeById(rechargeId);
    }
    public boolean approveRecharge(int rechargeId) {
       return rechargeDao.agreeRecharge(rechargeId);
    }
}
