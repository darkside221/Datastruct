CREATE TABLE Sller  
	SellerID int,
	SellerFirstName varchar(255),
	SellerLastName  varchar(255),
	AgentID  int,
	HouseID int,
	SellerPhoneNum int,
);
CREATE TABLE Buyer (
    BuyerID int,
    BuyerFirstName varchar(255),
    BuyerLastName varchar(255),
    AgentID int,
    HouseID int,
    BuyerPhoneNum int,
);

CREATE TABLE RealtyAgent ( 
    AgentID INT,
    AgentFirstName VARCHAR(255),
    AgentLastName  VARCHAR(255),
    RealtyOfficeID  INT,
    AgentPhoneNum INT,
);

CREATE TABLE RealtyOffice ( 
    RealtyOfficeID INT,
    RealStreetAddress VARCHAR(255),
    RealTown  VARCHAR(255),
    RealST  VARCHAR(2),
    RealZIP INT,
    RealtyOfficePhoneNum INT,
);

CREATE TABLE House ( 
    HouseID INT,
    HSStreetAddress VARCHAR(255),
    HSTown  VARCHAR(255),
    HSST  VARCHAR (2),
    HSZIP INT,
    HousePrice INT,
    SellerID INT,
    BuyerID INT,
    MLSNum INT,
    PropertyStatus VARCHAR(255),
    HouseSold DATE,
     AgentID INT,
    
);

