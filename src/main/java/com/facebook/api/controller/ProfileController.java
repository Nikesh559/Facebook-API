package com.facebook.api.controller;

import com.facebook.api.dto.ContactDTO;
import com.facebook.api.dto.ProfileDTO;
import com.facebook.api.model.Member;
import com.facebook.api.model.Message;
import com.facebook.api.model.Profile;
import com.facebook.api.service.MemberService;
import com.facebook.api.service.ProfileService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    MemberService memberService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping("/profile/{profileId}")
    public ResponseEntity getProfile(@PathVariable("profileId") String profileId) {
        if (profileService.userExists(profileId)) {
            Profile user = profileService.getProfileById(profileId);
            Member member = memberService.getMember(profileId);
            Profile p = member.getProfile();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            ProfileDTO profileDTO = modelMapper.map(user, ProfileDTO.class);
            modelMapper.map(member, profileDTO);
            Link link = linkTo(methodOn(ProfileController.class).getProfile(profileId)).withSelfRel();
            Link picture = linkTo(methodOn(ProfileController.class).getProfilePicture(profileId)).withRel("profilePicture");
            Link coverPhoto = linkTo(methodOn(ProfileController.class).getProfileCoverPhoto(profileId)).withRel("coverPhoto");
            Link workExp = linkTo(methodOn(WorkController.class).getAllWorkById(profileId)).withRel("workExperiences");
            Link educations = linkTo(methodOn(EducationController.class).getEducationList(profileId)).withRel("educations");
            Link connectionRequest = linkTo(methodOn(ConnectionReqController.class).getRequests(profileId, null)).withRel("invitations");
            Link posts = linkTo(methodOn(PostController.class).getPost(profileId)).withRel("posts");
            profileDTO.add(picture);
            profileDTO.add(coverPhoto);
            profileDTO.add(workExp);
            profileDTO.add(educations);
            profileDTO.add(connectionRequest);
            profileDTO.add(posts);
            profileDTO.add(link);
            return new ResponseEntity(profileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/profile/{profileId}/profile_picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getProfilePicture(@PathVariable("profileId") String profileId) {
        return new ResponseEntity(profileService.getProfilePicture(profileId), HttpStatus.OK);
    }


    @GetMapping(value = "/profile/{profileId}/cover_photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getProfileCoverPhoto(@PathVariable("profileId") String profileId) {
        return new ResponseEntity(profileService.getProfileCoverPhoto(profileId), HttpStatus.OK);
    }


    @PatchMapping(value = "/profile/{profileId}/contact")
    public ResponseEntity updateContactInfo(@PathVariable("profileId") String profileId,
                                           @Valid @RequestBody ContactDTO contactDTO) {
        memberService.updateContactInfo(profileId, contactDTO);
        return new ResponseEntity(new Message(HttpStatus.OK, "Contact Info Updated."), HttpStatus.OK);
    }
}
