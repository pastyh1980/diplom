package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.ReportCardDao;
import com.github.vkpeb.dao.VisitingCardDao;
import com.github.vkpeb.model.ReportCard;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.VisitingCard;
import com.github.vkpeb.model.enumer.Month;
import com.github.vkpeb.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pasty on 26.05.2016.
 */
@Service
public class CardsServiceImpl implements CardsService {

    @Autowired
    private ReportCardDao reportCardDao;

    @Autowired
    private VisitingCardDao visitingCardDao;

    @Override
    public List<ReportCard> getStudsRate(Student student, Month month, Integer year) {
        return reportCardDao.getStudsReportCard(student, month, year);
    }

    @Override
    public ReportCard saveReportCard(ReportCard reportCard) {
        return reportCardDao.saveAndFlush(reportCard);
    }

    @Override
    public List<String> getAddedCards() {
        return reportCardDao.getAddedCards();
    }

    @Override
    public List<VisitingCard> getStudsVisiting(Student student, Integer month, Integer year) {
        return visitingCardDao.getStudsVisiting(student, month + 1, year);
    }

    @Override
    public VisitingCard saveVisitingCard(VisitingCard visitingCard) {
        return visitingCardDao.saveAndFlush(visitingCard);
    }

    @Override
    public List<String> getVisitingCards() {
        return visitingCardDao.getStudsCards();
    }

    @Override
    public Integer getTotalMiss(Student student, Integer month, Integer year) {
        return visitingCardDao.getTotalMiss(student, month + 1, year);
    }

    @Override
    public Integer getValidMiss(Student student, Integer month, Integer year) {
        return visitingCardDao.getValidMiss(student, month + 1, year);
    }

    @Override
    public Integer getInvalidMiss(Student student, Integer month, Integer year) {
        return visitingCardDao.getInvalidMiss(student, month + 1, year);
    }
}
