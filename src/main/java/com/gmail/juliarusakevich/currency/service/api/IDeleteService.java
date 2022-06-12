package com.gmail.juliarusakevich.currency.service.api;

import com.gmail.juliarusakevich.currency.service.exception.ServiceException;

public interface IDeleteService<K> {

    void delete(K id) throws ServiceException;
}
