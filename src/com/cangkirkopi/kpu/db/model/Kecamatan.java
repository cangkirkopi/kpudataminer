package com.cangkirkopi.kpu.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "kecamatan" ) 
public class Kecamatan {
	
	@Id
	@Column(name = "kc_code")
	String kc_code;
	@Column(name = "kc_name")
	String kc_name;
	@Column(name = "p_code")
	String p_code;
	@Column(name = "k_code")
	String k_code;
	public Kecamatan(){
		
	}
	public Kecamatan(String code, String name,String p_code,String k_code){
		this.kc_code=code;
		this.kc_name=name;
		this.p_code=p_code;
		this.k_code=k_code;
	}
	public String getK_code() {
		return k_code;
	}
	public String getKc_code() {
		return kc_code;
	}
	public void setKc_code(String kc_code) {
		this.kc_code = kc_code;
	}
	public String getKc_name() {
		return kc_name;
	}
	public void setKc_name(String kc_name) {
		this.kc_name = kc_name;
	}
	public void setK_code(String k_code) {
		this.k_code = k_code;
	}
	 
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	
}
