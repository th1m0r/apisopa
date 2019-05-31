package br.org.sopa.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.sopa.domain.DetalheErro;
import br.org.sopa.service.exception.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ RecursoNaoEncontradoException.class })
	public ResponseEntity<Object> idInvalido(RecursoNaoEncontradoException ex, WebRequest request) {

		return handleExceptionInternal(ex, DetalheErro.builder()
				.addErro(ex.getMessage())
				.addStatus(HttpStatus.NOT_FOUND)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request)).build(), 
				new HttpHeaders(), 
				HttpStatus.NOT_FOUND, request);
	}

	private String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}

	private String getHttpMethod(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getMethod();
	}
}
