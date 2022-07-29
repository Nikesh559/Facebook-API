package com.facebook.api.controller;

import com.facebook.api.model.Education;
import com.facebook.api.model.Message;
import com.facebook.api.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EducationController {
    @Autowired
    private EducationService educationService;

    @GetMapping("/profile/{profileId}/education")
    public List<Education> getEducationList(@PathVariable("profileId") String profileId) {
        List<Education> list = educationService.getProfileEducation(profileId);

        for(Education education : list) {
            Link link = linkTo(methodOn(EducationController.class).getEducationOfProfile(profileId, education.getId())).withSelfRel();
            education.add(link);
        }
        return list;
    }

    @GetMapping("/profile/{profileId}/education/{eduId}")
    public Education getEducationOfProfile(@PathVariable("profileId") String profileId, @PathVariable("eduId") String eduId) {
        return educationService.getEducation(profileId, eduId);
    }

    @PutMapping("/profile/{profileId}/education/{eduId}")
    public ResponseEntity updateEducation(@PathVariable("profileId") String profileId, @PathVariable("eduId") String eduId, @RequestBody Education ed) {
        if(educationService.updateEducation(profileId, eduId, ed)) {
            return new ResponseEntity(new Message(HttpStatus.OK, "Profile Updated"), HttpStatus.OK);
        }else {
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        }
    }
}
