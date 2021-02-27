CREATE TABLE Seller ( 
    SellerID INT,
    SellerFirstName VARCHAR(255),
    SellerLastName  VARCHAR(255),
    AgentID  INT,
    HouseID INT,
    SellerPhoneNum INT,
);
CREATE TABLE Buyer ( 
    BuyerID INT,
    BuyerFirstName VARCHAR(255),
    BuyerLastName  VARCHAR(255),
    AgentID  INT,
    HouseID INT,
    BuyerPhoneNum INT,
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
);

