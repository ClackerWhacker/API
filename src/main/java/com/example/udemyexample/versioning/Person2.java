package com.example.udemyexample.versioning;

import lombok.Getter;
import lombok.Setter;

public class Person2 {

    @Getter
    @Setter
    private Name name;

    public Person2(Name name) {
        this.name = name;
    }
}


class Name{

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String secondName;

    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}