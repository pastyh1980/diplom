package com.github.vkpeb.dao;

import com.github.vkpeb.model.ParentMeetings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasty on 01.06.2016.
 */
public interface ParentMeetingDao extends JpaRepository<ParentMeetings, Long> {
}
