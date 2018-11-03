package com.hq.fivestars.api.repository.checkin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hq.fivestars.api.model.Checkin;
import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.model.vo.HospedeVO;
import com.hq.fivestars.api.repository.filter.CheckinFilter;

public class CheckinRepositoryImpl implements CheckinRepositoryQuery {
	
	@PersistenceContext private EntityManager manager;

	@Override
	public List<HospedeVO> filtrar(CheckinFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<HospedeVO> criteria = builder.createQuery(HospedeVO.class);
		Root<Checkin> root = criteria.from(Checkin.class);
		Join<Checkin, Hospede> joinHospede = (Join<Checkin, Hospede>) root.<Checkin, Hospede>join("hospede");
		
		Predicate[] predicates = criarPredicates(filtro, builder, root);
		
		criteria.select(builder.construct(HospedeVO.class, joinHospede.<Hospede>get("nome"), joinHospede.<Hospede>get("documento"), root.get("valor")));
		criteria.where(predicates);
		
		TypedQuery<HospedeVO> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	private Predicate[] criarPredicates(CheckinFilter filtro, CriteriaBuilder builder, Root<Checkin> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(filtro.isPresentes()) {
			predicates.add(builder.lessThanOrEqualTo(root.<LocalDateTime>get("dataHoraEntrada"), LocalDateTime.now()));
			predicates.add(builder.greaterThanOrEqualTo(root.<LocalDateTime>get("dataHoraSaida"), LocalDateTime.now()));
		}else {
			predicates.add(builder.or(
				builder.and(builder.lessThan(root.<LocalDateTime>get("dataHoraEntrada"), LocalDateTime.now()), 
				builder.lessThan(root.<LocalDateTime>get("dataHoraSaida"), LocalDateTime.now())),
				builder.and(builder.greaterThan(root.<LocalDateTime>get("dataHoraEntrada"), LocalDateTime.now()),
				builder.greaterThan(root.<LocalDateTime>get("dataHoraSaida"), LocalDateTime.now()))));
		}		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}