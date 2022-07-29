package com.facebook.api.service;

import com.facebook.api.model.Education;
import com.facebook.api.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProfileService profileService;

    public List<Education> getProfileEducation(String profileId) {
        return educationRepository.getEducationByProfileId(profileId);
    }

    public Education getEducation(String profileId, String eduId) {
        return educationRepository.getEducation(profileId, eduId);
    }

    public boolean updateEducation(String profileId, String eduId, Education ed) {
        if(profileService.userExists(profileId)) {
            ed.setProfileId(profileId);
            return educationRepository.save(ed) != null;
        }
        return false;
    }
}
