package fr.snowsdy.controleaccesfx.services;

import fr.snowsdy.controleaccesfx.entities.Admin;
import fr.snowsdy.controleaccesfx.entities.User;
import fr.snowsdy.controleaccesfx.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        Optional<User> optionalUser = repository.findByName(user.getName());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Name already taken.");
        }
        repository.save(user);
    }

    @Override
    public void delete(User user) {
        Optional<User> optionalUser = repository.findByName(user.getName());
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("Name already taken.");
        }
        repository.delete(user);
    }

    @Override
    public void saveAll(Iterable<User> values) {
        repository.saveAll(values);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<Admin> findByPassword(String password) {
        String encoded = Admin.codeMD5(password);
        return repository.findByPassword(encoded);
    }
}
