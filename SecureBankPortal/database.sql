DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_number BIGINT UNIQUE,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    balance DOUBLE,
    role VARCHAR(20) DEFAULT 'USER'
);

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_number BIGINT,
    type VARCHAR(20),
    amount DOUBLE,
    receiver_account BIGINT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

UPDATE users SET role='ADMIN' WHERE username='admin';

INSERT INTO users (account_number, username, password, balance, role)
VALUES (1000000001, 'admin', 'admin123', 0, 'ADMIN');