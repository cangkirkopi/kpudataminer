package com.cangkirkopi.kpu.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "kabupaten" ) 
public class Kabupaten {
	
	@Id
	@Column(name = "k_code")
	String k_code;
	@Column(name = "k_name")
	String k_name;
	@Column(name = "p_code")
	String p_code;
	public Kabupaten(){
		
	}
	public Kabupaten(String code, String name,String p_code){
		this.k_code=code;
		this.k_name=name;
		this.p_code=p_code;
	}
	public String getK_code() {
		return k_code;
	}
	public void setK_code(String k_code) {
		this.k_code = k_code;
	}
	public String getK_name() {
		return k_name;
	}
	public void setK_name(String k_name) {
		this.k_name = k_name;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	
}
