package com.github.vkpeb.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "disciplines")
public class Discipline {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "discipline_id", length = 6, nullable = false)
    private long id;

    @Column(name = "discipline_name")
    private String disciplineName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
