-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.40-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pms
--

CREATE DATABASE IF NOT EXISTS pms;
USE pms;

--
-- Definition of table `assign_task`
--

DROP TABLE IF EXISTS `assign_task`;
CREATE TABLE `assign_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assign_date` datetime DEFAULT NULL,
  `developer_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo2s2k6x6otwdg1tm0m2y1tsld` (`developer_id`),
  KEY `FKe2if1q7w8jy2fbis9ik0rap6c` (`task_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assign_task`
--

/*!40000 ALTER TABLE `assign_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `assign_task` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_registration_date` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `no_of_staffs` int(11) NOT NULL,
  `registration_no` bigint(20) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bma9lv19ba3yjwf12a34xord3` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`,`address`,`company_name`,`company_registration_date`,`email`,`file_extension`,`file_name`,`file_path`,`file_size`,`mobile`,`no_of_staffs`,`registration_no`,`website`) VALUES 
 (1,NULL,'Rojina IT Limited',NULL,'juitrishadewan@gmail.com','image/png','new-fox.png','/images/new-fox.png',182887,'01719444064',0,0,NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  `pm_id` bigint(20) NOT NULL,
  `project_status_id` bigint(20) NOT NULL,
  `team_lead_id` bigint(20) NOT NULL,
  `analysis_report` varchar(255) DEFAULT NULL,
  `requirements` varchar(255) DEFAULT NULL,
  `use_case_as_jpg` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1xyvksttvuuyps5pcpxt8hyqi` (`client_id`),
  KEY `FK76fngi71pfr8phbobe5pq0swd` (`company_id`),
  KEY `FKl5odvw4ewvwr7spn4i2oyktfo` (`pm_id`),
  KEY `FKmshv9pg3iw2t70144rjjh378j` (`project_status_id`),
  KEY `FKsdt6insxij8ckcetcfayqnc90` (`team_lead_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


--
-- Definition of table `project_consultant`
--

DROP TABLE IF EXISTS `project_consultant`;
CREATE TABLE `project_consultant` (
  `project_id` bigint(20) NOT NULL,
  `consultant_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`,`consultant_id`),
  KEY `FKm5foisbr9utjoa6wh5me1dehb` (`consultant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_consultant`
--

/*!40000 ALTER TABLE `project_consultant` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_consultant` ENABLE KEYS */;


--
-- Definition of table `project_developer`
--

DROP TABLE IF EXISTS `project_developer`;
CREATE TABLE `project_developer` (
  `project_id` bigint(20) NOT NULL,
  `developer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`,`developer_id`),
  KEY `FKrje3q0xabh9jogs2v96ifqlfb` (`developer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_developer`
--

/*!40000 ALTER TABLE `project_developer` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_developer` ENABLE KEYS */;


--
-- Definition of table `project_engineering`
--

DROP TABLE IF EXISTS `project_engineering`;
CREATE TABLE `project_engineering` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `developer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK365ucd0ak487p2388sc7p09vc` (`project_id`),
  KEY `FKob4lr21eyd7xk6q41e1j5nhw8` (`developer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_engineering`
--

/*!40000 ALTER TABLE `project_engineering` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_engineering` ENABLE KEYS */;


--
-- Definition of table `project_module`
--

DROP TABLE IF EXISTS `project_module`;
CREATE TABLE `project_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKidk278tx3lpdjidydv03g39t5` (`company_id`),
  KEY `FKfqwcf5m5sipuainmynnni3a98` (`project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_module`
--

/*!40000 ALTER TABLE `project_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_module` ENABLE KEYS */;


--
-- Definition of table `project_status`
--

DROP TABLE IF EXISTS `project_status`;
CREATE TABLE `project_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_status`
--

/*!40000 ALTER TABLE `project_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_status` ENABLE KEYS */;


--
-- Definition of table `project_status_details`
--

DROP TABLE IF EXISTS `project_status_details`;
CREATE TABLE `project_status_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status_change_date` datetime DEFAULT NULL,
  `project_status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlryj6iotqxpl466b907vp0hg3` (`project_status_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_status_details`
--

/*!40000 ALTER TABLE `project_status_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_status_details` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`,`role_name`) VALUES 
 (1,'SUPERADMIN'),
 (2,'TEAMLEAD'),
 (3,'CADMIN'),
 (4,'PM'),
 (5,'USER'),
 (6,'DEVELOPER'),
 (7,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `pmodule_id` bigint(20) NOT NULL,
  `tc_level_id` bigint(20) DEFAULT NULL,
  `dependent_task_status` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `parent_task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkovhsjug063l45ggbgdfxp21s` (`company_id`),
  KEY `FKqxs9fnf0nvg4pou1gng0jtom4` (`parent_task_id`),
  KEY `FKs89sqn0ywj44qy2qk551i7o6x` (`pmodule_id`),
  KEY `FKea367w6vctnb92yo68y47hsf3` (`tc_level_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task`
--

/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;


--
-- Definition of table `task_critical_level`
--

DROP TABLE IF EXISTS `task_critical_level`;
CREATE TABLE `task_critical_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  `level_point` double NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_critical_level`
--

/*!40000 ALTER TABLE `task_critical_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_critical_level` ENABLE KEYS */;


--
-- Definition of table `task_dependency_level`
--

DROP TABLE IF EXISTS `task_dependency_level`;
CREATE TABLE `task_dependency_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dependent_task_status` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_dependency_level`
--

/*!40000 ALTER TABLE `task_dependency_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_dependency_level` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date NOT NULL,
  `confirmation_token` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `regi_date` datetime DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `token_expired` bit(1) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`),
  UNIQUE KEY `UK_cnjwxx5favk5ycqajjt17fwy1` (`mobile`),
  KEY `FK2yuxsfrkkrnkn5emoobcnnc3r` (`company_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`birth_date`,`confirmation_token`,`email`,`enabled`,`file_extension`,`file_name`,`file_path`,`file_size`,`first_name`,`gender`,`last_modified_date`,`last_name`,`mobile`,`password`,`regi_date`,`user_name`,`token_expired`,`company_id`) VALUES 
 (1,'2019-03-06','dc447940-2273-4161-908f-927184779bb3','admin@pms.com',0x01,'image/jpeg','new-rajaul.jpg','/images/new-rajaul.jpg',5892,'Md.',NULL,'2019-03-06 09:55:51',NULL,'01686000000','$2a$10$j2mh682T/2fOBjl4soTbl.DL8Xefi14OCokcUXoDB56R6LLPBxaXi',NULL,'sadmin',0x00,1),
 (10,'1993-12-10','010f45ce-77ea-4f0c-a0e4-ef3f1478d698','juitrishadewan@gmail.com',0x01,'image/jpeg','new-rojina.jpg','/images/new-rojina.jpg',28794,'Rojina','f','2019-03-18 09:17:13',NULL,'01719444064','$2a$10$YHGdnNchnf8AeHho4Cntd.r4SNaoBsXy596Q93leuiuaEqwZ3yJS6','2019-03-18 09:17:13','juitrishadewan',0x01,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`,`role_id`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (10,1),
 (10,2),
 (10,3),
 (10,4),
 (10,5),
 (10,6),
 (10,7);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
