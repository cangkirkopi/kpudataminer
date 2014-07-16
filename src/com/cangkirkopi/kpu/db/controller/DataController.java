package com.cangkirkopi.kpu.db.controller;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cangkirkopi.kpu.db.model.Da1;
import com.cangkirkopi.kpu.db.model.Kabupaten;
import com.cangkirkopi.kpu.db.model.Kecamatan;
import com.cangkirkopi.kpu.db.model.Propinsi;
 

public class DataController {
	private static final Logger logger = Logger.getLogger(
			DataController.class.getName());
	public List<Da1> getDa1List(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			  Criteria crit = session.createCriteria(Da1.class);
			  crit.add(Restrictions.eq("da_status", 0));
			 List<Da1> resultList=crit.list();
			 return resultList;
			 
		} catch (HibernateException e) {
			
			logger.error(e.fillInStackTrace());
			 return null;
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public List<Kecamatan> getKecList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Criteria crit = session.createCriteria(Kecamatan.class);
			 List<Kecamatan> resultList=crit.list();
			 return resultList;
		} catch (HibernateException e) {
			
			logger.error(e.fillInStackTrace());
			 return null;
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public List<Propinsi> getPropList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Criteria crit = session.createCriteria(Propinsi.class);
			 List<Propinsi> resultList=crit.list();
			 return resultList;
		} catch (HibernateException e) {
			
			logger.error(e.fillInStackTrace());
			 return null;
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public List<Kabupaten> getKabList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Criteria crit = session.createCriteria(Kabupaten.class);
			 List<Kabupaten> resultList=crit.list();
			 return resultList;
		} catch (HibernateException e) {
			
			logger.error(e.fillInStackTrace());
			 return null;
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public void saveOrUpdateProp(Propinsi prop){
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
	 
		try {
			 // Creating a Criteria instance
            Criteria crit = session.createCriteria(Propinsi.class);
            crit.add(Restrictions.eq("p_code", prop.getP_code()));
           
            List resultList=crit.list();
            if (!resultList.isEmpty()) {
            	updateProp(prop, null);
            }
            else saveProp(prop, null);
            	 
			 
		} catch (HibernateException e) {
			 
			logger.error(e.fillInStackTrace());
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public void saveProp(Propinsi prop,Session session )
	{
	 
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
	 
		try {
			transaction = session.beginTransaction();
			 
			session.save(prop);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		 
			logger.error(e.fillInStackTrace() );
		} finally {
			if (session.isOpen())
			session.close();
		}
	 
	}
	public void updateProp(Propinsi prop,Session session){
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(prop);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session.isOpen())
			session.close();
		}
	}
	public void saveOrUpdateKab(Kabupaten kab){
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
	 
		try {
			 // Creating a Criteria instance
            Criteria crit = session.createCriteria(Kabupaten.class);
            crit.add(Restrictions.eq("k_code", kab.getK_code()));
           
            List resultList=crit.list();
            if (!resultList.isEmpty()) {
            	updateKab(kab, null);
            }
            else saveKab(kab, null);
            	 
			 
		} catch (HibernateException e) {
			 
			logger.error(e.fillInStackTrace());
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public void saveKab(Kabupaten kab,Session session )
	{
	 
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
	 
		try {
			transaction = session.beginTransaction();
			 
			session.save(kab);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		 
			logger.error(e.fillInStackTrace() );
		} finally {
			if (session.isOpen())
			session.close();
		}
	 
	}
	public void updateKab(Kabupaten kab,Session session){
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(kab);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session.isOpen())
			session.close();
		}
	}

	public void saveOrUpdateKec(Kecamatan kec){
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
	 
		try {
			 // Creating a Criteria instance
            Criteria crit = session.createCriteria(Kecamatan.class);
            crit.add(Restrictions.eq("kc_code", kec.getKc_code()));
           
            List resultList=crit.list();
            if (!resultList.isEmpty()) {
            	updateKec(kec, null);
            }
            else saveKec(kec, null);
            	 
			 
		} catch (HibernateException e) {
			 
			logger.error(e.fillInStackTrace());
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public void saveKec(Kecamatan kec,Session session )
	{
	 
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
	 
		try {
			transaction = session.beginTransaction();
			 
			session.save(kec);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		 
			logger.error(e.fillInStackTrace() );
		} finally {
			if (session.isOpen())
			session.close();
		}
	 
	}
	public void updateKec(Kecamatan kec,Session session){
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(kec);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session.isOpen())
			session.close();
		}
	}
 
	public void saveOrUpdateDa1(Da1 da1){
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
	 
		try {
			 // Creating a Criteria instance
            Criteria crit = session.createCriteria(Da1.class);
            crit.add(Restrictions.eq("kc_code", da1.getKc_code()));
           
            List resultList=crit.list();
            if (!resultList.isEmpty()) {
            	updateDa1(da1, null);
            }
            else saveDa1(da1, null);
            	 
			 
		} catch (HibernateException e) {
			 
			logger.error(e.fillInStackTrace());
		} finally {
			if (session.isOpen())
				session.close();
			 
		}
	}
	public void saveDa1(Da1 da1,Session session )
	{
		logger.info("saving da1");
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
	 
		try {
			transaction = session.beginTransaction();
			 
			session.save(da1);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		 
			logger.error(e.fillInStackTrace() );
		} finally {
			if (session.isOpen())
			session.close();
		}
	 
	}
	public void updateDa1(Da1 da1,Session session){
		logger.info("update da1 "+da1.toString());
		if (session==null)
			  session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(da1);
			transaction.commit();
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session.isOpen())
			session.close();
		}
	}
	 
	
}
