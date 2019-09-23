package br.org.sopa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sopa.domain.Frequencia;
import br.org.sopa.domain.Assistido;

public interface Frequencias extends JpaRepository<Frequencia, Long>{
	
	public List<Frequencia> findByAssistido(Assistido assistido);
	
	public List<Frequencia> findByDataDistribuicaoAndAssistidoPontoId(LocalDate dataDistribuicao, Long id);

}
