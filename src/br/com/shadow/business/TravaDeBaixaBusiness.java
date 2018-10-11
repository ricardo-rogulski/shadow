package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.TravaBaixaDAO;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.util.HibernateUtil;

public class TravaDeBaixaBusiness {
	
	
	public List<TravaBaixa> getTravaBaixasFinalizadas(){
		Session session = HibernateUtil.currentSession();
		TravaBaixaDAO cd = new TravaBaixaDAO(session, TravaBaixa.class);
		List<TravaBaixa> travasFinalizadas = new ArrayList<TravaBaixa>();
		List<TravaBaixa> travas = cd.getTravasDeBaixa();
		if (travas!=null && !travas.isEmpty()){
			for(TravaBaixa trava : travas){
				if (trava.getDataVenda()!=null){
					travasFinalizadas.add(trava);
				}
			}
		}
		return travasFinalizadas;
	}

	public List<TravaBaixa> getTravaBaixasPendentes(){
		Session session = HibernateUtil.currentSession();
		TravaBaixaDAO cd = new TravaBaixaDAO(session, TravaBaixa.class);
		List<TravaBaixa> travas = cd.getTravasDeBaixa();
		List<TravaBaixa> travasPendentes = new ArrayList<TravaBaixa>();
		if (travas!=null && !travas.isEmpty()){
			for(TravaBaixa trava : travas){
				if (trava.getDataVenda()==null){
					travasPendentes.add(trava);
				}
			}
		}
		return travasPendentes;
	}
	
	public List<TravaBaixa> getTravasDeBaixaByUserByPendente(String user, boolean pendente){
		Session session = HibernateUtil.currentSession();
		TravaBaixaDAO cd = new TravaBaixaDAO(session, TravaBaixa.class);
		List<TravaBaixa> travas = cd.getTravasDeBaixaByUser(user);
		List<TravaBaixa> travasSel = new ArrayList<TravaBaixa>();
		
		if (travas!=null && !travas.isEmpty()){
			for (TravaBaixa trava : travas){
				if (pendente){
					if (trava.getDataVenda()==null){
						travasSel.add(trava);
					}
				}else{
					if (trava.getDataVenda()!=null){
						travasSel.add(trava);
					}
				}
			}
		}
		return travasSel;
	}
	
	

}
