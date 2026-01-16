package sulamerica.atendimentoLaboratorio.controller;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sulamerica.atendimentoLaboratorio.model.Pacientes;
import sulamerica.atendimentoLaboratorio.repository.PacientesRepository;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacientesController {

	@Autowired
	private PacientesRepository repository;

	@GetMapping
	public ResponseEntity<List<Pacientes>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/paciente/{id}")
	public ResponseEntity<Pacientes> getById(@PathVariable UUID id) {
		return repository.findById(id).stream().map(resp -> ResponseEntity.ok(resp)).findAny()
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Pacientes> post(@Valid @RequestBody Pacientes paciente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(paciente));
	}

	@PutMapping("/paciente/")
	public ResponseEntity<Pacientes> put(@Valid @RequestBody Pacientes paciente) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(paciente));
	}

}
