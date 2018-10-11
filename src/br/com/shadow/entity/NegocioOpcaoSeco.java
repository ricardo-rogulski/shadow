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
@Table(name="negocio_opcao_seco")
public class NegocioOpcaoSeco {
	
	@Id @GeneratedValue
	private Integer id;
	@ManyToOne
	private User user;
	private String acao;	
	private String opcao;
	private Float valorOpcaoInicial;
	private Float valorOpcaoFinal;
	private Float valorAcaoInicial;
	private Float valorAcaoFinal;
	private Float valorExercOpcao;
	private Integer quantidade;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCompra;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVenda;
	private String motivoVenda;
	
	@Transient
	private Float valorOpcaoAtual;
	@Transient
	private Float valorAcaoAtual;
	@Transient
	private Float valorOscilacaoAcaoAtual;
	@Transient
	private Float valorOscilacaoOpcaoAtual;

		
	public Float getLucroAtual(){
		Float lucro = ((valorOpcaoAtual-valorOpcaoInicial)*quantidade);
		return lucro;		
	}
	
	public Float getLucroFinal(){
		Float lucro = ((valorOpcaoFinal-valorOpcaoInicial)*quantidade);
		return lucro;		
	}
	
	public Float getLucroFinalPCT(){
		Float valorInvestido = valorOpcaoInicial*quantidade;
		Float lucroFinal = getLucroFinal();
		Float lucroPct = ((lucroFinal/valorInvestido)*100);
		return paraDuasCasas(lucroPct);		
	}

	
	public Float getLucroAtualPCT(){
		Float valorInvestido = valorOpcaoInicial*quantidade;
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
	
	public String getQuantidadeStr(){
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(quantidade);
	}
	
	public String getLucroAtualStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroAtual());		
	}

	public String getLucroFinalStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroFinal());		
	}
	
	
	public String getLucroAtualPCTStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroAtualPCT());		
	}

	public String getLucroFinalPCTStr(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getLucroFinalPCT());		
	}

	
	public String getDataCompraStr() {
		return Util.getDataAsDiaMesAnoHoraMinuto(dataCompra);
	}
	
	public String getDataVendaStr() {
		return Util.getDataAsDiaMesAnoHoraMinuto(dataVenda);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Float getValorOpcaoInicial() {
		return valorOpcaoInicial;
	}

	public void setValorOpcaoInicial(Float valorOpcaoInicial) {
		this.valorOpcaoInicial = valorOpcaoInicial;
	}

	public Float getValorOpcaoFinal() {
		return valorOpcaoFinal;
	}

	public void setValorOpcaoFinal(Float valorOpcaoFinal) {
		this.valorOpcaoFinal = valorOpcaoFinal;
	}

	public Float getValorAcaoInicial() {
		return valorAcaoInicial;
	}

	public void setValorAcaoInicial(Float valorAcaoInicial) {
		this.valorAcaoInicial = valorAcaoInicial;
	}

	public Float getValorAcaoFinal() {
		return valorAcaoFinal;
	}

	public void setValorAcaoFinal(Float valorAcaoFinal) {
		this.valorAcaoFinal = valorAcaoFinal;
	}

	public Float getValorExercOpcao() {
		return valorExercOpcao;
	}

	public void setValorExercOpcao(Float valorExercOpcao) {
		this.valorExercOpcao = valorExercOpcao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getMotivoVenda() {
		return motivoVenda;
	}

	public void setMotivoVenda(String motivoVenda) {
		this.motivoVenda = motivoVenda;
	}

	public Float getValorOpcaoAtual() {
		return valorOpcaoAtual;
	}

	public void setValorOpcaoAtual(Float valorOpcaoAtual) {
		this.valorOpcaoAtual = valorOpcaoAtual;
	}

	public Float getValorAcaoAtual() {
		return valorAcaoAtual;
	}

	public void setValorAcaoAtual(Float valorAcaoAtual) {
		this.valorAcaoAtual = valorAcaoAtual;
	}

	public Float getValorOscilacaoAcaoAtual() {
		return valorOscilacaoAcaoAtual;
	}

	public void setValorOscilacaoAcaoAtual(Float valorOscilacaoAcaoAtual) {
		this.valorOscilacaoAcaoAtual = valorOscilacaoAcaoAtual;
	}

	public Float getValorOscilacaoOpcaoAtual() {
		return valorOscilacaoOpcaoAtual;
	}

	public void setValorOscilacaoOpcaoAtual(Float valorOscilacaoOpcaoAtual) {
		this.valorOscilacaoOpcaoAtual = valorOscilacaoOpcaoAtual;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
