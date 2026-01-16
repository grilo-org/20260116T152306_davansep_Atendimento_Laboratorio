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
public class Servicos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_servico")
	private Long identificadorServico;
	
	@NotBlank
	@JsonProperty("descricao_procedimento")
	private String descricaoProcedimento;

	@NotNull
	@JsonProperty("valor_servico")
	private Double valorServico;

	@ManyToMany(mappedBy = "servicos", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Atendimentos> atendimento = new ArrayList<Atendimentos>();

	public Long getIdentificadorServico() {
		return identificadorServico;
	}

	public void setIdentificadorServico(Long identificadorServico) {
		this.identificadorServico = identificadorServico;
	}

	public String getDescricaoProcedimento() {
		return descricaoProcedimento;
	}

	public void setDescricaoProcedimento(String descricaoProcedimento) {
		this.descricaoProcedimento = descricaoProcedimento;
	}

	public Double getValorServico() {
		return valorServico;
	}

	public void setValorServico(Double valorServico) {
		this.valorServico = valorServico;
	}

	public List<Atendimentos> getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(List<Atendimentos> atendimento) {
		this.atendimento = atendimento;
	}
}
