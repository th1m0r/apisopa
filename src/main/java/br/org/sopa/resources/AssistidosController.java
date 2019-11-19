package br.org.sopa.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.org.sopa.domain.Assistido;
import br.org.sopa.repository.Assistidos;
import br.org.sopa.repository.filter.AssistidoFiltro;
import br.org.sopa.service.AssistidoService;

@RestController
@RequestMapping(value = "/assistidos")
public class AssistidosController {

	@Autowired
	private AssistidoService assistidoService;
	
	@Autowired
	private Assistidos assistidos;

	@GetMapping
	@CrossOrigin
	public ResponseEntity<List<Assistido>> listar(AssistidoFiltro assistidoFiltro) {
		return ResponseEntity.ok().body(assistidoService.listar(assistidoFiltro));
	}
	
	@GetMapping("/aptos")
	@CrossOrigin
	public ResponseEntity<List<Assistido>> listarAptos() {
		return ResponseEntity.ok().body(assistidos.findAptos());
	}

	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<Assistido> pesquisar(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(assistidoService.pesquisar(id));
	}

	@PostMapping
	@CrossOrigin
	public ResponseEntity<Void> salvar(@Valid @RequestBody Assistido assistido) {
		assistido = assistidoService.salvar(assistido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(assistido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@CrossOrigin
	public ResponseEntity<Void> alterar(@Valid Assistido assistido, @PathVariable("id") Long id) {
		assistido.setId(id);
		assistidoService.alterar(assistido);
		return null;
	}
	
	@PutMapping(value = "/cadastrar/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@CrossOrigin
	public ResponseEntity<Void> efetuarCadastro(@PathVariable("id") Long id) {
		assistidoService.cadastrar(id);
		return null;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@CrossOrigin
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		assistidoService.excluir(id);
		return null;
	}

}
