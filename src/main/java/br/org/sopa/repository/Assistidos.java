package br.org.sopa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.sopa.domain.Assistido;
import br.org.sopa.repository.helper.AssistidosQueries;

@Repository
public interface Assistidos extends JpaRepository<Assistido, Long>, AssistidosQueries {

	public List<Assistido> findByPontoIdOrderByNome(Long id);

	@Query("from Assistido where month(dataNascimento)=?1 and situacao='C' order by ponto,dataNascimento")
	public List<Assistido> findAniversariantes(int monthValue);

	@Query("select count(a) from Assistido a")
	public Long numeroAssitidos();

	@Query("select count(a) from Assistido a where month(dataNascimento)=?1 and situacao='C'")
	public Long countAniversariantes(int monthValue);

	@Query(value = "select count(*) from " + "(select id_assistido,count(*) as vezes from frequencia f "
			+ "inner join assistido a on f.id_assistido=a.id where presente=1 and a.situacao!='C' group by id_assistido) aptos "
			+ "where vezes>=5", nativeQuery = true)
	public Long countAptos();

	@Query(value = "select a from Assistido a where (select count(*) from a.frequencias f where f.presente=1 )>=5 and a.situacao!='C'")
	public List<Assistido> findAptos();

	@Query("select count(a) from Assistido a where situacao='C'")
	public Long countCadastrados();

	@Query("select count(a) from Assistido a where situacao='N'")
	public Long countNaoCadastrados();

	@Query("select a from Assistido a where not exists (select f from a.frequencias f where f.dataDistribuicao=?1) and a.ponto.id=?2")
	public List<Assistido> findAssistidoSemFrequencia(LocalDate dataDistribuicao, Long id);

}
