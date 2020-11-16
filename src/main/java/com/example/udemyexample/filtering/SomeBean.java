package com.example.udemyexample.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;



//@JsonIgnoreProperties(value = {"filed11"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    @Getter
    @Setter
    private String filed1;

    @Getter
    @Setter
    private String filed11;

    @Getter
    @Setter
    private String filed12;

    public SomeBean(String filed1, String filed11, String filed12) {
        this.filed1 = filed1;
        this.filed11 = filed11;
        this.filed12 = filed12;
    }
}
