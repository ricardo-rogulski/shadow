package br.com.shadow.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class HibernateSessionFilter implements Filter{

	public void destroy() {    
	}   

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		
		HibernateUtil.openSession();
		try{
			HibernateUtil.currentSession().beginTransaction();
			fc.doFilter(req, res);
			HibernateUtil.currentSession().getTransaction().commit();
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			HibernateUtil.closeCurrentSession();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
