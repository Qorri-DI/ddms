insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100001','AHASS Jakarta','H23','021-56555430','Jl. Gatot Soebroto Kav. 1','ACTIVE','13500001');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100002','AHASS Cibeureum','H123','022-84644536','Jl. Cibeureum No. 26','ACTIVE','13500982');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100003','AHASS Kuningan','H23','022-74644536','Jl. Kuningan No 1 ','ACTIVE','13500983');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100004','AHASS Cimahi','H123','022-55644536','Jl. Cibeureum No. 26','INACTIVE','13500984');

insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000001','CBR 150 2019','100001','10','RED','ACTIVE',25000000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000002','CBR 150 2019','100001','20','BLACK','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000003','CBR 150 2019','100001','15','YELLOW','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000004','CBR 150 2019','100001','15','WHITE','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000005','Beat 150 2021','100001','15','WHITE','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000006','Beat 150 2021','100001','15','BLACK','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000007','Beat 150 2021','100001','15','RED','ACTIVE',25500000);
insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost)
values ('202203201010570000008','Beat 150 2021','100001','15','GRAY','ACTIVE',25500000);

insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('0','SUPER ADMIN','100002','0','GTLK','admin@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203192010570001111','AJANG SUDRAJAT','100002','0','GTLK','ajangsudrajat@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000001','NANA SUYADI','100002','202203192010570001111','GTPR','nana.suyadi2000@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000002','MAMAN SAYUTI 1','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000003','MAMAN SAYUTI 2','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000004','MAMAN SAYUTI 3','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000005','MAMAN SAYUTI 4','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000006','MAMAN SAYUTI 5','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000007','MAMAN SAYUTI 6','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000008','MAMAN SAYUTI 7','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000009','MAMAN SAYUTI 8','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000010','MAMAN SAYUTI 9','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000011','MAMAN SAYUTI 10','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000012','MAMAN SAYUTI 11','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000013','MAMAN SAYUTI 12','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000014','MAMAN SAYUTI 13','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000015','MAMAN SAYUTI 14','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000016','MAMAN SAYUTI 15','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000017','MAMAN SAYUTI 16','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');

insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005001','Risky Febiyanto 1','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005002','Risky Febiyanto 2','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005003','Risky Febiyanto 3','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005004','Risky Febiyanto 4','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005005','Risky Febiyanto 5','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005006','Risky Febiyanto 6','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005007','Risky Febiyanto 7','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005008','Risky Febiyanto 8','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005009','Risky Febiyanto 9','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');
insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_status)
values('202202181010570005010','Risky Febiyanto 10','100002','GTLK','5104154712930002','5104154712930003','rizsky.fe_biyan@gmail.com','Jl. Turunan Pojok No. 92','ACTIVE');

insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 1','100001',now(),now(),'1','0','ACTIVE');
insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 2','100001',now(),now(),'2','1','ACTIVE');
insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 3','100001',now(),now(),'3','2','ACTIVE');
insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 4','100001',now(),now(),'4','3','ACTIVE');
insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 5','100001',now(),now(),'5','4','ACTIVE');
insert into mst_ppn(description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status)
values('ppn 6','100001',now(),now(),'6','5','ACTIVE');

create or replace view vw_mst_sales as select * from mst_sales;
create or replace view vw_mst_customer as select * from mst_customer;
--
--insert into trx_order (order_id,unit_id,dealer_code,sales_id,customer_id,minimum_payment,
--total_value,order_value,offtheroad_value,ppn,plat_nomor,nomor_mesin,
--nomor_rangka,is_leasing,payment_status,unit_status) values
--('1', -- order id
--'202203201010570000001', -- unit id
--'100002',-- dealer code
--'202203192010570001111',-- sales id
--'202202181010570005001',-- customer id
--100000.0, --minimum payment
--14500000.0, -- total value
--1500000.0, --order value
--15000000.0,-- off the road
--500000.0,-- discount
--14486.0,--ppn
--'B 1234 SOQ',
--'KF21E 1400176',
--'KF2111LK400568',
--'YES',
--'PARTIAL PAID',
--'IN_STOCK')