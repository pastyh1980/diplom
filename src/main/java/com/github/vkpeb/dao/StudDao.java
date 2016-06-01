package com.github.vkpeb.dao;

import com.github.vkpeb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pasty on 27.04.2016.
 */
public interface StudDao extends JpaRepository<Student, Long> {
}
