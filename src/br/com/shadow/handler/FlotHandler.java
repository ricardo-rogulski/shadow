package br.com.shadow.handler;

import java.util.ArrayList;
import java.util.List;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.vo.MediaMovelVO;


public class FlotHandler {
	
	private String acao;
	private String cotacoes;
	private final Long DUAS_HORAS = 1000*60*60*2l;
	//1196463600000,2#1196467200000,4#1196471400000,6#1196488400000,10
	

	public String doVale(){
		this.acao = "VALE5";
		return "";
	}
	
	public String doPetro(){
		this.acao = "PETR4";
		return "";
	}
	
	public String getCotacoes() {
		
		if (acao==null || acao.equals("")){
			return "";
		}
		
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesDoDiaByAcao(acao);
		StringBuffer result = new StringBuffer();
		if (cotacoes==null || cotacoes.isEmpty()){
			return "";
		}
		List<CotacaoAcao> cots = new ArrayList<CotacaoAcao>();
		MediaMovelBusiness mmb = new MediaMovelBusiness();
		for(CotacaoAcao cotacao : cotacoes){
			result.append(cotacao.getData().getTimeInMillis()-DUAS_HORAS);
			result.append(",");
			result.append(cotacao.getValor());
			
			//Linha de fechamento do dia anterior.
			result.append(",");
			result.append(getValorFechamentoDiaAnterior(cotacao));

			cots.add(cotacao);
			MediaMovelVO mm = mmb.getMediaMovel(cots);
			if (mm!=null){
				result.append(",");
				result.append(mm.getValorMediaMenor());
				result.append(",");
				result.append(mm.getValorMediaMaior());
			}
			result.append("#");
		}
		String res = result.toString();
		res = res.substring(0, res.length()-1);
		
		return res;
	}
	
	private Float getValorFechamentoDiaAnterior(CotacaoAcao cotacaoAcao){
		
		Float valorMomento = cotacaoAcao.getValor();
		Float oscilacao = cotacaoAcao.getOscilacao();
		Float valorFech = valorMomento/(1+(oscilacao/100));
		
		return valorFech;
	}


	public void setCotacoes(String cotacoes) {
		this.cotacoes = cotacoes;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	

}
