package br.com.shadow.regra.geral;

import java.util.Calendar;
import java.util.Date;

import br.com.shadow.business.AdminBusiness;
import br.com.shadow.entity.Admin;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.factory.TravaAltaBeanFactory;

public abstract class Regra{
	
	private final Integer HORA_VENDA = 16;
	private final Integer STOP_LOSS = -10;
	private final Integer TEMPO_MINIMO_PARA_TROCA = 1000*60*20;
	
	protected final String HORA_DE_VENDER = "Horário.";
	protected final String OUTRA_TRAVA_MELHOR_PONTUACAO = "Outra trava com pontuação melhor";
	protected final String ACAO_FICOU_NEGATIVA = "Ação com oscilação negativa.";
	protected final String ACAO_FICOU_POSITIVA = "Ação com oscilação positiva.";
	protected final String STOP_LOSS_ACIONADO = "Stop Loss.";
	protected final String OUTRA_ACAO_MM_MELHOR = "Outra ação com média móvel melhor.";
	protected final String ACAO_FICOU_COM_MM_DESF = "Ação ficou com média móvel desfavorável";
	protected final String ACAO_FICOU_COM_MM_FAV = "Ação ficou com média móvel favorável";
	protected final String OUTRA_OPCAO_MAIOR_VALOR_EXT = "Outra opção com maior valor extrinsico.";

	protected String username = "rrogulsk"; 
	protected String password = "rrCasa200"; 
	protected String proxyHost = "webcache.sjk.emb"; 
	protected String proxyPort = "8080"; 

	
	protected boolean useProxy(){
		AdminBusiness ab = new AdminBusiness();
		Admin admin = ab.getAdminByRobo();
		boolean use = true;
		if (admin != null){
			use = admin.isUsarProxy();
		}
		return use;    		
	}
	
	protected boolean isBolsaAberta(){
		//Verifica se é dia de semana.
		/*
		Calendar calendar = Calendar.getInstance();
		Integer diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK); 
		if (diaDaSemana < 2 || diaDaSemana > 6){
			return false;
		}
		//Verifica se está entre 10:00hs e 17:00hs.
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
		//System.out.println("Hora: "+hora);
		if (hora < 10 || hora > 17){
			return false;
		}
		*/
		return true;	
	}
	
	protected boolean jaPegouCotacoes(){
		Calendar calendar = Calendar.getInstance();
		//Verifica o horário é depois das 10:45hs
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
		Integer minuto = calendar.get(Calendar.MINUTE);
		//if (hora==10){
			//Verificar minutos.
		//	if(minuto>=45){
		//		return true;
		//	}
		//}
		if (hora >= 11){
			return true;
		}
		return false;	
	}
	
	protected boolean isHoraDeVender(){
		Calendar calendar = Calendar.getInstance();
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
		if (hora>HORA_VENDA){
			return true;
		}
		return false;
	}
	
	protected boolean isStopLoss(TravaAlta trava){
		if (trava.getLucroAtualPCT()<=STOP_LOSS){
			return true;
		}
		return false;
	}
	
	protected boolean jaPassouTempoMinimoNoNegocio(TravaAlta trava){
		Long horaAtual = new Date().getTime();
		Long horaTrava = trava.getDataCompra().getTime().getTime();
		
		if (horaAtual > horaTrava+TEMPO_MINIMO_PARA_TROCA){
			return true;
		}
		return false;
		
	}
	
	protected void showMsgTrocaPorPontos(TravaAlta travaAtual, TravaAlta novaTrava){
		String robo = travaAtual.getUser().getLogin();
		String travaDesf = travaAtual.getOpcaoCompra()+" "+travaAtual.getOpcaoVenda();
		String pontuacaoAtual = ""+travaAtual.getPontuacao();
		
		String travaNova = novaTrava.getOpcaoCompra()+" "+novaTrava.getOpcaoVenda();
		String pontuacaoNova = ""+novaTrava.getPontuacao();
		
		System.out.println("************************************************************************************************");
		System.out.println("\n Robo: "+robo+" Desfazendo trava: "+travaDesf+" Pontos: "+pontuacaoAtual);
		System.out.println("\n Para travar: "+travaNova+" Pontos: "+pontuacaoNova);
		System.out.println("************************************************************************************************");
	}


}
