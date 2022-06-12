package com.gmail.juliarusakevich.currency.service.mapper;

import com.gmail.juliarusakevich.currency.dao.model.Currency;
import com.gmail.juliarusakevich.currency.service.dto.currency.CreateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.mapper.api.IMapper;

import java.time.LocalDateTime;

public class CreateMapper implements IMapper<CreateCurrencyDto, Currency> {

    private final static CreateMapper INSTANCE = new CreateMapper();

    private CreateMapper() {
    }

    @Override
    public Currency mapFrom(CreateCurrencyDto object) {
        return Currency.builder()
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .createDate(LocalDateTime.now())
                .build();
    }

    public static CreateMapper getInstance() {
        return INSTANCE;
    }
}
