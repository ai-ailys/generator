package ru.currency_pair.generator.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.currency_pair.generator.config.CurrencyPairConfig;
import ru.currency_pair.generator.mapper.CurrencyPairMapper;
import ru.currency_pair.generator.model.CurrencyPair;

import java.util.*;

@Service
@Getter
public class GeneratorService {

    @Autowired
    CurrencyPairConfig config;
    @Autowired
    CurrencyPairMapper mapper;

    private final Map<String, List<CurrencyPair>> allPairs = new HashMap<>();

    @Scheduled(fixedDelayString = "PT010S")
    public void hundredValues(){

        int index = 0;
        List<CurrencyPair> pairs;

        while(index < config.getPairs().size()){

            allPairs.computeIfAbsent(config.getPairs().get(index).getName(), k -> new LinkedList<>());

            pairs = allPairs.get(config.getPairs().get(index).getName());

            CurrencyPair pair = mapper.toCurrencyPair(config, index);

            if (pairs.size() >= 100) {
                pairs.remove(0);
            }
            pairs.add(pair);
            allPairs.put(config.getPairs().get(index).getName(), pairs);
            index++;
        }
    }

}