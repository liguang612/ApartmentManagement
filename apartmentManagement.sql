CREATE DATABASE ApartmentManagement
USE ApartmentManagement
GO

CREATE TABLE Account (
    userId INT NOT NULL,
    name NVARCHAR(100),
    birthday DATE,
    phoneNumber INT,
)

ALTER TABLE Account ADD CONSTRAINT pk_User PRIMARY KEY(userId)

CREATE TABLE [Login] (
    userId INT NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
)

ALTER TABLE [Login] ADD CONSTRAINT pk_Login PRIMARY KEY(userId)
ALTER TABLE [Login] ADD CONSTRAINT fk_Login_User FOREIGN KEY(userId) REFERENCES Account(userId)

INSERT INTO Account(userId, name, birthday, phoneNumber) VALUES (1, N'Quốc', '2002-10-03', 947578718)

INSERT INTO Login(userId, username, [password]) VALUES (1, 'ragnie', 'Alfie_2110')

SELECT userId FROM [Login] WHERE username = 'ragnie' AND [password] = 'Alfie_2110'
SELECT userId, name, birthday, phoneNumber FROM Account