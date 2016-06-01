package com.github.vkpeb.dao;

import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.VisitingCard;
import com.github.vkpeb.model.enumer.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 29.05.2016.
 */
public interface VisitingCardDao extends JpaRepository<VisitingCard, Long> {

    @Query("select v from VisitingCard v where v.student=:student and month(v.date)=:month and year(v.date)=:year")
    List<VisitingCard> getStudsVisiting(@Param("student") Student student, @Param("month") Integer month, @Param("year") Integer year);

    @Query("select distinct (str(month(v.date)) || '.' || str(year(v.date))) from VisitingCard v")
    List<String> getStudsCards();
}
