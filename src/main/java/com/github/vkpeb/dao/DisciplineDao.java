package com.github.vkpeb.dao;

import com.github.vkpeb.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pasty on 17.05.2016.
 */
public interface DisciplineDao extends JpaRepository<Discipline, Long> {

    @Query("select d from Discipline d where d.disciplineName=:name")
    Discipline getDisciplineByName(@Param("name") String name);

}
