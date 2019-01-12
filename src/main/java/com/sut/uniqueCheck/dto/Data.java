package com.sut.uniqueCheck.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;

    private Integer uniq;

    public Integer getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUniq(Integer uniq) {
        this.uniq = uniq;
    }
}
