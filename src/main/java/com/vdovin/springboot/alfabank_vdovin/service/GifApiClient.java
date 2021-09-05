package com.vdovin.springboot.alfabank_vdovin.service;

import com.vdovin.springboot.alfabank_vdovin.dto.GifDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gifClient", url = "${GifClient.url}")
public interface GifApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/random")
    ResponseEntity<GifDTO> getRandom(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);
}