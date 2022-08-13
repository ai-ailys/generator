package ru.currency_pair.generator.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurrencyPair {

    private String name;
    private Double value;
    private String time;

}
