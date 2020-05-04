
insert into member(city,street,zipcode,birth,email,name,password,phone,role) values('서울','광진구','211-1','1995-11-29','yusa2@naver.com','전현우','123','010-9259-1556','PARENT');
insert into member(city,street,zipcode,birth,email,name,password,phone,role) values('경기','일산','211-1','1995-11-29','yusa2@naver.com','이다은','123','010-9259-1556','PARENT');
insert into member(city,street,zipcode,birth,email,name,password,phone,role) values('경기','수원','211-1','1995-11-29','yusa2@naver.com','황승환','123','010-9259-1556','PARENT');

insert into hospital(city,street,zipcode,hospital_name,op_time,status,tel,member_id) values('서울','광진구','200-16','현우병원','09~24','OPEN','111-111',1);
insert into hospital(city,street,zipcode,hospital_name,op_time,status,tel,member_id) values('경기','일산','200-16','다은병원','09~24','OPEN','111-111',2);
insert into hospital(city,street,zipcode,hospital_name,op_time,status,tel,member_id) values('경기','수원','200-16','승환병원','09~24','OPEN','111-111',3);

insert into product(ftype, name, price, status,hospital_id) values('V', '비타민종류','10000','SALE','1');
insert into product(ftype, name, price, status,hospital_id) values('V', '비타민종류','1000','SALE','2');
insert into product(ftype, name, price, status,hospital_id) values('V', '비타민종류','100','SALE','3');
insert into product(ftype, name, price, status,hospital_id) values('V', '뼈다귀','300','SALE','3');
insert into product(ftype, name, price, status,hospital_id) values('V', '뼈다귀','300','SALE','2');


