package ru.currency_pair.generator.mapper;

import org.springframework.stereotype.Component;
import ru.currency_pair.generator.config.CurrencyPairConfig;
import ru.currency_pair.generator.model.CurrencyPair;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
@Component
public class CurrencyPairMapper {

    public CurrencyPair toCurrencyPair(CurrencyPairConfig config, int index){

        CurrencyPairConfig.Pair pair = config.getPairs().get(index);
        Random random = new Random();
        Double eps = random.nextGaussian();

        return CurrencyPair.builder()
                .name(pair.getName())
                .value(pair.getValue() + pair.getValue()/ pair.getA() * eps)
                .time(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                .build();
    }
}
