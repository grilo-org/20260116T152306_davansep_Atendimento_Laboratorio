package sulamerica.atendimentoLaboratorio.controller;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sulamerica.atendimentoLaboratorio.model.Atendimentos;
import sulamerica.atendimentoLaboratorio.model.Pacientes;
import sulamerica.atendimentoLaboratorio.repository.AtendimentosRepository;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@RestController
@RequestMapping("/atendimentos")
@CrossOrigin("*")
public class AtendimentosController {

	@Autowired
	private AtendimentosRepository repository;

	@PostMapping("/paciente/{codigo_paciente}")
	public ResponseEntity<Atendimentos> post(@Valid @PathVariable UUID codigo_paciente,
			@RequestBody Atendimentos atendimento) {
		Pacientes paciente = new Pacientes();
		paciente.setId(codigo_paciente);
		atendimento.setPaciente(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(atendimento));
	}

	@GetMapping("/pacientes/{codigo_paciente}")
	public ResponseEntity<List<Atendimentos>> getByPacienteId(@PathVariable UUID codigo_paciente) {
		List<Atendimentos> lista = repository.findByPacienteId(codigo_paciente);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/atendimento/{codigo_atendimento}")
	public ResponseEntity<Atendimentos> getById(@PathVariable UUID codigo_atendimento) {
		return repository.findById(codigo_atendimento).stream().map(resp -> ResponseEntity.ok(resp)).findAny()
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/atendimento/")
	public ResponseEntity<Atendimentos> put(@Valid @RequestBody Atendimentos atendimento) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(atendimento));
	}

	@PostMapping("/atendimento/{codigo_atendimento}")
	public ResponseEntity<Atendimentos> cancelar(@Valid @PathVariable UUID codigo_atendimento) {
		Atendimentos atendimento = repository.findById(codigo_atendimento).orElseThrow();
		atendimento.setCancelado(true);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(atendimento));
	}
}
