package br.org.sopa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sopa.domain.Frequencia;
import br.org.sopa.domain.Pessoa;
import br.org.sopa.domain.StatusPessoa;
import br.org.sopa.repository.Frequencias;
import br.org.sopa.repository.Pessoas;
import br.org.sopa.service.exception.RecursoNaoEncontradoException;

@Service
public class PessoaService {

	@Autowired
	private Pessoas pessoas;

	@Autowired
	private Frequencias frequencias;

	public List<Pessoa> listar() {
		return pessoas.findAll();
	}

	public Pessoa pesquisar(Long id) {
		Optional<Pessoa> pessoaOptional = pessoas.findById(id);
		if (!pessoaOptional.isPresent()) {
			throw new RecursoNaoEncontradoException("Pessoa não encontrada");
		}
		return pessoaOptional.get();
	}

	public Pessoa salvar(Pessoa pessoa) {
		pessoa.setId(null);
		if (pessoa.getStatus() == null) {
			pessoa.setStatus(StatusPessoa.N);
		}
		return pessoas.save(pessoa);
	}

	public void alterar(Pessoa pessoa) {
		existe(pessoa);
		pessoas.save(pessoa);
	}

	public void excluir(Long id) {
		existe(new Pessoa(id));
		pessoas.deleteById(id);
	}

	private void existe(Pessoa pessoa) {
		pesquisar(pessoa.getId());
	}

	public List<Frequencia> montarFrequencia(Long id) {
		List<Frequencia> listaFrequencia = frequencias.findByDataDistribuicaoAndPessoaPontoId(LocalDate.now(), id);
		if (listaFrequencia.isEmpty()) {
			listaFrequencia = new ArrayList<>();
			for (Pessoa pessoa : pessoas.findByPontoId(id)) {
				listaFrequencia.add(new Frequencia(pessoa));
			}
		}
		return listaFrequencia;
	}

	public List<Pessoa> listarAniversariantes() {
		LocalDate hoje = LocalDate.now();
		return pessoas.findAniversariantes(hoje.getMonthValue());
	}

	public void salvarFrequencia(List<Frequencia> listaFrequencia) {
		System.out.println(frequencias.saveAll(listaFrequencia));
	}

}
