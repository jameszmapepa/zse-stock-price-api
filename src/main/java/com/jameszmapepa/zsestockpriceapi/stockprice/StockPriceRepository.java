package com.jameszmapepa.zsestockpriceapi.stockprice;

import com.jameszmapepa.zsestockpriceapi.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
}
