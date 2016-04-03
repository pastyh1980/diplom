package com.github.vkpeb.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "class_meetings")
public class ClassMeeting {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "class_meeting_id", length = 6, nullable = false)
    private long id;

    @Column(name = "meeting_date")
    private Date date;

    @Column(name = "meeting_theme")
    private String theme;

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
