package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.shadow.entity.User;


public class UserDAO extends DAO{
	
	public UserDAO(Session session, Class persistentClass) {
		super(session, persistentClass);
	}
	
	
	public User getUserByLoginBySenha(String login, String senha){
		
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("pass", senha));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (User)lista.get(0);

	}

	public List<User> getUsers(){
		Criteria c = session.createCriteria(User.class);
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return lista;
	}

	
	public User getUserByEmail(String email){
		
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("login", email));
		
		List lista = c.list();
		if (lista==null || lista.isEmpty()){
			return null;
		}
		return (User)lista.get(0);

	}
	

}
