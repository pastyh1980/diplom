package com.github.vkpeb.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by pasty on 03.04.2016.
 */
@Entity
@Table(name = "meeting_members")
public class MeetingMember {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "meeting_member_id", length = 6, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meeting_id")
    private ParentMeetings parentMeetings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public long getId() {
        return id;
    }

    public ParentMeetings getParentMeetings() {
        return parentMeetings;
    }

    public void setParentMeetings(ParentMeetings parentMeetings) {
        this.parentMeetings = parentMeetings;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
