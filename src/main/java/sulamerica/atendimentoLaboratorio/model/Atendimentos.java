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
public class Atendimentos {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(updatable = false, unique = true, nullable = false)
	@JsonProperty("codigo_atendimento")
	private UUID id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "identificador_paciente", referencedColumnName = "id")
	@JsonIgnoreProperties("atendimento")
	private Pacientes paciente;

	@NotBlank
	@JsonProperty("tipo_atendimento")
	private String tipoAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss-03:00")
	@JsonProperty("data_atendimento")
	private Date dataAtendimento = new java.sql.Date(System.currentTimeMillis());

	@NotBlank
	private String local;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Servicos> servicos = new ArrayList<Servicos>();
	
	private Boolean cancelado;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pacientes getPaciente() {
		return paciente;
	}

	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}
}
