package fr.snowsdy.controleaccesfx.services;

import java.util.List;

public interface IService<T> {
    void save(T val);

    void delete(T val);

    void saveAll(Iterable<T> values);

    List<T> findAll();
}
