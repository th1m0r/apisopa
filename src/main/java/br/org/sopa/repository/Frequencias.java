package br.org.sopa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.sopa.domain.Frequencia;

public interface Frequencias extends JpaRepository<Frequencia, Long> {

	@Query("select new br.org.sopa.domain.Frequencia(f.presente,f.dataDistribuicao) from Frequencia f where f.assistido.id=?1")
	public List<Frequencia> findByAssistidoId(Long idAssistido);

	public List<Frequencia> findByDataDistribuicaoAndAssistidoPontoIdOrderByAssistidoNome(LocalDate dataDistribuicao, Long id);

}
