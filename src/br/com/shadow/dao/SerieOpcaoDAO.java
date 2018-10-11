package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.shadow.entity.SerieOpcao;


public class SerieOpcaoDAO extends DAO{
	
	public SerieOpcaoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<SerieOpcao> getSeries(){
		Criteria c = session.createCriteria(SerieOpcao.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}


}
