package com.github.vkpeb.service;

import com.github.vkpeb.model.ReportCard;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.VisitingCard;
import com.github.vkpeb.model.enumer.Month;

import java.util.List;

/**
 * Created by pasty on 26.05.2016.
 */
public interface CardsService {

    List<ReportCard> getStudsRate(Student student, Month month, Integer year);
    ReportCard saveReportCard(ReportCard reportCard);
    List<String> getAddedCards();
    List<VisitingCard> getStudsVisiting(Student student, Integer month, Integer year);
    VisitingCard saveVisitingCard(VisitingCard visitingCard);
    List<String> getVisitingCards();
    Integer getTotalMiss(Student student, Integer month, Integer year);
    Integer getValidMiss(Student student, Integer month, Integer year);
    Integer getInvalidMiss(Student student, Integer month, Integer year);
}
