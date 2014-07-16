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
import com.cangkirkopi.kpu.db.model.Da1;
import com.cangkirkopi.kpu.db.model.Kabupaten;
import com.cangkirkopi.kpu.db.model.Kecamatan;

public class Da1Proc {
	private Properties propLog= new Properties();
	private DataController dataControl;
	private static final int connTimeout=100000; //in miliseconds
	private static final Logger logger = Logger.getLogger(
			MasterPopulator.class.getName());
	private final String URL_DA1="http://pilpres2014.kpu.go.id/da1.php";
	public Da1Proc(){
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
		dataControl=new DataController();
	}
	public void da1() throws IOException{
		logger.info("populating DA1...");
	 
		List<Da1> daList = dataControl.getDa1List();
		logger.info("Data Da1 Count :"+daList.size());
		for (Da1 da1 : daList){
			 String newURL1=URL_DA1+"?cmd=select&grandparent="+da1.getK_code() +"&parent="+da1.getKc_code();
			 logger.info("connecting to "+newURL1+", data da1 dari "+da1.getKc_code()+" please wait...");
			 Document doc = Jsoup.connect(newURL1).timeout(connTimeout).get();
			 
			 Elements   tablelist=doc.select("table");
			 Elements trList=tablelist.get(2).select("tr");
			 Element  pick1List=trList.get(3);
			 Elements td1List=pick1List.select("td");
			 Element  pick2List=trList.get(4);
			 Elements td2List=pick2List.select("td");
			 String lastTd1=td1List.get(td1List.size()-1).text();
			 String lastTd2=td2List.get(td2List.size()-1).text();
			// logger.info("table data :"+lastTd1+":"+lastTd2);
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
				 logger.info("Saving "+da1.getKc_code()+" -> "+count1+":"+count2 );
				 Da1 da1data = new Da1(da1.getKc_code(),da1.getK_code(),1,count1,count2);
				 dataControl.saveOrUpdateDa1(da1data);
			 }
		   
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Da1Proc daProc= new Da1Proc();
		try {
			daProc.da1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
