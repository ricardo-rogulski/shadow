package br.com.shadow.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;




public class CotacaoAcaoDAO extends DAO{
	
	public CotacaoAcaoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<CotacaoAcao> getCotacoes(){
		Criteria c = session.createCriteria(CotacaoAcao.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public List<CotacaoAcao> getCotacoesByCodeByData(String code, Calendar dataInicial, Calendar dataFinal){
		Criteria c = session.createCriteria(CotacaoAcao.class).createAlias("acao", "acao");
		c.add(Restrictions.gt("data", dataInicial));
		c.add(Restrictions.le("data", dataFinal));
		c.add(Restrictions.eq("acao.code", code));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	
	public List<CotacaoAcao> getCotacoesByAcao(String code){
		
		Criteria c = session.createCriteria(CotacaoAcao.class).createAlias("acao", "acao");
		c.add(Restrictions.eq("acao.code", code));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}


}
