package com.hq.fivestars.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.fivestars.api.model.Checkin;
import com.hq.fivestars.api.model.TipoDias;
import com.hq.fivestars.api.repository.CheckinRepository;
import com.hq.fivestars.api.utils.DateUtils;

@Service
public class CheckinService {
	
	@Autowired private CheckinRepository checkinRepository;
	
	public Checkin salvar(Checkin checkin) {		
		checkin = calcularValores(checkin);		
		return checkinRepository.save(checkin);		
	}
	
	/**
	 * Calcula o valor total da estadia do hóspede de um checkin informado
	 * @param checkin {@link Checkin} contendo os dados da estadia
	 * @return {@link Checkin} atualizado com os valores
	 */
	private Checkin calcularValores(Checkin checkin) {
		
		List<LocalDate> datasEspeciais = DateUtils.checarFinsDeSemana(checkin.getDataHoraEntrada().toLocalDate(), checkin.getDataHoraSaida().toLocalDate());
		
		Long diasEspeciais = datasEspeciais.isEmpty() ? new Long(0) : new Long(datasEspeciais.size());
		Long diasUteis = DateUtils.contarDias(checkin.getDataHoraEntrada(), checkin.getDataHoraSaida()) - diasEspeciais;
		
		BigDecimal totalDiaria = TipoDias.UTEIS.calcularEstadia(diasUteis).add(TipoDias.NAO_UTEIS.calcularEstadia(diasEspeciais));
		BigDecimal totalEstacionamento = checkin.isTemVeiculo() ? 
			TipoDias.UTEIS.calcularEstacionamento(diasUteis).add(TipoDias.NAO_UTEIS.calcularEstacionamento(diasEspeciais)) : BigDecimal.ZERO;
		
		checkin.setObservacoes(comporObservacoes(diasUteis, diasEspeciais, checkin.isTemVeiculo()));		
		checkin.setValor(checkin.getValor().add(totalDiaria.add(totalEstacionamento)));
		
		if(checkin.getDataHoraSaida().toLocalTime().compareTo(LocalTime.of(16, 30)) > 0) {
			BigDecimal valorExtra = datasEspeciais.contains(checkin.getDataHoraSaida().toLocalDate()) ? new BigDecimal("150.00") : new BigDecimal("120.00");			
			checkin.setValor(checkin.getValor().add(valorExtra));
			checkin.setObservacoes(checkin.getObservacoes().concat("\nValor adicional referente à saída posterior às 16:30: R$"+valorExtra));
		}
		
		return checkin;		
	}
	
	/**
	 * Retorna uma descrição detalhada dos valores a serem pagos pelo cliente
	 * @param diasUteis {@link Long} Total de dias úteis da estadia
	 * @param diasEspeciais {@link Long} Total de dias não úteis da estadia
	 * @param temVeiculo {@link Boolean} Flag que determina se o cliente possui veículo
	 * @return {@link String} Descrição detalhada dos valores a serem pagos pelo cliente
	 */
	private String comporObservacoes(Long diasUteis, Long diasEspeciais, boolean temVeiculo) {
		String observacoes = "\"Descrição do Valor\"\n";
		
		observacoes+=(diasUteis != 0) ? TipoDias.UTEIS.registrarEstadia(diasUteis) : "";
		observacoes+=(diasEspeciais != 0) ? TipoDias.NAO_UTEIS.registrarEstadia(diasEspeciais) : "";
		
		observacoes+=(temVeiculo && diasUteis != 0) ? TipoDias.UTEIS.registrarEstacionamento(diasUteis) : "";
		observacoes+=(temVeiculo && diasEspeciais != 0) ? TipoDias.NAO_UTEIS.registrarEstacionamento(diasEspeciais) : "";
		
		return observacoes;
	}
}