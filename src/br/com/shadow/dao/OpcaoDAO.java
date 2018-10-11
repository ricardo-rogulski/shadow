package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;




public class OpcaoDAO extends DAO{
	
	public OpcaoDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	public List<Opcao> getOpcoes(){
		Criteria c = session.createCriteria(Opcao.class).createAlias("acao", "acao").createAlias("serie", "serie");
		c.addOrder(Order.asc("acao.name")).addOrder(Order.asc("serie.name")).addOrder(Order.asc("code"));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public List<Opcao> getOpcoesAtivas(){
		Criteria c = session.createCriteria(Opcao.class);
		c.add(Restrictions.eq("ativo", true));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}
	
	public Opcao getBySerie(Integer idSerie){
		
		Criteria c = session.createCriteria(Opcao.class);
		c.add(Restrictions.eq("serie.id", idSerie));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (Opcao)lista.get(0);
	}

	public List<Opcao> getBySerieByAcaoOrderByVlExerc(Integer idSerie, Integer idAcao){
		
		Criteria c = session.createCriteria(Opcao.class);
		c.add(Restrictions.eq("serie.id", idSerie));
		c.add(Restrictions.eq("acao.id", idAcao));
		c.addOrder(Order.asc("vlExercicio"));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	public List<Opcao> getByAcao(Acao acao){
		
		Criteria c = session.createCriteria(Opcao.class);
		c.add(Restrictions.eq("acao.id", acao.getId()));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	public Opcao getByCode(String code){
		Criteria c = session.createCriteria(Opcao.class);
		c.add(Restrictions.eq("code", code));
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (Opcao)lista.get(0);
	}

}
