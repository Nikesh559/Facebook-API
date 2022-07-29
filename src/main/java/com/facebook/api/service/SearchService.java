package com.facebook.api.service;

import com.facebook.api.dto.MemberDTO;
import com.facebook.api.dto.ProfileDTO;
import com.facebook.api.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class SearchService implements SearchRepository{

    @Autowired
    MemberService memberService;

    @Override
    public List<MemberDTO> searchMember(String name) {
        List<MemberDTO> memberDTOS = memberService.getMemberByName(name);
        return memberDTOS;
    }

    public List<MemberDTO> searchMember(String name, int start, int size) {
        List<MemberDTO> memberDTOS = null;

        if(name != null && !name.isEmpty())
            memberDTOS = memberService.getMemberByName(name);
        else
            memberDTOS = memberService.getMembers();
        if (start - 1 < memberDTOS.size() && start >= 1 && size > 0) {
            return memberDTOS.subList(start - 1, Math.min(memberDTOS.size(), start + size - 1));
        }
        return memberDTOS;
    }
}
