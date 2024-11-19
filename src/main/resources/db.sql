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
INSERT INTO springboot_demo.brand (name, icon) VALUES('苹果', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/%E8%8B%B9%E6%9E%9C.png?sign=169618aa8752af39873ee548bf7fe683&t=1732029882');
INSERT INTO springboot_demo.brand (name, icon) VALUES('小米', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/%E5%B0%8F%E7%B1%B3.png?sign=d014f41da5a4f68d1ab21cb62d6ec1cb&t=1732029893');
INSERT INTO springboot_demo.brand (name, icon) VALUES('华为', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/%E5%8D%8E%E4%B8%BA.png?sign=a7cba986cdcd45a033c46e5f5b97e9d3&t=1732029902');
INSERT INTO springboot_demo.brand (name, icon) VALUES('OPPO', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/OPPO.png?sign=961f6fe770f392e04f9f4eebe76cb1d2&t=1732029913');
INSERT INTO springboot_demo.brand (name, icon) VALUES('VIVO', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/VIVO.png?sign=473047b72d4afdac8728037e4af6f15b&t=1732029920');
INSERT INTO springboot_demo.brand (name, icon) VALUES('红米', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/%E7%BA%A2%E7%B1%B3.png?sign=90f305692ab5a61a2c40f20d8f9a501f&t=1732029928');
INSERT INTO springboot_demo.brand (name, icon) VALUES('荣耀', 'https://7072-prod-1gqvztydb6cf03eb-1331383526.tcb.qcloud.la/icon/%E8%8D%A3%E8%80%80.png?sign=65f42324ee69694dd850dc799749288b&t=1732029935');