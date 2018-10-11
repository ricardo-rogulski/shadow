package br.com.shadow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.shadow.business.CotacaoOpcaoBusiness;
import br.com.shadow.util.Util;


@Entity
@Table(name="opcao")
public class Opcao {
	
	@Id @GeneratedValue
	private Integer id; //1
	private String code; //PETRF42
	private Float vlExercicio; //42.30
	@ManyToOne
	private Acao acao; //PETR4
	@ManyToOne
	private SerieOpcao serie; //Serie F
	private boolean ativo; //true

	
	public CotacaoOpcao getUltimaCotacao(){
		CotacaoOpcaoBusiness cob = new CotacaoOpcaoBusiness();
		return cob.getUltimaCotacao(code);
	}
	
	public CotacaoOpcao getUltimaCotacaoByRobo(){
		CotacaoOpcaoBusiness cob = new CotacaoOpcaoBusiness();
		return cob.getUltimaCotacaoByOpcaoByRobo(code);
	}
	
	public Float getValorExtrinsicoByRobo(){
		Float valorAcao = acao.getUltimaCotacaoByRobo().getValor();
		CotacaoOpcao cotacaoOpcao = getUltimaCotacaoByRobo();
		if (cotacaoOpcao==null){
			return 0f;
		}
		Float valorOpcao = cotacaoOpcao.getValor();
		
		Float vlExtrinsico = vlExercicio-valorAcao+valorOpcao;
		if (vlExtrinsico>valorOpcao){
			return valorOpcao;
		}
		return vlExtrinsico;
	}
	
	public Float getValorExtrinsico(){
		if(acao==null || acao.getUltimaCotacao()==null){
			return 0f;
		}		
		Float valorAcao = acao.getUltimaCotacao().getValor();
		CotacaoOpcao cotacaoOpcao = getUltimaCotacao();
		if (cotacaoOpcao==null){
			return 0f;
		}
		Float valorOpcao = cotacaoOpcao.getValor();
		
		Float vlExtrinsico = vlExercicio-valorAcao+valorOpcao;
		if (vlExtrinsico>valorOpcao){
			return valorOpcao;
		}
		return Util.paraDuasCasas(vlExtrinsico);
	}

	
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
