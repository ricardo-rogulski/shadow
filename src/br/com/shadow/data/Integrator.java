package br.com.shadow.data;

import java.util.Calendar;

import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.entity.Opcao;

public class Integrator {
	
	
	public CotacaoAcao getCotacaoAcao(Acao acao){
		
		CotacaoAcao cotacao = new CotacaoAcao();
		cotacao.setAcao(acao);
		cotacao.setData(Calendar.getInstance());
		
		String codigo = acao.getCode();
		//A partir daqui você pega os dados. O codigo tem o papel pra voce buscar.
		
		//cotacao.setValor(xxx);
		//cotacao.setOscilacao(xxx);
		
		return cotacao;
	}
	
	public CotacaoOpcao getCotacaoOpcao(Opcao opcao){
		
		CotacaoOpcao cotacao = new CotacaoOpcao();
		cotacao.setOpcao(opcao);
		cotacao.setData(Calendar.getInstance());
		
		String codigo = opcao.getCode();
		//A partir daqui você pega os dados. O codigo tem o papel pra voce buscar.
		
		//cotacao.setValor(xxx);
		//cotacao.setOscilacao(xxx);
		
		return cotacao;		
	}

}
