package br.com.shadow.regra.seco;

import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.OpcaoSeco;
import br.com.shadow.regra.geral.Regra;
import br.com.shadow.vo.MediaMovelVO;

public class RegraS1 extends Regra{
	
	/*
	 * Regra A:
	 * 
	 * Compra: 
	 *   Verifica se a opção está com média móvel favorável.
	 *   Comprar.
	 * 
	 * Venda: 
	 *   Se a opção não tiver mais a média móvel favorável;
	 *   ou Stop (Gain, Loss ou Móvel)
	 *   ou 
	 *   Horário. 
	 *
	 */
	
	private final String NOME_REGRA = "RegraA";
	private final String OPCAO = "VALEH32";
	private final Integer STOP_GAIN = 10;
	private final Integer STOP_LOSS = 5;
	private final Integer STOP_MOVEL = 3;
	private final boolean DORME_COMPRADO = true;
	private final String HORARIO_MAXIMO_COMPRA = "15:30";
	private final String HORARIO_MAXIMO_VENDA = "16:30";
	private final Integer MM_MENOR = 6;
	private final Integer MM_MAIOR = 18;
	
	
	
	//
	public void execute(){
		//TradeBusiness tradeBusiness = new TradeBusiness();
		//OpcaoSeco opcao = opcaoRoboBusiness.getOpcaoSecoPendenteByRobo(NOME_REGRA);
		//if (opcao!=null){
			//verificaSeVende(opcao);
		//}else{
			verificaSeCompra();				
		//}
	}
	
	//NOT OK
	private void verificaSeCompra(){
		/*
		 *   Buscar a ação com a média móvel mais favorável.
		 *   Buscar a opção mais perto do dinheiro.
		 *   Comprar.
		 */
		if (isHoraDeVender()){
			return;
		}
		
		MediaMovelBusiness mediaMovelBusiness = new MediaMovelBusiness();
		MediaMovelVO mediaMovel = mediaMovelBusiness.getMelhorMediaMovelByRobo();
		Acao acao = mediaMovel.getAcao();
		
		
		
		
	}
	
	//NOT OK
	private void verificaSeVende(OpcaoSeco opcao){
		/*
		 *   Horário. Não dormir comprado.
		 *   ou 
		 * 	 ação com média móvel desfavorável;
		 *   ou 
		 *   Se outra opção tiver pontuação maior;
		 */
	}
	
	
	
	

}
