package br.com.shadow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id @GeneratedValue
	private Integer id; 
	private Integer intervaloCotacaoAcao;
	private Integer intervaloCotacaoOpcao;
	private Integer intervaloNegocios;
	private String horarioInicioCotacoes;
	private String horarioFinalCotacoes;
	private String acao;
	private Float stopMovel;
	private Float stopGain;
	private boolean roboCotacaoAcaoAtivo;
	private boolean roboCotacaoOpcaoAtivo;
	private boolean roboNegociosAtivo;
	private boolean usarProxy;
	
	private Integer mediaMovelMenor;
	private Integer mediaMovelMaior;
	
	//private Integer hrRoboApagaCotacoesOpcoes;
	//private Integer intervaloApagaCotacoesOpcoes;

	//private Integer hrRoboRevisaListaOpcoes;
	//private Integer intervaloRoboRevisaOpcoes;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntervaloCotacaoAcao() {
		return intervaloCotacaoAcao;
	}
	public void setIntervaloCotacaoAcao(Integer intervaloCotacaoAcao) {
		this.intervaloCotacaoAcao = intervaloCotacaoAcao;
	}
	public Integer getIntervaloCotacaoOpcao() {
		return intervaloCotacaoOpcao;
	}
	public void setIntervaloCotacaoOpcao(Integer intervaloCotacaoOpcao) {
		this.intervaloCotacaoOpcao = intervaloCotacaoOpcao;
	}
	public Integer getIntervaloNegocios() {
		return intervaloNegocios;
	}
	public void setIntervaloNegocios(Integer intervaloNegocios) {
		this.intervaloNegocios = intervaloNegocios;
	}
	public String getHorarioInicioCotacoes() {
		return horarioInicioCotacoes;
	}
	public void setHorarioInicioCotacoes(String horarioInicioCotacoes) {
		this.horarioInicioCotacoes = horarioInicioCotacoes;
	}
	public String getHorarioFinalCotacoes() {
		return horarioFinalCotacoes;
	}
	public void setHorarioFinalCotacoes(String horarioFinalCotacoes) {
		this.horarioFinalCotacoes = horarioFinalCotacoes;
	}
	public Float getStopMovel() {
		return stopMovel;
	}
	public void setStopMovel(Float stopMovel) {
		this.stopMovel = stopMovel;
	}
	public Float getStopGain() {
		return stopGain;
	}
	public void setStopGain(Float stopGain) {
		this.stopGain = stopGain;
	}
	public boolean isRoboCotacaoAcaoAtivo() {
		return roboCotacaoAcaoAtivo;
	}
	public void setRoboCotacaoAcaoAtivo(boolean roboCotacaoAcaoAtivo) {
		this.roboCotacaoAcaoAtivo = roboCotacaoAcaoAtivo;
	}
	public boolean isRoboCotacaoOpcaoAtivo() {
		return roboCotacaoOpcaoAtivo;
	}
	public void setRoboCotacaoOpcaoAtivo(boolean roboCotacaoOpcaoAtivo) {
		this.roboCotacaoOpcaoAtivo = roboCotacaoOpcaoAtivo;
	}
	public boolean isRoboNegociosAtivo() {
		return roboNegociosAtivo;
	}
	public void setRoboNegociosAtivo(boolean roboNegociosAtivo) {
		this.roboNegociosAtivo = roboNegociosAtivo;
	}
	public Integer getMediaMovelMenor() {
		return mediaMovelMenor;
	}
	public void setMediaMovelMenor(Integer mediaMovelMenor) {
		this.mediaMovelMenor = mediaMovelMenor;
	}
	public Integer getMediaMovelMaior() {
		return mediaMovelMaior;
	}
	public void setMediaMovelMaior(Integer mediaMovelMaior) {
		this.mediaMovelMaior = mediaMovelMaior;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public boolean isUsarProxy() {
		return usarProxy;
	}
	public void setUsarProxy(boolean usarProxy) {
		this.usarProxy = usarProxy;
	}
	
	
	
	
	
}
