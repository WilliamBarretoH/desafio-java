﻿CREATE TABLE IF NOT EXISTS `user_role` (
  `id_user` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `fk_user_role_role` (`id_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_user`) REFERENCES `user_tb` (`id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`id_role`) REFERENCES `role_tb` (`id`)
);