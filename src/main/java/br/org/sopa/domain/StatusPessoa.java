package br.org.sopa.domain;

public enum StatusPessoa {

	CADASTRADO("Cadastrado"), APTO("Apto"), NAO_CADASTRADO("Não Cadastrado");

	private final String descricao;

	private StatusPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
