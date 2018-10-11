package br.com.shadow.handler;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.shadow.business.UserBusiness;
import br.com.shadow.entity.User;


public class LoginHandler {
		
	private User user = new User();
	private String msg;
	private boolean logado;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String logar(){
		msg = "";
		
		if (this.user.getLogin()==null || this.user.getLogin().length() < 1){
			msg = "Please, fill your Login!";
			return "";
		}
		if (this.user.getPass()==null || this.user.getPass().length() < 1){
			msg = "Please, fill your Password!";
			return "";
		}
		
		if (this.user!=null && this.user.getLogin()!=null){
			//Busca usuário no banco.
			UserBusiness ub = new UserBusiness();	
			
			User userRetorno = ub.getUserByLoginBySenha(user.getLogin(), user.getPass());
			if (userRetorno==null){
				msg = "Invalid User or Password!";
				return "";
			}
			this.user = userRetorno;
			logado = true;

			return "logged";
		}
		return "";
	}
	
	
	public String sair(){
		msg = "";
		this.user = new User();
		logado = false;
		
		//Invalidar a sessão.
		HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
		session.invalidate();
		
		return "ok";
	}
	
	public String goToLogin(){
		return "login";
	}
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	

}
