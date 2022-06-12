package com.gmail.juliarusakevich.currency.dao.api;

import com.gmail.juliarusakevich.currency.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface ICrudDao<E, K> {

    void create(E entity) throws DAOException;

    List<E> read() throws DAOException;

    Optional<E> readById(K id) throws DAOException;

    void update(E oldEntity, E newEntity) throws DAOException;

   void delete(K id) throws DAOException;
}
