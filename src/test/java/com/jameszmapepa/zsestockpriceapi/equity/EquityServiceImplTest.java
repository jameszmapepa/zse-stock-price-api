package com.jameszmapepa.zsestockpriceapi.equity;

import com.jameszmapepa.zsestockpriceapi.common.enums.EquityStatus;
import com.jameszmapepa.zsestockpriceapi.model.Equity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EquityServiceImplTest {

    @InjectMocks
    private EquityServiceImpl equityService;

    @Mock
    private EquityRepository equityRepository;


    @Test
    void shouldGetEquity() {
        final Equity mockEquity = new Equity("Scorpion Tech", "SCP", "ICT", EquityStatus.ACTIVE, LocalDate.now(), LocalDate.now());
        Mockito.doReturn(Optional.of(mockEquity)).when(equityRepository).findById(1L);

        final Optional<Equity> returnedEquity = equityService.get(1L);

        assertTrue(returnedEquity.isPresent(), "Equity not found");
        assertEquals(mockEquity, returnedEquity.get(), "Equities should be the same");
    }

    @Test
    void findAll() {
        final Equity mockEquity1 = new Equity("Microsoft", "MSFT", "ICT", EquityStatus.ACTIVE, LocalDate.now(), LocalDate.now());
        final Equity mockEquity2 = new Equity("Google", "GOOGL", "ICT", EquityStatus.ACTIVE, LocalDate.now(), LocalDate.now());
        final Equity mockEquity3 = new Equity("Amazon", "AMZ", "ICT", EquityStatus.ACTIVE, LocalDate.now(), LocalDate.now());

        Mockito.doReturn(Arrays.asList(mockEquity1, mockEquity2, mockEquity3)).when(equityRepository).findAll();

        List<Equity> returnedEquities = equityService.findAll();

        assertNotNull(returnedEquities);
        assertFalse(returnedEquities.isEmpty());
        assertEquals(3, returnedEquities.size());
    }

    @Test
    void shouldSaveEquity() {
        final Equity mockEquity = new Equity("Microsoft", "MSFT", "ICT", EquityStatus.ACTIVE, LocalDate.now(), LocalDate.now());
        Mockito.doReturn(mockEquity).when(equityRepository).save(any());

        final Equity returnedEquity = equityService.save(mockEquity);

        Assertions.assertNotNull(returnedEquity, "Saved equity not returned");
        Assertions.assertNotNull(returnedEquity.getName(), "Equity name cannot be null");
        Assertions.assertNotNull(returnedEquity.getSymbol(), "Equity symbol cannot be null");
        Assertions.assertEquals(mockEquity.getEquityStatus(), returnedEquity.getEquityStatus(), "Saved and returned equity don't match");
    }
}