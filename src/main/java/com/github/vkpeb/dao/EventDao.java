package com.github.vkpeb.dao;

import com.github.vkpeb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasty on 12.06.2016.
 */
public interface EventDao extends JpaRepository<Event, Long> {
}
