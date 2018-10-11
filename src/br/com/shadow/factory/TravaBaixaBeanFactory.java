package br.com.shadow.factory;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.business.CotacaoOpcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.entity.User;
import br.com.shadow.util.Util;

public class TravaBaixaBeanFactory {
	

	
	
	public TravaBaixa getTravaBaixaByRobo(String nomeRobo, Opcao opcaoCompra, Opcao opcaoVenda){
		
		UserBusiness ub = new UserBusiness();
		User usuario = ub.getUserByRobo(nomeRobo);
		
		if (opcaoCompra==null || opcaoVenda==null){
			return null;
		}
		if (opcaoCompra.getUltimaCotacaoByRobo()==null || opcaoVenda.getUltimaCotacaoByRobo()==null){
			return null;
		}
		TravaBaixa trava = new TravaBaixa();
		trava.setUser(usuario);
		trava.setAcao(opcaoCompra.getAcao().getCode());
		trava.setOpcaoCompra(opcaoCompra.getCode());
		trava.setOpcaoVenda(opcaoVenda.getCode());
		trava.setValorOpcaoCompraAtual(opcaoCompra.getUltimaCotacaoByRobo().getValor());
		trava.setValorOpcaoVendaAtual(opcaoVenda.getUltimaCotacaoByRobo().getValor());
		trava.setValorAcaoAtual(opcaoCompra.getAcao().getUltimaCotacaoByRobo().getValor());
		trava.setValorOscilacaoAcaoAtual(opcaoCompra.getAcao().getUltimaOscilacaoByRobo());
		trava.setValorOscilacaoOpcaoCompraAtual(opcaoCompra.getUltimaCotacaoByRobo().getOscilacao());
		trava.setValorOscilacaoOpcaoVendaAtual(opcaoVenda.getUltimaCotacaoByRobo().getOscilacao());
		trava.setValorExercOpcaoCompra(opcaoCompra.getVlExercicio());
		trava.setValorExercOpcaoVenda(opcaoVenda.getVlExercicio());
		trava.setQuantidade(calculaQtdCompra(opcaoCompra.getUltimaCotacaoByRobo().getValor(), usuario.getSaldo()));
		
		return trava;
	}
	
	
	public TravaBaixa atualizaValores(TravaBaixa trava){
		
		OpcaoBusiness ob = new OpcaoBusiness();
		Opcao compra = ob.getByCode(trava.getOpcaoCompra());
		Opcao venda = ob.getByCode(trava.getOpcaoVenda());
		Acao acao = compra.getAcao();
		
		trava.setValorOpcaoCompraAtual(compra.getUltimaCotacao().getValor());
		trava.setValorOpcaoVendaAtual(venda.getUltimaCotacao().getValor());
		trava.setValorAcaoAtual(acao.getUltimoValor());
		trava.setValorOscilacaoAcaoAtual(acao.getUltimaOscilacao());
		trava.setValorOscilacaoOpcaoCompraAtual(compra.getUltimaCotacao().getOscilacao());
		trava.setValorOscilacaoOpcaoVendaAtual(venda.getUltimaCotacao().getOscilacao());
		
		return trava;
	}
	
	
	public TravaBaixa atualizaValoresByRobo(TravaBaixa trava){
		
		CotacaoOpcaoBusiness cob = new CotacaoOpcaoBusiness();
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();

		CotacaoOpcao ultimaCotacaoCompra = cob.getUltimaCotacaoByOpcaoByRobo(trava.getOpcaoCompra());
		CotacaoOpcao ultimaCotacaoVenda = cob.getUltimaCotacaoByOpcaoByRobo(trava.getOpcaoVenda());
		CotacaoAcao ultimaCotacaoAcao = cab.getUltimaCotacaoByAcaoByRobo(trava.getAcao());
		
		trava.setValorOpcaoCompraAtual(ultimaCotacaoCompra.getValor());
		trava.setValorOpcaoVendaAtual(ultimaCotacaoVenda.getValor());
		trava.setValorAcaoAtual(ultimaCotacaoAcao.getValor());
		trava.setValorOscilacaoAcaoAtual(ultimaCotacaoAcao.getOscilacao());
		trava.setValorOscilacaoOpcaoCompraAtual(ultimaCotacaoCompra.getOscilacao());
		trava.setValorOscilacaoOpcaoVendaAtual(ultimaCotacaoVenda.getOscilacao());
		
		return trava;
	}

	
	private Integer calculaQtdCompra(Float valor, Float saldoTotal){
		Float vlLote = valor*100; 
		vlLote = Util.paraDuasCasas(vlLote);
		Float qtdLotes = saldoTotal / vlLote; 
		qtdLotes = Util.paraDuasCasas(qtdLotes);
		Integer qtd = Math.round(qtdLotes);
		return qtd*100;		
	}

	
	
}
