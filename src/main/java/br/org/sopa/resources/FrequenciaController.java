package br.org.sopa.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.sopa.domain.Frequencia;
import br.org.sopa.service.AssistidoService;

@RestController
@RequestMapping(value = "/frequencias")
public class FrequenciaController {

	@Autowired
	private AssistidoService pessoaService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@CrossOrigin
	public ResponseEntity<Void> salvarFrequencia(@RequestBody List<Frequencia> listaFrequencia) {
		pessoaService.salvarFrequencia(listaFrequencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/ponto/{idPonto}/dataDistribuicao/{dataDistribuicao}")
	@CrossOrigin
	public ResponseEntity<List<Frequencia>> montarFrequencia(@PathVariable("idPonto") Long idPonto, 
			@PathVariable("dataDistribuicao") LocalDate dataDistribuicao) {
		System.out.println("data distribuicao: " + dataDistribuicao);
		return ResponseEntity.ok().body(pessoaService.montarFrequencia(idPonto, dataDistribuicao));
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/assistido/{idAssistido}")
	@CrossOrigin
	public ResponseEntity<List<Frequencia>> consultarFrequencia(@PathVariable("idAssistido") Long idAssistido) {
		return ResponseEntity.ok().body(pessoaService.consultarFrequencia(idAssistido));
	}

}
