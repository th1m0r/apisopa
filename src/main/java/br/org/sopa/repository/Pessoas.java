package br.org.sopa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.sopa.domain.Pessoa;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, Long> {
	
	public List<Pessoa> findByPontoId(Long id);

	@Query("select p from Pessoa p where month(p.dataNascimento)=?1 order by p.ponto,p.dataNascimento")
	public List<Pessoa> findAniversariantes(int monthValue);

}
