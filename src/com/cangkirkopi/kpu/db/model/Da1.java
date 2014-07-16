package com.cangkirkopi.kpu.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "da1" )
public class Da1 {

	@Id
	@Column(name = "kc_code")
	String kc_code;
	@Column(name = "k_code")
	String k_code;
	@Column(name = "da_status")
	int da_status;
	@Column(name = "da_count1")
	double da_count1;
	@Column(name = "da_count2")
	double da_count2;
	public Da1(){
		
	}
	public Da1(String kc_code,String k_code,int da_status, double count1, double count2){
		this.kc_code=kc_code;
		this.k_code=k_code;
		this.da_status=da_status;
		this.da_count1=count1;
		this.da_count2=count2;
	}
	public String getKc_code() {
		return kc_code;
	}
	public void setKc_code(String kc_code) {
		this.kc_code = kc_code;
	}
	public String getK_code() {
		return k_code;
	}
	public void setK_code(String k_code) {
		this.k_code = k_code;
	}
	public int getDa_status() {
		return da_status;
	}
	public void setDa_status(int da_status) {
		this.da_status = da_status;
	}
	public double getDa_count1() {
		return da_count1;
	}
	public void setDa_count1(double da_count1) {
		this.da_count1 = da_count1;
	}
	public double getDa_count2() {
		return da_count2;
	}
	public void setDa_count2(double da_count2) {
		this.da_count2 = da_count2;
	}
	@Override
	public String toString() {
		return "Da1 [kc_code=" + kc_code + ", k_code=" + k_code
				+ ", da_status=" + da_status + ", da_count1=" + da_count1
				+ ", da_count2=" + da_count2 + "]";
	}
	 
	
	
}
