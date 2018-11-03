package com.hq.fivestars.api.repository.checkin;

import java.util.List;

import com.hq.fivestars.api.model.vo.HospedeVO;
import com.hq.fivestars.api.repository.filter.CheckinFilter;

public interface CheckinRepositoryQuery {
	
	public List<HospedeVO> filtrar(CheckinFilter filtro);
}
