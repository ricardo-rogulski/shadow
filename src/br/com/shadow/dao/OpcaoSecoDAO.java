package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.Acao;
import br.com.shadow.entity.OpcaoSeco;




public class OpcaoSecoDAO extends DAO{
	
	public OpcaoSecoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	
	public List<OpcaoSeco> getOpcoes(){
		Criteria c = session.createCriteria(OpcaoSeco.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public List<OpcaoSeco> getOpcoesAtivas(){
		Criteria c = session.createCriteria(OpcaoSeco.class);
		c.add(Restrictions.eq("ativo", true));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	
	public List<OpcaoSeco> getByAcao(Acao acao){
		
		Criteria c = session.createCriteria(OpcaoSeco.class);
		c.add(Restrictions.eq("acao.id", acao.getId()));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	public OpcaoSeco getByCode(String code){
		Criteria c = session.createCriteria(OpcaoSeco.class);
		c.add(Restrictions.eq("code", code));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (OpcaoSeco)lista.get(0);
	}

}
