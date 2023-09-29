-- CREATE TABLE Fee (
--     id INT IDENTITY(1, 1) PRIMARY KEY,
--     [name] NVARCHAR(100),
--     const INT,
--     mandatory BIT,
--     expiration BIGINT,
--     cycle INT,
-- )

INSERT INTO Fee([name], const, mandatory, expiration, cycle) VALUES (N'Tiền điện', 4000, 1, null, 1)
INSERT INTO Fee([name], const, mandatory, expiration, cycle) VALUES (N'Tiền rằm trung thu dự bị', 400000, 0, 1695806460000, 0)
INSERT INTO Fee([name], const, mandatory, expiration, cycle) VALUES (N'Tiền rằm trung thu', 400000, 0, 1696152060000, 0)
INSERT INTO Fee([name], const, mandatory, expiration, cycle) VALUES (N'Tiền nhà trả góp', 40000000, 1, null, 2)

-- CREATE DATABASE ApartmentManagement
-- USE ApartmentManagement
-- GO

-- CREATE TABLE Account (
--     userId INT NOT NULL,
--     name NVARCHAR(100),
--     birthday DATE,
--     phoneNumber INT,
-- )

-- ALTER TABLE Account ADD CONSTRAINT pk_User PRIMARY KEY(userId)

-- CREATE TABLE [Login] (
--     userId INT NOT NULL,
--     username VARCHAR(100) NOT NULL,
--     password VARCHAR(100) NOT NULL,
-- )

-- ALTER TABLE [Login] ADD CONSTRAINT pk_Login PRIMARY KEY(userId)
-- ALTER TABLE [Login] ADD CONSTRAINT fk_Login_User FOREIGN KEY(userId) REFERENCES Account(userId)

-- INSERT INTO Account(userId, name, birthday, phoneNumber) VALUES (1, N'Quốc', '2002-10-03', 947578718)

-- INSERT INTO Login(userId, username, [password]) VALUES (1, 'ragnie', 'Alfie_2110')

-- SELECT userId FROM [Login] WHERE username = 'ragnie' AND [password] = 'Alfie_2110'
-- SELECT userId, name, birthday, phoneNumber FROM Account

-- UPDATE Login SET username='admin', [password]='admin' WHERE userId=1