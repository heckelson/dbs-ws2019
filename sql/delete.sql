-- Musikverein delete.sql

drop table proben cascade constraints;
drop table Termin cascade constraints;
drop table spielen cascade constraints;
drop table Musikstil cascade constraints;
drop table Musikgruppe cascade constraints;
drop table Instrument cascade constraints;
drop table Musikerauszeichnung cascade constraints;
drop table Musiker cascade constraints;
drop table befreundet cascade constraints;
drop table Mitglied cascade constraints;
drop table Vereinsauszeichnung cascade constraints;
drop table Musikverein cascade constraints;
drop table Ausstattung cascade constraints;
drop table Raum cascade constraints;
drop table Gebaeude cascade constraints;
drop table Adresse cascade constraints;

drop sequence mitgliedsnr_sequence;

