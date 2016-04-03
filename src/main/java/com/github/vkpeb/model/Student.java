package com.github.vkpeb.model;

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

}
