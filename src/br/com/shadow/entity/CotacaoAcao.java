package br.com.shadow.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.shadow.util.Util;


@Entity
@Table(name="cotacaoacao")
public class CotacaoAcao {
	
	@Id @GeneratedValue
	private Integer id; //1
	private Float valor; //42.35
	private Float oscilacao; //1.42
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data; //21/07/2011 : 10:22:30
	@ManyToOne
	private Acao acao; //PETR4
	
	
	public String getDataStr(){
		return Util.getDataAsDiaMesAnoHoraMinuto(data);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Float getOscilacao() {
		return oscilacao;
	}
	public void setOscilacao(Float oscilacao) {
		this.oscilacao = oscilacao;
	}
	
	

}
