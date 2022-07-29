package com.facebook.api.service;

import com.facebook.api.model.Profile;
import com.facebook.api.model.Work;
import com.facebook.api.repository.ProfileRepository;
import com.facebook.api.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    WorkRepository workRepository;

    public boolean userExists(String profileId) {
        return profileRepository.existsById(profileId);
    }

    public List<Work> getAllWorkOfProfile(String profileId) {
        return workRepository.getAllWorkOfProfile(profileId);
    }

    public Profile getProfileById(String profileId) {
        return profileRepository.findById(profileId).get();
    }

    public byte[] getProfilePicture(String profileId) {
        return profileRepository.getProfilePicture(profileId);
    }

    public byte[] getProfileCoverPhoto(String profileId) {
        return profileRepository.getProfileCoverPhoto(profileId);
    }
}
