package com.sut.uniqueCheck.repository;

import com.sut.uniqueCheck.dto.Data;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRepository extends CrudRepository<Data, Integer> {
    List<Data> findByText(String text);
}
