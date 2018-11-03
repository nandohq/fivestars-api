package com.hq.fivestars.api.repository.hospede;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.hq.fivestars.api.model.Hospede;
import com.hq.fivestars.api.repository.filter.HospedeFilter;

public class HospedeRepositoryImpl implements HospedeRepositoryQuery {
	
	@PersistenceContext private EntityManager manager;

	@Override
	public List<Hospede> filtrar(HospedeFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Hospede> criteria = builder.createQuery(Hospede.class);
		Root<Hospede> root = criteria.from(Hospede.class);
		
		Predicate[] predicates = criarPredicates(filtro, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Hospede> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarPredicates(HospedeFilter filtro, CriteriaBuilder builder, Root<Hospede> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get("nome")), "%"+filtro.getNome().toLowerCase()+"%"));
		}
		
		if(!StringUtils.isEmpty(filtro.getDocumento())) {
			predicates.add(builder.like(root.get("documento"), filtro.getDocumento()));
		}
		
		if(!StringUtils.isEmpty(filtro.getTelefone())) {
			predicates.add(builder.like(root.get("telefone"), filtro.getTelefone()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}