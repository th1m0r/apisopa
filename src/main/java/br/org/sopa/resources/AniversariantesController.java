package br.org.sopa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.sopa.domain.Assistido;
import br.org.sopa.service.AssistidoService;

@RestController
@RequestMapping(value = "/aniversariantes")
public class AniversariantesController {

	@Autowired
	private AssistidoService pessoaService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@CrossOrigin
	public ResponseEntity<List<Assistido>> listarAniversariantes(Integer mes) {
		return ResponseEntity.ok().body(pessoaService.listarAniversariantes(mes));
	}

}
