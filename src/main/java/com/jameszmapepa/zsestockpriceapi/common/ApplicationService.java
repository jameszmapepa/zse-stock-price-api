package com.jameszmapepa.zsestockpriceapi.common;

import java.util.List;
import java.util.Optional;

public interface ApplicationService<T> {

    Optional<T> get(Long id);

    List<T> findAll();

    T save(T t);


}
