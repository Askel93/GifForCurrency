package com.vdovin.springboot.alfabank_vdovin.dto;

import java.util.Map;

public class ExchangeRatesDTO {
    private Map<String,Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public ExchangeRatesDTO() {
    }
}
