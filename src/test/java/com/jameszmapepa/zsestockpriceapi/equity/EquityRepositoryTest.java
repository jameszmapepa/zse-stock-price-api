package com.jameszmapepa.zsestockpriceapi.equity;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.jameszmapepa.zsestockpriceapi.common.enums.EquityStatus;
import com.jameszmapepa.zsestockpriceapi.model.Equity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({DBUnitExtension.class, SpringExtension.class})
class EquityRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EquityRepository equityRepository;

    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet("equities.json")
    void shouldFindAllEquities() {
        final List<Equity> equities = equityRepository.findAll();
        Assertions.assertNotNull(equities);
        Assertions.assertEquals(4, equities.size());
    }

    @Test
    @DataSet("equities.json")
    void shouldFindEquityById() {
        final Optional<Equity> optionalEquity = equityRepository.findById(2L);
        Assertions.assertTrue(optionalEquity.isPresent());

        final Equity equity = optionalEquity.get();
        Assertions.assertEquals("Border Timbers Limited", equity.getName());
        Assertions.assertEquals("BRDR", equity.getSymbol());
        Assertions.assertEquals("Materials", equity.getSector());
        Assertions.assertEquals(EquityStatus.SUSPENDED, equity.getEquityStatus());
        Assertions.assertEquals(LocalDate.parse("1945-01-01"), equity.getFoundedYear());
        Assertions.assertEquals(LocalDate.parse("1959-01-01"), equity.getListedYear());
    }

    @Test
    @DataSet("equities.json")
    void shouldNotReturnEquityWhenIdNotFound() {
        final Optional<Equity> optionalEquity = equityRepository.findById(10L);

        Assertions.assertFalse(optionalEquity.isPresent());
    }

    @Test
    @DataSet("equities.json")
    void shouldFindEquityByEquityStatus() {
        final List<Equity> suspendedEquities = equityRepository.findByEquityStatusEquals(EquityStatus.SUSPENDED);
        Assertions.assertNotNull(suspendedEquities);
        Assertions.assertEquals(1, suspendedEquities.size());

        final List<Equity> activeEquities = equityRepository.findByEquityStatusEquals(EquityStatus.ACTIVE);
        Assertions.assertNotNull(activeEquities);
        Assertions.assertEquals(3, activeEquities.size());
    }

    @Test
    @DataSet("equities.json")
    void shouldSaveEquity() {
        final Equity newEquity = new Equity();
        newEquity.setId(5L);
        newEquity.setName("Econet Wireless Zimbabwe Limited");
        newEquity.setSector("ICT");
        newEquity.setSymbol("ECO");
        newEquity.setEquityStatus(EquityStatus.ACTIVE);
        newEquity.setFoundedYear(LocalDate.parse("1998-01-01"));
        newEquity.setListedYear(LocalDate.parse("1998-01-01"));

        equityRepository.save(newEquity);

        final Optional<Equity> optionalEquity = equityRepository.findById(5L);
        Assertions.assertTrue(optionalEquity.isPresent());

        final Equity equity = optionalEquity.get();
        Assertions.assertEquals(newEquity, equity);

    }


}
