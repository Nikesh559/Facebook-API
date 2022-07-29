package com.facebook.api.repository;

import com.facebook.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    @Query("SELECT p.profilePicture FROM Profile p WHERE p.profileId = :profileId")
    byte[] getProfilePicture(@Param("profileId") String profileId);

    @Query("SELECT p.coverPhoto FROM Profile p WHERE p.profileId = :profileId")
    byte[] getProfileCoverPhoto(@Param("profileId") String profileId);

}
