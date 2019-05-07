create database common;

create table takeOffPlace
    (
        province varchar(10),
        city varchar(10),
        airport varchar(10)
    );

insert into takeOffPlace
    values ("湖南","长沙","黄花机场"),
            ("吉林","长春","龙嘉机场"),
            ("江苏","南京","禄口机场"),
            ("","","");

create table flight
    (
        flightID varchar(10) not null,
        company varchar(30) not null,
        planeType varchar(30) not null,
        building varchar(10) not null,
        week enum('周一','周二','周三','周四','周五','周六','周日') not null,
        capcity int not null,
        remain int not null,
        kidPrice float not null,
        adultPrice float not null, 
        takeOff_place varchar(10) not null,
        trans_place varchar(10),
        arrive_place varchar(10) not null,
        takeOff_time timestamp not null,
        midArvTm timestamp,
        midLevTm timestamp,
        arrive_time timestamp not null,
        primary key(flightID)
    );

insert into flight values("MF8734","厦门航空","738(中机型)","T1","周二",
100,100,800.00,1139.00,"龙嘉","","长沙",
"2019-05-04 22:20:00","1970-01-01 10:10:00","1970-01-01 10:10:00","2019-05-04 23:55:00");
