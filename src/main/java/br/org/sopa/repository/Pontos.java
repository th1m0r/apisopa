package br.org.sopa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.sopa.domain.Ponto;

@Repository
public interface Pontos extends JpaRepository<Ponto, Long> {

}
