package fr.snowsdy.controleaccesfx.services;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import fr.snowsdy.controleaccesfx.repositories.AccessCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessCardService implements IService<AccessCard> {

    private final AccessCardRepository repository;

    public AccessCardService(AccessCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AccessCard accessCard) {
        Optional<AccessCard> cardOptional = repository
                .findBySerialNumber(accessCard.getSerialNumber());
        if (cardOptional.isPresent()) {
            throw new IllegalStateException("Serial Number taken.");
        }
        this.repository.save(accessCard);
    }

    @Override
    public void delete(AccessCard accessCard) {
        Optional<AccessCard> cardOptional = repository
                .findBySerialNumber(accessCard.getSerialNumber());
        if (cardOptional.isEmpty()) {
            throw new IllegalStateException("This Access Card doesn't exists.");
        }
        this.repository.delete(accessCard);
    }

    @Override
    public void saveAll(Iterable<AccessCard> values) {
        repository.saveAll(values);
    }
}
