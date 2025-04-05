package com.projectName.www.service;

import com.projectName.www.dao.UserDao;
import com.projectName.www.po.User;
import com.projectName.www.constant.RoleConstants;

import java.util.Date;

/**
 * 用户业务逻辑处理类，处理用户相关的业务逻辑
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象，如果登录失败返回 null
     */
    public User login(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param realName 真实姓名
     * @param phone 手机号码
     * @return 如果注册成功返回 true，否则返回 false
     */
    public boolean register(String username, String password, String realName, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setPhone(phone);
        user.setBalance(0);
        user.setRole(RoleConstants.ROLE_CUSTOMER);
        user.setCreateTime(new Date());
        return userDao.registerUser(user);
    }

    /**
     * 修改用户信息
     * @param userId 用户 ID
     * @param realName 真实姓名
     * @param phone 手机号码
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateUserInfo(int userId, String realName, String phone) {
        User user = userDao.findUserByUsernameAndPassword(null, null); // 这里需要根据实际情况修改查询方式
        if (user != null) {
            user.setRealName(realName);
            user.setPhone(phone);
            return userDao.updateUser(user);
        }
        return false;
    }

    /**
     * 用户充值
     * @param userId 用户 ID
     * @param amount 充值金额
     * @return 如果充值成功返回 true，否则返回 false
     */
    public boolean recharge(int userId, double amount) {
        User user = userDao.findUserByUsernameAndPassword(null, null); // 这里需要根据实际情况修改查询方式
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            return userDao.updateUser(user);
        }
        return false;
    }
}