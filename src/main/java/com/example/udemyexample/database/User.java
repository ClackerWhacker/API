package com.example.udemyexample.database;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter @Setter
    @NotNull
    private String name;

    @Column(nullable = false)
    @Getter @Setter
    @Min(value = 1, message = "Age should not be less than 18")
    private int age;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    public User() {
    }

    @JsonCreator
    public User(@NotNull String name, @NotNull int age) {
        this.name = name;
        this.age = age;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }



}
