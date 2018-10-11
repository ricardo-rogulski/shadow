package br.com.shadow.factory;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.business.CotacaoOpcaoBusiness;
import br.com.shadow.business.OpcaoBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.User;

public class TravaAltaBeanFactory {
	

	public TravaAlta getTravaAlta(User usuario, String opcaoCompra, String opcaoVenda){
		OpcaoBusiness ob = new OpcaoBusiness();
		Opcao compra = ob.getByCode(opcaoCompra);
		Opcao venda = ob.getByCode(opcaoVenda);
		
		return getTravaAlta(usuario, compra.getAcao(), compra, venda);
	}
	
	
	public TravaAlta getTravaAlta(User usuario, Acao acao, Opcao opcaoCompra, Opcao opcaoVenda){
		
		if (opcaoCompra.getUltimaCotacao()==null || opcaoVenda.getUltimaCotacao()==null){
			return null;
		}
		TravaAlta trava = new TravaAlta();
		trava.setUser(usuario);
		trava.setAcao(acao.getCode());
		trava.setOpcaoCompra(opcaoCompra.getCode());
		trava.setOpcaoVenda(opcaoVenda.getCode());
		trava.setValorOpcaoCompraAtual(opcaoCompra.getUltimaCotacao().getValor());
		trava.setValorOpcaoVendaAtual(opcaoVenda.getUltimaCotacao().getValor());
		trava.setValorAcaoAtual(acao.getUltimoValor());
		trava.setValorOscilacaoAcaoAtual(acao.getUltimaOscilacao());
		trava.setValorOscilacaoOpcaoCompraAtual(opcaoCompra.getUltimaCotacao().getOscilacao());
		trava.setValorOscilacaoOpcaoVendaAtual(opcaoVenda.getUltimaCotacao().getOscilacao());
		trava.setValorExercOpcaoCompra(opcaoCompra.getVlExercicio());
		trava.setValorExercOpcaoVenda(opcaoVenda.getVlExercicio());
		trava.setQuantidade(calculaQtdCompra(opcaoCompra.getUltimaCotacao().getValor(), usuario.getSaldo()));
		
		return trava;
	}
	
	public TravaAlta atualizaValores(TravaAlta trava){
		
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
	
	public TravaAlta atualizaValoresByRobo(TravaAlta trava){
		
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
		Float qtdLotes = saldoTotal / vlLote; 
		Integer qtd = Math.round(qtdLotes);
		return qtd*100;		
	}

	
	
}
