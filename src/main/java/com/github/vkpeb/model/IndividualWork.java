package com.github.vkpeb.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "individual_work")
public class IndividualWork {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "work_id", length = 6, nullable = false)
    private long id;

    @Column(name = "work_date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.ALL})
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "student_work_descr")
    private String studentWorkDescr;

    @Column(name = "parent_work_descr")
    private String parentWorkDescr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentWorkDescr() {
        return studentWorkDescr;
    }

    public void setStudentWorkDescr(String studentWorkDescr) {
        this.studentWorkDescr = studentWorkDescr;
    }

    public String getParentWorkDescr() {
        return parentWorkDescr;
    }

    public void setParentWorkDescr(String parentWorkDescr) {
        this.parentWorkDescr = parentWorkDescr;
    }
}
