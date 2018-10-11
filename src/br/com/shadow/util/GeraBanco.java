package br.com.shadow.util;



import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class GeraBanco {
	
	public static void main(String[] args) {

		Configuration cfg = new AnnotationConfiguration();
		cfg.configure();
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
		
		System.out.println("Banco gerado com sucesso!");
	}
	
	public void gera(){
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure();
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

}
