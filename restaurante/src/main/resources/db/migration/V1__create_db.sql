CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);


CREATE TABLE `category_tb` (
  `id_category` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
);

CREATE TABLE `product_tb` (
    `id_product` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `id_category` INT,
    PRIMARY KEY (`id_product`),
    FOREIGN KEY (`id_category`) REFERENCES `category_tb` (`id_category`)
);

CREATE TABLE `order_tb` (
    `id_order` BIGINT NOT NULL AUTO_INCREMENT,
    `order_date_creation` DATETIME(6),
    `total_amount` DECIMAL(10,2),
    `is_open` BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (`id_order`)
);

CREATE TABLE `order_product_tb` (
    `id_order_product` BIGINT NOT NULL AUTO_INCREMENT,
    `id_order` BIGINT,
    `id_product` BIGINT,
    `quantity` INT,
    PRIMARY KEY (`id_order_product`),
    FOREIGN KEY (`id_order`) REFERENCES `order_tb` (`id_order`),
    FOREIGN KEY (`id_product`) REFERENCES `product_tb` (`id_product`)
);

ALTER TABLE order_product_tb
ADD CONSTRAINT check_product_count CHECK (quantity > 0);

