package com.example.Scrapping.service;
import com.example.Scrapping.entity.StockData;
import com.example.Scrapping.repository.StockRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    private StockRepo stockRepo;

    @Autowired
    public StockService(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

//    public List<StockData> getStockData(){
//        List<StockData> st1=new ArrayList<>();
//        String url="https://ipowatch.in/ipo-grey-market-premium-latest-ipo-gmp/";
//        try{
//            Document document= Jsoup.connect(url).get();
//            Elements stock=document.select(".wp-block-table");
//
//            for(Element st:stock){
//                String title=st.select("table>tbody> td >a").text();
//                String price= st.select("tbody> td>strong").text();
//
//                StockData stockData=new StockData();
//                stockData.setName(title);
//                stockData.setPrice(price);
//
//                st1.add(stockData);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return st1;
//    }
    public List<StockData> getStockData() {
        List<StockData> st1 = new ArrayList<>();
        String url = "https://ipowatch.in/ipo-grey-market-premium-latest-ipo-gmp/";

        try {
            Document document = Jsoup.connect(url).get();
            Elements stock = document.select(".elementor-widget-container .wp-block-table tbody tr");

            for (Element st : stock) {
                //String title = st.select("td:nth-child(1) a").text();
                String title=st.select("td:nth-child(1)").first().text();
                String type=st.select("td:nth-child(2)").text();
                String price = st.select("td:nth-child(4)").text();
                String gmpPreice=st.select("td:nth-child(3)").text();
                String gainPer=st.select("td:nth-child(5)").text();

                if(type.equals("Mainline")){
                    type="IPO";
                }

                StockData stockData = new StockData();
                stockData.setName(title);
                stockData.setType(type);
                stockData.setPrice(price);
                stockData.setGmpPremium(gmpPreice);
                stockData.setGainPer(gainPer);
                updateStockInDb(stockData);

                st1.add(stockData);
                if(st1.size()==12){
                    return st1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return st1;
    }

    private void updateStockInDb(StockData stockData) {
        StockData stock=this.stockRepo.findByName(stockData.getName()).orElse(null);
        if(stock==null){
            stockRepo.save(stockData);
        }else{
            stockData.setStockId(stock.getStockId());
            stockRepo.save(stockData);
        }
    }

}
