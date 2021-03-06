package com.gmail.juliarusakevich.currency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gmail.juliarusakevich.currency.service.CurrencyService;
import com.gmail.juliarusakevich.currency.service.dto.currency.CreateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.dto.currency.ReadCurrencyDto;
import com.gmail.juliarusakevich.currency.service.dto.currency.UpdateCurrencyDto;
import com.gmail.juliarusakevich.currency.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CurrencyServlet", urlPatterns = "/currency")
public class CurrencyServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CurrencyService service = CurrencyService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var dto = objectMapper.readValue(
                req.getInputStream(),
                CreateCurrencyDto.class);

        try {
            service.save(dto);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        List<ReadCurrencyDto> list = null;
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .registerModule(new JavaTimeModule());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModules(new JavaTimeModule());
        try {
            list = service.findAll();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        var jsonData = objectMapper.writeValueAsString(list);
        try (var writer = resp.getWriter()) {
            writer.write(jsonData);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            var dto = objectMapper.readValue(req.getInputStream(), ReadCurrencyDto.class);
            service.delete(dto.getId());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            try (var writer = resp.getWriter()) {
                writer.write(e.getMessage());
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var newDto = objectMapper.readValue(req.getInputStream(), UpdateCurrencyDto.class);

        try {
            service.update(newDto);

        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            try (var writer = resp.getWriter()) {
                writer.write(e.getMessage());
            }
        }
    }
}
/*
DOPOST
{
    "title": "324",
    "description": "???????????????????? ??????????",
    "code": "GNF"
}

DOGET

DODELETE

{
    "id": 7
}


 */
