package com.example.Scrapping.controller;

import com.example.Scrapping.entity.StockData;
import com.example.Scrapping.service.StockService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    private String homepage(Model model){
        List<StockData> ipoName=stockService.getStockData();
        model.addAttribute("stocks",ipoName);
        return "home";
    }

    @GetMapping("/stockDetails/{stockId}")
    public String getIPODetails(@PathVariable("stockId") Long id, Model model){
        return "details";
    }
}
