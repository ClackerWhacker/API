package com.example.udemyexample.controller;


import com.example.udemyexample.database.Posts;
import com.example.udemyexample.errorhandling.CustomUserException;
import com.example.udemyexample.repository.RepositoryPosts;
import com.example.udemyexample.repository.RepositoryUser;
import com.example.udemyexample.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UdemyController {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryPosts repositoryPosts;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/ping")
    public ResponseEntity<String> pongInternational(){

        return new ResponseEntity<String>(messageSource.getMessage("good.morning.message",
                null, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }

    @GetMapping("/getusers")
    public Collection<User> getUsers() {
        return repositoryUser.findAll();
    }

    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable Long id) throws CustomUserException {
        User user = repositoryUser.findById(id).orElseThrow(() -> new CustomUserException("id-"+ id));
        Link link = linkTo(methodOn(UdemyController.class).getUsers()).withRel("allUsers");
        user.add(link);
        return user;
    }

    @DeleteMapping("/deleteuser/{id}")
    public User deleteUser(@PathVariable Long id) throws CustomUserException {
        User user = repositoryUser.findById(id).orElseThrow(() -> new CustomUserException("id-"));
        user.add(linkTo(methodOn(UdemyController.class).deleteUser(id)).withSelfRel());
        repositoryUser.deleteById(id);
        return user;
    }

    @PutMapping("user/{id}/replace")
    public User setValues(@PathVariable Long id, @Valid @RequestBody User user){
        return repositoryUser.findById(id)
                .map(
                        user1 -> {
                            user.setAge(user.getAge());
                            user.setName(user.getName());
                            user.add(linkTo(methodOn(UdemyController.class).setValues(id, user)).withSelfRel());
                            return repositoryUser.save(user1);
                        }
                ).orElseGet(() -> repositoryUser.save(user));
    }

    @PostMapping("/adduser")
    public User addUser(@Valid @RequestBody User user){
        user.add(linkTo(methodOn(UdemyController.class).addUser(user)).withSelfRel());
        return repositoryUser.save(user);
    }

    @GetMapping(path = "/help")
    public User apiList() throws CustomUserException {
        User user = new User("God", 99);
        user.add(linkTo(methodOn(UdemyController.class).addUser(user)).withSelfRel());
        user.add(linkTo(methodOn(UdemyController.class).setValues(1L, user)).withSelfRel());
        user.add(linkTo(methodOn(UdemyController.class).deleteUser(1L)).withSelfRel());
        user.add(linkTo(methodOn(UdemyController.class).getUser(1L)).withSelfRel());
        user.add(linkTo(methodOn(UdemyController.class).getUsers()).withSelfRel());
        user.add(linkTo(methodOn(UdemyController.class).pongInternational()).withSelfRel());

        return user;
    }

    @GetMapping(path = "/posts/{id}")
    public Collection<Posts> getPosts(@PathVariable Long id) throws CustomUserException {

        Optional<User> user = repositoryUser.findById(id);
        if(user.isEmpty()){
            throw new CustomUserException("id-");
        }


        return user.get().getPosts();
    }

    // returns a user bean
    @GetMapping("/ponguser")
    public ResponseEntity<User> pongUser(){
        return new ResponseEntity<User>(new User("Harrison", 26), HttpStatus.CREATED);
    }
}
