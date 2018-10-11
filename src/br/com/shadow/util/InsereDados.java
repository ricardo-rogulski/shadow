package br.com.shadow.util;

import java.util.Calendar;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import br.com.shadow.dao.StatelessDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Admin;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.User;


public class InsereDados {
	
	public static void main(String[] args) {
		insereDados();
	}
	
	public static void insereDados(){
		
		
		StatelessSession session = HibernateUtil.openStatelessSession();
		
		Transaction transaction = (Transaction) session.beginTransaction();

		
		//Administrador do Site
		StatelessDAO<User> daoUsuario = new StatelessDAO<User>(session, User.class);

		User aa = new User();
		aa.setLogin("aa");
		aa.setPass("aa");
		daoUsuario.save(aa);
		
		//Ações
		StatelessDAO<Acao> daoAcao = new StatelessDAO<Acao>(session, Acao.class);
		Acao petr = new Acao();
		petr.setName("PETRO");
		petr.setCode("PETR4");
		daoAcao.save(petr);

		Acao vale = new Acao();
		vale.setName("VALE");
		vale.setCode("VALE5");
		daoAcao.save(vale);
		
		//Serie Opção
		StatelessDAO<SerieOpcao> daoSerieOpcao = new StatelessDAO<SerieOpcao>(session, SerieOpcao.class);
		SerieOpcao se = new SerieOpcao();
		se.setName("SERIE H");
		Calendar calE = Calendar.getInstance();
		calE.set(Calendar.DAY_OF_MONTH, 18);
		calE.set(Calendar.MONTH, Calendar.AUGUST);
		calE.set(Calendar.YEAR, 2014);
		se.setDataExercicio(calE);
		daoSerieOpcao.save(se);

		//Admin
		StatelessDAO<Admin> daoAdmin = new StatelessDAO<Admin>(session, Admin.class);
		Admin adm = new Admin();
		adm.setAcao("VALE5");
		adm.setHorarioInicioCotacoes("10:30");
		adm.setHorarioFinalCotacoes("16:50");
		adm.setIntervaloCotacaoAcao(60);
		adm.setIntervaloCotacaoOpcao(40);
		adm.setIntervaloNegocios(100);
		adm.setMediaMovelMenor(6);
		adm.setMediaMovelMaior(18);
		adm.setRoboCotacaoAcaoAtivo(false);
		adm.setRoboCotacaoOpcaoAtivo(false);
		adm.setRoboNegociosAtivo(false);
		adm.setStopGain(10f);
		adm.setStopMovel(6f);
		adm.setUsarProxy(true);
		daoAdmin.save(adm);
		
		//Opção
		StatelessDAO<Opcao> daoOpcao = new StatelessDAO<Opcao>(session, Opcao.class);
		Opcao op1 = new Opcao();
		op1.setAcao(petr);
		op1.setCode("PETRH14");
		op1.setSerie(se);
		op1.setVlExercicio(13.25f);
		op1.setAtivo(true);
		daoOpcao.save(op1);

		Opcao op2 = new Opcao();
		op2.setAcao(petr);
		op2.setCode("PETRH15");
		op2.setSerie(se);
		op2.setVlExercicio(14.25f);
		op2.setAtivo(true);
		daoOpcao.save(op2);

		Opcao op3 = new Opcao();
		op3.setAcao(petr);
		op3.setCode("PETRH16");
		op3.setSerie(se);
		op3.setVlExercicio(16f);
		op3.setAtivo(true);
		daoOpcao.save(op3);

		Opcao op4 = new Opcao();
		op4.setAcao(petr);
		op4.setCode("PETRH17");
		op4.setSerie(se);
		op4.setVlExercicio(16.50f);
		op4.setAtivo(true);
		daoOpcao.save(op4);

		Opcao op5 = new Opcao();
		op5.setAcao(petr);
		op5.setCode("PETRH18");
		op5.setSerie(se);
		op5.setVlExercicio(17.50f);
		op5.setAtivo(true);
		daoOpcao.save(op5);

		Opcao op6 = new Opcao();
		op6.setAcao(petr);
		op6.setCode("PETRH19");
		op6.setSerie(se);
		op6.setVlExercicio(18.50f);
		op6.setAtivo(true);
		daoOpcao.save(op6);

		Opcao op7 = new Opcao();
		op7.setAcao(petr);
		op7.setCode("VALEH25");
		op7.setSerie(se);
		op7.setVlExercicio(25.17f);
		op7.setAtivo(true);
		daoOpcao.save(op7);
		
		Opcao op8 = new Opcao();
		op8.setAcao(petr);
		op8.setCode("VALEH26");
		op8.setSerie(se);
		op8.setVlExercicio(26.17f);
		op8.setAtivo(true);
		daoOpcao.save(op8);

		Opcao op9 = new Opcao();
		op9.setAcao(petr);
		op9.setCode("VALEH27");
		op9.setSerie(se);
		op9.setVlExercicio(27.17f);
		op9.setAtivo(true);
		daoOpcao.save(op9);

		Opcao op10 = new Opcao();
		op10.setAcao(petr);
		op10.setCode("VALEH29");
		op10.setSerie(se);
		op10.setVlExercicio(29.17f);
		op10.setAtivo(true);
		daoOpcao.save(op10);

		
		try{
			transaction.commit();	
			System.out.println("OK!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
