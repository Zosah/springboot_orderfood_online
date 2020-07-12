/*
 Navicat Premium Data Transfer

 Source Server         : Mydatabase
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : springboot_orderfood_end

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 02/07/2020 14:26:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu_detail
-- ----------------------------
DROP TABLE IF EXISTS `menu_detail`;
CREATE TABLE `menu_detail`  (
  `md_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜品id',
  `md_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `md_price` int(10) NULL DEFAULT NULL COMMENT '菜品价格',
  `md_amount` int(10) NULL DEFAULT NULL COMMENT '菜品数量',
  `md_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/images/no_Image_default.jpg' COMMENT '菜品图片',
  `md_new` int(10) NULL DEFAULT 1 COMMENT '是否为新产品：默认为是1，否0',
  `md_star` int(10) NULL DEFAULT 3 COMMENT '推荐星值，默认为3； 0-5',
  `md_deleted` int(10) NULL DEFAULT 0 COMMENT '逻辑删除',
  `md_version` int(10) NULL DEFAULT 1 COMMENT '乐观锁',
  `mt_id` int(10) NULL DEFAULT NULL COMMENT '类型id',
  PRIMARY KEY (`md_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_detail
-- ----------------------------
INSERT INTO `menu_detail` VALUES (1, '白饭', 2, 998, '/images/439b73708eb0431c8d41c2102f910f87.jpg', 0, 3, 0, 44, 1);
INSERT INTO `menu_detail` VALUES (2, '炒河粉', 15, 998, '/images/172b1faf39db4944a7a1f7bacb1296ef.jpg', 1, 5, 0, 8, 2);
INSERT INTO `menu_detail` VALUES (3, '大馒头10个', 10, 997, '/images/fbb8786ea77e414ca9c67ddb83a303bf.jpg', 1, 4, 0, 11, 1);
INSERT INTO `menu_detail` VALUES (4, '扬州炒饭', 15, 999, '/images/590b37b1bd494afaa45b7a712bf191c9.jpg', 1, 5, 0, 6, 1);
INSERT INTO `menu_detail` VALUES (5, '玉米粥', 9, 999, '/images/0d4a7a6e70614b5287f586b4ece1cf61.jpg', 1, 4, 0, 6, 1);
INSERT INTO `menu_detail` VALUES (6, '巨无霸', 30, 999, '/images/5b3fb289d524443fb53217fae93d4a5b.jpg', 1, 5, 0, 3, 4);
INSERT INTO `menu_detail` VALUES (7, '益禾蜂蜜拿铁', 10, 999, '/images/1aac7ef93f7b45cab7d98fa0c6c9d19f.jpg', 1, 4, 0, 6, 3);
INSERT INTO `menu_detail` VALUES (8, '烧鸡翅', 35, 999, '/images/7c03668169f2406192a16cc47c9577dd.jpg', 0, 5, 0, 2, 2);
INSERT INTO `menu_detail` VALUES (9, '芹菜炒肉', 30, 999, '/images/5f83429ba92349aa9a520f822d85202a.jpg', 1, 4, 0, 2, 2);
INSERT INTO `menu_detail` VALUES (10, '煎蛋', 15, 999, '/images/bef255b63555472bad0111f109e2662e.jpg', 1, 3, 0, 2, 2);
INSERT INTO `menu_detail` VALUES (11, '瑞幸小鹿茶', 27, 992, '/images/a3a4c26ad4dd4715ba9775f6b34de639.jpg', 1, 5, 0, 4, 3);
INSERT INTO `menu_detail` VALUES (12, '一点点奶茶', 20, 999, '/images/2cdf1fcbe39c408492fdaf5d3d2f1f67.jpg', 1, 5, 0, 2, 3);
INSERT INTO `menu_detail` VALUES (13, '星巴克咖啡', 35, 999, '/images/999713e493d743f1bb03ec8921201d6d.jpg', 0, 2, 0, 2, 3);
INSERT INTO `menu_detail` VALUES (14, '通心菜', 15, 999, '/images/5559f8b80c4548a6aeaf19e1fafdbff8.jpg', 1, 4, 0, 2, 2);
INSERT INTO `menu_detail` VALUES (15, '木耳菜花', 15, 999, '/images/83924f23a4cc4e1285f5833a6ec7f51b.jpg', 1, 4, 0, 2, 2);

-- ----------------------------
-- Table structure for menu_type
-- ----------------------------
DROP TABLE IF EXISTS `menu_type`;
CREATE TABLE `menu_type`  (
  `mt_id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mt_deleted` int(10) NULL DEFAULT 0,
  `mt_version` int(10) NULL DEFAULT 1,
  PRIMARY KEY (`mt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_type
-- ----------------------------
INSERT INTO `menu_type` VALUES (1, '主食', 0, 1);
INSERT INTO `menu_type` VALUES (2, '小炒', 0, 1);
INSERT INTO `menu_type` VALUES (3, '饮品', 0, 1);
INSERT INTO `menu_type` VALUES (4, 'KFC系列', 0, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `od_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `od_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `od_createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `od_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细内容',
  `od_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `od_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `od_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `od_total` int(10) NULL DEFAULT NULL COMMENT '成交价',
  `od_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `od_status` int(10) NULL DEFAULT NULL COMMENT '订单状态',
  `u_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`od_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (17, '20200701124007326', '2020-07-01 12:40:07', '【白饭 × 1;大馒头10个 × 1;炒河粉 × 1;瑞幸小鹿茶 × 1;】', '内测用户', '13544551200', '广东广州', 54, '内测用户测试', 2, 2);
INSERT INTO `order_detail` VALUES (18, '20200701195218732', '2020-07-01 19:52:19', '【瑞幸小鹿茶 × 6;】', 'xxx', '13544551200', '广东广州', 162, '123', 2, 2);
INSERT INTO `order_detail` VALUES (19, '20200702010523918', '2020-07-02 01:05:23', '【大馒头10个 × 1;】', '内测用户', '13544551200', '广东广州', 10, '多加点糖', 2, 2);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `sc_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '购物车序号',
  `sc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `sc_amount` int(10) NULL DEFAULT NULL COMMENT '单品数量',
  `sc_price` int(10) NULL DEFAULT NULL COMMENT '菜品价格',
  `sc_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜品图片',
  `md_id` int(10) NULL DEFAULT NULL COMMENT '菜品id',
  `u_id` int(10) NULL DEFAULT NULL COMMENT '当前用户id',
  PRIMARY KEY (`sc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (28, '白饭', 1, 2, '/images/439b73708eb0431c8d41c2102f910f87.jpg', 1, 1);
INSERT INTO `shopping_cart` VALUES (31, '大馒头10个', 1, 10, '/images/fbb8786ea77e414ca9c67ddb83a303bf.jpg', 3, 2);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `u_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `u_username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `u_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `u_role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色_0为商家_1为顾客',
  `u_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `u_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `u_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `u_money` int(100) NULL DEFAULT 0 COMMENT '用户收支',
  `u_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/images/head_Image_default.jpg' COMMENT '头像',
  `u_deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除：默认为0',
  `u_version` int(1) NULL DEFAULT 1 COMMENT '乐观锁：默认值为1',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '123456', 'ROLE_admin', '奸商', '13104997306', '广东省茂名市电白区', 226, '/images/9d610f3d96404bff8e4ff29daa699b94.jpg', 0, 30);
INSERT INTO `tb_user` VALUES (2, 'user', '1', 'ROLE_user', 'xxx', '13544551200', '广东广州', 226, '/images/3cbfc74f412b43a086920d880eb2e3e6.jpg', 0, 13);
INSERT INTO `tb_user` VALUES (3, 'zhangsan', '1', 'ROLE_user', '张三', '13800138000', '广东广州', 0, '/images/head_Image_default.jpg', 0, 2);
INSERT INTO `tb_user` VALUES (4, 'lisi', '1', 'ROLE_user', '李四', '5362222', '广东', 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (5, 'wangwu', '1', 'ROLE_user', '王五', '987654321', '中国广东', 0, '/images/head_Image_default.jpg', 1, 1);
INSERT INTO `tb_user` VALUES (6, 'test1', '1', 'ROLE_admin', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 1, 1);
INSERT INTO `tb_user` VALUES (7, 'test2', '1', 'ROLE_user', '哈哈', '13544551200', '广东', 0, '/images/a89fd1bd96444f04881d40f490211fc1.jpg', 0, 9);
INSERT INTO `tb_user` VALUES (8, 'test3', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (9, 'test4', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (10, 'test5', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 1, 1);
INSERT INTO `tb_user` VALUES (11, 'test6', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (12, 'test7', '1', 'ROLE_admin', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (13, 'test8', '1', 'ROLE_admin', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (14, 'test9', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (15, 'test10', 'test10', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (16, '1', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (17, 'aaaaa', 'aaaaa', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 1, 1);
INSERT INTO `tb_user` VALUES (18, 'qq', 'qq', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (19, 'ggggg', 'ggggg', 'ROLE_admin', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (20, 'cc', 'cc', 'ROLE_admin', '顾客1', '13544551200', '广东', 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (21, 'ww', 'ww', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (22, 'laowang', '123456', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (23, 'guest2', '123456', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);
INSERT INTO `tb_user` VALUES (24, 'user', '1', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 1, 1);
INSERT INTO `tb_user` VALUES (25, 'a123456', '123456', 'ROLE_user', NULL, NULL, NULL, 0, '/images/head_Image_default.jpg', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
