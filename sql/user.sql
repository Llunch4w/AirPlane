create database user;

create table userLogin
    (
        id varchar(10),
        password varchar(20)
    );

insert into userLogin
    values (2019527708,123),
        (2019527630,456);

create table userDetail
    (
        id varchar(10),
        idCard varchar(20),
        name varchar(20),
        phone varchar(20),
        type varchar(10)
    );

