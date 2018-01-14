create schema eTraining;

use eTraining;

create table Tirocinante(
    Email varchar(50) primary key,
    Password varchar(20) not null,
    Nome varchar(50) not null,
    Cognome varchar(50) not null,
    Matricola char(10) unique,
    AnnoIscrizione char(9) not null
);

create table FunzionarioSegreteria(
    Email varchar(50) primary key,
    Password varchar(20) not null,
    Nome varchar(50) not null,
    Cognome varchar(50) not null,
    CodiceFiscale char(16) not null
);

create table Qualifica(
    FunzionarioSegreteria varchar(50) references FunzionarioSegreteria(Email),
    Descrizione varchar(50) not null,
    primary key(FunzionarioSegreteria,Descrizione)
);

create table TutorDidattico(
    Email varchar(50) primary key,
    Password varchar(20) not null,
    Nome varchar(50) not null,
    Cognome varchar(50) not null,
    CodiceFiscale char(16) not null,
    Disponibilita boolean not null
);

create table CampoInteresse(
    TutorDidattico varchar(50) references TutorDidattico(Email),
    Descrizione varchar(50) not null,
    primary key(TutorDidattico,Descrizione)
);

create table Insegnamento(
    TutorDidattico varchar(50) references TutorDidattico(Email),
    Descrizione varchar(50) not null,
    primary key(TutorDidattico,Descrizione)
);

create table Azienda(
    Email varchar(50) primary key,
    Password varchar(20) not null,
    Nome varchar(50) not null,
    PartitaIVA char(11) not null,
    Sede varchar(100) not null,
    Citta varchar(50) not null
);

create table TitolareAzienda(
    Azienda varchar(50) references Azienda(Email),
    Nome varchar(50) not null,
    Cognome varchar(50) not null,
    CodiceFiscale char(16) not null,
    primary key(Azienda)
);

create table TutorAziendale(
    Email varchar(50) primary key,
    Azienda varchar(50) not null references Azienda(Email),
    Password varchar(20) not null,
    Nome varchar(50) not null,
    Cognome varchar(50) not null,
    CodiceFiscale char(16) not null,
    SettoreLavoro varchar(100) not null
);

create table OffertaFormativaTirocinioEsterno(
    Id integer(10) primary key AUTO_INCREMENT,
    TutorDidattico varchar(50) not null references TutorDidattico(Email),
    TutorAziendale varchar(50) not null references TutorAziendale(Email),
    Azienda varchar(50) not null references Azienda(Email),
    Tema varchar(250) not null,
    InizioTirocinio date not null,
    FineTirocinio date not null,
    Validita boolean not null,
    Status varchar(50)
);

create table Facilitazione(
    IdOffertaFormativaTirocinioEsterno integer(10) references OffertaFormativaTirocinioEsterno(Id),
    Descrizione varchar(50) not null,
    primary key(IdOffertaFormativaTirocinioEsterno,Descrizione)
);

create table Tirocinio(
    Tirocinante varchar(50) references Tirocinante(Email),
    IdOffertaFormativaTirocinioEsterno integer(10) references OffertaFormativaTirocinioEsterno(Id),
    CFU integer(2) not null,
    OreRimanenti integer(3) not null,
    Status varchar(50) not null,
    primary key(Tirocinante,IdOffertaFormativaTirocinioEsterno)
);

create table Obiettivo(
    Tirocinante varchar(50) references Tirocinante(Email),
    IdOffertaFormativaTirocinioEsterno integer(10) references OffertaFormativaTirocinioEsterno(Id),
    Descrizione varchar(50) not null,
    primary key(Tirocinante,IdOffertaFormativaTirocinioEsterno,Descrizione)
);

create table AttivitaRegistro(
    Tirocinante varchar(50) references Tirocinante(Email),
    IdOffertaFormativaTirocinioEsterno integer(10) references OffertaFormativaTirocinioEsterno(Id),
    AttivitaSvolta varchar(50) not null,
    Inizio date not null,
    Fine date not null,
    OreSvolte integer(2) not null,
    Convalida boolean not null,
    MotivazioneRifiuto varchar(500),
    primary key(Tirocinante,IdOffertaFormativaTirocinioEsterno,AttivitaSvolta)
);
