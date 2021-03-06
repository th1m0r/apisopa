package br.org.sopa.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Assistido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Ponto ponto;
	private LocalDate dataCadastro;
	private StatusPessoa situacao;
	private List<Frequencia> frequencias;

	public Assistido() {
		dataCadastro = LocalDate.now();
	}

	public Assistido(Long id) {
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

	@Size(min = 5, max = 100, message = "O nome precisa ter entre 5 e 100 caracteres")
	@NotNull(message = "O nome é obrigatório")
	@NotEmpty(message = "O nome não pode ser em branco")
	public String getNome() {
		return nome.trim();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "A data de nascimento é obrigatória")
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@ManyToOne
	@JoinColumn(name = "id_ponto", referencedColumnName = "id", nullable = true)
	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	@Column(name = "data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Enumerated(EnumType.STRING)
	public StatusPessoa getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusPessoa situacao) {
		this.situacao = situacao;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "assistido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
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
		Assistido other = (Assistido) obj;
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
