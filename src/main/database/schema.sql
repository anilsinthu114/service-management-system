/* To create a dtabase*/
CREATE DATABASE IF NOT EXISTS ServiceDB;
use ServiceDB;
/*To create a user mangemnet table*/
CREATE TABLE IF NOT EXISTS users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
/* to create a table for services*/
CREATE TABLE IF NOT EXISTS service (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL
);
