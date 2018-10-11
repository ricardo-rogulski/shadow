package br.com.shadow.regra.trava;

import java.util.Calendar;
import java.util.List;

import br.com.shadow.business.TravaAltaRoboBusiness;
import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.regra.geral.Regra;

public class RegraAlta extends Regra{
	
	public void fazTrava(String regra, String acao){
		TravaAltaRoboBusiness trb = new TravaAltaRoboBusiness();
		List<TravaAlta> travas = trb.getTravasDeAltaPossiveisByRobo(regra, acao);
 		TravaAlta travaDeMaiorPontuacao = trb.getTravaComMaiorPontuacao(travas);
		if (travaDeMaiorPontuacao!=null){
			fazTrava(travaDeMaiorPontuacao);			
		}
	}
	
	public void fazTrava(String regra, List<Acao> acoes){
		TravaAltaRoboBusiness trb = new TravaAltaRoboBusiness();
		List<TravaAlta> travas = trb.getTravasDeAltaPossiveisByRobo(regra, acoes);
 		TravaAlta travaDeMaiorPontuacao = trb.getTravaComMaiorPontuacao(travas);
		if (travaDeMaiorPontuacao!=null){
			fazTrava(travaDeMaiorPontuacao);			
		}
	}
	
	public void fazTrava(TravaAlta trava){
		showMsgCompra(trava);
		trava.setDataCompra(Calendar.getInstance());
		trava.setPontuacaoInicial(trava.getPontuacao());
		trava.setValorAcaoInicial(trava.getValorAcaoAtual());
		trava.setValorOpcaoCompraInicial(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaInicial(trava.getValorOpcaoVendaAtual());

		TravaAltaRoboBusiness trb = new TravaAltaRoboBusiness();
		trb.saveByRobo(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario -= trava.getValorNegocio();
		
		User usuarioTrava = trava.getUser();
		usuarioTrava.setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUserByRobo(usuarioTrava);
		
	}
	
	public void desfazTrava(TravaAlta trava, String motivo){
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		trava = tbf.atualizaValoresByRobo(trava);
		showMsgVenda(trava);
		
		trava.setDataVenda(Calendar.getInstance());
		trava.setPontuacaoFinal(trava.getPontuacao());
		trava.setValorAcaoFinal(trava.getValorAcaoAtual());
		trava.setValorOpcaoCompraFinal(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaFinal(trava.getValorOpcaoVendaAtual());
		trava.setMotivoVenda(motivo);

		TravaAltaRoboBusiness trb = new TravaAltaRoboBusiness();
		trb.saveByRobo(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario += trava.getValorNegocio();
		
		User usuarioTrava = trava.getUser();
		usuarioTrava.setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUserByRobo(usuarioTrava);
	}
	
	private void showMsgCompra(TravaAlta trava){
		System.out.println("\n Robo: "+trava.getUser().getLogin()+" Realizando trava de alta: "+trava.getOpcaoCompra()+" "+trava.getOpcaoVenda()+" Qtd: "+trava.getQuantidade()+ " Pontos: "+trava.getPontuacao());
	}
	
	private void showMsgVenda(TravaAlta trava){
		System.out.println("\n Robo: "+trava.getUser().getLogin()+" Desfazendo trava de alta: "+trava.getOpcaoCompra()+" "+trava.getOpcaoVenda()+" Qtd: "+trava.getQuantidade()+ " Pontos: "+trava.getPontuacao());
	}
	
	
	
	
	

}
