package ru.currency_pair.generator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.currency_pair.generator.config.CurrencyPairConfig;
import ru.currency_pair.generator.mapper.CurrencyPairMapper;
import ru.currency_pair.generator.model.CurrencyPair;
import ru.currency_pair.generator.service.GeneratorService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
class GeneratorApplicationTests {

    @Autowired
    CurrencyPairConfig currencyPairConfig;

    @Autowired
    CurrencyPairMapper currencyPairMapper;

    @Test
    void testYAMLConfig(){

        Assertions.assertEquals("EUR/USD", currencyPairConfig.getPairs().get(0).getName());
        Assertions.assertEquals(1.0241, currencyPairConfig.getPairs().get(0).getValue());
        Assertions.assertEquals(2, currencyPairConfig.getPairs().size());
        Assertions.assertEquals(CurrencyPairConfig.Pair.class, currencyPairConfig.getPairs().get(0).getClass());

    }
    @Test
    void testMapper(){

        CurrencyPair currencyPair = currencyPairMapper.toCurrencyPair(currencyPairConfig, 0);
        Assertions.assertEquals("EUR/USD", currencyPair.getName());
        Assertions.assertEquals(Double.class, currencyPair.getValue().getClass());
        Assertions.assertEquals(String.class, currencyPair.getTime().getClass());

    }

    @Autowired
    GeneratorService generatorService;

    @Test
    void testService(){

        generatorService.hundredValues();
        Assertions.assertNotNull(generatorService.getAllPairs());

    }


    @Test
    void testControllerWithMockMvc(@Autowired MockMvc mvc) throws Exception {

        String name = "USD/RUB";
        mvc.perform(get("/api/v1/currency_pairs")).andExpect(status().isBadRequest());
        mvc.perform(get("/api/v1/currency_pairs?name=" + name)).andExpect(status().isOk());
        //mvc.perform(get("/api/v1/currency_pairs?name=USD/RUB")).andExpect(content().json(""));

    }

}
