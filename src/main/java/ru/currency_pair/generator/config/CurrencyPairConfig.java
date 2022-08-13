package ru.currency_pair.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "application")
@Data
@NoArgsConstructor
public class CurrencyPairConfig {

    List<Pair> pairs;
    @Data
    @NoArgsConstructor
    public static class Pair{
        private String name;
        private Double value;
        private Integer a;
    }

}
