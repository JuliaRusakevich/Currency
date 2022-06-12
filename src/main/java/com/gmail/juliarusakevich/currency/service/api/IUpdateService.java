package com.gmail.juliarusakevich.currency.service.api;

import com.gmail.juliarusakevich.currency.service.exception.ServiceException;

public interface IUpdateService<U> {

    void update(U dto) throws ServiceException;
}
