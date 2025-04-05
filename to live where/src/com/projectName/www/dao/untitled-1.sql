-- 用户表
-- 用户表
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL,
                      realName VARCHAR(50),
                      phone VARCHAR(20),
                      balance DOUBLE DEFAULT 0,
                      role INT NOT NULL,
                      createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商户表
CREATE TABLE merchant (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          merchantName VARCHAR(100) NOT NULL,
                          merchantAddress VARCHAR(200),
                          merchantPhoneNumber VARCHAR(20),
                          keywords VARCHAR(200),
                          merchantState VARCHAR(20),
                          merchantApplyState VARCHAR(20),
                          merchantSales DOUBLE DEFAULT 0,
                          createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 房型表
CREATE TABLE room_type (
                           roomTypeId VARCHAR(50) PRIMARY KEY,
                           merchantId VARCHAR(50) NOT NULL,
                           bedType VARCHAR(50),
                           price DOUBLE,
                           keywords VARCHAR(200),
                           stock INT,
                           alreadySale INT DEFAULT 0,
                           description TEXT,
                           createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 订单表
CREATE TABLE orders (
                        orderId INT AUTO_INCREMENT PRIMARY KEY,
                        customerId VARCHAR(50) NOT NULL,
                        merchantId VARCHAR(50) NOT NULL,
                        roomTypeId VARCHAR(50) NOT NULL,
                        status VARCHAR(20),
                        price DOUBLE,
                        createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        checkInTime TIMESTAMP,
                        checkOutTime TIMESTAMP
);

-- 充值表
CREATE TABLE recharge (
                          rechargeId INT AUTO_INCREMENT PRIMARY KEY,
                          customerId VARCHAR(50) NOT NULL,
                          amount DOUBLE,
                          status VARCHAR(20),
                          createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
