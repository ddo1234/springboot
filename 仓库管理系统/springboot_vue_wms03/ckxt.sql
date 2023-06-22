/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.30-log : Database - ckxt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ckxt` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ckxt`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '货名',
  `storage` varchar(11) NOT NULL COMMENT '仓库',
  `goodsType` varchar(11) NOT NULL COMMENT '分类',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`storage`,`goodsType`,`count`,`remark`) values (1,'西瓜','生鲜速递','水果',141,NULL),(2,'菠萝','1号仓库','水果',96,NULL),(3,'香蕉','1号仓库','水果',15,NULL),(4,'哈密瓜','生鲜速递','水果',21,NULL),(24,'菠萝','1号仓库','水果',8999,NULL),(25,'西瓜','1号仓库','水果',1009,NULL),(27,'香蕉','生鲜速递','水果',96,NULL);

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '分类名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `goodstype` */

insert  into `goodstype`(`id`,`name`,`remark`) values (1,'水果',NULL);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `menuCode` varchar(8) DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) DEFAULT NULL,
  `menuIcon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`menuCode`,`menuName`,`menuLevel`,`menuParentCode`,`menuClick`,`menuRight`,`menuComponent`,`menuIcon`) values (1,'001','管理员管理','1',NULL,'Admin','0','admin/AdminManage.vue','iconfont icon-guanliyuan_guanliyuanliebiao'),(2,'002','用户管理','1',NULL,'User','0,1','user/UserManage.vue','iconfont icon-yonghuguanli1'),(3,'003','仓库管理','1',NULL,'Storage','0,1','storage/StorageManage','iconfont icon-cangku_kucun_o'),(4,'004','物品分类管理','1',NULL,'Goodstype','0,1','goodstype/GoodstypeManage','iconfont icon-huowudui'),(5,'005','物品管理 ','1',NULL,'Goods','0,1,2','goods/GoodsManage','iconfont icon-icon-goods'),(6,'006','记录管理','1',NULL,'Record','0,1,2','record/RecordManage','iconfont icon-jiluguanli');

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) NOT NULL COMMENT '货品id',
  `user_id` int(11) DEFAULT NULL COMMENT '取货人/补货人id',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `record` */

insert  into `record`(`id`,`goods_id`,`user_id`,`admin_id`,`count`,`create_time`,`remark`) values (1,1,6,1,11,'2022-12-12 15:36:44','入库'),(2,1,6,1,-50,'2022-12-12 15:38:33','出库'),(3,24,2,1,-1000,'2022-12-12 15:39:24','出库'),(4,25,8,1,10,'2022-12-12 16:06:17','入库');

/*Table structure for table `storage` */

DROP TABLE IF EXISTS `storage`;

CREATE TABLE `storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `storage` */

insert  into `storage`(`id`,`name`,`remark`) values (1,'广东分仓','广州'),(2,'潮汕分仓','普宁'),(3,'天津分仓','天津'),(4,'深圳京东分仓厂','龙岗'),(5,'广东分仓','清远'),(6,'淘宝仓库','002');

/*Table structure for table `storage2` */

DROP TABLE IF EXISTS `storage2`;

CREATE TABLE `storage2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '仓库名',
  `person` varchar(1000) DEFAULT NULL COMMENT '负责人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `sex` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `storage2` */

insert  into `storage2`(`id`,`name`,`person`,`createtime`,`remark`,`sex`) values (11,'生鲜速递','leo','2025-10-07 16:00:00','福建','1'),(12,'生鲜速递','小明','2022-11-21 16:00:00','福建','1'),(14,'生鲜速递','小明','2022-11-21 16:00:00','福建','0'),(16,'生鲜速递','小明','2022-11-21 16:00:00','福建','1'),(17,'生鲜速递','小明','2022-11-21 16:00:00','福建','1'),(18,'生鲜仓库','leo','2022-11-22 16:00:00','福建','1'),(19,'热带水果','大漠','2022-10-31 16:00:00','海南','1'),(20,'热带水果','大漠','2022-10-31 16:00:00','海南','1'),(21,'澳大利亚美人鱼','aion','2022-11-21 16:00:00','新鲜上岸','1'),(22,'北京地道美食','老白','2022-11-05 16:00:00','北京','1'),(23,'aw','wd','2022-11-28 16:00:00','wa','1'),(24,'新仓库','aaa','2022-11-21 16:00:00','test','1'),(25,'test','leo','2022-11-21 16:00:00','a','1'),(26,'淘宝仓库','小东西','2022-11-09 15:32:25','北京','0'),(27,'莆田仓库','李四啊','2023-01-01 15:33:05','福建','1'),(28,'淘淘仓库','kk小姐','2010-10-11 16:00:00','东北','1'),(34,'生鲜速递','leo我','2025-10-07 16:00:00','福建','1'),(35,'京东仓库','leopp','2022-12-19 16:00:00','广州','1'),(36,'水果速递','朱老三','2022-12-25 16:00:00','1023.125','0'),(37,'啊啊我','啊啊啊','2022-12-12 16:00:00','玩4534','1'),(38,'生鲜速递','leo','2025-10-07 16:00:00','福建','0'),(39,'生鲜速递','leo','2025-10-07 16:00:00','福建','0'),(40,'生鲜速递','leo','2025-10-07 16:00:00','福建','0'),(41,'生鲜速递','leo','2025-10-07 16:00:00','福建','0'),(42,'生鲜速递','leo','2025-10-07 16:00:00','福建222','0'),(43,'生鲜速递','leo','2025-10-07 16:00:00','福建','0'),(44,'生鲜速递','leo啊','2025-10-07 16:00:00','福建','1'),(45,'生鲜速递','leo','2025-10-07 16:00:00','福建规划的任何','0'),(46,'生鲜速递4','leo','2025-10-04 16:00:00','福建','1'),(47,'生鲜速递','leo啊','2025-10-07 16:00:00','福建','1'),(48,'生鲜速递','leo','2025-10-04 16:00:00','福建','0'),(51,'水果生鲜','kk','2025-10-04 16:00:00','北京','1');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(100) NOT NULL COMMENT '名字',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '性别 0男性，1女性',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `role_id` int(11) DEFAULT NULL COMMENT '0超级管理员，1管理员，2普通账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`name`,`age`,`sex`,`phone`,`role_id`) values (1,'admin','123456789','admin',20,0,'11100000000',0),(2,'pe','123456','pe',21,0,'1100000011',2),(3,'admin2','123456','admin2',22,0,'18924003374',1),(4,'root2','root','root4',11,0,'18029201239',1),(5,'amt','1900','amtoel',23,1,'19000000000',2),(6,'pe','123456','pe11',22,1,'18029201239',2),(7,'dgx','000000','op',21,1,'18924003374',1),(8,'oop','123','p11',18,1,'18000000000',2),(9,'ming','123456','小明',11,0,'18200000000',2),(10,'mo','123456','大漠',18,0,'19012340000',2),(11,'bai','123456','老白',41,1,'13400030090',2),(12,'taobao','taobao','小东西',20,0,'17012345678',2),(13,'小白','123456','小白22',22,0,'18012303211',1),(14,'xiaomo','0012','小莫',52,0,'13012001191',1),(15,'8080','888888','ggl11',88,1,'18088218080',2),(16,'七七','777777','77',77,0,'17701773624',2),(17,'++0','ottsx','007',74,0,'15455210738',2),(18,'0851','357dhxf','jlk',10,0,'15920135578',2),(19,'小猪','zztm','zzr',3,0,'15044135971',2),(20,'0','123','000',88,1,'16033333311',2),(21,'san','333333','朱老三',99,1,'14512347890',2),(23,'伞兵','111111','san',29,1,'19012813367',2),(24,'OP','yuanP','原P',2,1,'13482116510',2),(26,'eok','deodel','ee',84,1,'14205741982',2),(27,'b','GMB','大B老师',28,1,'16987335400',2),(29,'tt','000123','TT',55,0,'13011901210',2),(30,'kRYST4L','418154','k神',31,1,'16799103574',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
