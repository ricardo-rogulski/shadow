package br.com.shadow.vo;

import java.text.DecimalFormat;
import java.util.Calendar;

import br.com.shadow.entity.User;

public class AnaliseRoboVO {
	
	private User robo;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private Integer qtdNegocios = 0;
	private Integer qtdNegociosPositivos = 0;
	private Integer qtdNegociosNegativos = 0;
	private Integer corretagem = 0;
	private Float lucro =0f;
	private Float lucroReal = 0f;
	
	
	public String getLucroStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucro());		
	}
	
	public String getLucroRealStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroReal());		
	}

	
	public User getRobo() {
		return robo;
	}
	public void setRobo(User robo) {
		this.robo = robo;
	}
	public Calendar getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Calendar getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Integer getQtdNegocios() {
		return qtdNegocios;
	}
	public void setQtdNegocios(Integer qtdNegocios) {
		this.qtdNegocios = qtdNegocios;
	}
	public Integer getQtdNegociosPositivos() {
		return qtdNegociosPositivos;
	}
	public void setQtdNegociosPositivos(Integer qtdNegociosPositivos) {
		this.qtdNegociosPositivos = qtdNegociosPositivos;
	}
	public Integer getQtdNegociosNegativos() {
		return qtdNegociosNegativos;
	}
	public void setQtdNegociosNegativos(Integer qtdNegociosNegativos) {
		this.qtdNegociosNegativos = qtdNegociosNegativos;
	}
	public Float getLucro() {
		return lucro;
	}
	public void setLucro(Float lucro) {
		this.lucro = lucro;
	}

	public Integer getCorretagem() {
		return corretagem;
	}

	public void setCorretagem(Integer corretagem) {
		this.corretagem = corretagem;
	}

	public Float getLucroReal() {
		return lucroReal;
	}

	public void setLucroReal(Float lucroReal) {
		this.lucroReal = lucroReal;
	}
	

}
