package sulamerica.atendimentoLaboratorio.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sulamerica.atendimentoLaboratorio.model.Servicos;
import sulamerica.atendimentoLaboratorio.repository.ServicosRepository;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@RestController
@RequestMapping("/servicos")
@CrossOrigin("*")
public class ServicosController {

	@Autowired
	private ServicosRepository repository;

	@GetMapping
	public ResponseEntity<List<Servicos>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id_servico}")
	public ResponseEntity<Servicos> getById(@PathVariable Long id_servico) {
		return repository.findById(id_servico).stream().map(resp -> ResponseEntity.ok(resp)).findAny()
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Servicos> post(@Valid @RequestBody Servicos servicos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(servicos));
	}

	@PutMapping("/servico/")
	public ResponseEntity<Servicos> atualizar(@Valid @RequestBody Servicos servicos) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(servicos));
	}
}
