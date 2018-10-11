package br.com.shadow.business;

import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.AdminDAO;
import br.com.shadow.entity.Admin;
import br.com.shadow.util.HibernateUtil;

public class AdminBusiness {
	
	
	public Admin getAdmin(){
		Session session = HibernateUtil.currentSession();
		AdminDAO ad = new AdminDAO(session, Admin.class);
		return ad.getAdmin();
	}
	
	public Admin getAdminByRobo(){
		Session session = HibernateUtil.openSession();
		AdminDAO ad = new AdminDAO(session, Admin.class);
		Admin adm = ad.getAdmin();
		session.close();
		return adm;
	}
	
	public Admin load(Integer id){
		Session session = HibernateUtil.currentSession();
		AdminDAO ed = new AdminDAO(session, Admin.class);
		return (Admin)ed.load(id);
	}




}
