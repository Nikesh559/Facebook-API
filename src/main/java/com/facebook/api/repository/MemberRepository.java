package com.facebook.api.repository;

import com.facebook.api.model.Member;
import com.facebook.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:name%")
    List<Member> getMemberByName(@Param("name") String name);

    @Query("SELECT count(1) FROM Member m WHERE m.apiKey = :apiKey")
    String containsKey(@Param("apiKey") String apiKey);

    @Modifying
    @Query("UPDATE Member m SET m.email = :email WHERE m.profileId = :profileId")
    void updateEmail(@Param("profileId") String profileId,@Param("email") String email);

    @Modifying
    @Query("UPDATE Member m SET m.phone = :phone WHERE m.profileId = :profileId")
    void updatePhone(@Param("profileId") String profileId,@Param("phone") String phone);
}
