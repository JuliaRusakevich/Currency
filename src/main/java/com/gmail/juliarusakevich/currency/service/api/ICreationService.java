package com.gmail.juliarusakevich.currency.service.api;

import com.gmail.juliarusakevich.currency.service.exception.ServiceException;

public interface ICreationService<T> {

    void save(T dto) throws ServiceException;
}
