package br.com.shadow.dao;

import java.util.List;

import org.hibernate.Session;

public class DAO<T> {
	
	private Class<T> persistentClass;
	protected Session session;
	
	public DAO(Session session, Class<T> persistentClass){
		this.session = session;
		this.persistentClass = persistentClass;
	}

	public T load(Integer id){
		return (T)session.load(persistentClass, id);
	}
	
	public T get(Integer id){
		return (T)session.get(persistentClass, id);
	}
	
	public Object save(T t){
		return session.save(t);
	}
	
	public void delete(T t){
		session.delete(t);
	}
	
	public List<T> list(){
		return session.createCriteria(persistentClass).list();
	}
	
	public Object merge(T t){
		return session.merge(t);
	}
	
}
