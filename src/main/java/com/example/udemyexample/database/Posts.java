package com.example.udemyexample.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter @Setter
    private String description;

    // Lazy get means it wont grab the user unless you ask for it
    @JsonIgnore
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Posts() {
    }

    public Posts(String description, User user) {
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", description='" + description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
