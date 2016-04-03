package com.github.vkpeb.model;

import com.github.vkpeb.model.enumer.EventLevel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "event_id", length = 6, nullable = false)
    private long id;

    @Column(name = "event_date")
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private EventLevel eventLevel;

    @Column(name = "result")
    private String result;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventLevel getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(EventLevel eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
