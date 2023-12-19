package com.example.Scrapping.repository;

import com.example.Scrapping.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepo extends JpaRepository<StockData,Long> {

    Optional<StockData> findByName(String name);
}
