CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(64) NOT NULL,
                        `password` varchar(64) NOT NULL,
                        `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- springboot_demo.brand definition

CREATE TABLE `brand` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(64) NOT NULL,
                         `icon` varchar(1024) DEFAULT NULL,
                         `priceUrl` varchar(1024) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO springboot_demo.brand (id, name, icon, priceUrl) VALUES(1, '苹果', '1');
INSERT INTO springboot_demo.brand (id, name, icon, priceUrl) VALUES(2, '小米', '2');
INSERT INTO springboot_demo.brand (id, name, icon, priceUrl) VALUES(3, '华为', '3');
INSERT INTO springboot_demo.brand (id, name, icon, priceUrl) VALUES(4, 'OPPO', '4');
INSERT INTO springboot_demo.brand (id, name, icon, priceUrl) VALUES(5, 'VIVO', '5');