package spring.currency.service;

import org.springframework.stereotype.Service;
import spring.currency.dao.CurrencyDao;
import spring.currency.dao.exception.DAOException;
import spring.currency.dao.model.Currency;
import spring.currency.service.api.ICrudService;
import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.dto.currency.ReadCurrencyDto;
import spring.currency.service.dto.currency.UpdateCurrencyDto;
import spring.currency.service.exception.ServiceException;
import spring.currency.service.mapper.MapperService;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class CurrencyService implements ICrudService {

    private final CurrencyDao dao;
    private final MapperService mapper;

    public CurrencyService(CurrencyDao dao,
                           MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public CreateCurrencyDto save(CreateCurrencyDto dto) throws ServiceException {
        var entity = mapper.createMapper(dto);
        try {
            dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to create record", e);
        }
        return dto;
    }

    @Override
    public List<ReadCurrencyDto> findAll() throws ServiceException {
        try {
            return dao.read().stream()
                    .map(mapper::readMapper)
                    .sorted(comparing(ReadCurrencyDto::getId))
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException("Unable to return list.", e);
        }
    }

    @Override
    public Optional<ReadCurrencyDto> findById(Integer id) throws ServiceException {
        try {
            var entity = dao.readById(id).get();
            var dto = mapper.readMapper(entity);
            return Optional.ofNullable(dto);
        } catch (DAOException e) {
            throw new ServiceException("Unable to return record", e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Unable to delete.", e);
        }
    }

    @Override
    public void update(Integer id, LocalDateTime dtUpdate, UpdateCurrencyDto dto) throws ServiceException {
        var oldDto = findById(id).get();
        //var oldEntity = mapper.readToCurrencyMapper(oldDto);
        var newEntity = mapper.updateMapper(dto);
        try {
            dao.update(id, dtUpdate, newEntity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to update record", e);
        }
    }
}

/*
    @Override
    public void update(UpdateCurrencyDto dto) throws ServiceException {
        var oldDto = findById(dto.getId()).get();
        var oldEntity = mapper.readToCurrencyMapper(oldDto);
        var newEntity = mapper.updateMapper(dto);
        try {
            dao.update(oldEntity, newEntity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to update record", e);
        }
    }

        @Override
    public void update(Integer id, UpdateCurrencyDto dto) throws ServiceException {
        var oldDto = findById(id).get();
        var oldEntity = mapper.readToCurrencyMapper(oldDto);
        var newEntity = mapper.updateMapper(dto);
        try {
            dao.update(oldEntity, newEntity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to update record", e);
        }
    }
}
 */