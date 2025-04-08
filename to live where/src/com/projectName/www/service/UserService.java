package com.projectName.www.service;

import com.projectName.www.dao.UserDao;
import com.projectName.www.po.User;

import java.util.Date;

/**
 * 用户服务类，处理用户相关的业务逻辑
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户对象，失败返回 null
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
     * @return 注册成功返回 true，失败返回 false
     */
    public boolean register(String username, String password, String realName, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setPhone(phone);
        user.setBalance(0);
        user.setCreateTime(new Date());
        return userDao.registerUser(user);
    }

    /**
     * 修改用户信息
     * @param userId 用户 ID
     * @param realName 真实姓名
     * @param phone 手机号码
     * @return 修改成功返回 true，失败返回 false
     */
    public boolean updateUserInfo(int userId, String realName, String phone) {
        User user = userDao.findUserById(userId); // 修改查询方式为使用用户 ID
        if (user != null) {
            user.setRealName(realName);
            user.setPhone(phone);
            return userDao.updateUser(user);
        }
        return false;
    }

    /**
     * 用户充值
     * @param id 用户 ID
     * @param amount 充值金额
     * @return 充值成功返回 true，失败返回 false
     */
    public boolean recharge(int id, double amount) {
        User user = userDao.findUserById(id);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            return userDao.updateUser(user);
        }
        return false;
    }

    /**
     * 根据 ID 查找用户
     * @param id 用户 ID
     * @return 找到返回用户对象，未找到返回 null
     */
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }
}