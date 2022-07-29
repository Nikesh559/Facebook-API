package com.facebook.api.controller;

import com.facebook.api.model.Message;
import com.facebook.api.model.Work;
import com.facebook.api.service.ProfileService;
import com.facebook.api.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkController {
    static private Logger logger = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    private ProfileService profileService;

    @Autowired
    private WorkService workService;


    @GetMapping(value = "/profile/{profileId}/works")
    public ResponseEntity getAllWorkById(@PathVariable("profileId") String profileId) {
        if(profileService.userExists(profileId)) {
            List<Work> list = profileService.getAllWorkOfProfile(profileId);

            for(Work work : list) {
                Link link = linkTo(methodOn(WorkController.class).getProfileWorkById(profileId, work.getId())).withSelfRel();
                work.add(link);
            }
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/profile/{profileId}/work")
    public ResponseEntity addWork(@PathVariable(name = "profileId") String profileId, @RequestBody Work work) {

        if(workService.addWork(profileId,work))
            return new ResponseEntity(new Message(HttpStatus.CREATED, "New work added to profile"), HttpStatus.CREATED);
        else
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);

    }

    @GetMapping(value="/profile/{profileId}/work/{workId}")
    public ResponseEntity getProfileWorkById(@PathVariable(name = "profileId") String profileId, @PathVariable(name = "workId")
    Long workId) {
        if(profileService.userExists(profileId)) {
            return new ResponseEntity(workService.getWorkById(workId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/profile/{profileId}/work/{workId}")
    public ResponseEntity updateWork(@PathVariable("profileId")String profileId, @PathVariable Long workId, @RequestBody Work work) {
        if(workService.save(profileId, workId, work)) {
            return new ResponseEntity(new Message(HttpStatus.OK, "Profile Updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new Message(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        }
    }


}
