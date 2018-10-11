package br.com.shadow.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.shadow.entity.User;
import br.com.shadow.handler.LoginHandler;

public class Util {
	
	
	public static String getYesNoImage(boolean param){
		if (param){
			return "images/yes.png";
		}
		return "images/no.png";
	}
	
	public static String getDataAsDiaMesAno(Calendar calendar){
		
		if (calendar==null || calendar.getTime()==null){
			return "----";
		}
		
		String dia = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (dia.length()==1){
			dia = "0"+dia;
		}
		String mes = Integer.toString(calendar.get(Calendar.MONTH)+1);
		if (mes.length()==1){
			mes = "0"+mes;
		}
		String ano = Integer.toString(calendar.get(Calendar.YEAR));
		
		String data = dia+"/"+mes+"/"+ano;
		return data;
	}
	
	public static String getDataAsDiaMesAnoHoraMinuto(){
		Calendar calendar = Calendar.getInstance();
		return getDataAsDiaMesAnoHoraMinuto(calendar);
	}
	
	
	public static String getDataAsDiaMesAnoHoraMinuto(Calendar calendar){
		
		if (calendar==null || calendar.getTime()==null){
			return "----";
		}
		
		String dia = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (dia.length()==1){
			dia = "0"+dia;
		}
		String mes = Integer.toString(calendar.get(Calendar.MONTH)+1);
		if (mes.length()==1){
			mes = "0"+mes;
		}
		String ano = Integer.toString(calendar.get(Calendar.YEAR));
		
		String hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		if (hora.length()==1){
			hora = "0"+hora;
		}
		String minuto = Integer.toString(calendar.get(Calendar.MINUTE));
		if (minuto.length()==1){
			minuto = "0"+minuto;
		}
		String data = dia+"/"+mes+"/"+ano+ " "+hora+":"+minuto;
		return data;
	}

	public static String getDataAsHoraMinuto(Calendar calendar){
		
		if (calendar==null || calendar.getTime()==null){
			return "----";
		}
		
		String hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		if (hora.length()==1){
			hora = "0"+hora;
		}
		String minuto = Integer.toString(calendar.get(Calendar.MINUTE));
		if (minuto.length()==1){
			minuto = "0"+minuto;
		}
		String data = hora+":"+minuto;
		return data;
	}

	public static Integer getDataAsHora(Calendar calendar){
		
		if (calendar==null || calendar.getTime()==null){
			return 0;
		}
		
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	
	public static Object getHandler(String handlerName){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ELResolver resolver = facesContext.getApplication().getELResolver();
		Object uh = resolver.getValue(elContext, null, handlerName);
		return uh;
	}
	
	
	public static void verificaAcesso(){
		LoginHandler lh = (LoginHandler)getHandler("loginHandler");
		if(!lh.isLogado()){
			FacesContext faces = FacesContext.getCurrentInstance();   
			ExternalContext context = faces.getExternalContext();   
			try{
				System.out.println("Usuário não logado.");
				context.redirect("login.jsf");	
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	
	public String getRealPath(){

		FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext)aFacesContext.getExternalContext().getContext();

        //Usado para salvar o arquivo no FileSystem. 
        String realPath = context.getRealPath("graph");
        
        return realPath;
	}
	
	public User getUsuarioLogado(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ELResolver resolver = facesContext.getApplication().getELResolver();
		Object lh = resolver.getValue(elContext, null, "loginHandler");
		return ((LoginHandler)lh).getUser();
	}
	
	public static void main(String[] args) {
		
		float a = 4f;
		System.out.println(a);
		a = paraDuasCasas(a);
		System.out.println(a);
		
		float b = 2.9544435f;
		System.out.println(b);
		b = paraDuasCasas(b);
		System.out.println(b);
	}
	
	public static float paraDuasCasas(Float numero){
		
		BigDecimal aNumber = new BigDecimal(numero);  
		aNumber = aNumber.setScale(2, BigDecimal.ROUND_HALF_EVEN);      
		return aNumber.floatValue();
	}

	


}
