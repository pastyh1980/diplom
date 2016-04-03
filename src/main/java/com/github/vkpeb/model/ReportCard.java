package com.github.vkpeb.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "report_card")
public class ReportCard {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "report_card_id", length = 6, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Column(name = "mounth")
    private Date date;

    @Column(name = "rate")
    private String rate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
