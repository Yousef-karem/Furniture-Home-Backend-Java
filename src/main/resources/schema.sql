-- Furniture Home Database Schema
-- This script creates all tables for the complete e-commerce system

-- Drop existing tables if they exist
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `order_state_change`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `favourite`;
DROP TABLE IF EXISTS `enquiry`;
DROP TABLE IF EXISTS `cartitem`;
DROP TABLE IF EXISTS `image`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `storesetting`;

-- Create cart table
CREATE TABLE `cart` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `total_no_of_items` int(11) NOT NULL,
   `total_price` float NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create product table
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(1023) NOT NULL,
  `price` float NOT NULL,
  `discount` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create user table
CREATE TABLE `user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role` enum('Customer','Admin') NOT NULL DEFAULT 'Customer',
  `cart_id` int(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cart_id` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create cartitem table
CREATE TABLE `cartitem` (
  `product_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`cart_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create enquiry table
CREATE TABLE `enquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1023) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create favourite table
CREATE TABLE `favourite` (
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`customer_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create image table
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `link` varchar(1023) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create order table
CREATE TABLE `order` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `customer_id` int(11) NOT NULL,
   `state` enum('Ordered','Shipped','Delivered','Canceled') NOT NULL,
   `total_price` float NOT NULL,
   `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create order_item table
CREATE TABLE `order_item` (
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`order_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create order_state_change table
CREATE TABLE `order_state_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `state` enum('Ordered','Shipped','Delivered','Canceled') NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create review table
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `rating` enum('1','2','3','4','5') NOT NULL,
  `comment` varchar(1023) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create storesetting table
CREATE TABLE `storesetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1023) NOT NULL,
  `logo_url` varchar(1023) NOT NULL,
  `about_image_url` varchar(1023) NOT NULL,
  `about_description` varchar(1023) NOT NULL,
  `terms_and_conditions` varchar(1023) NOT NULL,
  `facebook_url` varchar(1023) NOT NULL,
  `whatsapp_no` varchar(1023) NOT NULL,
  `phone_no` varchar(1023) NOT NULL,
  `second_phone_no` varchar(1023) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Add foreign key constraints
ALTER TABLE `cartitem`
  ADD CONSTRAINT `cartitem_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `cartitem_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

ALTER TABLE `enquiry`
  ADD CONSTRAINT `enquiry_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`);

ALTER TABLE `favourite`
  ADD CONSTRAINT `favourite_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `favourite_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`);

ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `order_state_change`
  ADD CONSTRAINT `order_state_change_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);

ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

-- Insert sample data for storesetting
INSERT INTO `storesetting` (`name`, `logo_url`, `about_image_url`, `about_description`, `terms_and_conditions`, `facebook_url`, `whatsapp_no`, `phone_no`, `second_phone_no`) VALUES
('Furniture Home', '/images/logo.png', '/images/about.jpg', 'Your trusted source for quality furniture', 'Standard terms and conditions apply', 'https://facebook.com/furniturehome', '+1234567890', '+1234567890', '+0987654321');

-- Insert sample product data
INSERT INTO `product` (`name`, `description`, `price`, `discount`, `category`) VALUES
('Modern Sofa', 'Comfortable 3-seater sofa with premium fabric', 899.99, 10, 'Living Room'),
('Dining Table Set', '6-person dining table with chairs', 599.99, 15, 'Dining Room'),
('Queen Bed Frame', 'Elegant queen-size bed frame with headboard', 399.99, 5, 'Bedroom');
