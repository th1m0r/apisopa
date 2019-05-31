package br.org.sopa.domain;

public enum StatusPessoa {

	C("Cadastrado"), A("Apto"), N("Não Cadastrado");

	private final String descricao;

	private StatusPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
