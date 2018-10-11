package br.com.shadow.handler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.business.SerieOpcaoBusiness;
import br.com.shadow.dao.DAO;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.util.HibernateUtil;
import br.com.shadow.util.Util;

public class CrudSerieOpcaoHandler {
	
	private SerieOpcao serie = new SerieOpcao();
	
	public List<SerieOpcao> getSeries(){
		Util.verificaAcesso();
		SerieOpcaoBusiness lb = new SerieOpcaoBusiness();
		return lb.getSeries();
	}
	
	public String salva(){
		Session session = HibernateUtil.currentSession();
		DAO<SerieOpcao> dao = new DAO<SerieOpcao>(session, SerieOpcao.class);
		
		dao.merge(this.serie);
		this.serie = new SerieOpcao();

		return "sucesso";	
	}
	
	public String cancel(){
		this.serie = new SerieOpcao();
		return "";	
	}

	
	public void exclui(ActionEvent event){
		Session session = HibernateUtil.currentSession();
		DAO<SerieOpcao> dao = new DAO<SerieOpcao>(session, SerieOpcao.class);
		
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("excluiId");
		Integer id = (Integer)param.getValue();
		this.serie = dao.load(id);
		
		dao.delete(this.serie);
		this.serie = new SerieOpcao();
	}

	public void escolheSerieOpcao(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("editId");
		Integer id = (Integer)param.getValue();
		
		Session session = HibernateUtil.currentSession();
		DAO<SerieOpcao> dao = new DAO<SerieOpcao>(session, SerieOpcao.class);
		
		this.serie = dao.get(id);
	}
	
	public List<SelectItem> getSeriesParaComboBox(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<SerieOpcao> series = getSeries();
		for(SerieOpcao c : series){
			lista.add(new SelectItem(c.getId(), c.getName()));
		}
		return lista;
	}


	public SerieOpcao getSerie() {
		return serie;
	}

	public void setSerie(SerieOpcao serie) {
		this.serie = serie;
	}

	


}
