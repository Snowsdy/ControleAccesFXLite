package fr.snowsdy.controleaccesfx.repositories;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessCardRepository extends JpaRepository<AccessCard, Long> {

    @Query("SELECT c FROM AccessCard c WHERE c.serialNumber = ?1")
    Optional<AccessCard> findBySerialNumber(String serialNumber);
}
