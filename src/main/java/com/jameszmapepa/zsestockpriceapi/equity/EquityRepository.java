package com.jameszmapepa.zsestockpriceapi.equity;

import com.jameszmapepa.zsestockpriceapi.common.enums.EquityStatus;
import com.jameszmapepa.zsestockpriceapi.model.Equity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquityRepository extends JpaRepository<Equity, Long> {

    List<Equity> findByEquityStatusEquals(EquityStatus equityStatus);
}
