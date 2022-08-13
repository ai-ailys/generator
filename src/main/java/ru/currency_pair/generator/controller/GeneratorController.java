package ru.currency_pair.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.currency_pair.generator.service.GeneratorService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/currency_pairs")
public class GeneratorController {
    @Autowired
    GeneratorService service;

    @GetMapping
    public List getListOfCurrencyPair(@RequestParam String name){
        return service.getAllPairs().get(name);
    }

}
