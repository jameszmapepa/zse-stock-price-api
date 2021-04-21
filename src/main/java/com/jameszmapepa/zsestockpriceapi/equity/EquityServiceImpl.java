package com.jameszmapepa.zsestockpriceapi.equity;

import com.jameszmapepa.zsestockpriceapi.model.Equity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquityServiceImpl implements EquityService {

    private final EquityRepository equityRepository;

    @Override
    public Optional<Equity> get(Long id) {
        return equityRepository.findById(id);
    }

    @Override
    public List<Equity> findAll() {
        return equityRepository.findAll();
    }

    @Override
    public Equity save(Equity equity) {
        return equityRepository.save(equity);
    }
}
