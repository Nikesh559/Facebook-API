package com.facebook.api.service;

import com.facebook.api.controller.ProfileController;
import com.facebook.api.dto.ContactDTO;
import com.facebook.api.dto.MemberDTO;
import com.facebook.api.model.Member;
import com.facebook.api.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProfileService profileService;

    public Member getMember(String profileId) {
        return memberRepository.findById(profileId).get();
    }

    public List<MemberDTO> getMemberByName(String name) {
        List<Member> members = memberRepository.getMemberByName(name);
        List<MemberDTO> memberDTOS = new ArrayList<>();

        return convertMemberToDTO(members);
    }

    public List<MemberDTO> getMembers() {
        return convertMemberToDTO(memberRepository.findAll());
    }

    private List<MemberDTO> convertMemberToDTO(List<Member> members) {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        for(Member member : members) {
            MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
            Link profileLink = linkTo(methodOn(ProfileController.class).getProfilePicture(member.getProfileId())).withRel("profilePicture");
            Link link = linkTo(methodOn(ProfileController.class).getProfile(memberDTO.getProfileId())).withRel("profile");
            memberDTO.add(link);
            memberDTO.add(profileLink);
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }

    public boolean containsKey(String apiKey) {
        return Integer.parseInt(memberRepository.containsKey(apiKey)) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateContactInfo(String profileId, ContactDTO contactDTO) {
        if(contactDTO.getEmail() != null)
            memberRepository.updateEmail(profileId, contactDTO.getEmail());
        if(contactDTO.getPhone() != null)
            memberRepository.updatePhone(profileId, contactDTO.getPhone());
    }
}
