package br.org.sopa.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.org.sopa.domain.Assistido;
import br.org.sopa.domain.StatusPessoa;
import br.org.sopa.repository.filter.AssistidoFiltro;


public class AssistidosImpl implements AssistidosQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Assistido> filtrar(AssistidoFiltro filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Assistido.class);

		adicionarFiltro(filtro, criteria);

		return criteria.list();
	}

	private void adicionarFiltro(AssistidoFiltro filtro, Criteria criteria) {
		criteria.createAlias("ponto", "p");
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (filtro.getPonto() != 0) {
				criteria.add(Restrictions.eq("p.id", filtro.getPonto()));
			}
			if (!filtro.getSituacao().equals("0") ) {
				if(filtro.getSituacao().equals("N")) {
					criteria.add(Restrictions.eq("situacao", StatusPessoa.N));					
				} else if(filtro.getSituacao().equals("A")) {
					criteria.add(Restrictions.eq("situacao", StatusPessoa.A));
				} else {
					criteria.add(Restrictions.eq("situacao", StatusPessoa.C));
				}
			}
		}
	}
}
