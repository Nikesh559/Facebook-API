package com.facebook.api.repository;

import com.facebook.api.dto.MemberDTO;
import com.facebook.api.dto.ProfileDTO;

import java.util.List;

public interface SearchRepository {
    List<MemberDTO> searchMember(String name);
}
