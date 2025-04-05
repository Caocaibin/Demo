package com.projectName.www.service;

import com.projectName.www.dao.OrderDao;
import com.projectName.www.po.Order;

import java.util.Date;
import java.util.List;

/**
 * 订单业务逻辑处理类，处理订单相关的业务逻辑
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();

    /**
     * 根据用户 ID 获取订单列表
     * @param customerId 用户 ID
     * @return 订单列表
     */
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderDao.findOrdersByCustomerId(customerId);
    }

    /**
     * 创建订单
     * @param customerId 用户 ID
     * @param merchantId 商户 ID
     * @param roomTypeId 房型 ID
     * @param price 价格
     * @param checkInTime 入住时间
     * @param checkOutTime 退房时间
     * @return 如果创建成功返回 true，否则返回 false
     */
    public boolean createOrder(String customerId, String merchantId, String roomTypeId, double price, Date checkInTime, Date checkOutTime) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setMerchantId(merchantId);
        order.setRoomTypeId(roomTypeId);
        order.setStatus("pending");
        order.setPrice(price);
        order.setCreateTime(new Date());
        order.setCheckInTime(checkInTime);
        order.setCheckOutTime(checkOutTime);
        return orderDao.createOrder(order);
    }

    /**
     * 修改订单状态
     * @param orderId 订单 ID
     * @param status 订单状态
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateOrderStatus(int orderId, String status) {
        return orderDao.updateOrderStatus(orderId, status);
    }

    /**
     * 删除订单
     * @param orderId 订单 ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteOrder(int orderId) {
        return orderDao.deleteOrder(orderId);
    }
}