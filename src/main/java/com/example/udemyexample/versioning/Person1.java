package com.example.udemyexample.versioning;

import lombok.Getter;
import lombok.Setter;

public class Person1 {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String secondname;

    public Person1() {
    }

    public Person1(String firstname, String secondname) {
        this.firstname = firstname;
        this.secondname = secondname;
    }


}
