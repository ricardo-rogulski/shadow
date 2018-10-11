package br.com.shadow.regra.trava;

import java.util.ArrayList;
import java.util.List;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.business.TravaAltaRoboBusiness;
import br.com.shadow.business.TravaBaixaRoboBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.factory.TravaBaixaBeanFactory;
import br.com.shadow.regra.geral.Regra;
import br.com.shadow.vo.MediaMovelVO;

public class RegraD extends Regra{
	
	/*
	 * Regra D:
	 * 
	 * Compra: 
	 *   Verificar as a��es com oscila��o positiva e com as medias moveis positivas, e montar travas
	 *   de alta na que tem a maior pontua��o.
	 *   
	 *   Se n�o existir a��es nessa condi��o, verificar a��es com oscila��o negativa e com as m�dias 
	 *   moveis negativas, montar trava de baixa na que tem o maior valor extrinsico.
	 * 
	 * Venda: 
	 * 	 Travas de alta:
	 *   	ou Stop Gain
	 *   	ou Stop Loss 
	 *   	ou Hor�rio.
	 *   
	 *   Travas de baixa:
	 *   	ou Stop Gain
	 *   	ou Stop Loss 
	 *   	ou Hor�rio.
	 *   
	 *   */
	
	private String NOME_REGRA = "Regra_D";
	private Integer STOP_GAIN = 10;
	private Integer STOP_LOSS = -10;

	
	public void execute(){
		TravaAltaRoboBusiness travaAltaRoboBusiness = new TravaAltaRoboBusiness();
		TravaBaixaRoboBusiness travaBaixaRoboBusiness = new TravaBaixaRoboBusiness();
		
		TravaAlta travaAlta = travaAltaRoboBusiness.getTravaPendenteByRobo(NOME_REGRA);
		TravaBaixa travaBaixa = travaBaixaRoboBusiness.getTravaBaixaPendenteByRobo(NOME_REGRA);
		
		if (travaAlta==null && travaBaixa==null){
			//N�o tem nenhuma trava pendente.
			verificaSeTrava();
		}
		if (travaAlta!=null){
			verificaSeDestrava(travaAlta);
		}
		if (travaBaixa!=null){
			verificaSeDestrava(travaBaixa);
		}
		
	}
	
	private void verificaSeTrava(){
		//N�o comprar depois da hora de vender.
		if (isHoraDeVender()){
			return;
		}
		if (!tentaFazerTravaDeAlta()){
			tentaFazerTravaDeBaixa();
		}
	}
	
	private boolean tentaFazerTravaDeAlta(){
		
		//Busca as a��es com oscila��o positiva.
		List<Acao> acoesPositivas = getAcoesComOscilacaoPositiva();
		if (acoesPositivas==null || acoesPositivas.isEmpty()){
			return false;
		}
		
		//Dentre as a��es com oscila��o positiva, verifica quais est�o com mm favor�vel
		// e com o valor da a��o acima das m�dias m�veis.
		List<Acao> mmPositivas = new ArrayList<Acao>();
		
		for(Acao acao : acoesPositivas){
			MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
			MediaMovelVO media = mediaMovelBusiness.getMediaMovelByRobo(acao);
			if (media!=null && media.getFator()>1){
				mmPositivas.add(acao);
			}
		}
		if (mmPositivas==null || mmPositivas.isEmpty()){
			return false;
		}

		//Faz trava de alta.
		RegraAlta regraAlta = new RegraAlta();
		regraAlta.fazTrava(NOME_REGRA, mmPositivas);
		return true;
	}
	
	private boolean tentaFazerTravaDeBaixa(){
		
		//Busca as a��es com oscila��o negativa.
		List<Acao> acoesNegativas = getAcoesComOscilacaoNegativa();
		if (acoesNegativas==null || acoesNegativas.isEmpty()){
			return false;
		}
		
		//Dentre as a��es com oscila��o negativa, verifica quais est�o com mm desfavor�vel.
		List<Acao> mmNegativas = new ArrayList<Acao>();
		for(Acao acao : acoesNegativas){
			MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
			MediaMovelVO media = mediaMovelBusiness.getMediaMovelByRobo(acao);
			if (media != null && media.getFator()<1){
				mmNegativas.add(acao);
			}
		}
		
		if (mmNegativas==null || mmNegativas.isEmpty()){
			return false;
		}
		
		//Verifica as travas de baixa poss�veis.
		RegraBaixa regraBaixa = new RegraBaixa();
		Opcao opcaoComMaiorValorExtrinsico = regraBaixa.getOpcaoComMaiorValorExtrinsico(mmNegativas);
		Opcao umaAcima = regraBaixa.getUmaAcima(opcaoComMaiorValorExtrinsico);
		
		TravaBaixaBeanFactory tbbf = new TravaBaixaBeanFactory();
		TravaBaixa trava = tbbf.getTravaBaixaByRobo(NOME_REGRA, umaAcima, opcaoComMaiorValorExtrinsico);
		regraBaixa.fazTrava(trava);

		return true;
	}
	
	
	private void verificaSeDestrava(TravaAlta trava){
		RegraAlta regraAlta = new RegraAlta();
		if (isHoraDeVender()){
			regraAlta.desfazTrava(trava, HORA_DE_VENDER);
			return;
		}
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		TravaAlta travaAtualizada = tbf.atualizaValoresByRobo(trava);
		
		if (travaAtualizada.getLucroAtualPCT()>=STOP_GAIN){
			regraAlta.desfazTrava(travaAtualizada, "Stop Gain!");
			return;
		}
		if (travaAtualizada.getLucroAtualPCT()<=STOP_LOSS){
			regraAlta.desfazTrava(travaAtualizada, "Stop Loss!");
			return;
		}
	}
	
	private void verificaSeDestrava(TravaBaixa trava){
		RegraBaixa regraBaixa = new RegraBaixa();
		if (isHoraDeVender()){
			regraBaixa.desfazTrava(trava, HORA_DE_VENDER);
			return;
		}
		TravaBaixaBeanFactory tbf = new TravaBaixaBeanFactory();
		TravaBaixa travaAtualizada = tbf.atualizaValoresByRobo(trava);

		if (travaAtualizada.getLucroAtualPCT()>=STOP_GAIN){
			regraBaixa.desfazTrava(travaAtualizada, "Stop Gain!");
			return;
		}
		if (travaAtualizada.getLucroAtualPCT()<=STOP_LOSS){
			regraBaixa.desfazTrava(travaAtualizada, "Stop Loss!");
			return;
		}
	}

	
	
	//OK
	private List<Acao> getAcoesComOscilacaoPositiva(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> acoesPositivas = new ArrayList<Acao>();
		
		for(Acao acao : acoes){
			CotacaoAcao cotacao = acao.getUltimaCotacaoByRobo();
			if (cotacao!=null && cotacao.getOscilacao()>0){
				acoesPositivas.add(acao);
			}
		}
		return acoesPositivas;
	}
	
	private List<Acao> getAcoesComOscilacaoNegativa(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> acoesPositivas = new ArrayList<Acao>();
		
		for(Acao acao : acoes){
			CotacaoAcao cotacao = acao.getUltimaCotacaoByRobo();
			if (cotacao!=null && cotacao.getOscilacao()<0){
				acoesPositivas.add(acao);
			}
		}
		return acoesPositivas;
	}
	
	
}
