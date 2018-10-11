package br.com.shadow.business;

import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.DAO;
import br.com.shadow.dao.UserDAO;
import br.com.shadow.entity.User;
import br.com.shadow.util.HibernateUtil;


public class UserBusiness {
	
	
	public User getUserByLoginBySenha(String login, String senha){
		Session session = HibernateUtil.currentSession();
		UserDAO ud = new UserDAO(session, User.class);
		return ud.getUserByLoginBySenha(login, senha);
	}
	
	public User getUserByEmail(String email){
		Session session = HibernateUtil.currentSession();
		UserDAO ud = new UserDAO(session, User.class);
		return ud.getUserByEmail(email);
	}
	
	public List<User> getUsers(){
		Session session = HibernateUtil.currentSession();
		UserDAO ud = new UserDAO(session, User.class);
		return ud.getUsers();
	}
	
	public User getUserByRobo(String nomeRobo){
		Session session = HibernateUtil.openSession();
		UserDAO ud = new UserDAO(session, User.class);
		User user = ud.getUserByEmail(nomeRobo);
		session.close();
		return user;
	}
	
	public void salvaUser(User usuario){
		
		Session session = HibernateUtil.currentSession();
		DAO<User> dao = new DAO<User>(session, User.class);
		dao.merge(usuario);
	}
	
	public void salvaUserByRobo(User usuario){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		DAO<User> dao = new DAO<User>(session, User.class);
		dao.merge(usuario);
		session.getTransaction().commit();
		session.close();
	}

	
	public User load(Integer id){
		Session session = HibernateUtil.currentSession();
		UserDAO ed = new UserDAO(session, User.class);
		return (User)ed.load(id);
	}




}
