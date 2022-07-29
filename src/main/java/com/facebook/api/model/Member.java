package com.facebook.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Member")
public class Member extends Person implements Serializable {
    @Id
    private String profileId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId")
    private List<ConnectionRequest> invitations;

    @OneToMany
    @JoinColumn(name = "profileId")
    private List<Post> post;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<ConnectionRequest> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<ConnectionRequest> invitations) {
        this.invitations = invitations;
    }
}
