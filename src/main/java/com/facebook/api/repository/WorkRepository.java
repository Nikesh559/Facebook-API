package com.facebook.api.repository;

import com.facebook.api.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("SELECT w FROM Work w WHERE w.profileId = :profileId")
    List<Work> getAllWorkOfProfile(@Param("profileId") String profileId);
}
