package com.vdovin.springboot.alfabank_vdovin.controller;

import com.vdovin.springboot.alfabank_vdovin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
@RequestMapping()
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("/picture-for-currency")
    public ResponseEntity<?> getPicture(@RequestParam(name = "code") String code) throws IOException {

        return currencyService.getGifForCurrency(code);

    }


}
