package br.com.shadow.util;

import java.util.Calendar;

import br.com.shadow.business.TravaDeAltaBusiness;
import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.handler.LoginHandler;

public class TravaUtil {

	public static final Integer CORRETAGEM = 10;
	

	public void fazTrava(TravaAlta trava){
		trava.setDataCompra(Calendar.getInstance());
		trava.setPontuacaoInicial(trava.getPontuacao());
		trava.setValorAcaoInicial(trava.getValorAcaoAtual());
		trava.setValorOpcaoCompraInicial(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaInicial(trava.getValorOpcaoVendaAtual());

		TravaDeAltaBusiness tb = new TravaDeAltaBusiness();
		tb.save(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario -= trava.getValorNegocio();
		trava.getUser().setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUser(trava.getUser());
		
		//Atualiza saldo do usuário no Handler.
		Util util = new Util();
		LoginHandler lh = (LoginHandler)util.getHandler("loginHandler");
		lh.setUser(trava.getUser());
	}
	
	public void desfazTrava(TravaAlta trava){
		trava.setDataVenda(Calendar.getInstance());
		trava.setPontuacaoFinal(trava.getPontuacao());
		trava.setValorAcaoFinal(trava.getValorAcaoAtual());
		trava.setValorOpcaoCompraFinal(trava.getValorOpcaoCompraAtual());
		trava.setValorOpcaoVendaFinal(trava.getValorOpcaoVendaAtual());

		TravaDeAltaBusiness tb = new TravaDeAltaBusiness();
		tb.save(trava);
			
		Float saldoUsuario = trava.getUser().getSaldo();
		saldoUsuario += trava.getValorNegocio();
		trava.getUser().setSaldo(saldoUsuario);
		
		UserBusiness ub = new UserBusiness();
		ub.salvaUser(trava.getUser());
		
		//Atualiza saldo do usuário no Handler.
		Util util = new Util();
		LoginHandler lh = (LoginHandler)util.getHandler("loginHandler");
		lh.setUser(trava.getUser());
	}
	
	private Integer getDiasParaVencimentoSerie(SerieOpcao serie){
		Calendar now = Calendar.getInstance();
		Calendar vencto = serie.getDataExercicio();
		Integer diasParaVencto = vencto.get(Calendar.DAY_OF_YEAR)-now.get(Calendar.DAY_OF_YEAR);
		return diasParaVencto;
	}

	
}
