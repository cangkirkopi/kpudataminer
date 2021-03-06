create table propinsi(
	p_code	varchar(20),
	p_name varchar(125),
	constraint propinsi_pk primary key (p_code)
);
create table kabupaten(
	k_code	varchar(20),
	k_name	varchar(125),
	p_code	varchar(20),
	constraint kabupaten_pk primary key (k_code)
);
create index kab_idx1 on kabupaten(p_code);

create table kecamatan(
	kc_code	varchar(20),
	kc_name	varchar(125),
	k_code	varchar(20),
	p_code	varchar(20),
	constraint kecamatan_pk primary key (kc_code)
);
create index kec_idx1 on kecamatan(p_code,k_code);

create table da1(
	kc_code	varchar(20),
	k_code	varchar(20),
	da_status smallint default 0,--0=not ver, 1=ver
	
	da_count1 numeric(20) default 0,
	da_count2 numeric(20) default 0,
	
	constraint da1_pk primary key(kc_code)
);
create table db1(
	 
	k_code	varchar(20),
	p_code	varchar(20),
	db_status smallint default 0,--0=not ver, 1=ver
	
	db_count1 numeric(20) default 0,
	db_count2 numeric(20) default 0,
	
	constraint db1_pk primary key(k_code)
);
create or replace view da1_view
as select p.p_code,p.p_name,k.k_code,k.k_name,
kc.kc_code,kc.kc_name, d.da_status,d.da_count1, d.da_count2, (select count(*) from da1) as total_form
from da1 d, kecamatan kc, kabupaten k, propinsi p
where d.kc_code=kc.kc_code and kc.k_code=k.k_code and k.p_code=p.p_code;

create or replace view da1_view2
as
 (select p_code,p_name, sum(da_count1) suara_prabowo,round((sum(da_count1)/sum(da_count1+da_count2))*100,2)
as prabowo,sum(da_count2) as suara_jokowi, round((sum(da_count2)/sum(da_count1+da_count2))*100,2) as jokowi,(select 
count(*) as form_verified from da1_view d2 where   d2.da_status=1 and d2.p_name=d1.p_name)::numeric as form_inc,count(*)::numeric as total_form1 from da1_view d1
group by p_code, p_name 
order by p_code );


create or replace view db1_view
as select p.p_code,p.p_name,k.k_code,k.k_name,
  d.db_status,d.db_count1, d.db_count2, (select count(*) from db1) as total_form
from db1 d,   kabupaten k, propinsi p
where d.k_code=k.k_code and k.p_code=p.p_code;

create or replace view db1_view2
as
  select p_code,p_name, sum(db_count1) suara_prabowo, case when (sum(db_count1+db_count2)<>0)
						then round((sum(db_count1)/sum(db_count1+db_count2))*100,2)
						else 0 end
as prabowo,sum(db_count2) as suara_jokowi, case when (sum(db_count1+db_count2)<>0)
						then round((sum(db_count2)/sum(db_count1+db_count2))*100,2)
						else 0 end  as jokowi,(select 
count(*) as form_verified from db1_view d2 where   d2.db_status=1 and d2.p_name=d1.p_name)::numeric as form_inc,count(*)::numeric as total_form1 from db1_view d1
 
group by p_code, p_name 
order by p_name;

/*

select *,round((form_inc/total_form1)*100,2) as form_percent from da1_view2
order by p_name

select   t.p_name, t.suara_prabowo, round(prabowo,2) as prabowo, t.suara_jokowi, round(jokowi,2) as jokowi,
t.form_verified,t.total_form1,round((t.form_verified::numeric/t.total_form1::numeric)*100,2) as persentase_verified from da1_view2 t

script for query
select sum(da_count1) as suara_prabowo,(sum(da_count1)/sum(da_count1+da_count2))*100
as prabowo,sum(da_count2) as suara_jokowi, (sum(da_count2)/sum(da_count1+da_count2))*100  as jokowi , count(*) as form_terverifikasi,
(select count(*) from da1) as total_form  from da1 where da_status>0

select   t.*,(t.form_verified::numeric/t.total_form1::numeric)*100 as persentase_verified from da1_view2 t

select p_name, sum(da_count1) suara_prabowo,(sum(da_count1)/sum(da_count1+da_count2))*100
as prabowo,sum(da_count2) as suara_jokowi, (sum(da_count2)/sum(da_count1+da_count2))*100  as jokowi,(select 
count(*) as form_verified from da1_view d2 where   d2.da_status=1 and d2.p_name=d1.p_name) as form_verified,count(*) as total_form from da1_view d1
group by p_name 
order by p_name


select p_name, sum(da_count1) suara_prabowo,(sum(da_count1)/sum(da_count1+da_count2))*100
as prabowo,sum(da_count2) as suara_jokowi, (sum(da_count2)/sum(da_count1+da_count2))*100  as jokowi,(select 
count(*) as form_verified from da1_view d2 where   d2.da_status=1 and d2.p_name=d1.p_name) as form_verified,count(*) as total_form from da1_view d1
group by p_name 
order by p_name
*/
