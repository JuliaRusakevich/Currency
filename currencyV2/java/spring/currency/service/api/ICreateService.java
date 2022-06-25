package spring.currency.service.api;

import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.exception.ServiceException;

public interface ICreateService<C> {

    CreateCurrencyDto save(C dto) throws ServiceException;
}
