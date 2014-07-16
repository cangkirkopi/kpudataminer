package com.cangkirkopi.kpu.db.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "propinsi" ) 
public class Propinsi {
	@Id
	@Column(name = "p_code")
	String p_code;
	@Column(name = "p_name")
	String p_name;
	public Propinsi(){
		
	}
	public Propinsi(String code, String name){
		this.p_code=code;
		this.p_name=name;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

}
