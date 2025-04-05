package com.projectName.www.servlet;

import com.projectName.www.service.UserService;
import com.projectName.www.service.MerchantService;
import com.projectName.www.service.OrderService;
import com.projectName.www.service.RoomTypeService;
import com.projectName.www.service.RechargeService;
import com.projectName.www.po.User;
import com.projectName.www.po.Merchant;
import com.projectName.www.po.Order;
import com.projectName.www.po.RoomType;
import com.projectName.www.po.Recharge;
import com.projectName.www.constant.RoleConstants;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 主交互类，处理用户输入和业务逻辑调用
 */
public class MainServlet {
    private UserService userService = new UserService();
    private MerchantService merchantService = new MerchantService();
    private OrderService orderService = new OrderService();
    private RoomTypeService roomTypeService = new RoomTypeService();
    private RechargeService rechargeService = new RechargeService();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainServlet mainServlet = new MainServlet();
        mainServlet.showMainMenu();
    }

    /**
     * 显示主菜单
     */
    public void showMainMenu() {
        System.out.println("====《住哪儿》===");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("请输入数字来选择您的操作");
        int choose = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        switch (choose) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            default:
                System.out.println("操作有误，您输入的数字有误或不是数字");
                showMainMenu();
        }
    }

    /**
     * 用户登录
     */
    public void login() {
        System.out.println("请选择您的的登录身份");
        System.out.println("1.用户");
        System.out.println("2.商户");
        System.out.println("3.管理员");
        int role = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        System.out.println("请输入您的账号");
        String username = scanner.nextLine();
        System.out.println("请输入您的密码");
        String password = scanner.nextLine();
        switch (role) {
            case 1:
                User user = userService.login(username, password);
                if (user != null) {
                    showCustomerMenu(user);
                } else {
                    System.out.println("用户名或密码错误，请重新输入");
                    login();
                }
                break;
            case 2:
                Merchant merchant = merchantService.login(username, password);
                if (merchant != null) {
                    showMerchantMenu(merchant);
                } else {
                    System.out.println("用户名或密码错误，请重新输入");
                    login();
                }
                break;
            case 3:
                // 管理员登录逻辑
                break;
            default:
                System.out.println("操作有误，您输入的数字有误或不是数字");
                login();
        }
    }

    /**
     * 用户注册
     */
    public void register() {
        System.out.println("1.用户注册");
        System.out.println("2.商户注册");
        int role = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        switch (role) {
            case 1:
                System.out.println("请输入用户名");
                String username = scanner.nextLine();
                System.out.println("请输入密码");
                String password = scanner.nextLine();
                System.out.println("请输入真实姓名");
                String realName = scanner.nextLine();
                System.out.println("请输入手机号码");
                String phone = scanner.nextLine();
                if (userService.register(username, password, realName, phone)) {
                    System.out.println("注册成功，请登录");
                    login();
                } else {
                    System.out.println("注册失败，请重试");
                    register();
                }
                break;
            case 2:
                System.out.println("请输入商户名称");
                String merchantName = scanner.nextLine();
                System.out.println("请输入商户地址");
                String merchantAddress = scanner.nextLine();
                System.out.println("请输入商户联系电话");
                String merchantPhoneNumber = scanner.nextLine();
                System.out.println("请输入关键词");
                String keywords = scanner.nextLine();
                System.out.println("请输入商户状态");
                String merchantState = scanner.nextLine();
                System.out.println("请输入商户申请状态");
                String merchantApplyState = scanner.nextLine();
                if (merchantService.register(merchantName, merchantAddress, merchantPhoneNumber, keywords, merchantState, merchantApplyState)) {
                    System.out.println("注册成功，请登录");
                    login();
                } else {
                    System.out.println("注册失败，请重试");
                    register();
                }
                break;
            default:
                System.out.println("操作有误，您输入的数字有误或不是数字");
                register();
        }
    }

    /**
     * 显示客户端菜单
     * @param user 用户对象
     */
    public void showCustomerMenu(User user) {
        System.out.println("欢迎，" + user.getUsername());
        System.out.println("1.修改个人信息");
        System.out.println("2.查看余额");
        System.out.println("3.充值");
        System.out.println("4.浏览店铺");
        System.out.println("5.查看历史订单");
        System.out.println("6.订房");
        System.out.println("7.退订");
        System.out.println("8.退出");
        int choose = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        switch (choose) {
            case 1:
                System.out.println("请输入新的真实姓名");
                String realName = scanner.nextLine();
                System.out.println("请输入新的手机号码");
                String phone = scanner.nextLine();
                if (userService.updateUserInfo(user.getId(), realName, phone)) {
                    System.out.println("修改成功");
                } else {
                    System.out.println("修改失败，请重试");
                }
                showCustomerMenu(user);
                break;
            case 2:
                System.out.println("您的余额为：" + user.getBalance());
                showCustomerMenu(user);
                break;
            case 3:
                System.out.println("请输入充值金额");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // 消耗换行符
                if (userService.recharge(user.getId(), amount)) {
                    System.out.println("充值成功");
                } else {
                    System.out.println("充值失败，请重试");
                }
                showCustomerMenu(user);
                break;
            case 4:
                List<Merchant> merchants = merchantService.getAllMerchants();
                for (Merchant merchant : merchants) {
                    System.out.println("商户名称：" + merchant.getMerchantName());
                    System.out.println("销量：" + merchant.getMerchantSales());
                    System.out.println("关键词：" + merchant.getKeywords());
                    System.out.println("-------------------");
                }
                showCustomerMenu(user);
                break;
            case 5:
                List<Order> orders = orderService.getOrdersByCustomerId(String.valueOf(user.getId()));
                for (Order order : orders) {
                    System.out.println("订单 ID：" + order.getOrderId());
                    System.out.println("商户 ID：" + order.getMerchantId());
                    System.out.println("房型 ID：" + order.getRoomTypeId());
                    System.out.println("订单状态：" + order.getStatus());
                    System.out.println("价格：" + order.getPrice());
                    System.out.println("创建时间：" + order.getCreateTime());
                    System.out.println("入住时间：" + order.getCheckInTime());
                    System.out.println("退房时间：" + order.getCheckOutTime());
                    System.out.println("-------------------");
                }
                showCustomerMenu(user);
                break;
            case 6:
                System.out.println("请输入商户 ID");
                String merchantId = scanner.nextLine();
                List<RoomType> roomTypes = roomTypeService.getRoomTypesByMerchantId(merchantId);
                for (RoomType roomType : roomTypes) {
                    System.out.println("房型 ID：" + roomType.getRoomTypeId());
                    System.out.println("床型：" + roomType.getBedType());
                    System.out.println("剩余数量：" + roomType.getStock());
                    System.out.println("关键词：" + roomType.getKeywords());
                    System.out.println("价格：" + roomType.getPrice());
                    System.out.println("-------------------");
                }
                System.out.println("请输入房型 ID");
                String roomTypeId = scanner.nextLine();
                System.out.println("请输入入住时间（格式：yyyy-MM-dd）");
                String checkInTimeStr = scanner.nextLine();
                System.out.println("请输入退房时间（格式：yyyy-MM-dd）");
                String checkOutTimeStr = scanner.nextLine();
                try {
                    Date checkInTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(checkInTimeStr);
                    Date checkOutTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(checkOutTimeStr);
                    RoomType selectedRoomType = roomTypes.stream()
                            .filter(rt -> rt.getRoomTypeId().equals(roomTypeId))
                            .findFirst()
                            .orElse(null);
                    if (selectedRoomType != null && selectedRoomType.getStock() > 0) {
                        if (orderService.createOrder(String.valueOf(user.getId()), merchantId, roomTypeId, selectedRoomType.getPrice(), checkInTime, checkOutTime)) {
                            System.out.println("订房成功");
                            // 更新库存
                            selectedRoomType.setStock(selectedRoomType.getStock() - 1);
                            roomTypeService.updateRoomType(roomTypeId, selectedRoomType.getBedType(), selectedRoomType.getPrice(), selectedRoomType.getKeywords(), selectedRoomType.getStock(), selectedRoomType.getDescription());
                        } else {
                            System.out.println("订房失败，请重试");
                        }
                    } else {
                        System.out.println("该房型已无库存，请选择其他房型");
                    }
                } catch (Exception e) {
                    System.out.println("日期格式错误，请重新输入");
                }
                showCustomerMenu(user);
                break;
            case 7:
                System.out.println("请输入订单 ID");
                int orderId = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                if (orderService.deleteOrder(orderId)) {
                    System.out.println("退订成功");
                } else {
                    System.out.println("退订失败，请重试");
                }
                showCustomerMenu(user);
                break;
            case 8:
                showMainMenu();
                break;
            default:
                System.out.println("操作有误，您输入的数字有误或不是数字");
                showCustomerMenu(user);
        }
    }

    /**
     * 显示商户端菜单
     * @param merchant 商户对象
     */
    public void showMerchantMenu(Merchant merchant) {
        System.out.println("欢迎，" + merchant.getMerchantName());
        System.out.println("1.查看店铺信息");
        System.out.println("2.修改店铺信息");
        System.out.println("3.房间信息");
        System.out.println("4.客户入住");
        System.out.println("5.核销");
        System.out.println("6.修改订单状态");
        System.out.println("7.查看营业额");
        System.out.println("8.退出");
        int choose = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符
        switch (choose) {
            case 1:
                System.out.println("商户名称：" + merchant.getMerchantName());
                System.out.println("商户地址：" + merchant.getMerchantAddress());
                System.out.println("商户联系电话：" + merchant.getMerchantPhoneNumber());
                System.out.println("关键词：" + merchant.getKeywords());
                System.out.println("商户状态：" + merchant.getMerchantState());
                System.out.println("商户申请状态：" + merchant.getMerchantApplyState());
                System.out.println("营业额：" + merchant.getMerchantSales());
                showMerchantMenu(merchant);
                break;
            case 2:
                System.out.println("请输入新的商户名称");
                String merchantName = scanner.nextLine();
                System.out.println("请输入新的商户地址");
                String merchantAddress = scanner.nextLine();
                System.out.println("请输入新的商户联系电话");
                String merchantPhoneNumber = scanner.nextLine();
                System.out.println("请输入新的关键词");
                String keywords = scanner.nextLine();
                System.out.println("请输入新的商户状态");
                String merchantState = scanner.nextLine();
                System.out.println("请输入新的商户申请状态");
                String merchantApplyState = scanner.nextLine();
                if (merchantService.updateMerchantInfo(merchant.getId(), merchantName, merchantAddress, merchantPhoneNumber, keywords, merchantState, merchantApplyState)) {
                    System.out.println("修改成功");
                    merchant = merchantService.findMerchantById(merchant.getId());
                } else {
                    System.out.println("修改失败，请重试");
                }
                showMerchantMenu(merchant);
                break;
            case 3:
                System.out.println("1.添加房型");
                System.out.println("2.修改房型信息");
                System.out.println("3.删除房型");
                System.out.println("4.查看房型信息");
                int roomTypeChoose = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                switch (roomTypeChoose) {
                    case 1:
                        System.out.println("请输入床型");
                        String bedType = scanner.nextLine();
                        System.out.println("请输入价格");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // 消耗换行符
                        System.out.println("请输入关键词");
                        String roomKeywords = scanner.nextLine();
                        System.out.println("请输入库存");
                        int stock = scanner.nextInt();
                        scanner.nextLine(); // 消耗换行符
                        System.out.println("请输入描述");
                        String description = scanner.nextLine();
                        if (roomTypeService.addRoomType(String.valueOf(merchant.getId()), bedType, price, roomKeywords, stock, description)) {
                            System.out.println("添加成功");
                        } else {
                            System.out.println("添加失败，请重试");
                        }
                        showMerchantMenu(merchant);
                        break;
                    case 2:
                        System.out.println("请输入房型 ID");
                        String roomTypeId = scanner.nextLine();
                        System.out.println("请输入新的床型");
                        String newBedType = scanner.nextLine();
                        System.out.println("请输入新的价格");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // 消耗换行符
                        System.out.println("请输入新的关键词");
                        String newKeywords = scanner.nextLine();
                        System.out.println("请输入新的库存");
                        int newStock = scanner.nextInt();
                        scanner.nextLine(); // 消耗换行符
                        System.out.println("请输入新的描述");
                        String newDescription = scanner.nextLine();
                        if (roomTypeService.updateRoomType(roomTypeId, newBedType, newPrice, newKeywords, newStock, newDescription)) {
                            System.out.println("修改成功");
                        } else {
                            System.out.println("修改失败，请重试");
                        }
                        showMerchantMenu(merchant);
                        break;
                    case 3:
                        System.out.println("请输入房型 ID");
                        String deleteRoomTypeId = scanner.nextLine();
                        if (roomTypeService.deleteRoomType(deleteRoomTypeId)) {
                            System.out.println("删除成功");
                        } else {
                            System.out.println("删除失败，请重试");
                        }
                        showMerchantMenu(merchant);
                        break;
                    case 4:
                        List<RoomType> roomTypes = roomTypeService.getRoomTypesByMerchantId(String.valueOf(merchant.getId()));
                        for (RoomType roomType : roomTypes) {
                            System.out.println("房型 ID：" + roomType.getRoomTypeId());
                            System.out.println("床型：" + roomType.getBedType());
                            System.out.println("剩余数量：" + roomType.getStock());
                            System.out.println("关键词：" + roomType.getKeywords());
                            System.out.println("价格：" + roomType.getPrice());
                            System.out.println("-------------------");
                        }
                        showMerchantMenu(merchant);
                        break;
                    default:
                        System.out.println("操作有误，您输入的数字有误或不是数字");
                        showMerchantMenu(merchant);
                }
                break;
            case 4:
                // 客户入住逻辑
                showMerchantMenu(merchant);
                break;
            case 5:
                // 核销逻辑
                showMerchantMenu(merchant);
                break;
            case 6:
                System.out.println("请输入订单 ID");
                int orderId = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                System.out.println("请输入新的订单状态");
                String status = scanner.nextLine();
                if (orderService.updateOrderStatus(orderId, status)) {
                    System.out.println("修改成功");
                } else {
                    System.out.println("修改失败，请重试");
                }
                showMerchantMenu(merchant);
                break;
            case 7:
                System.out.println("您的营业额为：" + merchant.getMerchantSales());
                showMerchantMenu(merchant);
                break;
            case 8:
                showMainMenu();
                break;
            default:
                System.out.println("操作有误，您输入的数字有误或不是数字");
                showMerchantMenu(merchant);
        }
    }
}