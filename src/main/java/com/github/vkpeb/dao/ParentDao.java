package com.github.vkpeb.dao;

import com.github.vkpeb.model.Parent;
import com.github.vkpeb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 02.05.2016.
 */
public interface ParentDao extends JpaRepository<Parent, Long> {

    @Query("select p from Parent p where p.student = :student order by p.family")
    List<Parent> getParentsByStudents(@Param("student")Student student);
}
