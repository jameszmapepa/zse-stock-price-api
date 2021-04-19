package com.jameszmapepa.zsestockpriceapi.stockprice;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.jameszmapepa.zsestockpriceapi.model.StockPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({DBUnitExtension.class, SpringExtension.class})
class StockPriceRepositoryTest {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private StockPriceRepository stockPriceRepository;

    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet(value = "stockPrices.json", disableConstraints = true)
    void shouldFindAllStockPrices() {
        final List<StockPrice> stockPrices = stockPriceRepository.findAll();
        Assertions.assertNotNull(stockPrices);
        Assertions.assertEquals(7, stockPrices.size());
    }

}