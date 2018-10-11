package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.dao.OpcaoSecoDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.OpcaoSeco;
import br.com.shadow.util.HibernateUtil;

public class OpcaoSecoBusiness {
	
	
	public List<OpcaoSeco> getOpcoes(){
		Session session = HibernateUtil.currentSession();
		OpcaoSecoDAO cd = new OpcaoSecoDAO(session, OpcaoSeco.class);
		return cd.getOpcoes();
	}
	
	public List<SelectItem> getOpcoesToComboBox(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<OpcaoSeco> acoes = getOpcoes();
		for(OpcaoSeco c : acoes){
			list.add(new SelectItem(c.getId(), c.getCode()));
		}
		return list;
	}
	
	public OpcaoSeco load(Integer id){
		Session session = HibernateUtil.currentSession();
		OpcaoSecoDAO ed = new OpcaoSecoDAO(session, OpcaoSeco.class);
		return (OpcaoSeco)ed.load(id);
	}
	
	public List<OpcaoSeco> getOpcoesByRobo(){
		Session session = HibernateUtil.openSession();
		OpcaoSecoDAO cd = new OpcaoSecoDAO(session, OpcaoSeco.class);
		List<OpcaoSeco> opcoes = cd.getOpcoes();
		session.close();
		return opcoes;
	}
	
	public List<OpcaoSeco> getByAcaoByRobo(Acao acao){
		Session session = HibernateUtil.openSession();
		OpcaoSecoDAO od = new OpcaoSecoDAO(session, OpcaoSeco.class);
		List<OpcaoSeco> opcoes = od.getByAcao(acao);
		return opcoes;
	}
	
	public OpcaoSeco getByCode(String code){
		Session session = HibernateUtil.currentSession();
		OpcaoSecoDAO od = new OpcaoSecoDAO(session, OpcaoSeco.class);
		return od.getByCode(code);
	}

	public OpcaoSeco getByCodeByRobo(String code){
		Session session = HibernateUtil.openSession();
		OpcaoSecoDAO od = new OpcaoSecoDAO(session, OpcaoSeco.class);
		OpcaoSeco OpcaoSeco = od.getByCode(code);
		session.close();
		return OpcaoSeco;
	}



}
