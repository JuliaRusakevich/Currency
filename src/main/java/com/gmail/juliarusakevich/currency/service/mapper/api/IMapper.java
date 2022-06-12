package com.gmail.juliarusakevich.currency.service.mapper.api;

public interface IMapper<F, T> {

    T mapFrom(F object);
}
