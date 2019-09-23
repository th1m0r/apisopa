package br.org.sopa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.sopa.domain.Assistido;
import br.org.sopa.repository.helper.AssistidosQueries;

@Repository
public interface Assistidos extends JpaRepository<Assistido, Long>, AssistidosQueries {

	public List<Assistido> findByPontoId(Long id);
	
	@Query("from Assistido where month(dataNascimento)=?1 and situacao='C' order by ponto,dataNascimento")
	public List<Assistido> findAniversariantes(int monthValue);

}
