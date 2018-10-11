package br.com.shadow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="opcao_seco")
public class OpcaoSeco {
	
	@Id @GeneratedValue
	private Integer id; //1
	private String code; //PETRF42
	private Float vlExercicio; //42.30
	@ManyToOne
	private Acao acao; //PETR4
	@ManyToOne
	private SerieOpcao serie; //Serie F
	private boolean ativo; //true
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Float getVlExercicio() {
		return vlExercicio;
	}
	public void setVlExercicio(Float vlExercicio) {
		this.vlExercicio = vlExercicio;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public SerieOpcao getSerie() {
		return serie;
	}
	public void setSerie(SerieOpcao serie) {
		this.serie = serie;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}