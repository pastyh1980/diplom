package com.github.vkpeb.model;

import com.github.vkpeb.model.enumer.MissType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "visiting")
public class VisitingCard {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "visit_id", length = 6, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "visit_date")
    private Date date;

    @Column(name = "hour_miss")
    private int hourMiss;

    @Column(name = "type_miss")
    @Enumerated(EnumType.STRING)
    private MissType missType;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHourMiss() {
        return hourMiss;
    }

    public void setHourMiss(int hourMiss) {
        this.hourMiss = hourMiss;
    }

    public MissType getMissType() {
        return missType;
    }

    public void setMissType(MissType missType) {
        this.missType = missType;
    }
}
