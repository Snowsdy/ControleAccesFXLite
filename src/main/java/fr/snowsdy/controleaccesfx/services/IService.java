package fr.snowsdy.controleaccesfx.services;

public interface IService<T> {
    void save(T val);

    void delete(T val);

    void saveAll(Iterable<T> values);
}
