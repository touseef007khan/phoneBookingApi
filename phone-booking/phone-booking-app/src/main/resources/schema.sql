drop table if exists PHONE;

create table PHONE(
  ID int not null AUTO_INCREMENT,
  MODEL varchar(100) not null,
  AVAILABILITY varchar(100) not null,
  BOOKED_BY varchar(100),
  BOOKED_AT varchar(100),
  PRIMARY KEY ( ID )
);