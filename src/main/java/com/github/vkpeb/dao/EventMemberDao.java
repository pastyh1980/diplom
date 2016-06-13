package com.github.vkpeb.dao;

import com.github.vkpeb.model.Event;
import com.github.vkpeb.model.EventMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 12.06.2016.
 */
public interface EventMemberDao extends JpaRepository<EventMember, Long> {

    @Query("select m from EventMember m where m.event=:event")
    List<EventMember> getEventMembers(@Param("event")Event event);
}
