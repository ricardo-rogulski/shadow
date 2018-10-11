package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.Acao;




public class AcaoDAO extends DAO{
	
	public AcaoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<Acao> getAcoes(){
		Criteria c = session.createCriteria(Acao.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	
	public Acao getByCode(String code){
		
		Criteria c = session.createCriteria(Acao.class);
		c.add(Restrictions.eq("code", code));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (Acao)lista.get(0);
	}


}
