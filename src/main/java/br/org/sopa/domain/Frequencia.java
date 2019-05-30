package br.org.sopa.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Frequencia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Pessoa pessoa;
	private boolean presente;
	private LocalDate dataDistribuicao;

	public Frequencia() {
	}

	public Frequencia(Pessoa pessoa) {
		this.pessoa = pessoa;
		dataDistribuicao = LocalDate.now();
		presente = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = true)
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_distribuicao")
	public LocalDate getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(LocalDate dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frequencia other = (Frequencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Frequencia [pessoa=" + pessoa.getNome() + ", presente=" + presente + ", dataSopa=" + dataDistribuicao + "]";
	}

}
