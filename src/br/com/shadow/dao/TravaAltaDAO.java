package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.TravaAlta;




public class TravaAltaDAO extends DAO{
	
	public TravaAltaDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<TravaAlta> getTravasDeAlta(){
		Criteria c = session.createCriteria(TravaAlta.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	
	
	public List<TravaAlta> getTravasDeAltaByUser(String user){
		
		Criteria c = session.createCriteria(TravaAlta.class).createAlias("user", "user");
		c.add(Restrictions.eq("user.login", user));
		c.addOrder(Order.desc("dataCompra"));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}


	
}
