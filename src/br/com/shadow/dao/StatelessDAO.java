package br.com.shadow.dao;

import java.util.List;

import org.hibernate.StatelessSession;

public class StatelessDAO<T> {
	
	private Class<T> persistentClass;
	protected StatelessSession session;
	
	public StatelessDAO(StatelessSession session, Class<T> persistentClass){
		this.session = session;
		this.persistentClass = persistentClass;
	}

	public T get(Integer id){
		return (T)session.get(persistentClass, id);
	}
	
	public Object save(T t){
		return session.insert(t);
	}
	
	public void delete(T t){
		session.delete(t);
	}
	
	public List<T> list(){
		return session.createCriteria(persistentClass).list();
	}
	
}
