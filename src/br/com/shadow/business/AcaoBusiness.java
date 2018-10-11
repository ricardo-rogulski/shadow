package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.dao.AcaoDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.util.HibernateUtil;

public class AcaoBusiness {
	
	
	public List<Acao> getAcoes(){
		Session session = HibernateUtil.currentSession();
		AcaoDAO cd = new AcaoDAO(session, Acao.class);
		return cd.getAcoes();
	}
	
	public List<SelectItem> getAcoesToComboBox(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Acao> acoes = getAcoes();
		for(Acao c : acoes){
			list.add(new SelectItem(c.getId(), c.getName()));
		}
		return list;
	}
	
	public Acao getByCode(String code){
		Session session = HibernateUtil.currentSession();
		AcaoDAO cd = new AcaoDAO(session, Acao.class);
		return cd.getByCode(code);		
	}

	public Acao getByCodeByRobo(String code){
		Session session = HibernateUtil.openSession();
		AcaoDAO cd = new AcaoDAO(session, Acao.class);
		Acao acao = cd.getByCode(code);		
		session.close();
		return acao;
	}

	
	public Acao load(Integer id){
		Session session = HibernateUtil.currentSession();
		AcaoDAO ed = new AcaoDAO(session, Acao.class);
		return (Acao)ed.load(id);
	}
	
	public List<Acao> getAcoesByRobo(){
		Session session = HibernateUtil.openSession();
		AcaoDAO cd = new AcaoDAO(session, Acao.class);
		List<Acao> acoes = cd.getAcoes(); 
		session.close();
		return acoes;
	}





}
