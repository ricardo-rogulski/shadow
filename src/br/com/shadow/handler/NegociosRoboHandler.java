package br.com.shadow.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.business.TravaDeAltaBusiness;
import br.com.shadow.business.TravaDeBaixaBusiness;
import br.com.shadow.dao.DAO;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.entity.User;
import br.com.shadow.util.HibernateUtil;
import br.com.shadow.vo.MediaMovelVO;

public class NegociosRoboHandler {
	
	private User user;
	private String cotacoes;
	private final Long DUAS_HORAS = 1000*60*60*2l;
	private boolean seeGrafico;
	private TravaAlta travaAlta;
	
	public List<TravaAlta> getNegociosTravaDeAlta(){
		TravaDeAltaBusiness tb = new TravaDeAltaBusiness();
		List<TravaAlta> travas = tb.getTravasDeAltaByUserByPendente(user.getLogin(), false); 
		if (travas==null || travas.isEmpty()){
			return new ArrayList<TravaAlta>();
		}
		return travas;
	}

	public List<TravaBaixa> getNegociosTravaDeBaixa(){
		TravaDeBaixaBusiness tb = new TravaDeBaixaBusiness();
		List<TravaBaixa> travas = tb.getTravasDeBaixaByUserByPendente(user.getLogin(), false); 
		if (travas==null || travas.isEmpty()){
			return new ArrayList<TravaBaixa>();
		}
		return travas;
	}
	
	public String salvaAnalise(){
		Session session = HibernateUtil.currentSession();
		DAO<User> dao = new DAO<User>(session, User.class);
		dao.merge(this.user);

		return "";	
	}
	
	public String verGrafico(){
		seeGrafico  = true;
		
		return "";
	}
	
	public String esconderGrafico(){
		seeGrafico = false;
		
		return "";
	}
	
	public String getCotacoes() {
		
		if (travaAlta==null){
			return "";
		}
		
		Calendar dtInicial = Calendar.getInstance(); 
		Calendar dtFinal = Calendar.getInstance();	
		Integer diaDaTrava = travaAlta.getDataCompra().get(Calendar.DAY_OF_MONTH);
		
		dtInicial.set(Calendar.HOUR_OF_DAY, 8);
		dtInicial.set(Calendar.DAY_OF_MONTH, diaDaTrava);
		
		dtFinal.set(Calendar.HOUR_OF_DAY, 17);
		dtFinal.set(Calendar.DAY_OF_MONTH, diaDaTrava);
		
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesByCodeByData(travaAlta.getAcao(), dtInicial, dtFinal);
		StringBuffer result = new StringBuffer();
		if (cotacoes==null || cotacoes.isEmpty()){
			return "";
		}
		List<CotacaoAcao> cots = new ArrayList<CotacaoAcao>();
		MediaMovelBusiness mmb = new MediaMovelBusiness();
		for(CotacaoAcao cotacao : cotacoes){
			result.append(cotacao.getData().getTimeInMillis()-DUAS_HORAS);
			result.append(",");
			result.append(cotacao.getValor());

			//Linha de fechamento do dia anterior.
			result.append(",");
			result.append(getValorFechamentoDiaAnterior(cotacao));
			
			cots.add(cotacao);
			MediaMovelVO mm = mmb.getMediaMovel(cots);
			if (mm!=null){
				result.append(",");
				result.append(mm.getValorMediaMenor());
				result.append(",");
				result.append(mm.getValorMediaMaior());
			}
			result.append("#");
		}
		String res = result.toString();
		res = res.substring(0, res.length()-1);
		
		return res;
	}
	
	private Float getValorFechamentoDiaAnterior(CotacaoAcao cotacaoAcao){
		
		Float valorMomento = cotacaoAcao.getValor();
		Float oscilacao = cotacaoAcao.getOscilacao();
		Float valorFech = valorMomento/(1+(oscilacao/100));
		
		return valorFech;
	}
	
	public String getPontosTrava(){
		
		StringBuffer result = new StringBuffer();
		result.append(travaAlta.getDataCompra().getTimeInMillis()-DUAS_HORAS);
		result.append(",");
		result.append(travaAlta.getValorAcaoInicial());
		result.append("#");

		result.append(travaAlta.getDataVenda().getTimeInMillis()-DUAS_HORAS);
		result.append(",");
		result.append(travaAlta.getValorAcaoFinal());

		String res = result.toString();
		return res;
	}

	public void setCotacoes(String cotacoes) {
		this.cotacoes = cotacoes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public boolean isSeeGrafico() {
		return seeGrafico;
	}

	public void setSeeGrafico(boolean seeGrafico) {
		this.seeGrafico = seeGrafico;
	}

	public TravaAlta getTravaAlta() {
		return travaAlta;
	}

	public void setTravaAlta(TravaAlta travaAlta) {
		this.travaAlta = travaAlta;
	}
	
	
	
	
	
	
	
	
	
	

	
	
	

}
