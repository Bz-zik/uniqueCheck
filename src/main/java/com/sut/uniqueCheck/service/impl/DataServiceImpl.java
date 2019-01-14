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

    @Override
    public void reset() {
        dataRepository.deleteAll();
        addFieldToTable("Если я чешу в затылке Не беда, В голове моей опилки Да, да, да.", 100);
        add("И хотя там и опилки, Но кричалки и дразнилки,", 2);
        add("А так-же сопелки, пыхтелки Сочиняю я неплохо иногда.", 2);
        add("Да! Хорошо живёт на свете Винни Пух, От того поёт он эти песни вслух.", 2);
        add("И не важно, чем он занят, Если он худеть не станет,", 2);
        add("Если конечно вовремя подкрепиться. Да! Трам-па-рам-па-рам-па-рам-па. Трам-пам-па.", 2);
        add("Трум-пу-рум-пу-трум-пу-рум-пу. Трам-пам-пам. Трам-па-рам-па-рам-па-рам-па.", 2);
        add("Мой дядя самых честных правил,", 2);
        add("Когда не в шутку занемог,", 2);
        add("Он уважать себя заставил,", 2);
        add("И лучше выдумать не мог,", 2);
        add("Его пример другим наука,", 2);
        add("Но, боже мой, какая скука,", 2);
        add("С больным сидеть и день и ночь,", 2);
        add("Не отходя ни шагу прочь!", 2);
        add("Какое низкое коварство", 2);
        add("Полуживого забавлять", 2);
        add("Ему подушки поправлять,", 2);
        add("Вздыхать и думать про себя:", 2);
        add("Когда же черт возьмет тебя!»", 2);
        add("Разум дан человеку для того, чтобы он разумно жил, а не для того только, чтобы он понимал, что он неразумно живет.", 2);
        add("Разум дан для того, чтобы он разумно жил, а не для того только, чтобы он понимал, что он неразумно живет", 2);
        add("мягкие французские булочки для тебя", 2);
        add("мягкие французские булочки для тебя", 2);
        add("мягкие французские уникальный текст тут", 2);
        add("мягкие французские уникальный текст", 2);
    }

    private double checkUniq(String text, int count, String oldText) {
        return new ShingleStrategy().getUniq(text, count, oldText);
    }


    private void addFieldToTable(String text, double uniq) {
        Data data = new Data();
        data.setText(text);
        data.setUniq(uniq);
        dataRepository.save(data);
        System.out.println("Was added new field to Data Base: " + text + "\t:\t" + uniq);
    }

}
