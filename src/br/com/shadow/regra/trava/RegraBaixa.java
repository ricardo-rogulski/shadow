package br.com.shadow.regra.trava;

import java.util.Calendar;
import java.util.List;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.business.TravaBaixaRoboBusiness;
import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaBaixaBeanFactory;
import br.com.shadow.regra.geral.Regra;

public class RegraBaixa extends Regra{
	
	
	protected void fazTrava(String regra){
		Opcao opcaoComMaiorValorExtrinsico = getOpcaoComMaiorValorExtrinsico();
		Opcao umaAcima = getUmaAcima(opcaoComMaiorValorExtrinsico);
		if (umaAcima!=null){
			TravaBaixaBeanFactory tbbf = new TravaBaixaBeanFactory();
			TravaBaixa trava = tbbf.getTravaBaixaByRobo(regra, umaAcima, opcaoComMaiorValorExtrinsico);
			fazTrava(trava);
		}
	}
	
	
	protected void fazTrava(String regra, String acao){
		Opcao opcaoComMaiorValorExtrinsico = getOpcaoComMaiorValorExtrinsico(acao);
		Opcao umaAcima = getUmaAcima(opcaoComMaiorValorExtrinsico);
		if (umaAcima!=null){
			TravaBaixaBeanFactory tbbf = new TravaBaixaBeanFactory();
			TravaBaixa trava = tbbf.getTravaBaixaByRobo(regra, umaAcima, opcaoComMaiorValorExtrinsico);
			fazTrava(trava);
		}
	}
	
	protected void fazTrava(TravaBaixa trava){
		if (trava==null){
			System.out.println("Tentando fazer uma trava nula");
			return;
		}
		showMsgCompra(trava);
		trava.setDataCompra(Calendar.getInstance());
		trava.setValorOpcaoCompraInicial(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaInicial(trava.getValorOpcaoVendaAtual());

		TravaBaixaRoboBusiness trb = new TravaBaixaRoboBusiness();
		trb.saveByRobo(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario += trava.getValorNegocio();
		
		User usuarioTrava = trava.getUser();
		usuarioTrava.setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUserByRobo(usuarioTrava);
		
	}
	
	protected void desfazTrava(TravaBaixa trava, String motivo){
		TravaBaixaBeanFactory tbf = new TravaBaixaBeanFactory();
		trava = tbf.atualizaValoresByRobo(trava);
		showMsgVenda(trava);
		
		trava.setDataVenda(Calendar.getInstance());
		trava.setValorOpcaoCompraFinal(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaFinal(trava.getValorOpcaoVendaAtual());
		trava.setMotivoVenda(motivo);

		TravaBaixaRoboBusiness trb = new TravaBaixaRoboBusiness();
		trb.saveByRobo(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario -= trava.getValorNegocio();
		
		User usuarioTrava = trava.getUser();
		usuarioTrava.setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUserByRobo(usuarioTrava);
	}
	
	
	
	private void showMsgCompra(TravaBaixa trava){
		System.out.println("\n Robo: "+trava.getUser().getLogin()+" Realizando trava de baixa: "+trava.getOpcaoCompra()+" "+trava.getOpcaoVenda()+" Qtd: "+trava.getQuantidade());
	}
	
	private void showMsgVenda(TravaBaixa trava){
		System.out.println("\n Robo: "+trava.getUser().getLogin()+" Desfazendo trava de baixa: "+trava.getOpcaoCompra()+" "+trava.getOpcaoVenda()+" Qtd: "+trava.getQuantidade());
	}
	
	protected Opcao getOpcaoComMaiorValorExtrinsico(){
		OpcaoBusiness ob = new OpcaoBusiness();
		Float maiorValorExtrinsico = 0f;
		Opcao opcaoComMaiorValorExtrinsico = null;
		List<Opcao> opcoes = ob.getOpcoesByRobo();
		for(Opcao opcao : opcoes){
			Float valorExtrinsico = opcao.getValorExtrinsicoByRobo();
			if (valorExtrinsico>maiorValorExtrinsico){
				maiorValorExtrinsico = valorExtrinsico;
				opcaoComMaiorValorExtrinsico = opcao;
			}
		}
		return opcaoComMaiorValorExtrinsico;
	}
	
	protected Opcao getOpcaoComMaiorValorExtrinsico(Acao acao){
		OpcaoBusiness ob = new OpcaoBusiness();
		Float maiorValorExtrinsico = 0f;
		Opcao opcaoComMaiorValorExtrinsico = null;
		List<Opcao> opcoes = ob.getByAcaoByRobo(acao);
		for(Opcao opcao : opcoes){
			Float valorExtrinsico = opcao.getValorExtrinsicoByRobo();
			if (valorExtrinsico>maiorValorExtrinsico){
				maiorValorExtrinsico = valorExtrinsico;
				opcaoComMaiorValorExtrinsico = opcao;
			}
		}
		return opcaoComMaiorValorExtrinsico;
	}
	
	protected Opcao getOpcaoComMaiorValorExtrinsico(List<Acao> acoes){
		OpcaoBusiness ob = new OpcaoBusiness();
		Float maiorValorExtrinsico = 0f;
		Opcao opcaoComMaiorValorExtrinsico = null;
		for(Acao acao : acoes){
			List<Opcao> opcoes = ob.getByAcaoByRobo(acao);
			for(Opcao opcao : opcoes){
				Float valorExtrinsico = opcao.getValorExtrinsicoByRobo();
				if (valorExtrinsico>maiorValorExtrinsico){
					maiorValorExtrinsico = valorExtrinsico;
					opcaoComMaiorValorExtrinsico = opcao;
				}
			}
		}
		return opcaoComMaiorValorExtrinsico;
	}
	
	protected Opcao getOpcaoComMaiorValorExtrinsico(String acao){
		AcaoBusiness ab = new AcaoBusiness();
		Acao ac = ab.getByCodeByRobo(acao);	
		return getOpcaoComMaiorValorExtrinsico(ac);
	}
	
	
	
	protected Opcao getUmaAcima(Opcao opcao){
		OpcaoBusiness ob = new OpcaoBusiness();
		List<Opcao> opcoes = ob.getBySerieByAcaoByRobo(opcao.getSerie().getId(), opcao.getAcao().getId());
		if (opcoes==null || opcoes.isEmpty()){
			return null;
		}
		Opcao umaAcima = null;
		for(int i=0; i<opcoes.size()-1; i++){
			Opcao opt = opcoes.get(i);
			if (opt.getId().intValue()==opcao.getId().intValue()){
				if (i+1 <=opcoes.size()){
					umaAcima = opcoes.get(i+1);
				}
			}
		}
		return umaAcima;
	}
		
	
	
	
}
