package br.com.shadow.handler;


import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import br.com.shadow.business.AdminBusiness;
import br.com.shadow.dao.DAO;
import br.com.shadow.entity.Admin;
import br.com.shadow.util.HibernateUtil;
import br.com.shadow.util.Util;

public class CrudAdminHandler {
	
	private Admin admin;
	
	public Admin carrega(){
		Util.verificaAcesso();
		AdminBusiness ab = new AdminBusiness();
		return ab.getAdmin();
	}
	
	public String salva(){
		Session session = HibernateUtil.currentSession();
		DAO<Admin> dao = new DAO<Admin>(session, Admin.class);
		
		dao.merge(this.admin);
		//this.admin = new Admin();

		return "sucesso";	
	}
	
	public String cancel(){
		this.admin = new Admin();
		return "";	
	}

	public Admin getAdmin() {
		if (admin==null){
			Admin adm = carrega();
			if (adm!=null){
				this.admin = adm;
			}else{
				this.admin = new Admin();
			}
		}
		return admin;		
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	


}
