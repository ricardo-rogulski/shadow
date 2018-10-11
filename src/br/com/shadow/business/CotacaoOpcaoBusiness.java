package br.com.shadow.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.CotacaoOpcaoDAO;
import br.com.shadow.dao.OpcaoDAO;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.util.HibernateUtil;

public class CotacaoOpcaoBusiness {
	
	
	public List<CotacaoOpcao> getCotacoesByOpcao(String code){
		Session session = HibernateUtil.currentSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByOpcao(code);
		return cotacoes;
	}

	public List<CotacaoOpcao> getCotacoesByOpcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByOpcao(code);
		session.close();
		return cotacoes;
	}
	
	public List<CotacaoOpcao> getCotacoesByCodeByData(String code, Calendar dataInicial, Calendar dataFinal){
		Session session = HibernateUtil.currentSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByCodeByData(code, dataInicial, dataFinal);
		return cotacoes;
	}
	
	public void deleteByOpcao(Opcao opcao){
		Session session = HibernateUtil.currentSession();
		CotacaoOpcaoDAO cod = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);

		List<CotacaoOpcao> cotacoes = cod.getCotacoesByOpcao(opcao.getCode());
		if (cotacoes!=null){
			for (CotacaoOpcao co : cotacoes){
				cod.delete(co);
			}
		}
	}
	
	public CotacaoOpcao getUltimaCotacao(String code){
		
		List<CotacaoOpcao> cotacoes = getUltimasCotacoes(code);
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
		return cotacoes.get(cotacoes.size()-1);
	}

	
	private List<CotacaoOpcao> getUltimasCotacoes(String code){
		Calendar agora = Calendar.getInstance();
		//Tenta com 1 hora atrás.
		Calendar umaHoraAtras = Calendar.getInstance();
		umaHoraAtras.set(Calendar.HOUR_OF_DAY, umaHoraAtras.get(Calendar.HOUR_OF_DAY)-1);
		List<CotacaoOpcao> cotacoes = getCotacoesByCodeByData(code, umaHoraAtras, agora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}

		//Tenta com 1 dia atrás.
		Calendar umDiaAtras = Calendar.getInstance();
		umDiaAtras.set(Calendar.DAY_OF_MONTH, umDiaAtras.get(Calendar.DAY_OF_MONTH)-1);
		cotacoes = getCotacoesByCodeByData(code, umDiaAtras, agora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}

		//Tenta com 5 dias atrás.
		Calendar cincoDiasAtras = Calendar.getInstance();
		cincoDiasAtras.set(Calendar.DAY_OF_MONTH, cincoDiasAtras.get(Calendar.DAY_OF_MONTH)-5);
		cotacoes = getCotacoesByCodeByData(code, cincoDiasAtras, agora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}
		
		//Tenta com 10 dias atrás.
		Calendar dezDiasAtras = Calendar.getInstance();
		dezDiasAtras.set(Calendar.DAY_OF_MONTH, dezDiasAtras.get(Calendar.DAY_OF_MONTH)-10);
		cotacoes = getCotacoesByCodeByData(code, dezDiasAtras, agora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}
		return null;
		
	}


	
	public List<CotacaoOpcao> getCotacoesDoDiaByRobo(){
		Session session = HibernateUtil.openSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		Calendar oitoHorasDaMatina = Calendar.getInstance();
		oitoHorasDaMatina.set(Calendar.HOUR_OF_DAY, 8);
		
		Calendar oitoHorasDaNoite = Calendar.getInstance();
		oitoHorasDaNoite.set(Calendar.HOUR_OF_DAY, 20);
		
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByData(oitoHorasDaMatina, oitoHorasDaNoite);
		session.close();
		return cotacoes;
	}
	
	public List<CotacaoOpcao> getCotacoesDoDiaByOpcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		Calendar oitoHorasDaMatina = Calendar.getInstance();
		oitoHorasDaMatina.set(Calendar.HOUR_OF_DAY, 8);
		
		Calendar oitoHorasDaNoite = Calendar.getInstance();
		oitoHorasDaNoite.set(Calendar.HOUR_OF_DAY, 20);
		
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByOpcaoByData(code, oitoHorasDaMatina, oitoHorasDaNoite);
		session.close();
		return cotacoes;
	}

	public List<CotacaoOpcao> getCotacoesDaUltimaHoraByOpcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		Calendar umaHoraAtras = Calendar.getInstance();
		umaHoraAtras.set(Calendar.HOUR_OF_DAY, umaHoraAtras.get(Calendar.HOUR_OF_DAY)-1);
		
		Calendar daquiAUmaHora = Calendar.getInstance();
		daquiAUmaHora.set(Calendar.HOUR_OF_DAY, daquiAUmaHora.get(Calendar.HOUR_OF_DAY)+1);
		
		List<CotacaoOpcao> cotacoes = cd.getCotacoesByOpcaoByData(code, umaHoraAtras, daquiAUmaHora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}

		//Tenta com 5 dias atrás.
		Calendar cincoDiasAtras = Calendar.getInstance();
		cincoDiasAtras.set(Calendar.DAY_OF_MONTH, cincoDiasAtras.get(Calendar.DAY_OF_MONTH)-5);
		cotacoes = getCotacoesByCodeByData(code, cincoDiasAtras, daquiAUmaHora);
		if (cotacoes!=null && !cotacoes.isEmpty()){
			return cotacoes;
		}
		
		session.close();
		return cotacoes;
	}

	public CotacaoOpcao getUltimaCotacaoByOpcaoByRobo(String code){
		List<CotacaoOpcao> cotacoes = getCotacoesDaUltimaHoraByOpcaoByRobo(code);
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
		return cotacoes.get(cotacoes.size()-1);
	}

	
	public void saveByRobo(CotacaoOpcao cotacao){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		CotacaoOpcaoDAO cd = new CotacaoOpcaoDAO(session, CotacaoOpcao.class);
		cd.merge(cotacao);
		session.getTransaction().commit();
		session.close();
	}
	
	public Opcao load(Integer id){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO ed = new OpcaoDAO(session, Opcao.class);
		return (Opcao)ed.load(id);
	}

	public void delete(CotacaoOpcao cotacao){
		Session session = HibernateUtil.currentSession();
		OpcaoDAO ed = new OpcaoDAO(session, Opcao.class);
		ed.delete(cotacao);
	}

	public void deleteByRobo(CotacaoOpcao cotacao){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		OpcaoDAO ed = new OpcaoDAO(session, Opcao.class);
		ed.delete(cotacao);
		session.getTransaction().commit();
		session.close();
	}

}
