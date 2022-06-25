package spring.currency.service.mapper.api;

import spring.currency.dao.model.Currency;
import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.dto.currency.ReadCurrencyDto;
import spring.currency.service.dto.currency.UpdateCurrencyDto;

public interface IMapper {

    Currency createMapper(CreateCurrencyDto object);

    ReadCurrencyDto readMapper(Currency object);

    Currency updateMapper(UpdateCurrencyDto object);

    Currency readToCurrencyMapper(ReadCurrencyDto object);
}
