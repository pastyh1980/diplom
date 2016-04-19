package com.github.vkpeb.model;

import com.github.vkpeb.model.enumer.EducationType;
import com.github.vkpeb.model.enumer.ParentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "parents")
public class Parent {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "parent_id", length = 6, nullable = false)
    private long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ParentType parentType;

    @Column(name = "family")
    private String family;

    @Column(name = "name")
    private String name;

    @Column(name = "otchestvo")
    private String otchestvo;

    @Column(name = "phone")
    private String phone;

    @Column(name = "work")
    private String work;

    @Column(name = "education")
    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "auth_id", unique = true)
    private Auth auth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ParentType getParentType() {
        return parentType;
    }

    public void setParentType(ParentType parentType) {
        this.parentType = parentType;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
