package com.facebook.api.model;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile{

    @Id
    @Column(name = "profile_id")
    private String profileId;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Lob
    @Column(name = "cover_photo")
    private byte[] coverPhoto;

    @Column(name = "status")
    private String status;

    @Transient
    private List<Work> workExperiences;

    @Transient
    private List<Education> educations;


    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public byte[] getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(byte[] coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Work> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<Work> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId='" + profileId + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", coverPhoto=" + Arrays.toString(coverPhoto) +
                ", status='" + status + '\'' +
                ", workExperiences=" + workExperiences +
                ", educations=" + educations +
                '}';
    }
}
