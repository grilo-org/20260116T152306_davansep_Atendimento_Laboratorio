package sulamerica.atendimentoLaboratorio.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sulamerica.atendimentoLaboratorio.model.Pacientes;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, UUID> {

	Optional<Pacientes> findById(UUID id);

}
