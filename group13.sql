/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50648
Source Host           : 118.190.61.238:3306
Source Database       : group13

Target Server Type    : MYSQL
Target Server Version : 50648
File Encoding         : 65001

Date: 2020-05-19 23:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `sex` int(11) NOT NULL,
  `identity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for artcile_list
-- ----------------------------
DROP TABLE IF EXISTS `artcile_list`;
CREATE TABLE `artcile_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `artcile` varchar(10000) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of artcile_list
-- ----------------------------
INSERT INTO `artcile_list` VALUES ('1', '2', '这是一篇文章', '2020-05-19 04:25:37');
INSERT INTO `artcile_list` VALUES ('2', '1', '这是一篇文章', '2020-05-19 12:32:11');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `path` varchar(50) COLLATE utf8_bin NOT NULL,
  `user` varchar(50) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of data
-- ----------------------------

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `path` varchar(50) COLLATE utf8_bin NOT NULL,
  `user` varchar(50) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of homework
-- ----------------------------

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `level` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '日志级别',
  `threads` text COLLATE utf8_bin NOT NULL COMMENT '线程',
  `file_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `loggers` varchar(1000) COLLATE utf8_bin NOT NULL,
  `position` text COLLATE utf8_bin NOT NULL COMMENT '发生位置',
  `message` text COLLATE utf8_bin NOT NULL COMMENT '日志信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `context` varchar(50) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `context` varchar(50) COLLATE utf8_bin NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for notice_list
-- ----------------------------
DROP TABLE IF EXISTS `notice_list`;
CREATE TABLE `notice_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `notice` varchar(10000) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of notice_list
-- ----------------------------
INSERT INTO `notice_list` VALUES ('1', '2', '这是一条公告', '2020-05-19 04:26:11');
INSERT INTO `notice_list` VALUES ('2', '1', '我的公告', '2020-05-19 12:33:31');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `permissions_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'upload_video');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'teacher');
INSERT INTO `role` VALUES ('2', 'student');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '4', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `sex` int(11) NOT NULL,
  `identity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `teacherId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_course_user` (`studentId`),
  KEY `FK_student_course_user_2` (`teacherId`),
  CONSTRAINT `FK_student_course_user` FOREIGN KEY (`studentId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_student_course_user_2` FOREIGN KEY (`teacherId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student_course
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `sex` int(11) NOT NULL,
  `identity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacher
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `perms` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_list
-- ----------------------------
DROP TABLE IF EXISTS `user_list`;
CREATE TABLE `user_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '1 老师 2学生',
  `teacherid` int(11) DEFAULT NULL COMMENT '如果是学生，相对应有老师id',
  `classes` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '学生的班级',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_list
-- ----------------------------
INSERT INTO `user_list` VALUES ('1', '小明', null, '202cb962ac59075b964b07152d234b70', '2', '2', null, '2020-05-19 11:46:39');
INSERT INTO `user_list` VALUES ('2', '大明', null, '202cb962ac59075b964b07152d234b70', '1', '1', null, '2020-05-19 11:46:57');
INSERT INTO `user_list` VALUES ('3', '123456', null, '202cb962ac59075b964b07152d234b70', '2', '1', null, null);
INSERT INTO `user_list` VALUES ('4', null, null, null, '1', null, null, null);
INSERT INTO `user_list` VALUES ('5', 'hello', null, '202cb962ac59075b964b07152d234b70', '2', null, null, null);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `user` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `path` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of video
-- ----------------------------

-- ----------------------------
-- Table structure for video_list
-- ----------------------------
DROP TABLE IF EXISTS `video_list`;
CREATE TABLE `video_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `video_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of video_list
-- ----------------------------
INSERT INTO `video_list` VALUES ('1', '5', 'https://jiao-xue.oss-cn-beijing.aliyuncs.com/jiao-xue/e46cb2cb9d9648e1865673fc3e89dea0.docx', '2020-05-19 15:16:18');

-- ----------------------------
-- Table structure for work_list
-- ----------------------------
DROP TABLE IF EXISTS `work_list`;
CREATE TABLE `work_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `work_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `states` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '作业状态',
  `workid` int(11) DEFAULT NULL,
  `work` varchar(10000) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of work_list
-- ----------------------------
INSERT INTO `work_list` VALUES ('1', '1', 'https://jiao-xue.oss-cn-beijing.aliyuncs.com/jiao-xue/fdefe14ee3924ad990f9c715d38011fd.jpg', '已提交', '1', null, '2020-05-19 03:57:31');

-- ----------------------------
-- Table structure for work_teach
-- ----------------------------
DROP TABLE IF EXISTS `work_teach`;
CREATE TABLE `work_teach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `work` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `cratetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of work_teach
-- ----------------------------
INSERT INTO `work_teach` VALUES ('1', '2', null, '2020-05-19 03:55:35');
INSERT INTO `work_teach` VALUES ('2', '2', '作业内容', '2020-05-19 15:24:18');
