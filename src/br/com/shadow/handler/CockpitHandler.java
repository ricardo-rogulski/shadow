package br.com.shadow.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.business.SerieOpcaoBusiness;
import br.com.shadow.business.TravaDeAltaBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.util.Util;

public class CockpitHandler {

	private List<Acao> acoes = new ArrayList<Acao>();
	private List<SerieOpcao> series = new ArrayList<SerieOpcao>();
	
	private Float saldoUsuario;
	
	public List<TravaAlta> getTravasDeAltaPossiveis(){
		Util util = new Util();
		User usuario = util.getUsuarioLogado();
		TravaDeAltaBusiness travaBusiness = new TravaDeAltaBusiness();
		return travaBusiness.getTravasDeAltaPossiveis(usuario);
	}
	
	public void detalhes(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter)link.findComponent("detalhe");
		String ids = (String)param.getValue();
		String optCompra = ids.substring(0, ids.indexOf(";"));
		String optVenda = ids.substring(ids.indexOf(";")+1, ids.length());
		
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		Util util = new Util();
		User usuario = util.getUsuarioLogado();
		
		TravaAlta trava = tbf.getTravaAlta(usuario, optCompra, optVenda);

		DetalheTravaAltaHandler dtah = (DetalheTravaAltaHandler)util.getHandler("dtaHandler");
		dtah.setTrava(trava);
		
		OpcaoBusiness ob = new OpcaoBusiness();
		Opcao compra = ob.getByCode(optCompra);
		dtah.setAcao(compra.getAcao());
		
		FacesContext faces = FacesContext.getCurrentInstance();   
		ExternalContext context = faces.getExternalContext();   
		try{
			context.redirect("detalheTravaAlta.jsf");	
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public List<SerieOpcao> getSeries() {
		if (series.isEmpty()){
			SerieOpcaoBusiness sob = new SerieOpcaoBusiness();
			series = sob.getSeries();
		}
		return series;		
	}
	
	public List<Acao> getAcoes(){
		if (acoes.isEmpty()){
			AcaoBusiness ab = new AcaoBusiness();
			acoes = ab.getAcoes();
		}
		return acoes;		
	}

	public Float getSaldoUsuario() {
		return saldoUsuario;
	}

	public void setSaldoUsuario(Float saldoUsuario) {
		this.saldoUsuario = saldoUsuario;
	}
	
}
