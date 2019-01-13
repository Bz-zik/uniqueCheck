package com.sut.uniqueCheck.service.impl;

import com.sut.uniqueCheck.dto.Data;
import com.sut.uniqueCheck.repository.DataRepository;
import com.sut.uniqueCheck.service.DataService;
import com.sut.uniqueCheck.strategy.ShingleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataServiceImpl implements DataService {

    public DataServiceImpl() {
    }

    @Autowired
    private DataRepository dataRepository;


    @Override
    public void add(String text, int count) {

        final Optional<Double> minUniq = dataRepository.findAll().stream()
                                                       .map(Data::getText)
                                                       .map(field -> checkUniq(text, count, field))
                                                       .sorted()
                                                       .findFirst();


        addFieldToTable(text, minUniq.isPresent() ? minUniq.get() : 100);
    }

    private double checkUniq(String text, int count, String oldText) {
        return new ShingleStrategy().getUniq(text, count, oldText);
    }

    private void addFieldToTable(String text, double uniq) {
        Data data = new Data();
        data.setText(text);
        data.setUniq(uniq);
        dataRepository.save(data);
    }

}
