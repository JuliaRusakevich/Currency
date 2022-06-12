package com.gmail.juliarusakevich.currency.service.mapper;

import com.gmail.juliarusakevich.currency.dao.model.Currency;
import com.gmail.juliarusakevich.currency.service.dto.currency.ReadCurrencyDto;
import com.gmail.juliarusakevich.currency.service.mapper.api.IMapper;

public class ReadMapper implements IMapper<Currency, ReadCurrencyDto> {

    private static final ReadMapper INSTANCE = new ReadMapper();

    private ReadMapper() {
    }

    @Override
    public ReadCurrencyDto mapFrom(Currency object) {
        return ReadCurrencyDto.builder()
                .id(object.getId())
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .createDate(object.getCreateDate())
                .updateDate(object.getUpdateDate())
                .build();
    }

    public static ReadMapper getInstance() {
        return INSTANCE;
    }
}
