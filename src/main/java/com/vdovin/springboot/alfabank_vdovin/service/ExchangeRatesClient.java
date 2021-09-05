package com.vdovin.springboot.alfabank_vdovin.service;

import com.vdovin.springboot.alfabank_vdovin.dto.ExchangeRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeRatesClient", url = "${ExchangeRates.url}")
public interface ExchangeRatesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json")
    ResponseEntity<ExchangeRatesDTO> getHistoricalRates(@PathVariable("date") String date, @RequestParam("app_id") String app_id, @RequestParam("base") String base);

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json")
    ResponseEntity<ExchangeRatesDTO> getLatestRates(@RequestParam("app_id") String app_id, @RequestParam("base") String base);

}
