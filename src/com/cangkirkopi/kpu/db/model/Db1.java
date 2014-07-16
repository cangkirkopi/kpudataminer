package com.cangkirkopi.kpu.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "db1" )
public class Db1 {

	@Id
	@Column(name = "k_code")
	String k_code;
	@Column(name = "p_code")
	String p_code;
	
	@Column(name = "db_status")
	int db_status;
	@Column(name = "db_count1")
	double db_count1;
	@Column(name = "db_count2")
	double db_count2;
	public Db1(){
		
	}
	public Db1(String k_code,String p_code,int db_status, double count1, double count2){
		 
		this.k_code=k_code;
		this.p_code=p_code;
		this.db_status=db_status;
		this.db_count1=count1;
		this.db_count2=count2;
	}
	 
	public String getK_code() {
		return k_code;
	}
	public void setK_code(String k_code) {
		this.k_code = k_code;
	}
	public int getDb_status() {
		return db_status;
	}
	public void setDb_status(int db_status) {
		this.db_status = db_status;
	}
	public double getDb_count1() {
		return db_count1;
	}
	public void setDb_count1(double db_count1) {
		this.db_count1 = db_count1;
	}
	public double getDb_count2() {
		return db_count2;
	}
	public void setDb_count2(double db_count2) {
		this.db_count2 = db_count2;
	}
	@Override
	public String toString() {
		return "Db1 [k_code=" + k_code + ", p_code=" + p_code + ", db_status="
				+ db_status + ", db_count1=" + db_count1 + ", db_count2="
				+ db_count2 + "]";
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	 
	 
	
	
}
