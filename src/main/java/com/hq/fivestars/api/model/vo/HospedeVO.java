package com.hq.fivestars.api.model.vo;

import java.math.BigDecimal;

import com.hq.fivestars.api.model.Checkin;

/**
 * Classe de transferência de informações para simples exibição <br>
 * usada para conversão dos dados mais importantes da entidade {@link Checkin}
 * 
 * @author Fernando Souza
 * @since 03/11/2018
 */
public class HospedeVO {
	
	public HospedeVO() {}
	
	public HospedeVO(String nome, String documento, BigDecimal valor) {
		this.nome = nome;
		this.documento = documento;
		this.valor = valor;
	}
	
	private String nome;
	private String documento;
	private BigDecimal valor;
	
	/** GETTERS & SETTERS **/
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}