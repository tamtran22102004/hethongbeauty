package ut.edu.project_skincarebooking.repositories;

import ut.edu.project_skincarebooking.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
