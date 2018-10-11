package br.com.shadow.handler;

import java.io.IOException;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.util.MyChart;
import br.com.shadow.util.TravaUtil;

public class DetalheTravaAltaHandler {
	
	private TravaAlta trava;
	private Acao acao;
	
	public String getGrafico(){
		MyChart cd = new MyChart();
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesByAcao(acao.getCode());
		
		if (cotacoes!=null && !cotacoes.isEmpty()){
			String file = cd.geraGraficoAcaoTrava(acao.getCode(), cotacoes, trava.getValorExercOpcaoCompra(), trava.getValorExercOpcaoVenda());
			return file;	
		}
		return null;
	}
	
	public void fazTrava(ActionEvent event){
		
		TravaUtil travaUtil = new TravaUtil();
		travaUtil.fazTrava(trava);
		
		FacesContext faces = FacesContext.getCurrentInstance();   
		ExternalContext context = faces.getExternalContext();   
		try{
			context.redirect("negocios.jsf");	
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public TravaAlta getTrava() {
		return trava;
	}

	public void setTrava(TravaAlta trava) {
		this.trava = trava;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

}
