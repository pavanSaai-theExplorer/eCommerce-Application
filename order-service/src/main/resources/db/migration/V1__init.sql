
CREATE TABLE `t_orders`
(
    `id` bigint(25) NOT NULL AUTO_INCREMENT,
    `order_number` varchar(255) DEFAULT NULL,
    `sku_code`  varchar(255),
    `price`    decimal(20, 2),
    `quantity` int(10),
    `first_name` varchar(255),
    `last_name` varchar(255),
    `email` varchar(255),
    PRIMARY KEY (`id`)
);