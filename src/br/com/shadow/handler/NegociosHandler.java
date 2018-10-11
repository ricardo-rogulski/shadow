package br.com.shadow.handler;

import java.util.List;

import br.com.shadow.business.TravaDeAltaBusiness;
import br.com.shadow.entity.TravaAlta;
import br.com.shadow.entity.User;
import br.com.shadow.factory.TravaAltaBeanFactory;
import br.com.shadow.util.TravaUtil;
import br.com.shadow.util.Util;

public class NegociosHandler {
	
	private TravaAlta trava;
	
	public void desfazTrava(){
		TravaUtil travaUtil = new TravaUtil();
		travaUtil.desfazTrava(trava);
	}
	
	public List<TravaAlta> getNegociosPendentes(){
		
		Util util = new Util();
		User usuario = util.getUsuarioLogado();
		TravaAltaBeanFactory tbf = new TravaAltaBeanFactory();
		TravaDeAltaBusiness tb = new TravaDeAltaBusiness();
		List<TravaAlta> travasPendentes = tb.getTravasDeAltaByUserByPendente(usuario.getLogin(), true);
		if (travasPendentes!=null && !travasPendentes.isEmpty()){
			for(TravaAlta trava : travasPendentes){
				trava = tbf.atualizaValores(trava);
			}
		}
		return travasPendentes;
	}

	public List<TravaAlta> getNegociosFinalizados(){
		
		Util util = new Util();
		User usuario = util.getUsuarioLogado();
		
		TravaDeAltaBusiness tb = new TravaDeAltaBusiness();
		return tb.getTravasDeAltaByUserByPendente(usuario.getLogin(), false);
	}

	public TravaAlta getTrava() {
		return trava;
	}

	public void setTrava(TravaAlta trava) {
		this.trava = trava;
	}
	
	

}
