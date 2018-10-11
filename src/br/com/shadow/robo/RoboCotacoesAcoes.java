package br.com.shadow.robo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sun.misc.BASE64Encoder;
import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.regra.geral.Regra;
import br.com.shadow.util.Util;

public class RoboCotacoesAcoes extends Regra implements Job{
	
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (isBolsaAberta()){			
			processaCotacoesAcoes();
		}
	}
	
	private void processaCotacoesAcoes(){
		
		Long inicio = new Date().getTime();
		
		//Pega as ações cadastradas.
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		for (Acao acao : acoes){
			//Busca as cotações.
			StringBuffer conteudo = getCotacoes(acao.getCode()); 
			
			//Extrai as cotações do conteúdo.
			CotacaoAcao cot = extraiValoresAcao(conteudo, acao);
			
			//Salva em BD.
			CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
			cab.saveByRobo(cot);
		}
		
		Long fim = new Date().getTime();
		System.out.println(Util.getDataAsDiaMesAnoHoraMinuto()+" - Cotações ações: "+(fim-inicio)+" ms");
		

	}

	private StringBuffer getCotacoes(String papel){
		String endereco = "http://www.bmfbovespa.com.br/Pregao-online/ExecutaAcaoCotRapXSL.asp?gstrCA=&txtCodigo="+papel+"&intIdiomaXsl=0";
		String conteudo;
		StringBuffer texto = new StringBuffer();
		try{
			URL url = new URL(endereco);	
			URLConnection con = url.openConnection();
			if (useProxy()){
				String userpass = username + ":" + password; 
				System.setProperty( "proxySet", "true" );
				System.setProperty( "http.proxyPort", Integer.toString( 80 ) );
				System.setProperty("http.proxyHost", proxyHost); 
				System.setProperty("http.proxyPort", proxyPort);
				String encodedLogin = new BASE64Encoder().encode(userpass.getBytes());
				con.setRequestProperty("Proxy-Authorization", "Basic " + encodedLogin);
			}
			
			InputStream is = con.getInputStream();
	        InputStreamReader ir = new InputStreamReader(is, "8859_1"); 
	        BufferedReader in = new BufferedReader(ir); 
	        
	        while ( (conteudo = in.readLine()) != null){ 
	        	texto.append(conteudo);
	        } 
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return texto;
	}
	
	public CotacaoAcao extraiValoresAcao(StringBuffer texto, Acao acao){
		CotacaoAcao cot = new CotacaoAcao();
		cot.setAcao(acao);
		
		String valor = texto.substring(texto.indexOf("R$")+3, texto.indexOf("R$")+8);
		if (valor.contains("<")){
			valor = valor.substring(0, valor.length()-1);
		}
		
		if (valor.contains(",")){
			valor = valor.replace(",", ".");			
		}
		Float fValor = Float.parseFloat(valor);
		cot.setValor(fValor);		
		cot.setData(Calendar.getInstance());
		
		String osc = texto.substring(texto.indexOf("Oscila"), texto.length());
		osc = osc.substring(osc.indexOf("%")-10, osc.indexOf("%"));
		osc = osc.substring(osc.indexOf(">")+1, osc.length());
		if (osc.contains(",")){
			osc = osc.replace(",", ".");			
		}
		Float fOsc = Float.parseFloat(osc);
		cot.setOscilacao(fOsc);
		
		//System.out.println("Ação: "+cot.getAcao().getCode()+" Valor: "+cot.getValor()+" Oscilação: "+cot.getOscilacao()+" Negócios: "+cot.getNegocios());
		
		return cot;
	}
	

}
