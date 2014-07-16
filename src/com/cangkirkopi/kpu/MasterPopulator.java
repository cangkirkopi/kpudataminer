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
import com.cangkirkopi.kpu.db.model.Kabupaten;
import com.cangkirkopi.kpu.db.model.Kecamatan;
import com.cangkirkopi.kpu.db.model.Propinsi;

 

public class MasterPopulator {
	private Properties propLog= new Properties();
	private DataController dataControl;
	private static final int connTimeout=100000; //in miliseconds
	private static final Logger logger = Logger.getLogger(
			MasterPopulator.class.getName());
	private final String URL_DA1="http://pilpres2014.kpu.go.id/da1.php";
	public MasterPopulator(){
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
	public void kecamatanPopulator() throws IOException{
		logger.info("populating kecamatan...");
		 
		//get propinsi list first
		 
		List<Kabupaten> kabDataList = dataControl.getKabList();
		for (Kabupaten kab : kabDataList){
			 String newURL1=URL_DA1+"?cmd=select&grandparent="+kab.getP_code()+"&parent="+kab.getK_code();
			 logger.info("connecting to "+newURL1+", data kecamatan dari kabupaten :"+kab.getK_name()+" please wait...");
			 Document doc = Jsoup.connect(newURL1).timeout(connTimeout).get();
		 
			 Element   parent=doc.getElementsByAttributeValue("name", "wilayah_id").get(0);
		  	 
			 Elements kecElList  = parent.getElementsByTag("option");
			 for (Element kecEl : kecElList) {
				  String optValue = kecEl.attr("value");
				  String optLabel = kecEl.text();
				  if (optValue.length()>=1){
					  logger.info(optValue+":"+optLabel+":"+kab.getK_name());
					  Kecamatan kec= new Kecamatan(optValue.trim(),optLabel.trim(),kab.getP_code(),kab.getK_code());
					  dataControl.saveKec(kec,  null);
				  
				  }
			 }
			 
		}
		 
	}
	public void kabupatenPopulator() throws IOException{
		logger.info("populating kabupaten...");
		 
		//get propinsi list first
		 
		List<Propinsi> propDataList = dataControl.getPropList();
		for (Propinsi prop : propDataList){
			 String newURL1=URL_DA1+"?cmd=select&grandparent=0&parent="+prop.getP_code();
			 logger.info("connecting to "+newURL1+", data kab dari propinsi :"+prop.getP_name()+" please wait...");
			 Document doc = Jsoup.connect(newURL1).timeout(connTimeout).get();
		 
			 Element   parent=doc.getElementsByAttributeValue("name", "wilayah_id").get(0);
		  	 
			 Elements kabElList  = parent.getElementsByTag("option");
			 for (Element kabEl : kabElList) {
				  String optValue = kabEl.attr("value");
				  String optLabel = kabEl.text();
				  if (optValue.length()>=1){
					  logger.info(optValue+":"+optLabel+":"+prop.getP_code());
					  Kabupaten kab= new Kabupaten(optValue.trim(),optLabel.trim(),prop.getP_code());
					  dataControl.saveKab(kab, null);
				  
				  }
			 }
			 
		}
		 
	}
	public void propinsiPopulator() throws IOException{
		logger.info("Connecting to "+URL_DA1+", please wait...");
		Document doc = Jsoup.connect(URL_DA1).timeout(connTimeout).get();
		Elements  parentEl=doc.getElementsByAttributeValue("name","wilayah_id");
		for (Element parent : parentEl){
			logger.info(parent);
			 Elements propList  = parent.getElementsByTag("option");
			 for (Element propEl : propList) {
				  String optValue = propEl.attr("value");
				  String optLabel = propEl.text();
				  if (optValue.length()>=1){
					  logger.info(optValue+":"+optLabel);
					  Propinsi prop= new Propinsi(optValue.trim(),optLabel.trim());
					  dataControl.saveOrUpdateProp(prop);
				  }
			 }
		}
	 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MasterPopulator pop=new MasterPopulator();
		try {
			//pop.propinsiPopulator();
			//pop.kabupatenPopulator();
			pop.kecamatanPopulator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
