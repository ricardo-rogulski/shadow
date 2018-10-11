package br.com.shadow.handler;


import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.dao.DAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.util.HibernateUtil;
import br.com.shadow.util.Util;

public class CrudAcaoHandler {
	
	private Acao acao = new Acao();
	
	public List<Acao> getAcoes(){
		Util.verificaAcesso();
		AcaoBusiness lb = new AcaoBusiness();
		return lb.getAcoes();
	}
	
	public String salva(){
		Session session = HibernateUtil.currentSession();
		DAO<Acao> dao = new DAO<Acao>(session, Acao.class);
		
		dao.merge(this.acao);
		this.acao = new Acao();

		return "sucesso";	
	}
	
	public String cancel(){
		this.acao = new Acao();
		return "";	
	}

	
	public void exclui(ActionEvent event){
		Session session = HibernateUtil.currentSession();
		DAO<Acao> dao = new DAO<Acao>(session, Acao.class);
		
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("excluiId");
		Integer id = (Integer)param.getValue();
		this.acao = dao.load(id);
		
		dao.delete(this.acao);
		this.acao = new Acao();
	}

	public void escolheAcao(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("editId");
		Integer id = (Integer)param.getValue();
		
		Session session = HibernateUtil.currentSession();
		DAO<Acao> dao = new DAO<Acao>(session, Acao.class);
		
		this.acao = dao.get(id);
	}
	
	public List<SelectItem> getAcoesParaComboBox(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<Acao> acaos = getAcoes();
		for(Acao c : acaos){
			lista.add(new SelectItem(c.getId(), c.getName()));
		}
		return lista;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
	


}
