package spring.currency.service.api;

import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.dto.currency.ReadCurrencyDto;
import spring.currency.service.dto.currency.UpdateCurrencyDto;


public interface ICrudService extends
        ICreateService<CreateCurrencyDto>,
        IReadService<ReadCurrencyDto, Integer>,
        IDeleteService<Integer>,
        IUpdateService<Integer, UpdateCurrencyDto> {
}
