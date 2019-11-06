package br.org.sopa.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sopa.domain.Dashboard;
import br.org.sopa.repository.Assistidos;

@Service
public class DashboardService {

	@Autowired
	private Assistidos assistidos;

	public Dashboard carregar() {
		Dashboard dash = new Dashboard();
		dash.setNumeroAssistidos(assistidos.numeroAssitidos());
		dash.setAptos(assistidos.countAptos());
		dash.setAniversariantes(assistidos.countAniversariantes(LocalDate.now().getMonthValue()));
		dash.setCadastrados(assistidos.countCadastrados());
		dash.setNaoCadastrados(assistidos.countNaoCadastrados());
		return dash;
	}

}
