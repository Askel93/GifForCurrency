package com.vdovin.springboot.alfabank_vdovin.service;

import com.vdovin.springboot.alfabank_vdovin.dto.GifDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GifApiClientService{
    @Autowired
    private GifApiClient gifApiClient;

    @Value("${GifClient.api_key}")
    private String api_key;

    public ResponseEntity<GifDTO> getRandom(String tag){
        return gifApiClient.getRandom(api_key,tag);
    }
}