package com.hq.fivestars.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Classe de modelo que representa a tabela de checkin
 * @author Fernando Souza
 * @since 02/11/2018
 *
 */
@Entity
@Table(name="tb_checkin")
@DynamicUpdate
public class Checkin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="chk_id")
	private Long id;
	
	@NotNull
	@Column(name="chk_data_hora_entrada")
	private LocalDateTime dataHoraEntrada;
	
	@NotNull
	@Column(name="chk_data_hora_saida")
	private LocalDateTime dataHoraSaida;
	
	@Column(name="chk_valor")
	private BigDecimal valor  = BigDecimal.ZERO;
	
	@Column(name="chk_tem_veiculo")
	private boolean temVeiculo;
	
	@Column(name="chk_observacoes", columnDefinition="text")
	private String observacoes;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="chk_id_hospede")
	private Hospede hospede;
	
	/** GETTERS & SETTERS **/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}
	
	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
	
	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}
	
	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public boolean isTemVeiculo() {
		return temVeiculo;
	}
	
	public void setTemVeiculo(boolean temVeiculo) {
		this.temVeiculo = temVeiculo;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
	/** HASHCODE **/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checkin other = (Checkin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}