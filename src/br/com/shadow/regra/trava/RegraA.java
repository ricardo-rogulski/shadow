package br.com.shadow.regra.trava;

import java.util.List;

import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.business.TravaAltaRoboBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.factory.TravaAltaBeanFactory;

public class RegraA extends RegraAlta{
	
	/*
	 * Regra AA:
	 * 
	 * Compra: 
	 *   Buscar as ações com média móvel favorável.
	 *   Selecionar as travas possíveis das ações selecionadas;
	 *   Fazer negócio na que tem a maior pontuação.
	 * 
	 * Venda: 
	 *   Se a ação não tiver mais a média móvel favorável (10 e 30);
	 *   ou 
	 *   Se outra opção da mesma ação tiver pontuação maior;
	 *   ou 
	 *   ou Horário. Não dormir comprado.
	 *
	 */
	
	private final String NOME_REGRA = "Regra_A";
	
	
	//OK
	public void execute(){
		TravaAltaRoboBusiness travaRoboBusiness = new TravaAltaRoboBusiness();
		TravaAlta trava = travaRoboBusiness.getTravaPendenteByRobo(NOME_REGRA);
		if (trava!=null){
			verificaSeDestrava(trava);
		}else{
			verificaSeTrava();				
		}
	}
	
	//OK
	private void verificaSeTrava(){
		/*
		 *   Buscar as ações com média móvel favorável.
		 *   Selecionar as travas possíveis das ações selecionadas;
		 *   Fazer negócio na que tem a maior pontuação.
		 */
		if (isHoraDeVender()){
			return;
		}
		MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
		List<Acao> acoesComMMFavoravel = mediaMovelBusiness.getAcoesComMMFavoravel();
		if (acoesComMMFavoravel==null || acoesComMMFavoravel.isEmpty()){
			return;
		}
		TravaAltaRoboBusiness travaRoboBusiness = new TravaAltaRoboBusiness();
		List<TravaAlta> travas = travaRoboBusiness.getTravasDeAltaPossiveisByRobo(NOME_REGRA, acoesComMMFavoravel);
		TravaAlta travaDeMaiorPontuacao = travaRoboBusiness.getTravaComMaiorPontuacao(travas);
		if (travaDeMaiorPontuacao!=null){
			fazTrava(travaDeMaiorPontuacao);			
		}
	}
	
	//OK
	private void verificaSeDestrava(TravaAlta trava){
		/*
		 *   Horário. Não dormir comprado.
		 *   ou 
		 * 	 ação com média móvel desfavorável;
		 *   ou 
		 *   Se outra opção tiver pontuação maior;
		 */
		if (isHoraDeVender()){
			desfazTrava(trava, HORA_DE_VENDER);
			return;
		}
		
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		TravaAlta travaAtualizada = tbf.atualizaValoresByRobo(trava);
		
		//Só verifica se sai, se o lucro for diferente de 0.
		if(travaAtualizada.getLucroAtualPCT()==0){
			return;
		}
		
		MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
		List<Acao> acoesComMMFavoravel = mediaMovelBusiness.getAcoesComMMFavoravel();
		if (acoesComMMFavoravel==null || acoesComMMFavoravel.isEmpty()){
			desfazTrava(trava, ACAO_FICOU_COM_MM_DESF);
			return;
		}
		boolean achou = false;
		for(Acao acao : acoesComMMFavoravel){
			if (acao.getCode().equals(trava.getAcao())){
				achou = true;
			}
		}
		if (!achou){
			desfazTrava(trava, ACAO_FICOU_COM_MM_DESF);
			return;
		}
		TravaAltaRoboBusiness travaRoboBusiness = new TravaAltaRoboBusiness();
		List<TravaAlta> travas = travaRoboBusiness.getTravasDeAltaPossiveisByRobo(NOME_REGRA, acoesComMMFavoravel);
		TravaAlta travaDeMaiorPontuacao = travaRoboBusiness.getTravaComMaiorPontuacao(travas);
		if (!travaDeMaiorPontuacao.equals(trava)){
			desfazTrava(trava, OUTRA_TRAVA_MELHOR_PONTUACAO);
			//fazTrava(travaDeMaiorPontuacao);
		}
	}


}
