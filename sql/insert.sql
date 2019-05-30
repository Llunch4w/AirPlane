create database common;
use common;

insert into takeOffPlace
    values  ("","",""),
            ("湖南","长沙","黄花机场"),
            ("吉林","长春","龙嘉机场"),
            ("江苏","南京","禄口机场"),
            ("广东","广州","白云机场"),
            ("湖北","武汉","天河机场"),
            ("四川","成都","双流机场"),
            ("辽宁","大连","周水子机场"),
            ("上海","上海","浦东机场");


insert into flight values("MF8734","厦门航空","738(中机型)","T1","周二","长春","长沙","2019-05-04 22:20","2019-05-05 14:10");
insert into tickets_info values("MF8734",50,2000.00,800.00,1200.00,4.2);
insert into status values("MF8734","计划",false,false,false);

insert into flight values("MF1234","厦门航空","748(中机型)","T1","周二","长春","长沙","2019-04-14 23:10","2019-04-15 8:30");
insert into tickets_info values("MF1234",50,2400.00,560.00,1140.00,9.1);
insert into status values("MF1234","计划",true,false,false);
insert into transport values("MF1234","南京","2019-04-15 0:40","2019-04-15 7:0",false,0);

insert into flight values("AB2333","九元航空","534小型机","T2","周六","武汉","成都","2019-01-20 9:40","2019-01-20 11:20");
insert into tickets_info values("AB2333",50,3000.00,789.00,1340.00,3.3);
insert into status values("AB2333","计划",false,false,false);

insert into orders values("11944202553","MF1234","LQ",0,"2019-05-04 20:25");

insert into ticket values("MF1234",0,"null","阿妹","1234313","123445212",1140.00,"null",6.6,false);
