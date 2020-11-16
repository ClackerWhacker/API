package com.example.udemyexample.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping(value = "/person/param", params = "version=1")
    public Person1 getPeople(){
        return new Person1("Harrison", "Boyns");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public Person2 getPeople2(){
        return new Person2(new Name("Harrison", "Boyns"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public Person2 getPersonHeader(){
        return new Person2(new Name("Harrison", "Boyns"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public Person2 getPersonHeader2(){
        return new Person2(new Name("Harrison", "Boyns"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public Person2 getPersonProduces1(){
        return new Person2(new Name("Harrison", "Boyns"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public Person2 getPersonProduces2(){
        return new Person2(new Name("Harrison", "Boyns"));
    }

    // The version you use depends on the company
}
