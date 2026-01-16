package sulamerica.atendimentoLaboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sulamerica.atendimentoLaboratorio.model.Servicos;

/**
 * @author Priscila Davanse
 * @version 1.0
 */
@Repository
public interface ServicosRepository extends JpaRepository<Servicos, Long>{

}
