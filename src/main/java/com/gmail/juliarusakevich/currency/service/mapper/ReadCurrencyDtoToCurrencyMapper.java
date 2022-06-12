package com.gmail.juliarusakevich.currency.service.mapper;

import com.gmail.juliarusakevich.currency.dao.model.Currency;
import com.gmail.juliarusakevich.currency.service.dto.currency.ReadCurrencyDto;
import com.gmail.juliarusakevich.currency.service.mapper.api.IMapper;

public class ReadCurrencyDtoToCurrencyMapper implements IMapper<ReadCurrencyDto, Currency> {

    private static final ReadCurrencyDtoToCurrencyMapper INSTANCE = new ReadCurrencyDtoToCurrencyMapper();

    private ReadCurrencyDtoToCurrencyMapper() {
    }

    @Override
    public Currency mapFrom(ReadCurrencyDto object) {
        return Currency.builder()
                .id(object.getId())
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .updateDate(object.getUpdateDate())
                .build();
    }

    public static ReadCurrencyDtoToCurrencyMapper getInstance() {
        return INSTANCE;
    }
}
