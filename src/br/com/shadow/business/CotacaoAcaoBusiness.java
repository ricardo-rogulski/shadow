package br.com.shadow.business;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.AcaoDAO;
import br.com.shadow.dao.CotacaoAcaoDAO;
import br.com.shadow.dao.CotacaoOpcaoDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.CotacaoOpcao;
import br.com.shadow.util.HibernateUtil;

public class CotacaoAcaoBusiness {
	
	
	public List<CotacaoAcao> getCotacoesByAcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		List<CotacaoAcao> cotacoes = cd.getCotacoesByAcao(code);
		session.close();
		return cotacoes;
	}


	public List<CotacaoAcao> getCotacoesDoDiaByAcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		
		Calendar oitoHorasDaMatina = Calendar.getInstance();
		oitoHorasDaMatina.set(Calendar.HOUR_OF_DAY, 8);
		
		Calendar oitoHorasDaNoite = Calendar.getInstance();
		oitoHorasDaNoite.set(Calendar.HOUR_OF_DAY, 20);
		
		List<CotacaoAcao> cotacoes = cd.getCotacoesByCodeByData(code, oitoHorasDaMatina, oitoHorasDaNoite);
		session.close();
		return cotacoes;
	}

	
	public List<CotacaoAcao> getCotacoesDoDiaByAcao(String code){
		Session session = HibernateUtil.currentSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		
		Calendar oitoHorasDaMatina = Calendar.getInstance();
		oitoHorasDaMatina.set(Calendar.HOUR_OF_DAY, 8);
		
		//oitoHorasDaMatina.set(Calendar.DAY_OF_MONTH, oitoHorasDaMatina.get(Calendar.DAY_OF_MONTH)-1);
		
		Calendar oitoHorasDaNoite = Calendar.getInstance();
		oitoHorasDaNoite.set(Calendar.HOUR_OF_DAY, 20);
		
		List<CotacaoAcao> cotacoes = cd.getCotacoesByCodeByData(code, oitoHorasDaMatina, oitoHorasDaNoite);
		return cotacoes;
	}
	
	public List<CotacaoAcao> getCotacoesByAcao(String code){
		Session session = HibernateUtil.currentSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		List<CotacaoAcao> cotacoes = cd.getCotacoesByAcao(code);
		return cotacoes;
	}

	
	public List<CotacaoAcao> getCotacoesByCodeByData(String code, Calendar dataInicial, Calendar dataFinal){
		Session session = HibernateUtil.currentSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		List<CotacaoAcao> cotacoes = cd.getCotacoesByCodeByData(code, dataInicial, dataFinal);
		return cotacoes;
	}
	
	public CotacaoAcao getUltimaCotacao(String code){
		
		List<CotacaoAcao> cotacoes = getUltimasCotacoes(code);
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
		return cotacoes.get(cotacoes.size()-1);
	}

	private List<CotacaoAcao> getUltimasCotacoes(String code){
		Calendar agora = Calendar.getInstance();
		//Tenta com 1 hora atrás.
		Calendar umaHoraAtras = Calendar.getInstance();
		umaHoraAtras.set(Calendar.HOUR_OF_DAY, umaHoraAtras.get(Calendar.HOUR_OF_DAY)-1);
		List<CotacaoAcao> cotacoes = getCotacoesByCodeByData(code, umaHoraAtras, agora);
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
	
	public List<CotacaoAcao> getCotacoesDaUltimaHoraByAcaoByRobo(String code){
		Session session = HibernateUtil.openSession();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		Calendar umaHoraAtras = Calendar.getInstance();
		umaHoraAtras.set(Calendar.HOUR_OF_DAY, umaHoraAtras.get(Calendar.HOUR_OF_DAY)-1);
		
		Calendar daquiAUmaHora = Calendar.getInstance();
		daquiAUmaHora.set(Calendar.HOUR_OF_DAY, daquiAUmaHora.get(Calendar.HOUR_OF_DAY)+1);
		
		List<CotacaoAcao> cotacoes = cd.getCotacoesByCodeByData(code, umaHoraAtras, daquiAUmaHora);
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

	
	public CotacaoAcao getUltimaCotacaoByAcaoByRobo(String code){
		List<CotacaoAcao> cotacoes = getCotacoesDaUltimaHoraByAcaoByRobo(code);
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
		return cotacoes.get(cotacoes.size()-1);
	}
	

	public void saveByRobo(CotacaoAcao cotacao){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		CotacaoAcaoDAO cd = new CotacaoAcaoDAO(session, CotacaoAcao.class);
		cd.merge(cotacao);
		session.getTransaction().commit();
		session.close();
	}
	
	public Acao load(Integer id){
		Session session = HibernateUtil.currentSession();
		AcaoDAO ed = new AcaoDAO(session, Acao.class);
		return (Acao)ed.load(id);
	}

	

}
