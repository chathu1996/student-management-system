create database studentManagementSystem;
use studentManagementSystem;

create table Admin(
  aid int(100) primary key not NULL auto_increment,
  email varchar(255) not null,
  password varchar(255) not null
);

create table Student(
  sid varchar(100) primary key not null,
  studentName varchar(255) not null,
  studentTelNum varchar(255),
  studentEmail varchar(255),
  studentAddress varchar(255)
);

create table Course(
  cid varchar(100) primary key not null,
  duration varchar(100),
  name varchar(200),
  price double(100),
  status varchar(100)
);

create table Payment (
  pid varchar(100) primary key not null,
  paymentAmount double(100),
  cvc int(100),
  cardExpire date,
  cardNumber int (100)
);

ALTER table admin
ADD name varchar(255);

drop table payment;

create table RegistrationDetails(
    rid varchar(100) primary key not null,
    sid varchar(100),
    studentName varchar(255),
    cid varchar(100),
    name varchar(200),
    status varchar(100),
    price double(100),
    FOREIGN key(sid) references student(sid),
    FOREIGN KEY(cid) REFERENCES course(cid)
);