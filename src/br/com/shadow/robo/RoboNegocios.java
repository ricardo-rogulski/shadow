package br.com.shadow.robo;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.shadow.regra.geral.Regra;
import br.com.shadow.regra.trava.RegraA;
import br.com.shadow.regra.trava.RegraC;
import br.com.shadow.regra.trava.RegraD;
import br.com.shadow.util.Util;

public class RoboNegocios extends Regra implements Job{
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		if (isBolsaAberta() && jaPegouCotacoes()){
			Long inicio = new Date().getTime();

			executaRegra_A();
			
			executaRegra_C();
			
			executaRegra_D();
						
			Long fim = new Date().getTime();
			System.out.println(Util.getDataAsDiaMesAnoHoraMinuto()+" - Execução das regras em: "+(fim-inicio)+" ms");

		}
	}
	
	private void executaRegra_A(){
		try{
			RegraA regraA = new RegraA();
			regraA.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

	private void executaRegra_C(){
		try{
			RegraC regraC = new RegraC();
			regraC.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	private void executaRegra_D(){
		try{
			RegraD regraD = new RegraD();
			regraD.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	


}
