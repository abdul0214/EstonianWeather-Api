//package com.scalablecapital.exchangerateservice.currency;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.scalablecapital.exchangerateservice.ExchangeRateServiceApplication;
//import com.scalablecapital.exchangerateservice.currency.application.dto.CurrencyCustomDTO;
//import com.scalablecapital.exchangerateservice.currency.application.service.ScheduledExchangeRateUpdateService;
//import com.scalablecapital.exchangerateservice.currency.rest.CurrencyRestController;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ExchangeRateServiceApplication.class) // Check if the name of this class is correct or not
//@WebAppConfiguration
//public class CurrencyRestControllerTest {
//
//    @Autowired
//    ObjectMapper mapper;
//    @Autowired
//    CurrencyRestController currencyRestController;
//    @Autowired
//    ScheduledExchangeRateUpdateService scheduler;
//    @Autowired
//    private WebApplicationContext wac;
//    private MockMvc mockMvc;
//    private List<CurrencyCustomDTO> currencies;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Before
//    public void runScheduler() throws Exception {
//        scheduler.updateExchangeRate();
//    }
//
//    @Test
//    public void getAllCurrenciesWithCountTest() throws Exception {
//
//        MvcResult result = mockMvc.perform(get("/api/exchangerateservice/currencies")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JsonNode responseJSON = mapper.readTree(result.getResponse().getContentAsString());
//        currencies = mapper.readValue(responseJSON.toString(), new TypeReference<List<CurrencyCustomDTO>>() {
//        });
//        Assertions.assertThat(currencies.size()).isGreaterThan(10);
//    }
//
//
//    @Test
//    public void getUSDExchangeRate() throws Exception {
//
//        MvcResult result = mockMvc.perform(get("/api/exchangerateservice/USD")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JsonNode responseJSON = mapper.readTree(result.getResponse().getContentAsString());
//        Double usdRate = mapper.readValue(responseJSON.toString(), new TypeReference<Double>() {
//        });
//        System.out.println(usdRate);
//        Assertions.assertThat(currencies.contains("USD"));
//    }
//
//}