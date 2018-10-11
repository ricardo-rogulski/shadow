package br.com.shadow.vo;

import br.com.shadow.entity.Acao;

public class MediaMovelVO {
	
	private Acao acao;
	private Float valorMediaMenor;
	private Float valorMediaMaior;
	
	public MediaMovelVO(){}
	
	public MediaMovelVO(Acao acao, Float valorMediaMenor, Float valorMediaMaior) {
		this.acao = acao;
		this.valorMediaMenor = valorMediaMenor;
		this.valorMediaMaior = valorMediaMaior;
	}
	
	public Float getFator(){
		if (valorMediaMenor==null || valorMediaMaior==null || valorMediaMenor==0 || valorMediaMaior==0){
			return 0f;
		}
		float fator = valorMediaMenor.floatValue()/valorMediaMaior.floatValue(); 
		return  fator;
	}

	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Float getValorMediaMenor() {
		return valorMediaMenor;
	}
	public void setValorMediaMenor(Float valorMediaMenor) {
		this.valorMediaMenor = valorMediaMenor;
	}
	public Float getValorMediaMaior() {
		return valorMediaMaior;
	}
	public void setValorMediaMaior(Float valorMediaMaior) {
		this.valorMediaMaior = valorMediaMaior;
	}

}
