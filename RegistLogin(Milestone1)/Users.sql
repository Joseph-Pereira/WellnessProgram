DROP TABLE IF EXISTS users;

CREATE TABLE users (
    student_number VARCHAR(20) PRIMARY KEY,
    name           VARCHAR(50) NOT NULL,
    surname        VARCHAR(50) NOT NULL,
    email          VARCHAR(100) UNIQUE NOT NULL,
    phone          VARCHAR(15) NOT NULL,
    password       TEXT NOT NULL  -- store hashed passwords
);

INSERT INTO users (student_number, name, surname, email, phone, password)
VALUES ('202501234', 'Oarabile', 'Mpane', 'oarabile@example.com', '0761234567', 'hashed_password_here');

--SELECT * FROM users WHERE email = ?;
--INSERT INTO users (student_number, name, surname, email, phone, password)
--VALUES (?, ?, ?, ?, ?, ?);
--SELECT * FROM users WHERE email = ? AND password = ?;
