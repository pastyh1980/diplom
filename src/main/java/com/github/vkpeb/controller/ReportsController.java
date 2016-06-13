package com.github.vkpeb.controller;

import com.github.vkpeb.model.*;
import com.github.vkpeb.model.enumer.*;
import com.github.vkpeb.model.form.FirstSemestrVisiting;
import com.github.vkpeb.model.form.ReportCardForm;
import com.github.vkpeb.model.form.SecondSemestrVisiting;
import com.github.vkpeb.service.CardsService;
import com.github.vkpeb.service.DisciplineService;
import com.github.vkpeb.service.StudService;
import com.github.vkpeb.util.ReportGeneratorUtil;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by pasty on 02.06.2016.
 */
@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private StudService studService;

    @Autowired
    private CardsService cardsService;

    @Autowired
    private DisciplineService disciplineService;

    @RequestMapping(value = "/socpassport", method = RequestMethod.GET)
    public ModelAndView generateSocialPassport(HttpServletResponse response) throws Docx4JException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=socpassport.docx");

        List<FamilyStatuses> familyStatusesList = Arrays.asList(FamilyStatuses.values());
        List<Student> studentList = studService.getAll();
        Map<FamilyStatuses, List<Student>> studentMap = new HashMap<>();
        Map<Long, List<Parent>> parentMap = new HashMap<>();

        Group group = studentList.get(0).getGroup();

        Integer fem_count = 0;
        Integer men_count = 0;
        Integer dormitory = 0;
        Integer apartment = 0;
        Integer rent = 0;
        Integer cottage = 0;
        Integer high_m = 0;
        Integer high_f = 0;
        Integer ss_m = 0;
        Integer ss_f = 0;
        Integer s_m = 0;
        Integer s_f = 0;
        Integer ns_m = 0;
        Integer ns_f = 0;

        for (FamilyStatuses status : familyStatusesList) {
            studentMap.put(status, new ArrayList<>());
        }

        for (Student student : studentList) {
            List<Parent> parents = studService.getParents(student);
            studentMap.get(student.getFamilyStatus()).add(student);
            parentMap.put(student.getId(), parents);

            if (student.getGender().equals(Gender.М)) ++men_count;
            else if (student.getGender().equals(Gender.Ж)) ++fem_count;

            if (student.getLivingCondition().equals(LivingConditions.DORMITORY)) ++dormitory;
            else if (student.getLivingCondition().equals(LivingConditions.APARTMENT)) ++apartment;
            else if (student.getLivingCondition().equals(LivingConditions.RENT)) ++rent;
            else if (student.getLivingCondition().equals(LivingConditions.COTTAGE)) ++cottage;

            for (Parent parent : parents) {
                if (parent.getParentType().equals(ParentType.MOTHER)) {
                    if (parent.getEducationType().equals(EducationType.HIGH)) ++high_m;
                    else if (parent.getEducationType().equals(EducationType.MIDDLESPEC)) ++ss_m;
                    else if (parent.getEducationType().equals(EducationType.MIDDLE)) ++s_m;
                    else if (parent.getEducationType().equals(EducationType.INCOMPLETEMIDDLE)) ++ns_m;
                } else if (parent.getParentType().equals(ParentType.FATHER)) {
                    if (parent.getEducationType().equals(EducationType.HIGH)) ++high_f;
                    else if (parent.getEducationType().equals(EducationType.MIDDLESPEC)) ++ss_f;
                    else if (parent.getEducationType().equals(EducationType.MIDDLE)) ++s_f;
                    else if (parent.getEducationType().equals(EducationType.INCOMPLETEMIDDLE)) ++ns_f;
                }
            }
        }

        Integer total_count = studentList.size();

        WordprocessingMLPackage outputFile = ReportGeneratorUtil.socialPassport(group, total_count, fem_count, men_count,
                dormitory, apartment, rent, cottage, high_m, high_f, ss_m, ss_f, s_m, s_f, ns_m, ns_f, studentMap, parentMap);

        outputFile.save(response.getOutputStream());

        return modelAndView;
    }

    @RequestMapping(value = "/firstsemestrvisiting", method = RequestMethod.GET, params = "year")
    public ModelAndView firstSemestrVisitingReport(HttpServletResponse response, @RequestParam("year") Integer year) throws Docx4JException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=first_semestr_visiting.docx");

        int number = 0;

        List<Student> studentList = studService.getAll();
        List<FirstSemestrVisiting> firstSemestrVisitingList = new ArrayList<>();
        FirstSemestrVisiting total = new FirstSemestrVisiting();

        for (Student student : studentList) {
            FirstSemestrVisiting visiting = new FirstSemestrVisiting();
            visiting.setNumber(++number);
            visiting.setStudFio(student.getFamily() + " " + student.getName() + " " + student.getOtchestvo());

            List<VisitingCard> septemberCards = cardsService.getStudsVisiting(student, 8, year);

            for (VisitingCard visitingCard : septemberCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setSeptemberValid(visiting.getSeptemberValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setSeptemberValid(total.getSeptemberValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setSeptemberInvalid(visiting.getSeptemberInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setSeptemberInvalid(total.getSeptemberInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> octoberCards = cardsService.getStudsVisiting(student, 9, year);

            for (VisitingCard visitingCard : octoberCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setOctoberValid(visiting.getOctoberValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setOctoberValid(total.getOctoberValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setOctoberInvalid(visiting.getOctoberInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setOctoberInvalid(total.getOctoberInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> novemberCards = cardsService.getStudsVisiting(student, 9, year);

            for (VisitingCard visitingCard : novemberCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setNovemberValid(visiting.getNovemberValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setNovemberValid(total.getNovemberValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setNovemberInvalid(visiting.getNovemberInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setNovemberInvalid(total.getNovemberInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> decemberCards = cardsService.getStudsVisiting(student, 9, year);

            for (VisitingCard visitingCard : decemberCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setDecemberValid(visiting.getDecemberValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setDecemberValid(total.getDecemberValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setDecemberInvalid(visiting.getDecemberInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setDecemberInvalid(total.getDecemberInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            firstSemestrVisitingList.add(visiting);
        }

        String group = studentList.get(0).getGroup().getGroupName();

        WordprocessingMLPackage outputFile = ReportGeneratorUtil.firstSemestrVisiting(firstSemestrVisitingList, total, group);

        outputFile.save(response.getOutputStream());

        return modelAndView;
    }

    @RequestMapping(value = "/secondsemestrvisiting", method = RequestMethod.GET, params = "year")
    public ModelAndView secondSemestrVisitingReport(HttpServletResponse response, @RequestParam("year") Integer year) throws Docx4JException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=second_semestr_visiting.docx");

        int number = 0;

        List<Student> studentList = studService.getAll();
        List<SecondSemestrVisiting> secondSemestrVisitings= new ArrayList<>();
        SecondSemestrVisiting total = new SecondSemestrVisiting();

        for (Student student : studentList) {
            SecondSemestrVisiting visiting = new SecondSemestrVisiting();
            visiting.setNumber(++number);
            visiting.setStudFio(student.getFamily() + " " + student.getName() + " " + student.getOtchestvo());

            List<VisitingCard> januaryCards = cardsService.getStudsVisiting(student, 0, year);

            for (VisitingCard visitingCard : januaryCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setJanuaryValid(visiting.getJanuaryValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setJanuaryValid(total.getJanuaryValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setJanuaryInvalid(visiting.getJanuaryInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setJanuaryInvalid(total.getJanuaryInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> februaryCards = cardsService.getStudsVisiting(student, 1, year);

            for (VisitingCard visitingCard : februaryCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setFebruaryValid(visiting.getFebruaryValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setFebruaryValid(total.getFebruaryValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setFebruaryInvalid(visiting.getFebruaryInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setFebruaryInvalid(total.getFebruaryInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> martCards = cardsService.getStudsVisiting(student, 2, year);

            for (VisitingCard visitingCard : martCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setMartValid(visiting.getMartValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setMartValid(total.getMartValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setMartInvalid(visiting.getMartInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setMartInvalid(total.getMartInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> aprilCards = cardsService.getStudsVisiting(student, 3, year);

            for (VisitingCard visitingCard : aprilCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setAprilValid(visiting.getAprilValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setAprilValid(total.getAprilValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setAprilInvalid(visiting.getAprilInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setAprilInvalid(total.getAprilInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            List<VisitingCard> mayCards = cardsService.getStudsVisiting(student, 4, year);

            for (VisitingCard visitingCard : mayCards) {
                if (visitingCard.getMissType().equals(MissType.VALID)) {
                    visiting.setMayValid(visiting.getMayValid() + visitingCard.getHourMiss());
                    visiting.setTotalValid(visiting.getTotalValid() + visitingCard.getHourMiss());
                    total.setMayValid(total.getMayValid() + visitingCard.getHourMiss());
                    total.setTotalValid(total.getTotalValid() + visitingCard.getHourMiss());
                } else if (visitingCard.getMissType().equals(MissType.INVALID)) {
                    visiting.setMayInvalid(visiting.getMayInvalid() + visitingCard.getHourMiss());
                    visiting.setTotalInvalid(visiting.getTotalInvalid() + visitingCard.getHourMiss());
                    total.setMayInvalid(total.getMayInvalid() + visitingCard.getHourMiss());
                    total.setTotalInvalid(total.getTotalInvalid() + visitingCard.getHourMiss());
                }
            }

            secondSemestrVisitings.add(visiting);
        }

        String group = studentList.get(0).getGroup().getGroupName();

        WordprocessingMLPackage outputFile = ReportGeneratorUtil.secondSemestrVisiting(secondSemestrVisitings, total, group);

        outputFile.save(response.getOutputStream());

        return modelAndView;
    }

    @RequestMapping(value = "/reportcard", method = RequestMethod.GET, params = {"month", "year"})
    public ModelAndView generateReportCard(HttpServletResponse response, @RequestParam("month") Integer month, @RequestParam("year") Integer year) throws Docx4JException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=report_card.docx");

        ReportCardForm reportCardForm = new ReportCardForm();
        reportCardForm.setRows(new HashMap<>());

        List<Discipline> disciplines = disciplineService.getAll();

        List<Student> students = studService.getAll();

        Map<Long, Integer> totalMiss = new HashMap<>();
        Map<Long, Integer> validMiss = new HashMap<>();
        Map<Long, Integer> invalidMiss = new HashMap<>();
        Integer t;
        Integer v;
        Integer i;

        for (Student student : students) {
            List<ReportCard> reportCardList = cardsService.getStudsRate(student, Month.values()[month], year);
            ReportCardForm.Row existRow = reportCardToRow(reportCardList);
            ReportCardForm.Row row = new ReportCardForm.Row();
            for (Discipline discipline : disciplines) {
                if (existRow.containsKey(discipline.getDisciplineName()))
                    row.put(discipline.getDisciplineName(), existRow.get(discipline.getDisciplineName()));
                else
                    row.put(discipline.getDisciplineName(), new ReportCardForm.Rate());

            }
            reportCardForm.getRows().put(student.getId(), row);

            t = cardsService.getTotalMiss(student, month, year);
            v = cardsService.getValidMiss(student, month, year);
            i = cardsService.getInvalidMiss(student, month, year);
            totalMiss.put(student.getId(), (t == null ? 0 : t));
            validMiss.put(student.getId(), (v == null ? 0 : v));
            invalidMiss.put(student.getId(), (i == null ? 0 : i));
        }

        WordprocessingMLPackage outputFile = ReportGeneratorUtil.reportCard(Month.values()[month], year, disciplines, students,
                reportCardForm, totalMiss, validMiss, invalidMiss);

        outputFile.save(response.getOutputStream());

        return modelAndView;
    }

    private ReportCardForm.Row reportCardToRow(List<ReportCard> reportCardList) {
        ReportCardForm.Row row = new ReportCardForm.Row();
        for (ReportCard reportCard : reportCardList) {
            ReportCardForm.Rate rate = new ReportCardForm.Rate();
            rate.setId(reportCard.getId());
            rate.setRate(reportCard.getRate());
            row.put(reportCard.getDiscipline().getDisciplineName(), rate);
        }
        return row;
    }
}
