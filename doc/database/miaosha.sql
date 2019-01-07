/*
Navicat MySQL Data Transfer

Source Server         : MySQL80
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-18 12:00:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL,
  `goods_name` varchar(30) DEFAULT NULL,
  `goods_title` varchar(64) DEFAULT NULL,
  `goods_img` varchar(64) DEFAULT NULL,
  `goods_detail` longtext,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'iphoneX', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', '/img/iphonex.png', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', '7788.00', '100');
INSERT INTO `goods` VALUES ('2', '华为 P20 PRO', 'Huawei/华为 P20 PRO全网通4G智能手机', '/img/p20pro.png', 'Huawei/华为 P20 PRO 8G+256G 全网通4G智能手机', '5299.00', '50');
INSERT INTO `goods` VALUES ('3', '荣耀9i', 'Huawei/荣耀9i', '/img/荣耀9i.jpg', 'Huawei/荣耀9i', '999.00', '9999');
INSERT INTO `goods` VALUES ('4', '魅族PRO7', 'meizu/魅族PRO7', '/img/魅族PRO7.jpg', '魅族PRO7', '1599.00', '200');

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods` (
  `id` bigint(20) NOT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `miaosha_price` decimal(10,2) DEFAULT NULL,
  `stock_count` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `miaosha_goods_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods` VALUES ('1', '1', '0.01', '6', '2018-06-13 11:05:50', '2018-06-13 11:08:00');
INSERT INTO `miaosha_goods` VALUES ('2', '2', '0.01', '442', '2018-06-01 00:00:00', '2022-10-01 22:56:15');
INSERT INTO `miaosha_goods` VALUES ('3', '3', '899.00', '553', '2018-06-17 23:05:28', '2018-12-01 23:05:34');
INSERT INTO `miaosha_goods` VALUES ('4', '4', '1333.00', '22', '2018-06-17 23:05:31', '2021-08-17 23:05:42');

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_uid_gid` (`user_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------
INSERT INTO `miaosha_order` VALUES ('15', '15650592852', '1', '2');
INSERT INTO `miaosha_order` VALUES ('16', '11111111111', '1', '2');
INSERT INTO `miaosha_order` VALUES ('17', '11111111111', '20', '3');
INSERT INTO `miaosha_order` VALUES ('18', '11111111111', '21', '4');

-- ----------------------------
-- Table structure for miaosha_user
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_user`;
CREATE TABLE `miaosha_user` (
  `id` bigint(20) NOT NULL COMMENT '用户手机号码',
  `nickname` varchar(255) NOT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT 'md5(md5(pass明文+固定salt)+salt)',
  `salt` varchar(10) NOT NULL,
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登陆时间',
  `login_count` int(11) DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of miaosha_user
-- ----------------------------
INSERT INTO `miaosha_user` VALUES ('11111111111', '王八蛋', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-06-13 15:45:54', '2018-06-13 15:45:54', '0');
INSERT INTO `miaosha_user` VALUES ('15650592852', 'tengshe789', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-06-10 19:18:17', '2018-06-10 19:18:17', '0');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址',
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT NULL COMMENT 's数量',
  `goods_price` decimal(10,2) DEFAULT NULL,
  `order_channel` tinyint(4) DEFAULT NULL COMMENT '订单渠道，1在线，2android，3ios',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('18', '15650592852', null, null, '华为 P20 PRO', '1', '0.01', '1', '0', '2018-06-18 10:52:17', null);
INSERT INTO `order_info` VALUES ('19', '11111111111', null, null, '华为 P20 PRO', '1', '0.01', '1', '0', '2018-06-18 10:59:34', null);
INSERT INTO `order_info` VALUES ('20', '11111111111', '3', null, '荣耀9i', '1', '899.00', '1', '0', '2018-06-18 11:03:14', null);
INSERT INTO `order_info` VALUES ('21', '11111111111', '4', null, '魅族PRO7', '1', '1333.00', '1', '0', '2018-06-18 11:05:29', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tom');
INSERT INTO `user` VALUES ('2', 'jack');
