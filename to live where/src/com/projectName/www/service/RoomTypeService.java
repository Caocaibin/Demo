package com.projectName.www.service;

import com.projectName.www.dao.RoomTypeDao;
import com.projectName.www.po.RoomType;

import java.util.Date;
import java.util.List;

/**
 * 房型业务逻辑处理类，处理房型相关的业务逻辑
 */
public class RoomTypeService {
    private RoomTypeDao roomTypeDao = new RoomTypeDao();

    /**
     * 根据商户 ID 获取房型列表
     * @param merchantId 商户 ID
     * @return 房型列表
     */
    public List<RoomType> getRoomTypesByMerchantId(String merchantId) {
        return roomTypeDao.findRoomTypesByMerchantId(merchantId);
    }

    /**
     * 添加房型
     * @param merchantId 商户 ID
     * @param bedType 床型
     * @param price 价格
     * @param keywords 关键词
     * @param stock 库存
     * @param description 描述
     * @return 如果添加成功返回 true，否则返回 false
     */
    public boolean addRoomType(String merchantId, String bedType, double price, String keywords, int stock, String description) {
        RoomType roomType = new RoomType();
        roomType.setMerchantId(merchantId);
        roomType.setBedType(bedType);
        roomType.setPrice(price);
        roomType.setKeywords(keywords);
        roomType.setStock(stock);
        roomType.setDescription(description);
        roomType.setCreateTime(new Date());
        return roomTypeDao.addRoomType(roomType);
    }

    /**
     * 修改房型信息
     * @param roomTypeId 房型 ID
     * @param bedType 床型
     * @param price 价格
     * @param keywords 关键词
     * @param stock 库存
     * @param description 描述
     * @return 如果修改成功返回 true，否则返回 false
     */
    public boolean updateRoomType(String roomTypeId, String bedType, double price, String keywords, int stock, String description) {
        RoomType roomType = roomTypeDao.findRoomTypesByMerchantId(null).stream()
                .filter(rt -> rt.getRoomTypeId().equals(roomTypeId))
                .findFirst()
                .orElse(null);
        if (roomType != null) {
            roomType.setBedType(bedType);
            roomType.setPrice(price);
            roomType.setKeywords(keywords);
            roomType.setStock(stock);
            roomType.setDescription(description);
            return roomTypeDao.updateRoomType(roomType);
        }
        return false;
    }

    /**
     * 删除房型
     * @param roomTypeId 房型 ID
     * @return 如果删除成功返回 true，否则返回 false
     */
    public boolean deleteRoomType(String roomTypeId) {
        return roomTypeDao.deleteRoomType(roomTypeId);
    }
}