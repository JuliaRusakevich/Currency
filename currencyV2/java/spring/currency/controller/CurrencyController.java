package spring.currency.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import spring.currency.service.api.ICrudService;
import spring.currency.service.dto.currency.CreateCurrencyDto;
import spring.currency.service.dto.currency.ReadCurrencyDto;
import spring.currency.service.dto.currency.UpdateCurrencyDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyController {

    private final ICrudService service;

    public CurrencyController(ICrudService service) {
        this.service = service;
    }


    @SneakyThrows
    @RequestMapping(
            value = "/currency",
            method = RequestMethod.GET)
    public List<ReadCurrencyDto> findList() {
        return service.findAll();
    }

    /*
    ???
    {
    "empty": false,
    "present": true
}
     */
    @SneakyThrows
    @RequestMapping(
            value = "/currency/{id}",
            method = RequestMethod.GET)
    public Optional<ReadCurrencyDto> findById(@PathVariable Integer id) {
        return this.service.findById(id);

    }

    /*
    +
    {
    "title": "324",
    "description": "Гвинейский франк",
    "code": "GNF"
}
     */
    @SneakyThrows
    @RequestMapping(
            value = "/currency",
            method = RequestMethod.POST)
    public CreateCurrencyDto add(@RequestBody CreateCurrencyDto dto) {
        return service.save(dto);
    }


    @SneakyThrows
    @RequestMapping(value = "/currency/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Integer id) {
        this.service.delete(id);
    }


    @SneakyThrows
    @RequestMapping(value = "/currency/{id}/version/{version}", method = RequestMethod.PUT)
    public void refreshDate(@PathVariable Integer id,
                            @PathVariable Long version,
                            @RequestBody UpdateCurrencyDto dto) {
        var lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(version), ZoneId.systemDefault());
        this.service.update(id, lastKnowDtUpdate, dto);
    }


}
