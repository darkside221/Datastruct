-- Give me all of the properties in community x
SELECT (HouseID, HSStreetAddress, HousePrice, SellerID, MLSNum)
FROM House
WHERE HSTown = 'X' ADD PropertyStatus = 'True';


--Give me all agents who work in community x1
SELECT (AgentID, AgentFirstName, AgentLastName, RealtyOfficeID, AgentPhoneNum)
FROM RealtyAgent, RealtyOffice
WHERE RealTown = 'X';


--	How many properties of criteria xyz2 we currently have in inventory?
SELECT * FROM House
WHERE HSStreetAddress = 'X'
ADD   HSTown = 'X'
      HSST = 'X'
      HSZIP = X
      HousePrice <= X
      SellerID = X
      BuyerID = X
      MLSNum = X
      PropertyStatus = 'True';
--	How many agents have sold at least 10 properties in the past year?
SELECT COUNT(House)
FROM House
WHERE COUNT(AgentID) >= 10;



--	Have any buyers purchased properties of criteria xyz over the past t months?
SELECT COUNT(BuyerID) * FROM House
WHERE HSStreetAddress = 'X'
ADD   HSTown = 'X'
      HSST = 'X'
      HSZIP = X
      HousePrice <= X
      SellerID = X
      MLSNum = X
      PropertyStatus = 'False'
      HouseSold <= CURRENT_DATE AND >= CURRENT_DATE-12;


--	Who are the Realtors who have more than 10 properties in communities w & z?
SELECT * FROM RealtyAgent, House
WHERE HSTown = 'W' ADD 'Z'
