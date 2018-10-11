package br.com.shadow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.shadow.business.CotacaoAcaoBusiness;
import br.com.shadow.util.MyChart;



@Entity
@Table(name="acao")
public class Acao {
	
	@Id @GeneratedValue
	private Integer id; //1
	private String name; //Petrobrás
	private String code; //PETR4
	
	@Transient
	private Float ultimoValor;
	@Transient
	private Float ultimaOscilacao;
	
	
	public CotacaoAcao getUltimaCotacao(){
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		return cab.getUltimaCotacao(code);
	}
	
	public CotacaoAcao getUltimaCotacaoByRobo(){
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		return cab.getUltimaCotacaoByAcaoByRobo(code);
	}
	
	public Float getUltimaOscilacao() {
		if (ultimaOscilacao==null || ultimaOscilacao<=0){
			ultimaOscilacao = getUltimaCotacao().getOscilacao();
		}
		return ultimaOscilacao;
	}

	public Float getUltimaOscilacaoByRobo() {
		if (ultimaOscilacao==null || ultimaOscilacao<=0){
			ultimaOscilacao = getUltimaCotacaoByRobo().getOscilacao();
		}
		return ultimaOscilacao;
	}


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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getGrafico(){
		MyChart cd = new MyChart();
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesByAcao(code);
		
		if (cotacoes!=null && !cotacoes.isEmpty()){
			String file = cd.geraGraficoAcao(code, cotacoes);
			return file;	
		}
		return null;
	}
	
	public String getGraficoDiario(){
		MyChart cd = new MyChart();
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesDoDiaByAcao(code);
		
		if (cotacoes!=null && !cotacoes.isEmpty()){
			String file = cd.geraGraficoAcao(code, cotacoes);
			return file;	
		}
		return null;
	}

	public Float getUltimoValor() {
		if (ultimoValor==null || ultimoValor<=0){
			ultimoValor = getUltimaCotacao().getValor();
		}
		return ultimoValor;
	}

	public void setUltimoValor(Float ultimoValor) {
		this.ultimoValor = ultimoValor;
	}

	public void setUltimaOscilacao(Float ultimaOscilacao) {
		this.ultimaOscilacao = ultimaOscilacao;
	}

	
	
	
	

}
