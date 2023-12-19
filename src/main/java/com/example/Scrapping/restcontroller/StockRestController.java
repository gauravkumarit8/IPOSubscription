package com.example.Scrapping.restcontroller;

import com.example.Scrapping.entity.StockData;
import com.example.Scrapping.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class StockRestController {
    private StockService stockService;

    @Autowired
    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockData>> getAllStock(){
        return new ResponseEntity<>(this.stockService.getStockData(), HttpStatus.OK);
    }
}
