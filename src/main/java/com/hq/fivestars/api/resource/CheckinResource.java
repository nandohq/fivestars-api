package com.hq.fivestars.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq.fivestars.api.event.CreatedResourceEvent;
import com.hq.fivestars.api.model.Checkin;
import com.hq.fivestars.api.model.vo.HospedeVO;
import com.hq.fivestars.api.repository.CheckinRepository;
import com.hq.fivestars.api.repository.filter.CheckinFilter;
import com.hq.fivestars.api.service.CheckinService;

@RestController
@RequestMapping("/checkin")
public class CheckinResource {
	
	@Autowired private CheckinRepository checkinRepository;
	@Autowired private CheckinService checkinService;
	
	@Autowired private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<HospedeVO> pesquisar(CheckinFilter filtro) {
		return checkinRepository.filtrar(filtro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Checkin> porId(@PathVariable Long id) {
		Checkin retornado = checkinRepository.findOne(id);
		return retornado != null ? ResponseEntity.ok(retornado) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Checkin> salvar(@Valid @RequestBody Checkin checkin, HttpServletResponse response){
		Checkin retornado = checkinService.salvar(checkin);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, retornado.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(retornado);
	}
}