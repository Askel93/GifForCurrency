package com.vdovin.springboot.alfabank_vdovin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
public class CurrencyService {

    @Value("${GifClient.tag.downgrade}")
    private String tagDowngrade;
    @Value("${GifClient.tag.raise}")
    private String tagRaise;
    @Value("${ExchangeRates.ourCurrency}")
    private String firstCurrency;
    @Value("#{new Integer('${CurrencyService.dayOffset}')}")
    private int dayOffset;

    @Autowired
    private GifApiClientService gifApiClientService;
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private GifClientService gifClientService;

    private boolean rateRaised(String secondCurrency){
        if(firstCurrency.equals(secondCurrency))
            return false;

        LocalDate prevDate = LocalDate.now().minusDays(dayOffset);
        return getLatestRate(secondCurrency) - getHistoricalRate(prevDate, secondCurrency) > 0;
    }

    private Double getHistoricalRate(LocalDate date, String secondCurrency){
        return exchangeRatesService.getHistoricalRate(date,secondCurrency);
    }

    private Double getLatestRate(String secondCurrency){
        return exchangeRatesService.getLatestRate(secondCurrency);
    }

    public ResponseEntity<byte[]> getGifForCurrency(String currencyCode) throws IOException {
        boolean rateRaised = rateRaised(currencyCode);

        String tag = rateRaised?tagRaise:tagDowngrade;
        String gif_url = gifApiClientService.getRandom(tag).getBody().getData().getImage_url();

        return gifClientService.getGif(gif_url);
    }
}
