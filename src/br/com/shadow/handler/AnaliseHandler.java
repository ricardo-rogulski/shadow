package br.com.shadow.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.shadow.business.TravaDeAltaBusiness;
import br.com.shadow.business.TravaDeBaixaBusiness;
import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.TravaBaixa;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.factory.TravaBaixaBeanFactory;
import br.com.shadow.util.Util;
import br.com.shadow.vo.AnaliseRoboVO;

public class AnaliseHandler {
	
	private Integer CORRETAGEM_POR_NEGOCIO = 20;
	private AnaliseRoboVO analise;
	List<AnaliseRoboVO> analises = new ArrayList<AnaliseRoboVO>();
	
	public String verNegocios(){
		Util util = new Util();
		NegociosRoboHandler nrh = (NegociosRoboHandler)util.getHandler("negociosRoboHandler");
		nrh.setUser(analise.getRobo());
		
		FacesContext faces = FacesContext.getCurrentInstance();   
		ExternalContext context = faces.getExternalContext();   
		try{
			context.redirect("negociosRobo.jsf");	
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return "";
	}
	
	public List<AnaliseRoboVO> getAnalises(){
		analises = new ArrayList<AnaliseRoboVO>();
		List<TravaAlta> travasDeAlta = getTravasDeAltaFinalizadas();
		List<TravaBaixa> travasDeBaixa = getTravasDeBaixaFinalizadas();
		
		List<User> users = getUsuarios();
		
		
		for(User user : users){
			AnaliseRoboVO analise = new AnaliseRoboVO();
			analise.setRobo(user);
			for(TravaAlta trava : travasDeAlta){
				if(trava.getUser().equals(user)){
					analise.setLucro(analise.getLucro()+trava.getLucroFinal());
					analise.setQtdNegocios(analise.getQtdNegocios()+1);
					if(trava.getLucroFinal()>=0){
						analise.setQtdNegociosPositivos(analise.getQtdNegociosPositivos()+1);
					}else{
						analise.setQtdNegociosNegativos(analise.getQtdNegociosNegativos()+1);
					}
					analise.setCorretagem(analise.getCorretagem()+CORRETAGEM_POR_NEGOCIO);
				}
			}

			for(TravaBaixa trava : travasDeBaixa){
				if(trava.getUser().equals(user)){
					analise.setLucro(analise.getLucro()+trava.getLucroFinal());
					analise.setQtdNegocios(analise.getQtdNegocios()+1);
					if(trava.getLucroFinal()>=0){
						analise.setQtdNegociosPositivos(analise.getQtdNegociosPositivos()+1);
					}else{
						analise.setQtdNegociosNegativos(analise.getQtdNegociosNegativos()+1);
					}
					analise.setCorretagem(analise.getCorretagem()+CORRETAGEM_POR_NEGOCIO);
				}
			}
			analises.add(analise);	
		}
		return analises;		
	}
	
	
	
	public List<TravaAlta> getTravasDeAltaFinalizadas(){
		TravaDeAltaBusiness travaBusiness = new TravaDeAltaBusiness();
		return travaBusiness.getTravaAltasFinalizadas();
	}
	
	public List<TravaBaixa> getTravasDeBaixaFinalizadas(){
		TravaDeBaixaBusiness travaBusiness = new TravaDeBaixaBusiness();
		return travaBusiness.getTravaBaixasFinalizadas();
	}
	
	public List<TravaAlta> getTravasDeAltaPendentes(){
		TravaDeAltaBusiness travaBusiness = new TravaDeAltaBusiness();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		List<TravaAlta> travasPendentes = travaBusiness.getTravaAltasPendentes();
		if (travasPendentes!=null && !travasPendentes.isEmpty()){
			for(TravaAlta trava : travasPendentes){
				trava = tbf.atualizaValores(trava);
			}
		}
		return travasPendentes;
	}

	public List<TravaBaixa> getTravasDeBaixaPendentes(){
		TravaDeBaixaBusiness travaBusiness = new TravaDeBaixaBusiness();
		TravaBaixaBeanFactory tbf = new TravaBaixaBeanFactory();
		List<TravaBaixa> travasPendentes = travaBusiness.getTravaBaixasPendentes();
		if (travasPendentes!=null && !travasPendentes.isEmpty()){
			for(TravaBaixa trava : travasPendentes){
				trava = tbf.atualizaValores(trava);
			}
		}
		return travasPendentes;
	}
	
	public List<User> getUsuarios(){
		UserBusiness userBusiness = new UserBusiness();
		return userBusiness.getUsers();
	}


	public AnaliseRoboVO getAnalise() {
		return analise;
	}


	public void setAnalise(AnaliseRoboVO analise) {
		this.analise = analise;
	}
	
	

}
