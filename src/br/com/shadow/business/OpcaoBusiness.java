package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.dao.OpcaoDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.util.HibernateUtil;

public class OpcaoBusiness {
	
	
	public List<Opcao> getOpcoes(){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO cd = new OpcaoDAO(session, Opcao.class);
		return cd.getOpcoes();
	}
	
	public List<SelectItem> getOpcoesToComboBox(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Opcao> acoes = getOpcoes();
		for(Opcao c : acoes){
			list.add(new SelectItem(c.getId(), c.getCode()));
		}
		return list;
	}
	
	public Opcao getBySerie(Integer idSerie){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO cd = new OpcaoDAO(session, Opcao.class);
		return cd.getBySerie(idSerie);
	}
	
	public Opcao load(Integer id){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO ed = new OpcaoDAO(session, Opcao.class);
		return (Opcao)ed.load(id);
	}
	
	public List<Opcao> getOpcoesByRobo(){
		Session session = HibernateUtil.openSession();
		OpcaoDAO cd = new OpcaoDAO(session, Opcao.class);
		List<Opcao> opcoes = cd.getOpcoes();
		session.close();
		return opcoes;
	}
	
	public List<Opcao> getBySerieByAcaoOrderByVlExerc(Integer idSerie, Integer idAcao){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO od = new OpcaoDAO(session, Opcao.class);
		return od.getBySerieByAcaoOrderByVlExerc(idSerie, idAcao);
	}

	public List<Opcao> getBySerieByAcaoByRobo(Integer idSerie, Integer idAcao){
		Session session = HibernateUtil.openSession();
		OpcaoDAO od = new OpcaoDAO(session, Opcao.class);
		List<Opcao> opcoes = od.getBySerieByAcaoOrderByVlExerc(idSerie, idAcao);
		return opcoes;
	}
	
	public List<Opcao> getByAcaoByRobo(Acao acao){
		Session session = HibernateUtil.openSession();
		OpcaoDAO od = new OpcaoDAO(session, Opcao.class);
		List<Opcao> opcoes = od.getByAcao(acao);
		return opcoes;
	}
	
	public Opcao getByCode(String code){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO od = new OpcaoDAO(session, Opcao.class);
		return od.getByCode(code);
	}

	public Opcao getByCodeByRobo(String code){
		Session session = HibernateUtil.openSession();
		OpcaoDAO od = new OpcaoDAO(session, Opcao.class);
		Opcao opcao = od.getByCode(code);
		session.close();
		return opcao;
	}



}
