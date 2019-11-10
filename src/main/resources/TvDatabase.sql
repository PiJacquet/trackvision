#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

# 911
# Afin d'appliquer le modèle physique des données, nous avons généré le code sql depuis le MCD automatiquement.
# Sur ce script, nous avons supprimé de toutes les tables capteur[Autre] (CapteurFumee, CapteurHygro, CapteurOuverture...), la clé étrangère
# vers la table capteur et défini l'identifiant de chacune de ces tables comme clé étrangère vers l'identifiant de la table capteurs.

#------------------------------------------------------------
# Table: Adresses
#------------------------------------------------------------

CREATE TABLE Adresses(
        ID_Addresse        Int  Auto_increment  NOT NULL ,
        Numero_Addresse    Int NOT NULL ,
        Rue_Adresse        Varchar (35) NOT NULL ,
        Ville_Adresse      Varchar (35) NOT NULL ,
        CodePostal_Adresse Varchar (5) NOT NULL ,
        Pays_Adresse       Varchar (30) NOT NULL
	,CONSTRAINT Adresse_PK PRIMARY KEY (ID_Addresse)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Residences
#------------------------------------------------------------

CREATE TABLE Residences(
        ID_Residence  Int  Auto_increment  NOT NULL ,
        Nom_Residence Varchar (50) NOT NULL ,
        ID_Addresse   Int NOT NULL
	,CONSTRAINT Residences_PK PRIMARY KEY (ID_Residence)

	,CONSTRAINT Residences_Adresse_FK FOREIGN KEY (ID_Addresse) REFERENCES Adresses(ID_Addresse)
	,CONSTRAINT Residences_Adresse_AK UNIQUE (ID_Addresse)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Residents
#------------------------------------------------------------

CREATE TABLE Residents(
        ID_Resident          Int  Auto_increment  NOT NULL ,
        NumChambre_Resident  Int NOT NULL ,
        Nom_Resident         Varchar (30) NOT NULL ,
        Prenom_Resident      Varchar (30) NOT NULL ,
        Age_Resident         Int NOT NULL ,
        Sexe_Resident        Bool NOT NULL ,
        Information_Resident Varchar (300) NOT NULL COMMENT "Informations médicales sur le résident (antécédents, traitement actuel)"  ,
        ID_Residence         Int NOT NULL
	,CONSTRAINT Residents_PK PRIMARY KEY (ID_Resident)

	,CONSTRAINT Residents_Residences_FK FOREIGN KEY (ID_Residence) REFERENCES Residences(ID_Residence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personnels
#------------------------------------------------------------
CREATE TABLE Personnels(
        ID_Personnel          Int  Auto_increment  NOT NULL ,
        Identifiant_Personnel Varchar (20) NOT NULL ,
        MotDePasse_Personnel  Varchar (40) NOT NULL ,
        Nom_Personnel         Varchar (30) NOT NULL ,
        Prenom_Personnel      Varchar (30) NOT NULL ,
        Fonction_Personnel    Varchar (30) NOT NULL ,
        Numero_Personnel      Varchar (12) NOT NULL ,
        ID_Addresse           Int NOT NULL
	,CONSTRAINT Personnel_PK PRIMARY KEY (ID_Personnel)

	,CONSTRAINT Personnel_Adresse_FK FOREIGN KEY (ID_Addresse) REFERENCES Adresses(ID_Addresse)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Etages
#------------------------------------------------------------
CREATE TABLE Etages(
        ID_Etage    Int  Auto_increment  NOT NULL ,
        Image_Etage LONGBLOB, 
        Niveau_Etage Int NOT NULL ,
        ID_Residence      Int NOT NULL
	,CONSTRAINT Etage_PK PRIMARY KEY (ID_Etage)
	,CONSTRAINT Etage_Residences_FK FOREIGN KEY (ID_Residence) REFERENCES Residences(ID_Residence)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Emplacements
#------------------------------------------------------------
CREATE TABLE Emplacements(
        ID_Emplacement   Int  Auto_increment  NOT NULL ,
        Nom_Emplacement  Varchar (30) NOT NULL ,
        ID_Etage      Int NOT NULL,
        X Int NOT NULL,
		Y Int NOT NULL,
		Width Int NOT NULL,
		Height Int NOT NULL
	,CONSTRAINT Emplacement_PK PRIMARY KEY (ID_Emplacement)
	,CONSTRAINT Emplacement_Etage_FK FOREIGN KEY (ID_Etage) REFERENCES Etages(ID_Etage)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Referentiel_Capteurs
#------------------------------------------------------------
CREATE TABLE Referentiel_Capteurs(
		Type_Capteur Varchar(30) NOT NULL,
        CONSTRAINT Referentiel_Capteurs PRIMARY KEY (Type_Capteur)
);


#------------------------------------------------------------
# Table: Capteurs
#------------------------------------------------------------
CREATE TABLE Capteurs(
        ID_Capteur     Int  Auto_increment  NOT NULL ,
        Type_Capteur   Varchar(30) NOT NULL COMMENT "Enumeration des différents type de capteur à définir"  ,
        Etat_Capteur   Bool NOT NULL ,
        ID_Emplacement Int NOT NULL ,
        Mac_Capteur VarChar(17) 
	,CONSTRAINT Capteurs_PK PRIMARY KEY (ID_Capteur)
	,CONSTRAINT Capteurs_Type_FK FOREIGN KEY (Type_Capteur) REFERENCES Referentiel_Capteurs(Type_Capteur) 
	,CONSTRAINT Capteurs_Emplacement_FK FOREIGN KEY (ID_Emplacement) REFERENCES Emplacements(ID_Emplacement)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Notifications
#------------------------------------------------------------

CREATE TABLE Notifications(
        ID_Notification        Int  Auto_increment  NOT NULL ,
        Niveau_Notification    Int NOT NULL ,
        Date_Notification      TIMESTAMP NOT NULL ,
        Message_Notification   Varchar (60) ,
        Numerique_Notification Float ,
        ID_Capteur             Int NOT NULL
	,CONSTRAINT Notifications_PK PRIMARY KEY (ID_Notification)

	,CONSTRAINT Notifications_Capteurs_FK FOREIGN KEY (ID_Capteur) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursFumee
#------------------------------------------------------------

CREATE TABLE CapteursFumee(
        ID_CapteurFumee          Int NOT NULL COMMENT "Clé étrangère vers ID_Capteur de Capteur"  ,
        Sensibilite_CapteurFumee Int NOT NULL ,
        Intervalle_CapteurFumee  Int NOT NULL
	,CONSTRAINT CapteurFumee_PK PRIMARY KEY (ID_CapteurFumee)
	,CONSTRAINT CapteurFumee_Capteurs_FK FOREIGN KEY (ID_CapteurFumee) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursTemperature
#------------------------------------------------------------

CREATE TABLE CapteursTemperature(
        ID_CapteurTemperature           Int NOT NULL ,
        Reference_CapteurTemperature    Float NOT NULL ,
        ToleranceMin_CapteurTemperature Float NOT NULL ,
        ToleranceMax_CapteurTemperature Float NOT NULL 
	,CONSTRAINT CapteurTemperature_PK PRIMARY KEY (ID_CapteurTemperature)
	,CONSTRAINT CapteurTemperature_Capteurs_FK FOREIGN KEY (ID_CapteurTemperature) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursOuverture
#------------------------------------------------------------

CREATE TABLE CapteursOuverture(
        ID_CapteurOuverture    Int NOT NULL ,
        Tempo_CapteurOuverture Int
	,CONSTRAINT CapteurOuverture_PK PRIMARY KEY (ID_CapteurOuverture)
	,CONSTRAINT CapteurOuverture_Capteurs_FK FOREIGN KEY (ID_CapteurOuverture) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursHygro
#------------------------------------------------------------

CREATE TABLE CapteursHygro(
        ID_CapteurHygro    Int NOT NULL ,
        Tempo_CapteurHygro Int
	,CONSTRAINT CapteurHygro_PK PRIMARY KEY (ID_CapteurHygro)
	,CONSTRAINT CapteurHygro_Capteurs_FK FOREIGN KEY (ID_CapteurHygro) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteurPresence
#------------------------------------------------------------

CREATE TABLE CapteursPresence(
        ID_CapteurPresence    Int NOT NULL ,
        Tempo_CapteurPresence Int ,
        ID_Capteur            Int NOT NULL
	,CONSTRAINT CapteurPresence_PK PRIMARY KEY (ID_CapteurPresence)
	,CONSTRAINT CapteurPresence_Capteurs_FK FOREIGN KEY (ID_CapteurPresence) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursAppel
#------------------------------------------------------------

CREATE TABLE CapteursAppel(
        ID_CapteurAppel           Int NOT NULL ,
        NiveauAlerte_CapteurAppel Int NOT NULL ,
        ID_Capteur                Int NOT NULL
	,CONSTRAINT CapteurAppel_PK PRIMARY KEY (ID_CapteurAppel)
	,CONSTRAINT CapteurAppel_Capteurs_FK FOREIGN KEY (ID_CapteurAppel) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CapteursRFID
#------------------------------------------------------------

CREATE TABLE CapteurRFID(
        ID_CapteurRFID              Int NOT NULL ,
        Tempo_CapteurRFID           Int ,
        LectureIdentite_CapteurRFID Bool NOT NULL
	,CONSTRAINT CapteurRFID_PK PRIMARY KEY (ID_CapteurRFID)
	,CONSTRAINT CapteurRFID_Capteurs_FK FOREIGN KEY (ID_CapteurRFID) REFERENCES Capteurs(ID_Capteur)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: dépendre de
#------------------------------------------------------------

CREATE TABLE dependre_de(
        ID_Personnel Int NOT NULL ,
        ID_Residence Int NOT NULL
	,CONSTRAINT dependre_de_PK PRIMARY KEY (ID_Personnel,ID_Residence)

	,CONSTRAINT dependre_de_Personnel_FK FOREIGN KEY (ID_Personnel) REFERENCES Personnels(ID_Personnel)
	,CONSTRAINT dependre_de_Residences0_FK FOREIGN KEY (ID_Residence) REFERENCES Residences(ID_Residence)
)ENGINE=InnoDB;

insert into Referentiel_Capteurs(Type_Capteur) values('Capteur de température');
insert into Referentiel_Capteurs(Type_Capteur) values('Capteur de fumée');
insert into Referentiel_Capteurs(Type_Capteur) values('Capteur ouverture');
insert into Referentiel_Capteurs(Type_Capteur) values('Capteur hygrométrique');
insert into Referentiel_Capteurs(Type_Capteur) values('Capteur de présence');
insert into Referentiel_Capteurs(Type_Capteur) values('Capteur appel');
insert into Referentiel_Capteurs(Type_Capteur) values('Bracelet RFID');

insert into Adresses (Numero_Addresse, Rue_Adresse, Ville_Adresse, CodePostal_Adresse, Pays_Adresse) values ('15', 'Rue de Hella' , 'Paris' , '75000', 'France');

insert into Personnels (Identifiant_Personnel, MotDePasse_Personnel, Nom_Personnel, Prenom_Personnel, Fonction_Personnel, Numero_Personnel, ID_Addresse) values ('Justin911', '911', 'Aguesse', 'Justin', 'Admin', '0607080901', 1);

insert into Residences (Nom_Residence, ID_Addresse) values ('R1', 1);

insert into Etages (Niveau_Etage, ID_Residence) values (0, 1);
insert into Etages (Niveau_Etage, ID_Residence) values (1, 1);

insert into Emplacements(Nom_Emplacement, ID_Etage, X, Y, width, height) values('Couloir' , 1, 125, 256, 12, 12);

insert into Capteurs (Type_Capteur, Etat_Capteur, ID_Emplacement) values ('Capteur de fumée', 1, 1);