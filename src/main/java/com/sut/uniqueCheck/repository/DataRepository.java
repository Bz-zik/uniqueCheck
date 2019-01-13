package com.sut.uniqueCheck.repository;

import com.sut.uniqueCheck.dto.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends CrudRepository<Data, Integer> {
    List<Data> findAll();
}
