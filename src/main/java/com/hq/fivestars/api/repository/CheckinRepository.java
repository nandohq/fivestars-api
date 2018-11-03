package com.hq.fivestars.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hq.fivestars.api.model.Checkin;
import com.hq.fivestars.api.repository.checkin.CheckinRepositoryQuery;

public interface CheckinRepository extends JpaRepository<Checkin, Long>, CheckinRepositoryQuery {

}
