package com.facebook.api.service;

import com.facebook.api.model.Profile;
import com.facebook.api.model.Work;
import com.facebook.api.repository.WorkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WorkService{
    private static Logger logger = LoggerFactory.getLogger(WorkService.class);

    @Autowired
    WorkRepository workRepository;

    @Autowired
    ProfileService profileService;

    public boolean addWork(String profileId, Work work) {
        try {
            if(profileService.userExists(profileId)) {
                work.setProfileId(profileId);
                workRepository.save(work);
            }
            else
                return false;
        }
        catch(Exception ex) {
            logger.warn("Work cannot be created ", ex);
            return false;
        }
        return true;
    }


    public Work getWorkById(Long workId) {
        return workRepository.findById(workId).get();
    }


    public boolean save(String profileId, Long Id, Work wk) {
        if(profileService.userExists(profileId)) {
            wk.setProfileId(profileId);
            return workRepository.save(wk) != null;
        }
        return false;
    }
}
