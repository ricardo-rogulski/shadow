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
import br.com.shadow.business.CotacaoOpcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.regra.geral.Regra;
import br.com.shadow.util.Util;

public class RoboCotacoesOpcoes extends Regra implements Job{
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (isBolsaAberta()){
			processaCotacoesOpcoes();
		}
	}
	
	private void processaCotacoesOpcoes(){
		
		Long inicio = new Date().getTime();
		
		//Pega as opções cadastradas e ativas.
		OpcaoBusiness ob = new OpcaoBusiness();
		List<Opcao> opcoes = ob.getOpcoesByRobo();
		for (Opcao opcao : opcoes){
			if (opcao.isAtivo()){
				//Busca as cotações.
				StringBuffer conteudo = getCotacoes(opcao.getCode()); 
				if (conteudo.length()>0){
					//Extrai as cotações do conteúdo.
					CotacaoOpcao cot = extraiValoresOpcao(conteudo, opcao);
					
					//Salva em BD.
					CotacaoOpcaoBusiness cob = new CotacaoOpcaoBusiness();
					cob.saveByRobo(cot);
				}
			}			
		}
		Long fim = new Date().getTime();
		System.out.println(Util.getDataAsDiaMesAnoHoraMinuto()+" - Cotações opções: "+(fim-inicio)+" ms");
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
	
	public CotacaoOpcao extraiValoresOpcao(StringBuffer texto, Opcao opcao){
		CotacaoOpcao cot = new CotacaoOpcao();
		cot.setOpcao(opcao);
		
		String valor = texto.substring(texto.indexOf("R$")+3, texto.indexOf("R$")+8);
		valor = valor.substring(0, valor.indexOf("<"));
		if (valor.contains(",")){
			valor = valor.replace(",", ".");			
		}
		Float fValor = Float.parseFloat(valor);
		cot.setValor(fValor);		
		cot.setData(Calendar.getInstance());
		
		String osc = texto.substring(texto.indexOf("Oscila"), texto.length());
		if (osc.contains("%")){
			osc = osc.substring(osc.indexOf("%")-10, osc.indexOf("%"));
			osc = osc.substring(osc.indexOf(">")+1, osc.length());
			if (osc.contains(",")){
				osc = osc.replace(",", ".");			
			}
			Float fOsc = Float.parseFloat(osc);
			cot.setOscilacao(fOsc);
		}else{
			cot.setOscilacao(0f);
		}
		

		//System.out.println("Opção: "+cot.getOpcao().getCode()+" Valor: "+cot.getValor()+" Oscilação: "+cot.getOscilacao()+" Negócios: "+cot.getNegocios());
		
		return cot;
	}
	
	
	

}
