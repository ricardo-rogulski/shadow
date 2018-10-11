package br.com.shadow.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	private static ThreadLocal<StatelessSession> statelessSessions = new ThreadLocal<StatelessSession>();
	
	static{
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static Session openSession(){
		if (sessions.get()!=null){
		}
		sessions.set(sessionFactory.openSession());
		return sessions.get();
	}
	
	public static StatelessSession openStatelessSession(){
		statelessSessions.set(sessionFactory.openStatelessSession());
		return statelessSessions.get();
	}

	
	public static void closeCurrentSession(){
		sessions.get().close();
		sessions.set(null);
	}
	
	public static Session currentSession (){
		return sessions.get();
	}
}
