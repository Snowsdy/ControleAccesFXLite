package fr.snowsdy.controleaccesfx.repositories;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import fr.snowsdy.controleaccesfx.entities.Attribution;
import fr.snowsdy.controleaccesfx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributionRepository extends JpaRepository<Attribution, Long> {

    @Query("SELECT attr FROM Attribution attr WHERE attr.accessCard = ?1")
    Optional<Attribution> findByAccessCard(AccessCard accessCard);

    @Query("SELECT attr FROM Attribution attr WHERE attr.user = ?1")
    Optional<Attribution> findByUser(User user);
}
