/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : binghe_demo

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-02-24 18:56:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_code` varchar(50) NOT NULL COMMENT '权限编号',
  `perm_name` varchar(50) NOT NULL COMMENT '权限名',
  `perm_desc` varchar(50) NOT NULL COMMENT '权限描述',
  `pid` int(11) NOT NULL COMMENT '父id',
  `perm_type` tinyint(1) DEFAULT NULL COMMENT '权限类型 1-功能，2-菜单',
  `menu_index` smallint(5) DEFAULT NULL COMMENT '菜单顺序',
  `url` varchar(50) NOT NULL COMMENT '资源路径',
  `state` tinyint(1) NOT NULL COMMENT '状态 0-不可用，1-可用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(50) NOT NULL COMMENT '角色描述',
  `state` tinyint(1) NOT NULL COMMENT '状态 0-不可用，1-可用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `salt` varchar(50) NOT NULL COMMENT '密码加密用的盐值',
  `state` tinyint(1) NOT NULL COMMENT '用户状态 0-不可用，1-可用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT 'sys_user表的id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`user_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色表';
