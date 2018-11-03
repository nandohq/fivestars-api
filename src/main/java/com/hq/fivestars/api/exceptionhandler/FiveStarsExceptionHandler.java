package com.hq.fivestars.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FiveStarsExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msg;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgAmigavel = msg.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDebug = ex.getCause().toString();
		
		List<Error> erros = Arrays.asList(new Error(msgAmigavel, msgDebug));
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> erros = obterErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		
		String msgAmigavel = msg.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDebug = ex.toString();
		
		List<Error> erros = Arrays.asList(new Error(msgAmigavel, msgDebug));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String msgAmigavel = msg.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String msgDebug = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> erros = Arrays.asList(new Error(msgAmigavel, msgDebug));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
}
	
	private List<Error> obterErros(BindingResult bindingResult){
		List<Error> erros = new ArrayList<>();
		
		for(FieldError field : bindingResult.getFieldErrors()) {
			String msgAmigavel = msg.getMessage(field, LocaleContextHolder.getLocale());
			String msgDebug = field.toString();
			
			erros.add(new Error(msgAmigavel, msgDebug));
		}
		
		return erros;
	}
	
	public static class Error {
			
		private String msgAmigavel;
		private String msgDebug;
		
		public Error(String msgAmigavel, String msgDebug) {
			this.msgAmigavel = msgAmigavel;
			this.msgDebug = msgDebug;
		}

		public String getMsgAmigavel() {
			return msgAmigavel;
		}
		
		public String getMsgDebug() {
			return msgDebug;
		}			
	}
}