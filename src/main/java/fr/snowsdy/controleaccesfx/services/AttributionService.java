package fr.snowsdy.controleaccesfx.services;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import fr.snowsdy.controleaccesfx.entities.Attribution;
import fr.snowsdy.controleaccesfx.entities.User;
import fr.snowsdy.controleaccesfx.repositories.AttributionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributionService implements IService<Attribution> {

    private final AttributionRepository repository;

    public AttributionService(AttributionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Attribution attr) {
        Optional<Attribution> optionalAttr = repository.findByAccessCard(attr.getAccessCard());
        if (optionalAttr.isPresent()) {
            throw new IllegalStateException("This attribution is already granted.");
        }
        repository.save(attr);
    }

    @Override
    public void delete(Attribution attr) {
        Optional<Attribution> optionalAttr = repository.findByAccessCard(attr.getAccessCard());
        if (optionalAttr.isEmpty()) {
            throw new IllegalStateException("The selected attribution doesn't exists.");
        }
        repository.delete(attr);
    }

    @Override
    public void saveAll(Iterable<Attribution> values) {
        repository.saveAll(values);
    }

    public Optional<Attribution> findByAccessCard(AccessCard accessCard) {
        return repository.findByAccessCard(accessCard);
    }

    public Optional<Attribution> findByUser(User user) {
        return repository.findByUser(user);
    }
}
