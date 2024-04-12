/*
 Navicat Premium Data Transfer

 Source Server         : miku
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : lt

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 12/04/2024 01:14:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for funs
-- ----------------------------
DROP TABLE IF EXISTS `funs`;
CREATE TABLE `funs`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int NULL DEFAULT NULL COMMENT '用户的id',
  `fid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '关注的人的id',
  `follow` int NULL DEFAULT NULL COMMENT '0为关注 1互相关注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of funs
-- ----------------------------
INSERT INTO `funs` VALUES (1, 1, '1', 1);
INSERT INTO `funs` VALUES (2, 2, '1', 1);
INSERT INTO `funs` VALUES (3, 3, '1', 1);
INSERT INTO `funs` VALUES (4, 2, '2', 1);
INSERT INTO `funs` VALUES (8, 2, '3', 1);
INSERT INTO `funs` VALUES (10, 1, '2', 0);
INSERT INTO `funs` VALUES (11, 3, '2', 1);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `pid` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '帖子内容',
  `tag` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帖子标签',
  `visit` int NULL DEFAULT NULL COMMENT '浏览数',
  `likes` int NULL DEFAULT NULL COMMENT '点赞数',
  `replycount` int NULL DEFAULT NULL COMMENT '评论数',
  `ctime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `uid` int NULL DEFAULT NULL COMMENT '发布用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, '111', '<p style=\"text-align: center;\">八月的尾声悄然而至，但燃烧你的梦为您带来的暑期惊喜仍将持续。即日起至8月27日，燃烧你的梦旅游度假区将推出一系列主题鲜明、形式多样、内容丰富的活动，为广大游客倾力打造欢乐消暑之旅。<br><img src=\"http://localhost:8085/img/058ace89-5156-4274-9209-2d96f8736704.png\" alt=\"\" data-href=\"http://localhost:8085/img/058ace89-5156-4274-9209-2d96f8736704.png\" style=\"\"><br>啤酒电音节，释放假日狂欢<br>即日起至8月27日，期待前往郑州方特欢乐世界体验的游客，可通过燃烧你的梦官方渠道，提前一天抢购“199元双人特惠夜场票”，为彼此定制仲夏狂欢之夜。<br><img src=\"http://localhost:8085/img/db735d21-c123-4ce1-9a78-111d7b2d05d9.png\" alt=\"\" data-href=\"http://localhost:8085/img/db735d21-c123-4ce1-9a78-111d7b2d05d9.png\" style=\"\"><img src=\"file:///E:\\QQ\\Tencent Files\\1083344129\\nt_qq\\nt_data\\Pic\\2024-01\\Ori\\f82d022683cc1bba6c6c10dc897f2f84.png\" alt=\"\" data-href=\"\" style=\"\"><br>与此同时，燃烧你的梦联合“青岛白啤”及“红旗HS3”聚焦“沉浸式体验、年轻化消费”的市场需求，精心打造众多深受年轻游客喜爱的网红打卡点，并推出“啤酒电音派对”“欢乐奇遇大巡游”“赛博朋克派对”等一系列精彩演出，将为广大游客带来多元有趣的潮玩体验。<br>熊熊嬉水节，畅享亲子时光<br>暑假期间，燃烧你的梦精彩开启“熊熊嬉水节”，不仅推出“熊熊水花派对”“熊熊水上运动会”“熊熊泼水大战”等嬉水互动项目，还轮番献上“夏日缤纷大巡游”“大嘴的奇思妙想”“红鼻子夏日欢乐颂”等夏日限定演出，吸引了众多亲子家庭前来体验。<br><img src=\"http://localhost:8085/img/fb145178-7908-410f-a77c-5c55ed1e6bb7.png\" alt=\"\" data-href=\"http://localhost:8085/img/fb145178-7908-410f-a77c-5c55ed1e6bb7.png\" style=\"\"><img src=\"file:///E:\\QQ\\Tencent Files\\1083344129\\nt_qq\\nt_data\\Pic\\2024-01\\Ori\\da0d21dd8b0f4275df892d462e86a690.png\" alt=\"\" data-href=\"\" style=\"\"><br>此外，燃烧你的梦逢周六日延长营业时间至20:00，方便广大游客日夜畅玩《华夏五千年》《决战金山寺》《千古蝶恋》等近30个大型室内外主题游乐项目，畅享从早到晚的欢乐时光。<br>音浪狂欢节，解锁玩水新花样<br>音浪狂欢，清凉消暑。即日起，燃烧你的梦特针对12:00前入园的游客推出“199元双人票”。游客们凭此票，可以畅玩各种经典水上项目，体验“狂欢水派对”“FUN肆一夏游走派对”等一系列水上互动演出，解锁玩水新花样。<br><img src=\"http://localhost:8085/img/d5dfd4cb-6350-4d8b-bcd7-dfc6e293f730.png\" alt=\"\" data-href=\"http://localhost:8085/img/d5dfd4cb-6350-4d8b-bcd7-dfc6e293f730.png\" style=\"\"><img src=\"file:///E:\\QQ\\Tencent Files\\1083344129\\nt_qq\\nt_data\\Pic\\2024-01\\Ori\\156253456a4eab8af1fcafd003dc2dd5.png\" alt=\"\" data-href=\"\" style=\"\"><br>亲爱的游客朋友们，抓住暑期的小尾巴，约上亲朋好友，来燃烧你的梦收获一段难忘的欢乐之旅吧。</p>', '1', 1, 1, 1, '2024-01-25 16:13:53', 1, 'miku', 'http://localhost:8085/lt/img/1.jpg');
INSERT INTO `post` VALUES (2, '22', '22', '2', 2, 2, 2, '2024-01-25 17:16:04', 2, 'rin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (3, '3', '3', '3', 32, 3, 3, NULL, 2, 'rin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (4, '4', '4', '4', 4, 4, 4, '2024-01-25 17:16:20', 2, 'rin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (5, '5', '5', '5', 5, 5, 5, NULL, 2, 'rin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (6, '6', '6', '6', 6, 6, 6, NULL, 2, 'rin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (7, 'asdzxc', NULL, NULL, 0, 0, NULL, NULL, 2, 'rinrin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (10, 't天皇来咯zxc', NULL, '日常校园', 0, 0, 0, '2024-02-03 15:56:37', 2, 'rinrin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (11, 'zxc', NULL, '日常校园', 0, 0, 0, '2024-02-03 15:57:23', 2, 'rinrin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (13, 'ww', 'ww', 'w', 0, 0, 0, NULL, 2, 'rinrin', 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `post` VALUES (14, '22x2qweqweqweqwe', '<p>12zxczxc</p>', '讨论,校园', 0, 0, 0, '2024-02-13 22:26:16', 1, '', '1');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `reply` int NULL DEFAULT NULL COMMENT '0表示评论 1 表示回复',
  `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `likes` int NULL DEFAULT NULL COMMENT '点赞数',
  `username` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论用户',
  `pid` int NULL DEFAULT NULL COMMENT '博客id',
  `rid` int NULL DEFAULT NULL COMMENT '回复的是哪个评论的id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 419 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (414, '5666', 0, '2024-02-01 22:34:29', 0, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `review` VALUES (415, 'zxcasd', 0, '2024-02-01 22:36:24', 2, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `review` VALUES (416, 'zcxzc', 0, '2024-02-01 22:36:28', 2, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `review` VALUES (417, 'zxczxcasd', 0, '2024-02-01 22:39:10', 1, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `review` VALUES (418, 'sb', 0, '2024-02-01 22:46:44', 2, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');
INSERT INTO `review` VALUES (419, 'zxcasdasd', 1, '2024-02-01 22:34:24', 0, 'rinrin', 1, NULL, 'http://localhost:8085/lt/img/4.jpg');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tid` int NOT NULL COMMENT '标签id',
  `tag` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '校园');
INSERT INTO `tag` VALUES (2, '日常');
INSERT INTO `tag` VALUES (3, '讨论');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `type` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户类型',
  `xp` int NULL DEFAULT NULL COMMENT '用户经验',
  `email` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `state` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用户签命',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'miku', 'miku', 'miku', 'http://localhost:8085/lt/img/1.jpg', '管理员', 3, '1083344129@qq.com', '我是初音未来');
INSERT INTO `user` VALUES (2, 'rinrin', 'rinrin', 'rinrin', 'http://localhost:8085/lt/img/4.jpg', '用户', 36, '1083344129@qq.com', '我是镜音铃');
INSERT INTO `user` VALUES (3, 'test', 'test', 'test', NULL, '用户', 0, NULL, '666');

SET FOREIGN_KEY_CHECKS = 1;
