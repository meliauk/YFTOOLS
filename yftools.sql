/*
Navicat MySQL Data Transfer

Source Server         : 1212
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : yftools

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-06-21 18:35:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `mid` bigint(20) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目名称',
  `murl` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目链接',
  `mformat` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '链接格式',
  `mtype` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目类型',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('1', 'CCTV-1', 'http://111.13.111.242/otttv.bj.chinamobile.com/PLTV/88888888/224/3221226226/1.m3u8', 'm3u8', '央视');
INSERT INTO `movie` VALUES ('2', 'CCTV-2', 'http://111.13.111.242/otttv.bj.chinamobile.com/PLTV/88888888/224/3221226230/1.m3u8', 'm3u8', '央视');
INSERT INTO `movie` VALUES ('3', 'CCTV-3', 'http://223.110.241.130:6610/gitv/live1/G_CCTV-3-HQ/.m3u8', 'm3u8', '央视');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `upassword` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `uphoto` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  `uemail` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `utoken` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户令牌',
  `uinfo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户信息',
  `upower` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户权限',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', 'yifan', 'db463c2457d27b2c976e97e51f29a221', null, 'yifan@163.com', '93de17ac-51f3-4265-87de-06dd1ea30c55db463c2457d27b2c976e97e51f29a221', null, null, '2020-05-12 12:49:41', null);
