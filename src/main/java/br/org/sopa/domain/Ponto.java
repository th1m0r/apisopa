package br.org.sopa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ponto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private List<Assistido> assistidos;

	public Ponto() {
	}

	public Ponto(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "O nome é obrigatório")
	@NotEmpty(message = "O nome não pode ser em branco")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "ponto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Assistido> getAssistidos() {
		return assistidos;
	}

	public void setAssistidos(List<Assistido> assistidos) {
		this.assistidos = assistidos;
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
		Ponto other = (Ponto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	@PrePersist
	@PreUpdate
	public void prePersistUpdate() {
		nome = nome.toUpperCase();
	}

}
