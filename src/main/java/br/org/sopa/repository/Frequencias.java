package br.org.sopa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sopa.domain.Assistido;
import br.org.sopa.domain.Frequencia;

public interface Frequencias extends JpaRepository<Frequencia, Long>{
	
	public List<Frequencia> findByAssistido(Assistido assistido);
	
	public List<Frequencia> findByDataDistribuicaoAndAssistidoPontoIdOrderByAssistidoNome(LocalDate dataDistribuicao, Long id);
	
	
}
