package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.dao.SerieOpcaoDAO;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.util.HibernateUtil;

public class SerieOpcaoBusiness {
	
	
	public List<SerieOpcao> getSeries(){
		Session session = HibernateUtil.currentSession();
		SerieOpcaoDAO cd = new SerieOpcaoDAO(session, SerieOpcao.class);
		return cd.getSeries();
	}
	
	public List<SerieOpcao> getSeriesByRobo(){
		Session session = HibernateUtil.openSession();
		SerieOpcaoDAO cd = new SerieOpcaoDAO(session, SerieOpcao.class);
		List<SerieOpcao> series = cd.getSeries();
		session.close();
		return series;
	}

	
	public List<SelectItem> getSeriesToComboBox(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<SerieOpcao> acoes = getSeries();
		for(SerieOpcao c : acoes){
			list.add(new SelectItem(c.getId(), c.getName()));
		}
		return list;
	}
	
	public SerieOpcao load(Integer id){
		Session session = HibernateUtil.currentSession();
		SerieOpcaoDAO ed = new SerieOpcaoDAO(session, SerieOpcao.class);
		return (SerieOpcao)ed.load(id);
	}




}
