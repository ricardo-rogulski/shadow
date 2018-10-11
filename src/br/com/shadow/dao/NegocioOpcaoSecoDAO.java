package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.NegocioOpcaoSeco;
import br.com.shadow.entity.OpcaoSeco;


public class NegocioOpcaoSecoDAO extends DAO{
	
	public NegocioOpcaoSecoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<NegocioOpcaoSeco> getOpcoesSeco(){
		Criteria c = session.createCriteria(NegocioOpcaoSeco.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public List<NegocioOpcaoSeco> getNegociosOpcaoSecoByUser(String user){
		Criteria c = session.createCriteria(OpcaoSeco.class).createAlias("user", "user");
		c.add(Restrictions.eq("user.login", user));
		c.addOrder(Order.desc("dataCompra"));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}


	
}
