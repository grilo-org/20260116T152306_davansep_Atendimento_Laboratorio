package sulamerica.atendimentoLaboratorio.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@Entity
public class Pacientes {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(updatable = false, unique = true, nullable = false)
	@JsonProperty("identificador_paciente")
	private UUID id;

	@NotBlank
	@Size(min = 2, max = 50)
	private String nome;

	@NotBlank
	private String sexo;

	@NotNull
	private Byte idade;

	@NotBlank
	private String endereco;

	@NotBlank
	@Size(min = 10, max = 11)
	private String telefone;

	private String email;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Atendimentos> atendimento = new ArrayList<Atendimentos>();

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Byte getIdade() {
		return idade;
	}

	public void setIdade(Byte idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Atendimentos> getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(List<Atendimentos> atendimento) {
		this.atendimento = atendimento;
	}
}
