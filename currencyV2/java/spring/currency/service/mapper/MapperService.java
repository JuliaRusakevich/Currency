package spring.currency.service.mapper;

import org.springframework.stereotype.Service;
import spring.currency.dao.model.Currency;
import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.dto.currency.ReadCurrencyDto;
import spring.currency.service.dto.currency.UpdateCurrencyDto;
import spring.currency.service.mapper.api.IMapper;

import java.time.LocalDateTime;

@Service
public class MapperService implements IMapper {

    @Override
    public Currency createMapper(CreateCurrencyDto object) {
        return Currency.builder()
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .createDate(LocalDateTime.now())
                .build();
    }

    @Override
    public ReadCurrencyDto readMapper(Currency object) {
        return ReadCurrencyDto.builder()
                .id(object.getId())
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .createDate(object.getCreateDate())
                .updateDate(object.getUpdateDate())
                .build();
    }

    @Override
    public Currency updateMapper(UpdateCurrencyDto object) {
        return Currency.builder()
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
               // .updateDate(LocalDateTime.now()) @Version  занимает hibernate этим полем
                .build();
    }

    @Override
    public Currency readToCurrencyMapper(ReadCurrencyDto object) {
        return Currency.builder()
                .id(object.getId())
                .title(object.getTitle())
                .description(object.getDescription())
                .code(object.getCode())
                .updateDate(object.getUpdateDate())
                .build();
    }
}
