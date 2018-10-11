package br.com.shadow.regra.trava;

import java.util.ArrayList;
import java.util.List;

import br.com.shadow.business.AcaoBusiness;
import br.com.shadow.business.AdminBusiness;
import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.business.TravaAltaRoboBusiness;
import br.com.shadow.business.TravaBaixaRoboBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.factory.TravaBaixaBeanFactory;
import br.com.shadow.regra.geral.Regra;
import br.com.shadow.vo.MediaMovelVO;

public class RegraC extends Regra{
	
	/*
	 * Regra CC:
	 * 
	 * Compra: Verifica as médias móveis positivas e montar trava de alta na que tem a maior pontuação.
	 * Se não tiver media movel positiva, montar trava de baixa na opção com o maior valor extrinsico.
	 * 
	 * Venda: 
	 * 	 Travas de alta:
	 *   	ou Stop Gain
	 *   	ou Stop Loss 
	 *   	ou Horário.
	 *   	ou Ação ficou com média móvel desfavorável
	 *   	ou Outra trava com pontuação maior.
	 *   
	 *   Travas de baixa:
	 *		Ação ficou com média móvel positiva.
	 *   	ou Stop Gain
	 *   	ou Stop Loss 
	 *   	ou Horário.
	 */
	
	private String NOME_REGRA = "Regra_C";
	private Integer STOP_GAIN = 10;
	private Integer STOP_LOSS = -10;
	

	
	public void execute(){
		TravaAltaRoboBusiness travaAltaRoboBusiness = new TravaAltaRoboBusiness();
		TravaBaixaRoboBusiness travaBaixaRoboBusiness = new TravaBaixaRoboBusiness();
				
		TravaAlta travaAlta = travaAltaRoboBusiness.getTravaPendenteByRobo(NOME_REGRA);
		TravaBaixa travaBaixa = travaBaixaRoboBusiness.getTravaBaixaPendenteByRobo(NOME_REGRA);
		
		
		if (travaAlta==null && travaBaixa==null){
			//Não tem nenhuma trava pendente.
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
		//Não comprar depois da hora de vender.
		if (isHoraDeVender()){
			return;
		}
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> positivas = new ArrayList<Acao>();
		for(Acao acao : acoes){
			if (isMediaMovelPositiva(acao.getCode())){
				positivas.add(acao);
			}
		}
		if (positivas!=null && !positivas.isEmpty()){
			//Faz trava de alta.
			RegraAlta regraAlta = new RegraAlta();
			regraAlta.fazTrava(NOME_REGRA, positivas);
		}else{
			//Faz trava de baixa.
			RegraBaixa regraBaixa = new RegraBaixa();
			regraBaixa.fazTrava(NOME_REGRA);
		}
	}
	
	private void verificaSeDestrava(TravaAlta trava){
		RegraAlta regraAlta = new RegraAlta();
		if (isHoraDeVender()){
			regraAlta.desfazTrava(trava, HORA_DE_VENDER);
			return;
		}
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		TravaAlta travaAtualizada = tbf.atualizaValoresByRobo(trava);
		
		//Só verifica se sai, se o lucro for diferente de 0.
		if(travaAtualizada.getLucroAtualPCT()==0){
			return;
		}
		
		if (travaAtualizada.getLucroAtualPCT()>=STOP_GAIN){
			regraAlta.desfazTrava(travaAtualizada, "Stop Gain!");
			return;
		}
		if (travaAtualizada.getLucroAtualPCT()<=STOP_LOSS){
			regraAlta.desfazTrava(travaAtualizada, "Stop Loss!");
			return;
		}
		if (!isMediaMovelPositiva(trava.getAcao())){
			regraAlta.desfazTrava(trava, ACAO_FICOU_COM_MM_DESF);
			return;
		}
		
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> positivas = new ArrayList<Acao>();
		for(Acao acao : acoes){
			if (isMediaMovelPositiva(acao.getCode())){
				positivas.add(acao);
			}
		}
		
		if (positivas!=null && !positivas.isEmpty()){
			TravaAltaRoboBusiness travaRoboBusiness = new TravaAltaRoboBusiness();
			List<TravaAlta> travas = travaRoboBusiness.getTravasDeAltaPossiveisByRobo(NOME_REGRA, positivas);
			TravaAlta travaDeMaiorPontuacao = travaRoboBusiness.getTravaComMaiorPontuacao(travas);
			if (!travaDeMaiorPontuacao.equals(travaAtualizada)){
				regraAlta.desfazTrava(travaAtualizada, OUTRA_TRAVA_MELHOR_PONTUACAO);
			}
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
		
		//Só verifica se sai, se o lucro for diferente de 0.
		if(travaAtualizada.getLucroAtualPCT()==0){
			return;
		}
		
		if (isMediaMovelPositiva(trava.getAcao())){
			regraBaixa.desfazTrava(trava, ACAO_FICOU_COM_MM_FAV);
			return;
		}
		
		if (travaAtualizada.getLucroAtualPCT()>=STOP_GAIN){
			regraBaixa.desfazTrava(travaAtualizada, "Stop Gain!");
			return;
		}
		if (travaAtualizada.getLucroAtualPCT()<=STOP_LOSS){
			regraBaixa.desfazTrava(travaAtualizada, "Stop Loss!");
			return;
		}
	}

	private boolean isMediaMovelPositiva(String acao){
		MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
		MediaMovelVO media = mediaMovelBusiness.getMediaMovelByRobo(acao);
		if (media!=null && media.getFator()>1){
			return true;
		}
		return false;

	}

	
	
	
	
	
	

}
