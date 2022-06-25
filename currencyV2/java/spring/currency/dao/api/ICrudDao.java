package spring.currency.dao.api;

import spring.currency.dao.exception.DAOException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICrudDao<E, K> {

    void create(E entity) throws DAOException;

    List<E> read() throws DAOException;

    Optional<E> readById(K id) throws DAOException;

    void update(K id, LocalDateTime dtUpdate, E entity) throws DAOException;

    void delete(K id) throws DAOException;
}
