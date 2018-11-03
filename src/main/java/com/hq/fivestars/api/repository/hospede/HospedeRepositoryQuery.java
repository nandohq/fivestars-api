package com.hq.fivestars.api.repository.hospede;

import java.util.List;

import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.repository.filter.HospedeFilter;

public interface HospedeRepositoryQuery {
	
	public List<Hospede> filtrar(HospedeFilter filtro);

}
