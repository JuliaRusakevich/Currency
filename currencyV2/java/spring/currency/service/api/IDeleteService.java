package spring.currency.service.api;

import spring.currency.service.exception.ServiceException;

public interface IDeleteService<D> {

    void delete(D id) throws ServiceException;
}
