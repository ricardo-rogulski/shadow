package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.TravaBaixaDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.util.HibernateUtil;

public class TravaBaixaRoboBusiness {
	
	public TravaBaixa getTravaBaixaPendenteByRobo(String nomeRobo){
		Session session = HibernateUtil.openSession();
		TravaBaixaDAO cd = new TravaBaixaDAO(session, TravaBaixa.class);
		List<TravaBaixa> travas = cd.getTravasDeBaixaByUser(nomeRobo);
		List<TravaBaixa> travasSel = new ArrayList<TravaBaixa>();
		
		if (travas!=null && !travas.isEmpty()){
			for (TravaBaixa trava : travas){
				if (trava.getDataVenda()==null){
					travasSel.add(trava);
				}
			}
		}
		session.close();
		if (travasSel!=null && !travasSel.isEmpty()){
			return travasSel.get(0);
		}
		return null;
	}
	
	
	public void saveByRobo(TravaBaixa trava){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		TravaBaixaDAO td = new TravaBaixaDAO(session, TravaBaixa.class);
		td.merge(trava);
		session.getTransaction().commit();
		session.close();
	}
}
