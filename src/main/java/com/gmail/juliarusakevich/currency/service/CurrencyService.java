package com.gmail.juliarusakevich.currency.service;

import com.gmail.juliarusakevich.currency.dao.CurrencyDao;
import com.gmail.juliarusakevich.currency.dao.exception.DAOException;
import com.gmail.juliarusakevich.currency.service.api.ICreationService;
import com.gmail.juliarusakevich.currency.service.api.IDeleteService;
import com.gmail.juliarusakevich.currency.service.api.IReturnService;
import com.gmail.juliarusakevich.currency.service.api.IUpdateService;
import com.gmail.juliarusakevich.currency.service.dto.currency.CreateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.dto.currency.ReadCurrencyDto;
import com.gmail.juliarusakevich.currency.service.dto.currency.UpdateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.exception.ServiceException;
import com.gmail.juliarusakevich.currency.service.mapper.CreateMapper;
import com.gmail.juliarusakevich.currency.service.mapper.ReadCurrencyDtoToCurrencyMapper;
import com.gmail.juliarusakevich.currency.service.mapper.ReadMapper;
import com.gmail.juliarusakevich.currency.service.mapper.UpdateMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CurrencyService implements
        ICreationService<CreateCurrencyDto>,
        IReturnService<ReadCurrencyDto, Integer>,
        IDeleteService<Integer>,
        IUpdateService<UpdateCurrencyDto> {

    private static final CurrencyService INSTANCE = new CurrencyService();
    private final CurrencyDao dao = CurrencyDao.getInstance();
    private final CreateMapper createMapper = CreateMapper.getInstance();
    private final ReadMapper readMapper = ReadMapper.getInstance();
    private final UpdateMapper updateMapper = UpdateMapper.getInstance();
    private final ReadCurrencyDtoToCurrencyMapper toCurrencyMapper = ReadCurrencyDtoToCurrencyMapper.getInstance();

    private CurrencyService() {
    }


    @Override
    public void save(CreateCurrencyDto dto) throws ServiceException {

        var entity = createMapper.mapFrom(dto);
        try {
            dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to create record", e);
        }
    }

    @Override
    public List<ReadCurrencyDto> findAll() throws ServiceException {
        try {
            return dao.read().stream()
                    .map(readMapper::mapFrom)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException("Unable to return list.", e);
        }
    }

    @Override
    public Optional<ReadCurrencyDto> findById(Integer id) throws ServiceException {
        try {
            var entity = dao.readById(id).get();
            var dto = readMapper.mapFrom(entity);
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
    public void update(UpdateCurrencyDto dto) throws ServiceException {
        var oldDto = findById(dto.getId()).get();
        var oldEntity = toCurrencyMapper.mapFrom(oldDto);
        var newEntity = updateMapper.mapFrom(dto);
        try {
            dao.update(oldEntity, newEntity);
        } catch (DAOException e) {
            throw new ServiceException("Unable to update record", e);
        }
    }

    public static CurrencyService getInstance() {
        return INSTANCE;
    }


}
