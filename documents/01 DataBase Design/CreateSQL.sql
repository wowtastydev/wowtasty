DROP TABLE code_master;
DROP TABLE delivery_area;
DROP TABLE delivery_man;
DROP TABLE delivery_open_hour;
DROP TABLE delivery_master;
DROP TABLE ecash_purchase;
DROP TABLE id_master;
DROP TABLE member_master;
DROP TABLE bill_detail;
DROP TABLE bill_master;
DROP TABLE order_menu;
DROP TABLE order_menu_option;
DROP TABLE order_restaurant;
DROP TABLE order_master;
DROP TABLE restaurant_category;
DROP TABLE restaurant_delivery_area;
DROP TABLE restaurant_menu;
DROP TABLE restaurant_menu_option;
DROP TABLE restaurant_open_hour;
DROP TABLE restaurant_pict;
DROP TABLE restaurant_master;
DROP TABLE wow_master;
DROP TABLE city_postal_match;
DROP TABLE page_auth;


CREATE TABLE Bill_Master (
  BillingID CHAR(10) NOT NULL,
  YearMonth CHAR(6) NOT NULL,
  SemiMonthType CHAR(1) NOT NULL,
  BillFrom DATE NULL,
  BillTo DATE NULL,
  DueDate DATE NULL,
  CompanyID CHAR(6) NULL,
  CompanyName VARCHAR(50) NULL,
  BillAddress VARCHAR(100) NULL,
  BillSuite VARCHAR(10) NULL,
  BillCity CHAR(5) NULL,
  BillProvince CHAR(2) NULL,
  BillPostalCode CHAR(6) NULL,
  BillAccountNO CHAR(6) NULL,
  PaymentAmount DECIMAL(10,2) ZEROFILL NULL,
  SalesAmount DECIMAL(10,2) ZEROFILL NULL,
  CommissionAmount DECIMAL(10,2) ZEROFILL NULL,
  CashCommissionAmount DECIMAL(10,2) ZEROFILL NULL,
  DeliveryAmount DECIMAL(10,2) ZEROFILL NULL,
  TipAmount DECIMAL(10,2) ZEROFILL NULL,
  TaxAmount DECIMAL(10,2) ZEROFILL NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(BillingID),
  INDEX Bill_Master_YearMonth_SemiType(YearMonth, SemiMonthType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Member_Master (
  MemberID CHAR(10) NOT NULL,
  Email VARCHAR(256) NOT NULL,
  Password CHAR(128) NOT NULL,
  FirstName VARCHAR(50) NULL,
  LastName VARCHAR(50) NULL,
  Telephone VARCHAR(20) NULL,
  Address VARCHAR(100) NULL,
  Suite VARCHAR(10) NULL,
  City CHAR(5) NULL,
  Province CHAR(2) NULL,
  PostalCode CHAR(6) NULL,
  DelivFirstName VARCHAR(50) NULL,
  DelivLastName VARCHAR(50) NULL,
  DelivTelephone VARCHAR(20) NULL,
  DelivAddress VARCHAR(100) NULL,
  DelivSuite VARCHAR(10) NULL,
  DelivBuzzer VARCHAR(10) NULL,
  DelivCity CHAR(5) NULL,
  DelivProvince CHAR(2) NULL,
  DelivPostalCode CHAR(6) NULL,
  DelivInstruction VARCHAR(100) NULL,
  Ecash INT(11) NULL,
  Status CHAR(1) NULL,
  TotalOrderCnt INTEGER UNSIGNED NULL,
  RegisterDate DATE NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(MemberID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ID_Master (
  Seq INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  IDPrefix VARCHAR(5) NULL,
  IDLength TINYINT UNSIGNED NULL,
  IDMax BIGINT UNSIGNED NULL,
  PRIMARY KEY(Seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Wow_Master (
  Seq INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Name VARCHAR(50) NULL,
  Telephone VARCHAR(20) NULL,
  Fax VARCHAR(20) NULL,
  Address VARCHAR(100) NULL,
  Suite VARCHAR(10) NULL,
  City CHAR(5) NULL,
  Province CHAR(2) NULL,
  PostalCode CHAR(6) NULL,
  LogoImagePath VARCHAR(200) NULL,
  Website VARCHAR(100) NULL,
  Facebook VARCHAR(100) NULL,
  Email1 VARCHAR(256) NULL,
  Email2 VARCHAR(256) NULL,
  EcashPerCent TINYINT UNSIGNED NULL,
  EcashBonus INTEGER UNSIGNED NULL,
  EcashPerCoupon TINYINT UNSIGNED NULL,
  HoldingFlag CHAR(1) NULL,
  TaxNO CHAR(9) NULL,
  TransactionKey VARCHAR(20) NULL,
  TransactionLoginID VARCHAR(20) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(Seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Delivery_Master (
  DeliveryCompanyID CHAR(6) NOT NULL,
  Name VARCHAR(50) NULL,
  DeliveryCompanyType CHAR(1) NULL,
  FirstName VARCHAR(50) NULL,
  LastName VARCHAR(50) NULL,
  Telephone VARCHAR(20) NULL,
  Fax VARCHAR(20) NULL,
  Address VARCHAR(100) NULL,
  Suite VARCHAR(10) NULL,
  City CHAR(5) NULL,
  Province CHAR(2) NULL,
  PostalCode CHAR(6) NULL,
  LogoImagePath VARCHAR(200) NULL,
  Website VARCHAR(100) NULL,
  Facebook VARCHAR(100) NULL,
  Email1 VARCHAR(256) NULL,
  Email2 VARCHAR(256) NULL,
  CommissionType CHAR(1) NULL,
  Commission DECIMAL(5,2) ZEROFILL NULL,
  Status CHAR(1) NULL,
  RegisterDate DATE NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(DeliveryCompanyID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Code_Master (
  GroupCD CHAR(3) NOT NULL,
  Code VARCHAR(10) NOT NULL,
  ShortName VARCHAR(20) NULL,
  Name VARCHAR(50) NULL,
  Value VARCHAR(10) NULL,
  Sort INTEGER UNSIGNED NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(GroupCD, Code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Ecash_Purchase (
  Seq INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  MemberID CHAR(10) NULL,
  EcashAmount INTEGER UNSIGNED NULL,
  PaymentPrice DECIMAL(5,2) ZEROFILL NULL,
  PaymentType CHAR(1) NULL,
  PaymentCreditType CHAR(1) NULL,
  PaymentCreditNO CHAR(19) NULL,
  PaymentDebitNO CHAR(19) NULL,
  PaymentStatus CHAR(1) NULL,
  PaymentTime DATETIME NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(Seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Master (
  RestaurantID CHAR(6) NOT NULL,
  RestaurantType CHAR(1) NULL,
  Name VARCHAR(50) NULL,
  CuisineType CHAR(2) NULL,
  MemberID CHAR(10) NULL,
  FirstName VARCHAR(50) NULL,
  LastName VARCHAR(50) NULL,
  Telephone VARCHAR(20) NULL,
  Fax VARCHAR(20) NULL,
  Address VARCHAR(100) NULL,
  Suite VARCHAR(10) NULL,
  City CHAR(5) NULL,
  Province CHAR(2) NULL,
  PostalCode CHAR(6) NULL,
  Website VARCHAR(100) NULL,
  Facebook VARCHAR(100) NULL,
  Email1 VARCHAR(256) NULL,
  Email2 VARCHAR(256) NULL,
  LogoImagePath VARCHAR(200) NULL,
  MainImagePath VARCHAR(200) NULL,
  GoogleMapURL VARCHAR(1500) NULL,
  Profile VARCHAR(500) NULL,
  Keyword VARCHAR(500) NULL,
  CommissionType CHAR(1) NULL,
  Commission DECIMAL(5,2) ZEROFILL NULL,
  CashCommission DECIMAL(5,2) ZEROFILL NULL,
  AveragePrice DECIMAL(5,2) ZEROFILL NULL,
  MinPrice DECIMAL(5,2) ZEROFILL NULL,
  DeliveryCompanyID CHAR(6) NULL,
  DeliveryTime INTEGER UNSIGNED NULL,
  BillAccountNO CHAR(6) NULL,
  AverageRate DECIMAL(2,1) NULL,
  TotalReviewCnt INTEGER UNSIGNED NULL,
  TotalOrderCnt INTEGER UNSIGNED NULL,
  Status CHAR(1) NULL,
  RegisterDate DATE NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(RestaurantID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Menu (
  RestaurantID CHAR(6) NOT NULL,
  MenuID INTEGER UNSIGNED NOT NULL,
  CategoryID INTEGER UNSIGNED NOT NULL,
  Name VARCHAR(50) NULL,
  Price DECIMAL(5,2) NULL,
  Description VARCHAR(100) NULL,
  ImagePath VARCHAR(200) NULL,
  Sort INTEGER UNSIGNED NULL,
  TaxRate DECIMAL(4,2) NULL,
  Status CHAR(1) NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(RestaurantID, MenuID),
  INDEX Restaurant_Menu_FKIndex1(RestaurantID),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Delivery_Area (
  RestaurantID CHAR(6) NOT NULL,
  PostalPrefix CHAR(3) NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  DeliveryCompanyID CHAR(6) NOT NULL,
  MinPrice DECIMAL(5,2) NULL,
  DeliveryFee DECIMAL(5,2) NULL,
  PRIMARY KEY(RestaurantID, PostalPrefix, Seq),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Pict (
  RestaurantID CHAR(6) NOT NULL,
  Seq INTEGER UNSIGNED NOT NULL,
  FileName VARCHAR(100) NULL,
  FilePath VARCHAR(200) NULL,
  FileSize INTEGER UNSIGNED NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(RestaurantID, Seq),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Open_Hour (
  RestaurantID CHAR(6) NOT NULL,
  WeekDay TINYINT UNSIGNED NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  StartTime TIME NULL,
  EndTime TIME NULL,
  PRIMARY KEY(RestaurantID, WeekDay, Seq),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Menu_Option (
  RestaurantID CHAR(6) NOT NULL,
  MenuID INTEGER UNSIGNED NOT NULL,
  OptionID INTEGER UNSIGNED NOT NULL,
  OptionType CHAR(1) NULL,
  OptionGroup TINYINT UNSIGNED NULL,
  Name VARCHAR(50) NULL,
  ExtraCharge DECIMAL(5,2) NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(RestaurantID, MenuID, OptionID),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Restaurant_Category (
  RestaurantID CHAR(6) NOT NULL,
  CategoryID INTEGER UNSIGNED NOT NULL,
  Name VARCHAR(50) NULL,
  Sort TINYINT UNSIGNED NULL,
  PRIMARY KEY(RestaurantID, CategoryID),
  FOREIGN KEY(RestaurantID)
    REFERENCES Restaurant_Master(RestaurantID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Delivery_Area (
  DeliveryCompanyID CHAR(6) NOT NULL,
  PostalPrefix CHAR(3) NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  MinPrice DECIMAL(5,2) ZEROFILL NULL,
  DeliveryFee DECIMAL(5,2) ZEROFILL NULL,
  PRIMARY KEY(DeliveryCompanyID, PostalPrefix, Seq),
  FOREIGN KEY(DeliveryCompanyID)
    REFERENCES Delivery_Master(DeliveryCompanyID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Delivery_Man (
  DeliveryCompanyID CHAR(6) NOT NULL,
  DeliveryManID INTEGER UNSIGNED NOT NULL,
  FirstName VARCHAR(50) NULL,
  LastName VARCHAR(50) NULL,
  Telephone VARCHAR(20) NULL,
  Email VARCHAR(256) NULL,
  Address VARCHAR(100) NULL,
  Suite VARCHAR(10) NULL,
  City CHAR(5) NULL,
  Province CHAR(2) NULL,
  PostalCode CHAR(6) NULL,
  NAFlag CHAR(1) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(DeliveryCompanyID, DeliveryManID),
  FOREIGN KEY(DeliveryCompanyID)
    REFERENCES Delivery_Master(DeliveryCompanyID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Bill_Detail (
  BillingID CHAR(10) NOT NULL,
  Seq INTEGER UNSIGNED NOT NULL,
  OrderID CHAR(13) NULL,
  InvoiceNO CHAR(13) NULL,
  OrderTime DATETIME NULL,
  DeliveryTime DATETIME NULL,
  OrderType CHAR(1) NULL,
  OrderMemberID CHAR(10) NULL,
  TotalMenuName VARCHAR(100) NULL,
  TotalUnit INTEGER UNSIGNED NULL,
  UnitPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalExtraCharge DECIMAL(5,2) ZEROFILL NULL,
  CouponPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalPrice DECIMAL(7,2) ZEROFILL NULL,
  OrderStatus CHAR(2) NULL,
  PRIMARY KEY(BillingID, Seq),
  FOREIGN KEY(BillingID)
    REFERENCES Bill_Master(BillingID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Delivery_Open_Hour (
  DeliveryCompanyID CHAR(6) NOT NULL,
  WeekDay TINYINT UNSIGNED NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  StartTime TIME NULL,
  EndTime TIME NULL,
  PRIMARY KEY(DeliveryCompanyID, WeekDay, Seq),
  FOREIGN KEY(DeliveryCompanyID)
    REFERENCES Delivery_Master(DeliveryCompanyID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE order_master (
  OrderID CHAR(13) NOT NULL ,
  InvoiceNO CHAR(13) NULL,
  OrderTime DATETIME NULL,
  DeliveryTime DATETIME NULL,
  OrderType CHAR(1) NULL,
  PaymentType CHAR(1) NULL,
  PaymentCreditType CHAR(1) NULL,
  PaymentCreditNO CHAR(19) NULL,
  PaymentDebitNO CHAR(19) NULL,
  PaymentAmount DECIMAL(7,2) NULL,
  PaymentRefNO VARCHAR(20) NULL,
  PaymentStatus CHAR(1) NULL,
  PaymentTime DATETIME NULL,
  PaymentEcash INTEGER UNSIGNED NULL,
  PaymentGiftCard DECIMAL(7,2) ZEROFILL NULL,
  TotalPrice DECIMAL(7,2) ZEROFILL NULL,
  TotalPriceWithTax DECIMAL(7,2) ZEROFILL NULL,
  OrderMemberID CHAR(10) NULL,
  OrderMemberEmail VARCHAR(256) NULL,
  OrderMemberTelephone VARCHAR(20) NULL,
  BillFirstName VARCHAR(50) NULL,
  BillLastName VARCHAR(50) NULL,
  BillTelephone VARCHAR(20) NULL,
  BillAddress VARCHAR(100) NULL,
  BillSuite VARCHAR(10) NULL,
  BillCity CHAR(5) NULL,
  BillProvince CHAR(2) NULL,
  BillPostalCode CHAR(6) NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY (OrderID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Order_Restaurant (
  OrderID CHAR(13) NOT NULL,
  RestaurantID CHAR(6) NOT NULL,
  RestaurantName VARCHAR(50) NULL,
  RestaurantEmail VARCHAR(256) NULL,
  DeliveryType CHAR(1) NULL,
  DeliveryPrice DECIMAL(5,2) ZEROFILL NULL,
  DeliveryTaxPrice DECIMAL(5,2) ZEROFILL NULL,
  FoodTotalPrice DECIMAL(7,2) ZEROFILL NULL,
  FoodTaxPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalPrice DECIMAL(7,2) ZEROFILL NULL,
  TipPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalPriceWithTax DECIMAL(7,2) ZEROFILL NULL,
  OrderStatus CHAR(2) NULL,
  DeclinedReason CHAR(1) NULL,
  DeliveryCompanyID CHAR(6) NULL,
  DeliverymanID INTEGER UNSIGNED NULL,
  DeliverymanName VARCHAR(50) NULL,
  DeliverymanTelephone VARCHAR(20) NULL,
  DeliveryFirstName VARCHAR(50) NULL,
  DeliveryLastName VARCHAR(50) NULL,
  DeliveryTelephone VARCHAR(20) NULL,
  DeliveryAddress VARCHAR(100) NULL,
  DeliverySuite VARCHAR(10) NULL,
  DeliveryBuzzer VARCHAR(10) NULL,
  DeliveryCity CHAR(5) NULL,
  DeliveryProvince CHAR(2) NULL,
  DeliveryPostalCode CHAR(6) NULL,
  DeliveryInstruction VARCHAR(100) NULL,
  ReviewRate TINYINT UNSIGNED NULL,
  UpdateID CHAR(10) NULL,
  UpdateTime DATETIME NULL,
  PRIMARY KEY(OrderID, RestaurantID),
  FOREIGN KEY(OrderID)
    REFERENCES Order_Master(OrderID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Order_Menu (
  OrderID CHAR(13) NOT NULL,
  RestaurantID CHAR(6) NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  MenuID INTEGER UNSIGNED NOT NULL,
  MenuName VARCHAR(50) NULL,
  Unit INTEGER UNSIGNED NULL,
  UnitPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalExtraCharge DECIMAL(5,2) NULL,
  CouponNO INTEGER UNSIGNED NULL,
  CouponPrice DECIMAL(5,2) ZEROFILL NULL,
  TaxPrice DECIMAL(5,2) ZEROFILL NULL,
  TotalPrice DECIMAL(7,2) ZEROFILL NULL,
  TotalPriceWithTax DECIMAL(7,2) NULL,
  Instruction VARCHAR(50) NULL,
  PRIMARY KEY(OrderID, RestaurantID, Seq),
  FOREIGN KEY(OrderID)
    REFERENCES Order_Master(OrderID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Order_Menu_Option (
  OrderID CHAR(13) NOT NULL,
  RestaurantID CHAR(6) NOT NULL,
  Seq TINYINT UNSIGNED NOT NULL,
  OptionID INTEGER UNSIGNED NOT NULL,
  OptionName VARCHAR(50) NULL,
  MenuID INTEGER UNSIGNED NOT NULL,
  Unit TINYINT UNSIGNED NULL,
  ExtraCharge DECIMAL(5,2) ZEROFILL NULL,
  PRIMARY KEY(OrderID, RestaurantID, Seq, OptionID),
  FOREIGN KEY(OrderID)
    REFERENCES Order_Master(OrderID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE city_postal_match (
  Seq int(10) unsigned NOT NULL AUTO_INCREMENT,
  CityCode char(5) DEFAULT NULL,
  PostalCodePrefix char(3) DEFAULT NULL,
  PRIMARY KEY (Seq)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8;

CREATE TABLE action_auth (
  ActionName varchar(100) NOT NULL,
  Auth char(2) DEFAULT NULL,
  PRIMARY KEY (ActionName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE contents_text (
  Seq int(10) unsigned NOT NULL AUTO_INCREMENT,
  Subject varchar(256) DEFAULT NULL,
  Contents text,
  PRIMARY KEY (Seq)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;