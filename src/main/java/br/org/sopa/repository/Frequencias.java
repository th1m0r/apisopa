package br.org.sopa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sopa.domain.Frequencia;
import br.org.sopa.domain.Pessoa;

public interface Frequencias extends JpaRepository<Frequencia, Long>{
	
	public List<Frequencia> findByPessoa(Pessoa pessoa);
	
	public List<Frequencia> findByDataDistribuicaoAndPessoaPontoId(LocalDate dataDistribuicao, Long id);

}
