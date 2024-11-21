
CREATE TABLE `t_inventory`
(
    `id` bigint(25) NOT NULL AUTO_INCREMENT,
    `sku_code`  varchar(255) DEFAULT NULL,
    `quantity` int(15)      DEFAULT NULL,
    PRIMARY KEY (`id`)
);