package com.github.vkpeb.dao;

import com.github.vkpeb.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasty on 07.05.2016.
 */
public interface GroupDao extends JpaRepository<Group, Long> {
}
