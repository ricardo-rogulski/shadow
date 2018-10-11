package br.com.shadow.handler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.CotacaoOpcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.business.SerieOpcaoBusiness;
import br.com.shadow.dao.DAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.util.HibernateUtil;
import br.com.shadow.util.Util;

public class CrudOpcaoHandler {
	
	private Opcao opcao = new Opcao();
	private HtmlSelectOneMenu selectedAcao;
	private HtmlSelectOneMenu selectedSerie;
	
	public List<Opcao> getOpcoes(){
		Util.verificaAcesso();
		OpcaoBusiness lb = new OpcaoBusiness();
		return lb.getOpcoes();
	}
	
	public String salva(){
		Session session = HibernateUtil.currentSession();
		DAO<Opcao> dao = new DAO<Opcao>(session, Opcao.class);
		
		Integer idAcao = Integer.parseInt(selectedAcao.getValue().toString());
		Acao acao = new Acao();
		acao.setId(idAcao);
		opcao.setAcao(acao);
		
		Integer idSerie = Integer.parseInt(selectedSerie.getValue().toString());
		SerieOpcao serie = new SerieOpcao();
		serie.setId(idSerie);
		opcao.setSerie(serie);		
		
		dao.merge(this.opcao);
		this.opcao = new Opcao();

		return "sucesso";	
	}
	
	public String cancel(){
		this.opcao = new Opcao();
		return "";	
	}
	
	public List<SelectItem> getAcoesToComboBox(){
		AcaoBusiness ab = new AcaoBusiness();
		return ab.getAcoesToComboBox();
	}

	public List<SelectItem> getSeriesToComboBox(){
		SerieOpcaoBusiness sob = new SerieOpcaoBusiness();
		return sob.getSeriesToComboBox();
	}
	
	public void exclui(ActionEvent event){
		Session session = HibernateUtil.currentSession();
		DAO<Opcao> dao = new DAO<Opcao>(session, Opcao.class);
		
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("excluiId");
		Integer id = (Integer)param.getValue();
		this.opcao = dao.load(id);
		
		//Deletar todas as cotações da opção.
		CotacaoOpcaoBusiness cob = new CotacaoOpcaoBusiness();
		cob.deleteByOpcao(opcao);
		
		dao.delete(this.opcao);
		this.opcao = new Opcao();
	}

	public void escolheOpcao(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("editId");
		Integer id = (Integer)param.getValue();
		
		Session session = HibernateUtil.currentSession();
		DAO<Opcao> dao = new DAO<Opcao>(session, Opcao.class);
		
		this.opcao = dao.get(id);
		
		selectedAcao.setValue(opcao.getAcao().getId());
		selectedSerie.setValue(opcao.getSerie().getId());
		
	}
	
	public List<SelectItem> getOpcoesParaComboBox(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<Opcao> opcaos = getOpcoes();
		for(Opcao c : opcaos){
			lista.add(new SelectItem(c.getId(), c.getCode()));
		}
		return lista;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

	public HtmlSelectOneMenu getSelectedAcao() {
		return selectedAcao;
	}

	public void setSelectedAcao(HtmlSelectOneMenu selectedAcao) {
		this.selectedAcao = selectedAcao;
	}

	public HtmlSelectOneMenu getSelectedSerie() {
		return selectedSerie;
	}

	public void setSelectedSerie(HtmlSelectOneMenu selectedSerie) {
		this.selectedSerie = selectedSerie;
	}

	
	
	


}
