package br.com.shadow.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.shadow.util.Util;


@Entity
@Table(name="serieopcao")
public class SerieOpcao {
	
	@Id @GeneratedValue
	private Integer id; //1
	private String name; //Serie F
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExercicio = new GregorianCalendar(); //21/07/2011
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getDataExercicio() {
		return dataExercicio;
	}
	public void setDataExercicio(Calendar dataExercicio) {
		this.dataExercicio = dataExercicio;
	}
	
	public String getDataExercicioStr(){
		return Util.getDataAsDiaMesAno(dataExercicio);
	}

	

}
