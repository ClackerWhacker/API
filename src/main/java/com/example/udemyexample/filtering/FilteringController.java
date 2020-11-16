package com.example.udemyexample.filtering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
public class FilteringController {

    @GetMapping(path = "/staticFiltering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1", "value2", "value3");
    }

    // The static filtering removes the responses for this on the fly
    // Can ignore then with JsonIgnore
    // or can use JsonIgnoreProperties

    @GetMapping(path = "/getBeans")
    public Collection<SomeBean> returnListOfBeans(){
        return Arrays.asList(
                new SomeBean("Hello", "World", "Harrison"),
                new SomeBean("Harrison", "World", "Hello"));
    }

    @GetMapping(path = "/dynamicFilteringBeans")
    public Collection<SomeBean> dynamicFilteringListOfBeans(){
        return Arrays.asList(
                new SomeBean("Hello", "World", "Harrison"),
                new SomeBean("Harrison", "World", "Hello"));
    }


    @GetMapping(path = "/dynamicFiltering")
    public MappingJacksonValue dynamicFiltering(){
        SomeBean bean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("filed1", "filed11");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
        mappingJacksonValue.setFilters(filters);
        System.out.println((((SomeBean)mappingJacksonValue.getValue()).getFiled12()));
        return mappingJacksonValue;
    }
}
