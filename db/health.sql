/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : health

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2021-02-18 21:58:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001775D839B107874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'DESKTOP-PUO58N01613653099436', '1613656677349', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1613656800000', '1613655000000', '5', 'WAITING', 'CRON', '1613652559000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001775D839B107874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'renren', '0 0/30 * * * ?', '0', '参数测试', '2021-02-01 20:14:02');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'renren', '0', null, '0', '2021-02-18 21:00:00');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'renren', '0', null, '36', '2021-02-18 21:30:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('b239a2f3-5c93-42bc-80ce-68f272ef6753', 'an4pn', '2021-02-18 20:54:35');
INSERT INTO `sys_captcha` VALUES ('b96effce-c183-455e-8180-c65f03e446e3', 'ngdfe', '2021-02-18 21:05:18');
INSERT INTO `sys_captcha` VALUES ('ccf2e972-e237-4f5e-8bc2-3073f7fbb8fa', 'w87y2', '2021-02-18 21:06:12');
INSERT INTO `sys_captcha` VALUES ('dd864183-1390-466a-87d3-5d2af4563afd', 'cde53', '2021-02-18 22:00:25');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":101,\"parentId\":0,\"name\":\"医护人员\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shouye\",\"orderNum\":0}]', '15', '0:0:0:0:0:0:0:1', '2021-02-18 21:02:21');
INSERT INTO `sys_log` VALUES ('2', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":101,\"name\":\"活动公告\",\"url\":\"generator/activity\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '28', '0:0:0:0:0:0:0:1', '2021-02-18 21:02:49');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":101,\"name\":\"老人出入院记录\",\"url\":\"generator/gafferadmissionrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:03:02');
INSERT INTO `sys_log` VALUES ('4', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":41,\"parentId\":101,\"name\":\"老人信息\",\"url\":\"generator/gafferinfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '9', '0:0:0:0:0:0:0:1', '2021-02-18 21:03:40');
INSERT INTO `sys_log` VALUES ('5', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":51,\"parentId\":101,\"name\":\"查房记录\",\"url\":\"generator/roomcheckrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '8', '0:0:0:0:0:0:0:1', '2021-02-18 21:03:59');
INSERT INTO `sys_log` VALUES ('6', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":46,\"parentId\":101,\"name\":\"老人额外消费\",\"url\":\"generator/gafferpayment\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:04:21');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":102,\"parentId\":0,\"name\":\"院领导\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:05:19');
INSERT INTO `sys_log` VALUES ('8', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":76,\"parentId\":102,\"name\":\"财务收支\",\"url\":\"generator/financepayment\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '9', '0:0:0:0:0:0:0:1', '2021-02-18 21:05:38');
INSERT INTO `sys_log` VALUES ('9', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":66,\"parentId\":102,\"name\":\"员工评级\",\"url\":\"generator/emprating\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:06:01');
INSERT INTO `sys_log` VALUES ('10', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":61,\"parentId\":102,\"name\":\"员工信息\",\"url\":\"generator/empinfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:06:20');
INSERT INTO `sys_log` VALUES ('11', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":71,\"parentId\":102,\"name\":\"员工工资统计\",\"url\":\"generator/empsalaryrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:06:39');
INSERT INTO `sys_log` VALUES ('12', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":56,\"parentId\":102,\"name\":\"员工考勤\",\"url\":\"generator/empattendance\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '8', '0:0:0:0:0:0:0:1', '2021-02-18 21:06:53');
INSERT INTO `sys_log` VALUES ('13', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":103,\"parentId\":0,\"name\":\"老人信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"admin\",\"orderNum\":0}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:08:29');
INSERT INTO `sys_log` VALUES ('14', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":36,\"parentId\":103,\"name\":\"老人出入院记录\",\"url\":\"generator/gafferadmissionrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '8', '0:0:0:0:0:0:0:1', '2021-02-18 21:08:46');
INSERT INTO `sys_log` VALUES ('15', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":81,\"parentId\":103,\"name\":\"老人缴费记录\",\"url\":\"generator/gafferpaymentrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '8', '0:0:0:0:0:0:0:1', '2021-02-18 21:08:59');
INSERT INTO `sys_log` VALUES ('16', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":86,\"parentId\":103,\"name\":\"老人账户充值记录\",\"url\":\"generator/gafferrecharge\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:09:09');
INSERT INTO `sys_log` VALUES ('17', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":104,\"parentId\":103,\"name\":\"房间信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shouye\",\"orderNum\":0}]', '8', '0:0:0:0:0:0:0:1', '2021-02-18 21:09:23');
INSERT INTO `sys_log` VALUES ('18', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":105,\"parentId\":0,\"name\":\"房间信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"shouye\",\"orderNum\":0}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:09:51');
INSERT INTO `sys_log` VALUES ('19', 'admin', '删除菜单', 'io.renren.modules.sys.controller.SysMenuController.delete()', '[104]', '391', '0:0:0:0:0:0:0:1', '2021-02-18 21:10:01');
INSERT INTO `sys_log` VALUES ('20', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":91,\"parentId\":105,\"name\":\"房间变更\",\"url\":\"generator/roomchange\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '9', '0:0:0:0:0:0:0:1', '2021-02-18 21:10:10');
INSERT INTO `sys_log` VALUES ('21', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":96,\"parentId\":105,\"name\":\"房间信息\",\"url\":\"generator/roominfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '10', '0:0:0:0:0:0:0:1', '2021-02-18 21:10:21');
INSERT INTO `sys_log` VALUES ('22', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":41,\"parentId\":103,\"name\":\"老人信息\",\"url\":\"generator/gafferinfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:10:37');
INSERT INTO `sys_log` VALUES ('23', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":46,\"parentId\":103,\"name\":\"老人额外消费\",\"url\":\"generator/gafferpayment\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:10:48');
INSERT INTO `sys_log` VALUES ('24', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":56,\"parentId\":101,\"name\":\"员工考勤\",\"url\":\"generator/empattendance\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '9', '0:0:0:0:0:0:0:1', '2021-02-18 21:11:11');
INSERT INTO `sys_log` VALUES ('25', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":61,\"parentId\":101,\"name\":\"员工信息\",\"url\":\"generator/empinfo\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:11:25');
INSERT INTO `sys_log` VALUES ('26', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":66,\"parentId\":101,\"name\":\"员工评级\",\"url\":\"generator/emprating\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:13:21');
INSERT INTO `sys_log` VALUES ('27', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":71,\"parentId\":101,\"name\":\"员工工资统计\",\"url\":\"generator/empsalaryrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '7', '0:0:0:0:0:0:0:1', '2021-02-18 21:13:30');
INSERT INTO `sys_log` VALUES ('28', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":102,\"parentId\":0,\"name\":\"财务信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"\",\"orderNum\":0}]', '4', '0:0:0:0:0:0:0:1', '2021-02-18 21:14:36');
INSERT INTO `sys_log` VALUES ('29', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":71,\"parentId\":102,\"name\":\"员工工资统计\",\"url\":\"generator/empsalaryrecord\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', '6', '0:0:0:0:0:0:0:1', '2021-02-18 21:14:52');
INSERT INTO `sys_log` VALUES ('30', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":102,\"parentId\":0,\"name\":\"财务信息\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"tubiao\",\"orderNum\":0}]', '5', '0:0:0:0:0:0:0:1', '2021-02-18 21:15:19');
INSERT INTO `sys_log` VALUES ('31', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"医护人员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[103,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,81,82,83,84,85,86,87,88,89,90,-666666],\"createTime\":\"Feb 18, 2021 9:54:56 PM\"}]', '2117', '0:0:0:0:0:0:0:1', '2021-02-18 21:54:57');
INSERT INTO `sys_log` VALUES ('32', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"医院领导\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,101,31,32,33,34,35,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,102,71,72,73,74,75,76,77,78,79,80,103,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,81,82,83,84,85,86,87,88,89,90,105,91,92,93,94,95,96,97,98,99,100,-666666],\"createTime\":\"Feb 18, 2021 9:55:46 PM\"}]', '373', '0:0:0:0:0:0:0:1', '2021-02-18 21:55:46');
INSERT INTO `sys_log` VALUES ('33', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"财务人员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[56,57,58,59,60,61,62,63,64,65,102,71,72,73,74,75,76,77,78,79,80,103,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,81,82,83,84,85,86,87,88,89,90,-666666,101],\"createTime\":\"Feb 18, 2021 9:57:42 PM\"}]', '175', '0:0:0:0:0:0:0:1', '2021-02-18 21:57:42');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO `sys_menu` VALUES ('31', '101', '活动公告', 'generator/activity', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'generator:activity:list,generator:activity:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'generator:activity:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'generator:activity:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'generator:activity:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('36', '103', '老人出入院记录', 'generator/gafferadmissionrecord', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'generator:gafferadmissionrecord:list,generator:gafferadmissionrecord:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'generator:gafferadmissionrecord:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'generator:gafferadmissionrecord:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'generator:gafferadmissionrecord:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '103', '老人信息', 'generator/gafferinfo', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('42', '41', '查看', null, 'generator:gafferinfo:list,generator:gafferinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '41', '新增', null, 'generator:gafferinfo:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '41', '修改', null, 'generator:gafferinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('45', '41', '删除', null, 'generator:gafferinfo:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '103', '老人额外消费', 'generator/gafferpayment', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('47', '46', '查看', null, 'generator:gafferpayment:list,generator:gafferpayment:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '46', '新增', null, 'generator:gafferpayment:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '46', '修改', null, 'generator:gafferpayment:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('50', '46', '删除', null, 'generator:gafferpayment:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '101', '查房记录', 'generator/roomcheckrecord', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('52', '51', '查看', null, 'generator:roomcheckrecord:list,generator:roomcheckrecord:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '51', '新增', null, 'generator:roomcheckrecord:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('54', '51', '修改', null, 'generator:roomcheckrecord:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('55', '51', '删除', null, 'generator:roomcheckrecord:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '101', '员工考勤', 'generator/empattendance', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('57', '56', '查看', null, 'generator:empattendance:list,generator:empattendance:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '56', '新增', null, 'generator:empattendance:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('59', '56', '修改', null, 'generator:empattendance:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('60', '56', '删除', null, 'generator:empattendance:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('61', '101', '员工信息', 'generator/empinfo', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('62', '61', '查看', null, 'generator:empinfo:list,generator:empinfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('63', '61', '新增', null, 'generator:empinfo:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('64', '61', '修改', null, 'generator:empinfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('65', '61', '删除', null, 'generator:empinfo:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('66', '101', '员工评级', 'generator/emprating', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('67', '66', '查看', null, 'generator:emprating:list,generator:emprating:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('68', '66', '新增', null, 'generator:emprating:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('69', '66', '修改', null, 'generator:emprating:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('70', '66', '删除', null, 'generator:emprating:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('71', '102', '员工工资统计', 'generator/empsalaryrecord', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('72', '71', '查看', null, 'generator:empsalaryrecord:list,generator:empsalaryrecord:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('73', '71', '新增', null, 'generator:empsalaryrecord:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('74', '71', '修改', null, 'generator:empsalaryrecord:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('75', '71', '删除', null, 'generator:empsalaryrecord:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('76', '102', '财务收支', 'generator/financepayment', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('77', '76', '查看', null, 'generator:financepayment:list,generator:financepayment:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('78', '76', '新增', null, 'generator:financepayment:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('79', '76', '修改', null, 'generator:financepayment:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('80', '76', '删除', null, 'generator:financepayment:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('81', '103', '老人缴费记录', 'generator/gafferpaymentrecord', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('82', '81', '查看', null, 'generator:gafferpaymentrecord:list,generator:gafferpaymentrecord:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('83', '81', '新增', null, 'generator:gafferpaymentrecord:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('84', '81', '修改', null, 'generator:gafferpaymentrecord:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('85', '81', '删除', null, 'generator:gafferpaymentrecord:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('86', '103', '老人账户充值记录', 'generator/gafferrecharge', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('87', '86', '查看', null, 'generator:gafferrecharge:list,generator:gafferrecharge:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('88', '86', '新增', null, 'generator:gafferrecharge:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('89', '86', '修改', null, 'generator:gafferrecharge:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('90', '86', '删除', null, 'generator:gafferrecharge:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('91', '105', '房间变更', 'generator/roomchange', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('92', '91', '查看', null, 'generator:roomchange:list,generator:roomchange:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('93', '91', '新增', null, 'generator:roomchange:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('94', '91', '修改', null, 'generator:roomchange:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('95', '91', '删除', null, 'generator:roomchange:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('96', '105', '房间信息', 'generator/roominfo', null, '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('97', '96', '查看', null, 'generator:roominfo:list,generator:roominfo:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('98', '96', '新增', null, 'generator:roominfo:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('99', '96', '修改', null, 'generator:roominfo:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('100', '96', '删除', null, 'generator:roominfo:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('101', '0', '医护人员', '', '', '0', 'shouye', '0');
INSERT INTO `sys_menu` VALUES ('102', '0', '财务信息', '', '', '0', 'tubiao', '0');
INSERT INTO `sys_menu` VALUES ('103', '0', '老人信息', '', '', '0', 'admin', '0');
INSERT INTO `sys_menu` VALUES ('105', '0', '房间信息', '', '', '0', 'shouye', '0');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '医护人员', '', '1', '2021-02-18 21:54:56');
INSERT INTO `sys_role` VALUES ('2', '医院领导', '', '1', '2021-02-18 21:55:46');
INSERT INTO `sys_role` VALUES ('3', '财务人员', '', '1', '2021-02-18 21:57:42');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '45');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '46');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '47');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '49');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '50');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '-666666');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('32', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('34', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('35', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('36', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('37', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('40', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('42', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('43', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('44', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('45', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('46', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('47', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('48', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('49', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('50', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('51', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('52', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('53', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('54', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('55', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('56', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('57', '2', '101');
INSERT INTO `sys_role_menu` VALUES ('58', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('59', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('60', '2', '33');
INSERT INTO `sys_role_menu` VALUES ('61', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('62', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('63', '2', '51');
INSERT INTO `sys_role_menu` VALUES ('64', '2', '52');
INSERT INTO `sys_role_menu` VALUES ('65', '2', '53');
INSERT INTO `sys_role_menu` VALUES ('66', '2', '54');
INSERT INTO `sys_role_menu` VALUES ('67', '2', '55');
INSERT INTO `sys_role_menu` VALUES ('68', '2', '56');
INSERT INTO `sys_role_menu` VALUES ('69', '2', '57');
INSERT INTO `sys_role_menu` VALUES ('70', '2', '58');
INSERT INTO `sys_role_menu` VALUES ('71', '2', '59');
INSERT INTO `sys_role_menu` VALUES ('72', '2', '60');
INSERT INTO `sys_role_menu` VALUES ('73', '2', '61');
INSERT INTO `sys_role_menu` VALUES ('74', '2', '62');
INSERT INTO `sys_role_menu` VALUES ('75', '2', '63');
INSERT INTO `sys_role_menu` VALUES ('76', '2', '64');
INSERT INTO `sys_role_menu` VALUES ('77', '2', '65');
INSERT INTO `sys_role_menu` VALUES ('78', '2', '66');
INSERT INTO `sys_role_menu` VALUES ('79', '2', '67');
INSERT INTO `sys_role_menu` VALUES ('80', '2', '68');
INSERT INTO `sys_role_menu` VALUES ('81', '2', '69');
INSERT INTO `sys_role_menu` VALUES ('82', '2', '70');
INSERT INTO `sys_role_menu` VALUES ('83', '2', '102');
INSERT INTO `sys_role_menu` VALUES ('84', '2', '71');
INSERT INTO `sys_role_menu` VALUES ('85', '2', '72');
INSERT INTO `sys_role_menu` VALUES ('86', '2', '73');
INSERT INTO `sys_role_menu` VALUES ('87', '2', '74');
INSERT INTO `sys_role_menu` VALUES ('88', '2', '75');
INSERT INTO `sys_role_menu` VALUES ('89', '2', '76');
INSERT INTO `sys_role_menu` VALUES ('90', '2', '77');
INSERT INTO `sys_role_menu` VALUES ('91', '2', '78');
INSERT INTO `sys_role_menu` VALUES ('92', '2', '79');
INSERT INTO `sys_role_menu` VALUES ('93', '2', '80');
INSERT INTO `sys_role_menu` VALUES ('94', '2', '103');
INSERT INTO `sys_role_menu` VALUES ('95', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('96', '2', '37');
INSERT INTO `sys_role_menu` VALUES ('97', '2', '38');
INSERT INTO `sys_role_menu` VALUES ('98', '2', '39');
INSERT INTO `sys_role_menu` VALUES ('99', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('100', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('101', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('102', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('103', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('104', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('105', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('106', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('107', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('108', '2', '49');
INSERT INTO `sys_role_menu` VALUES ('109', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('110', '2', '81');
INSERT INTO `sys_role_menu` VALUES ('111', '2', '82');
INSERT INTO `sys_role_menu` VALUES ('112', '2', '83');
INSERT INTO `sys_role_menu` VALUES ('113', '2', '84');
INSERT INTO `sys_role_menu` VALUES ('114', '2', '85');
INSERT INTO `sys_role_menu` VALUES ('115', '2', '86');
INSERT INTO `sys_role_menu` VALUES ('116', '2', '87');
INSERT INTO `sys_role_menu` VALUES ('117', '2', '88');
INSERT INTO `sys_role_menu` VALUES ('118', '2', '89');
INSERT INTO `sys_role_menu` VALUES ('119', '2', '90');
INSERT INTO `sys_role_menu` VALUES ('120', '2', '105');
INSERT INTO `sys_role_menu` VALUES ('121', '2', '91');
INSERT INTO `sys_role_menu` VALUES ('122', '2', '92');
INSERT INTO `sys_role_menu` VALUES ('123', '2', '93');
INSERT INTO `sys_role_menu` VALUES ('124', '2', '94');
INSERT INTO `sys_role_menu` VALUES ('125', '2', '95');
INSERT INTO `sys_role_menu` VALUES ('126', '2', '96');
INSERT INTO `sys_role_menu` VALUES ('127', '2', '97');
INSERT INTO `sys_role_menu` VALUES ('128', '2', '98');
INSERT INTO `sys_role_menu` VALUES ('129', '2', '99');
INSERT INTO `sys_role_menu` VALUES ('130', '2', '100');
INSERT INTO `sys_role_menu` VALUES ('131', '2', '-666666');
INSERT INTO `sys_role_menu` VALUES ('132', '3', '56');
INSERT INTO `sys_role_menu` VALUES ('133', '3', '57');
INSERT INTO `sys_role_menu` VALUES ('134', '3', '58');
INSERT INTO `sys_role_menu` VALUES ('135', '3', '59');
INSERT INTO `sys_role_menu` VALUES ('136', '3', '60');
INSERT INTO `sys_role_menu` VALUES ('137', '3', '61');
INSERT INTO `sys_role_menu` VALUES ('138', '3', '62');
INSERT INTO `sys_role_menu` VALUES ('139', '3', '63');
INSERT INTO `sys_role_menu` VALUES ('140', '3', '64');
INSERT INTO `sys_role_menu` VALUES ('141', '3', '65');
INSERT INTO `sys_role_menu` VALUES ('142', '3', '102');
INSERT INTO `sys_role_menu` VALUES ('143', '3', '71');
INSERT INTO `sys_role_menu` VALUES ('144', '3', '72');
INSERT INTO `sys_role_menu` VALUES ('145', '3', '73');
INSERT INTO `sys_role_menu` VALUES ('146', '3', '74');
INSERT INTO `sys_role_menu` VALUES ('147', '3', '75');
INSERT INTO `sys_role_menu` VALUES ('148', '3', '76');
INSERT INTO `sys_role_menu` VALUES ('149', '3', '77');
INSERT INTO `sys_role_menu` VALUES ('150', '3', '78');
INSERT INTO `sys_role_menu` VALUES ('151', '3', '79');
INSERT INTO `sys_role_menu` VALUES ('152', '3', '80');
INSERT INTO `sys_role_menu` VALUES ('153', '3', '103');
INSERT INTO `sys_role_menu` VALUES ('154', '3', '36');
INSERT INTO `sys_role_menu` VALUES ('155', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('156', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('157', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('158', '3', '40');
INSERT INTO `sys_role_menu` VALUES ('159', '3', '41');
INSERT INTO `sys_role_menu` VALUES ('160', '3', '42');
INSERT INTO `sys_role_menu` VALUES ('161', '3', '43');
INSERT INTO `sys_role_menu` VALUES ('162', '3', '44');
INSERT INTO `sys_role_menu` VALUES ('163', '3', '45');
INSERT INTO `sys_role_menu` VALUES ('164', '3', '46');
INSERT INTO `sys_role_menu` VALUES ('165', '3', '47');
INSERT INTO `sys_role_menu` VALUES ('166', '3', '48');
INSERT INTO `sys_role_menu` VALUES ('167', '3', '49');
INSERT INTO `sys_role_menu` VALUES ('168', '3', '50');
INSERT INTO `sys_role_menu` VALUES ('169', '3', '81');
INSERT INTO `sys_role_menu` VALUES ('170', '3', '82');
INSERT INTO `sys_role_menu` VALUES ('171', '3', '83');
INSERT INTO `sys_role_menu` VALUES ('172', '3', '84');
INSERT INTO `sys_role_menu` VALUES ('173', '3', '85');
INSERT INTO `sys_role_menu` VALUES ('174', '3', '86');
INSERT INTO `sys_role_menu` VALUES ('175', '3', '87');
INSERT INTO `sys_role_menu` VALUES ('176', '3', '88');
INSERT INTO `sys_role_menu` VALUES ('177', '3', '89');
INSERT INTO `sys_role_menu` VALUES ('178', '3', '90');
INSERT INTO `sys_role_menu` VALUES ('179', '3', '-666666');
INSERT INTO `sys_role_menu` VALUES ('180', '3', '101');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '4bb34a883dc9fce178d295b24943f73d', '2021-02-19 08:58:37', '2021-02-18 20:58:37');

-- ----------------------------
-- Table structure for tb_activity
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity`;
CREATE TABLE `tb_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) NOT NULL COMMENT '标题',
  `content` varchar(25) NOT NULL COMMENT '内容',
  `operation_user_id` varchar(512) NOT NULL COMMENT '发布人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动公告';

-- ----------------------------
-- Records of tb_activity
-- ----------------------------

-- ----------------------------
-- Table structure for tb_emp_attendance
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp_attendance`;
CREATE TABLE `tb_emp_attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) NOT NULL COMMENT '员工id',
  `attendance_date` varchar(25) NOT NULL COMMENT '考勤所属年月',
  `should_day` tinyint(2) NOT NULL COMMENT '应到天数',
  `actual_day` tinyint(2) NOT NULL COMMENT '实到天数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工考勤';

-- ----------------------------
-- Records of tb_emp_attendance
-- ----------------------------

-- ----------------------------
-- Table structure for tb_emp_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp_info`;
CREATE TABLE `tb_emp_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(25) NOT NULL COMMENT '编号',
  `name` varchar(25) NOT NULL COMMENT '姓名',
  `sex` varchar(5) NOT NULL COMMENT '性别',
  `age` bigint(11) NOT NULL COMMENT '年龄',
  `department` varchar(25) NOT NULL COMMENT '科室',
  `job` varchar(20) NOT NULL COMMENT '职称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `pic` varchar(512) NOT NULL COMMENT '相片',
  `basic_salary` decimal(10,0) NOT NULL COMMENT '基本工资',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工信息';

-- ----------------------------
-- Records of tb_emp_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_emp_rating
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp_rating`;
CREATE TABLE `tb_emp_rating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) NOT NULL COMMENT '员工id',
  `user_id` bigint(20) NOT NULL COMMENT '评价人id',
  `star` varchar(5) NOT NULL COMMENT '星级',
  `star_date` varchar(25) NOT NULL COMMENT '评级所属年月',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工评级';

-- ----------------------------
-- Records of tb_emp_rating
-- ----------------------------

-- ----------------------------
-- Table structure for tb_emp_salary_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_emp_salary_record`;
CREATE TABLE `tb_emp_salary_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) NOT NULL COMMENT '员工id',
  `amount` decimal(10,2) NOT NULL COMMENT '本月基本工资',
  `star_amount` decimal(10,2) NOT NULL COMMENT '考评工资',
  `attendance_amount` decimal(10,2) NOT NULL COMMENT '考勤工资',
  `total_amount` decimal(10,2) NOT NULL COMMENT '总工资',
  `operation_user_id` varchar(512) NOT NULL COMMENT '操作人',
  `record_date` varchar(25) NOT NULL COMMENT '记录所属年月',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工工资统计';

-- ----------------------------
-- Records of tb_emp_salary_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_finance_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_finance_payment`;
CREATE TABLE `tb_finance_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(5) NOT NULL COMMENT '收/支',
  `content` varchar(20) NOT NULL COMMENT '内容',
  `amount` decimal(10,0) NOT NULL COMMENT '费用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='财务收支';

-- ----------------------------
-- Records of tb_finance_payment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_gaffer_admission_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaffer_admission_record`;
CREATE TABLE `tb_gaffer_admission_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gaffer_id` bigint(20) NOT NULL COMMENT '老人账户id',
  `type` varchar(25) NOT NULL COMMENT '出院/入院',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老人出入院记录';

-- ----------------------------
-- Records of tb_gaffer_admission_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_gaffer_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaffer_info`;
CREATE TABLE `tb_gaffer_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL COMMENT '姓名',
  `sex` varchar(5) NOT NULL COMMENT '性别',
  `age` bigint(11) NOT NULL COMMENT '年龄',
  `telephone` varchar(255) NOT NULL COMMENT '电话',
  `guardian_name` varchar(25) NOT NULL COMMENT '监护人姓名',
  `guardian_telephone` varchar(11) NOT NULL COMMENT '监护人电话',
  `address` varchar(255) NOT NULL COMMENT '家庭住址',
  `pic` varchar(512) NOT NULL COMMENT '相片',
  `wallet_amount` decimal(10,0) NOT NULL COMMENT '钱包余额',
  `health_record` longtext NOT NULL COMMENT '健康档案',
  `operation_user_id` varchar(512) NOT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老人信息';

-- ----------------------------
-- Records of tb_gaffer_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_gaffer_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaffer_payment`;
CREATE TABLE `tb_gaffer_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gaffer_id` bigint(20) NOT NULL COMMENT '老人账户id',
  `amount` decimal(10,2) NOT NULL COMMENT '消费金额',
  `detail` bigint(11) NOT NULL COMMENT '消费详情',
  `status` varchar(255) NOT NULL COMMENT '审核状态：0-提交 1-审核通过 2-审核拒绝',
  `operation_id` bigint(20) NOT NULL COMMENT '提交医护id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老人额外消费';

-- ----------------------------
-- Records of tb_gaffer_payment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_gaffer_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaffer_payment_record`;
CREATE TABLE `tb_gaffer_payment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gaffer_id` bigint(20) NOT NULL COMMENT '老人账户id',
  `amount` decimal(10,2) NOT NULL COMMENT '缴费金额',
  `payment_date` varchar(25) NOT NULL COMMENT '缴费所属年月',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老人缴费记录';

-- ----------------------------
-- Records of tb_gaffer_payment_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_gaffer_recharge
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaffer_recharge`;
CREATE TABLE `tb_gaffer_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gaffer_id` bigint(20) NOT NULL COMMENT '老人账户id',
  `amount` decimal(10,2) NOT NULL COMMENT '充值金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老人账户充值记录';

-- ----------------------------
-- Records of tb_gaffer_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for tb_room_change
-- ----------------------------
DROP TABLE IF EXISTS `tb_room_change`;
CREATE TABLE `tb_room_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `the_aged_id` bigint(20) NOT NULL COMMENT '所属老人id',
  `before_room_id` bigint(20) NOT NULL COMMENT '变更前房间id',
  `now_room_id` bigint(20) NOT NULL COMMENT '变更后房间id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房间变更';

-- ----------------------------
-- Records of tb_room_change
-- ----------------------------

-- ----------------------------
-- Table structure for tb_room_check_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_room_check_record`;
CREATE TABLE `tb_room_check_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gaffer_id` bigint(20) NOT NULL COMMENT '老人账户id',
  `operation_id` bigint(20) NOT NULL COMMENT '查房人id',
  `content` varchar(25) NOT NULL COMMENT '查房内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='查房记录';

-- ----------------------------
-- Records of tb_room_check_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_room_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_room_info`;
CREATE TABLE `tb_room_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_no` varchar(255) NOT NULL COMMENT '房间号',
  `type` varchar(25) NOT NULL COMMENT '房间类型',
  `price` decimal(10,2) NOT NULL COMMENT '房间价格（按天）',
  `status` tinyint(1) NOT NULL COMMENT '当前状态：0-空置 1-占用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房间信息';

-- ----------------------------
-- Records of tb_room_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
