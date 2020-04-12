/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : app_rest

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-04-12 16:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `app_exception_log`;
CREATE TABLE `app_exception_log` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BRAND` varchar(10) DEFAULT NULL COMMENT '产品品牌',
  `PRODUCT` varchar(10) DEFAULT NULL COMMENT '产品型号',
  `RELEASES` varchar(10) DEFAULT NULL COMMENT 'Android release版本',
  `SDK` varchar(10) DEFAULT NULL COMMENT 'Android API版本（String类型）',
  `EX_MSG` text COMMENT '异常信息',
  `LAT` varchar(10) DEFAULT NULL COMMENT '纬度',
  `LNG` varchar(10) DEFAULT NULL COMMENT '经度',
  `CREATE_TIME` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `FEED_BACK_MSG` varchar(1000) DEFAULT NULL COMMENT 'APP异常-反馈信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app异常日志表';

-- ----------------------------
-- Records of app_exception_log
-- ----------------------------
INSERT INTO `app_exception_log` VALUES ('8a929cb2715e2deb01715e4d0dc70000', 'nubia', 'NX612J', '7.1.2', '25', null, null, null, null, '前账号：macaiyun--垃圾');
INSERT INTO `app_exception_log` VALUES ('8a929cb2715e2deb01715e5100310001', 'nubia', 'NX612J', '7.1.2', '25', null, null, null, '2020-04-09 17:41:38', '前账号：macaiyun--错了');
INSERT INTO `app_exception_log` VALUES ('8a929cb2715e2deb01715e59bb730002', 'nubia', 'NX612J', '7.1.2', '25', 'java.lang.RuntimeException: Unable to start activity ComponentInfo{com.zdww.lzshzz/com.zdww.main.activity.HomeActivity}: java.lang.ArithmeticException: divide by zero\n	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2931)\n	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2992)\n	at android.app.ActivityThread.-wrap13(ActivityThread.java)\n	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1642)\n	at android.os.Handler.dispatchMessage(Handler.java:102)\n	at android.os.Looper.loop(Looper.java:172)\n	at android.app.ActivityThread.main(ActivityThread.java:6564)\n	at java.lang.reflect.Method.invoke(Native Method)\n	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:902)\n	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:792)\nCaused by: java.lang.ArithmeticException: divide by zero\n	at com.zdww.main.activity.HomeActivity.initView(HomeActivity.kt:86)\n	at com.zdww.basecommon.base.BaseActivity.onCreate(BaseActivity.kt:75)\n	at android.app.Activity.performCreate(Activity.java:6768)\n	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1132)\n	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2884)\n	... 9 more\n', null, null, '2020-04-09 17:51:11', '当前账号：macaiyun--错了');

-- ----------------------------
-- Table structure for app_version
-- ----------------------------
DROP TABLE IF EXISTS `app_version`;
CREATE TABLE `app_version` (
  `PK_ID` varchar(32) NOT NULL,
  `APP_VERSION` varchar(255) DEFAULT NULL COMMENT 'app版本',
  `FORCE_UPDATE` varchar(12) DEFAULT NULL COMMENT '是否强制更新',
  `APP_SIZE` varchar(64) DEFAULT NULL COMMENT 'app大小',
  `DESCRIPTION` varchar(2000) NOT NULL COMMENT '更新描述，两条及以上内容之间用|或者回车隔开',
  `UPDATE_TIME` varchar(19) NOT NULL COMMENT '更新时间',
  `FILE_URL` varchar(255) DEFAULT NULL,
  `IS_PUBLISH` char(2) DEFAULT NULL COMMENT '是否发布 1 以发布 0 未发布',
  `APP_FLAG` char(2) DEFAULT NULL COMMENT '安卓和iOS区分  0:android   1:ios',
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='android APP版本信息维护表';

-- ----------------------------
-- Records of app_version
-- ----------------------------
INSERT INTO `app_version` VALUES ('1', '1.0.0.1', 'false', '42.17', '1.修改一张图综合查询功能|2.修改事件管理调运摄像机问题', '2020-03-25 18:59:59', '/APK/2020-03-25/lzshzz.apk', '1', '0');
INSERT INTO `app_version` VALUES ('8a9201a8713da78101713f2d3be80002', '5.9', 'false', '1.6', '', '', '', '0', '0');
INSERT INTO `app_version` VALUES ('8a9204b17110c97101711116e2c50001', '1.0.0.25', 'false', '1.5', 'uuuu', '2020-03-25 17:47:20', '/APK/2020-03-25/lzshzz.apk', '0', '1');
INSERT INTO `app_version` VALUES ('ffd5cd8d51f643908a064a6a52fd1466', '2.3.6', 'false', '45.111', '1、优化用户体验/n2、加强用户信息安全', '2020-03-25 18:59:58', '', '1', '1');

-- ----------------------------
-- Table structure for bas_file_event
-- ----------------------------
DROP TABLE IF EXISTS `bas_file_event`;
CREATE TABLE `bas_file_event` (
  `pk_id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键id',
  `bus_id` varchar(64) DEFAULT NULL COMMENT '事件id',
  `file_old_name` varchar(255) DEFAULT NULL COMMENT '文件原名',
  `file_new_name` varchar(255) DEFAULT NULL COMMENT '重命名文件',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(4) DEFAULT NULL COMMENT '文件类型 1.图片 2 视频 3.文件',
  `is_thum` varchar(2) DEFAULT NULL COMMENT '是否缩略图：0不是；1是',
  `file_source` varchar(40) DEFAULT NULL COMMENT '附件来源1：新增2：上传资料03：驳回上传资料',
  PRIMARY KEY (`pk_id`),
  KEY `bas_file_id` (`bus_id`) USING BTREE,
  KEY `bas_file_type` (`file_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_file_event
-- ----------------------------

-- ----------------------------
-- Table structure for bas_file_head
-- ----------------------------
DROP TABLE IF EXISTS `bas_file_head`;
CREATE TABLE `bas_file_head` (
  `pk_id` varchar(32) NOT NULL COMMENT '主键',
  `bus_id` varchar(32) DEFAULT NULL COMMENT '业务主键',
  `file_old_name` varchar(255) DEFAULT NULL COMMENT '原文件名',
  `file_new_name` varchar(255) DEFAULT NULL COMMENT '重命名后的文件名',
  `file_url` varchar(512) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(255) DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`pk_id`),
  KEY `bus_id` (`bus_id`),
  KEY `head_id_2` (`bus_id`),
  KEY `bus_id_2` (`bus_id`),
  KEY `bus_id_3` (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='河长头像表';

-- ----------------------------
-- Records of bas_file_head
-- ----------------------------

-- ----------------------------
-- Table structure for bas_river_head
-- ----------------------------
DROP TABLE IF EXISTS `bas_river_head`;
CREATE TABLE `bas_river_head` (
  `RIVER_HEAD_ID` varchar(32) NOT NULL COMMENT '河长id',
  `LOGIN_NAME` varchar(32) DEFAULT NULL COMMENT '登录账号',
  `NAME` varchar(32) DEFAULT NULL COMMENT '河长名称',
  `LINK_TEL` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `RIVER_POST` varchar(32) DEFAULT NULL COMMENT '河长级别',
  `ADMIN_POST` varchar(128) DEFAULT NULL COMMENT '行政职务',
  `HEAD_LEVEL` varchar(255) DEFAULT NULL COMMENT '河段名称',
  `prev_river_head_name` varchar(16) DEFAULT NULL COMMENT '上级河长姓名',
  `prev_river_head_id` varchar(32) DEFAULT NULL COMMENT '上级河长id',
  `ACCOUNT_ID` varchar(32) DEFAULT NULL COMMENT '用户表的id',
  `area_code` varchar(16) DEFAULT NULL COMMENT '地区编号',
  `state` varchar(2) DEFAULT NULL COMMENT '0：河长；1：治河管理人员',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `WORK_TEL` varchar(12) DEFAULT NULL COMMENT '工作电话',
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `LON` varchar(32) DEFAULT NULL COMMENT '经度',
  `LAT` varchar(32) DEFAULT NULL COMMENT '纬度',
  `DEPT_CODE` varchar(32) DEFAULT NULL COMMENT '部门code',
  `ADMIN_POST_SORT` varchar(2) DEFAULT NULL COMMENT '行政级别编号',
  `AREA_NAME` varchar(64) DEFAULT NULL COMMENT '地区名称',
  `HEAD_ID_NUMBER` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `MAIN_LEADER` varchar(32) DEFAULT NULL COMMENT '主要领导(1:党委主要领导;2:政府主要领导;3:人大主要领导;4:政协主要领导;9:其他)',
  `ADMIN_LEVEL` varchar(2) DEFAULT NULL COMMENT '行政级别(1:省级;2:副省级;3:地市级;4:副地市级;5:县级;6:副县级;7:科级;8:副科级;9:股级;10:副股级;99其他;)',
  `IS_NOTICE` varchar(2) DEFAULT NULL COMMENT '是否已公告',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `IS_FULL_TIME` varchar(2) DEFAULT NULL COMMENT '是否专职',
  `HEAD_STATE` varchar(255) DEFAULT NULL COMMENT '状态：0已删除数据(历任河长) 1正常数据（现任河长）',
  `POST_SORT` int(3) DEFAULT NULL COMMENT '河长位置排序字段',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `UNIT_CATEGORY` int(2) DEFAULT NULL COMMENT '单位类别（1：党委、政府，2：人大、政协，9：其他）',
  `ENTRY_INTO_FORCE_TIME` varchar(19) DEFAULT NULL COMMENT '生效时间',
  `RIVER_LAKE_HEAD` varchar(4) DEFAULT NULL COMMENT '河湖长（1：河长；2：湖长；1，2：河长湖长）',
  `SLB_HEAD_CODE` varchar(32) DEFAULT NULL COMMENT '水利部河长编码',
  `IS_GENDER` varchar(4) DEFAULT NULL COMMENT '性别（1：男；0：女）',
  `DATE_OF_BIRTH` varchar(19) DEFAULT NULL COMMENT '出生日期',
  `EDUCATION` varchar(4) DEFAULT NULL COMMENT '学历（0：小学、1：初中、2：高中、3：专科教育、4：本科教育、5：研究生、6：博士生）',
  `river_head_num` varchar(64) DEFAULT NULL COMMENT '标识现历任河长的字段',
  `RIVER_POST_LEVEL` varchar(255) DEFAULT NULL COMMENT '河湖长级别：1：无，2市级，3县区级，4乡镇级，5村级',
  PRIMARY KEY (`RIVER_HEAD_ID`),
  KEY `idx-areacode` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='河长基本信息表';

-- ----------------------------
-- Records of bas_river_head
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(48) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', null, 'app', 'app', 'password,refresh_token', null, null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('lzshzz', null, '5b80076a74ca62e71f7139d9479f23bb', 'app', 'authorization_code,password,refresh_token,client_credentials', null, null, '-1', '-1', null, null);
INSERT INTO `oauth_client_details` VALUES ('XcWebApp', null, '$2a$10$9bEpZ/hWRQxyr5hn5wHUj.jxFpIrnOmBcWlE/g/0Zp3uNxt9QTh/S', 'app', 'authorization_code,password,refresh_token,client_credentials', null, null, '43200', '43200', null, null);

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(48) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
INSERT INTO `oauth_refresh_token` VALUES ('b96e057b4e1c4920428e833db48d4c15', 0xEFBFBDEFBFBD00057372004C6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744578706972696E674F417574683252656672657368546F6B656E2FEFBFBD4763EFBFBDEFBFBDC9B70200014C000A65787069726174696F6E7400104C6A6176612F7574696C2F446174653B787200446F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F6D6D6F6E2E44656661756C744F417574683252656672657368546F6B656E73EFBFBD0E0A6354EFBFBD5E0200014C000576616C75657400124C6A6176612F6C616E672F537472696E673B787074002462303132643438372D613930382D343361662D613865662D3533633533393963386264367372000E6A6176612E7574696C2E44617465686AEFBFBD014B59741903000078707708000001613B74C98E78, 0xEFBFBDEFBFBD0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EEFBFBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374EFBFBD0F2531EFBFBDEFBFBD100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E194200EFBFBDEFBFBD5EEFBFBD1E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C69737478EFBFBDEFBFBD1DEFBFBDEFBFBD61EFBFBD03000149000473697A65787000000009770400000009737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F7269747900000000000001EFBFBD0200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B787074000A524F4C455F61646D696E7371007E000D740006617069646F637371007E000D74000C64617461626173652F6C6F677371007E000D74000673797374656D7371007E000D740008757365722F6164647371007E000D74000B757365722F64656C6574657371007E000D740009757365722F656469747371007E000D740009757365722F766965777371007E000D740008757365724C6973747871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B726564697265637455726971007E000E4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0024787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EEFBFBD7169EFBFBD0200034C0008636C69656E74496471007E000E4C001172657175657374506172616D657465727371007E00224C000573636F706571007E00247870740006776562417070737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170EFBFBDEFBFBD74EFBFBD07420200014C00016D71007E00227870737200116A6176612E7574696C2E486173684D61700507EFBFBDEFBFBDEFBFBD1660EFBFBD03000246000A6C6F6164466163746F724900097468726573686F6C6478703F400000000000037708000000040000000274000A6772616E745F7479706574000870617373776F7264740008757365726E616D6574000561646D696E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574EFBFBD1DEFBFBDD18FEFBFBDEFBFBD550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574EFBFBD6CEFBFBD5AEFBFBDEFBFBD2A1E020000787200116A6176612E7574696C2E48617368536574EFBFBD44EFBFBDEFBFBDEFBFBDEFBFBDEFBFBD340300007870770C000000103F4000000000000174000361707078017371007E0033770C000000103F40000000000000787371007E002A3F40000000000000770800000010000000007870707371007E0033770C000000103F40000000000000787371007E0033770C000000103F40000000000000787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001EFBFBD0200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B0000000977040000000971007E000F71007E001171007E001371007E001571007E001771007E001971007E001B71007E001D71007E001F7871007E003D737200176A6176612E7574696C2E4C696E6B6564486173684D617034EFBFBD4E5C106CEFBFBDEFBFBD0200015A000B6163636573734F726465727871007E002A3F400000000000067708000000080000000271007E002C71007E002D71007E002E71007E002F780070737200326F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657200000000000001EFBFBD0200075A00116163636F756E744E6F6E457870697265645A00106163636F756E744E6F6E4C6F636B65645A001563726564656E7469616C734E6F6E457870697265645A0007656E61626C65644C000B617574686F72697469657371007E00244C000870617373776F726471007E000E4C0008757365726E616D6571007E000E7870010101017371007E0030737200116A6176612E7574696C2E54726565536574DD9850EFBFBDEFBFBDEFBFBD5B0300007870737200466F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657224417574686F72697479436F6D70617261746F7200000000000001EFBFBD020000787077040000000971007E000F71007E001171007E001371007E001571007E001771007E001971007E001B71007E001D71007E001F787074000561646D696E);

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `USER_ACCT_ID` varchar(32) NOT NULL COMMENT '用户账号ID',
  `LOGIN_ACCOUNT` varchar(32) DEFAULT NULL COMMENT '登录帐号',
  `LOGIN_PASSWORD` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `USER_NAME` varchar(32) DEFAULT NULL COMMENT '人员姓名',
  `USER_SEX` char(1) DEFAULT NULL COMMENT '性别:0:男  1:女  2:未知',
  `USER_POSITION` varchar(64) DEFAULT NULL COMMENT '职位',
  `USER_TELE` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `USER_HOME_TELE` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `USER_EMAIL` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `DEPT_ID` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `USER_STATE` char(1) NOT NULL COMMENT '状态：0无效1有效',
  `CREATE_TIME` char(19) NOT NULL COMMENT '创建时间',
  `OPE_ACCT_ID` varchar(32) DEFAULT NULL COMMENT '操作账号ID',
  `SET_ID` varchar(32) DEFAULT NULL,
  `AREA_CODE` varchar(12) DEFAULT NULL COMMENT '行政区划代码',
  `USER_TYPE` char(1) DEFAULT NULL COMMENT '用户类型0：巡河员，1：河长，2：河长办，3：成员单位',
  `DEVICE_ID` varchar(32) DEFAULT '' COMMENT '用户对应的移动端设备ID',
  `UPDATE_TIME` varchar(19) DEFAULT NULL COMMENT '更新时间',
  `ADMIN_POST_SORT` char(2) DEFAULT NULL,
  `POST_SORT` char(16) DEFAULT NULL,
  PRIMARY KEY (`USER_ACCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号表';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', 'admin', '7b4b004f3bee951ba70a63e30d5e8161', '管理员', '1', '', '18152104414', '', '', '8a92a0b364f55ab60164f56843690004', '1', '2015-07-30 18:01:45', null, '1', '620100000000', '2', '0ff096eb4a9135e171910d1395b6c293', null, null, null);
INSERT INTO `sys_account` VALUES ('2', 'admin2', '7b4b004f3bee951ba70a63e30d5e8161', 'admin', '1', '', '18309315291', '', '', '8a80cb816417662b0164181eaa5d0026', '1', '2020-01-08 14:46:04', null, '1', '620100000000', '2', null, null, null, null);

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `DEPT_ID` varchar(32) NOT NULL COMMENT '部门ID',
  `DEPT_NAME` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `DEPT_CODE` varchar(32) DEFAULT NULL COMMENT '部门编码',
  `PARENT_DEPT_CODE` varchar(32) DEFAULT NULL COMMENT '父部门编码  0：为根目标，标识为单位\n规则按0，001,001001这种方式存储',
  `DEPT_STATE` char(1) NOT NULL COMMENT '状态：0无效1有效',
  `DEPT_SEQ` decimal(4,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` char(19) NOT NULL COMMENT '创建时间',
  `OPE_ACCT_ID` varchar(32) DEFAULT NULL COMMENT '操作账号ID',
  `XZQH` varchar(12) DEFAULT NULL COMMENT '行政区划',
  `SET_ID` varchar(32) DEFAULT NULL,
  `AREA_CODE` varchar(12) DEFAULT NULL COMMENT '部门所在行政区划编码',
  `MODIFY_TIME` char(19) DEFAULT NULL COMMENT '修改时间',
  `ORG_CODE` varchar(64) DEFAULT NULL COMMENT '组织机构代码（如甘肃省发展和改革委员会为013896483）',
  `NODE_TYPE` char(1) DEFAULT NULL COMMENT '节点类型  1-区域  2-单位  3-部门或处室  4-下属单位',
  `DEPT_FULL_NAME` varchar(256) DEFAULT NULL COMMENT '机构全名',
  `AREA_TYPE` char(1) DEFAULT NULL COMMENT '区域类型  1-省级  2-市（州）级  3-区县  4-乡镇街道  5-其他',
  PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('8a80cb816417662b0164181eaa5d0026', '城关区河长办', '0010020010021000', '001002001002', '1', '0', '2018-06-19 20:57:00', '', '620102000000', '1', '', '', '', '5', '', '');
INSERT INTO `sys_department` VALUES ('8a92a0b364f55ab60164f56843690004', '兰州市河长办', '0010020010011000', '001002001001', '1', '0', '2018-08-01 20:13:28', '', '620100000000', '', '', '', '', '5', '', '');

-- ----------------------------
-- Table structure for sys_districts
-- ----------------------------
DROP TABLE IF EXISTS `sys_districts`;
CREATE TABLE `sys_districts` (
  `RES_ID` varchar(12) NOT NULL,
  `RES_PART_ID` varchar(12) DEFAULT NULL,
  `RES_LEVEL` varchar(32) DEFAULT NULL,
  `RES_NAME` varchar(60) DEFAULT NULL COMMENT '区县名',
  `SORT_CODE` char(3) DEFAULT NULL COMMENT 'qu',
  `STATE` char(1) DEFAULT NULL,
  `REMARK` varchar(64) DEFAULT NULL,
  `STRUCT_FULLNAME` text,
  `LAT` varchar(32) DEFAULT NULL COMMENT '纬度',
  `LNG` varchar(32) DEFAULT NULL COMMENT '经度',
  `RES_ENAME` varchar(32) DEFAULT NULL COMMENT '市州县英文名',
  `RES_SUB_ID` varchar(12) DEFAULT NULL,
  `UPDATE_TIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`RES_ID`),
  KEY `idx_res_part_id` (`RES_PART_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_districts
-- ----------------------------
INSERT INTO `sys_districts` VALUES ('620100000000', '', '2', '兰州市', '11', '1', null, '甘肃省兰州市', '36.07914', '103.82213', '兰州市', '6201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102000000', '620100000000', '3', '城关区', '99', '1', null, '甘肃省兰州市城关区', '36.054008', '103.851571', '城关区', '620102', '2019-10-31 16:46:03');
INSERT INTO `sys_districts` VALUES ('620102001000', '620102000000', '4', '酒泉路街道', '99', '1', null, '甘肃省兰州市城关区酒泉路街道', '36.055782', '103.837431', '酒泉路街道', '620102001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102001001', '620102001000', '5', '畅家巷社区居委会', '99', '1', null, '甘肃省兰州市城关区酒泉路街道畅家巷社区居委会', '36.055716', '103.840029', '畅家巷社区居委会', '620102001001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102001002', '620102001000', '5', '张家园社区居委会', '99', '1', null, '甘肃省兰州市城关区酒泉路街道张家园社区居委会', '36.051931', '103.840477', '张家园社区居委会', '620102001002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102001003', '620102001000', '5', '中街子社区居委会', '99', '1', null, '甘肃省兰州市城关区酒泉路街道中街子社区居委会', '36.058678', '103.835116', '中街子社区居委会', '620102001003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102001004', '620102001000', '5', '南稍门社区居委会', '99', '1', null, '甘肃省兰州市城关区酒泉路街道南稍门社区居委会', '36.058771', '103.837478', '南稍门社区居委会', '620102001004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102001005', '620102001000', '5', '杨家园社区居委会', '99', '1', null, '甘肃省兰州市城关区酒泉路街道杨家园社区居委会', '36.055782', '103.837431', '杨家园社区居委会', '620102001005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002000', '620102000000', '4', '张掖路街道', '99', '1', null, '甘肃省兰州市城关区张掖路街道', '36.065885', '103.832587', '张掖路街道', '620102002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002001', '620102002000', '5', '陇西路社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道陇西路社区居委会', '36.064908', '103.82899', '陇西路社区居委会', '620102002001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002002', '620102002000', '5', '山字石社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道山字石社区居委会', '36.062914', '103.835729', '山字石社区居委会', '620102002002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002003', '620102002000', '5', '贡元巷社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道贡元巷社区居委会', '36.062914', '103.835729', '贡元巷社区居委会', '620102002003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002004', '620102002000', '5', '曹家厅社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道曹家厅社区居委会', '36.061574', '103.835169', '曹家厅社区居委会', '620102002004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002005', '620102002000', '5', '大众巷社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道大众巷社区居委会', '36.067464', '103.828662', '大众巷社区居委会', '620102002005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102002006', '620102002000', '5', '金塔巷社区居委会', '99', '1', null, '甘肃省兰州市城关区张掖路街道金塔巷社区居委会', '36.061901', '103.828963', '金塔巷社区居委会', '620102002006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003000', '620102000000', '4', '雁南街道', '99', '1', null, '甘肃省兰州市城关区雁南街道', '36.06268', '103.877702', '雁南街道', '620102003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003001', '620102003000', '5', '雁宁路社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道雁宁路社区居委会', '36.068646', '103.862232', '雁宁路社区居委会', '620102003001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003002', '620102003000', '5', '滩尖子社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道滩尖子社区居委会', '36.071544', '103.865541', '滩尖子社区居委会', '620102003002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003003', '620102003000', '5', '大雁滩社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道大雁滩社区居委会', '36.063854', '103.875717', '大雁滩社区居委会', '620102003003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003004', '620102003000', '5', '沙洼河社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道沙洼河社区居委会', '36.06576', '103.885007', '沙洼河社区居委会', '620102003004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003005', '620102003000', '5', '张苏滩社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道张苏滩社区居委会', '36.060018', '103.882594', '张苏滩社区居委会', '620102003005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003006', '620102003000', '5', '天庆嘉园社区', '99', '1', null, '甘肃省兰州市城关区雁南街道天庆嘉园社区', '36.063213', '103.887356', '天庆嘉园社区', '620102003006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003007', '620102003000', '5', '南河社区居委会', '99', '1', null, '甘肃省兰州市城关区雁南街道南河社区居委会', '36.06268', '103.877702', '南河社区居委会', '620102003007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003201', '620102003000', '5', '张苏滩村民委员会', '99', '1', null, '甘肃省兰州市城关区雁南街道张苏滩村民委员会', '36.060018', '103.882594', '张苏滩村民委员会', '620102003201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003202', '620102003000', '5', '滩尖子村民委员会', '99', '1', null, '甘肃省兰州市城关区雁南街道滩尖子村民委员会', '36.071544', '103.865541', '滩尖子村民委员会', '620102003202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003203', '620102003000', '5', '大雁滩村民委员会', '99', '1', null, '甘肃省兰州市城关区雁南街道大雁滩村民委员会', '36.065005', '103.871399', '大雁滩村民委员会', '620102003203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102003204', '620102003000', '5', '沙洼河村民委员会', '99', '1', null, '甘肃省兰州市城关区雁南街道沙洼河村民委员会', '36.06576', '103.885007', '沙洼河村民委员会', '620102003204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004000', '620102000000', '4', '临夏路街道', '99', '1', null, '甘肃省兰州市城关区临夏路街道', '36.065504', '103.820289', '临夏路街道', '620102004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004001', '620102004000', '5', '木塔巷社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道木塔巷社区居委会', '36.067278', '103.825451', '木塔巷社区居委会', '620102004001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004002', '620102004000', '5', '付家巷社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道付家巷社区居委会', '36.06369', '103.818666', '付家巷社区居委会', '620102004002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004003', '620102004000', '5', '静安门社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道静安门社区居委会', '36.065504', '103.820289', '静安门社区居委会', '620102004003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004004', '620102004000', '5', '桥门社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道桥门社区居委会', '36.065504', '103.820289', '桥门社区居委会', '620102004004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004005', '620102004000', '5', '西城巷社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道西城巷社区居委会', '36.065504', '103.820289', '西城巷社区居委会', '620102004005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004006', '620102004000', '5', '雷坛河社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道雷坛河社区居委会', '36.064593', '103.814889', '雷坛河社区居委会', '620102004006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102004007', '620102004000', '5', '绣河沿社区居委会', '99', '1', null, '甘肃省兰州市城关区临夏路街道绣河沿社区居委会', '36.062179', '103.824862', '绣河沿社区居委会', '620102004007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005000', '620102000000', '4', '雁北街道', '99', '1', null, '甘肃省兰州市城关区雁北街道', '36.073919', '103.893046', '雁北街道', '620102005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005001', '620102005000', '5', '小雁滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道小雁滩村社区居委会', '36.071201', '103.880107', '小雁滩村社区居委会', '620102005001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005002', '620102005000', '5', '雁滩大桥社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道雁滩大桥社区居委会', '36.065678', '103.885402', '雁滩大桥社区居委会', '620102005002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005003', '620102005000', '5', '雁滩路社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道雁滩路社区居委会', '36.061334', '103.907585', '雁滩路社区居委会', '620102005003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005004', '620102005000', '5', '宋家滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道宋家滩村社区居委会', '36.077446', '103.876503', '宋家滩村社区居委会', '620102005004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005005', '620102005000', '5', '中河社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道中河社区居委会', '36.073919', '103.893046', '中河社区居委会', '620102005005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005006', '620102005000', '5', '刘家滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道刘家滩村社区居委会', '36.062584', '103.901018', '刘家滩村社区居委会', '620102005006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005007', '620102005000', '5', '北面滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道北面滩村社区居委会', '36.076423', '103.895028', '北面滩村社区居委会', '620102005007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005008', '620102005000', '5', '高滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道高滩村社区居委会', '36.060007', '103.917051', '高滩村社区居委会', '620102005008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005009', '620102005000', '5', '雁西路社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道雁西路社区居委会', '36.070244', '103.878314', '雁西路社区居委会', '620102005009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005010', '620102005000', '5', '雁滨社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道雁滨社区居委会', '36.073919', '103.893046', '雁滨社区居委会', '620102005010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102005011', '620102005000', '5', '雁东社区居委会', '99', '1', null, '甘肃省兰州市城关区雁北街道雁东社区居委会', '36.073919', '103.893046', '雁东社区居委会', '620102005011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006000', '620102000000', '4', '五泉街道', '99', '1', null, '甘肃省兰州市城关区五泉街道', '36.047996', '103.832464', '五泉街道', '620102006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006001', '620102006000', '5', '禄家巷社区居委会', '99', '1', null, '甘肃省兰州市城关区五泉街道禄家巷社区居委会', '36.048446', '103.831457', '禄家巷社区居委会', '620102006001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006002', '620102006000', '5', '闵家桥社区居委会', '99', '1', null, '甘肃省兰州市城关区五泉街道闵家桥社区居委会', '36.050414', '103.839211', '闵家桥社区居委会', '620102006002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006003', '620102006000', '5', '力行新村社区居委会', '99', '1', null, '甘肃省兰州市城关区五泉街道力行新村社区居委会', '36.049571', '103.830746', '力行新村社区居委会', '620102006003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006004', '620102006000', '5', '兰山村社区居委会', '99', '1', null, '甘肃省兰州市城关区五泉街道兰山村社区居委会', '36.04045', '103.840871', '兰山村社区居委会', '620102006004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006005', '620102006000', '5', '和平新村社区居委会', '99', '1', null, '甘肃省兰州市城关区五泉街道和平新村社区居委会', '36.047996', '103.832464', '和平新村社区居委会', '620102006005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102006006', '620102006000', '5', '五泉村社区', '99', '1', null, '甘肃省兰州市城关区五泉街道五泉村社区', '36.044223', '103.834194', '五泉村社区', '620102006006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007000', '620102000000', '4', '白银路街道', '99', '1', null, '甘肃省兰州市城关区白银路街道', '36.056801', '103.827749', '白银路街道', '620102007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007001', '620102007000', '5', '安定门社区居委会', '99', '1', null, '甘肃省兰州市城关区白银路街道安定门社区居委会', '36.057547', '103.825088', '安定门社区居委会', '620102007001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007002', '620102007000', '5', '正宁路社区居委会', '99', '1', null, '甘肃省兰州市城关区白银路街道正宁路社区居委会', '36.056492', '103.828938', '正宁路社区居委会', '620102007002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007003', '620102007000', '5', '甘家巷社区居委会', '99', '1', null, '甘肃省兰州市城关区白银路街道甘家巷社区居委会', '36.056801', '103.827749', '甘家巷社区居委会', '620102007003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007004', '620102007000', '5', '西北新村社区居委会', '99', '1', null, '甘肃省兰州市城关区白银路街道西北新村社区居委会', '36.057279', '103.824184', '西北新村社区居委会', '620102007004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102007005', '620102007000', '5', '徐家巷社区居委会', '99', '1', null, '甘肃省兰州市城关区白银路街道徐家巷社区居委会', '36.056669', '103.828049', '徐家巷社区居委会', '620102007005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008000', '620102000000', '4', '皋兰路街道', '99', '1', null, '甘肃省兰州市城关区皋兰路街道', '36.054208', '103.847084', '皋兰路街道', '620102008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008001', '620102008000', '5', '郑家台社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道郑家台社区居委会', '36.053225', '103.846787', '郑家台社区居委会', '620102008001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008002', '620102008000', '5', '詹家拐子社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道詹家拐子社区居委会', '36.052046', '103.843994', '詹家拐子社区居委会', '620102008002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008003', '620102008000', '5', '王家庄社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道王家庄社区居委会', '36.052046', '103.843994', '王家庄社区居委会', '620102008003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008004', '620102008000', '5', '榆中街社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道榆中街社区居委会', '36.056315', '103.850641', '榆中街社区居委会', '620102008004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008005', '620102008000', '5', '周家庄社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道周家庄社区居委会', '36.054208', '103.847084', '周家庄社区居委会', '620102008005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102008006', '620102008000', '5', '耿家庄社区居委会', '99', '1', null, '甘肃省兰州市城关区皋兰路街道耿家庄社区居委会', '36.052506', '103.852291', '耿家庄社区居委会', '620102008006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009000', '620102000000', '4', '广武门街道', '99', '1', null, '甘肃省兰州市城关区广武门街道', '36.064632', '103.846295', '广武门街道', '620102009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009001', '620102009000', '5', '黄河沿社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道黄河沿社区居委会', '36.066682', '103.842833', '黄河沿社区居委会', '620102009001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009002', '620102009000', '5', '南城根社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道南城根社区居委会', '36.060996', '103.848749', '南城根社区居委会', '620102009002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009003', '620102009000', '5', '广后街社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道广后街社区居委会', '36.055736', '103.816656', '广后街社区居委会', '620102009003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009004', '620102009000', '5', '新华巷社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道新华巷社区居委会', '36.06396', '103.842143', '新华巷社区居委会', '620102009004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009005', '620102009000', '5', '大教梁社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道大教梁社区居委会', '36.065054', '103.850987', '大教梁社区居委会', '620102009005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009006', '620102009000', '5', '民勤街社区居委会', '99', '1', null, '甘肃省兰州市城关区广武门街道民勤街社区居委会', '36.061767', '103.84308', '民勤街社区居委会', '620102009006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102009201', '620102009000', '5', '光辉村民委员会', '99', '1', null, '甘肃省兰州市城关区广武门街道光辉村民委员会', '36.064632', '103.846295', '光辉村民委员会', '620102009201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010000', '620102000000', '4', '伏龙坪街道', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道', '36.018855', '103.857832', '伏龙坪街道', '620102010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010001', '620102010000', '5', '后街社区居委会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道后街社区居委会', '36.055641', '103.816677', '后街社区居委会', '620102010001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010002', '620102010000', '5', '前街社区居委会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道前街社区居委会', '36.018855', '103.857832', '前街社区居委会', '620102010002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010003', '620102010000', '5', '杨家沟社区居委会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道杨家沟社区居委会', '36.062238', '103.816541', '杨家沟社区居委会', '620102010003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010201', '620102010000', '5', '红沟村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道红沟村民委员会', '35.993289', '103.88267', '红沟村民委员会', '620102010201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010202', '620102010000', '5', '头营村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道头营村民委员会', '36.018273', '103.851658', '头营村民委员会', '620102010202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010203', '620102010000', '5', '二营村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道二营村民委员会', '36.018855', '103.857832', '二营村民委员会', '620102010203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010204', '620102010000', '5', '三营村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道三营村民委员会', '36.018855', '103.857832', '三营村民委员会', '620102010204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010205', '620102010000', '5', '民族村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道民族村民委员会', '36.012196', '103.857342', '民族村民委员会', '620102010205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102010206', '620102010000', '5', '卓家沟村民委员会', '99', '1', null, '甘肃省兰州市城关区伏龙坪街道卓家沟村民委员会', '36.018855', '103.857832', '卓家沟村民委员会', '620102010206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011000', '620102000000', '4', '靖远路街道', '99', '1', null, '甘肃省兰州市城关区靖远路街道', '36.082836', '103.818245', '靖远路街道', '620102011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011001', '620102011000', '5', '徐家湾社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道徐家湾社区居委会', '36.079357', '103.799467', '徐家湾社区居委会', '620102011001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011002', '620102011000', '5', '金城关社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道金城关社区居委会', '36.076896', '103.808392', '金城关社区居委会', '620102011002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011003', '620102011000', '5', '白塔山社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道白塔山社区居委会', '36.080726', '103.817161', '白塔山社区居委会', '620102011003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011004', '620102011000', '5', '朝阳村社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道朝阳村社区居委会', '36.082836', '103.818245', '朝阳村社区居委会', '620102011004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011005', '620102011000', '5', '靖远路社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道靖远路社区居委会', '36.075645', '103.835324', '靖远路社区居委会', '620102011005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011006', '620102011000', '5', '九州大道社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道九州大道社区居委会', '36.089684', '103.820352', '九州大道社区居委会', '620102011006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011007', '620102011000', '5', '九州中路社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道九州中路社区居委会', '36.09364', '103.813972', '九州中路社区居委会', '620102011007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011008', '620102011000', '5', '西李家湾社区居委会', '99', '1', null, '甘肃省兰州市城关区靖远路街道西李家湾社区居委会', '36.080301', '103.832609', '西李家湾社区居委会', '620102011008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102011201', '620102011000', '5', '徐家湾村村民委员会', '99', '1', null, '甘肃省兰州市城关区靖远路街道徐家湾村村民委员会', '36.082836', '103.818245', '徐家湾村村民委员会', '620102011201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012000', '620102000000', '4', '草场街街道', '99', '1', null, '甘肃省兰州市城关区草场街街道', '36.091001', '103.836664', '草场街街道', '620102012', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012001', '620102012000', '5', '五一山社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道五一山社区居委会', '36.089589', '103.833053', '五一山社区居委会', '620102012001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012002', '620102012000', '5', '庙滩子社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道庙滩子社区居委会', '36.075805', '103.83562', '庙滩子社区居委会', '620102012002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012003', '620102012000', '5', '砂坪村社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道砂坪村社区居委会', '36.082039', '103.842059', '砂坪村社区居委会', '620102012003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012004', '620102012000', '5', '亚太社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道亚太社区居委会', '36.091001', '103.836664', '亚太社区居委会', '620102012004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012005', '620102012000', '5', '大砂坪社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道大砂坪社区居委会', '36.083812', '103.839303', '大砂坪社区居委会', '620102012005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102012006', '620102012000', '5', '草场街社区居委会', '99', '1', null, '甘肃省兰州市城关区草场街街道草场街社区居委会', '36.091001', '103.836664', '草场街社区居委会', '620102012006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013000', '620102000000', '4', '火车站街道', '99', '1', null, '甘肃省兰州市城关区火车站街道', '36.036668', '103.857095', '火车站街道', '620102013', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013001', '620102013000', '5', '车站社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道车站社区居委会', '36.036668', '103.857095', '车站社区居委会', '620102013001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013002', '620102013000', '5', '红山根社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道红山根社区居委会', '36.035972', '103.865571', '红山根社区居委会', '620102013002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013003', '620102013000', '5', '红二村社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道红二村社区居委会', '36.036668', '103.857095', '红二村社区居委会', '620102013003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013004', '620102013000', '5', '红三村社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道红三村社区居委会', '36.037017', '103.864456', '红三村社区居委会', '620102013004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013005', '620102013000', '5', '红西村社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道红西村社区居委会', '36.039152', '103.851234', '红西村社区居委会', '620102013005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102013006', '620102013000', '5', '红山根东路社区居委会', '99', '1', null, '甘肃省兰州市城关区火车站街道红山根东路社区居委会', '36.035143', '103.871505', '红山根东路社区居委会', '620102013006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014000', '620102000000', '4', '拱星墩街道', '99', '1', null, '甘肃省兰州市城关区拱星墩街道', '36.052348', '103.890304', '拱星墩街道', '620102014', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014001', '620102014000', '5', '拱星墩后街社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道拱星墩后街社区居委会', '36.046456', '103.899022', '拱星墩后街社区居委会', '620102014001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014002', '620102014000', '5', '五里铺西社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道五里铺西社区居委会', '36.050057', '103.882436', '五里铺西社区居委会', '620102014002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014003', '620102014000', '5', '段家滩东社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道段家滩东社区居委会', '36.05392', '103.88777', '段家滩东社区居委会', '620102014003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014004', '620102014000', '5', '段家滩西社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道段家滩西社区居委会', '36.05392', '103.88777', '段家滩西社区居委会', '620102014004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014005', '620102014000', '5', '东岗东路社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道东岗东路社区居委会', '36.044216', '103.911331', '东岗东路社区居委会', '620102014005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014006', '620102014000', '5', '五里铺东社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道五里铺东社区居委会', '36.050057', '103.882436', '五里铺东社区居委会', '620102014006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014007', '620102014000', '5', '五里铺村社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道五里铺村社区居委会', '36.052348', '103.890304', '五里铺村社区居委会', '620102014007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014008', '620102014000', '5', '段家滩村社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道段家滩村社区居委会', '36.055191', '103.886803', '段家滩村社区居委会', '620102014008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014009', '620102014000', '5', '范家湾村社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道范家湾村社区居委会', '36.052348', '103.890304', '范家湾村社区居委会', '620102014009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102014010', '620102014000', '5', '拱星墩村社区居委会', '99', '1', null, '甘肃省兰州市城关区拱星墩街道拱星墩村社区居委会', '36.052348', '103.890304', '拱星墩村社区居委会', '620102014010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015000', '620102000000', '4', '东岗街道', '99', '1', null, '甘肃省兰州市城关区东岗街道', '36.045035', '103.941054', '东岗街道', '620102015', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015001', '620102015000', '5', '桃树坪社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道桃树坪社区居委会', '36.042329', '103.938511', '桃树坪社区居委会', '620102015001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015002', '620102015000', '5', '新兴社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道新兴社区居委会', '36.050775', '103.933493', '新兴社区居委会', '620102015002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015003', '620102015000', '5', '振兴社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道振兴社区居委会', '36.050775', '103.933493', '振兴社区居委会', '620102015003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015004', '620102015000', '5', '雁儿湾社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道雁儿湾社区居委会', '36.053733', '103.919131', '雁儿湾社区居委会', '620102015004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015005', '620102015000', '5', '深沟桥社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道深沟桥社区居委会', '36.045035', '103.941054', '深沟桥社区居委会', '620102015005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015006', '620102015000', '5', '店子街村社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道店子街村社区居委会', '36.047334', '103.923112', '店子街村社区居委会', '620102015006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015007', '620102015000', '5', '东岗镇村社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗街道东岗镇村社区居委会', '36.052032', '103.933676', '东岗镇村社区居委会', '620102015007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015201', '620102015000', '5', '长洼山村民委员会', '99', '1', null, '甘肃省兰州市城关区东岗街道长洼山村民委员会', '36.045035', '103.941054', '长洼山村民委员会', '620102015201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102015202', '620102015000', '5', '大洼山村民委员会', '99', '1', null, '甘肃省兰州市城关区东岗街道大洼山村民委员会', '36.045035', '103.941054', '大洼山村民委员会', '620102015202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016000', '620102000000', '4', '团结新村街道', '99', '1', null, '甘肃省兰州市城关区团结新村街道', '36.044325', '103.866042', '团结新村街道', '620102016', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016001', '620102016000', '5', '团结新村社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道团结新村社区居委会', '36.044325', '103.866042', '团结新村社区居委会', '620102016001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016002', '620102016000', '5', '天平街社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道天平街社区居委会', '36.048617', '103.857641', '天平街社区居委会', '620102016002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016003', '620102016000', '5', '红星巷社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道红星巷社区居委会', '36.042331', '103.870474', '红星巷社区居委会', '620102016003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016004', '620102016000', '5', '定西二支路社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道定西二支路社区居委会', '36.046444', '103.866804', '定西二支路社区居委会', '620102016004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016005', '620102016000', '5', '定西南路社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道定西南路社区居委会', '36.044736', '103.869064', '定西南路社区居委会', '620102016005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102016006', '620102016000', '5', '天水南路社区居委会', '99', '1', null, '甘肃省兰州市城关区团结新村街道天水南路社区居委会', '36.049807', '103.861087', '天水南路社区居委会', '620102016006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017000', '620102000000', '4', '东岗西路街道', '99', '1', null, '甘肃省兰州市城关区东岗西路街道', '36.058169', '103.857998', '东岗西路街道', '620102017', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017001', '620102017000', '5', '农民巷东社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道农民巷东社区居委会', '36.058587', '103.860479', '农民巷东社区居委会', '620102017001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017002', '620102017000', '5', '农民巷西社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道农民巷西社区居委会', '36.058587', '103.860479', '农民巷西社区居委会', '620102017002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017003', '620102017000', '5', '天水路社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道天水路社区居委会', '36.07785', '103.868429', '天水路社区居委会', '620102017003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017004', '620102017000', '5', '一只船社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道一只船社区居委会', '36.058169', '103.857998', '一只船社区居委会', '620102017004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017005', '620102017000', '5', '东岗西路社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道东岗西路社区居委会', '36.055716', '103.863276', '东岗西路社区居委会', '620102017005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017006', '620102017000', '5', '平凉路社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道平凉路社区居委会', '36.05236', '103.855844', '平凉路社区居委会', '620102017006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102017007', '620102017000', '5', '东郊场社区居委会', '99', '1', null, '甘肃省兰州市城关区东岗西路街道东郊场社区居委会', '36.058169', '103.857998', '东郊场社区居委会', '620102017007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102018000', '620102000000', '4', '铁路东村街道', '99', '1', null, '甘肃省兰州市城关区铁路东村街道', '36.044703', '103.851173', '铁路东村街道', '620102018', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102018001', '620102018000', '5', '铁路东村社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路东村街道铁路东村社区居委会', '36.044703', '103.851173', '铁路东村社区居委会', '620102018001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102018002', '620102018000', '5', '和政东街社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路东村街道和政东街社区居委会', '36.046598', '103.850608', '和政东街社区居委会', '620102018002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102018003', '620102018000', '5', '铁路新村社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路东村街道铁路新村社区居委会', '36.044703', '103.851173', '铁路新村社区居委会', '620102018003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102018004', '620102018000', '5', '何家庄社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路东村街道何家庄社区居委会', '36.044033', '103.852708', '何家庄社区居委会', '620102018004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019000', '620102000000', '4', '铁路西村街道', '99', '1', null, '甘肃省兰州市城关区铁路西村街道', '36.044715', '103.841586', '铁路西村街道', '620102019', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019001', '620102019000', '5', '和政西街社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道和政西街社区居委会', '36.047107', '103.840759', '和政西街社区居委会', '620102019001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019002', '620102019000', '5', '居安社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道居安社区居委会', '36.043741', '103.841173', '居安社区居委会', '620102019002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019003', '620102019000', '5', '西村社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道西村社区居委会', '36.044715', '103.841586', '西村社区居委会', '620102019003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019004', '620102019000', '5', '牟家庄东社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道牟家庄东社区居委会', '36.041531', '103.84569', '牟家庄东社区居委会', '620102019004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019005', '620102019000', '5', '牟家庄北社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道牟家庄北社区居委会', '36.041531', '103.84569', '牟家庄北社区居委会', '620102019005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102019006', '620102019000', '5', '牟家庄南社区居委会', '99', '1', null, '甘肃省兰州市城关区铁路西村街道牟家庄南社区居委会', '36.041254', '103.844166', '牟家庄南社区居委会', '620102019006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020000', '620102000000', '4', '渭源路街道', '99', '1', null, '甘肃省兰州市城关区渭源路街道', '36.054162', '103.870586', '渭源路街道', '620102020', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020001', '620102020000', '5', '南河新村社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道南河新村社区居委会', '36.059736', '103.876902', '南河新村社区居委会', '620102020001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020002', '620102020000', '5', '南昌路社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道南昌路社区居委会', '36.059692', '103.864733', '南昌路社区居委会', '620102020002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020003', '620102020000', '5', '宁卧庄社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道宁卧庄社区居委会', '36.054162', '103.870586', '宁卧庄社区居委会', '620102020003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020004', '620102020000', '5', '兰州大学社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道兰州大学社区居委会', '36.051763', '103.866501', '兰州大学社区居委会', '620102020004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020005', '620102020000', '5', '科技街社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道科技街社区居委会', '36.055336', '103.874339', '科技街社区居委会', '620102020005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102020006', '620102020000', '5', '定西路社区居委会', '99', '1', null, '甘肃省兰州市城关区渭源路街道定西路社区居委会', '36.047075', '103.868233', '定西路社区居委会', '620102020006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021000', '620102000000', '4', '盐场路街道', '99', '1', null, '甘肃省兰州市城关区盐场路街道', '36.116662', '103.825791', '盐场路街道', '620102021', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021001', '620102021000', '5', '穆柯寨社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道穆柯寨社区居委会', '36.077131', '103.853278', '穆柯寨社区居委会', '620102021001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021002', '620102021000', '5', '小沟坪社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道小沟坪社区居委会', '36.080682', '103.84636', '小沟坪社区居委会', '620102021002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021003', '620102021000', '5', '盐场堡社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道盐场堡社区居委会', '36.080538', '103.860324', '盐场堡社区居委会', '620102021003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021004', '620102021000', '5', '盐场堡村社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道盐场堡村社区居委会', '36.080073', '103.859859', '盐场堡村社区居委会', '620102021004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021005', '620102021000', '5', '上川村社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道上川村社区居委会', '36.080315', '103.859528', '上川村社区居委会', '620102021005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021006', '620102021000', '5', '草场街村社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道草场街村社区居委会', '36.081315', '103.832806', '草场街村社区居委会', '620102021006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021007', '620102021000', '5', '亭子村社区居委会', '99', '1', null, '甘肃省兰州市城关区盐场路街道亭子村社区居委会', '36.07309', '103.847421', '亭子村社区居委会', '620102021007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102021201', '620102021000', '5', '石门沟村民委员会', '99', '1', null, '甘肃省兰州市城关区盐场路街道石门沟村民委员会', '36.108722', '103.85696', '石门沟村民委员会', '620102021201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022000', '620102000000', '4', '嘉峪关路街道', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道', '36.036025', '103.877811', '嘉峪关路街道', '620102022', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022001', '620102022000', '5', '排洪沟社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道排洪沟社区居委会', '36.040865', '103.87219', '排洪沟社区居委会', '620102022001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022002', '620102022000', '5', '排洪沟南路社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道排洪沟南路社区居委会', '36.040865', '103.87219', '排洪沟南路社区居委会', '620102022002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022003', '620102022000', '5', '五里铺社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道五里铺社区居委会', '36.036025', '103.877811', '五里铺社区居委会', '620102022003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022004', '620102022000', '5', '嘉峪关路社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道嘉峪关路社区居委会', '36.045816', '103.8895', '嘉峪关路社区居委会', '620102022004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022005', '620102022000', '5', '嘉峪关北路社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道嘉峪关北路社区居委会', '36.04556', '103.889267', '嘉峪关北路社区居委会', '620102022005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102022006', '620102022000', '5', '嘉峪关西路社区居委会', '99', '1', null, '甘肃省兰州市城关区嘉峪关路街道嘉峪关西路社区居委会', '36.0439', '103.882789', '嘉峪关西路社区居委会', '620102022006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102023000', '620102000000', '4', '焦家湾街道', '99', '1', null, '甘肃省兰州市城关区焦家湾街道', '36.031403', '103.896457', '焦家湾街道', '620102023', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102023001', '620102023000', '5', '焦家湾东社区居委会', '99', '1', null, '甘肃省兰州市城关区焦家湾街道焦家湾东社区居委会', '36.042649', '103.901306', '焦家湾东社区居委会', '620102023001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102023002', '620102023000', '5', '焦家湾社区居委会', '99', '1', null, '甘肃省兰州市城关区焦家湾街道焦家湾社区居委会', '36.044438', '103.903508', '焦家湾社区居委会', '620102023002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102023003', '620102023000', '5', '焦家湾南路社区居委会', '99', '1', null, '甘肃省兰州市城关区焦家湾街道焦家湾南路社区居委会', '36.042649', '103.901306', '焦家湾南路社区居委会', '620102023003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102023004', '620102023000', '5', '嘉峪关东路社区居委会', '99', '1', null, '甘肃省兰州市城关区焦家湾街道嘉峪关东路社区居委会', '36.041995', '103.891566', '嘉峪关东路社区居委会', '620102023004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024000', '620102000000', '4', '青白石街道', '99', '1', null, '甘肃省兰州市城关区青白石街道', '36.106704', '103.90345', '青白石街道', '620102024', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024201', '620102024000', '5', '白道坪村村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道白道坪村村民委员会', '36.077852', '103.912797', '白道坪村村民委员会', '620102024201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024202', '620102024000', '5', '上坪村村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道上坪村村民委员会', '36.089596', '103.892355', '上坪村村民委员会', '620102024202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024203', '620102024000', '5', '石沟村村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道石沟村村民委员会', '36.117654', '103.904448', '石沟村村民委员会', '620102024203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024204', '620102024000', '5', '碱水沟村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道碱水沟村民委员会', '36.076154', '103.920343', '碱水沟村民委员会', '620102024204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024205', '620102024000', '5', '青石湾村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道青石湾村民委员会', '36.069493', '103.930947', '青石湾村民委员会', '620102024205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024206', '620102024000', '5', '杨家湾村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道杨家湾村民委员会', '36.065751', '103.941957', '杨家湾村民委员会', '620102024206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024207', '620102024000', '5', '大浪沟村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道大浪沟村民委员会', '36.098011', '103.932078', '大浪沟村民委员会', '620102024207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024208', '620102024000', '5', '马家沟村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道马家沟村民委员会', '36.125923', '103.941212', '马家沟村民委员会', '620102024208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102024209', '620102024000', '5', '青山村民委员会', '99', '1', null, '甘肃省兰州市城关区青白石街道青山村民委员会', '36.092589', '103.97844', '青山村民委员会', '620102024209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102025000', '620102000000', '4', '九州管委会', '99', '1', null, '甘肃省兰州市城关区九州管委会', null, null, '九州管委会', '620102025', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102026000', '620102000000', '4', '南河办', '99', '1', null, '甘肃省兰州市城关区南河办', null, null, '南河办', '620102026', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102400000', '620102000000', '4', '高新区', '99', '0', null, '甘肃省兰州市城关区高新区', '36.054008', '103.851571', '高新区', '620102400', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102400400', '620102400000', '5', '高新区均家滩社区', '99', '0', null, '甘肃省兰州市城关区高新区高新区均家滩社区', '36.061604', '103.898607', '高新区均家滩社区', '620102400400', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102400401', '620102400000', '5', '高新区南面滩社区', '99', '0', null, '甘肃省兰州市城关区高新区高新区南面滩社区', '36.055331', '103.904589', '高新区南面滩社区', '620102400401', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620102400402', '620102400000', '5', '高新区骆驼滩社区', '99', '0', null, '甘肃省兰州市城关区高新区高新区骆驼滩社区', '36.060998', '103.909871', '高新区骆驼滩社区', '620102400402', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103000000', '620100000000', '3', '七里河区', '99', '1', null, '甘肃省兰州市七里河区', '35.992495', '103.771994', '七里河区', '620103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001000', '620103000000', '4', '西园街道', '99', '1', null, '甘肃省兰州市七里河区西园街道', '36.063993', '103.805102', '西园街道', '620103001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001001', '620103001000', '5', '下西园社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道下西园社区居委会', '36.066759', '103.808482', '下西园社区居委会', '620103001001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001002', '620103001000', '5', '上西园社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道上西园社区居委会', '36.064157', '103.798703', '上西园社区居委会', '620103001002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001003', '620103001000', '5', '工林路社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道工林路社区居委会', '36.057387', '103.810373', '工林路社区居委会', '620103001003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001004', '620103001000', '5', '林家庄社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道林家庄社区居委会', '36.083835', '103.759435', '林家庄社区居委会', '620103001004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001005', '620103001000', '5', '雷坛河西街社区居民委员会', '99', '1', null, '甘肃省兰州市七里河区西园街道雷坛河西街社区居民委员会', '36.063993', '103.805102', '雷坛河西街社区居民委员会', '620103001005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001006', '620103001000', '5', '柏树巷社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道柏树巷社区居委会', '36.063993', '103.805102', '柏树巷社区居委会', '620103001006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001007', '620103001000', '5', '五星坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道五星坪社区居委会', '36.062615', '103.792974', '五星坪社区居委会', '620103001007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001008', '620103001000', '5', '华林山社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道华林山社区居委会', '36.064571', '103.808455', '华林山社区居委会', '620103001008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001009', '620103001000', '5', '华林坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道华林坪社区居委会', '36.060308', '103.809067', '华林坪社区居委会', '620103001009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103001010', '620103001000', '5', '文化宫社区居委会', '99', '1', null, '甘肃省兰州市七里河区西园街道文化宫社区居委会', '36.063993', '103.805102', '文化宫社区居委会', '620103001010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002000', '620103000000', '4', '西湖街道', '99', '1', null, '甘肃省兰州市七里河区西湖街道', '36.072234', '103.791937', '西湖街道', '620103002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002001', '620103002000', '5', '理工大社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道理工大社区居委会', '35.992495', '103.771994', '理工大社区居委会', '620103002001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002002', '620103002000', '5', '兰工坪北街社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道兰工坪北街社区居委会', '36.067206', '103.787151', '兰工坪北街社区居委会', '620103002002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002003', '620103002000', '5', '骆驼巷社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道骆驼巷社区居委会', '36.06494', '103.795097', '骆驼巷社区居委会', '620103002003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002004', '620103002000', '5', '梁家庄社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道梁家庄社区居委会', '36.070678', '103.794741', '梁家庄社区居委会', '620103002004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002005', '620103002000', '5', '建工中街社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道建工中街社区居委会', '36.070497', '103.789527', '建工中街社区居委会', '620103002005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002006', '620103002000', '5', '小西湖东街社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道小西湖东街社区居委会', '36.072822', '103.798434', '小西湖东街社区居委会', '620103002006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002007', '620103002000', '5', '小西湖西街社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道小西湖西街社区居委会', '36.07373', '103.792422', '小西湖西街社区居委会', '620103002007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002008', '620103002000', '5', '西津桥社区居委会', '99', '1', null, '甘肃省兰州市七里河区西湖街道西津桥社区居委会', '36.072234', '103.791937', '西津桥社区居委会', '620103002008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103002009', '620103002000', '5', '瓜州路社区居民委员会', '99', '1', null, '甘肃省兰州市七里河区西湖街道瓜州路社区居民委员会', '36.078089', '103.782236', '瓜州路社区居民委员会', '620103002009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003000', '620103000000', '4', '建兰路街道', '99', '1', null, '甘肃省兰州市七里河区建兰路街道', '36.082066', '103.784094', '建兰路街道', '620103003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003001', '620103003000', '5', '建兰路社区居委会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道建兰路社区居委会', '36.082066', '103.784094', '建兰路社区居委会', '620103003001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003002', '620103003000', '5', '王家堡社区居委会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道王家堡社区居委会', '36.078911', '103.783309', '王家堡社区居委会', '620103003002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003003', '620103003000', '5', '健康路社区居民委员会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道健康路社区居民委员会', '36.082066', '103.784094', '健康路社区居民委员会', '620103003003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003004', '620103003000', '5', '吴家园社区居委会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道吴家园社区居委会', '36.08096', '103.78497', '吴家园社区居委会', '620103003004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003005', '620103003000', '5', '吴家园西街社区居委会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道吴家园西街社区居委会', '36.08791', '103.773078', '吴家园西街社区居委会', '620103003005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103003006', '620103003000', '5', '兰石社区居委会', '99', '1', null, '甘肃省兰州市七里河区建兰路街道兰石社区居委会', '36.08124', '103.777135', '兰石社区居委会', '620103003006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004000', '620103000000', '4', '敦煌路街道', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道', '36.082548', '103.758133', '敦煌路街道', '620103004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004001', '620103004000', '5', '金港城社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道金港城社区居委会', '36.087019', '103.74976', '金港城社区居委会', '620103004001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004002', '620103004000', '5', '郑家庄社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道郑家庄社区居委会', '36.083924', '103.765167', '郑家庄社区居委会', '620103004002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004003', '620103004000', '5', '任家庄街西社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道任家庄街西社区居委会', '36.077379', '103.773019', '任家庄街西社区居委会', '620103004003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004005', '620103004000', '5', '任家庄街东社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道任家庄街东社区居委会', '36.077379', '103.773019', '任家庄街东社区居委会', '620103004005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004006', '620103004000', '5', '光华街社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道光华街社区居委会', '36.082548', '103.758133', '光华街社区居委会', '620103004006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004007', '620103004000', '5', '柳家营社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道柳家营社区居委会', '36.075318', '103.774181', '柳家营社区居委会', '620103004007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103004008', '620103004000', '5', '西津社区居委会', '99', '1', null, '甘肃省兰州市七里河区敦煌路街道西津社区居委会', '36.082548', '103.758133', '西津社区居委会', '620103004008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005000', '620103000000', '4', '西站街道', '99', '1', null, '甘肃省兰州市七里河区西站街道', '36.072223', '103.766374', '西站街道', '620103005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005001', '620103005000', '5', '机车厂社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道机车厂社区居委会', '36.070433', '103.780507', '机车厂社区居委会', '620103005001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005002', '620103005000', '5', '西站东路社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道西站东路社区居委会', '35.992495', '103.771994', '西站东路社区居委会', '620103005002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005003', '620103005000', '5', '西站西路社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道西站西路社区居委会', '36.072311', '103.772486', '西站西路社区居委会', '620103005003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005004', '620103005000', '5', '西客站社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道西客站社区居委会', '36.072223', '103.766374', '西客站社区居委会', '620103005004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005005', '620103005000', '5', '三角线社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道三角线社区居委会', '36.072223', '103.766374', '三角线社区居委会', '620103005005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005006', '620103005000', '5', '武威路社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道武威路社区居委会', '36.069087', '103.773251', '武威路社区居委会', '620103005006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005007', '620103005000', '5', '小西坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道小西坪社区居委会', '36.072223', '103.766374', '小西坪社区居委会', '620103005007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103005008', '620103005000', '5', '建西东路社区居委会', '99', '1', null, '甘肃省兰州市七里河区西站街道建西东路社区居委会', '36.068352', '103.775407', '建西东路社区居委会', '620103005008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006000', '620103000000', '4', '晏家坪街道', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道', '36.057559', '103.769775', '晏家坪街道', '620103006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006001', '620103006000', '5', '晏家坪北院社区居委会', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道晏家坪北院社区居委会', '36.055104', '103.775781', '晏家坪北院社区居委会', '620103006001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006002', '620103006000', '5', '晏家坪铁路院社区居委会', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道晏家坪铁路院社区居委会', '36.055104', '103.775781', '晏家坪铁路院社区居委会', '620103006002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006003', '620103006000', '5', '中院社区居委会', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道中院社区居委会', '36.055813', '103.772892', '中院社区居委会', '620103006003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006004', '620103006000', '5', '晏家坪南院社区居委会', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道晏家坪南院社区居委会', '36.055205', '103.769622', '晏家坪南院社区居委会', '620103006004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103006005', '620103006000', '5', '西院社区居委会', '99', '1', null, '甘肃省兰州市七里河区晏家坪街道西院社区居委会', '36.055104', '103.775781', '西院社区居委会', '620103006005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007000', '620103000000', '4', '龚家湾街道', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道', '36.057416', '103.754075', '龚家湾街道', '620103007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007001', '620103007000', '5', '丽苑社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道丽苑社区居委会', '36.057416', '103.754075', '丽苑社区居委会', '620103007001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007002', '620103007000', '5', '龚家坪西路社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道龚家坪西路社区居委会', '36.056429', '103.742674', '龚家坪西路社区居委会', '620103007002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007003', '620103007000', '5', '龚家坪东路社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道龚家坪东路社区居委会', '36.061339', '103.753088', '龚家坪东路社区居委会', '620103007003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007004', '620103007000', '5', '龚家坪北路社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道龚家坪北路社区居委会', '36.063365', '103.752765', '龚家坪北路社区居委会', '620103007004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007005', '620103007000', '5', '民乐路社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道民乐路社区居委会', '36.064377', '103.759416', '民乐路社区居委会', '620103007005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103007006', '620103007000', '5', '武山路社区居委会', '99', '1', null, '甘肃省兰州市七里河区龚家湾街道武山路社区居委会', '36.059965', '103.758135', '武山路社区居委会', '620103007006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008000', '620103000000', '4', '土门墩街道', '99', '1', null, '甘肃省兰州市七里河区土门墩街道', '36.082772', '103.73779', '土门墩街道', '620103008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008001', '620103008000', '5', '河湾堡社区居委会', '99', '1', null, '甘肃省兰州市七里河区土门墩街道河湾堡社区居委会', '36.082772', '103.73779', '河湾堡社区居委会', '620103008001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008002', '620103008000', '5', '土门墩社区居委会', '99', '1', null, '甘肃省兰州市七里河区土门墩街道土门墩社区居委会', '36.080158', '103.743831', '土门墩社区居委会', '620103008002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008003', '620103008000', '5', '兰通社区居委会', '99', '1', null, '甘肃省兰州市七里河区土门墩街道兰通社区居委会', '36.082772', '103.73779', '兰通社区居委会', '620103008003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008004', '620103008000', '5', '建西西路社区居委会', '99', '1', null, '甘肃省兰州市七里河区土门墩街道建西西路社区居委会', '36.071977', '103.751798', '建西西路社区居委会', '620103008004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103008005', '620103008000', '5', '西津西路社区居委会', '99', '1', null, '甘肃省兰州市七里河区土门墩街道西津西路社区居委会', '36.08039', '103.719946', '西津西路社区居委会', '620103008005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009000', '620103000000', '4', '秀川街道', '99', '1', null, '甘肃省兰州市七里河区秀川街道', '36.087263', '103.721607', '秀川街道', '620103009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009001', '620103009000', '5', '秀川社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道秀川社区居委会', '36.082978', '103.718357', '秀川社区居委会', '620103009001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009002', '620103009000', '5', '穴崖子社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道穴崖子社区居委会', '36.07837', '103.720612', '穴崖子社区居委会', '620103009002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009003', '620103009000', '5', '郑家庄新社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道郑家庄新社区居委会', '36.083924', '103.765167', '郑家庄新社区居委会', '620103009003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009004', '620103009000', '5', '马滩社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道马滩社区居委会', '36.087082', '103.734551', '马滩社区居委会', '620103009004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009005', '620103009000', '5', '崔家崖社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道崔家崖社区居委会', '36.083025', '103.709537', '崔家崖社区居委会', '620103009005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009006', '620103009000', '5', '大滩社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道大滩社区居委会', '36.090702', '103.682601', '大滩社区居委会', '620103009006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009007', '620103009000', '5', '郑家庄中心坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区秀川街道郑家庄中心坪社区居委会', '36.083924', '103.765167', '郑家庄中心坪社区居委会', '620103009007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103009008', '620103009000', '5', '银滩花园社区', '99', '1', null, '甘肃省兰州市七里河区秀川街道银滩花园社区', '36.095116', '103.727934', '银滩花园社区', '620103009008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100000', '620103000000', '4', '阿干镇', '99', '1', null, '甘肃省兰州市七里河区阿干镇', '35.894201', '103.856083', '阿干镇', '620103100', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100001', '620103100000', '5', '大水子社区居委会', '99', '1', null, '甘肃省兰州市七里河区阿干镇大水子社区居委会', '35.907519', '103.850419', '大水子社区居委会', '620103100001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100002', '620103100000', '5', '石门沟社区居委会', '99', '1', null, '甘肃省兰州市七里河区阿干镇石门沟社区居委会', '35.910649', '103.855157', '石门沟社区居委会', '620103100002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100003', '620103100000', '5', '高林沟社区居委会', '99', '1', null, '甘肃省兰州市七里河区阿干镇高林沟社区居委会', '35.913339', '103.854174', '高林沟社区居委会', '620103100003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100004', '620103100000', '5', '中街社区居委会', '99', '1', null, '甘肃省兰州市七里河区阿干镇中街社区居委会', '35.894201', '103.856083', '中街社区居委会', '620103100004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100005', '620103100000', '5', '烂泥沟社区居委会', '99', '1', null, '甘肃省兰州市七里河区阿干镇烂泥沟社区居委会', '35.928893', '103.867082', '烂泥沟社区居委会', '620103100005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100006', '620103100000', '5', '民意社区居民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇民意社区居民委员会', '35.894201', '103.856083', '民意社区居民委员会', '620103100006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100201', '620103100000', '5', '马泉村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇马泉村民委员会', '35.894201', '103.856083', '马泉村民委员会', '620103100201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100202', '620103100000', '5', '琅峪村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇琅峪村民委员会', '35.894201', '103.856083', '琅峪村民委员会', '620103100202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100204', '620103100000', '5', '深沟掌村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇深沟掌村民委员会', '35.88583', '103.893427', '深沟掌村民委员会', '620103100204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100205', '620103100000', '5', '马场村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇马场村民委员会', '35.897714', '103.88559', '马场村民委员会', '620103100205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100206', '620103100000', '5', '大水子村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇大水子村民委员会', '35.907519', '103.850419', '大水子村民委员会', '620103100206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100207', '620103100000', '5', '阿干村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇阿干村民委员会', '35.921161', '103.85601', '阿干村民委员会', '620103100207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100208', '620103100000', '5', '坪岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇坪岭村民委员会', '35.992495', '103.771994', '坪岭村民委员会', '620103100208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103100209', '620103100000', '5', '大沟村民委员会', '99', '1', null, '甘肃省兰州市七里河区阿干镇大沟村民委员会', '35.926364', '103.893981', '大沟村民委员会', '620103100209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101000', '620103000000', '4', '八里镇', '99', '1', null, '甘肃省兰州市七里河区八里镇', '36.003604', '103.839898', '八里镇', '620103101', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101001', '620103101000', '5', '八里窑社区居委会', '99', '1', null, '甘肃省兰州市七里河区八里镇八里窑社区居委会', '36.053554', '103.812316', '八里窑社区居委会', '620103101001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101002', '620103101000', '5', '岘口子社区居委会', '99', '1', null, '甘肃省兰州市七里河区八里镇岘口子社区居委会', '35.943351', '103.874773', '岘口子社区居委会', '620103101002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101003', '620103101000', '5', '华林路社区居委会', '99', '1', null, '甘肃省兰州市七里河区八里镇华林路社区居委会', '36.003604', '103.839898', '华林路社区居委会', '620103101003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101004', '620103101000', '5', '西园社区居委会', '99', '1', null, '甘肃省兰州市七里河区八里镇西园社区居委会', '36.003604', '103.839898', '西园社区居委会', '620103101004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101201', '620103101000', '5', '岘口子村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇岘口子村民委员会', '35.943351', '103.874773', '岘口子村民委员会', '620103101201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101202', '620103101000', '5', '东果园村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇东果园村民委员会', '35.992495', '103.771994', '东果园村民委员会', '620103101202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101203', '620103101000', '5', '清水营村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇清水营村民委员会', '35.961871', '103.870721', '清水营村民委员会', '620103101203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101204', '620103101000', '5', '侯家峪村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇侯家峪村民委员会', '36.003604', '103.839898', '侯家峪村民委员会', '620103101204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101205', '620103101000', '5', '花寨子村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇花寨子村民委员会', '35.981627', '103.857973', '花寨子村民委员会', '620103101205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101206', '620103101000', '5', '二十里铺村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇二十里铺村民委员会', '35.99797', '103.842576', '二十里铺村民委员会', '620103101206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101207', '620103101000', '5', '崖头村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇崖头村民委员会', '36.008504', '103.831598', '崖头村民委员会', '620103101207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101208', '620103101000', '5', '后五泉村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇后五泉村民委员会', '36.02776', '103.820216', '后五泉村民委员会', '620103101208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101209', '620103101000', '5', '八里窑村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇八里窑村民委员会', '36.04079', '103.81452', '八里窑村民委员会', '620103101209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103101210', '620103101000', '5', '五里铺村民委员会', '99', '1', null, '甘肃省兰州市七里河区八里镇五里铺村民委员会', '36.003604', '103.839898', '五里铺村民委员会', '620103101210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102000', '620103000000', '4', '彭家坪镇', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇', '36.052122', '103.715194', '彭家坪镇', '620103102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102001', '620103102000', '5', '彭家坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇彭家坪社区居委会', '36.060237', '103.709206', '彭家坪社区居委会', '620103102001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102002', '620103102000', '5', '龚家湾新社区居委会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇龚家湾新社区居委会', '36.058852', '103.751841', '龚家湾新社区居委会', '620103102002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102201', '620103102000', '5', '贾家山村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇贾家山村民委员会', '36.029175', '103.691824', '贾家山村民委员会', '620103102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102202', '620103102000', '5', '石板山村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇石板山村民委员会', '36.018705', '103.691257', '石板山村民委员会', '620103102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102203', '620103102000', '5', '彭家坪村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇彭家坪村民委员会', '36.060237', '103.709206', '彭家坪村民委员会', '620103102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102204', '620103102000', '5', '蒋家坪村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇蒋家坪村民委员会', '36.048658', '103.724232', '蒋家坪村民委员会', '620103102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102205', '620103102000', '5', '牟家坪村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇牟家坪村民委员会', '36.063855', '103.699995', '牟家坪村民委员会', '620103102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102206', '620103102000', '5', '西坪村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇西坪村民委员会', '36.072115', '103.730918', '西坪村民委员会', '620103102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102208', '620103102000', '5', '土门墩村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇土门墩村民委员会', '36.080158', '103.743831', '土门墩村民委员会', '620103102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102209', '620103102000', '5', '王家堡村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇王家堡村民委员会', '36.052122', '103.715194', '王家堡村民委员会', '620103102209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103102210', '620103102000', '5', '任家庄村民委员会', '99', '1', null, '甘肃省兰州市七里河区彭家坪镇任家庄村民委员会', '36.050304', '103.729994', '任家庄村民委员会', '620103102210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103000', '620103000000', '4', '西果园镇', '99', '1', null, '甘肃省兰州市七里河区西果园镇', '35.975077', '103.759128', '西果园镇', '620103103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103001', '620103103000', '5', '晏家坪社区居委会', '99', '1', null, '甘肃省兰州市七里河区西果园镇晏家坪社区居委会', '36.055104', '103.775781', '晏家坪社区居委会', '620103103001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103002', '620103103000', '5', '南站社区居民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇南站社区居民委员会', '35.975077', '103.759128', '南站社区居民委员会', '620103103002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103201', '620103103000', '5', '上岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇上岭村民委员会', '35.975077', '103.759128', '上岭村民委员会', '620103103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103202', '620103103000', '5', '青岗村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇青岗村民委员会', '35.95508', '103.771748', '青岗村民委员会', '620103103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103203', '620103103000', '5', '袁家湾村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇袁家湾村民委员会', '35.975077', '103.759128', '袁家湾村民委员会', '620103103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103204', '620103103000', '5', '鹞子岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇鹞子岭村民委员会', '35.975077', '103.759128', '鹞子岭村民委员会', '620103103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103205', '620103103000', '5', '草原村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇草原村民委员会', '35.975077', '103.759128', '草原村民委员会', '620103103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103206', '620103103000', '5', '堡子村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇堡子村民委员会', '35.958985', '103.747985', '堡子村民委员会', '620103103206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103207', '620103103000', '5', '上果园村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇上果园村民委员会', '35.975077', '103.759128', '上果园村民委员会', '620103103207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103208', '620103103000', '5', '西果园村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇西果园村民委员会', '35.979394', '103.773449', '西果园村民委员会', '620103103208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103209', '620103103000', '5', '柴家河村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇柴家河村民委员会', '36.003939', '103.785112', '柴家河村民委员会', '620103103209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103210', '620103103000', '5', '王家坪村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇王家坪村民委员会', '36.012289', '103.783796', '王家坪村民委员会', '620103103210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103211', '620103103000', '5', '西津村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇西津村民委员会', '35.975077', '103.759128', '西津村民委员会', '620103103211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103212', '620103103000', '5', '周家山村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇周家山村民委员会', '36.022909', '103.760117', '周家山村民委员会', '620103103212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103103214', '620103103000', '5', '湖滩村民委员会', '99', '1', null, '甘肃省兰州市七里河区西果园镇湖滩村民委员会', '35.932063', '103.674107', '湖滩村民委员会', '620103103214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200000', '620103000000', '4', '魏岭乡', '99', '1', null, '甘肃省兰州市七里河区魏岭乡', '35.956188', '103.818386', '魏岭乡', '620103200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200201', '620103200000', '5', '小山口村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡小山口村民委员会', '35.956188', '103.818386', '小山口村民委员会', '620103200201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200202', '620103200000', '5', '柳树湾村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡柳树湾村民委员会', '35.935859', '103.847166', '柳树湾村民委员会', '620103200202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200203', '620103200000', '5', '龙池村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡龙池村民委员会', '35.930454', '103.820645', '龙池村民委员会', '620103200203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200204', '620103200000', '5', '白家岘村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡白家岘村民委员会', '35.920607', '103.797469', '白家岘村民委员会', '620103200204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200205', '620103200000', '5', '海家岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡海家岭村民委员会', '35.956188', '103.818386', '海家岭村民委员会', '620103200205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200206', '620103200000', '5', '晏家洼村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡晏家洼村民委员会', '35.956188', '103.818386', '晏家洼村民委员会', '620103200206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200207', '620103200000', '5', '沈家岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡沈家岭村民委员会', '36.019315', '103.806794', '沈家岭村民委员会', '620103200207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103200208', '620103200000', '5', '绿化村民委员会', '99', '1', null, '甘肃省兰州市七里河区魏岭乡绿化村民委员会', '36.001999', '103.795365', '绿化村民委员会', '620103200208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201000', '620103000000', '4', '黄峪镇', '99', '1', null, '甘肃省兰州市七里河区黄峪镇', '36.020408', '103.721249', '黄峪镇', '620103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201201', '620103201000', '5', '尖山村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇尖山村民委员会', '36.020408', '103.721249', '尖山村民委员会', '620103201201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201202', '620103201000', '5', '蒋家湾村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇蒋家湾村民委员会', '36.020408', '103.721249', '蒋家湾村民委员会', '620103201202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201203', '620103201000', '5', '张家岭村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇张家岭村民委员会', '36.020408', '103.721249', '张家岭村民委员会', '620103201203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201204', '620103201000', '5', '陶家沟村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇陶家沟村民委员会', '35.987463', '103.720577', '陶家沟村民委员会', '620103201204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201205', '620103201000', '5', '中庄村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇中庄村民委员会', '35.992469', '103.718147', '中庄村民委员会', '620103201205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201206', '620103201000', '5', '王官营村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇王官营村民委员会', '36.021512', '103.722316', '王官营村民委员会', '620103201206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201207', '620103201000', '5', '宋家沟村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇宋家沟村民委员会', '36.020408', '103.721249', '宋家沟村民委员会', '620103201207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201208', '620103201000', '5', '赵李家洼村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇赵李家洼村民委员会', '36.020408', '103.721249', '赵李家洼村民委员会', '620103201208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201209', '620103201000', '5', '邵家洼村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇邵家洼村民委员会', '35.967693', '103.643172', '邵家洼村民委员会', '620103201209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201210', '620103201000', '5', '王家庄村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇王家庄村民委员会', '35.956204', '103.6619', '王家庄村民委员会', '620103201210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620103201211', '620103201000', '5', '鲁家村民委员会', '99', '1', null, '甘肃省兰州市七里河区黄峪镇鲁家村民委员会', '36.020408', '103.721249', '鲁家村民委员会', '620103201211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104000000', '620100000000', '3', '西固区', '99', '1', null, '甘肃省兰州市西固区', '36.106472', '103.56268', '西固区', '620104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001000', '620104000000', '4', '陈坪街道', '99', '1', null, '甘肃省兰州市西固区陈坪街道', '36.078375', '103.666768', '陈坪街道', '620104001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001001', '620104001000', '5', '西固中路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道西固中路社区居民委员会', '36.103949', '103.642038', '西固中路社区居民委员会', '620104001001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001003', '620104001000', '5', '西固东路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道西固东路社区居民委员会', '36.096645', '103.662129', '西固东路社区居民委员会', '620104001003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001004', '620104001000', '5', '福利东路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道福利东路社区居民委员会', '36.0921', '103.655124', '福利东路社区居民委员会', '620104001004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001005', '620104001000', '5', '陈官营社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道陈官营社区居民委员会', '36.096979', '103.659295', '陈官营社区居民委员会', '620104001005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001006', '620104001000', '5', '新滩社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道新滩社区居民委员会', '36.078375', '103.666768', '新滩社区居民委员会', '620104001006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001007', '620104001000', '5', '小坪社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道小坪社区居民委员会', '36.08178', '103.6486', '小坪社区居民委员会', '620104001007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001008', '620104001000', '5', '东湾社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道东湾社区居民委员会', '36.09707', '103.667854', '东湾社区居民委员会', '620104001008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001009', '620104001000', '5', '兰馨花园社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道兰馨花园社区居民委员会', '36.092274', '103.650602', '兰馨花园社区居民委员会', '620104001009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001010', '620104001000', '5', '福利东路南社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道福利东路南社区居民委员会', '36.089615', '103.659614', '福利东路南社区居民委员会', '620104001010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001011', '620104001000', '5', '天庆新城社区居民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道天庆新城社区居民委员会', '36.078375', '103.666768', '天庆新城社区居民委员会', '620104001011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001201', '620104001000', '5', '范坪村民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道范坪村民委员会', '36.085856', '103.677976', '范坪村民委员会', '620104001201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104001202', '620104001000', '5', '孟家山村民委员会', '99', '1', null, '甘肃省兰州市西固区陈坪街道孟家山村民委员会', '36.078375', '103.666768', '孟家山村民委员会', '620104001202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002000', '620104000000', '4', '先锋路街道', '99', '1', null, '甘肃省兰州市西固区先锋路街道', '36.098421', '103.646008', '先锋路街道', '620104002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002001', '620104002000', '5', '文化社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道文化社区居民委员会', '36.098421', '103.646008', '文化社区居民委员会', '620104002001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002002', '620104002000', '5', '公园路东社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道公园路东社区居民委员会', '36.095666', '103.635618', '公园路东社区居民委员会', '620104002002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002003', '620104002000', '5', '幸福社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道幸福社区居民委员会', '36.098421', '103.646008', '幸福社区居民委员会', '620104002003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002004', '620104002000', '5', '东苑社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道东苑社区居民委员会', '36.098421', '103.646008', '东苑社区居民委员会', '620104002004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002005', '620104002000', '5', '南山社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道南山社区居民委员会', '36.098421', '103.646008', '南山社区居民委员会', '620104002005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002006', '620104002000', '5', '三姓庄社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道三姓庄社区居民委员会', '36.098421', '103.646008', '三姓庄社区居民委员会', '620104002006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002007', '620104002000', '5', '庄浪东路西社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道庄浪东路西社区居民委员会', '36.094023', '103.653419', '庄浪东路西社区居民委员会', '620104002007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002008', '620104002000', '5', '庄浪东路东社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道庄浪东路东社区居民委员会', '36.094023', '103.653419', '庄浪东路东社区居民委员会', '620104002008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002009', '620104002000', '5', '省建四公司社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道省建四公司社区居民委员会', '36.098421', '103.646008', '省建四公司社区居民委员会', '620104002009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002010', '620104002000', '5', '山丹街东路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道山丹街东路社区居民委员会', '36.096248', '103.630208', '山丹街东路社区居民委员会', '620104002010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002011', '620104002000', '5', '兰平玻璃厂西区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道兰平玻璃厂西区社区居民委员会', '36.098421', '103.646008', '兰平玻璃厂西区社区居民委员会', '620104002011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002012', '620104002000', '5', '兰平玻璃厂东区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道兰平玻璃厂东区社区居民委员会', '36.098421', '103.646008', '兰平玻璃厂东区社区居民委员会', '620104002012', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104002013', '620104002000', '5', '花园小区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区先锋路街道花园小区社区居民委员会', '36.098421', '103.646008', '花园小区社区居民委员会', '620104002013', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003000', '620104000000', '4', '福利路街道', '99', '1', null, '甘肃省兰州市西固区福利路街道', '36.102545', '103.624752', '福利路街道', '620104003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003001', '620104003000', '5', '公园路西社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道公园路西社区居民委员会', '36.095666', '103.635618', '公园路西社区居民委员会', '620104003001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003002', '620104003000', '5', '兰铝社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道兰铝社区居民委员会', '36.102545', '103.624752', '兰铝社区居民委员会', '620104003002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003003', '620104003000', '5', '山丹街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道山丹街社区居民委员会', '36.096248', '103.630208', '山丹街社区居民委员会', '620104003003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003004', '620104003000', '5', '兰化二十六街区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道兰化二十六街区社区居民委员会', '36.093452', '103.631758', '兰化二十六街区社区居民委员会', '620104003004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003005', '620104003000', '5', '兰化二十五街区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道兰化二十五街区社区居民委员会', '36.095912', '103.629503', '兰化二十五街区社区居民委员会', '620104003005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003006', '620104003000', '5', '红星社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道红星社区居民委员会', '36.099248', '103.636389', '红星社区居民委员会', '620104003006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003007', '620104003000', '5', '庄浪西路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道庄浪西路社区居民委员会', '36.101488', '103.630119', '庄浪西路社区居民委员会', '620104003007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003008', '620104003000', '5', '天鹅湖社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道天鹅湖社区居民委员会', '36.102545', '103.624752', '天鹅湖社区居民委员会', '620104003008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003009', '620104003000', '5', '福利路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道福利路社区居民委员会', '36.102545', '103.624752', '福利路社区居民委员会', '620104003009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003010', '620104003000', '5', '兰化二十二街区社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道兰化二十二街区社区居民委员会', '36.098247', '103.622759', '兰化二十二街区社区居民委员会', '620104003010', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104003011', '620104003000', '5', '福利西路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区福利路街道福利西路社区居民委员会', '36.101004', '103.62491', '福利西路社区居民委员会', '620104003011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004000', '620104000000', '4', '西固城街道', '99', '1', null, '甘肃省兰州市西固区西固城街道', '36.123132', '103.629308', '西固城街道', '620104004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004001', '620104004000', '5', '牌坊路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道牌坊路社区居民委员会', '36.097639', '103.646625', '牌坊路社区居民委员会', '620104004001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004002', '620104004000', '5', '兰棉厂社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道兰棉厂社区居民委员会', '36.101007', '103.643677', '兰棉厂社区居民委员会', '620104004002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004003', '620104004000', '5', '西固中路北社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道西固中路北社区居民委员会', '36.103949', '103.642038', '西固中路北社区居民委员会', '620104004003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004004', '620104004000', '5', '西固中路南社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道西固中路南社区居民委员会', '36.103949', '103.642038', '西固中路南社区居民委员会', '620104004004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004005', '620104004000', '5', '玉门街北社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道玉门街北社区居民委员会', '36.105747', '103.63753', '玉门街北社区居民委员会', '620104004005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004006', '620104004000', '5', '合水中路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道合水中路社区居民委员会', '36.123132', '103.629308', '合水中路社区居民委员会', '620104004006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004007', '620104004000', '5', '合水北路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道合水北路社区居民委员会', '36.120423', '103.633211', '合水北路社区居民委员会', '620104004007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004008', '620104004000', '5', '玉门街南社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道玉门街南社区居民委员会', '36.105747', '103.63753', '玉门街南社区居民委员会', '620104004008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104004009', '620104004000', '5', '清水桥社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西固城街道清水桥社区居民委员会', '36.123132', '103.629308', '清水桥社区居民委员会', '620104004009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005000', '620104000000', '4', '四季青街道', '99', '1', null, '甘肃省兰州市西固区四季青街道', '36.053999', '103.629862', '四季青街道', '620104005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005001', '620104005000', '5', '合水南路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道合水南路社区居民委员会', '36.10918', '103.630512', '合水南路社区居民委员会', '620104005001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005002', '620104005000', '5', '西固巷社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道西固巷社区居民委员会', '36.109546', '103.624324', '西固巷社区居民委员会', '620104005002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005003', '620104005000', '5', '桃园社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道桃园社区居民委员会', '36.053999', '103.629862', '桃园社区居民委员会', '620104005003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005004', '620104005000', '5', '古城社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道古城社区居民委员会', '36.053999', '103.629862', '古城社区居民委员会', '620104005004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005005', '620104005000', '5', '四季青社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道四季青社区居民委员会', '36.053999', '103.629862', '四季青社区居民委员会', '620104005005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005006', '620104005000', '5', '福源小镇社区居民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道福源小镇社区居民委员会', '36.095154', '103.622309', '福源小镇社区居民委员会', '620104005006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005201', '620104005000', '5', '杏胡台村民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道杏胡台村民委员会', '36.0801', '103.613009', '杏胡台村民委员会', '620104005201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005202', '620104005000', '5', '马耳山村民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道马耳山村民委员会', '36.053999', '103.629862', '马耳山村民委员会', '620104005202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104005203', '620104005000', '5', '光月山村民委员会', '99', '1', null, '甘肃省兰州市西固区四季青街道光月山村民委员会', '36.048784', '103.638662', '光月山村民委员会', '620104005203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006000', '620104000000', '4', '临洮街街道', '99', '1', null, '甘肃省兰州市西固区临洮街街道', '36.099406', '103.60062', '临洮街街道', '620104006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006001', '620104006000', '5', '康乐路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道康乐路社区居民委员会', '36.114293', '103.619096', '康乐路社区居民委员会', '620104006001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006002', '620104006000', '5', '清水街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道清水街社区居民委员会', '36.109907', '103.615422', '清水街社区居民委员会', '620104006002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006003', '620104006000', '5', '临洮街北街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道临洮街北街社区居民委员会', '36.10865', '103.609553', '临洮街北街社区居民委员会', '620104006003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006004', '620104006000', '5', '临洮街后街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道临洮街后街社区居民委员会', '36.10865', '103.609553', '临洮街后街社区居民委员会', '620104006004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006005', '620104006000', '5', '临洮街中街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道临洮街中街社区居民委员会', '36.10865', '103.609553', '临洮街中街社区居民委员会', '620104006005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006006', '620104006000', '5', '临洮街前街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道临洮街前街社区居民委员会', '36.10865', '103.609553', '临洮街前街社区居民委员会', '620104006006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104006007', '620104006000', '5', '寺儿沟社区居民委员会', '99', '1', null, '甘肃省兰州市西固区临洮街街道寺儿沟社区居民委员会', '36.10333', '103.617257', '寺儿沟社区居民委员会', '620104006007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007000', '620104000000', '4', '西柳沟街道', '99', '1', null, '甘肃省兰州市西固区西柳沟街道', '36.121948', '103.621764', '西柳沟街道', '620104007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007001', '620104007000', '5', '上坎社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道上坎社区居民委员会', '36.121948', '103.621764', '上坎社区居民委员会', '620104007001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007002', '620104007000', '5', '西柳沟社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道西柳沟社区居民委员会', '36.115675', '103.61691', '西柳沟社区居民委员会', '620104007002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007003', '620104007000', '5', '化工街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道化工街社区居民委员会', '36.120794', '103.6138', '化工街社区居民委员会', '620104007003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007004', '620104007000', '5', '月牙桥社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道月牙桥社区居民委员会', '36.121948', '103.621764', '月牙桥社区居民委员会', '620104007004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007005', '620104007000', '5', '古浪路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道古浪路社区居民委员会', '36.131515', '103.623865', '古浪路社区居民委员会', '620104007005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007201', '620104007000', '5', '张家大坪村民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道张家大坪村民委员会', '36.121948', '103.621764', '张家大坪村民委员会', '620104007201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104007202', '620104007000', '5', '柴家台村民委员会', '99', '1', null, '甘肃省兰州市西固区西柳沟街道柴家台村民委员会', '36.121948', '103.621764', '柴家台村民委员会', '620104007202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100000', '620104000000', '4', '新城镇', '99', '1', null, '甘肃省兰州市西固区新城镇', '36.155217', '103.465246', '新城镇', '620104100', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100001', '620104100000', '5', '河口南社区居民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇河口南社区居民委员会', '36.155217', '103.465246', '河口南社区居民委员会', '620104100001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100002', '620104100000', '5', '新城街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇新城街社区居民委员会', '36.155217', '103.465246', '新城街社区居民委员会', '620104100002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100003', '620104100000', '5', '新维街社区居民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇新维街社区居民委员会', '36.155217', '103.465246', '新维街社区居民委员会', '620104100003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100004', '620104100000', '5', '新冶路社区居民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇新冶路社区居民委员会', '36.155217', '103.465246', '新冶路社区居民委员会', '620104100004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100201', '620104100000', '5', '新联村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇新联村民委员会', '36.173752', '103.488555', '新联村民委员会', '620104100201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100202', '620104100000', '5', '下川村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇下川村民委员会', '36.167525', '103.50176', '下川村民委员会', '620104100202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100203', '620104100000', '5', '园艺村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇园艺村民委员会', '36.155217', '103.465246', '园艺村民委员会', '620104100203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100204', '620104100000', '5', '新合村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇新合村民委员会', '36.173245', '103.474581', '新合村民委员会', '620104100204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100205', '620104100000', '5', '青春村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇青春村民委员会', '36.16968', '103.452431', '青春村民委员会', '620104100205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104100206', '620104100000', '5', '青石台村民委员会', '99', '1', null, '甘肃省兰州市西固区新城镇青石台村民委员会', '36.150015', '103.473461', '青石台村民委员会', '620104100206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101000', '620104000000', '4', '东川镇', '99', '1', null, '甘肃省兰州市西固区东川镇', '36.143589', '103.539776', '东川镇', '620104101', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101001', '620104101000', '5', '东川社区居民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇东川社区居民委员会', '36.143589', '103.539776', '东川社区居民委员会', '620104101001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101201', '620104101000', '5', '下车村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇下车村民委员会', '36.143589', '103.539776', '下车村民委员会', '620104101201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101202', '620104101000', '5', '马泉村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇马泉村民委员会', '36.149052', '103.502814', '马泉村民委员会', '620104101202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101203', '620104101000', '5', '东河湾村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇东河湾村民委员会', '36.148661', '103.510503', '东河湾村民委员会', '620104101203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101204', '620104101000', '5', '坡底下村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇坡底下村民委员会', '36.143681', '103.522447', '坡底下村民委员会', '620104101204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101205', '620104101000', '5', '梁家湾村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇梁家湾村民委员会', '36.127411', '103.541339', '梁家湾村民委员会', '620104101205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104101206', '620104101000', '5', '龙爪山村民委员会', '99', '1', null, '甘肃省兰州市西固区东川镇龙爪山村民委员会', '36.113662', '103.517201', '龙爪山村民委员会', '620104101206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102000', '620104000000', '4', '河口镇', '99', '1', null, '甘肃省兰州市西固区河口镇', '36.187379', '103.425855', '河口镇', '620104102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102201', '620104102000', '5', '河口村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇河口村民委员会', '36.180122', '103.447528', '河口村民委员会', '620104102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102202', '620104102000', '5', '青杨村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇青杨村民委员会', '36.187379', '103.425855', '青杨村民委员会', '620104102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102203', '620104102000', '5', '石圈村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇石圈村民委员会', '36.187379', '103.425855', '石圈村民委员会', '620104102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102204', '620104102000', '5', '岗镇村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇岗镇村民委员会', '36.187379', '103.425855', '岗镇村民委员会', '620104102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102205', '620104102000', '5', '咸水村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇咸水村民委员会', '36.187379', '103.425855', '咸水村民委员会', '620104102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102206', '620104102000', '5', '八盘村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇八盘村民委员会', '36.187379', '103.425855', '八盘村民委员会', '620104102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102207', '620104102000', '5', '张家台村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇张家台村民委员会', '36.152858', '103.415375', '张家台村民委员会', '620104102207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102208', '620104102000', '5', '大滩村民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇大滩村民委员会', '36.187379', '103.425855', '大滩村民委员会', '620104102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104102400', '620104102000', '5', '河口社区居民委员会', '99', '1', null, '甘肃省兰州市西固区河口镇河口社区居民委员会', '36.187379', '103.425855', '河口社区居民委员会', '620104102400', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103000', '620104000000', '4', '达川镇', '99', '1', null, '甘肃省兰州市西固区达川镇', '36.183441', '103.37644', '达川镇', '620104103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103201', '620104103000', '5', '岔路村民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇岔路村民委员会', '36.183441', '103.37644', '岔路村民委员会', '620104103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103202', '620104103000', '5', '上车村民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇上车村民委员会', '36.183441', '103.37644', '上车村民委员会', '620104103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103203', '620104103000', '5', '河咀村民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇河咀村民委员会', '36.183441', '103.37644', '河咀村民委员会', '620104103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103204', '620104103000', '5', '吊庄村民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇吊庄村民委员会', '36.137156', '103.392294', '吊庄村民委员会', '620104103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103205', '620104103000', '5', '幸福村民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇幸福村民委员会', '36.14018', '103.372925', '幸福村民委员会', '620104103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104103400', '620104103000', '5', '达川社区居民委员会', '99', '1', null, '甘肃省兰州市西固区达川镇达川社区居民委员会', '36.183441', '103.37644', '达川社区居民委员会', '620104103400', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104000', '620104000000', '4', '柳泉镇', '99', '1', null, '甘肃省兰州市西固区柳泉镇', '36.121948', '103.596713', '柳泉镇', '620104104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104201', '620104104000', '5', '中坪村民委员会', '99', '1', null, '甘肃省兰州市西固区柳泉镇中坪村民委员会', '36.121948', '103.596713', '中坪村民委员会', '620104104201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104202', '620104104000', '5', '东坪村民委员会', '99', '1', null, '甘肃省兰州市西固区柳泉镇东坪村民委员会', '36.105924', '103.59401', '东坪村民委员会', '620104104202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104203', '620104104000', '5', '西坪村民委员会', '99', '1', null, '甘肃省兰州市西固区柳泉镇西坪村民委员会', '36.107854', '103.585395', '西坪村民委员会', '620104104203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104204', '620104104000', '5', '岸门村民委员会', '99', '1', null, '甘肃省兰州市西固区柳泉镇岸门村民委员会', '36.121948', '103.596713', '岸门村民委员会', '620104104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104104205', '620104104000', '5', '漫坡头村民委员会', '99', '1', null, '甘肃省兰州市西固区柳泉镇漫坡头村民委员会', '36.121948', '103.596713', '漫坡头村民委员会', '620104104205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104204000', '620104000000', '4', '金沟乡', '99', '1', null, '甘肃省兰州市西固区金沟乡', '36.019577', '103.65284', '金沟乡', '620104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104204201', '620104204000', '5', '小金沟村民委员会', '99', '1', null, '甘肃省兰州市西固区金沟乡小金沟村民委员会', '36.019577', '103.65284', '小金沟村民委员会', '620104204201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104204202', '620104204000', '5', '马家山村民委员会', '99', '1', null, '甘肃省兰州市西固区金沟乡马家山村民委员会', '36.050228', '103.660214', '马家山村民委员会', '620104204202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104204203', '620104204000', '5', '杨家咀村民委员会', '99', '1', null, '甘肃省兰州市西固区金沟乡杨家咀村民委员会', '36.019577', '103.65284', '杨家咀村民委员会', '620104204203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620104204204', '620104204000', '5', '熊子湾村民委员会', '99', '1', null, '甘肃省兰州市西固区金沟乡熊子湾村民委员会', '36.018137', '103.639762', '熊子湾村民委员会', '620104204204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105000000', '620100000000', '3', '安宁区', '99', '1', null, '甘肃省兰州市安宁区', '36.115523', '103.719156', '安宁区', '620105', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001000', '620105000000', '4', '培黎街道', '99', '1', null, '甘肃省兰州市安宁区培黎街道', '36.125359', '103.745045', '培黎街道', '620105001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001001', '620105001000', '5', '建宁路社区居委会', '99', '1', null, '甘肃省兰州市安宁区培黎街道建宁路社区居委会', '36.102687', '103.738322', '建宁路社区居委会', '620105001001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001002', '620105001000', '5', '向阳村社区居委会', '99', '1', null, '甘肃省兰州市安宁区培黎街道向阳村社区居委会', '36.104268', '103.755334', '向阳村社区居委会', '620105001002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001003', '620105001000', '5', '西北师大社区居委会', '99', '1', null, '甘肃省兰州市安宁区培黎街道西北师大社区居委会', '36.103828', '103.737789', '西北师大社区居委会', '620105001003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001004', '620105001000', '5', '甘铝社区居委会', '99', '1', null, '甘肃省兰州市安宁区培黎街道甘铝社区居委会', '36.125359', '103.745045', '甘铝社区居委会', '620105001004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105001005', '620105001000', '5', '培黎社区居委会', '99', '1', null, '甘肃省兰州市安宁区培黎街道培黎社区居委会', '36.107555', '103.751706', '培黎社区居委会', '620105001005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002000', '620105000000', '4', '西路街道', '99', '1', null, '甘肃省兰州市安宁区西路街道', '36.110396', '103.736681', '西路街道', '620105002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002001', '620105002000', '5', '水挂庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道水挂庄社区居委会', '36.107717', '103.73409', '水挂庄社区居委会', '620105002001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002002', '620105002000', '5', '费家营社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道费家营社区居委会', '36.111873', '103.726664', '费家营社区居委会', '620105002002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002003', '620105002000', '5', '万里社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道万里社区居委会', '36.110396', '103.736681', '万里社区居委会', '620105002003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002004', '620105002000', '5', '兰飞社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道兰飞社区居委会', '36.115523', '103.719156', '兰飞社区居委会', '620105002004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002005', '620105002000', '5', '阳光社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道阳光社区居委会', '36.120473', '103.722625', '阳光社区居委会', '620105002005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002006', '620105002000', '5', '枣林路社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道枣林路社区居委会', '36.11705', '103.705864', '枣林路社区居委会', '620105002006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002007', '620105002000', '5', '长风社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道长风社区居委会', '36.115249', '103.713271', '长风社区居委会', '620105002007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105002008', '620105002000', '5', '交大社区居委会', '99', '1', null, '甘肃省兰州市安宁区西路街道交大社区居委会', '36.110396', '103.736681', '交大社区居委会', '620105002008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003000', '620105000000', '4', '沙井驿街道', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道', '36.156077', '103.619861', '沙井驿街道', '620105003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003001', '620105003000', '5', '齿轮厂社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道齿轮厂社区居委会', '36.140359', '103.662084', '齿轮厂社区居委会', '620105003001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003002', '620105003000', '5', '元台子社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道元台子社区居委会', '36.145537', '103.656086', '元台子社区居委会', '620105003002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003003', '620105003000', '5', '沙井驿社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道沙井驿社区居委会', '36.156077', '103.619861', '沙井驿社区居委会', '620105003003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003004', '620105003000', '5', '西沙社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道西沙社区居委会', '36.156077', '103.619861', '西沙社区居委会', '620105003004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003005', '620105003000', '5', '沙井驿涉农社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道沙井驿涉农社区居委会', '36.148852', '103.656941', '沙井驿涉农社区居委会', '620105003005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003006', '620105003000', '5', '河湾社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道河湾社区居委会', '36.156077', '103.619861', '河湾社区居委会', '620105003006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003007', '620105003000', '5', '南坡坪社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道南坡坪社区居委会', '36.146844', '103.610467', '南坡坪社区居委会', '620105003007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105003008', '620105003000', '5', '焦家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区沙井驿街道焦家庄社区居委会', '36.156077', '103.619861', '焦家庄社区居委会', '620105003008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004000', '620105000000', '4', '十里店街道', '99', '1', null, '甘肃省兰州市安宁区十里店街道', '36.101254', '103.771903', '十里店街道', '620105004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004001', '620105004000', '5', '桥头社区委会', '99', '1', null, '甘肃省兰州市安宁区十里店街道桥头社区委会', '36.101254', '103.771903', '桥头社区委会', '620105004001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004002', '620105004000', '5', '南街社区居委会', '99', '1', null, '甘肃省兰州市安宁区十里店街道南街社区居委会', '36.101254', '103.771903', '南街社区居委会', '620105004002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004003', '620105004000', '5', '保安堡社区居委会', '99', '1', null, '甘肃省兰州市安宁区十里店街道保安堡社区居委会', '36.098152', '103.763646', '保安堡社区居委会', '620105004003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004004', '620105004000', '5', '园艺社区居委会', '99', '1', null, '甘肃省兰州市安宁区十里店街道园艺社区居委会', '36.0982', '103.757697', '园艺社区居委会', '620105004004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004005', '620105004000', '5', '和平社区居委会', '99', '1', null, '甘肃省兰州市安宁区十里店街道和平社区居委会', '36.096239', '103.752904', '和平社区居委会', '620105004005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105004006', '620105004000', '5', '黄河家园社区', '99', '1', null, '甘肃省兰州市安宁区十里店街道黄河家园社区', '36.093962', '103.762351', '黄河家园社区', '620105004006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005000', '620105000000', '4', '孔家崖街道', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道', '36.103937', '103.737772', '孔家崖街道', '620105005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005001', '620105005000', '5', '廖家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道廖家庄社区居委会', '36.103937', '103.737772', '廖家庄社区居委会', '620105005001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005002', '620105005000', '5', '刘家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道刘家庄社区居委会', '36.101614', '103.728264', '刘家庄社区居委会', '620105005002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005003', '620105005000', '5', '孔家崖社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道孔家崖社区居委会', '36.104376', '103.730258', '孔家崖社区居委会', '620105005003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005004', '620105005000', '5', '王家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道王家庄社区居委会', '36.103937', '103.737772', '王家庄社区居委会', '620105005004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005005', '620105005000', '5', '水挂庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道水挂庄社区居委会', '36.107717', '103.73409', '水挂庄社区居委会', '620105005005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105005006', '620105005000', '5', '科苑社区居委会', '99', '1', null, '甘肃省兰州市安宁区孔家崖街道科苑社区居委会', '36.103778', '103.730914', '科苑社区居委会', '620105005006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006000', '620105000000', '4', '银滩路街道', '99', '1', null, '甘肃省兰州市安宁区银滩路街道', '36.100881', '103.717267', '银滩路街道', '620105006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006001', '620105006000', '5', '农大社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道农大社区居委会', '36.100881', '103.717267', '农大社区居委会', '620105006001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006002', '620105006000', '5', '石磊庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道石磊庄社区居委会', '36.107893', '103.718714', '石磊庄社区居委会', '620105006002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006003', '620105006000', '5', '葛家巷道社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道葛家巷道社区居委会', '36.100881', '103.717267', '葛家巷道社区居委会', '620105006003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006004', '620105006000', '5', '乱庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道乱庄社区居委会', '36.100881', '103.717267', '乱庄社区居委会', '620105006004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006005', '620105006000', '5', '上庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道上庄社区居委会', '36.100881', '103.717267', '上庄社区居委会', '620105006005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006006', '620105006000', '5', '前庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道前庄社区居委会', '36.100881', '103.717267', '前庄社区居委会', '620105006006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006007', '620105006000', '5', '宝兴庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道宝兴庄社区居委会', '36.10134', '103.70478', '宝兴庄社区居委会', '620105006007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006008', '620105006000', '5', '李家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道李家庄社区居委会', '36.100881', '103.717267', '李家庄社区居委会', '620105006008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105006009', '620105006000', '5', '银滩路社区居委会', '99', '1', null, '甘肃省兰州市安宁区银滩路街道银滩路社区居委会', '36.111288', '103.714929', '银滩路社区居委会', '620105006009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007000', '620105000000', '4', '刘家堡街道', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道', '36.10876', '103.693222', '刘家堡街道', '620105007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007001', '620105007000', '5', '刘家堡社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道刘家堡社区居委会', '36.105493', '103.700891', '刘家堡社区居委会', '620105007001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007002', '620105007000', '5', '福兴路社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道福兴路社区居委会', '36.10876', '103.693222', '福星路社区居委会', '620105007002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007003', '620105007000', '5', '赵家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道赵家庄社区居委会', '36.104127', '103.701659', '赵家庄社区居委会', '620105007003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007004', '620105007000', '5', '吴家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道吴家庄社区居委会', '36.108147', '103.701432', '吴家庄社区居委会', '620105007004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007005', '620105007000', '5', '崔家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道崔家庄社区居委会', '36.114761', '103.690457', '崔家庄社区居委会', '620105007005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007006', '620105007000', '5', '邹家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道邹家庄社区居委会', '36.110865', '103.697486', '邹家庄社区居委会', '620105007006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007007', '620105007000', '5', '马家庄社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道马家庄社区居委会', '36.115756', '103.691657', '马家庄社区居委会', '620105007007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105007008', '620105007000', '5', '刘家堡涉农社区居委会', '99', '1', null, '甘肃省兰州市安宁区刘家堡街道刘家堡涉农社区居委会', '36.105493', '103.700891', '刘家堡涉农社区居委会', '620105007008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008000', '620105000000', '4', '安宁堡街道', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道', '36.143593', '103.678412', '安宁堡街道', '620105008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008002', '620105008000', '5', '黄家滩社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道黄家滩社区居委会', '36.127877', '103.679237', '黄家滩社区居委会', '620105008002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008003', '620105008000', '5', '南门社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道南门社区居委会', '36.143593', '103.678412', '南门社区居委会', '620105008003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008004', '620105008000', '5', '红艺社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道红艺社区居委会', '36.143593', '103.678412', '红艺社区居委会', '620105008004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008005', '620105008000', '5', '东门社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道东门社区居委会', '36.137259', '103.693974', '东门社区居委会', '620105008005', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008006', '620105008000', '5', '西街社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道西街社区居委会', '36.143593', '103.678412', '西街社区居委会', '620105008006', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008007', '620105008000', '5', '东街社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道东街社区居委会', '36.143593', '103.678412', '东街社区居委会', '620105008007', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008008', '620105008000', '5', '河涝坡社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道河涝坡社区居委会', '36.130373', '103.688728', '河涝坡社区居委会', '620105008008', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008009', '620105008000', '5', '桃林社区居委会', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道桃林社区居委会', '36.143593', '103.678412', '桃林社区居委会', '620105008009', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620105008011', '620105008000', '5', '农科中心', '99', '1', null, '甘肃省兰州市安宁区安宁堡街道农科中心', null, null, '农科中心', '620105008011', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111000000', '620100000000', '3', '红古区', '99', '1', null, '甘肃省兰州市红古区', '36.303488', '103.060275', '红古区', '620111', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001000', '620111000000', '4', '窑街街道', '99', '1', null, '甘肃省兰州市红古区窑街街道', '36.444228', '102.919615', '窑街街道', '620111001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001001', '620111001000', '5', '和平社区居民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道和平社区居民委员会', '36.444228', '102.919615', '和平社区居民委员会', '620111001001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001002', '620111001000', '5', '团结社区居民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道团结社区居民委员会', '36.444228', '102.919615', '团结社区居民委员会', '620111001002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001003', '620111001000', '5', '滨河社区居民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道滨河社区居民委员会', '36.444228', '102.919615', '滨河社区居民委员会', '620111001003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001004', '620111001000', '5', '新村社区居民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道新村社区居民委员会', '36.444228', '102.919615', '新村社区居民委员会', '620111001004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001200', '620111001000', '5', '红山村民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道红山村民委员会', '36.457085', '102.880112', '红山村民委员会', '620111001200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001201', '620111001000', '5', '大砂村民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道大砂村民委员会', '36.444228', '102.919615', '大砂村民委员会', '620111001201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111001202', '620111001000', '5', '上街村民委员会', '99', '1', null, '甘肃省兰州市红古区窑街街道上街村民委员会', '36.43802', '102.882533', '上街村民委员会', '620111001202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111002000', '620111000000', '4', '下窑街道', '99', '1', null, '甘肃省兰州市红古区下窑街道', '36.401781', '102.893505', '下窑街道', '620111002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111002001', '620111002000', '5', '跃进社区居民委员会', '99', '1', null, '甘肃省兰州市红古区下窑街道跃进社区居民委员会', '36.401781', '102.893505', '跃进社区居民委员会', '620111002001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111002002', '620111002000', '5', '新跃社区居民委员会', '99', '1', null, '甘肃省兰州市红古区下窑街道新跃社区居民委员会', '36.401781', '102.893505', '新跃社区居民委员会', '620111002002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111002003', '620111002000', '5', '二坪台社区居民委员会', '99', '1', null, '甘肃省兰州市红古区下窑街道二坪台社区居民委员会', '36.401781', '102.893505', '二坪台社区居民委员会', '620111002003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111002004', '620111002000', '5', '沙窝社区居民委员会', '99', '1', null, '甘肃省兰州市红古区下窑街道沙窝社区居民委员会', '36.401781', '102.893505', '沙窝社区居民委员会', '620111002004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111003000', '620111000000', '4', '矿区街道', '99', '1', null, '甘肃省兰州市红古区矿区街道', '36.423661', '102.907646', '矿区街道', '620111003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111003001', '620111003000', '5', '滩子社区居民委员会', '99', '1', null, '甘肃省兰州市红古区矿区街道滩子社区居民委员会', '36.423661', '102.907646', '滩子社区居民委员会', '620111003001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111003002', '620111003000', '5', '下街社区居民委员会', '99', '1', null, '甘肃省兰州市红古区矿区街道下街社区居民委员会', '36.423661', '102.907646', '下街社区居民委员会', '620111003002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111003003', '620111003000', '5', '山根社区居民委员会', '99', '1', null, '甘肃省兰州市红古区矿区街道山根社区居民委员会', '36.423661', '102.907646', '山根社区居民委员会', '620111003003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111003004', '620111003000', '5', '下窑社区居民委员会', '99', '1', null, '甘肃省兰州市红古区矿区街道下窑社区居民委员会', '36.421437', '102.871584', '下窑社区居民委员会', '620111003004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111004000', '620111000000', '4', '华龙街道', '99', '1', null, '甘肃省兰州市红古区华龙街道', '36.303488', '103.060275', '华龙街道', '620111004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111004001', '620111004000', '5', '华龙社区居民委员会', '99', '1', null, '甘肃省兰州市红古区华龙街道华龙社区居民委员会', '36.303488', '103.060275', '华龙社区居民委员会', '620111004001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111004002', '620111004000', '5', '复兴社区居民委员会', '99', '1', null, '甘肃省兰州市红古区华龙街道复兴社区居民委员会', '36.303488', '103.060275', '复兴社区居民委员会', '620111004002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111004200', '620111004000', '5', '下海石村民委员会', '99', '1', null, '甘肃省兰州市红古区华龙街道下海石村民委员会', '36.303488', '103.060275', '下海石村民委员会', '620111004200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101000', '620111000000', '4', '海石湾镇', '99', '1', null, '甘肃省兰州市红古区海石湾镇', '36.355185', '102.891331', '海石湾镇', '620111101', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101001', '620111101000', '5', '火车站社区居民委员会', '99', '1', null, '甘肃省兰州市红古区海石湾镇火车站社区居民委员会', '36.355185', '102.891331', '火车站社区居民委员会', '620111101001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101002', '620111101000', '5', '大通路社区居民委员会', '99', '1', null, '甘肃省兰州市红古区海石湾镇大通路社区居民委员会', '36.345795', '102.876423', '大通路社区居民委员会', '620111101002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101003', '620111101000', '5', '西苑社区居民委员会', '99', '1', null, '甘肃省兰州市红古区海石湾镇西苑社区居民委员会', '36.355185', '102.891331', '西苑社区居民委员会', '620111101003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101200', '620111101000', '5', '海石村民委员会', '99', '1', null, '甘肃省兰州市红古区海石湾镇海石村民委员会', '36.355185', '102.891331', '海石村民委员会', '620111101200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111101202', '620111101000', '5', '虎头崖村民委员会', '99', '1', null, '甘肃省兰州市红古区海石湾镇虎头崖村民委员会', '36.343869', '102.894019', '虎头崖村民委员会', '620111101202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102000', '620111000000', '4', '花庄镇', '99', '1', null, '甘肃省兰州市红古区花庄镇', '36.280545', '103.128924', '花庄镇', '620111102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102001', '620111102000', '5', '花庄社区居民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇花庄社区居民委员会', '36.280545', '103.128924', '花庄社区居民委员会', '620111102001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102002', '620111102000', '5', '白土路社区居民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇白土路社区居民委员会', '36.280545', '103.128924', '白土路社区居民委员会', '620111102002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102200', '620111102000', '5', '王家庄村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇王家庄村民委员会', '36.280545', '103.128924', '王家庄村民委员会', '620111102200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102201', '620111102000', '5', '洞子村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇洞子村民委员会', '36.229491', '103.077518', '洞子村民委员会', '620111102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102202', '620111102000', '5', '柳家村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇柳家村民委员会', '36.219181', '103.099698', '柳家村民委员会', '620111102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102203', '620111102000', '5', '青土坡村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇青土坡村民委员会', '36.231067', '103.104011', '青土坡村民委员会', '620111102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102204', '620111102000', '5', '北山村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇北山村民委员会', '36.349778', '103.202559', '北山村民委员会', '620111102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102205', '620111102000', '5', '河嘴村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇河嘴村民委员会', '36.280545', '103.128924', '河嘴村民委员会', '620111102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102206', '620111102000', '5', '苏家寺村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇苏家寺村民委员会', '36.280545', '103.128924', '苏家寺村民委员会', '620111102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102207', '620111102000', '5', '花庄村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇花庄村民委员会', '36.202867', '103.156559', '花庄村民委员会', '620111102207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111102208', '620111102000', '5', '湟兴村民委员会', '99', '1', null, '甘肃省兰州市红古区花庄镇湟兴村民委员会', '36.189671', '103.183547', '湟兴村民委员会', '620111102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103000', '620111000000', '4', '平安镇', '99', '1', null, '甘肃省兰州市红古区平安镇', '36.196725', '103.286437', '平安镇', '620111103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103001', '620111103000', '5', '平安台社区居民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇平安台社区居民委员会', '36.191116', '103.223196', '平安台社区居民委员会', '620111103001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103002', '620111103000', '5', '张家寺社区居民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇张家寺社区居民委员会', '36.173148', '103.274304', '张家寺社区居民委员会', '620111103002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103200', '620111103000', '5', '平安村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇平安村民委员会', '36.177522', '103.222305', '平安村民委员会', '620111103200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103201', '620111103000', '5', '若连村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇若连村民委员会', '36.196725', '103.286437', '若连村民委员会', '620111103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103202', '620111103000', '5', '上滩村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇上滩村民委员会', '36.172304', '103.266481', '上滩村民委员会', '620111103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103203', '620111103000', '5', '中和村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇中和村民委员会', '36.161236', '103.271353', '中和村民委员会', '620111103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103204', '620111103000', '5', '张家寺村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇张家寺村民委员会', '36.173148', '103.274304', '张家寺村民委员会', '620111103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103205', '620111103000', '5', '夹滩村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇夹滩村民委员会', '36.196725', '103.286437', '夹滩村民委员会', '620111103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103206', '620111103000', '5', '复兴村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇复兴村民委员会', '36.169361', '103.303137', '复兴村民委员会', '620111103206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103207', '620111103000', '5', '仁和村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇仁和村民委员会', '36.165121', '103.320544', '仁和村民委员会', '620111103207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103208', '620111103000', '5', '岗子村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇岗子村民委员会', '36.154817', '103.335481', '岗子村民委员会', '620111103208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103209', '620111103000', '5', '河湾村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇河湾村民委员会', '36.196725', '103.286437', '河湾村民委员会', '620111103209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111103210', '620111103000', '5', '新安村民委员会', '99', '1', null, '甘肃省兰州市红古区平安镇新安村民委员会', '36.196725', '103.286437', '新安村民委员会', '620111103210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104000', '620111000000', '4', '红古镇', '99', '1', null, '甘肃省兰州市红古区红古镇', '36.283648', '103.008649', '红古镇', '620111104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104001', '620111104000', '5', '红古社区居民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇红古社区居民委员会', '36.283648', '103.008649', '红古社区居民委员会', '620111104001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104200', '620111104000', '5', '旋子村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇旋子村民委员会', '36.327983', '102.917911', '旋子村民委员会', '620111104200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104201', '620111104000', '5', '王家口村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇王家口村民委员会', '36.302071', '102.954366', '王家口村民委员会', '620111104201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104202', '620111104000', '5', '米家台村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇米家台村民委员会', '36.320115', '102.957603', '米家台村民委员会', '620111104202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104203', '620111104000', '5', '薛家村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇薛家村民委员会', '36.306983', '102.976886', '薛家村民委员会', '620111104203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104204', '620111104000', '5', '水车湾村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇水车湾村民委员会', '36.283479', '102.995635', '水车湾村民委员会', '620111104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104205', '620111104000', '5', '新建村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇新建村民委员会', '36.27993', '103.011385', '新建村民委员会', '620111104205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104206', '620111104000', '5', '红古村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇红古村民委员会', '36.283648', '103.008649', '红古村民委员会', '620111104206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620111104207', '620111104000', '5', '新庄村民委员会', '99', '1', null, '甘肃省兰州市红古区红古镇新庄村民委员会', '36.252299', '103.042807', '新庄村民委员会', '620111104207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121000000', '620100000000', '3', '永登县', '99', '1', null, '甘肃省兰州市永登县', '36.616924', '103.252794', '永登县', '620121', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100000', '620121000000', '4', '城关镇', '99', '1', null, '甘肃省兰州市永登县城关镇', '36.717809', '103.249793', '城关镇', '620121100', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100001', '620121100000', '5', '南街居委会', '99', '1', null, '甘肃省兰州市永登县城关镇南街居委会', '36.736054', '103.26311', '南街居委会', '620121100001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100002', '620121100000', '5', '北街居委会', '99', '1', null, '甘肃省兰州市永登县城关镇北街居委会', '36.717809', '103.249793', '北街居委会', '620121100002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100003', '620121100000', '5', '东街居委会', '99', '1', null, '甘肃省兰州市永登县城关镇东街居委会', '36.740592', '103.271978', '东街居委会', '620121100003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100004', '620121100000', '5', '西街居委会', '99', '1', null, '甘肃省兰州市永登县城关镇西街居委会', '36.717809', '103.249793', '西街居委会', '620121100004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100201', '620121100000', '5', '南街村委会', '99', '1', null, '甘肃省兰州市永登县城关镇南街村委会', '36.737198', '103.269839', '南街村委会', '620121100201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100202', '620121100000', '5', '北街村委会', '99', '1', null, '甘肃省兰州市永登县城关镇北街村委会', '36.717809', '103.249793', '北街村委会', '620121100202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100203', '620121100000', '5', '百灵观村委会', '99', '1', null, '甘肃省兰州市永登县城关镇百灵观村委会', '36.717809', '103.249793', '百灵观村委会', '620121100203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100204', '620121100000', '5', '高家湾村委会', '99', '1', null, '甘肃省兰州市永登县城关镇高家湾村委会', '36.715377', '103.26689', '高家湾村委会', '620121100204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100205', '620121100000', '5', '五渠村委会', '99', '1', null, '甘肃省兰州市永登县城关镇五渠村委会', '36.717809', '103.249793', '五渠村委会', '620121100205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121100206', '620121100000', '5', '满城村委会', '99', '1', null, '甘肃省兰州市永登县城关镇满城村委会', '36.717809', '103.249793', '满城村委会', '620121100206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101000', '620121000000', '4', '红城镇', '99', '1', null, '甘肃省兰州市永登县红城镇', '36.464757', '103.39899', '红城镇', '620121101', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101201', '620121101000', '5', '宁朔村委会', '99', '1', null, '甘肃省兰州市永登县红城镇宁朔村委会', '36.464757', '103.39899', '宁朔村委会', '620121101201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101202', '620121101000', '5', '永安村委会', '99', '1', null, '甘肃省兰州市永登县红城镇永安村委会', '36.459988', '103.402744', '永安村委会', '620121101202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101203', '620121101000', '5', '徐家磨村委会', '99', '1', null, '甘肃省兰州市永登县红城镇徐家磨村委会', '36.434321', '103.404643', '徐家磨村委会', '620121101203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101204', '620121101000', '5', '华家山村委会', '99', '1', null, '甘肃省兰州市永登县红城镇华家山村委会', '36.464757', '103.39899', '华家山村委会', '620121101204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101205', '620121101000', '5', '野泉村委会', '99', '1', null, '甘肃省兰州市永登县红城镇野泉村委会', '36.464757', '103.39899', '野泉村委会', '620121101205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101206', '620121101000', '5', '下河村委会', '99', '1', null, '甘肃省兰州市永登县红城镇下河村委会', '36.464757', '103.39899', '下河村委会', '620121101206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101207', '620121101000', '5', '进化村委会', '99', '1', null, '甘肃省兰州市永登县红城镇进化村委会', '36.409952', '103.402159', '进化村委会', '620121101207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101208', '620121101000', '5', '凤山村委会', '99', '1', null, '甘肃省兰州市永登县红城镇凤山村委会', '36.421697', '103.398224', '凤山村委会', '620121101208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121101209', '620121101000', '5', '玉山村委会', '99', '1', null, '甘肃省兰州市永登县红城镇玉山村委会', '36.452253', '103.391377', '玉山村委会', '620121101209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102000', '620121000000', '4', '中堡镇', '99', '1', null, '甘肃省兰州市永登县中堡镇', '36.853893', '103.210515', '中堡镇', '620121102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102001', '620121102000', '5', '北坪台居委会', '99', '1', null, '甘肃省兰州市永登县中堡镇北坪台居委会', '36.853893', '103.210515', '北坪台居委会', '620121102001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102002', '620121102000', '5', '金城居委会', '99', '1', null, '甘肃省兰州市永登县中堡镇金城居委会', '36.796418', '103.231461', '金城居委会', '620121102002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102201', '620121102000', '5', '五里墩村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇五里墩村委会', '36.853893', '103.210515', '五里墩村委会', '620121102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102202', '620121102000', '5', '汪家湾村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇汪家湾村委会', '36.853893', '103.210515', '汪家湾村委会', '620121102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102203', '620121102000', '5', '塘土湾村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇塘土湾村委会', '36.791517', '103.221875', '塘土湾村委会', '620121102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102204', '620121102000', '5', '中堡村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇中堡村委会', '36.853893', '103.210515', '中堡村委会', '620121102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102205', '620121102000', '5', '清水河村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇清水河村委会', '36.842632', '103.210941', '清水河村委会', '620121102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102206', '620121102000', '5', '罗城滩村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇罗城滩村委会', '36.853893', '103.210515', '罗城滩村委会', '620121102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102207', '620121102000', '5', '大营湾村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇大营湾村委会', '36.853893', '103.210515', '大营湾村委会', '620121102207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102208', '620121102000', '5', '何家营村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇何家营村委会', '36.853893', '103.210515', '何家营村委会', '620121102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102209', '620121102000', '5', '鲁家庄村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇鲁家庄村委会', '36.853893', '103.210515', '鲁家庄村委会', '620121102209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121102210', '620121102000', '5', '邢家湾村委会', '99', '1', null, '甘肃省兰州市永登县中堡镇邢家湾村委会', '36.853893', '103.210515', '邢家湾村委会', '620121102210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103000', '620121000000', '4', '武胜驿镇', '99', '1', null, '甘肃省兰州市永登县武胜驿镇', '36.888502', '103.178145', '武胜驿镇', '620121103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103001', '620121103000', '5', '屯沟湾居委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇屯沟湾居委会', '36.878121', '103.20279', '屯沟湾居委会', '620121103001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103201', '620121103000', '5', '武胜驿村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇武胜驿村委会', '36.890944', '103.174688', '武胜驿村委会', '620121103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103202', '620121103000', '5', '富强堡村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇富强堡村委会', '36.92821', '103.153207', '富强堡村委会', '620121103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103203', '620121103000', '5', '新民村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇新民村委会', '36.899551', '103.085156', '新民村委会', '620121103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103204', '620121103000', '5', '聂家湾村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇聂家湾村委会', '36.888502', '103.178145', '聂家湾村委会', '620121103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103205', '620121103000', '5', '霍家湾村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇霍家湾村委会', '36.888502', '103.178145', '霍家湾村委会', '620121103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103206', '620121103000', '5', '道顺村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇道顺村委会', '36.888502', '103.178145', '道顺村委会', '620121103206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103207', '620121103000', '5', '石门砚村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇石门砚村委会', '36.888502', '103.178145', '石门砚村委会', '620121103207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103208', '620121103000', '5', '黑林村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇黑林村委会', '36.888502', '103.178145', '黑林村委会', '620121103208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103209', '620121103000', '5', '火家台村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇火家台村委会', '36.888502', '103.178145', '火家台村委会', '620121103209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103210', '620121103000', '5', '烧炭沟村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇烧炭沟村委会', '36.888502', '103.178145', '烧炭沟村委会', '620121103210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103211', '620121103000', '5', '金嘴村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇金嘴村委会', '36.888502', '103.178145', '金嘴村委会', '620121103211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103212', '620121103000', '5', '向阳村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇向阳村委会', '36.844132', '103.073538', '向阳村委会', '620121103212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103213', '620121103000', '5', '兑角村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇兑角村委会', '36.888502', '103.178145', '兑角村委会', '620121103213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103214', '620121103000', '5', '兰草村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇兰草村委会', '36.888502', '103.178145', '兰草村委会', '620121103214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103215', '620121103000', '5', '马荒村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇马荒村委会', '36.888502', '103.178145', '马荒村委会', '620121103215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103216', '620121103000', '5', '五段村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇五段村委会', '36.888502', '103.178145', '五段村委会', '620121103216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103217', '620121103000', '5', '长丰村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇长丰村委会', '36.767816', '103.145605', '长丰村委会', '620121103217', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103218', '620121103000', '5', '旅顺村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇旅顺村委会', '36.888502', '103.178145', '旅顺村委会', '620121103218', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103219', '620121103000', '5', '大利村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇大利村委会', '36.888502', '103.178145', '大利村委会', '620121103219', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103220', '620121103000', '5', '奖俊埠村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇奖俊埠村委会', '36.846112', '103.038749', '奖俊埠村委会', '620121103220', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103221', '620121103000', '5', '三庄村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇三庄村委会', '36.859373', '103.033377', '三庄村委会', '620121103221', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103222', '620121103000', '5', '缸子沟村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇缸子沟村委会', '36.857854', '103.038317', '缸子沟村委会', '620121103222', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121103223', '620121103000', '5', '石家滩村委会', '99', '1', null, '甘肃省兰州市永登县武胜驿镇石家滩村委会', '36.91127', '102.928391', '石家滩村委会', '620121103223', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104000', '620121000000', '4', '河桥镇', '99', '1', null, '甘肃省兰州市永登县河桥镇', '36.505655', '102.892728', '河桥镇', '620121104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104001', '620121104000', '5', '南关居委会', '99', '1', null, '甘肃省兰州市永登县河桥镇南关居委会', '36.505655', '102.892728', '南关居委会', '620121104001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104002', '620121104000', '5', '连铝居委会', '99', '1', null, '甘肃省兰州市永登县河桥镇连铝居委会', '36.505655', '102.892728', '连铝居委会', '620121104002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104201', '620121104000', '5', '河桥村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇河桥村委会', '36.534826', '102.888392', '河桥村委会', '620121104201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104202', '620121104000', '5', '南关村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇南关村委会', '36.529709', '102.890334', '南关村委会', '620121104202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104203', '620121104000', '5', '马莲滩村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇马莲滩村委会', '36.505655', '102.892728', '马莲滩村委会', '620121104203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104204', '620121104000', '5', '团结村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇团结村委会', '36.509039', '102.881583', '团结村委会', '620121104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104205', '620121104000', '5', '马军村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇马军村委会', '36.496505', '102.889054', '马军村委会', '620121104205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104206', '620121104000', '5', '乐山村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇乐山村委会', '36.481243', '102.883826', '乐山村委会', '620121104206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104207', '620121104000', '5', '蒋家坪村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇蒋家坪村委会', '36.505655', '102.892728', '蒋家坪村委会', '620121104207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104208', '620121104000', '5', '七里村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇七里村委会', '36.505655', '102.892728', '七里村委会', '620121104208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104209', '620121104000', '5', '四渠村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇四渠村委会', '36.505655', '102.892728', '四渠村委会', '620121104209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104210', '620121104000', '5', '敖塔村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇敖塔村委会', '36.505655', '102.892728', '敖塔村委会', '620121104210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121104211', '620121104000', '5', '主卜村委会', '99', '1', null, '甘肃省兰州市永登县河桥镇主卜村委会', '36.431979', '102.870742', '主卜村委会', '620121104211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105000', '620121000000', '4', '连城镇', '99', '1', null, '甘肃省兰州市永登县连城镇', '36.592768', '102.854297', '连城镇', '620121105', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105001', '620121105000', '5', '铁家台居委会', '99', '1', null, '甘肃省兰州市永登县连城镇铁家台居委会', '36.612444', '102.823556', '铁家台居委会', '620121105001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105201', '620121105000', '5', '浪排村委会', '99', '1', null, '甘肃省兰州市永登县连城镇浪排村委会', '36.585337', '102.864687', '浪排村委会', '620121105201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105202', '620121105000', '5', '连城村委会', '99', '1', null, '甘肃省兰州市永登县连城镇连城村委会', '36.592768', '102.854297', '连城村委会', '620121105202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105203', '620121105000', '5', '东河沿村委会', '99', '1', null, '甘肃省兰州市永登县连城镇东河沿村委会', '36.592768', '102.854297', '东河沿村委会', '620121105203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105204', '620121105000', '5', '淌沟村委会', '99', '1', null, '甘肃省兰州市永登县连城镇淌沟村委会', '36.592768', '102.854297', '淌沟村委会', '620121105204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105205', '620121105000', '5', '丰乐村委会', '99', '1', null, '甘肃省兰州市永登县连城镇丰乐村委会', '36.592768', '102.854297', '丰乐村委会', '620121105205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105206', '620121105000', '5', '永和村委会', '99', '1', null, '甘肃省兰州市永登县连城镇永和村委会', '36.582124', '102.843882', '永和村委会', '620121105206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105207', '620121105000', '5', '明家庄村委会', '99', '1', null, '甘肃省兰州市永登县连城镇明家庄村委会', '36.559695', '102.873192', '明家庄村委会', '620121105207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121105208', '620121105000', '5', '牛站村委会', '99', '1', null, '甘肃省兰州市永登县连城镇牛站村委会', '36.592768', '102.854297', '牛站村委会', '620121105208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106000', '620121000000', '4', '苦水镇', '99', '1', null, '甘肃省兰州市永登县苦水镇', '36.27856', '103.440016', '苦水镇', '620121106', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106201', '620121106000', '5', '周家庄村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇周家庄村委会', '36.27856', '103.440016', '周家庄村委会', '620121106201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106202', '620121106000', '5', '大沙沟村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇大沙沟村委会', '36.27856', '103.440016', '大沙沟村委会', '620121106202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106203', '620121106000', '5', '寺滩村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇寺滩村委会', '36.27856', '103.440016', '寺滩村委会', '620121106203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106204', '620121106000', '5', '苦水街村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇苦水街村委会', '36.27856', '103.440016', '苦水街村委会', '620121106204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106205', '620121106000', '5', '转轮寺村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇转轮寺村委会', '36.27856', '103.440016', '转轮寺村委会', '620121106205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106206', '620121106000', '5', '沙湾村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇沙湾村委会', '36.27856', '103.440016', '沙湾村委会', '620121106206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106207', '620121106000', '5', '大路村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇大路村委会', '36.274805', '103.439208', '大路村委会', '620121106207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106208', '620121106000', '5', '胡家坝村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇胡家坝村委会', '36.27856', '103.440016', '胡家坝村委会', '620121106208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106209', '620121106000', '5', '十里铺村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇十里铺村委会', '36.29774', '103.436167', '十里铺村委会', '620121106209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106210', '620121106000', '5', '下新沟村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇下新沟村委会', '36.27856', '103.440016', '下新沟村委会', '620121106210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106211', '620121106000', '5', '上新沟村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇上新沟村委会', '36.27856', '103.440016', '上新沟村委会', '620121106211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121106212', '620121106000', '5', '新屯川村委会', '99', '1', null, '甘肃省兰州市永登县苦水镇新屯川村委会', '36.352223', '103.415671', '新屯川村委会', '620121106212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109000', '620121000000', '4', '大同镇', '99', '1', null, '甘肃省兰州市永登县大同镇', '36.6213', '103.355578', '大同镇', '620121109', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109201', '620121109000', '5', '王家坪村委会', '99', '1', null, '甘肃省兰州市永登县大同镇王家坪村委会', '36.6213', '103.355578', '王家坪村委会', '620121109201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109202', '620121109000', '5', '郭家墩村委会', '99', '1', null, '甘肃省兰州市永登县大同镇郭家墩村委会', '36.647094', '103.33219', '郭家墩村委会', '620121109202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109203', '620121109000', '5', '北同村委会', '99', '1', null, '甘肃省兰州市永登县大同镇北同村委会', '36.6213', '103.355578', '北同村委会', '620121109203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109204', '620121109000', '5', '南同村委会', '99', '1', null, '甘肃省兰州市永登县大同镇南同村委会', '36.6213', '103.355578', '南同村委会', '620121109204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109205', '620121109000', '5', '泉水沟村委会', '99', '1', null, '甘肃省兰州市永登县大同镇泉水沟村委会', '36.597864', '103.371142', '泉水沟村委会', '620121109205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109206', '620121109000', '5', '高岑村委会', '99', '1', null, '甘肃省兰州市永登县大同镇高岑村委会', '36.6213', '103.355578', '高岑村委会', '620121109206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109207', '620121109000', '5', '安山村委会', '99', '1', null, '甘肃省兰州市永登县大同镇安山村委会', '36.560645', '103.386016', '安山村委会', '620121109207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109208', '620121109000', '5', '新农村村委会', '99', '1', null, '甘肃省兰州市永登县大同镇新农村村委会', '36.544068', '103.387802', '新农村村委会', '620121109208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109209', '620121109000', '5', '青寺村委会', '99', '1', null, '甘肃省兰州市永登县大同镇青寺村委会', '36.537587', '103.389443', '青寺村委会', '620121109209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109210', '620121109000', '5', '贾家场村委会', '99', '1', null, '甘肃省兰州市永登县大同镇贾家场村委会', '36.6213', '103.355578', '贾家场村委会', '620121109210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109211', '620121109000', '5', '保家湾村委会', '99', '1', null, '甘肃省兰州市永登县大同镇保家湾村委会', '36.6213', '103.355578', '保家湾村委会', '620121109211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109212', '620121109000', '5', '跌马沟村委会', '99', '1', null, '甘肃省兰州市永登县大同镇跌马沟村委会', '36.62788', '103.407802', '跌马沟村委会', '620121109212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121109213', '620121109000', '5', '长川村委会', '99', '1', null, '甘肃省兰州市永登县大同镇长川村委会', '36.624365', '103.444926', '长川村委会', '620121109213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110000', '620121000000', '4', '龙泉寺镇', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇', '36.486513', '103.394908', '龙泉寺镇', '620121110', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110201', '620121110000', '5', '福山村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇福山村委会', '36.527536', '103.398793', '福山村委会', '620121110201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110202', '620121110000', '5', '瑞芝村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇瑞芝村委会', '36.517266', '103.393451', '瑞芝村委会', '620121110202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110203', '620121110000', '5', '胡家湾村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇胡家湾村委会', '36.498447', '103.394869', '胡家湾村委会', '620121110203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110204', '620121110000', '5', '龙泉村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇龙泉村委会', '36.497193', '103.395057', '龙泉村委会', '620121110204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110205', '620121110000', '5', '水槽沟村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇水槽沟村委会', '36.485832', '103.397066', '水槽沟村委会', '620121110205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110206', '620121110000', '5', '河西村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇河西村委会', '36.47364', '103.389887', '河西村委会', '620121110206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110207', '620121110000', '5', '杨家营村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇杨家营村委会', '36.494342', '103.384193', '杨家营村委会', '620121110207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110208', '620121110000', '5', '费家湾村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇费家湾村委会', '36.486513', '103.394908', '费家湾村委会', '620121110208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110209', '620121110000', '5', '童家窑村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇童家窑村委会', '36.486513', '103.394908', '童家窑村委会', '620121110209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110210', '620121110000', '5', '花园村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇花园村委会', '36.486513', '103.394908', '花园村委会', '620121110210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110211', '620121110000', '5', '长涝池村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇长涝池村委会', '36.616924', '103.252794', '长涝池村委会', '620121110211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110212', '620121110000', '5', '官路沟村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇官路沟村委会', '36.486513', '103.394908', '官路沟村委会', '620121110212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110213', '620121110000', '5', '大涝池村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇大涝池村委会', '36.486513', '103.394908', '大涝池村委会', '620121110213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110214', '620121110000', '5', '土门川村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇土门川村委会', '36.488543', '103.489358', '土门川村委会', '620121110214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110215', '620121110000', '5', '碱柴井村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇碱柴井村委会', '36.486513', '103.394908', '碱柴井村委会', '620121110215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121110216', '620121110000', '5', '深沟村委会', '99', '1', null, '甘肃省兰州市永登县龙泉寺镇深沟村委会', '36.486513', '103.394908', '深沟村委会', '620121110216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111000', '620121000000', '4', '树屏镇', '99', '1', null, '甘肃省兰州市永登县树屏镇', '36.317685', '103.599034', '树屏镇', '620121111', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111201', '620121111000', '5', '树屏村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇树屏村委会', '36.317685', '103.599034', '树屏村委会', '620121111201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111202', '620121111000', '5', '上滩村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇上滩村委会', '36.384645', '103.506005', '上滩村委会', '620121111202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111203', '620121111000', '5', '刘家湾村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇刘家湾村委会', '36.408349', '103.613108', '刘家湾村委会', '620121111203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111204', '620121111000', '5', '毛茨村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇毛茨村委会', '36.317685', '103.599034', '毛茨村委会', '620121111204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111205', '620121111000', '5', '哈家咀村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇哈家咀村委会', '36.317685', '103.599034', '哈家咀村委会', '620121111205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111206', '620121111000', '5', '东沟村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇东沟村委会', '36.312696', '103.652079', '东沟村委会', '620121111206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111207', '620121111000', '5', '崖头村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇崖头村委会', '36.317685', '103.599034', '崖头村委会', '620121111207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121111208', '620121111000', '5', '杏花村委会', '99', '1', null, '甘肃省兰州市永登县树屏镇杏花村委会', '36.262173', '103.552312', '杏花村委会', '620121111208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112000', '620121000000', '4', '上川镇', '99', '1', null, '甘肃省兰州市永登县上川镇', '36.727409', '103.636368', '上川镇', '620121112', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112201', '620121112000', '5', '黄茨滩村委会', '99', '1', null, '甘肃省兰州市永登县上川镇黄茨滩村委会', '36.728987', '103.634721', '黄茨滩村委会', '620121112201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112202', '620121112000', '5', '红井槽村委会', '99', '1', null, '甘肃省兰州市永登县上川镇红井槽村委会', '36.706055', '103.640765', '红井槽村委会', '620121112202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112203', '620121112000', '5', '砂梁墩村委会', '99', '1', null, '甘肃省兰州市永登县上川镇砂梁墩村委会', '36.702512', '103.682165', '砂梁墩村委会', '620121112203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112204', '620121112000', '5', '甘露池村委会', '99', '1', null, '甘肃省兰州市永登县上川镇甘露池村委会', '36.742837', '103.686228', '甘露池村委会', '620121112204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112205', '620121112000', '5', '达家梁村委会', '99', '1', null, '甘肃省兰州市永登县上川镇达家梁村委会', '36.77903', '103.642242', '达家梁村委会', '620121112205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112206', '620121112000', '5', '四泉村委会', '99', '1', null, '甘肃省兰州市永登县上川镇四泉村委会', '36.727409', '103.636368', '四泉村委会', '620121112206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112207', '620121112000', '5', '古联村委会', '99', '1', null, '甘肃省兰州市永登县上川镇古联村委会', '36.754005', '103.580406', '古联村委会', '620121112207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112208', '620121112000', '5', '祁联村委会', '99', '1', null, '甘肃省兰州市永登县上川镇祁联村委会', '36.727409', '103.636368', '祁联村委会', '620121112208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112209', '620121112000', '5', '苗联村委会', '99', '1', null, '甘肃省兰州市永登县上川镇苗联村委会', '36.727409', '103.636368', '苗联村委会', '620121112209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112210', '620121112000', '5', '涝池滩村委会', '99', '1', null, '甘肃省兰州市永登县上川镇涝池滩村委会', '36.74424', '103.555244', '涝池滩村委会', '620121112210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112211', '620121112000', '5', '五联村委会', '99', '1', null, '甘肃省兰州市永登县上川镇五联村委会', '36.727409', '103.636368', '五联村委会', '620121112211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112212', '620121112000', '5', '上古山村委会', '99', '1', null, '甘肃省兰州市永登县上川镇上古山村委会', '36.727409', '103.636368', '上古山村委会', '620121112212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112213', '620121112000', '5', '下古山村委会', '99', '1', null, '甘肃省兰州市永登县上川镇下古山村委会', '36.727409', '103.636368', '下古山村委会', '620121112213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112214', '620121112000', '5', '东昌村委会', '99', '1', null, '甘肃省兰州市永登县上川镇东昌村委会', '36.727409', '103.636368', '东昌村委会', '620121112214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121112215', '620121112000', '5', '天山村委会', '99', '1', null, '甘肃省兰州市永登县上川镇天山村委会', '36.717462', '103.655587', '天山村委会', '620121112215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113000', '620121000000', '4', '柳树镇', '99', '1', null, '甘肃省兰州市永登县柳树镇', '36.728083', '103.393645', '柳树镇', '620121113', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113201', '620121113000', '5', '复兴村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇复兴村委会', '36.711155', '103.285467', '复兴村委会', '620121113201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113202', '620121113000', '5', '牌路村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇牌路村委会', '36.728083', '103.393645', '牌路村委会', '620121113202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113203', '620121113000', '5', '涧沟村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇涧沟村委会', '36.728083', '103.393645', '涧沟村委会', '620121113203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113204', '620121113000', '5', '柳树村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇柳树村委会', '36.728083', '103.393645', '柳树村委会', '620121113204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113205', '620121113000', '5', '营儿村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇营儿村委会', '36.728083', '103.393645', '营儿村委会', '620121113205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113206', '620121113000', '5', '黑城村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇黑城村委会', '36.728083', '103.393645', '黑城村委会', '620121113206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113207', '620121113000', '5', '山岑村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇山岑村委会', '36.728083', '103.393645', '山岑村委会', '620121113207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113208', '620121113000', '5', '李家湾村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇李家湾村委会', '36.698582', '103.274801', '李家湾村委会', '620121113208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113209', '620121113000', '5', '康家井村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇康家井村委会', '36.728083', '103.393645', '康家井村委会', '620121113209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113210', '620121113000', '5', '韩家井村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇韩家井村委会', '36.722159', '103.462498', '韩家井村委会', '620121113210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113211', '620121113000', '5', '孙家井村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇孙家井村委会', '36.761849', '103.461129', '孙家井村委会', '620121113211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113212', '620121113000', '5', '清水村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇清水村委会', '36.754379', '103.403409', '清水村委会', '620121113212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113213', '620121113000', '5', '红沙川村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇红沙川村委会', '36.789961', '103.360107', '红沙川村委会', '620121113213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121113214', '620121113000', '5', '教场村委会', '99', '1', null, '甘肃省兰州市永登县柳树镇教场村委会', '36.797334', '103.32391', '教场村委会', '620121113214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201000', '620121000000', '4', '坪城乡', '99', '1', null, '甘肃省兰州市永登县坪城乡', '36.97094', '103.361654', '坪城乡', '620121201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201201', '620121201000', '5', '长山河村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡长山河村委会', '36.99141', '103.550912', '长山河村委会', '620121201201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201202', '620121201000', '5', '白土咀村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡白土咀村委会', '36.97094', '103.361654', '白土咀村委会', '620121201202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201203', '620121201000', '5', '满塘村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡满塘村委会', '36.951648', '103.465889', '满塘村委会', '620121201203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201204', '620121201000', '5', '中塘村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡中塘村委会', '36.950274', '103.427416', '中塘村委会', '620121201204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201205', '620121201000', '5', '坪城村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡坪城村委会', '36.97094', '103.361654', '坪城村委会', '620121201205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201206', '620121201000', '5', '歇地沟村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡歇地沟村委会', '36.97094', '103.361654', '歇地沟村委会', '620121201206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201207', '620121201000', '5', '横沟村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡横沟村委会', '36.872262', '103.356177', '横沟村委会', '620121201207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201208', '620121201000', '5', '高家湾村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡高家湾村委会', '36.87906', '103.339142', '高家湾村委会', '620121201208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201209', '620121201000', '5', '火石洞村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡火石洞村委会', '36.97094', '103.361654', '火石洞村委会', '620121201209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201210', '620121201000', '5', '英鸽咀村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡英鸽咀村委会', '36.97094', '103.361654', '英鸽咀村委会', '620121201210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201211', '620121201000', '5', '井儿沟村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡井儿沟村委会', '36.97094', '103.361654', '井儿沟村委会', '620121201211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201212', '620121201000', '5', '三岔村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡三岔村委会', '36.97094', '103.361654', '三岔村委会', '620121201212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121201213', '620121201000', '5', '小沙沟村委会', '99', '1', null, '甘肃省兰州市永登县坪城乡小沙沟村委会', '37.038661', '103.198512', '小沙沟村委会', '620121201213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202000', '620121000000', '4', '民乐乡', '99', '1', null, '甘肃省兰州市永登县民乐乡', '36.755764', '102.94524', '民乐乡', '620121202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202201', '620121202000', '5', '细沟村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡细沟村委会', '36.597962', '102.912188', '细沟村委会', '620121202201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202202', '620121202000', '5', '普贯村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡普贯村委会', '36.755764', '102.94524', '普贯村委会', '620121202202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202203', '620121202000', '5', '前庄村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡前庄村委会', '36.661063', '102.929673', '前庄村委会', '620121202203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202204', '620121202000', '5', '卜洞村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡卜洞村委会', '36.702259', '102.903276', '卜洞村委会', '620121202204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202205', '620121202000', '5', '铁丰村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡铁丰村委会', '36.71561', '102.930143', '铁丰村委会', '620121202205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202206', '620121202000', '5', '柏杨村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡柏杨村委会', '36.694222', '102.966841', '柏杨村委会', '620121202206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202207', '620121202000', '5', '八岭村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡八岭村委会', '36.755764', '102.94524', '八岭村委会', '620121202207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202208', '620121202000', '5', '玉泉村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡玉泉村委会', '36.755764', '102.94524', '玉泉村委会', '620121202208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202209', '620121202000', '5', '西川村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡西川村委会', '36.755764', '102.94524', '西川村委会', '620121202209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202210', '620121202000', '5', '清泉村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡清泉村委会', '36.755764', '102.94524', '清泉村委会', '620121202210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202211', '620121202000', '5', '红岭村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡红岭村委会', '36.740452', '103.002326', '红岭村委会', '620121202211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202212', '620121202000', '5', '南沟村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡南沟村委会', '36.727102', '102.977097', '南沟村委会', '620121202212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202213', '620121202000', '5', '漫水村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡漫水村委会', '36.754446', '102.979617', '漫水村委会', '620121202213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202214', '620121202000', '5', '中川村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡中川村委会', '36.755764', '102.94524', '中川村委会', '620121202214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202215', '620121202000', '5', '先锋村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡先锋村委会', '36.758575', '102.865398', '先锋村委会', '620121202215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202216', '620121202000', '5', '黑龙村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡黑龙村委会', '36.755764', '102.94524', '黑龙村委会', '620121202216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202217', '620121202000', '5', '大湾村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡大湾村委会', '36.801438', '102.847457', '大湾村委会', '620121202217', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202218', '620121202000', '5', '井滩村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡井滩村委会', '36.755764', '102.94524', '井滩村委会', '620121202218', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202219', '620121202000', '5', '绽龙村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡绽龙村委会', '36.755764', '102.94524', '绽龙村委会', '620121202219', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202220', '620121202000', '5', '宽沟村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡宽沟村委会', '36.837123', '102.897327', '宽沟村委会', '620121202220', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202221', '620121202000', '5', '小有村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡小有村委会', '36.755764', '102.94524', '小有村委会', '620121202221', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202222', '620121202000', '5', '安仁村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡安仁村委会', '36.755764', '102.94524', '安仁村委会', '620121202222', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121202223', '620121202000', '5', '下川村委会', '99', '1', null, '甘肃省兰州市永登县民乐乡下川村委会', '36.775083', '102.917386', '下川村委会', '620121202223', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203000', '620121000000', '4', '通远乡', '99', '1', null, '甘肃省兰州市永登县通远乡', '36.654242', '103.039477', '通远乡', '620121203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203201', '620121203000', '5', '牌楼村委会', '99', '1', null, '甘肃省兰州市永登县通远乡牌楼村委会', '36.650674', '103.0362', '牌楼村委会', '620121203201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203202', '620121203000', '5', '晓林村委会', '99', '1', null, '甘肃省兰州市永登县通远乡晓林村委会', '36.588415', '102.984311', '晓林村委会', '620121203202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203203', '620121203000', '5', '上坪村委会', '99', '1', null, '甘肃省兰州市永登县通远乡上坪村委会', '36.654242', '103.039477', '上坪村委会', '620121203203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203204', '620121203000', '5', '边岭村委会', '99', '1', null, '甘肃省兰州市永登县通远乡边岭村委会', '36.654242', '103.039477', '边岭村委会', '620121203204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203205', '620121203000', '5', '团庄村委会', '99', '1', null, '甘肃省兰州市永登县通远乡团庄村委会', '36.712985', '103.042053', '团庄村委会', '620121203205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203206', '620121203000', '5', '青岭村委会', '99', '1', null, '甘肃省兰州市永登县通远乡青岭村委会', '36.654242', '103.039477', '青岭村委会', '620121203206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203207', '620121203000', '5', '临坪村委会', '99', '1', null, '甘肃省兰州市永登县通远乡临坪村委会', '36.694116', '103.12466', '临坪村委会', '620121203207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203208', '620121203000', '5', '捷岭村委会', '99', '1', null, '甘肃省兰州市永登县通远乡捷岭村委会', '36.702875', '103.168564', '捷岭村委会', '620121203208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203209', '620121203000', '5', '张坪村委会', '99', '1', null, '甘肃省兰州市永登县通远乡张坪村委会', '36.654242', '103.039477', '张坪村委会', '620121203209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121203210', '620121203000', '5', '涝池村委会', '99', '1', null, '甘肃省兰州市永登县通远乡涝池村委会', '36.654242', '103.039477', '涝池村委会', '620121203210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204000', '620121000000', '4', '七山乡', '99', '1', null, '甘肃省兰州市永登县七山乡', '36.519554', '103.053874', '七山乡', '620121204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204201', '620121204000', '5', '长沟村委会', '99', '1', null, '甘肃省兰州市永登县七山乡长沟村委会', '36.561809', '103.064803', '长沟村委会', '620121204201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204202', '620121204000', '5', '庞沟村委会', '99', '1', null, '甘肃省兰州市永登县七山乡庞沟村委会', '36.519554', '103.053874', '庞沟村委会', '620121204202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204203', '620121204000', '5', '官川村委会', '99', '1', null, '甘肃省兰州市永登县七山乡官川村委会', '36.519554', '103.053874', '官川村委会', '620121204203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204204', '620121204000', '5', '苏家峡村委会', '99', '1', null, '甘肃省兰州市永登县七山乡苏家峡村委会', '36.363061', '103.109278', '苏家峡村委会', '620121204204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204205', '620121204000', '5', '地沟村委会', '99', '1', null, '甘肃省兰州市永登县七山乡地沟村委会', '36.519554', '103.053874', '地沟村委会', '620121204205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204206', '620121204000', '5', '前山村委会', '99', '1', null, '甘肃省兰州市永登县七山乡前山村委会', '36.443835', '103.177121', '前山村委会', '620121204206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204207', '620121204000', '5', '鱼盆村委会', '99', '1', null, '甘肃省兰州市永登县七山乡鱼盆村委会', '36.50366', '103.156889', '鱼盆村委会', '620121204207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204208', '620121204000', '5', '雄湾村委会', '99', '1', null, '甘肃省兰州市永登县七山乡雄湾村委会', '36.513862', '103.131255', '雄湾村委会', '620121204208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620121204209', '620121204000', '5', '岢岱村委会', '99', '1', null, '甘肃省兰州市永登县七山乡岢岱村委会', '36.519554', '103.053874', '岢岱村委会', '620121204209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122000000', '620100000000', '3', '皋兰县', '99', '1', null, '甘肃省兰州市皋兰县', '36.394688', '103.890467', '皋兰县', '620122', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100000', '620122000000', '4', '石洞镇', '99', '1', null, '甘肃省兰州市皋兰县石洞镇', '36.408932', '104.023959', '石洞镇', '620122100', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100001', '620122100000', '5', '城中社区', '99', '1', null, '甘肃省兰州市皋兰县石洞镇城中社区', '36.408932', '104.023959', '城中社区', '620122100001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100002', '620122100000', '5', '城北社区', '99', '1', null, '甘肃省兰州市皋兰县石洞镇城北社区', '36.408932', '104.023959', '城北社区', '620122100002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100003', '620122100000', '5', '三川口社区', '99', '1', null, '甘肃省兰州市皋兰县石洞镇三川口社区', '36.408932', '104.023959', '三川口社区', '620122100003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100004', '620122100000', '5', '城南社区', '99', '1', null, '甘肃省兰州市皋兰县石洞镇城南社区', '36.408932', '104.023959', '城南社区', '620122100004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100201', '620122100000', '5', '庄子坪村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇庄子坪村委会', '36.408932', '104.023959', '庄子坪村委会', '620122100201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100202', '620122100000', '5', '东湾村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇东湾村委会', '36.341336', '103.960696', '东湾村委会', '620122100202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100203', '620122100000', '5', '中堡村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇中堡村委会', '36.408932', '104.023959', '中堡村委会', '620122100203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100204', '620122100000', '5', '魏家庄村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇魏家庄村委会', '36.408932', '104.023959', '魏家庄村委会', '620122100204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100205', '620122100000', '5', '蔡河村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇蔡河村委会', '36.408932', '104.023959', '蔡河村委会', '620122100205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100206', '620122100000', '5', '豆家庄村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇豆家庄村委会', '36.281837', '103.974065', '豆家庄村委会', '620122100206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100207', '620122100000', '5', '文山村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇文山村委会', '36.251469', '103.98335', '文山村委会', '620122100207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100208', '620122100000', '5', '涧沟村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇涧沟村委会', '36.408932', '104.023959', '涧沟村委会', '620122100208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100209', '620122100000', '5', '明星村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇明星村委会', '36.411302', '103.967859', '明星村委会', '620122100209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100210', '620122100000', '5', '丰水村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇丰水村委会', '36.461347', '103.990527', '丰水村委会', '620122100210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122100211', '620122100000', '5', '阳洼窑村委会', '99', '1', null, '甘肃省兰州市皋兰县石洞镇阳洼窑村委会', '36.408932', '104.023959', '阳洼窑村委会', '620122100211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102000', '620122000000', '4', '忠和镇', '99', '1', null, '甘肃省兰州市皋兰县忠和镇', '36.234626', '103.808031', '忠和镇', '620122102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102001', '620122102000', '5', '盐池社区', '99', '1', null, '甘肃省兰州市皋兰县忠和镇盐池社区', '36.234626', '103.808031', '盐池社区', '620122102001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102201', '620122102000', '5', '忠和村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇忠和村委会', '36.193117', '103.828573', '忠和村委会', '620122102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102202', '620122102000', '5', '崖川村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇崖川村委会', '36.234626', '103.808031', '崖川村委会', '620122102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102203', '620122102000', '5', '丰登村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇丰登村委会', '36.234626', '103.808031', '丰登村委会', '620122102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102204', '620122102000', '5', '平岘村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇平岘村委会', '36.193117', '103.828573', '平岘村委会', '620122102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102205', '620122102000', '5', '六合村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇六合村委会', '36.234626', '103.808031', '六合村委会', '620122102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102206', '620122102000', '5', '盐池村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇盐池村委会', '36.144613', '103.751231', '盐池村委会', '620122102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102207', '620122102000', '5', '罗官村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇罗官村委会', '36.234626', '103.808031', '罗官村委会', '620122102207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122102208', '620122102000', '5', '水源村委会', '99', '1', null, '甘肃省兰州市皋兰县忠和镇水源村委会', '36.145752', '103.88671', '水源村委会', '620122102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103000', '620122000000', '4', '什川镇', '99', '1', null, '甘肃省兰州市皋兰县什川镇', '36.177986', '104.073848', '什川镇', '620122103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103201', '620122103000', '5', '上车村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇上车村委会', '36.161467', '104.008317', '上车村委会', '620122103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103202', '620122103000', '5', '长坡村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇长坡村委会', '36.160236', '104.020744', '长坡村委会', '620122103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103203', '620122103000', '5', '南庄村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇南庄村委会', '36.167646', '104.021497', '南庄村委会', '620122103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103204', '620122103000', '5', '北庄村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇北庄村委会', '36.170364', '104.014038', '北庄村委会', '620122103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103205', '620122103000', '5', '上泥湾村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇上泥湾村委会', '36.176307', '103.993949', '上泥湾村委会', '620122103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103206', '620122103000', '5', '下泥湾村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇下泥湾村委会', '36.177986', '104.073848', '下泥湾村委会', '620122103206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103207', '620122103000', '5', '河口村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇河口村委会', '36.204476', '104.026838', '河口村委会', '620122103207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103208', '620122103000', '5', '打磨沟村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇打磨沟村委会', '36.131428', '103.96691', '打磨沟村委会', '620122103208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122103209', '620122103000', '5', '接官亭村委会', '99', '1', null, '甘肃省兰州市皋兰县什川镇接官亭村委会', '36.156344', '104.028926', '接官亭村委会', '620122103209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104000', '620122000000', '4', '九合镇', '99', '1', null, '甘肃省兰州市皋兰县九合镇', '36.264514', '103.638574', '九合镇', '620122104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104201', '620122104000', '5', '中心村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇中心村委会', '36.259538', '103.651202', '中心村委会', '620122104201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104202', '620122104000', '5', '九合村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇九合村委会', '36.264514', '103.638574', '九合村委会', '620122104202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104203', '620122104000', '5', '兰沟村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇兰沟村委会', '36.286733', '103.643248', '兰沟村委会', '620122104203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104204', '620122104000', '5', '高山村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇高山村委会', '36.324597', '103.70454', '高山村委会', '620122104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104205', '620122104000', '5', '钱家窑村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇钱家窑村委会', '36.275808', '103.690403', '钱家窑村委会', '620122104205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104206', '620122104000', '5', '头沟村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇头沟村委会', '36.264514', '103.638574', '头沟村委会', '620122104206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104207', '620122104000', '5', '李家沟村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇李家沟村委会', '36.222933', '103.65519', '李家沟村委会', '620122104207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104208', '620122104000', '5', '三坪村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇三坪村委会', '36.264514', '103.638574', '三坪村委会', '620122104208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104209', '620122104000', '5', '朱家井村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇朱家井村委会', '36.170712', '103.697253', '朱家井村委会', '620122104209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104210', '620122104000', '5', '曹家湾村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇曹家湾村委会', '36.165159', '103.72064', '曹家湾村委会', '620122104210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122104211', '620122104000', '5', '金沙村委会', '99', '1', null, '甘肃省兰州市皋兰县九合镇金沙村委会', '36.264514', '103.638574', '金沙村委会', '620122104211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105000', '620122000000', '4', '水阜镇', '99', '1', null, '甘肃省兰州市皋兰县水阜镇', '36.312501', '103.858944', '水阜镇', '620122105', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105201', '620122105000', '5', '彬草村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇彬草村委会', '36.312501', '103.858944', '彬草村委会', '620122105201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105202', '620122105000', '5', '涝池村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇涝池村委会', '36.36798', '103.814407', '涝池村委会', '620122105202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105203', '620122105000', '5', '砂岗村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇砂岗村委会', '36.312501', '103.858944', '砂岗村委会', '620122105203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105204', '620122105000', '5', '水阜村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇水阜村委会', '36.312501', '103.858944', '水阜村委会', '620122105204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105205', '620122105000', '5', '燕儿坪村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇燕儿坪村委会', '36.312501', '103.858944', '燕儿坪村委会', '620122105205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105206', '620122105000', '5', '长川村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇长川村委会', '36.266226', '103.928028', '长川村委会', '620122105206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122105207', '620122105000', '5', '老鹳村委会', '99', '1', null, '甘肃省兰州市皋兰县水阜镇老鹳村委会', '36.312501', '103.858944', '老鹳村委会', '620122105207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106000', '620122000000', '4', '黑石镇', '99', '1', null, '甘肃省兰州市皋兰县黑石镇', '36.51087', '103.901669', '黑石镇', '620122106', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106201', '620122106000', '5', '石青村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇石青村委会', '36.745162', '103.750851', '石青村委会', '620122106201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106202', '620122106000', '5', '白坡村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇白坡村委会', '36.79207', '103.884617', '白坡村委会', '620122106202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106203', '620122106000', '5', '大横村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇大横村委会', '36.672973', '103.83448', '大横村委会', '620122106203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106204', '620122106000', '5', '三和村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇三和村委会', '36.561268', '103.864983', '三和村委会', '620122106204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106205', '620122106000', '5', '黑石村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇黑石村委会', '36.51087', '103.901669', '黑石村委会', '620122106205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106206', '620122106000', '5', '和平村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇和平村委会', '36.475985', '103.949541', '和平村委会', '620122106206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106207', '620122106000', '5', '星湾村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇星湾村委会', '36.51087', '103.901669', '星湾村委会', '620122106207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106208', '620122106000', '5', '白崖村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇白崖村委会', '36.447634', '103.93697', '白崖村委会', '620122106208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106209', '620122106000', '5', '中窑村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇中窑村委会', '36.415153', '103.938376', '中窑村委会', '620122106209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106210', '620122106000', '5', '红柳村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇红柳村委会', '36.51087', '103.901669', '红柳村委会', '620122106210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620122106211', '620122106000', '5', '新地村委会', '99', '1', null, '甘肃省兰州市皋兰县黑石镇新地村委会', '36.51087', '103.901669', '新地村委会', '620122106211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123000000', '620100000000', '3', '榆中县', '99', '1', null, '甘肃省兰州市榆中县', '35.999785', '104.24429', '榆中县', '620123', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100000', '620123000000', '4', '城关镇', '99', '1', null, '甘肃省兰州市榆中县城关镇', '35.852104', '104.097184', '城关镇', '620123100', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100001', '620123100000', '5', '兴隆路居委会', '99', '1', null, '甘肃省兰州市榆中县城关镇兴隆路居委会', '35.852104', '104.097184', '兴隆路居委会', '620123100001', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100002', '620123100000', '5', '一悟路居委会', '99', '1', null, '甘肃省兰州市榆中县城关镇一悟路居委会', '35.852104', '104.097184', '一悟路居委会', '620123100002', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100003', '620123100000', '5', '栖云北路居委会', '99', '1', null, '甘肃省兰州市榆中县城关镇栖云北路居委会', '35.856569', '104.119405', '栖云北路居委会', '620123100003', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100004', '620123100000', '5', '文成路居委会', '99', '1', null, '甘肃省兰州市榆中县城关镇文成路居委会', '35.852104', '104.097184', '文成路居委会', '620123100004', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100201', '620123100000', '5', '兴隆山村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇兴隆山村委会', '35.827735', '104.087802', '兴隆山村委会', '620123100201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100202', '620123100000', '5', '南坡湾村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇南坡湾村委会', '35.83', '104.103622', '南坡湾村委会', '620123100202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100203', '620123100000', '5', '杨家庄村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇杨家庄村委会', '35.852104', '104.097184', '杨家庄村委会', '620123100203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100204', '620123100000', '5', '南关村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇南关村委会', '35.852104', '104.097184', '南关村委会', '620123100204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100205', '620123100000', '5', '东湾村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇东湾村委会', '35.830578', '104.152137', '东湾村委会', '620123100205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100206', '620123100000', '5', '下汉村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇下汉村委会', '35.852104', '104.097184', '下汉村委会', '620123100206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100207', '620123100000', '5', '城关村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇城关村委会', '35.852104', '104.097184', '城关村委会', '620123100207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100208', '620123100000', '5', '北关村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇北关村委会', '35.862702', '104.136353', '北关村委会', '620123100208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100209', '620123100000', '5', '金家圈村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇金家圈村委会', '35.852104', '104.097184', '金家圈村委会', '620123100209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100210', '620123100000', '5', '大营村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇大营村委会', '35.878688', '104.105487', '大营村委会', '620123100210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100211', '620123100000', '5', '李家庄村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇李家庄村委会', '35.852104', '104.097184', '李家庄村委会', '620123100211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100212', '620123100000', '5', '上蒲家村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇上蒲家村委会', '35.852104', '104.097184', '上蒲家村委会', '620123100212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100213', '620123100000', '5', '朱家湾村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇朱家湾村委会', '35.852104', '104.097184', '朱家湾村委会', '620123100213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123100214', '620123100000', '5', '分豁岔村委会', '99', '1', null, '甘肃省兰州市榆中县城关镇分豁岔村委会', '35.852104', '104.097184', '分豁岔村委会', '620123100214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101000', '620123000000', '4', '夏官营镇', '99', '1', null, '甘肃省兰州市榆中县夏官营镇', '36.032607', '104.221647', '夏官营镇', '620123101', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101201', '620123101000', '5', '过店子村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇过店子村委会', '35.991755', '104.157312', '过店子村委会', '620123101201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101202', '620123101000', '5', '太平堡村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇太平堡村委会', '35.977792', '104.176992', '太平堡村委会', '620123101202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101203', '620123101000', '5', '夏官营村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇夏官营村委会', '36.032607', '104.221647', '夏官营村委会', '620123101203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101204', '620123101000', '5', '红柳沟村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇红柳沟村委会', '36.032607', '104.221647', '红柳沟村委会', '620123101204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101205', '620123101000', '5', '中河堡村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇中河堡村委会', '36.032607', '104.221647', '中河堡村委会', '620123101205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101206', '620123101000', '5', '高家崖村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇高家崖村委会', '36.032607', '104.221647', '高家崖村委会', '620123101206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101207', '620123101000', '5', '彭家湾村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇彭家湾村委会', '36.032607', '104.221647', '彭家湾村委会', '620123101207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123101208', '620123101000', '5', '郝家湾村委会', '99', '1', null, '甘肃省兰州市榆中县夏官营镇郝家湾村委会', '36.032607', '104.221647', '郝家湾村委会', '620123101208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102000', '620123000000', '4', '高崖镇', '99', '1', null, '甘肃省兰州市榆中县高崖镇', '35.715406', '104.279618', '高崖镇', '620123102', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102201', '620123102000', '5', '砂河村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇砂河村委会', '35.715406', '104.279618', '砂河村委会', '620123102201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102202', '620123102000', '5', '关门口村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇关门口村委会', '35.715406', '104.279618', '关门口村委会', '620123102202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102203', '620123102000', '5', '高崖村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇高崖村委会', '35.715406', '104.279618', '高崖村委会', '620123102203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102204', '620123102000', '5', '新窑坡村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇新窑坡村委会', '35.708757', '104.302576', '新窑坡村委会', '620123102204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102205', '620123102000', '5', '马家集村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇马家集村委会', '35.715406', '104.279618', '马家集村委会', '620123102205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102206', '620123102000', '5', '小营子村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇小营子村委会', '35.715406', '104.279618', '小营子村委会', '620123102206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102207', '620123102000', '5', '李家磨村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇李家磨村委会', '35.756302', '104.297881', '李家磨村委会', '620123102207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102208', '620123102000', '5', '裴家岔村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇裴家岔村委会', '35.752689', '104.320611', '裴家岔村委会', '620123102208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102209', '620123102000', '5', '树梓沟村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇树梓沟村委会', '35.733339', '104.270927', '树梓沟村委会', '620123102209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102210', '620123102000', '5', '马家咀村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇马家咀村委会', '35.715406', '104.279618', '马家咀村委会', '620123102210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123102211', '620123102000', '5', '湖滩村委会', '99', '1', null, '甘肃省兰州市榆中县高崖镇湖滩村委会', '35.715406', '104.279618', '湖滩村委会', '620123102211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103000', '620123000000', '4', '金崖镇', '99', '1', null, '甘肃省兰州市榆中县金崖镇', '36.067524', '104.153022', '金崖镇', '620123103', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103201', '620123103000', '5', '金崖村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇金崖村委会', '36.024966', '104.104988', '金崖村委会', '620123103201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103202', '620123103000', '5', '永丰村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇永丰村委会', '36.017977', '104.112724', '永丰村委会', '620123103202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103203', '620123103000', '5', '古城村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇古城村委会', '36.012252', '104.126641', '古城村委会', '620123103203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103204', '620123103000', '5', '梁家湾村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇梁家湾村委会', '36.008899', '104.133041', '梁家湾村委会', '620123103204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103205', '620123103000', '5', '齐家坪村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇齐家坪村委会', '36.067524', '104.153022', '齐家坪村委会', '620123103205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103206', '620123103000', '5', '陆家崖村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇陆家崖村委会', '36.004784', '104.110455', '陆家崖村委会', '620123103206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103207', '620123103000', '5', '张家湾村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇张家湾村委会', '36.018371', '104.084543', '张家湾村委会', '620123103207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103208', '620123103000', '5', '窦家营村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇窦家营村委会', '36.067524', '104.153022', '窦家营村委会', '620123103208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103209', '620123103000', '5', '寺隆沟村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇寺隆沟村委会', '36.036619', '104.077664', '寺隆沟村委会', '620123103209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103210', '620123103000', '5', '邴家湾村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇邴家湾村委会', '36.028903', '104.089701', '邴家湾村委会', '620123103210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103211', '620123103000', '5', '豆家岘村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇豆家岘村委会', '36.114547', '104.181807', '豆家岘村委会', '620123103211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103212', '620123103000', '5', '大涝池村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇大涝池村委会', '36.067524', '104.153022', '大涝池村委会', '620123103212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103213', '620123103000', '5', '大耳朵村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇大耳朵村委会', '36.067524', '104.153022', '大耳朵村委会', '620123103213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123103214', '620123103000', '5', '瓦子岘村委会', '99', '1', null, '甘肃省兰州市榆中县金崖镇瓦子岘村委会', '36.067524', '104.153022', '瓦子岘村委会', '620123103214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104000', '620123000000', '4', '和平镇', '99', '1', null, '甘肃省兰州市榆中县和平镇', '35.97731', '103.938135', '和平镇', '620123104', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104201', '620123104000', '5', '和平村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇和平村委会', '36.005751', '103.981084', '和平村委会', '620123104201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104202', '620123104000', '5', '袁家营村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇袁家营村委会', '35.97731', '103.938135', '袁家营村委会', '620123104202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104203', '620123104000', '5', '沈家河村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇沈家河村委会', '35.983639', '103.956161', '沈家河村委会', '620123104203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104204', '620123104000', '5', '祁家坡村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇祁家坡村委会', '35.97731', '103.938135', '祁家坡村委会', '620123104204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104205', '620123104000', '5', '高营村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇高营村委会', '35.97731', '103.938135', '高营村委会', '620123104205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104206', '620123104000', '5', '菜籽山村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇菜籽山村委会', '35.97731', '103.938135', '菜籽山村委会', '620123104206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104207', '620123104000', '5', '范家营村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇范家营村委会', '35.97731', '103.938135', '范家营村委会', '620123104207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104208', '620123104000', '5', '马家山村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇马家山村委会', '35.97731', '103.938135', '马家山村委会', '620123104208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104209', '620123104000', '5', '豆家山村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇豆家山村委会', '35.97731', '103.938135', '豆家山村委会', '620123104209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104210', '620123104000', '5', '邵家泉村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇邵家泉村委会', '35.97731', '103.938135', '邵家泉村委会', '620123104210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104211', '620123104000', '5', '直沟门村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇直沟门村委会', '35.955766', '103.894803', '直沟门村委会', '620123104211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104212', '620123104000', '5', '陈家庄村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇陈家庄村委会', '35.97731', '103.938135', '陈家庄村委会', '620123104212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123104213', '620123104000', '5', '路口村委会', '99', '1', null, '甘肃省兰州市榆中县和平镇路口村委会', '35.97731', '103.938135', '路口村委会', '620123104213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105000', '620123000000', '4', '甘草店镇', '99', '1', null, '甘肃省兰州市榆中县甘草店镇', '35.796878', '104.325963', '甘草店镇', '620123105', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105201', '620123105000', '5', '三墩营村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇三墩营村委会', '35.829292', '104.28615', '三墩营村委会', '620123105201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105202', '620123105000', '5', '西村村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇西村村委会', '35.796878', '104.325963', '西村村委会', '620123105202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105203', '620123105000', '5', '东村村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇东村村委会', '35.796878', '104.325963', '东村村委会', '620123105203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105204', '620123105000', '5', '果园村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇果园村委会', '35.777219', '104.301093', '果园村委会', '620123105204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105205', '620123105000', '5', '项家堡村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇项家堡村委会', '35.785184', '104.28323', '项家堡村委会', '620123105205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105206', '620123105000', '5', '钱家坪村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇钱家坪村委会', '35.796878', '104.325963', '钱家坪村委会', '620123105206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105207', '620123105000', '5', '咸水岔村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇咸水岔村委会', '35.765228', '104.341503', '咸水岔村委会', '620123105207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105208', '620123105000', '5', '车道岭村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇车道岭村委会', '35.796878', '104.325963', '车道岭村委会', '620123105208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105209', '620123105000', '5', '唐家岔村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇唐家岔村委会', '35.820296', '104.371255', '唐家岔村委会', '620123105209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105210', '620123105000', '5', '克涝村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇克涝村委会', '35.796878', '104.325963', '克涝村委会', '620123105210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105211', '620123105000', '5', '蔡家沟村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇蔡家沟村委会', '35.796878', '104.325963', '蔡家沟村委会', '620123105211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105212', '620123105000', '5', '郭家湾村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇郭家湾村委会', '35.789548', '104.251715', '郭家湾村委会', '620123105212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123105213', '620123105000', '5', '好地岔村委会', '99', '1', null, '甘肃省兰州市榆中县甘草店镇好地岔村委会', '35.795714', '104.27874', '好地岔村委会', '620123105213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106000', '620123000000', '4', '青城镇', '99', '1', null, '甘肃省兰州市榆中县青城镇', '36.333454', '104.195194', '青城镇', '620123106', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106201', '620123106000', '5', '青城村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇青城村委会', '36.333454', '104.195194', '青城村委会', '620123106201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106202', '620123106000', '5', '苇茨湾村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇苇茨湾村委会', '36.333454', '104.195194', '苇茨湾村委会', '620123106202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106203', '620123106000', '5', '城河村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇城河村委会', '36.333454', '104.195194', '城河村委会', '620123106203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106204', '620123106000', '5', '新民村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇新民村委会', '36.334588', '104.200534', '新民村委会', '620123106204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106205', '620123106000', '5', '瓦窑村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇瓦窑村委会', '36.338959', '104.210266', '瓦窑村委会', '620123106205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106206', '620123106000', '5', '红岘村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇红岘村委会', '36.333454', '104.195194', '红岘村委会', '620123106206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106207', '620123106000', '5', '改地村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇改地村委会', '36.333454', '104.195194', '改地村委会', '620123106207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106208', '620123106000', '5', '上坪村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇上坪村委会', '36.089658', '103.892362', '上坪村委会', '620123106208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106209', '620123106000', '5', '下坪村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇下坪村委会', '36.333454', '104.195194', '下坪村委会', '620123106209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106210', '620123106000', '5', '东滩村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇东滩村委会', '36.35013', '104.252529', '东滩村委会', '620123106210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106211', '620123106000', '5', '红湾村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇红湾村委会', '36.348286', '104.253919', '红湾村委会', '620123106211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106212', '620123106000', '5', '大园子村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇大园子村委会', '36.333454', '104.195194', '大园子村委会', '620123106212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106213', '620123106000', '5', '建亭村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇建亭村委会', '36.333454', '104.195194', '建亭村委会', '620123106213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123106214', '620123106000', '5', '三合村委会', '99', '1', null, '甘肃省兰州市榆中县青城镇三合村委会', '36.333454', '104.195194', '三合村委会', '620123106214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107000', '620123000000', '4', '定远镇', '99', '1', null, '甘肃省兰州市榆中县定远镇', '35.95928', '104.005619', '定远镇', '620123107', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107201', '620123107000', '5', '歇家咀村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇歇家咀村委会', '35.95928', '104.005619', '歇家咀村委会', '620123107201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107202', '620123107000', '5', '董家湾村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇董家湾村委会', '35.95928', '104.005619', '董家湾村委会', '620123107202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107203', '620123107000', '5', '定远村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇定远村委会', '35.999785', '104.24429', '定远村委会', '620123107203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107204', '620123107000', '5', '张老营村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇张老营村委会', '35.95928', '104.005619', '张老营村委会', '620123107204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107205', '620123107000', '5', '蒋家营村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇蒋家营村委会', '35.997004', '104.030016', '蒋家营村委会', '620123107205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107206', '620123107000', '5', '猪咀岭村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇猪咀岭村委会', '35.95928', '104.005619', '猪咀岭村委会', '620123107206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107207', '620123107000', '5', '安家营村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇安家营村委会', '35.999785', '104.24429', '安家营村委会', '620123107207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107208', '620123107000', '5', '邓家营村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇邓家营村委会', '35.95928', '104.005619', '邓家营村委会', '620123107208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107209', '620123107000', '5', '陈家沟村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇陈家沟村委会', '35.95928', '104.005619', '陈家沟村委会', '620123107209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107210', '620123107000', '5', '矿湾村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇矿湾村委会', '35.95928', '104.005619', '矿湾村委会', '620123107210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107211', '620123107000', '5', '水岔沟村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇水岔沟村委会', '35.917078', '103.999981', '水岔沟村委会', '620123107211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123107212', '620123107000', '5', '转嘴子村委会', '99', '1', null, '甘肃省兰州市榆中县定远镇转嘴子村委会', '35.95928', '104.005619', '转嘴子村委会', '620123107212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108000', '620123000000', '4', '连搭镇', '99', '1', null, '甘肃省兰州市榆中县连搭镇', '35.916786', '104.076917', '连搭镇', '620123108', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108201', '620123108000', '5', '马家洼村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇马家洼村委会', '35.916786', '104.076917', '马家洼村委会', '620123108201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108202', '620123108000', '5', '麻家寺村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇麻家寺村委会', '35.888704', '104.041551', '麻家寺村委会', '620123108202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108203', '620123108000', '5', '秦启营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇秦启营村委会', '35.880076', '104.048696', '秦启营村委会', '620123108203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108204', '620123108000', '5', '朱家沟村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇朱家沟村委会', '35.884256', '104.061763', '朱家沟村委会', '620123108204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108205', '620123108000', '5', '薛家营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇薛家营村委会', '35.916786', '104.076917', '薛家营村委会', '620123108205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108206', '620123108000', '5', '孙家坡村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇孙家坡村委会', '35.920857', '104.037892', '孙家坡村委会', '620123108206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108207', '620123108000', '5', '魏家营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇魏家营村委会', '35.916786', '104.076917', '魏家营村委会', '620123108207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108208', '620123108000', '5', '乔家营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇乔家营村委会', '35.916786', '104.076917', '乔家营村委会', '620123108208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108209', '620123108000', '5', '麻启营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇麻启营村委会', '35.916786', '104.076917', '麻启营村委会', '620123108209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108210', '620123108000', '5', '连搭村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇连搭村委会', '35.916786', '104.076917', '连搭村委会', '620123108210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108211', '620123108000', '5', '朱典营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇朱典营村委会', '35.916786', '104.076917', '朱典营村委会', '620123108211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108212', '620123108000', '5', '金家营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇金家营村委会', '35.916786', '104.076917', '金家营村委会', '620123108212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108213', '620123108000', '5', '胡家营村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇胡家营村委会', '35.916786', '104.076917', '胡家营村委会', '620123108213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108214', '620123108000', '5', '张家坪村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇张家坪村委会', '35.916786', '104.076917', '张家坪村委会', '620123108214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108215', '620123108000', '5', '魏家沟村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇魏家沟村委会', '35.916786', '104.076917', '魏家沟村委会', '620123108215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108216', '620123108000', '5', '寇家沟村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇寇家沟村委会', '35.906212', '104.085525', '寇家沟村委会', '620123108216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108217', '620123108000', '5', '肖家咀村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇肖家咀村委会', '35.916786', '104.076917', '肖家咀村委会', '620123108217', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123108218', '620123108000', '5', '石头沟村委会', '99', '1', null, '甘肃省兰州市榆中县连搭镇石头沟村委会', '35.890404', '104.083046', '石头沟村委会', '620123108218', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109000', '620123000000', '4', '新营镇', '99', '1', null, '甘肃省兰州市榆中县新营镇', '35.689365', '104.142569', '新营镇', '620123109', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109201', '620123109000', '5', '清水沟村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇清水沟村委会', '35.689365', '104.142569', '清水沟村委会', '620123109201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109202', '620123109000', '5', '黄坪村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇黄坪村委会', '35.689365', '104.142569', '黄坪村委会', '620123109202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109203', '620123109000', '5', '祁家河村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇祁家河村委会', '35.689365', '104.142569', '祁家河村委会', '620123109203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109204', '620123109000', '5', '八门寺村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇八门寺村委会', '35.722374', '104.112091', '八门寺村委会', '620123109204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109205', '620123109000', '5', '红土坡村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇红土坡村委会', '35.689365', '104.142569', '红土坡村委会', '620123109205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109206', '620123109000', '5', '刘家湾村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇刘家湾村委会', '35.729651', '104.169897', '刘家湾村委会', '620123109206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109207', '620123109000', '5', '桦岭村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇桦岭村委会', '35.689365', '104.142569', '桦岭村委会', '620123109207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109208', '620123109000', '5', '窝子湾村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇窝子湾村委会', '35.689365', '104.142569', '窝子湾村委会', '620123109208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109209', '620123109000', '5', '杨家营村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇杨家营村委会', '35.689365', '104.142569', '杨家营村委会', '620123109209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109210', '620123109000', '5', '罗景村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇罗景村委会', '35.689365', '104.142569', '罗景村委会', '620123109210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109211', '620123109000', '5', '寨子村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇寨子村委会', '35.677438', '104.223504', '寨子村委会', '620123109211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109212', '620123109000', '5', '谢家营村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇谢家营村委会', '35.689365', '104.142569', '谢家营村委会', '620123109212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123109213', '620123109000', '5', '新营村委会', '99', '1', null, '甘肃省兰州市榆中县新营镇新营村委会', '35.711954', '104.190491', '新营村委会', '620123109213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110000', '620123000000', '4', '贡井镇', '99', '1', null, '甘肃省兰州市榆中县贡井镇', '36.074382', '104.391097', '贡井镇', '620123110', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110201', '620123110000', '5', '贡马井村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇贡马井村委会', '36.079667', '104.418627', '贡马井村委会', '620123110201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110202', '620123110000', '5', '吕家岘村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇吕家岘村委会', '36.074382', '104.391097', '吕家岘村委会', '620123110202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110203', '620123110000', '5', '大坪屲村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇大坪屲村委会', '36.081821', '104.367416', '大坪屲村委会', '620123110203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110204', '620123110000', '5', '石台村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇石台村委会', '36.074382', '104.391097', '石台村委会', '620123110204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110205', '620123110000', '5', '地湾村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇地湾村委会', '36.074382', '104.391097', '地湾村委会', '620123110205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110206', '620123110000', '5', '古坝村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇古坝村委会', '36.08139', '104.454595', '古坝村委会', '620123110206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110207', '620123110000', '5', '崖头岭村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇崖头岭村委会', '36.037854', '104.375931', '崖头岭村委会', '620123110207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110208', '620123110000', '5', '佐堤村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇佐堤村委会', '36.074382', '104.391097', '佐堤村委会', '620123110208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123110209', '620123110000', '5', '套岔岘村委会', '99', '1', null, '甘肃省兰州市榆中县贡井镇套岔岘村委会', '36.16242', '104.27447', '套岔岘村委会', '620123110209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200000', '620123000000', '4', '来紫堡乡', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡', '36.051134', '104.014609', '来紫堡乡', '620123200', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200201', '620123200000', '5', '火家店村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡火家店村委会', '36.046154', '104.041329', '火家店村委会', '620123200201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200202', '620123200000', '5', '郭家庄村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡郭家庄村委会', '36.051134', '104.014609', '郭家庄村委会', '620123200202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200203', '620123200000', '5', '黄家庄村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡黄家庄村委会', '36.040725', '104.065947', '黄家庄村委会', '620123200203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200204', '620123200000', '5', '东坪村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡东坪村委会', '36.060803', '104.017882', '东坪村委会', '620123200204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200205', '620123200000', '5', '西坪村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡西坪村委会', '35.808857', '104.409212', '西坪村委会', '620123200205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200206', '620123200000', '5', '大水洞村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡大水洞村委会', '36.051134', '104.014609', '大水洞村委会', '620123200206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200207', '620123200000', '5', '桑园子村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡桑园子村委会', '36.059522', '103.976556', '桑园子村委会', '620123200207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200208', '620123200000', '5', '方家泉村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡方家泉村委会', '36.040565', '103.968344', '方家泉村委会', '620123200208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200209', '620123200000', '5', '骆驼巷村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡骆驼巷村委会', '36.051134', '104.014609', '骆驼巷村委会', '620123200209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123200210', '620123200000', '5', '冯家湾村委会', '99', '1', null, '甘肃省兰州市榆中县来紫堡乡冯家湾村委会', '36.051134', '104.014609', '冯家湾村委会', '620123200210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201000', '620123000000', '4', '三角城乡', '99', '1', null, '甘肃省兰州市榆中县三角城乡', '35.896872', '104.190143', '三角城乡', '620123201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201201', '620123201000', '5', '周前村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡周前村委会', '35.862847', '104.138491', '周前村委会', '620123201201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201202', '620123201000', '5', '三角城村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡三角城村委会', '35.887801', '104.155294', '三角城村委会', '620123201202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201203', '620123201000', '5', '丁官营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡丁官营村委会', '35.896872', '104.190143', '丁官营村委会', '620123201203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201204', '620123201000', '5', '龚家屲村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡龚家屲村委会', '35.899661', '104.132634', '龚家屲村委会', '620123201204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201205', '620123201000', '5', '化家营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡化家营村委会', '35.896872', '104.190143', '化家营村委会', '620123201205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201206', '620123201000', '5', '大兴营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡大兴营村委会', '35.896872', '104.190143', '大兴营村委会', '620123201206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201207', '620123201000', '5', '高墩营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡高墩营村委会', '35.896872', '104.190143', '高墩营村委会', '620123201207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201208', '620123201000', '5', '詹家营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡詹家营村委会', '35.910003', '104.173298', '詹家营村委会', '620123201208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201209', '620123201000', '5', '孙家营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡孙家营村委会', '35.896872', '104.190143', '孙家营村委会', '620123201209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201210', '620123201000', '5', '敬家山村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡敬家山村委会', '35.871886', '104.22099', '敬家山村委会', '620123201210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201211', '620123201000', '5', '双店子村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡双店子村委会', '35.900319', '104.202907', '双店子村委会', '620123201211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201212', '620123201000', '5', '接驾咀村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡接驾咀村委会', '35.896872', '104.190143', '接驾咀村委会', '620123201212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123201213', '620123201000', '5', '下彭家营村委会', '99', '1', null, '甘肃省兰州市榆中县三角城乡下彭家营村委会', '35.999785', '104.24429', '下彭家营村委会', '620123201213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202000', '620123000000', '4', '小康营乡', '99', '1', null, '甘肃省兰州市榆中县小康营乡', '35.794642', '104.127004', '小康营乡', '620123202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202201', '620123202000', '5', '王保营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡王保营村委会', '35.794642', '104.127004', '王保营村委会', '620123202201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202202', '620123202000', '5', '上彭家营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡上彭家营村委会', '35.794642', '104.127004', '上彭家营村委会', '620123202202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202203', '620123202000', '5', '洪亮营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡洪亮营村委会', '35.794642', '104.127004', '洪亮营村委会', '620123202203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202204', '620123202000', '5', '刘家营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡刘家营村委会', '35.861074', '104.188706', '刘家营村委会', '620123202204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202205', '620123202000', '5', '孟家庄村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡孟家庄村委会', '35.822969', '104.192893', '孟家庄村委会', '620123202205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202206', '620123202000', '5', '郭家营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡郭家营村委会', '35.794642', '104.127004', '郭家营村委会', '620123202206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202207', '620123202000', '5', '李家营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡李家营村委会', '35.794642', '104.127004', '李家营村委会', '620123202207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202208', '620123202000', '5', '翟家湾村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡翟家湾村委会', '35.794642', '104.127004', '翟家湾村委会', '620123202208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202209', '620123202000', '5', '南北关村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡南北关村委会', '35.794642', '104.127004', '南北关村委会', '620123202209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202210', '620123202000', '5', '红寺村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡红寺村委会', '35.775582', '104.190315', '红寺村委会', '620123202210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202211', '620123202000', '5', '永红村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡永红村委会', '35.794642', '104.127004', '永红村委会', '620123202211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202212', '620123202000', '5', '窑坡村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡窑坡村委会', '35.794642', '104.127004', '窑坡村委会', '620123202212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202213', '620123202000', '5', '小康营村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡小康营村委会', '35.794642', '104.127004', '小康营村委会', '620123202213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202214', '620123202000', '5', '浪街村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡浪街村委会', '35.794642', '104.127004', '浪街村委会', '620123202214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202215', '620123202000', '5', '深沟子村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡深沟子村委会', '35.812773', '104.136462', '深沟子村委会', '620123202215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202216', '620123202000', '5', '范家山村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡范家山村委会', '35.794642', '104.127004', '范家山村委会', '620123202216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123202217', '620123202000', '5', '徐家峡村委会', '99', '1', null, '甘肃省兰州市榆中县小康营乡徐家峡村委会', '35.794642', '104.127004', '徐家峡村委会', '620123202217', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204000', '620123000000', '4', '银山乡', '99', '1', null, '甘肃省兰州市榆中县银山乡', '35.815197', '103.899433', '银山乡', '620123204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204201', '620123204000', '5', '高家湾村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡高家湾村委会', '35.849428', '103.865884', '高家湾村委会', '620123204201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204202', '620123204000', '5', '孙家湾村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡孙家湾村委会', '35.834182', '103.894406', '孙家湾村委会', '620123204202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204203', '620123204000', '5', '打磨沟村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡打磨沟村委会', '35.827265', '103.910269', '打磨沟村委会', '620123204203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204204', '620123204000', '5', '茨坪村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡茨坪村委会', '35.815197', '103.899433', '茨坪村委会', '620123204204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204205', '620123204000', '5', '小水子村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡小水子村委会', '35.815197', '103.899433', '小水子村委会', '620123204205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204206', '620123204000', '5', '斜路屲村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡斜路屲村委会', '35.815197', '103.899433', '斜路屲村委会', '620123204206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204207', '620123204000', '5', '旋马滩村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡旋马滩村委会', '35.815197', '103.899433', '旋马滩村委会', '620123204207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123204208', '620123204000', '5', '大滩村委会', '99', '1', null, '甘肃省兰州市榆中县银山乡大滩村委会', '35.815197', '103.899433', '大滩村委会', '620123204208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205000', '620123000000', '4', '马坡乡', '99', '1', null, '甘肃省兰州市榆中县马坡乡', '35.785374', '104.012121', '马坡乡', '620123205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205201', '620123205000', '5', '河湾村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡河湾村委会', '35.784182', '104.016889', '河湾村委会', '620123205201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205202', '620123205000', '5', '马坡村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡马坡村委会', '35.785374', '104.012121', '马坡村委会', '620123205202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205203', '620123205000', '5', '窑沟村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡窑沟村委会', '35.800625', '104.004771', '窑沟村委会', '620123205203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205204', '620123205000', '5', '哈班岔村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡哈班岔村委会', '35.785374', '104.012121', '哈班岔村委会', '620123205204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205205', '620123205000', '5', '羊上村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡羊上村委会', '35.785374', '104.012121', '羊上村委会', '620123205205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205206', '620123205000', '5', '羊下村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡羊下村委会', '35.785374', '104.012121', '羊下村委会', '620123205206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205207', '620123205000', '5', '后沟村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡后沟村委会', '35.818296', '103.970388', '后沟村委会', '620123205207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205208', '620123205000', '5', '张家寺村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡张家寺村委会', '35.785374', '104.012121', '张家寺村委会', '620123205208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205209', '620123205000', '5', '太平沟村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡太平沟村委会', '35.785374', '104.012121', '太平沟村委会', '620123205209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205210', '620123205000', '5', '尖山村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡尖山村委会', '35.738537', '104.121916', '尖山村委会', '620123205210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205211', '620123205000', '5', '马莲滩村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡马莲滩村委会', '35.73277', '104.094571', '马莲滩村委会', '620123205211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205212', '620123205000', '5', '上庄村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡上庄村委会', '35.785374', '104.012121', '上庄村委会', '620123205212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205213', '620123205000', '5', '白家堡村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡白家堡村委会', '35.785374', '104.012121', '白家堡村委会', '620123205213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205214', '620123205000', '5', '旧庄沟村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡旧庄沟村委会', '35.785374', '104.012121', '旧庄沟村委会', '620123205214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123205215', '620123205000', '5', '阳屲村委会', '99', '1', null, '甘肃省兰州市榆中县马坡乡阳屲村委会', '35.884863', '104.127906', '阳屲村委会', '620123205215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207000', '620123000000', '4', '清水驿乡', '99', '1', null, '甘肃省兰州市榆中县清水驿乡', '35.876082', '104.242075', '清水驿乡', '620123207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207201', '620123207000', '5', '东古城村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡东古城村委会', '35.910233', '104.229148', '东古城村委会', '620123207201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207202', '620123207000', '5', '太子营村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡太子营村委会', '35.876082', '104.242075', '太子营村委会', '620123207202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207203', '620123207000', '5', '天池峡村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡天池峡村委会', '35.908933', '104.249337', '天池峡村委会', '620123207203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207204', '620123207000', '5', '岘坪村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡岘坪村委会', '35.876082', '104.242075', '岘坪村委会', '620123207204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207205', '620123207000', '5', '清水村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡清水村委会', '35.878422', '104.24363', '清水村委会', '620123207205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207206', '620123207000', '5', '赵家岔村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡赵家岔村委会', '35.855685', '104.229847', '赵家岔村委会', '620123207206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207207', '620123207000', '5', '杨河村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡杨河村委会', '35.827156', '104.222707', '杨河村委会', '620123207207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207208', '620123207000', '5', '苏家堡村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡苏家堡村委会', '35.876082', '104.242075', '苏家堡村委会', '620123207208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207209', '620123207000', '5', '建家营村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡建家营村委会', '35.812327', '104.207258', '建家营村委会', '620123207209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207210', '620123207000', '5', '方家沟村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡方家沟村委会', '35.876082', '104.242075', '方家沟村委会', '620123207210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207211', '620123207000', '5', '柳树湾村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡柳树湾村委会', '35.935744', '103.847148', '柳树湾村委会', '620123207211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207212', '620123207000', '5', '红坪村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡红坪村委会', '35.876082', '104.242075', '红坪村委会', '620123207212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207213', '620123207000', '5', '王家湾村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡王家湾村委会', '35.842263', '104.265845', '王家湾村委会', '620123207213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207214', '620123207000', '5', '稠泥河村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡稠泥河村委会', '35.851603', '104.264478', '稠泥河村委会', '620123207214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207215', '620123207000', '5', '杨家山村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡杨家山村委会', '35.876082', '104.242075', '杨家山村委会', '620123207215', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123207216', '620123207000', '5', '孟家山村委会', '99', '1', null, '甘肃省兰州市榆中县清水驿乡孟家山村委会', '35.876082', '104.242075', '孟家山村委会', '620123207216', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208000', '620123000000', '4', '龙泉乡', '99', '1', null, '甘肃省兰州市榆中县龙泉乡', '35.633067', '104.258517', '龙泉乡', '620123208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208201', '620123208000', '5', '水家坡村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡水家坡村委会', '35.633067', '104.258517', '水家坡村委会', '620123208201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208202', '620123208000', '5', '张家窑村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡张家窑村委会', '35.633067', '104.258517', '张家窑村委会', '620123208202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208203', '620123208000', '5', '大坪村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡大坪村委会', '35.632854', '104.235105', '大坪村委会', '620123208203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208204', '620123208000', '5', '李家岔村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡李家岔村委会', '35.633067', '104.258517', '李家岔村委会', '620123208204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208205', '620123208000', '5', '骡子滩村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡骡子滩村委会', '35.633067', '104.258517', '骡子滩村委会', '620123208205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208206', '620123208000', '5', '花寨子村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡花寨子村委会', '35.981627', '103.857973', '花寨子村委会', '620123208206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208207', '620123208000', '5', '武家庄村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡武家庄村委会', '35.633067', '104.258517', '武家庄村委会', '620123208207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208208', '620123208000', '5', '银川村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡银川村委会', '35.626051', '104.270778', '银川村委会', '620123208208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208209', '620123208000', '5', '水泉湾村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡水泉湾村委会', '35.633067', '104.258517', '水泉湾村委会', '620123208209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208210', '620123208000', '5', '庙咀村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡庙咀村委会', '35.648065', '104.280627', '庙咀村委会', '620123208210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208211', '620123208000', '5', '杨家咀村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡杨家咀村委会', '35.633067', '104.258517', '杨家咀村委会', '620123208211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123208212', '620123208000', '5', '洞口村委会', '99', '1', null, '甘肃省兰州市榆中县龙泉乡洞口村委会', '35.666758', '104.328851', '洞口村委会', '620123208212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209000', '620123000000', '4', '韦营乡', '99', '1', null, '甘肃省兰州市榆中县韦营乡', '35.891113', '104.41352', '韦营乡', '620123209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209201', '620123209000', '5', '李家坪村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡李家坪村委会', '35.891113', '104.41352', '李家坪村委会', '620123209201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209202', '620123209000', '5', '韦家营村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡韦家营村委会', '35.891113', '104.41352', '韦家营村委会', '620123209202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209203', '620123209000', '5', '郭家沟村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡郭家沟村委会', '35.891113', '104.41352', '郭家沟村委会', '620123209203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209204', '620123209000', '5', '武家窑村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡武家窑村委会', '35.891113', '104.41352', '武家窑村委会', '620123209204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209205', '620123209000', '5', '黄家岔村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡黄家岔村委会', '35.891113', '104.41352', '黄家岔村委会', '620123209205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209206', '620123209000', '5', '全家岔村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡全家岔村委会', '35.901161', '104.366776', '全家岔村委会', '620123209206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123209207', '620123209000', '5', '孙家岔村委会', '99', '1', null, '甘肃省兰州市榆中县韦营乡孙家岔村委会', '35.870038', '104.405358', '孙家岔村委会', '620123209207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210000', '620123000000', '4', '中连川乡', '99', '1', null, '甘肃省兰州市榆中县中连川乡', '36.029491', '104.422827', '中连川乡', '620123210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210201', '620123210000', '5', '高窑沟村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡高窑沟村委会', '36.030773', '104.410412', '高窑沟村委会', '620123210201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210202', '620123210000', '5', '大湾村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡大湾村委会', '35.982788', '104.416058', '大湾村委会', '620123210202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210203', '620123210000', '5', '撒拉沟村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡撒拉沟村委会', '36.00889', '104.445462', '撒拉沟村委会', '620123210203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210204', '620123210000', '5', '中连川村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡中连川村委会', '36.02825', '104.424997', '中连川村委会', '620123210204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210205', '620123210000', '5', '野韭川村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡野韭川村委会', '36.02825', '104.449455', '野韭川村委会', '620123210205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210206', '620123210000', '5', '鞑靼窑村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡鞑靼窑村委会', '36.029491', '104.422827', '鞑靼窑村委会', '620123210206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210207', '620123210000', '5', '中庄窠村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡中庄窠村委会', '36.13247', '104.390729', '中庄窠村委会', '620123210207', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210208', '620123210000', '5', '刘家岘村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡刘家岘村委会', '36.029491', '104.422827', '刘家岘村委会', '620123210208', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210209', '620123210000', '5', '陡泉湾村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡陡泉湾村委会', '36.127996', '104.478537', '陡泉湾村委会', '620123210209', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210210', '620123210000', '5', '垲坪村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡垲坪村委会', '36.029491', '104.422827', '垲坪村委会', '620123210210', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210211', '620123210000', '5', '黄蒿湾村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡黄蒿湾村委会', '36.029491', '104.422827', '黄蒿湾村委会', '620123210211', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123210212', '620123210000', '5', '高家渠村委会', '99', '1', null, '甘肃省兰州市榆中县中连川乡高家渠村委会', '36.029491', '104.422827', '高家渠村委会', '620123210212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212000', '620123000000', '4', '园子岔乡', '99', '1', null, '甘肃省兰州市榆中县园子岔乡', '36.260814', '104.444075', '园子岔乡', '620123212', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212201', '620123212000', '5', '万羊村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡万羊村委会', '36.260814', '104.444075', '万羊村委会', '620123212201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212202', '620123212000', '5', '青碾村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡青碾村委会', '36.260814', '104.444075', '青碾村委会', '620123212202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212203', '620123212000', '5', '小岔村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡小岔村委会', '36.262382', '104.445816', '小岔村委会', '620123212203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212204', '620123212000', '5', '柏木村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡柏木村委会', '36.260814', '104.444075', '柏木村委会', '620123212204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212205', '620123212000', '5', '大岘村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡大岘村委会', '36.328854', '104.395122', '大岘村委会', '620123212205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123212206', '620123212000', '5', '金营村委会', '99', '1', null, '甘肃省兰州市榆中县园子岔乡金营村委会', '36.363825', '104.486055', '金营村委会', '620123212206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213000', '620123000000', '4', '上花岔乡', '99', '1', null, '甘肃省兰州市榆中县上花岔乡', '36.229136', '104.388565', '上花岔乡', '620123213', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213201', '620123213000', '5', '百禄村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡百禄村委会', '36.176556', '104.387678', '百禄村委会', '620123213201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213202', '620123213000', '5', '上花岔村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡上花岔村委会', '36.229136', '104.388565', '上花岔村委会', '620123213202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213203', '620123213000', '5', '平湾村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡平湾村委会', '36.266393', '104.377291', '平湾村委会', '620123213203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213204', '620123213000', '5', '王湾村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡王湾村委会', '36.289315', '104.368442', '王湾村委会', '620123213204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213205', '620123213000', '5', '大岔村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡大岔村委会', '36.321244', '104.341028', '大岔村委会', '620123213205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123213206', '620123213000', '5', '黑虎子村委会', '99', '1', null, '甘肃省兰州市榆中县上花岔乡黑虎子村委会', '36.229136', '104.388565', '黑虎子村委会', '620123213206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214000', '620123000000', '4', '哈岘乡', '99', '1', null, '甘肃省兰州市榆中县哈岘乡', '36.170699', '104.335915', '哈岘乡', '620123214', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214201', '620123214000', '5', '哈岘村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡哈岘村委会', '36.147975', '104.34851', '哈岘村委会', '620123214201', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214202', '620123214000', '5', '仁和村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡仁和村委会', '36.170583', '104.335786', '仁和村委会', '620123214202', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214203', '620123214000', '5', '柳树村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡柳树村委会', '36.214085', '104.329456', '柳树村委会', '620123214203', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214204', '620123214000', '5', '杨岘村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡杨岘村委会', '36.258076', '104.309105', '杨岘村委会', '620123214204', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214205', '620123214000', '5', '纪尔村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡纪尔村委会', '36.170699', '104.335915', '纪尔村委会', '620123214205', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214206', '620123214000', '5', '张湾村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡张湾村委会', '36.2299', '104.256804', '张湾村委会', '620123214206', '2019-05-16 18:00:00');
INSERT INTO `sys_districts` VALUES ('620123214207', '620123214000', '5', '宣家岔村委会', '99', '1', null, '甘肃省兰州市榆中县哈岘乡宣家岔村委会', '36.170699', '104.335915', '宣家岔村委会', '620123214207', '2019-05-16 18:00:00');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `LOG_ID` varchar(32) NOT NULL COMMENT '系统日志ID',
  `OPERATOR_TIME` varchar(32) NOT NULL COMMENT '操作时间',
  `LOG_MODEL` varchar(128) NOT NULL COMMENT '日志记录模块',
  `USER_ACCT_ID` varchar(32) NOT NULL COMMENT '用户账号ID',
  `LOG_IP` varchar(32) DEFAULT NULL COMMENT '日志IP地址',
  `LOG_TYPE` varchar(6) DEFAULT NULL COMMENT '日志类型,1:登陆日志（1-1：登录成功；1-2：登陆失败），2：操作日志(2-1:新增；2-2：编辑；2-3：删除)，3：错误日志（3-1:新增；3-2：编辑；3-3：删除）',
  `LOG_CONTENT` text COMMENT '日志内容',
  `LOGIN_ACCOUNT` varchar(32) DEFAULT NULL COMMENT '登录账号',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_acct_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acct_rel`;
CREATE TABLE `sys_role_acct_rel` (
  `ROLE_ACCT_ID` varchar(32) NOT NULL COMMENT '角色账号关系ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `USER_ACCT_ID` varchar(32) NOT NULL COMMENT '用户账号ID',
  PRIMARY KEY (`ROLE_ACCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色账号关系表';

-- ----------------------------
-- Records of sys_role_acct_rel
-- ----------------------------

-- ----------------------------
-- Table structure for wechat_attention
-- ----------------------------
DROP TABLE IF EXISTS `wechat_attention`;
CREATE TABLE `wechat_attention` (
  `PK_ID` varchar(32) NOT NULL,
  `AT_NUMBER` int(12) DEFAULT NULL COMMENT '微信关注人数',
  `APK_NUMBER` int(12) DEFAULT '0' COMMENT 'app下载次数',
  `UPDATE_TIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_attention
-- ----------------------------
INSERT INTO `wechat_attention` VALUES ('1', '1', '2', null);
