package com.jameszmapepa.zsestockpriceapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_price")
@EqualsAndHashCode(callSuper = true)
public class StockPrice extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "equity_id", referencedColumnName = "id")
    private Equity equity;
    @NonNull
    @Column(name = "date")
    private LocalDateTime date;
    @NonNull
    @Column(name = "opening_price", scale = 4)
    private BigDecimal openingPrice;
    @NonNull
    @Column(name = "closing_price", scale = 4)
    private BigDecimal closingPrice;
    @Column(name = "traded_volume")
    private long volumeTraded;
}
