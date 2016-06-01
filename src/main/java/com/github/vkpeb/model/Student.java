package com.github.vkpeb.model;

import com.github.vkpeb.model.enumer.FamilyStatuses;
import com.github.vkpeb.model.enumer.LivingConditions;
import com.github.vkpeb.model.enumer.Posts;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "student_id", length = 6, nullable = false)
    private long id;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "auth_id", unique = true)
    private Auth auth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "student_family")
    private String family;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_otchestvo")
    private String otchestvo;

    @Column(name = "student_born")
    private String born;

    @Column(name = "student_phone")
    private String phone;

    @Column(name = "living_condition")
    @Enumerated(EnumType.STRING)
    private LivingConditions livingCondition;

    @Column(name = "family_status")
    @Enumerated(EnumType.STRING)
    private FamilyStatuses familyStatus;

    @Column(name = "post")
    @Enumerated(EnumType.STRING)
    private Posts post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LivingConditions getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(LivingConditions livingCondition) {
        this.livingCondition = livingCondition;
    }

    public FamilyStatuses getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatuses familyStatus) {
        this.familyStatus = familyStatus;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", family='" + family + '\'' +
                ", name='" + name + '\'' +
                ", otchestvo='" + otchestvo + '\'' +
                '}';
    }
}
