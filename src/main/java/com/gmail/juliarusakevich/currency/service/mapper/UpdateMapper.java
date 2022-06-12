package com.gmail.juliarusakevich.currency.service.mapper;

import com.gmail.juliarusakevich.currency.dao.model.Currency;
import com.gmail.juliarusakevich.currency.service.dto.currency.UpdateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.mapper.api.IMapper;

import java.time.LocalDateTime;

public class UpdateMapper implements IMapper<UpdateCurrencyDto, Currency> {

    private static final UpdateMapper INSTANCE = new UpdateMapper();

    private UpdateMapper() {
    }

    public static UpdateMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Currency mapFrom(UpdateCurrencyDto object) {
        return Currency.builder()
                .id(object.getId())
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .updateDate(LocalDateTime.now())
                .build();
    }


}
