/*
Navicat MySQL Data Transfer

Source Server         : EmpDatabase
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : pss

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-02-19 15:19:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'IT部');
INSERT INTO `department` VALUES ('2', '采购部');
INSERT INTO `department` VALUES ('3', '销售部');

-- ----------------------------
-- Table structure for `depot`
-- ----------------------------
DROP TABLE IF EXISTS `depot`;
CREATE TABLE `depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `maxCapacity` decimal(19,2) DEFAULT NULL,
  `currentCapacity` decimal(19,2) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depot
-- ----------------------------
INSERT INTO `depot` VALUES ('1', '成都仓库', '10000.00', '100.00', '10000.00');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `headImage` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4AFD4ACE851EFECF` (`department_id`),
  CONSTRAINT `FK4AFD4ACE851EFECF` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'admin', 'lsq@itsource.cn', 'assets/avatars/avatar.png', '25', '1');
INSERT INTO `employee` VALUES ('2', 'roleAdmin', 'roleAdmin', 'roleAdmin@itsource.cn', 'assets/avatars/avatar1.png', '25', '1');
INSERT INTO `employee` VALUES ('3', 'admin1', 'admin1', 'amdin1@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('4', 'admin2', 'admin2', 'amdin2@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('5', 'admin3', 'admin3', 'amdin3@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('6', 'admin4', 'admin4', 'amdin4@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('7', 'admin5', 'admin5', 'amdin5@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('8', 'admin6', 'admin6', 'amdin6@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('9', 'admin7', 'admin7', 'amdin7@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('10', 'admin888', 'admin8', 'amdin8@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('11', 'admin9', 'admin9', 'amdin9@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('12', 'admin10', 'admin10', 'amdin10@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('13', 'admin11', 'admin11', 'amdin11@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('14', 'admin12', 'admin12', 'amdin12@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('15', 'admin13', 'admin13', 'amdin13@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('16', 'admin14', 'admin14', 'amdin14@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('17', 'admin15', 'admin15', 'amdin15@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('18', 'admin16', 'admin16', 'amdin16@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('19', 'admin17', 'admin17', 'amdin17@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('20', 'admin18', 'admin18', 'amdin18@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('21', 'admin19', 'admin19', 'amdin19@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('22', 'admin20', 'admin20', 'amdin20@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('23', 'admin21', 'admin21', 'amdin21@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('24', 'admin22', 'admin22', 'amdin22@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('25', 'admin23', 'admin23', 'amdin23@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('26', 'admin24', 'admin24', 'amdin24@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('27', 'admin25', 'admin25', 'amdin25@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('28', 'admin26', 'admin26', 'amdin26@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('29', 'admin27', 'admin27', 'amdin27@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('30', 'admin28', 'admin28', 'amdin28@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('31', 'admin29', 'admin29', 'amdin29@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('32', 'admin30', 'admin30', 'amdin30@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('33', 'admin31', 'admin31', 'amdin31@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('34', 'admin32', 'admin32', 'amdin32@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('35', 'admin33', 'admin33', 'amdin33@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('36', 'admin34', 'admin34', 'amdin34@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('37', 'admin35', 'admin35', 'amdin35@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('38', 'admin36', 'admin36', 'amdin36@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('39', 'admin37', 'admin37', 'amdin37@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('40', 'admin38', 'admin38', 'amdin38@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('41', 'admin39', 'admin39', 'amdin39@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('42', 'admin40', 'admin40', 'amdin40@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('43', 'admin41', 'admin41', 'amdin41@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('44', 'admin42', 'admin42', 'amdin42@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('45', 'admin43', 'admin43', 'amdin43@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('46', 'admin44', 'admin44', 'amdin44@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('47', 'admin45', 'admin45', 'amdin45@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('48', 'admin46', 'admin46', 'amdin46@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('49', 'admin47', 'admin47', 'amdin47@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('50', 'admin48', 'admin48', 'amdin48@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('51', 'admin49', 'admin49', 'amdin49@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('52', 'admin50', 'admin50', 'amdin50@itsource.cn', 'assets/avatars/avatar5.png', '25', '3');
INSERT INTO `employee` VALUES ('53', 'admin51', 'admin51', 'amdin51@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('54', 'admin52', 'admin52', 'amdin52@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');
INSERT INTO `employee` VALUES ('55', 'admin53', 'admin53', 'amdin53@itsource.cn', 'assets/avatars/avatar5.png', '25', '2');
INSERT INTO `employee` VALUES ('56', 'admin54', 'admin54', 'amdin54@itsource.cn', 'assets/avatars/avatar5.png', '25', '1');

-- ----------------------------
-- Table structure for `employee_role`
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  KEY `FK87184F674D26E00F` (`role_id`),
  KEY `FK87184F6710B1828F` (`employee_id`),
  CONSTRAINT `FK87184F6710B1828F` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK87184F674D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('1', '1');
INSERT INTO `employee_role` VALUES ('2', '2');
INSERT INTO `employee_role` VALUES ('3', '4');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24897F76799044` (`parent_id`),
  CONSTRAINT `FK24897F76799044` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', null, 'icon-glass', null);
INSERT INTO `menu` VALUES ('2', '角色管理', 'role.action', 'icon-music', '1');
INSERT INTO `menu` VALUES ('3', '菜单管理', 'menu.action', 'icon-envelope-alt', '1');
INSERT INTO `menu` VALUES ('4', '权限管理', 'permission.action', 'icon-search', '1');
INSERT INTO `menu` VALUES ('5', '导入管理', 'import.action', 'icon-envelope-alt', '1');
INSERT INTO `menu` VALUES ('6', '组织机构', null, 'icon-heart', null);
INSERT INTO `menu` VALUES ('7', '部门管理', 'department.action', 'icon-star', '6');
INSERT INTO `menu` VALUES ('8', '员工管理', 'employee.action', 'icon-user', '6');
INSERT INTO `menu` VALUES ('9', '基础数据', null, 'icon-film', null);
INSERT INTO `menu` VALUES ('10', '数据字典类型', 'systemDictionaryType.action', 'icon-th-large', '9');
INSERT INTO `menu` VALUES ('11', '数据字典明细', 'systemDictionaryDetail.action', 'icon-ok', '9');
INSERT INTO `menu` VALUES ('12', '产品类型', 'productType.action', 'icon-power-off', '9');
INSERT INTO `menu` VALUES ('13', '产品管理', 'product.action', 'icon-signal', '9');
INSERT INTO `menu` VALUES ('14', '供应商管理', 'supplier.action', 'icon-gear', '9');
INSERT INTO `menu` VALUES ('15', '采购模块', null, 'icon-flag', null);
INSERT INTO `menu` VALUES ('16', '采购管理', 'purchaseBill.action', 'icon-home', '15');
INSERT INTO `menu` VALUES ('17', '采购报表', 'purchaseBillItem.action', 'icon-time', '15');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '权限管理', 'PermissionAction.ALL', '权限管理,PermissionAction.ALL');
INSERT INTO `permission` VALUES ('2', '角色管理', 'RoleAction.ALL', '角色管理,RoleAction.ALL');
INSERT INTO `permission` VALUES ('3', '角色列表', 'RoleAction.execute', '角色列表,RoleAction.execute');
INSERT INTO `permission` VALUES ('4', '删除角色', 'RoleAction.delete', '删除角色,RoleAction.delete');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `smallPic` varchar(255) DEFAULT NULL,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `salePrice` decimal(19,2) DEFAULT NULL,
  `types_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  `brand_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C664CF8DF77FB5` (`types_id`),
  KEY `FK50C664CF422B987E` (`brand_id`),
  KEY `FK50C664CF329AED61` (`unit_id`),
  CONSTRAINT `FK50C664CF329AED61` FOREIGN KEY (`unit_id`) REFERENCES `systemdictionarydetail` (`id`),
  CONSTRAINT `FK50C664CF422B987E` FOREIGN KEY (`brand_id`) REFERENCES `systemdictionarydetail` (`id`),
  CONSTRAINT `FK50C664CF8DF77FB5` FOREIGN KEY (`types_id`) REFERENCES `producttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '产品1', 'red', 'upload/20150327-21433174.png', 'upload/20150327-21433174_small.png', '1.00', '1.00', '2', '3', '1');
INSERT INTO `product` VALUES ('2', '产品2', 'green', 'upload/20150327-214430762.png', 'upload/20150327-214430762_small.png', '2.00', '2.00', '7', '4', '2');

-- ----------------------------
-- Table structure for `productstock`
-- ----------------------------
DROP TABLE IF EXISTS `productstock`;
CREATE TABLE `productstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `incomeDate` datetime DEFAULT NULL,
  `warning` bit(1) DEFAULT NULL,
  `topNum` decimal(19,2) DEFAULT NULL,
  `bottomNum` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `depot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK459B4B879F064DC5` (`depot_id`),
  KEY `FK459B4B87D6A81925` (`product_id`),
  CONSTRAINT `FK459B4B879F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK459B4B87D6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productstock
-- ----------------------------

-- ----------------------------
-- Table structure for `producttype`
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA8168A931A015E4` (`parent_id`),
  CONSTRAINT `FKA8168A931A015E4` FOREIGN KEY (`parent_id`) REFERENCES `producttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '汽车', '汽车', null);
INSERT INTO `producttype` VALUES ('2', '奥迪', '奥迪', '1');
INSERT INTO `producttype` VALUES ('3', '奔驰', '奔驰', '1');
INSERT INTO `producttype` VALUES ('4', '大众', '大众', '1');
INSERT INTO `producttype` VALUES ('5', '电视', '电视', null);
INSERT INTO `producttype` VALUES ('6', '3D电视', '3D电视', '5');
INSERT INTO `producttype` VALUES ('7', '液晶电视', '液晶电视', '5');

-- ----------------------------
-- Table structure for `purchasebill`
-- ----------------------------
DROP TABLE IF EXISTS `purchasebill`;
CREATE TABLE `purchasebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNum` decimal(19,2) DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `auditorTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `inputUser_id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BD788C89FE0CD6A` (`buyer_id`),
  KEY `FK9BD788C83FF7A83F` (`auditor_id`),
  KEY `FK9BD788C8A902BD48` (`inputUser_id`),
  KEY `FK9BD788C812C245CF` (`supplier_id`),
  CONSTRAINT `FK9BD788C812C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK9BD788C83FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK9BD788C89FE0CD6A` FOREIGN KEY (`buyer_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK9BD788C8A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchasebill
-- ----------------------------
INSERT INTO `purchasebill` VALUES ('1', '2015-04-21 00:00:00', '5.00', '3.00', '2015-04-21 21:01:12', null, '0', '1', null, '1', '1');
INSERT INTO `purchasebill` VALUES ('2', '2015-04-21 00:00:00', '22.00', '22.00', '2015-04-21 21:01:27', null, '0', '1', null, '1', '1');
INSERT INTO `purchasebill` VALUES ('3', '2015-04-21 12:00:00', '4.00', '2.00', '2015-04-21 21:01:39', null, '0', '2', null, '1', '1');

-- ----------------------------
-- Table structure for `purchasebillitem`
-- ----------------------------
DROP TABLE IF EXISTS `purchasebillitem`;
CREATE TABLE `purchasebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5FF8D3FBD6A81925` (`product_id`),
  KEY `FK5FF8D3FB60931610` (`bill_id`),
  CONSTRAINT `FK5FF8D3FB60931610` FOREIGN KEY (`bill_id`) REFERENCES `purchasebill` (`id`),
  CONSTRAINT `FK5FF8D3FBD6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchasebillitem
-- ----------------------------
INSERT INTO `purchasebillitem` VALUES ('1', '1.00', '1.00', '1.00', '1', '1', '1');
INSERT INTO `purchasebillitem` VALUES ('2', '2.00', '2.00', '4.00', '', '2', '1');
INSERT INTO `purchasebillitem` VALUES ('3', '1.00', '11.00', '11.00', '', '1', '2');
INSERT INTO `purchasebillitem` VALUES ('4', '1.00', '11.00', '11.00', '', '1', '2');
INSERT INTO `purchasebillitem` VALUES ('5', '2.00', '2.00', '4.00', '', '2', '3');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员');
INSERT INTO `role` VALUES ('2', '角色管理员');
INSERT INTO `role` VALUES ('4', '$思念的愁$');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK1404278833B84B6F` (`menu_id`),
  KEY `FK140427884D26E00F` (`role_id`),
  CONSTRAINT `FK1404278833B84B6F` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK140427884D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('2', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('2', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');
INSERT INTO `role_menu` VALUES ('1', '16');
INSERT INTO `role_menu` VALUES ('1', '17');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FKAEE599B74D26E00F` (`role_id`),
  KEY `FKAEE599B7C854068F` (`permission_id`),
  CONSTRAINT `FKAEE599B74D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKAEE599B7C854068F` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('2', '2');
INSERT INTO `role_permission` VALUES ('4', '3');

-- ----------------------------
-- Table structure for `stockincomebill`
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebill`;
CREATE TABLE `stockincomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNum` decimal(19,2) DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `auditorTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `inputUser_id` bigint(20) NOT NULL,
  `keeper_id` bigint(20) NOT NULL,
  `depot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54AC64E69F064DC5` (`depot_id`),
  KEY `FK54AC64E63FF7A83F` (`auditor_id`),
  KEY `FK54AC64E6A902BD48` (`inputUser_id`),
  KEY `FK54AC64E6725F67CB` (`keeper_id`),
  KEY `FK54AC64E612C245CF` (`supplier_id`),
  CONSTRAINT `FK54AC64E612C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK54AC64E63FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK54AC64E6725F67CB` FOREIGN KEY (`keeper_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK54AC64E69F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK54AC64E6A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockincomebill
-- ----------------------------

-- ----------------------------
-- Table structure for `stockincomebillitem`
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebillitem`;
CREATE TABLE `stockincomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKACA67119D6A81925` (`product_id`),
  KEY `FKACA671192B5E3024` (`bill_id`),
  CONSTRAINT `FKACA671192B5E3024` FOREIGN KEY (`bill_id`) REFERENCES `stockincomebill` (`id`),
  CONSTRAINT `FKACA67119D6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockincomebillitem
-- ----------------------------

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '成都供应商');
INSERT INTO `supplier` VALUES ('2', '东莞供应商');

-- ----------------------------
-- Table structure for `systemdictionarydetail`
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionarydetail`;
CREATE TABLE `systemdictionarydetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `types_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81BC50F6718C93B5` (`types_id`),
  CONSTRAINT `FK81BC50F6718C93B5` FOREIGN KEY (`types_id`) REFERENCES `systemdictionarytype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionarydetail
-- ----------------------------
INSERT INTO `systemdictionarydetail` VALUES ('1', '七匹狼', '1');
INSERT INTO `systemdictionarydetail` VALUES ('2', '耐克', '1');
INSERT INTO `systemdictionarydetail` VALUES ('3', '条', '2');
INSERT INTO `systemdictionarydetail` VALUES ('4', '斤', '2');

-- ----------------------------
-- Table structure for `systemdictionarytype`
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionarytype`;
CREATE TABLE `systemdictionarytype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sn` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionarytype
-- ----------------------------
INSERT INTO `systemdictionarytype` VALUES ('1', 'productBrand', '产品品牌');
INSERT INTO `systemdictionarytype` VALUES ('2', 'productUnit', '产品单位');
