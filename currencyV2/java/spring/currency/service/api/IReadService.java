package spring.currency.service.api;

import spring.currency.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IReadService<R, K> {

    List<R> findAll() throws ServiceException;

    Optional<R> findById(K id) throws ServiceException;
}
