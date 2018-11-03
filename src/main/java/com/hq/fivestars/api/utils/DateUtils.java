package com.hq.fivestars.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária para manipulação de datas
 * @author Fernando Souza
 * @since 03/11/2018
 */
public class DateUtils {
	
	/**
	 * Retorna uma lista com todos os dias não úteis (fins de semana) de um intervalo de datas informado
	 * @param dataInicio {@link LocalDate} Data inicial do intervalo
	 * @param dataFim {@link LocalDate} Data final do intervalo
	 * @return {@link List} Lista de dias não úteis retornada
	 */
	public static List<LocalDate> checarFinsDeSemana(LocalDate dataInicio, LocalDate dataFim) {
		 
		List<LocalDate> finsDeSemana = new ArrayList<>();
		    
		    int diaInicial = dataInicio.getDayOfWeek().getValue();
		    int diasAteFimSemana = 6-diaInicial;
		    
		    LocalDate proximoFimSemana = dataInicio.plusDays(diasAteFimSemana);
		    
		    while(proximoFimSemana.isBefore(dataFim)){
		        finsDeSemana.add(proximoFimSemana);
		        if(proximoFimSemana.plusDays(1).isBefore(dataFim)){
		            finsDeSemana.add(proximoFimSemana.plusDays(1));
		        }
		        proximoFimSemana = proximoFimSemana.plusDays(7);
		    }
		    
		return finsDeSemana;
	}
	
	/**
	 * Retorna a quantidade de dias de um intervalo de data informado
	 * @param dataInicio {@link LocalDate} Data inicial do intervalo
	 * @param dataFim {@link LocalDate} Data final do intervalo
	 * @return {@link Long} Quantidade de dias do intervalo
	 */
	public static Long contarDias(LocalDateTime dataInicio, LocalDateTime dataFim) {
		return ChronoUnit.DAYS.between(dataInicio, dataFim);
	}	
}