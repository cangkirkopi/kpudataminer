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

/*
script for query
select sum(da_count1) as suara_prabowo,(sum(da_count1)/sum(da_count1+da_count2))*100
as prabowo,sum(da_count2) as suara_jokowi, (sum(da_count2)/sum(da_count1+da_count2))*100  as jokowi , count(*) as form_terverifikasi,
(select count(*) from da1) as total_form  from da1 where da_status>0
*/
