package com.hq.fivestars.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.repository.hospede.HospedeRepositoryQuery;

public interface HospedeRepository extends JpaRepository<Hospede, Long>, HospedeRepositoryQuery {

}
