package com.hq.fivestars.api.model;

import java.math.BigDecimal;

/**
 * Enum utilitário para cálculo do valor da estadia e do estacionamento<br>
 * com base nos dias da semana
 * @author Fernando Souza
 * @since 03/11/2018
 */
public enum TipoDias {
	
	UTEIS("úteis (Segunda - Sexta)", new BigDecimal("120.00"), new BigDecimal("15.00")),
	NAO_UTEIS("não úteis (fins de semana)", new BigDecimal("150.00"), new BigDecimal("20.00"));
	
	private String descricao;
	private BigDecimal valorEstadia;
	private BigDecimal valorEstacionamento;
	
	private TipoDias(String descricao, BigDecimal valorEstadia, BigDecimal valorEstacionamento) {
		this.descricao = descricao;
		this.valorEstadia = valorEstadia;
		this.valorEstacionamento = valorEstacionamento;
	}
	
	/** UTILS **/
	
	/**
	 * Retorna o valor da estadia a partir do total de dias informado
	 * @param dias {@link Long} Total de dias em que o cliente estará hospedado
	 * @return {@link BigDecimal} Valor total da estadia
	 */
	public BigDecimal calcularEstadia(Long dias) {
		return this.valorEstadia.multiply(BigDecimal.valueOf(dias));
	}
	
	/**
	 * Retorna o valor do estacionamento a partir do total de dias informado
	 * @param dias {@link Long} Total de dias em que o cliente estará hospedado
	 * @return {@link BigDecimal} Valor total do estacionamento
	 */
	public BigDecimal calcularEstacionamento(Long dias) {
		return this.valorEstacionamento.multiply(BigDecimal.valueOf(dias));
	}
	
	/**
	 * Retorna um texto descrevendo o valor total da estadia a partir do total de dias informados
	 * @param dias {@link Long} Total de dias em que o cliente estará hospedado
	 * @return {@link String} Descrição do valor total da estadia
	 */
	public String registrarEstadia(Long dias) {
		return "Valor total da estadia nos "+dias+" dias "+this.descricao+" : R$"+calcularEstadia(dias)+"\n";
	}
	
	/**
	 * Retorna um texto descrevendo o valor total do estacionamento a partir do total de dias informados
	 * @param dias {@link Long} Total de dias em que o cliente estará hospedado
	 * @return {@link String} Descrição do valor total do estacionamento
	 */
	public String registrarEstacionamento(Long dias) {
		return "Valor total do estacionamento nos "+dias+" dias "+this.descricao+" : R$"+calcularEstacionamento(dias)+"\n";
	}
	
	/** GETTERS **/
	
	public String getDescricao() {
		return descricao;
	}
	
	public BigDecimal getValorEstadia() {
		return valorEstadia;
	}
	
	public BigDecimal getValorEstacionamento() {
		return valorEstacionamento;
	}	
}