package com.gmail.juliarusakevich.currency.service.api;

import com.gmail.juliarusakevich.currency.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IReturnService<T, K> {

    List<T> findAll() throws ServiceException;

    Optional<T> findById(K id) throws ServiceException;
}
