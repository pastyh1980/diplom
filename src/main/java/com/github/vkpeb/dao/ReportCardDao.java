package com.github.vkpeb.dao;

import com.github.vkpeb.model.ReportCard;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.enumer.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 26.05.2016.
 */
public interface ReportCardDao extends JpaRepository<ReportCard, Long> {

    @Query("select r from ReportCard r where r.student=:student and r.month=:month and r.year = :year")
    List<ReportCard> getStudsReportCard(@Param("student")Student student, @Param("month") Month month, @Param("year") Integer year);

    @Query("select distinct (str(r.month) || '.' || str(r.year)) from ReportCard r order by r.year, r.month")
    List<String> getAddedCards();
}
