package br.org.sopa.repository.helper;

import java.util.List;

import br.org.sopa.domain.Assistido;
import br.org.sopa.repository.filter.AssistidoFiltro;

public interface AssistidosQueries {

	public List<Assistido> filtrar(AssistidoFiltro filtro);

}
