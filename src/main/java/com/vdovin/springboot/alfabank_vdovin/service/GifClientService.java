package com.vdovin.springboot.alfabank_vdovin.service;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class GifClientService {
    public GifClientService() {
    }

    public ResponseEntity<byte[]> getGif(String url) throws IOException {

        Document document = Jsoup.connect(url).get();
        String gifURL = document.select("meta[property=og:url]").get(0).attr("content");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_GIF);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        InputStream in = new UrlResource(gifURL).getInputStream();

        byte[] media = IOUtils.toByteArray(in);

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

}
