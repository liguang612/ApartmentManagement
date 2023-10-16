CREATE DATABASE ApartmentManagement
USE ApartmentManagement
GO

CREATE TABLE [User] (
    userId INT NOT NULL,
    [name] NVARCHAR(50),
    birthday DATE,
    phoneNumber INT,
    [image] VARBINARY,
    activateDay DATE,
    abName NVARCHAR(100),
)
ALTER TABLE [User] ADD CONSTRAINT pk_User PRIMARY KEY (userId)
ALTER TABLE [User] ADD CONSTRAINT unique_User_phoneNumber UNIQUE (phoneNumber)

CREATE TABLE [Login] (
    userId INT NOT NULL,
    username VARCHAR(100),
    [password] VARCHAR(100),
)
ALTER TABLE [Login] ADD CONSTRAINT pk_Login PRIMARY KEY (userId)
ALTER TABLE [Login] ADD CONSTRAINT unique_Login_username UNIQUE (username)
ALTER TABLE [Login] ADD CONSTRAINT fk_Login_User FOREIGN KEY (userId) REFERENCES [User](userId)

CREATE TABLE Apartment (
    apartmentId INT NOT NULL,
    ownerId INT,
    area FLOAT,
)
ALTER TABLE Apartment ADD CONSTRAINT pk_Apartment PRIMARY KEY (apartmentId)

CREATE TABLE Resident (
    id INT NOT NULL,
    [name] NVARCHAR(50),
    birthday DATE,
    phoneNumber INT,
    nationality NVARCHAR(50),
    apartmentId INT,
    relationship NVARCHAR(50),
)
ALTER TABLE Resident ADD CONSTRAINT pk_Resident PRIMARY KEY (id)
ALTER TABLE Resident ADD CONSTRAINT fk_Resident_Apartment FOREIGN KEY (apartmentId) REFERENCES Apartment(apartmentId)

CREATE TABLE Activity (
    id INT NOT NULL,
    residentId INT,
    [status] INT,
    timeIn DATE,
    [timeOut] DATE,
    note NVARCHAR(4000),
)

CREATE TABLE Fee (
    id INT NOT NULL,
    [name] NVARCHAR(50),
    cost INT,
    mandatory BIT,
    cycle INT,
    expiration DATE,
)

CREATE TABLE Payment (
    apartmentId INT NOT NULL,
    feeId INT,
    [number] INT,
    timeValidate DATE,
)

CREATE TABLE Vehicle (
    license_plates VARCHAR(9) NOT NULL,
    apartmentId INT,
    [type] INT,
)

USE master
GO
DROP DATABASE ApartmentManagement