package br.com.shadow.robo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import br.com.shadow.business.AdminBusiness;
import br.com.shadow.entity.Admin;



public class Gatilho implements ServletContextListener{
	
	long intervaloCotacoesAcoes; 
	long intervaloCotacoesOpcoes; 
	long intervaloNegocios; 
	long intervaloUmaHora = 60*60*1000;
	long intervaloUmMinuto = 1*60*1000;
	
	
	public void run(){
    	try{
    		//Pega no Admin os valores.
    		AdminBusiness ab = new AdminBusiness();
    		Admin admin = ab.getAdminByRobo();
    		if (admin != null){
        		intervaloCotacoesAcoes = admin.getIntervaloCotacaoAcao()*1000;
        		intervaloCotacoesOpcoes = admin.getIntervaloCotacaoOpcao()*1000;    	
        		intervaloNegocios = admin.getIntervaloNegocios()*1000;
        		
                SchedulerFactory sf = new StdSchedulerFactory();
                Scheduler sched = sf.getScheduler();
                
                
                if (admin.isRoboCotacaoAcaoAtivo()){
                //if (false){                	
                	//Ações.
                    JobDetail job1 = new JobDetail("job1", "group1", RoboCotacoesAcoes.class);            	
                    SimpleTrigger st1 = new SimpleTrigger("myTrigger1", SimpleTrigger.REPEAT_INDEFINITELY, intervaloCotacoesAcoes);
                    sched.scheduleJob(job1, st1);
                }
                
                if (admin.isRoboCotacaoOpcaoAtivo()){
                //if (false){
                	//Opções.
                    JobDetail job2 = new JobDetail("job2", "group1", RoboCotacoesOpcoes.class);            	
                    SimpleTrigger st2 = new SimpleTrigger("myTrigger2", SimpleTrigger.REPEAT_INDEFINITELY, intervaloCotacoesOpcoes);
                    sched.scheduleJob(job2, st2);
                }
                
                //if (admin.isRoboNegociosAtivo()){
                if (false){
                	//RoboNegocios.
                    JobDetail job3 = new JobDetail("job3", "group1", RoboNegocios.class);            	
                    SimpleTrigger st3 = new SimpleTrigger("myTrigger3", SimpleTrigger.REPEAT_INDEFINITELY, intervaloNegocios);
                    sched.scheduleJob(job3, st3);
                }
                
                
                sched.start();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		run();		
	}

}
