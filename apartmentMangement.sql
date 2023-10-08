CREATE DATABASE ApartmentManagement
USE ApartmentManagement
GO

CREATE TABLE [User] (
    userId INT NOT NULL,
    username VARCHAR(100) NOT NULL,
    [password] VARCHAR(100) NOT NULL,
    [name] NVARCHAR(100) NOT NULL,
    birthday DATE,
    phoneNumber INT,
)

ALTER TABLE [User] ADD CONSTRAINT pk_User PRIMARY KEY(userId)

INSERT INTO [User] VALUES (1, 'admin', 'admin', N'Bùi Anh Quốc', '2002-03-10', 947578718)

SELECT * FROM [User]