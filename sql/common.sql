create database common;

use common;

create table takeOffPlace
    (
        province varchar(10),
        city varchar(10),
        airport varchar(10)
    );

create table flight
    (
        flightID varchar(10) not null,
        company varchar(30) not null,
        planeType varchar(30) not null,
        building varchar(10) not null,
        week enum('周一','周二','周三','周四','周五','周六','周日') not null, 
        takeOff_place varchar(10) not null,     
        arrive_place varchar(10) not null,
        takeOff_time timestamp not null,    
        arrive_time timestamp not null,
        primary key(flightID)
    );

create table status
    (
        flightID varchar(10) not null,
        -- state enum('计划','值机','延误','取消','飞行','中转停靠','抵达'),
        state varchar(10) not null default "计划",
        isTrans boolean default 0,  
        isStartDelay boolean default 0,
        isCancel boolean default 0,
        foreign key(flightID)
            references flight(flightID)
    );

create table tickets_info
    (
        flightID varchar(10) not null,
        -- capacity int not null,
        remain int default 50 not null,
        topPrice float not null,
        kidPrice float not null,
        adultPrice float not null,
        discount float,--
        foreign key(flightID)
            references flight(flightID)
    );

create table normal_seats
    (
        flightID varchar(10),
        i int,
        j int,
        foreign key(flightID)
            references flight(flightID)
    );

create table top_seats
    (
        flightID varchar(10),
        loc int,
        foreign key(flightID)
            references flight(flightID)
    );


create table transport
    (
        flightID varchar(10) not null,
        trans_place varchar(10),  
        midArvTm timestamp,
        midLevTm timestamp,
        isMidDalay boolean,
        midDelayTime int,
        foreign key(flightID)
            references flight(flightID)   
    );

create table startDelay
    (
        flightID varchar(10) not null,
        delayTime int,
        delayReason varchar(100),
        foreign key(flightID)
            references flight(flightID)
    );

create table delayInform
    (
        flightID varchar(10),
        userID varchar(10),
        msg varchar(100),
        foreign key(flightID)
            references flight(flightID)
    );

create table ticket
    (
        flightID varchar(10),
        seatID int,
        orderID varchar(20),
        name varchar(10),
        IDcard varchar(18),
        phone varchar(14),
        price float,
        seatType varchar(10),
        discount float,
        used boolean default false,
        foreign key(flightID)
            references flight(flightID)
    );

create table orders
    (
        orderID varchar(20),
        flightID varchar(10),
        userID varchar(10),
        flyPersonNum int,
        buyTime timestamp,
        foreign key(flightID)
            references flight(flightID)
    );


