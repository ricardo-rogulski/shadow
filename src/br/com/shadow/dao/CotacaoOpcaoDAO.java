package br.com.shadow.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.CotacaoOpcao;




public class CotacaoOpcaoDAO extends DAO{
	
	public CotacaoOpcaoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	
	public List<CotacaoOpcao> getCotacoes(){
		Criteria c = session.createCriteria(CotacaoOpcao.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	

	public List<CotacaoOpcao> getCotacoesByData(Calendar dataInicial, Calendar dataFinal){
		Criteria c = session.createCriteria(CotacaoOpcao.class);
		c.add(Restrictions.gt("data", dataInicial));
		c.add(Restrictions.lt("data", dataFinal));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	public List<CotacaoOpcao> getCotacoesByCodeByData(String code, Calendar dataInicial, Calendar dataFinal){
		Criteria c = session.createCriteria(CotacaoOpcao.class).createAlias("opcao", "opcao");
		c.add(Restrictions.gt("data", dataInicial));
		c.add(Restrictions.le("data", dataFinal));
		c.add(Restrictions.eq("opcao.code", code));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public List<CotacaoOpcao> getCotacoesByOpcao(String code){
		
		Criteria c = session.createCriteria(CotacaoOpcao.class).createAlias("opcao", "opcao");
		c.add(Restrictions.eq("opcao.code", code));
		c.addOrder(Order.asc("data"));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	public List<CotacaoOpcao> getCotacoesByOpcaoByData(String code, Calendar dataInicial, Calendar dataFinal){
		
		Criteria c = session.createCriteria(CotacaoOpcao.class).createAlias("opcao", "opcao");
		c.add(Restrictions.eq("opcao.code", code));
		c.add(Restrictions.gt("data", dataInicial));
		c.add(Restrictions.lt("data", dataFinal));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

}
