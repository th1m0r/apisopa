package br.org.sopa.domain;

public class Dashboard {

	private Long numeroAssistidos;
	private Long aniversariantes;
	private Long aptos;

	public Dashboard() {
		super();
	}

	public Dashboard(Long numeroAssistidos, Long aniversariantes, Long aptos) {
		super();
		this.numeroAssistidos = numeroAssistidos;
		this.aniversariantes = aniversariantes;
		this.aptos = aptos;
	}

	public Long getNumeroAssistidos() {
		return numeroAssistidos;
	}

	public void setNumeroAssistidos(Long numeroAssistidos) {
		this.numeroAssistidos = numeroAssistidos;
	}

	public Long getAniversariantes() {
		return aniversariantes;
	}

	public void setAniversariantes(Long aniversariantes) {
		this.aniversariantes = aniversariantes;
	}

	public Long getAptos() {
		return aptos;
	}

	public void setAptos(Long aptos) {
		this.aptos = aptos;
	}

}
