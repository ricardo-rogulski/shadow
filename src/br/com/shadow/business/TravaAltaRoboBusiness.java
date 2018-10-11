package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.TravaAltaDAO;
import br.com.shadow.dao.TravaBaixaDAO;
import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Opcao;
import br.com.shadow.entity.SerieOpcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.util.HibernateUtil;

public class TravaAltaRoboBusiness {
	
	private List<Acao> acoes = new ArrayList<Acao>();
	private List<SerieOpcao> series = new ArrayList<SerieOpcao>();
	
	public List<TravaAlta> getTravasDeAltaPossiveisByRobo(String nomeRobo){
		OpcaoBusiness ob = new OpcaoBusiness();
		List<TravaAlta> travas = new ArrayList<TravaAlta>();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		UserBusiness ub = new UserBusiness();
		User usuario = ub.getUserByRobo(nomeRobo);
		for(SerieOpcao serie : getSeries()){
			for(Acao acao : getAcoes()){
				List<Opcao> opcoes = ob.getBySerieByAcaoByRobo(serie.getId(), acao.getId());
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
	
	public List<TravaAlta> getTravasDeAltaPossiveisByRobo(String nomeRobo, Acao acao){
		OpcaoBusiness ob = new OpcaoBusiness();
		List<TravaAlta> travas = new ArrayList<TravaAlta>();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		UserBusiness ub = new UserBusiness();
		User usuario = ub.getUserByRobo(nomeRobo);
		for(SerieOpcao serie : getSeries()){
			List<Opcao> opcoes = ob.getBySerieByAcaoByRobo(serie.getId(), acao.getId());
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
		return travas;		
	}
	
	public List<TravaAlta> getTravasDeAltaPossiveisByRobo(String nomeRobo, List<Acao> acoes){
		OpcaoBusiness ob = new OpcaoBusiness();
		List<TravaAlta> travas = new ArrayList<TravaAlta>();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		UserBusiness ub = new UserBusiness();
		User usuario = ub.getUserByRobo(nomeRobo);
		for(SerieOpcao serie : getSeries()){
			for(Acao acao : acoes){
				List<Opcao> opcoes = ob.getBySerieByAcaoByRobo(serie.getId(), acao.getId());
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
	
	public List<TravaAlta> getTravasDeAltaPossiveisByRobo(String nomeRobo, String acao){
		AcaoBusiness ab = new AcaoBusiness();
		Acao ac = ab.getByCodeByRobo(acao);
		return getTravasDeAltaPossiveisByRobo(nomeRobo, ac);		
	}

	
	public TravaAlta getTravaComMaiorPontuacao(List<TravaAlta> travas){
		if (travas==null || travas.isEmpty()){
			return null;
		}		
		Integer maiorPontuacao = -30000;
		TravaAlta travaDeMaiorPontuacao = null;
		for(TravaAlta tr : travas){
			Integer pontosTr = tr.getPontuacao();
			if(pontosTr>maiorPontuacao){
				maiorPontuacao = pontosTr;
				travaDeMaiorPontuacao = tr;
			}
		}
		return travaDeMaiorPontuacao;		
	}

	
	private List<Acao> getAcoes(){
		if (acoes.isEmpty()){
			AcaoBusiness ab = new AcaoBusiness();
			acoes = ab.getAcoesByRobo();
		}
		return acoes;		
	}
	
	private List<SerieOpcao> getSeries() {
		if (series.isEmpty()){
			SerieOpcaoBusiness sob = new SerieOpcaoBusiness();
			series = sob.getSeriesByRobo();
		}
		return series;		
	}
	
	public TravaAlta getTravaPendenteByRobo(String nomeRobo){
		Session session = HibernateUtil.openSession();
		TravaAltaDAO cd = new TravaAltaDAO(session, TravaAlta.class);
		List<TravaAlta> travas = cd.getTravasDeAltaByUser(nomeRobo); 
		List<TravaAlta> travasSel = new ArrayList<TravaAlta>();
		
		if (travas!=null && !travas.isEmpty()){
			for (TravaAlta trava : travas){
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
	
	public void saveByRobo(TravaAlta trava){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		TravaAltaDAO td = new TravaAltaDAO(session, TravaAlta.class);
		td.merge(trava);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
