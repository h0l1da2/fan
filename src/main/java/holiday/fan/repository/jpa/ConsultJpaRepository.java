package holiday.fan.repository.jpa;

import holiday.fan.domain.consulting.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultJpaRepository extends JpaRepository<Consult, Long> {
}
