package br.com.shadow.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.shadow.util.Util;

@Entity
@Table(name="trava_alta")
public class TravaAlta {
	
	@Id @GeneratedValue
	private Integer id;
	@ManyToOne
	private User user;
	private String acao;	
	private String opcaoCompra;
	private String opcaoVenda;
	private Float valorOpcaoCompraInicial;
	private Float valorOpcaoCompraFinal;
	private Float valorOpcaoVendaInicial;
	private Float valorOpcaoVendaFinal;
	private Float valorAcaoInicial;
	private Float valorAcaoFinal;
	private Float valorExercOpcaoCompra;
	private Float valorExercOpcaoVenda;
	private Integer quantidade;
	private Integer pontuacaoInicial;
	private Integer pontuacaoFinal;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCompra;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVenda;
	private String motivoVenda;
	
	@Transient
	private Float valorOpcaoCompraAtual;
	@Transient
	private Float valorOpcaoVendaAtual;
	@Transient
	private Float valorAcaoAtual;
	@Transient
	private Float valorOscilacaoAcaoAtual;
	@Transient
	private Float valorOscilacaoOpcaoCompraAtual;
	@Transient
	private Float valorOscilacaoOpcaoVendaAtual;

	
	public Integer getPontuacao(){
		Float pontuacao = 0f;
		//pontuacao = ((3+getMargemSeguranca()+valorOscilacaoAcaoAtual)*getLucroPotencialRS());	

		pontuacao = (3+(getMargemSeguranca()/3)+valorOscilacaoAcaoAtual)*getLucroPotencialRS()*10;
		
		Integer pontos = pontuacao.intValue();
		return pontos;
	}
	
	/*
	 * Métodos de cálculo, inerentes somente à classe Trava.
	 */
	
	private Float getMargemSeguranca(){
		Float margem = 100-((valorExercOpcaoVenda/valorAcaoAtual)*100);
		return paraDuasCasas(margem);
	}
	
	public Float getSpreedMaximo(){
		Float spreed = valorExercOpcaoVenda-valorExercOpcaoCompra;
		return paraDuasCasas(spreed);
	}
	
	public Float getSpreedAtual(){
		if (valorOpcaoCompraAtual==null || valorOpcaoVendaAtual==null){
			return 0f;
		}
		Float spreed = valorOpcaoCompraAtual-valorOpcaoVendaAtual;
		return paraDuasCasas(spreed);
	}
	
	public Float getSpreedInicial(){
		Float spreed = valorOpcaoCompraInicial-valorOpcaoVendaInicial;
		return paraDuasCasas(spreed);
	}

	public Float getSpreedFinal(){
		Float spreed = valorOpcaoCompraFinal-valorOpcaoVendaFinal;
		return paraDuasCasas(spreed);
	}

	
	private Float getLucroPotencialRS(){
		Float lucro = ((getSpreedMaximo()-getSpreedAtual())*quantidade);
		return paraDuasCasas(lucro);
	}
	
	private Float getLucroPotencialPCT(){
		Float valorInvestido = getSpreedAtual()*quantidade;
		Float lucroPotencial = getLucroPotencialRS();
		Float lucroPct = ((lucroPotencial/valorInvestido)*100);
		return paraDuasCasas(lucroPct);		
	}
		
	public Float getLucroAtual(){
		Float spreedAtual = getSpreedAtual();
		Float spreedNaTrava = valorOpcaoCompraInicial - valorOpcaoVendaInicial;
		Float lucro = ((spreedAtual-spreedNaTrava)*quantidade);
		return lucro;		
	}
	
	public Float getLucroFinal(){
		Float spreedFinal = getSpreedFinal();
		Float spreedNaTrava = valorOpcaoCompraInicial - valorOpcaoVendaInicial;
		Float lucro = ((spreedFinal-spreedNaTrava)*quantidade);
		return lucro;		
	}
	
	public Float getLucroFinalPCT(){
		Float valorInvestido = getSpreedInicial()*quantidade;
		Float lucroFinal = getLucroFinal();
		Float lucroPct = ((lucroFinal/valorInvestido)*100);
		return paraDuasCasas(lucroPct);		
	}

	
	public Float getLucroAtualPCT(){
		Float valorInvestido = getSpreedAtual()*quantidade;
		Float lucroAtual = getLucroAtual();
		Float lucroPct = ((lucroAtual/valorInvestido)*100);
		return paraDuasCasas(lucroPct);		
		
	}
	
	private float paraDuasCasas(Float numero){
		if (numero.isNaN() || numero.isInfinite()){
			return 0f;
		}
		BigDecimal aNumber = new BigDecimal(numero);  
		aNumber = aNumber.setScale(2, BigDecimal.ROUND_HALF_EVEN);      
		return aNumber.floatValue();
	}

	/*
	 * Fim dos métodos de cálculo.
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravaAlta other = (TravaAlta) obj;
		if (opcaoCompra == null) {
			if (other.opcaoCompra != null)
				return false;
		} else if (!opcaoCompra.equals(other.opcaoCompra))
			return false;
		return true;
	}

	public Float getValorNegocio(){
		Float valorInvestido = getSpreedAtual()*quantidade;
		return paraDuasCasas(valorInvestido);
	}
	
	public String getValorNegocioStr(){
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(getValorNegocio());
	}
	
	public String getQuantidadeStr(){
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(quantidade);
	}
	
	public String getMargemSegurancaStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		String margem = df.format(Math.abs(getMargemSeguranca()));
		if (getMargemSeguranca()<0){
			return "Precisa subir "+margem+" %";
		}
		return "Pode cair " +margem+" %";
	}
	
	public String getLucroPotencialRSStr() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(getLucroPotencialRS());
	}
	
	public String getLucroAtualStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroAtual());		
	}

	public String getLucroFinalStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroFinal());		
	}
	
	public String getLucroPotencialPCTStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroPotencialPCT());		
	}
	
	public String getLucroAtualPCTStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroAtualPCT());		
	}

	public String getLucroFinalPCTStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroFinalPCT());		
	}
	
	public String getSpreedStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getSpreedAtual());		
	}

	public String getSpreedInicialStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getSpreedInicial());		
	}

	
	public String getSpreedMaximoStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getSpreedMaximo());		
	}

	
	public String getDataCompraStr() {
		return Util.getDataAsDiaMesAnoHoraMinuto(dataCompra);
	}
	
	public String getDataVendaStr() {
		return Util.getDataAsDiaMesAnoHoraMinuto(dataVenda);
	}

	public void setSpreedInicial(Float a){
		
	}
	
	public void setSpreedAtual(Float a){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the opcaoCompra
	 */
	public String getOpcaoCompra() {
		return opcaoCompra;
	}

	/**
	 * @param opcaoCompra the opcaoCompra to set
	 */
	public void setOpcaoCompra(String opcaoCompra) {
		this.opcaoCompra = opcaoCompra;
	}

	/**
	 * @return the opcaoVenda
	 */
	public String getOpcaoVenda() {
		return opcaoVenda;
	}

	/**
	 * @param opcaoVenda the opcaoVenda to set
	 */
	public void setOpcaoVenda(String opcaoVenda) {
		this.opcaoVenda = opcaoVenda;
	}

	/**
	 * @return the valorOpcaoCompraAtual
	 */
	public Float getValorOpcaoCompraAtual() {
		return valorOpcaoCompraAtual;
	}

	/**
	 * @param valorOpcaoCompraAtual the valorOpcaoCompraAtual to set
	 */
	public void setValorOpcaoCompraAtual(Float valorOpcaoCompraAtual) {
		this.valorOpcaoCompraAtual = valorOpcaoCompraAtual;
	}

	/**
	 * @return the valorOpcaoCompraInicial
	 */
	public Float getValorOpcaoCompraInicial() {
		return valorOpcaoCompraInicial;
	}

	/**
	 * @param valorOpcaoCompraInicial the valorOpcaoCompraInicial to set
	 */
	public void setValorOpcaoCompraInicial(Float valorOpcaoCompraInicial) {
		this.valorOpcaoCompraInicial = valorOpcaoCompraInicial;
	}

	/**
	 * @return the valorOpcaoCompraFinal
	 */
	public Float getValorOpcaoCompraFinal() {
		return valorOpcaoCompraFinal;
	}

	/**
	 * @param valorOpcaoCompraFinal the valorOpcaoCompraFinal to set
	 */
	public void setValorOpcaoCompraFinal(Float valorOpcaoCompraFinal) {
		this.valorOpcaoCompraFinal = valorOpcaoCompraFinal;
	}

	/**
	 * @return the valorOpcaoVendaAtual
	 */
	public Float getValorOpcaoVendaAtual() {
		return valorOpcaoVendaAtual;
	}

	/**
	 * @param valorOpcaoVendaAtual the valorOpcaoVendaAtual to set
	 */
	public void setValorOpcaoVendaAtual(Float valorOpcaoVendaAtual) {
		this.valorOpcaoVendaAtual = valorOpcaoVendaAtual;
	}

	/**
	 * @return the valorOpcaoVendaInicial
	 */
	public Float getValorOpcaoVendaInicial() {
		return valorOpcaoVendaInicial;
	}

	/**
	 * @param valorOpcaoVendaInicial the valorOpcaoVendaInicial to set
	 */
	public void setValorOpcaoVendaInicial(Float valorOpcaoVendaInicial) {
		this.valorOpcaoVendaInicial = valorOpcaoVendaInicial;
	}

	/**
	 * @return the valorOpcaoVendaFinal
	 */
	public Float getValorOpcaoVendaFinal() {
		return valorOpcaoVendaFinal;
	}

	/**
	 * @param valorOpcaoVendaFinal the valorOpcaoVendaFinal to set
	 */
	public void setValorOpcaoVendaFinal(Float valorOpcaoVendaFinal) {
		this.valorOpcaoVendaFinal = valorOpcaoVendaFinal;
	}

	/**
	 * @return the valorAcaoAtual
	 */
	public Float getValorAcaoAtual() {
		return valorAcaoAtual;
	}

	/**
	 * @param valorAcaoAtual the valorAcaoAtual to set
	 */
	public void setValorAcaoAtual(Float valorAcaoAtual) {
		this.valorAcaoAtual = valorAcaoAtual;
	}

	/**
	 * @return the valorAcaoInicial
	 */
	public Float getValorAcaoInicial() {
		return valorAcaoInicial;
	}

	/**
	 * @param valorAcaoInicial the valorAcaoInicial to set
	 */
	public void setValorAcaoInicial(Float valorAcaoInicial) {
		this.valorAcaoInicial = valorAcaoInicial;
	}

	/**
	 * @return the valorAcaoFinal
	 */
	public Float getValorAcaoFinal() {
		return valorAcaoFinal;
	}

	/**
	 * @param valorAcaoFinal the valorAcaoFinal to set
	 */
	public void setValorAcaoFinal(Float valorAcaoFinal) {
		this.valorAcaoFinal = valorAcaoFinal;
	}

	/**
	 * @return the valorOscilacaoAcaoAtual
	 */
	public Float getValorOscilacaoAcaoAtual() {
		return valorOscilacaoAcaoAtual;
	}

	/**
	 * @param valorOscilacaoAcaoAtual the valorOscilacaoAcaoAtual to set
	 */
	public void setValorOscilacaoAcaoAtual(Float valorOscilacaoAcaoAtual) {
		this.valorOscilacaoAcaoAtual = valorOscilacaoAcaoAtual;
	}

	/**
	 * @return the valorExercOpcaoCompra
	 */
	public Float getValorExercOpcaoCompra() {
		return valorExercOpcaoCompra;
	}

	/**
	 * @param valorExercOpcaoCompra the valorExercOpcaoCompra to set
	 */
	public void setValorExercOpcaoCompra(Float valorExercOpcaoCompra) {
		this.valorExercOpcaoCompra = valorExercOpcaoCompra;
	}

	/**
	 * @return the valorExercOpcaoVenda
	 */
	public Float getValorExercOpcaoVenda() {
		return valorExercOpcaoVenda;
	}

	/**
	 * @param valorExercOpcaoVenda the valorExercOpcaoVenda to set
	 */
	public void setValorExercOpcaoVenda(Float valorExercOpcaoVenda) {
		this.valorExercOpcaoVenda = valorExercOpcaoVenda;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the pontuacaoInicial
	 */
	public Integer getPontuacaoInicial() {
		return pontuacaoInicial;
	}

	/**
	 * @param pontuacaoInicial the pontuacaoInicial to set
	 */
	public void setPontuacaoInicial(Integer pontuacaoInicial) {
		this.pontuacaoInicial = pontuacaoInicial;
	}

	/**
	 * @return the pontuacaoFinal
	 */
	public Integer getPontuacaoFinal() {
		return pontuacaoFinal;
	}

	/**
	 * @param pontuacaoFinal the pontuacaoFinal to set
	 */
	public void setPontuacaoFinal(Integer pontuacaoFinal) {
		this.pontuacaoFinal = pontuacaoFinal;
	}

	/**
	 * @return the dataCompra
	 */
	public Calendar getDataCompra() {
		return dataCompra;
	}

	/**
	 * @param dataCompra the dataCompra to set
	 */
	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	/**
	 * @return the dataVenda
	 */
	public Calendar getDataVenda() {
		return dataVenda;
	}

	/**
	 * @param dataVenda the dataVenda to set
	 */
	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	/**
	 * @return the valorOscilacaoOpcaoCompraAtual
	 */
	public Float getValorOscilacaoOpcaoCompraAtual() {
		return valorOscilacaoOpcaoCompraAtual;
	}

	/**
	 * @param valorOscilacaoOpcaoCompraAtual the valorOscilacaoOpcaoCompraAtual to set
	 */
	public void setValorOscilacaoOpcaoCompraAtual(
			Float valorOscilacaoOpcaoCompraAtual) {
		this.valorOscilacaoOpcaoCompraAtual = valorOscilacaoOpcaoCompraAtual;
	}

	/**
	 * @return the valorOscilacaoOpcaoVendaAtual
	 */
	public Float getValorOscilacaoOpcaoVendaAtual() {
		return valorOscilacaoOpcaoVendaAtual;
	}

	/**
	 * @param valorOscilacaoOpcaoVendaAtual the valorOscilacaoOpcaoVendaAtual to set
	 */
	public void setValorOscilacaoOpcaoVendaAtual(Float valorOscilacaoOpcaoVendaAtual) {
		this.valorOscilacaoOpcaoVendaAtual = valorOscilacaoOpcaoVendaAtual;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getMotivoVenda() {
		return motivoVenda;
	}

	public void setMotivoVenda(String motivoVenda) {
		this.motivoVenda = motivoVenda;
	}
	
}
