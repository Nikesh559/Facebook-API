package com.facebook.api.repository;

import com.facebook.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.profileId = :profileId")
    List<Post> getPosts(@Param("profileId") String profileId);
}
