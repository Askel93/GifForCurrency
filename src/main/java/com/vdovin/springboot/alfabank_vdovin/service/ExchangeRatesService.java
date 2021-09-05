package com.vdovin.springboot.alfabank_vdovin.service;

import com.vdovin.springboot.alfabank_vdovin.dto.ExchangeRatesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ExchangeRatesService {
    @Autowired
    private ExchangeRatesClient exchangeRatesClient;

    @Value("${ExchangeRates.api_key}")
    private String app_id;
    @Value("${ExchangeRates.patternOfDate}")
    private String patternOfDate;
    @Value("${ExchangeRates.baseCurrency}")
    private String base;

    public String getPatternOfDate() {
        return patternOfDate;
    }

    @Value("${ExchangeRates.ourCurrency}")
    private String firstCurrency;

    public Double getHistoricalRate(LocalDate date, String currency){
        Map<String,Double> rates = getHistoricalRates(date.format(DateTimeFormatter.ofPattern(patternOfDate))).getBody().getRates();
        return getRateFromMap(rates, currency);
    }

    public Double getLatestRate(String currency){
        Map<String,Double> rates = getLatestRates().getBody().getRates();
        return getRateFromMap(rates, currency);
    }
    private Double getRateFromMap(Map<String,Double> rates, String currency){
        return rates.get(firstCurrency)/(currency.equals(base)?1:rates.get(currency));
    }

    public ResponseEntity<ExchangeRatesDTO> getHistoricalRates(String date){
        return exchangeRatesClient.getHistoricalRates(date,app_id,base);
    }
    public ResponseEntity<ExchangeRatesDTO> getLatestRates(){
        return exchangeRatesClient.getLatestRates(app_id,base);
    }

}
