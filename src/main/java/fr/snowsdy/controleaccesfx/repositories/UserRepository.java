package fr.snowsdy.controleaccesfx.repositories;

import fr.snowsdy.controleaccesfx.entities.Admin;
import fr.snowsdy.controleaccesfx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query("SELECT a FROM Admin a WHERE a.login = ?1")
    Optional<Admin> findByLogin(String login);

    @Query("SELECT a FROM Admin a WHERE a.password = ?1")
    Optional<Admin> findByPassword(String password);
}
