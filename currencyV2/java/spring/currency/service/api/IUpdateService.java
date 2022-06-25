package spring.currency.service.api;

import spring.currency.service.exception.ServiceException;

import java.time.LocalDateTime;

public interface IUpdateService<K, U> {

    void update(K id, LocalDateTime dtUpdate, U dto) throws ServiceException;
}
