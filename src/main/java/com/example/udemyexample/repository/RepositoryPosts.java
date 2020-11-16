package com.example.udemyexample.repository;

import com.example.udemyexample.database.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPosts extends JpaRepository<Posts, Long> {

}
