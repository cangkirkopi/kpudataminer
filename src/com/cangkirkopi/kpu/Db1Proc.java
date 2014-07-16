package com.cangkirkopi.kpu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cangkirkopi.kpu.db.controller.DataController;
import com.cangkirkopi.kpu.db.model.Db1;

public class Db1Proc {
	private Properties propLog= new Properties();
	private DataController dbtaControl;
	private static final int connTimeout=100000; //in miliseconds
	private static final Logger logger = Logger.getLogger(
			MasterPopulator.class.getName());
	private final String URL_DB1="http://pilpres2014.kpu.go.id/db1.php";
	public Db1Proc(){
		propLog= new Properties();
		try {
		
			propLog.load(new FileInputStream("prop/log4j.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("Could not found any properties files on folder /prop");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException, please set privilege on folder /prop");
			e.printStackTrace();
		}
		PropertyConfigurator.configure(propLog);
		dbtaControl=new DataController();
	}
	public void db1() throws IOException{
		logger.info("populating DB1...");
	 
		List<Db1> dbList = dbtaControl.getDb1List();
		logger.info("Data Db1 Count :"+dbList.size());
		for (Db1 db1 : dbList){
			 String newURL1=URL_DB1+"?cmd=select&grandparent="+db1.getP_code() +"&parent="+db1.getK_code() ;
			 logger.info("connecting to "+newURL1+", data db1 dari "+db1.getP_code()+" please wait...");
			 Document doc = Jsoup.connect(newURL1).timeout(connTimeout).get();
			 
			 Elements   tablelist=doc.select("table");
			 
			 Elements trList=tablelist.get(2).select("tr");
			 Element  pick1List=trList.get(3);
			 Elements td1List=pick1List.select("td");
			 Element  pick2List=trList.get(4);
			 Elements td2List=pick2List.select("td");
			 String lastTd1=td1List.get(td1List.size()-1).text();
			 String lastTd2=td2List.get(td2List.size()-1).text();
			// logger.info("table dbta :"+lastTd1+":"+lastTd2);
			 double count1=0;
			 double count2=0;
			 if (lastTd1.equals("0") && lastTd2.equals("0")){
				 //
			 }
			 else {
				 try {
				 count1=Double.parseDouble(lastTd1);
				 count2=Double.parseDouble(lastTd2);
				 }
				 catch (Exception e ){
					 e.printStackTrace();
				 }
			 }
			 if (count1!=0 && count2!=0){
				 logger.info("Saving "+db1.getK_code()+" -> "+count1+":"+count2 );
				 Db1 db1data = new Db1(db1.getK_code(),db1.getP_code(),1,count1,count2);
				 dbtaControl.saveOrUpdateDb1(db1data);
			 }
		   
		}
	}
	public static void main(String[] args) {
		Db1Proc dbProc= new Db1Proc();
		try {
			dbProc.db1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
