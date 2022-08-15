package ru.currency_pair.generator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.currency_pair.generator.GeneratorApplication;
import ru.currency_pair.generator.model.CurrencyPair;
import ru.currency_pair.generator.service.GeneratorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1/currency_pairs")
public class GeneratorController {

    static final Logger log = LoggerFactory.getLogger(GeneratorApplication.class);
    @Autowired
    GeneratorService service;

    @GetMapping
    public List<CurrencyPair> getListOfCurrencyPair(@RequestParam String name){
        log.debug("Method 'getListOfCurrencyPair' was called with name = " + name);
        List<CurrencyPair> listByName = service.getAllPairs().get(name);
        if (listByName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Name Not Found");
        }
        return listByName;
    }

}
