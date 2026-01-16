package sulamerica.atendimentoLaboratorio.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sulamerica.atendimentoLaboratorio.model.Atendimentos;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@Repository
public interface AtendimentosRepository extends JpaRepository<Atendimentos, UUID> {

	public List<Atendimentos> findByPacienteId(UUID id);

}
