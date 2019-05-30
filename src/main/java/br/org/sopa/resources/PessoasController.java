package br.org.sopa.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.sopa.domain.Frequencia;
import br.org.sopa.domain.Pessoa;
import br.org.sopa.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PessoasController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Pessoa>> listar() {
		return ResponseEntity.ok().body(pessoaService.listar());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pessoa pessoa) {
		pessoa = pessoaService.salvar(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> alterar(@Valid Pessoa pessoa, @PathVariable("id") Long id) {
		pessoa.setId(id);
		pessoaService.alterar(pessoa);
		return null;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		pessoaService.excluir(id);
		return null;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/frequencia")
	public ResponseEntity<Void> savarFrequencia(@RequestBody List<Frequencia> listaFrequencia) {
		pessoaService.salvarFrequencia(listaFrequencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/frequencia/{idPonto}")
	public ResponseEntity<List<Frequencia>> montarFrequencia(@PathVariable("idPonto") Long idPonto) {
		return ResponseEntity.ok().body(pessoaService.montarFrequencia(idPonto));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/aniversariantes")
	public ResponseEntity<List<Pessoa>> listarAniversariantes() {
		return ResponseEntity.ok().body(pessoaService.listarAniversariantes());
	} 

}
