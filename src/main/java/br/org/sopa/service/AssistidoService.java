package br.org.sopa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sopa.domain.Assistido;
import br.org.sopa.domain.Frequencia;
import br.org.sopa.domain.StatusPessoa;
import br.org.sopa.repository.Assistidos;
import br.org.sopa.repository.Frequencias;
import br.org.sopa.repository.filter.AssistidoFiltro;
import br.org.sopa.service.exception.RecursoNaoEncontradoException;

@Service
public class AssistidoService {

	@Autowired
	private Assistidos pessoas;

	@Autowired
	private Frequencias frequencias;

	public List<Assistido> listar(AssistidoFiltro assistidoFiltro) {
		return pessoas.filtrar(assistidoFiltro);
	}

	public Assistido pesquisar(Long id) {
		Optional<Assistido> pessoaOptional = pessoas.findById(id);
		if (!pessoaOptional.isPresent()) {
			throw new RecursoNaoEncontradoException("Assistido não cadastrado");
		}
		return pessoaOptional.get();
	}

	public Assistido salvar(Assistido assistido) {
		if (assistido.getSituacao() == null) {
			assistido.setSituacao(StatusPessoa.N);
		}
		return pessoas.save(assistido);
	}

	public void alterar(Assistido pessoa) {
		existe(pessoa);
		pessoas.save(pessoa);
	}

	public void excluir(Long id) {
		existe(new Assistido(id));
		pessoas.deleteById(id);
	}

	private void existe(Assistido pessoa) {
		pesquisar(pessoa.getId());
	}

	public List<Frequencia> montarFrequencia(Long id) {
		List<Frequencia> listaFrequencia = frequencias.findByDataDistribuicaoAndAssistidoPontoIdOrderByAssistidoNome(LocalDate.now(), id);
		if (listaFrequencia.isEmpty()) {
			listaFrequencia = new ArrayList<>();
			for (Assistido pessoa : pessoas.findByPontoIdOrderByNome(id)) {
				listaFrequencia.add(new Frequencia(pessoa));
			}
		}
		return listaFrequencia;
	}

	public List<Assistido> listarAniversariantes(int mes) {
		LocalDate hoje = LocalDate.now();
		return pessoas.findAniversariantes(mes == 0 ? hoje.getMonthValue() : mes);
	}

	public void salvarFrequencia(List<Frequencia> listaFrequencia) {
		frequencias.saveAll(listaFrequencia);
	}

}
