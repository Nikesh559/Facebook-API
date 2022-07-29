package com.facebook.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class Education extends RepresentationModel<Education> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id;

    @Column(name = "school")
    private String school;

    @Column(name = "degree")
    private String degree;

    @Column(name = "fromYear")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private String fromYear;

    @Column(name = "toYear")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private String toYear;

    @Column(name = "profileId")
    private String profileId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }


    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Education{" +
                "profileId='" + id + '\'' +
                ", school='" + school + '\'' +
                ", degree='" + degree + '\'' +
                ", fromYear='" + fromYear + '\'' +
                ", toYear='" + toYear + '\'' +
                '}';
    }
}
