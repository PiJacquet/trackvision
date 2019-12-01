drop database trackprddb;
create database trackprddb;
use trackprddb;

#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Addresses
#------------------------------------------------------------

CREATE TABLE Addresses(
        ID_Address        Int  Auto_increment  NOT NULL ,
        Number_Address    Int NOT NULL ,
        Street_Address        Varchar (35) NOT NULL ,
        City_Address      Varchar (35) NOT NULL ,
        PostalCode_Address Varchar (5) NOT NULL ,
        Country_Address       Varchar (30) NOT NULL
	,CONSTRAINT Address_PK PRIMARY KEY (ID_Address)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Residences
#------------------------------------------------------------

CREATE TABLE Residences(
        ID_Residence   Int Auto_increment NOT NULL ,
        Name_Residence Varchar (50) NOT NULL ,
        ID_Address   Int NOT NULL
	,CONSTRAINT Residences_PK PRIMARY KEY (ID_Residence)

	,CONSTRAINT Residences_Adress_FK FOREIGN KEY (ID_Address) REFERENCES Addresses(ID_Address)
	,CONSTRAINT Residences_Adress_AK UNIQUE (ID_Address)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Employees
#------------------------------------------------------------
CREATE TABLE Employees(
        ID_Employee          Int  Auto_increment  NOT NULL ,
        Login_Employee Varchar (20) NOT NULL ,
        Password_Employee  Varchar (40) NOT NULL ,
        Lastname_Employee         Varchar (30) NOT NULL ,
        Firstname_Employee      Varchar (30) NOT NULL ,
        PhoneNumber_Employee      Varchar (12) NOT NULL ,
        ID_Address           Int NOT NULL
	,CONSTRAINT Employee_PK PRIMARY KEY (ID_Employee)

	,CONSTRAINT Employee_Adress_FK FOREIGN KEY (ID_Address) REFERENCES Addresses(ID_Address)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Levels
#------------------------------------------------------------
CREATE TABLE Levels(
        ID_Level    Int  Auto_increment  NOT NULL ,
        ID_Residence      Int NOT NULL
	,CONSTRAINT Level_PK PRIMARY KEY (ID_Level,ID_Residence)
	,CONSTRAINT Level_Residences_FK FOREIGN KEY (ID_Residence) REFERENCES Residences(ID_Residence)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Apartments
#------------------------------------------------------------
CREATE TABLE Apartments(
        ID_Apartment   Int  Auto_increment  NOT NULL,
        Name_Apartment  Varchar(30) NOT NULL ,
        ID_Level      Int NOT NULL
	,CONSTRAINT Apartment_PK PRIMARY KEY (ID_Apartment)
	,CONSTRAINT Apartment_Level_FK FOREIGN KEY (ID_Level) REFERENCES Levels(ID_Level)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Residents
#------------------------------------------------------------

CREATE TABLE Residents(
        ID_Resident          Int  Auto_increment  NOT NULL ,
        Login_Resident       Varchar (20) NOT NULL ,
        Password_Resident    Varchar (40) NOT NULL ,
        Lastname_Resident    Varchar (30) NOT NULL ,
        Firstname_Resident   Varchar (30) NOT NULL ,
        Age_Resident         Int NOT NULL ,
        Information_Resident Varchar (300) NOT NULL COMMENT "medical information"  ,
		ID_Apartment         Int
	,CONSTRAINT Residents_PK PRIMARY KEY (ID_Resident)
	,CONSTRAINT Residents_Apartment_FK FOREIGN KEY (ID_Apartment) REFERENCES Apartments(ID_Apartment)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Referentiel_Objects
#------------------------------------------------------------
CREATE TABLE Referentiel_Objects(
		Type_Object Varchar(30) NOT NULL,
        CONSTRAINT Referentiel_Objects PRIMARY KEY (Type_Object)
);


#------------------------------------------------------------
# Table: Objects
#------------------------------------------------------------
CREATE TABLE Objects(
        ID_Object     Int  Auto_increment  NOT NULL ,
        Type_Object   Varchar(30) NOT NULL,
	    State_Object  Bool NOT NULL ,
        ID_Apartment Int NOT NULL ,
        Mac_Object VarChar(17) 
	,CONSTRAINT Objects_PK PRIMARY KEY (ID_Object)
	,CONSTRAINT Objects_Type_FK FOREIGN KEY (Type_Object) REFERENCES Referentiel_Objects(Type_Object) 
	,CONSTRAINT Objects_Apartment_FK FOREIGN KEY (ID_Apartment) REFERENCES Apartments(ID_Apartment)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Alerts
#------------------------------------------------------------

CREATE TABLE Alerts(
        ID_Alert        Int  Auto_increment  NOT NULL ,
		State_Alert   Bool NOT NULL ,
        Level_Alert    Int NOT NULL,
        Date_Alert      TIMESTAMP NOT NULL ,
        Message_Alert   Varchar (60) ,
        ID_Object             Int NOT NULL
	,CONSTRAINT Alerts_PK PRIMARY KEY (ID_Alert)

	,CONSTRAINT Alerts_Objects_FK FOREIGN KEY (ID_Object) REFERENCES Objects(ID_Object)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Malfunctions
#------------------------------------------------------------

CREATE TABLE Malfunctions(
        ID_Malfunction   Int  Auto_increment  NOT NULL ,
		State_Malfuncion  Bool NOT NULL ,
        Date_Malfunction TIMESTAMP NOT NULL ,
        Message_Malfunction   Varchar (60) ,
        ID_Object             Int NOT NULL
	,CONSTRAINT Malfunctions_PK PRIMARY KEY (ID_Malfunction)

	,CONSTRAINT Malfunctions_Objects_FK FOREIGN KEY (ID_Object) REFERENCES Objects(ID_Object)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ObjectsSmoke
#------------------------------------------------------------

CREATE TABLE ObjectsSmoke(
        ID_ObjectSmoke          Int NOT NULL,
        Sensibility_ObjectSmoke Int NOT NULL
	,CONSTRAINT ObjectSmoke_PK PRIMARY KEY (ID_ObjectSmoke)
	,CONSTRAINT ObjectSmoke_Objects_FK FOREIGN KEY (ID_ObjectSmoke) REFERENCES Objects(ID_Object)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ObjectsTemperature
#------------------------------------------------------------

CREATE TABLE ObjectsTemperature(
        ID_ObjectTemperature           Int NOT NULL ,
        Reference_ObjectTemperature    Float NOT NULL ,
        ToleranceMin_ObjectTemperature Float NOT NULL ,
        ToleranceMax_ObjectTemperature Float NOT NULL 
	,CONSTRAINT ObjectTemperature_PK PRIMARY KEY (ID_ObjectTemperature)
	,CONSTRAINT ObjectTemperature_Objects_FK FOREIGN KEY (ID_ObjectTemperature) REFERENCES Objects(ID_Object)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ObjectsFurnace
#------------------------------------------------------------

CREATE TABLE ObjectsFurnace(
        ID_ObjectFurnace    Int NOT NULL ,
        Reference_ObjectTemperature  Float NOT NULL ,
		currentTemp_ObjetTemperature Float NOT NULL
	,CONSTRAINT ObjectFurnace_PK PRIMARY KEY (ID_ObjectFurnace)
	,CONSTRAINT ObjectFurnace_Objects_FK FOREIGN KEY (ID_ObjectFurnace) REFERENCES Objects(ID_Object)
)ENGINE=InnoDB;


insert into Referentiel_Objects(Type_Object) values('Smoke Sensor');
insert into Referentiel_Objects(Type_Object) values('Temperature Sensor');
insert into Referentiel_Objects(Type_Object) values('Furnace');

insert into Addresses (Number_Address, Street_Address, City_Address, PostalCode_Address, Country_Address) values ('71', 'rue Saint-Simon' , 'Créteil' , '94000', 'France');

insert into Employees (Login_Employee, Password_Employee, Lastname_Employee, Firstname_Employee, PhoneNumber_Employee, ID_Address) values ('pierre_adm', 'tv', 'Jacquet', 'Pierre', '0607080901', 1);

insert into Residences (Name_Residence, ID_Address) values ('R1', 1);

insert into Levels (ID_Level, ID_Residence) values (0, 1);

insert into Apartments(Name_Apartment, ID_Level) values('Apartment A' , 1);

insert into Residents(Login_Resident, Password_Resident, Lastname_Resident, Firstname_Resident, Age_Resident, Information_Resident, ID_Apartment) values ('pierre_res','tv','Jacquet','Pierre',85,'RAS',1);


insert into Objects (Type_Object, State_Object, ID_Apartment, Mac_Object) values ('Smoke sensor', 1, 1, 'c0:b0:0c:07:ac:11');
insert into ObjectsSmoke (ID_ObjectSmoke, Sensibility_ObjectSmoke) values (1,20);