package com.facebook.api.repository;

import com.facebook.api.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, String> {

    @Query("SELECT e FROM Education e WHERE e.profileId = :profileId")
    List<Education> getEducationByProfileId(@Param("profileId") String profileId);

    @Query("SELECT e FROM Education e WHERE e.profileId = :profileId AND id = :eduId")
    Education getEducation(@Param("profileId") String profileId, @Param("eduId") String eduId);
}
