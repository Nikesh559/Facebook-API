package com.facebook.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "work")
public class Work extends RepresentationModel<Work> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long workId;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "location")
    private String location;

    @Column(name= "profileId")
    private String profileId;


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Work{" +
                "profileId='" + workId + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return workId;
    }

    public void setId(Long id) {
        this.workId = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}
