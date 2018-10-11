package br.com.shadow.entity;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="negocio")
public class NegocioTravaAlta {
	
	@Id @GeneratedValue
	private Integer id; 
	private Acao acao;
	private Opcao opcaoComprado;
	private Opcao opcaoVendido;
	private Float cotacaoOpcaoCompradoNaEntrada;
	private Float cotacaoOpcaoVendidoNaEntrada;
	private Float cotacaoOpcaoCompradoNaSaida;
	private Float cotacaoOpcaoVendidoSaida;
	private Float spreedNaEntrada;
	private Float spreedNaSaida;
	private Float spreedMaximo;
	
	@Temporal(TemporalType.TIMESTAMP)	
	private Calendar dataEntrada = new GregorianCalendar();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataSaida = new GregorianCalendar();
	
	private Integer numeroComprasVendas;
	private Integer qtdOpcoes;
	private Integer corretagemCompra;
	private Integer corretagemVenda;
	private Integer corretagemFinal;
	
	private Float lucroPCT;
	private Float lucroPotencialRS;
	private Float valorGasto;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Opcao getOpcaoComprado() {
		return opcaoComprado;
	}

	public void setOpcaoComprado(Opcao opcaoComprado) {
		this.opcaoComprado = opcaoComprado;
	}

	public Opcao getOpcaoVendido() {
		return opcaoVendido;
	}

	public void setOpcaoVendido(Opcao opcaoVendido) {
		this.opcaoVendido = opcaoVendido;
	}

	public Float getCotacaoOpcaoCompradoNaEntrada() {
		return cotacaoOpcaoCompradoNaEntrada;
	}

	public void setCotacaoOpcaoCompradoNaEntrada(Float cotacaoOpcaoCompradoNaEntrada) {
		this.cotacaoOpcaoCompradoNaEntrada = cotacaoOpcaoCompradoNaEntrada;
	}

	public Float getCotacaoOpcaoVendidoNaEntrada() {
		return cotacaoOpcaoVendidoNaEntrada;
	}

	public void setCotacaoOpcaoVendidoNaEntrada(Float cotacaoOpcaoVendidoNaEntrada) {
		this.cotacaoOpcaoVendidoNaEntrada = cotacaoOpcaoVendidoNaEntrada;
	}

	public Float getCotacaoOpcaoCompradoNaSaida() {
		return cotacaoOpcaoCompradoNaSaida;
	}

	public void setCotacaoOpcaoCompradoNaSaida(Float cotacaoOpcaoCompradoNaSaida) {
		this.cotacaoOpcaoCompradoNaSaida = cotacaoOpcaoCompradoNaSaida;
	}

	public Float getCotacaoOpcaoVendidoSaida() {
		return cotacaoOpcaoVendidoSaida;
	}

	public void setCotacaoOpcaoVendidoSaida(Float cotacaoOpcaoVendidoSaida) {
		this.cotacaoOpcaoVendidoSaida = cotacaoOpcaoVendidoSaida;
	}

	public Float getSpreedNaEntrada() {
		return spreedNaEntrada;
	}

	public void setSpreedNaEntrada(Float spreedNaEntrada) {
		this.spreedNaEntrada = spreedNaEntrada;
	}

	public Float getSpreedNaSaida() {
		return spreedNaSaida;
	}

	public void setSpreedNaSaida(Float spreedNaSaida) {
		this.spreedNaSaida = spreedNaSaida;
	}

	public Float getSpreedMaximo() {
		return spreedMaximo;
	}

	public void setSpreedMaximo(Float spreedMaximo) {
		this.spreedMaximo = spreedMaximo;
	}

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Integer getNumeroComprasVendas() {
		return numeroComprasVendas;
	}

	public void setNumeroComprasVendas(Integer numeroComprasVendas) {
		this.numeroComprasVendas = numeroComprasVendas;
	}

	public Integer getCorretagemCompra() {
		return corretagemCompra;
	}

	public void setCorretagemCompra(Integer corretagemCompra) {
		this.corretagemCompra = corretagemCompra;
	}

	public Integer getCorretagemVenda() {
		return corretagemVenda;
	}

	public void setCorretagemVenda(Integer corretagemVenda) {
		this.corretagemVenda = corretagemVenda;
	}

	public Integer getCorretagemFinal() {
		return corretagemFinal;
	}

	public void setCorretagemFinal(Integer corretagemFinal) {
		this.corretagemFinal = corretagemFinal;
	}

	public Integer getQtdOpcoes() {
		return qtdOpcoes;
	}

	public void setQtdOpcoes(Integer qtdOpcoes) {
		this.qtdOpcoes = qtdOpcoes;
	}

	public Float getLucroPCT() {
		return lucroPCT;
	}

	public void setLucroPCT(Float lucroPCT) {
		this.lucroPCT = lucroPCT;
	}

	public Float getLucroPotencialRS() {
		return lucroPotencialRS;
	}
	public String getLucroPotencialRSStr() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "R$ "+df.format(lucroPotencialRS);
	}

	
	public void setLucroPotencialRS(Float lucroPotencialRS) {
		this.lucroPotencialRS = lucroPotencialRS;
	}

	public Float getValorGasto() {
		return valorGasto;
	}
	public String getValorGastoStr() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "R$ "+df.format(valorGasto);
	}

	
	public void setValorGasto(Float valorGasto) {
		this.valorGasto = valorGasto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
 


}
