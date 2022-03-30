insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100001','AHASS Jakarta','H23','021-56555430','Jl. Gatot Soebroto Kav. 1','ACTIVE','13500001');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100002','AHASS Cibeureum','H123','022-84644536','Jl. Cibeureum No. 26','ACTIVE','13500982');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100003','AHASS Kuningan','H23','022-74644536','Jl. Kuningan No 1 ','ACTIVE','13500983');
insert into mst_dealer(dealer_code, dealer_name, dealer_class, telp_number, alamat, dealer_status, dealer_ext_code)
values ('100004','AHASS Cimahi','H123','022-55644536','Jl. Cibeureum No. 26','INACTIVE','13500984');

insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('0','SUPER ADMIN','100002','0','GTLK','admin@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203192010570001111','AJANG SUDRAJAT','100002','0','GTLK','ajangsudrajat@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000001','NANA SUYADI','100002','202203192010570001111','GTPR','nana.suyadi2000@gmail.com','ACTIVE');
insert into mst_sales(sales_id, sales_name,dealer_code, supervisor_id, sales_gender, sales_email, sales_status)
values('202203201010570000011','MAMAN SAYUTI','100002','202203192010570001111','GTLK','maman_isgood@gmail.com','ACTIVE');