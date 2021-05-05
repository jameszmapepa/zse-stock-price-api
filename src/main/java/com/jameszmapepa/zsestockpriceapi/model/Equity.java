package com.jameszmapepa.zsestockpriceapi.model;

import com.jameszmapepa.zsestockpriceapi.common.enums.EquityStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equity")
@EqualsAndHashCode(callSuper = true)
public class Equity extends BaseEntity {
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "sector")
    private String sector;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EquityStatus equityStatus;
    @Column(name = "year_founded")
    private LocalDate foundedYear;
    @Column(name = "year_listed")
    private LocalDate listedYear;
}
