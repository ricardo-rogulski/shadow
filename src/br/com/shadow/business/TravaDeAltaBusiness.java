package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.TravaAltaDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.util.HibernateUtil;

public class TravaDeAltaBusiness {
	
	private List<Acao> acoes = new ArrayList<Acao>();
	private List<SerieOpcao> series = new ArrayList<SerieOpcao>();
	
	public List<TravaAlta> getTravasDeAltaPossiveis(User usuario){
		OpcaoBusiness ob = new OpcaoBusiness();
		List<TravaAlta> travas = new ArrayList<TravaAlta>();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();

		for(SerieOpcao serie : getSeries()){
			for(Acao acao : getAcoes()){
				List<Opcao> opcoes = ob.getBySerieByAcaoOrderByVlExerc(serie.getId(), acao.getId());
				if (opcoes!=null && opcoes.size()>1){
					for(int i=0; i<opcoes.size()-1; i++){
						Opcao compra = opcoes.get(i);
						Opcao venda = opcoes.get(i+1);

						TravaAlta trava = tbf.getTravaAlta(usuario, acao, compra, venda);
						if (trava!=null){
							travas.add(trava);	
						}
						
					}
				}
			}
		}
		return travas;		
	}
	
	public List<SerieOpcao> getSeries() {
		if (series.isEmpty()){
			SerieOpcaoBusiness sob = new SerieOpcaoBusiness();
			series = sob.getSeries();
		}
		return series;		
	}
	
	public List<Acao> getAcoes(){
		if (acoes.isEmpty()){
			AcaoBusiness ab = new AcaoBusiness();
			acoes = ab.getAcoes();
		}
		return acoes;		
	}
	
	public List<TravaAlta> getTravasDeAltaByUserByPendente(String user, boolean pendente){
		Session session = HibernateUtil.currentSession();
		TravaAltaDAO cd = new TravaAltaDAO(session, TravaAlta.class);
		List<TravaAlta> travas = cd.getTravasDeAltaByUser(user);
		List<TravaAlta> travasSel = new ArrayList<TravaAlta>();
		
		if (travas!=null && !travas.isEmpty()){
			for (TravaAlta trava : travas){
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
	
	public List<TravaAlta> getTravaAltasFinalizadas(){
		Session session = HibernateUtil.currentSession();
		TravaAltaDAO cd = new TravaAltaDAO(session, TravaAlta.class);
		List<TravaAlta> travasFinalizadas = new ArrayList<TravaAlta>();
		List<TravaAlta> travas = cd.getTravasDeAlta(); 
		if (travas!=null && !travas.isEmpty()){
			for(TravaAlta trava : travas){
				if (trava.getDataVenda()!=null){
					travasFinalizadas.add(trava);
				}
			}
		}
		return travasFinalizadas;
	}

	public List<TravaAlta> getTravaAltasPendentes(){
		Session session = HibernateUtil.currentSession();
		TravaAltaDAO cd = new TravaAltaDAO(session, TravaAlta.class);
		List<TravaAlta> travas = cd.getTravasDeAlta(); 
		List<TravaAlta> travasPendentes = new ArrayList<TravaAlta>();
		if (travas!=null && !travas.isEmpty()){
			for(TravaAlta trava : travas){
				if (trava.getDataVenda()==null){
					travasPendentes.add(trava);
				}
			}
		}
		return travasPendentes;
	}
	
	public TravaAlta load(Integer id){
		Session session = HibernateUtil.currentSession();
		TravaAltaDAO ed = new TravaAltaDAO(session, TravaAlta.class);
		return (TravaAlta)ed.load(id);
	}
	
	public void save(TravaAlta trava){
		Session session = HibernateUtil.currentSession();
		TravaAltaDAO td = new TravaAltaDAO(session, TravaAlta.class);
		td.save(trava);
	}

}
