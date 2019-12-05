/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.17 : Database - dressdatabase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dressdatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dressdatabase`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dressid` int(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `booknum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2E7B20B68FF963` (`userid`),
  CONSTRAINT `FK2E7B20B68FF963` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`dressid`,`userid`,`booknum`) values 
(6,1,14,1),
(12,2,14,1);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `orderid` int(20) DEFAULT NULL COMMENT '商品id',
  `comments` varchar(255) DEFAULT NULL COMMENT '评语',
  `dressid` int(20) NOT NULL,
  `score` int(1) NOT NULL COMMENT '评分',
  `userid` int(20) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `imgurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_evaluation_orders` (`orderid`),
  KEY `FK_evaluation_user` (`userid`),
  CONSTRAINT `FK_evaluation_orders` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_evaluation_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`orderid`,`comments`,`dressid`,`score`,`userid`,`time`,`imgurl`) values 
(1,NULL,'还好了',1,80,14,'2018-11-26 17:13:09',NULL),
(2,NULL,'不错,下次再来买',1,90,14,'2018-11-26 17:13:19',NULL),
(3,NULL,'一般般啦',1,70,14,'2018-11-26 17:13:39',NULL);

/*Table structure for table `dress` */

DROP TABLE IF EXISTS `dress`;

CREATE TABLE `dress` (
  `dressid` int(11) NOT NULL AUTO_INCREMENT,
  `dressname` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'img/female/t23.jpg',
  `description` varchar(100) DEFAULT NULL,
  `sales` int(11) DEFAULT '0',
  KEY `dressid` (`dressid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `dress` */

insert  into `dress`(`dressid`,`dressname`,`category`,`price`,`quantity`,`imgurl`,`description`,`sales`) values 
(1,'女裙1','女装',123,97,'img/female/t38.jpg','456',3),
(2,'女裙2','女装',300,97,'img/female/t21.jpg','123',3),
(3,'女裙3','女装',280,98,'img/female/t22.jpg','123',2),
(15,'女裙15','女装',288,100,'img/female/t23.jpg','123',0),
(16,'西装','男装',600,100,'img/female/t24.jpg','123',0),
(17,'公主裙','童装',200,100,'img/female/t25.jpg','123',0),
(4,'女裙4','女装',300,100,'img/female/t26.jpg','123',0),
(5,'女裙5','女装',400,97,'img/female/t27.jpg','123',3),
(6,'女裙6','女装',210,99,'img/female/t28.jpg','123',1),
(8,'女裙8','女装',235,99,'img/female/t30.jpg','123',1),
(9,'女裙9','女装',235,100,'img/female/t31.jpg','123',0),
(10,'女裙10','女装',235,100,'img/female/t32.jpg','123',0),
(11,'女裙11','女装',235,100,'img/female/t33.jpg','123',0),
(12,'女裙12','女装',235,100,'img/female/t34.jpg','123',0),
(13,'女裙13','女装',235,100,'img/female/t35.jpg','123',0),
(14,'女裙14','女装',580,100,'img/female/t36.jpg','123',0),
(15,'女15','女',123,123,'img/female/t36.jpg','123',0),
(17,'123',NULL,123,NULL,NULL,'123',NULL),
(17,'123',NULL,123,NULL,NULL,'123',NULL),
(17,'123',NULL,123,NULL,NULL,'123',NULL),
(17,'123',NULL,123,NULL,NULL,'123',NULL),
(21,'123',NULL,123,123,NULL,'123',NULL),
(21,'123',NULL,123,123,NULL,'123',NULL),
(22,'123','男装',123,123,NULL,'123',NULL),
(23,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(24,'123','男装',123,122,'img/female/t23.jpg','123',1);

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `orderid` int(100) NOT NULL COMMENT '订单id',
  `dressid` varchar(100) NOT NULL COMMENT '产品表',
  `buynum` int(11) DEFAULT NULL COMMENT '订单购买数量',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ostate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE8B2AB6127C924DD` (`dressid`),
  KEY `FKE8B2AB6174895891` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='订单\r\n';

/*Data for the table `orderitem` */

insert  into `orderitem`(`orderid`,`dressid`,`buynum`,`id`,`ostate`) values 
(98,'1',2,1,-1),
(99,'2',3,2,-2),
(100,'3',1,3,-3),
(101,'9',13,4,1),
(102,'4',2,6,1),
(103,'5',1,7,2),
(104,'6',6,8,3),
(105,'7',7,9,1),
(106,'8',2,10,1),
(107,'9',3,11,2),
(108,'10',4,12,1),
(109,'11',5,13,3),
(110,'12',6,14,-1),
(111,'13',7,15,2),
(112,'14',8,16,3),
(113,'15',9,17,1),
(114,'16',10,18,2),
(115,'17',2,19,2);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `paystate` int(11) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3DF62E5B68FF963` (`userid`),
  CONSTRAINT `FKC3DF62E5B68FF963` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='订单\r\n';

/*Data for the table `orders` */

insert  into `orders`(`id`,`money`,`receiverAddress`,`receiverName`,`receiverPhone`,`paystate`,`ordertime`,`userid`) values 
(98,73,'广东省广州市天河区微微威武','user3','135465465',1,'2018-06-05 12:09:27',14),
(99,182,'广东省广州市天河区中山大道','user3','135465465',1,'2018-06-07 14:33:11',14),
(100,50,'广东省广州市天河区中山大道','user3','135465465',1,'2018-06-07 14:41:09',14),
(101,279,'广东省广州市天河区中山大道','user3','135465465',0,'2018-06-30 16:13:33',14),
(102,55,'广东省广州市天河区中山大道','user3','135465465',0,'2018-06-30 16:21:29',14),
(103,73,'广东省广州市天河区中山大道','user3','135465465',0,'2018-07-04 12:16:42',14),
(104,869,'广东省广州市天河区中山大道','user3','135465465',1,'2018-07-04 12:28:33',14),
(105,1093,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-26 10:49:21',14),
(106,1203,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 14:25:39',14),
(107,590,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 14:40:40',14),
(108,334,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 15:20:06',14),
(109,279,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 15:45:41',14),
(110,334,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 16:00:55',14),
(111,334,'广东省广州市天河区中山大道','user3','135465465',1,'2018-09-27 16:18:37',14),
(112,78,'广东省广州市天河区中山大道','user3','135465465',1,'2018-10-26 17:18:18',14),
(113,500,'广东省广州市天河区中山大道','admin','137501142566',1,'2018-11-27 10:44:19',9),
(114,1245,'广东省广州市天河区中山大道','user3','12312312312',1,'2019-12-04 12:05:19',14),
(115,700,'安徽省合肥市瑶海区','123','2222222224',1,'2019-12-04 23:07:09',9),
(116,423,'安徽省合肥市瑶海区','123','2222222224',1,'2019-12-05 15:20:25',9);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'ordinary',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shippingAddress` varchar(1000) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `headimg` varchar(255) NOT NULL DEFAULT 'img/userheadimg/0.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`gender`,`email`,`telephone`,`introduce`,`role`,`registTime`,`shippingAddress`,`name`,`headimg`) values 
(9,'123','123','f','16380756419@qq.com','2222222224','-1','admin','2019-12-04 13:58:09','安徽省合肥市瑶海区','李白','img/userheadimg/a254b3b7-b45e-46da-9fe1-778c8169506b.jpg'),
(12,'abcd','123456','m','1638075649@qq.com','137501140000','-','admin','2018-06-05 15:33:13','广东省广州市天河区中山大道','李白','img/userheadimg/3ca11c64-7209-400a-ae32-f62c64d8f4b7.jpg'),
(14,'user3','123','m','1638075649@qq.com','12312312312','-','vip','2019-12-05 15:42:53','辽宁省沈阳市和平区','李白','img/userheadimg/2b3d4f52-9e00-404a-809a-522b3b2faad4.jpg'),
(23,'adsf','123123','m','1349281263@qq.com','18049876896','-','ordinary','2019-12-04 00:12:41','上海市浦东新区上海','jingwei','img/userheadimg/0.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
