package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;




public class TravaBaixaDAO extends DAO{
	
	public TravaBaixaDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<TravaBaixa> getTravasDeBaixa(){
		Criteria c = session.createCriteria(TravaBaixa.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	
	
	public List<TravaBaixa> getTravasDeBaixaByUser(String user){
		
		Criteria c = session.createCriteria(TravaBaixa.class).createAlias("user", "user");
		c.add(Restrictions.eq("user.login", user));
		c.addOrder(Order.desc("dataCompra"));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}


	
}
