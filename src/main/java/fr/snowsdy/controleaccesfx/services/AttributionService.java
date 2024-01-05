package fr.snowsdy.controleaccesfx.services;

import fr.snowsdy.controleaccesfx.entities.Attribution;
import fr.snowsdy.controleaccesfx.repositories.AttributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        optionalAttr.ifPresent(attribution -> {
            if ((Objects.equals(attr.getUser().getName(), attribution.getUser().getName())) && (Objects.equals(attr.getAccessCard().getSerialNumber(), attribution.getAccessCard().getSerialNumber()))) {
                throw new IllegalStateException("This attribution is already granted by this user and this access card.");
            }
        });
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

    @Override
    public List<Attribution> findAll() {
        return repository.findAll();
    }
}
