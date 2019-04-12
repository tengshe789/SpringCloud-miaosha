/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.184.130:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 08/04/2019 21:25:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `mall_goods`;
CREATE TABLE `mall_goods`  (
  `id` bigint(20) NOT NULL,
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `goods_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `goods_img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `goods_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `goods_stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `goods_buyingprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '进货价',
  `version` bigint(255) NULL DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_goods
-- ----------------------------
INSERT INTO `mall_goods` VALUES (1, 'iphoneX', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', '/img/iphonex.png', 'Apple/苹果iPhone X 全网通4G手机苹果X 10', 7788.00, 100, 1.00, NULL);
INSERT INTO `mall_goods` VALUES (2, '华为 P20 PRO', 'Huawei/华为 P20 PRO全网通4G智能手机', '/img/p20pro.png', 'Huawei/华为 P20 PRO 8G+256G 全网通4G智能手机', 5299.00, 50, 1.00, NULL);
INSERT INTO `mall_goods` VALUES (3, '荣耀9i', 'Huawei/荣耀9i', '/img/荣耀9i.jpg', 'Huawei/荣耀9i', 999.00, 9999, 1.00, NULL);
INSERT INTO `mall_goods` VALUES (4, '魅族PRO7', 'meizu/魅族PRO7', '/img/魅族PRO7.jpg', '魅族PRO7', 1599.00, 200, 1.00, NULL);

-- ----------------------------
-- Table structure for mall_miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `mall_miaosha_goods`;
CREATE TABLE `mall_miaosha_goods`  (
  `id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL,
  `miaosha_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价格',
  `stock_count` int(11) NULL DEFAULT NULL COMMENT '库存',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '截止日期',
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_mall_goods` FOREIGN KEY (`goods_id`) REFERENCES `mall_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_miaosha_goods
-- ----------------------------
INSERT INTO `mall_miaosha_goods` VALUES (1, 1, 0.01, 6, '2018-06-13 11:05:50', '2018-06-13 11:08:00', NULL);
INSERT INTO `mall_miaosha_goods` VALUES (2, 2, 0.01, 442, '2018-06-01 00:00:00', '2022-10-01 22:56:15', NULL);
INSERT INTO `mall_miaosha_goods` VALUES (3, 3, 899.00, 553, '2018-06-17 23:05:28', '2018-12-01 23:05:34', NULL);
INSERT INTO `mall_miaosha_goods` VALUES (4, 4, 1333.00, 22, '2018-06-17 23:05:31', '2021-08-17 23:05:42', NULL);

-- ----------------------------
-- Table structure for mall_miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_miaosha_order`;
CREATE TABLE `mall_miaosha_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  `goods_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_uid_gid`(`user_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_miaosha_order
-- ----------------------------
INSERT INTO `mall_miaosha_order` VALUES (17, 11111111111, 20, 3);
INSERT INTO `mall_miaosha_order` VALUES (18, 11111111111, 21, 4);

-- ----------------------------
-- Table structure for mall_order_info
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_info`;
CREATE TABLE `mall_order_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '编号',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '编号',
  `delivery_addr_id` bigint(20) NULL DEFAULT NULL COMMENT '收获地址',
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) NULL DEFAULT NULL COMMENT 's数量',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `order_channel` tinyint(4) NULL DEFAULT NULL COMMENT '订单渠道，1在线，2android，3ios',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `pay_date` datetime(0) NULL DEFAULT NULL COMMENT '支付日期',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_order_info
-- ----------------------------
INSERT INTO `mall_order_info` VALUES (18, 15650592852, NULL, NULL, '华为 P20 PRO', 1, 0.01, 1, 0, '2018-06-18 10:52:17', NULL, NULL);
INSERT INTO `mall_order_info` VALUES (19, 11111111111, NULL, NULL, '华为 P20 PRO', 1, 0.01, 1, 0, '2018-06-18 10:59:34', NULL, NULL);
INSERT INTO `mall_order_info` VALUES (20, 11111111111, 3, NULL, '荣耀9i', 1, 899.00, 1, 0, '2018-06-18 11:03:14', NULL, NULL);
INSERT INTO `mall_order_info` VALUES (21, 11111111111, 4, NULL, '魅族PRO7', 1, 1333.00, 1, 0, '2018-06-18 11:05:29', NULL, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('app', NULL, 'app', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('miaosha', NULL, 'miaosha', 'server', 'password,refresh_token,authorization_code,client_credentials', '', NULL, NULL, NULL, NULL, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('test', NULL, 'test', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '2' COMMENT '数据权限类型（数据权限：主要控制不同的资源主体（用户、角色、组织等）有查看不同的数据信息的权限，一般来说，数据权限又分为数据行权限和数据列权限，通过字面意思不难理解这两者的区别，比如上文“我们有一个订单列表，希望能够根据当前登陆的不同用户看到不同类型的订单数据” 这就是一个典型的数据行权限，而“我们系统需要不同用户查看不同的生产报表列”这就是数据列权限的范畴。由于数据权限和系统的业务逻辑关系非常密切，所以不同的系统设计差异性会非常大。从另一方面来说，由于数据权限和业务逻辑关联性非常强，如果系统的业务逻辑非常复杂，数据权限设计起来也会相对复杂，所以关于数据权限的设计一直没有一种相对通用和使用简单的设计方案。）',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据权限范围',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_idx1_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '管理员', '2', '2', '2019-03-11 15:45:51', '2019-03-11 09:27:56', '0');
INSERT INTO `sys_role` VALUES (2, '商城白金会员', 'ROLE_PLATINUM', '白金会员', '2', NULL, '2019-03-11 19:42:26', '2019-03-11 09:28:00', '0');
INSERT INTO `sys_role` VALUES (3, '商城黄金会员', 'ROLE_GOLD', '黄金', '2', NULL, '2019-03-11 09:26:59', '2019-03-11 09:27:26', '0');
INSERT INTO `sys_role` VALUES (4, '商城普通会员', 'ROLE_NORMAL', '普通', '2', NULL, '2019-03-11 09:27:47', '2019-03-11 17:28:46', '0');

-- ----------------------------
-- Table structure for sys_social_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_details`;
CREATE TABLE `sys_social_details`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appid',
  `app_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app_secret',
  `redirect_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统社交登录账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_social_details
-- ----------------------------
INSERT INTO `sys_social_details` VALUES (1, 'WX', '微信互联参数', 'wxd1678d3f83b1d83a', '6ddb043f94da5d2172926abe8533504f', 'daoweicloud.com', '2018-08-16 14:24:25', '2018-12-04 11:50:57', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '0-正常，9-锁定',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '0-正常，1-删除',
  `wx_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信openid',
  `qq_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'QQ openid',
  PRIMARY KEY (`user_id`, `username`, `phone`) USING BTREE,
  INDEX `user_wx_openid`(`wx_openid`) USING BTREE,
  INDEX `user_qq_openid`(`qq_openid`) USING BTREE,
  INDEX `user_idx1_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$QOfWxxFyAMmEEmnuw9UI/..1s4B4eF/u9PzE2ZaGO.ij9YfmcUy.u', NULL, '15650592852', '', '2019-04-20 07:15:18', '2019-04-01 04:18:20', '0', '0', 'o_0FT0uyg_H1vVy2H0JpSwlVGhWQ', NULL);
INSERT INTO `sys_user` VALUES (2, 'sys', '$10$QOfWxxFyAMmEEmnuw9UI/..1s4B4eF/u9PzE2ZaGO.ij9YfmcUy.u', NULL, '15650592855', NULL, '2019-04-01 03:32:31', '2019-04-01 06:13:52', '0', '0', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `role_id` bigint(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for zipkin_annotations
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_annotations`;
CREATE TABLE `zipkin_annotations`  (
  `trace_id_high` bigint(20) NOT NULL DEFAULT 0 COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.trace_id',
  `span_id` bigint(20) NOT NULL COMMENT 'coincides with zipkin_spans.id',
  `a_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'BinaryAnnotation.key or Annotation.value if type == -1',
  `a_value` blob NULL COMMENT 'BinaryAnnotation.value(), which must be smaller than 64KB',
  `a_type` int(11) NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if Annotation',
  `a_timestamp` bigint(20) NULL DEFAULT NULL COMMENT 'Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp',
  `endpoint_ipv4` int(11) NULL DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_ipv6` binary(16) NULL DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null, or no IPv6 address',
  `endpoint_port` smallint(6) NULL DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_service_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  UNIQUE INDEX `trace_id_high`(`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_timestamp`) USING BTREE COMMENT 'Ignore insert on duplicate',
  INDEX `trace_id_high_2`(`trace_id_high`, `trace_id`, `span_id`) USING BTREE COMMENT 'for joining with zipkin_spans',
  INDEX `trace_id_high_3`(`trace_id_high`, `trace_id`) USING BTREE COMMENT 'for getTraces/ByIds',
  INDEX `endpoint_service_name`(`endpoint_service_name`) USING BTREE COMMENT 'for getTraces and getServiceNames',
  INDEX `a_type`(`a_type`) USING BTREE COMMENT 'for getTraces',
  INDEX `a_key`(`a_key`) USING BTREE COMMENT 'for getTraces',
  INDEX `trace_id`(`trace_id`, `span_id`, `a_key`) USING BTREE COMMENT 'for dependencies job'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compressed;

-- ----------------------------
-- Table structure for zipkin_dependencies
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_dependencies`;
CREATE TABLE `zipkin_dependencies`  (
  `day` date NOT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `child` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `call_count` bigint(20) NULL DEFAULT NULL,
  `error_count` bigint(20) NULL DEFAULT NULL,
  UNIQUE INDEX `day`(`day`, `parent`, `child`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compressed;

-- ----------------------------
-- Table structure for zipkin_spans
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_spans`;
CREATE TABLE `zipkin_spans`  (
  `trace_id_high` bigint(20) NOT NULL DEFAULT 0 COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `debug` bit(1) NULL DEFAULT NULL,
  `start_ts` bigint(20) NULL DEFAULT NULL COMMENT 'Span.timestamp(): epoch micros used for endTs query and to implement TTL',
  `duration` bigint(20) NULL DEFAULT NULL COMMENT 'Span.duration(): micros used for minDuration and maxDuration query',
  UNIQUE INDEX `trace_id_high`(`trace_id_high`, `trace_id`, `id`) USING BTREE COMMENT 'ignore insert on duplicate',
  INDEX `trace_id_high_2`(`trace_id_high`, `trace_id`, `id`) USING BTREE COMMENT 'for joining with zipkin_annotations',
  INDEX `trace_id_high_3`(`trace_id_high`, `trace_id`) USING BTREE COMMENT 'for getTracesByIds',
  INDEX `name`(`name`) USING BTREE COMMENT 'for getTraces and getSpanNames',
  INDEX `start_ts`(`start_ts`) USING BTREE COMMENT 'for getTraces ordering and range'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compressed;

SET FOREIGN_KEY_CHECKS = 1;
