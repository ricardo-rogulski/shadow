package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.Admin;




public class AdminDAO extends DAO{
	
	public AdminDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public Admin getAdmin(){
		Criteria c = session.createCriteria(Admin.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (Admin)lista.get(0);
	}

}
