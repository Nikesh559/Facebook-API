package com.facebook.api.controller;

import com.facebook.api.dto.MemberDTO;
import com.facebook.api.service.MemberService;
import com.facebook.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {


    @Autowired
    MemberService memberService;

    @Autowired
    SearchService searchService;

    @GetMapping(value = "/users")
    public List<MemberDTO> searchMember(@RequestParam(value = "name", defaultValue = "") String name
            , @RequestParam(value = "start", defaultValue = "-1") int start,
            @RequestParam(value = "size", defaultValue = "-1") int size) {
        return searchService.searchMember(name, start, size);
    }


}
