package com.github.vkpeb.controller;

import com.github.vkpeb.model.Discipline;
import com.github.vkpeb.model.ReportCard;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.VisitingCard;
import com.github.vkpeb.model.enumer.MissType;
import com.github.vkpeb.model.enumer.Month;
import com.github.vkpeb.model.form.NewCardForm;
import com.github.vkpeb.model.form.ReportCardForm;
import com.github.vkpeb.model.form.VisitingCardForm;
import com.github.vkpeb.service.CardsService;
import com.github.vkpeb.service.DisciplineService;
import com.github.vkpeb.service.StudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pasty on 27.05.2016.
 */
@Controller
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @Autowired
    private StudService studService;

    @Autowired
    private DisciplineService disciplineService;

    private Map<Long, Student> cacheStudentMap = new HashMap<>();

    private Map<String, Discipline> cacheDisciplineMap = new HashMap<>();

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView reportCardList() {
        ModelAndView response = new ModelAndView();

        List<String> cards = cardsService.getAddedCards();
        Map<String, String[]> cardMap = new HashMap<>();

        for (String card : cards) {
            String[] cardsArray = card.split("\\.");
            Month month = Month.values()[Integer.parseInt(cardsArray[0])];
            cardMap.put(month.name().concat(" ").concat(cardsArray[1]), cardsArray);
        }


        List<String> visitingCards = cardsService.getVisitingCards();
        Map<String, String[]> visitingCardMap = new HashMap<>();

        for (String card : visitingCards) {
            String[] cardsArray = card.split("\\.");
            Month month = Month.values()[Integer.parseInt(cardsArray[0]) - 1];
            visitingCardMap.put(month.name().concat(" ").concat(cardsArray[1]), cardsArray);
        }

        response.addObject("cardMap", cardMap);
        response.addObject("visitingCardMap", visitingCardMap);
        response.addObject("newReportCardForm", new NewCardForm());
        updateModel(response);
        response.setViewName("reportcardlist");
        return response;
    }

    @RequestMapping(value = "/newreportcard", method = RequestMethod.POST)
    public ModelAndView addReportCard(@ModelAttribute("newReportCardForm") NewCardForm newReportCardForm) {
        ModelAndView response = new ModelAndView();

        List<String> cards = cardsService.getAddedCards();
        String monthYear = String.valueOf(newReportCardForm.getMonth().ordinal()).concat(String.valueOf(newReportCardForm.getYear()));

        if (cards.contains(monthYear)) {
            response.addObject("cards", cards);
            response.addObject("msg", "Уже существует");
            updateModel(response);
            response.setViewName("reportcardlist");
            return response;
        }

        response.setViewName("redirect:/cards/newreportcard?month=" + newReportCardForm.getMonth().ordinal() + "&year=" + newReportCardForm.getYear());
        return response;
    }

    @RequestMapping(value = "/newvisitingcard", method = RequestMethod.POST)
    public ModelAndView addVisitingCard(@ModelAttribute("newReportCardForm") NewCardForm newReportCardForm) {
        ModelAndView response = new ModelAndView();

        List<String> cards = cardsService.getVisitingCards();
        String monthYear = String.valueOf(newReportCardForm.getMonth().ordinal()).concat(String.valueOf(newReportCardForm.getYear()));

        if (cards.contains(monthYear)) {
            response.addObject("cards", cards);
            response.addObject("msg", "Уже существует");
            updateModel(response);
            response.setViewName("reportcardlist");
            return response;
        }

        response.setViewName("redirect:/cards/newvisitingcard?month=" + newReportCardForm.getMonth().ordinal() + "&year=" + newReportCardForm.getYear());
        return response;
    }


    @RequestMapping(value = "/newreportcard", method = RequestMethod.GET, params = {"month", "year"})
    public ModelAndView newReportCard(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        ModelAndView response = new ModelAndView();

        ReportCardForm reportCardForm = new ReportCardForm();
        reportCardForm.setMonth(Month.values()[month]);
        reportCardForm.setYear(year);
        Map<Long, ReportCardForm.Row> studentMap = new HashMap<>();

        List<Discipline> disciplines = disciplineService.getAll();

        List<Student> students = studService.getAll();

        for (Student student : students) {
            ReportCardForm.Row disciplineStringMap = new ReportCardForm.Row();
            for (Discipline discipline : disciplines) {
                cacheDisciplineMap.put(discipline.getDisciplineName(), discipline);
                disciplineStringMap.put(discipline.getDisciplineName(), new ReportCardForm.Rate());
            }
            cacheStudentMap.put(student.getId(), student);
            studentMap.put(student.getId(), disciplineStringMap);
        }

        reportCardForm.setRows(studentMap);

        response.addObject("reportCardForm", reportCardForm);
        response.addObject("disciplines", disciplines);
        response.addObject("students", students);
        updateModel(response);
        response.setViewName("reportcard");
        return response;
    }

    @RequestMapping(value = "/editreportcard", method = RequestMethod.GET, params = {"month", "year"})
    public ModelAndView editReportCard(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        ModelAndView response = new ModelAndView();

        ReportCardForm reportCardForm = new ReportCardForm();
        reportCardForm.setMonth(Month.values()[month]);
        reportCardForm.setYear(year);
        reportCardForm.setRows(new HashMap<>());

        List<Discipline> disciplines = disciplineService.getAll();

        List<Student> students = studService.getAll();

        for (Student student : students) {
            List<ReportCard> reportCardList = cardsService.getStudsRate(student, Month.values()[month], year);
            ReportCardForm.Row existRow = reportCardToRow(reportCardList);
            ReportCardForm.Row row = new ReportCardForm.Row();
            for (Discipline discipline : disciplines) {
                cacheDisciplineMap.put(discipline.getDisciplineName(), discipline);
                if (existRow.containsKey(discipline.getDisciplineName()))
                    row.put(discipline.getDisciplineName(), existRow.get(discipline.getDisciplineName()));
                else
                    row.put(discipline.getDisciplineName(), new ReportCardForm.Rate());

            }
            cacheStudentMap.put(student.getId(), student);
            reportCardForm.getRows().put(student.getId(), row);
        }

        response.addObject("reportCardForm", reportCardForm);
        response.addObject("disciplines", disciplines);
        response.addObject("students", students);
        updateModel(response);
        response.setViewName("reportcard");
        return response;
    }

    @RequestMapping(value = "/savereportcard", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveReportCard(@ModelAttribute("reportCardForm") ReportCardForm reportCardForm) {
        ModelAndView response = new ModelAndView();

        Map<Long, ReportCardForm.Row> studentRowMap = reportCardForm.getRows();
        Month month = reportCardForm.getMonth();
        Integer year = reportCardForm.getYear();

        for (Map.Entry<Long, ReportCardForm.Row> studentRowEntry : studentRowMap.entrySet()) {
            Student student = cacheStudentMap.get(studentRowEntry.getKey());
            for (Map.Entry<String, ReportCardForm.Rate> disciplineRateEntry : studentRowEntry.getValue().entrySet()) {
                ReportCard reportCard = new ReportCard();
                Discipline discipline = cacheDisciplineMap.get(disciplineRateEntry.getKey());
                ReportCardForm.Rate rate = disciplineRateEntry.getValue();
                reportCard.setId(rate.getId());
                reportCard.setMonth(month);
                reportCard.setYear(year);
                reportCard.setRate(rate.getRate());
                reportCard.setDiscipline(discipline);
                reportCard.setStudent(student);
                cardsService.saveReportCard(reportCard);
            }
        }

        response.setViewName("redirect:/cards/");
        return response;
    }

    @RequestMapping(value = "/newvisitingcard", method = RequestMethod.GET, params = {"month", "year"})
    public ModelAndView newVisitingCard(@RequestParam("month") Integer month, @RequestParam Integer year) {
        ModelAndView response = new ModelAndView();

        VisitingCardForm visitingCardForm = new VisitingCardForm();
        visitingCardForm.setMonth(Month.values()[month]);
        visitingCardForm.setYear(year);

        List<Calendar> calendarList = new ArrayList<>();
        Calendar calendar = new GregorianCalendar(year, month, 1);
        calendarList.add(calendar);
        Calendar nextDay = (Calendar) calendar.clone();
        nextDay.add(Calendar.DAY_OF_MONTH, 1);

        while (month.equals(nextDay.get(Calendar.MONTH))) {
            calendarList.add(nextDay);
            nextDay = (Calendar) nextDay.clone();
            nextDay.add(Calendar.DAY_OF_MONTH, 1);
        }

        List<String> dateStrList = new ArrayList<>();

        for (Calendar cal : calendarList) {
            dateStrList.add(new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime()));
        }

        List<String> dateStrList1 = new ArrayList<>();
        for (int i = 0 ; dateStrList.size() > i && i < 11 ; ++i) {
            dateStrList1.add(dateStrList.get(i));
        }

        List<String> dateStrList2 = new ArrayList<>();
        for (int i = 11 ; dateStrList.size() > i && i < 22 ; ++i) {
            dateStrList2.add(dateStrList.get(i));
        }

        List<String> dateStrList3 = new ArrayList<>();
        for (int i = 22 ; dateStrList.size() > i && i < 31 ; ++i) {
            dateStrList3.add(dateStrList.get(i));
        }

        List<Student> studentList = studService.getAll();

        for (Student student : studentList) {
            VisitingCardForm.Row row = new VisitingCardForm.Row();
            for (Calendar cal : calendarList) {
                String stdDate = new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime());
                row.put(stdDate, new VisitingCardForm.Miss());
            }
            cacheStudentMap.put(student.getId(), student);
            visitingCardForm.getRows().put(student.getId(), row);
        }

        response.addObject("visitingCardForm", visitingCardForm);
        response.addObject("calendarList", dateStrList);
        response.addObject("calendarList1", dateStrList1);
        response.addObject("calendarList2", dateStrList2);
        response.addObject("calendarList3", dateStrList3);
        response.addObject("studentList", studentList);
        updateModel(response);
        response.setViewName("visitingcardform");
        return response;
    }

    @RequestMapping(value = "/editvisitingcard", method = RequestMethod.GET, params = {"month", "year"})
    public ModelAndView editVisitingCard(@RequestParam("month") Integer month, @RequestParam Integer year) {
        ModelAndView response = new ModelAndView();

        VisitingCardForm visitingCardForm = new VisitingCardForm();
        visitingCardForm.setMonth(Month.values()[month]);
        visitingCardForm.setYear(year);

        List<Student> studentList = studService.getAll();

        for (Student student : studentList) {
            List<VisitingCard> visitingCardList = cardsService.getStudsVisiting(student, month, year);
            VisitingCardForm.Row row = new VisitingCardForm.Row();
            for (VisitingCard visitingCard : visitingCardList) {
                VisitingCardForm.Miss miss = new VisitingCardForm.Miss();
                miss.setId(visitingCard.getId());
                miss.setHour(visitingCard.getHourMiss());
                miss.setMissType(visitingCard.getMissType().getRus());
                String strDate = new SimpleDateFormat("dd.MM.yyyy").format(visitingCard.getDate());
                row.put(strDate, miss);
            }
            cacheStudentMap.put(student.getId(), student);
            visitingCardForm.getRows().put(student.getId(), row);
        }

        List<Calendar> calendarList = new ArrayList<>();
        Calendar calendar = new GregorianCalendar(year, month, 1);
        calendarList.add(calendar);
        Calendar nextDay = (Calendar) calendar.clone();
        nextDay.add(Calendar.DAY_OF_MONTH, 1);

        while (month.equals(nextDay.get(Calendar.MONTH))) {
            calendarList.add(nextDay);
            nextDay = (Calendar) nextDay.clone();
            nextDay.add(Calendar.DAY_OF_MONTH, 1);
        }

        List<String> dateStrList = new ArrayList<>();

        for (Calendar cal : calendarList) {
            dateStrList.add(new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime()));
        }

        List<String> dateStrList1 = new ArrayList<>();
        for (int i = 0 ; dateStrList.size() > i && i < 11 ; ++i) {
            dateStrList1.add(dateStrList.get(i));
        }

        List<String> dateStrList2 = new ArrayList<>();
        for (int i = 11 ; dateStrList.size() > i && i < 22 ; ++i) {
            dateStrList2.add(dateStrList.get(i));
        }

        List<String> dateStrList3 = new ArrayList<>();
        for (int i = 22 ; dateStrList.size() > i && i < 31 ; ++i) {
            dateStrList3.add(dateStrList.get(i));
        }

        response.addObject("visitingCardForm", visitingCardForm);
        response.addObject("calendarList", dateStrList);
        response.addObject("calendarList1", dateStrList1);
        response.addObject("calendarList2", dateStrList2);
        response.addObject("calendarList3", dateStrList3);
        response.addObject("studentList", studentList);
        updateModel(response);
        response.setViewName("visitingcardform");
        return response;
    }

    @RequestMapping(value = "/savevisitingcard", method = RequestMethod.POST)
    public ModelAndView saveVisitingCard(@ModelAttribute("visitingCardForm") VisitingCardForm visitingCardForm) {
        ModelAndView response = new ModelAndView();

        for (Map.Entry<Long, VisitingCardForm.Row> visitingCardEntry : visitingCardForm.getRows().entrySet()) {
            Student student = cacheStudentMap.get(visitingCardEntry.getKey());
            for (Map.Entry<String, VisitingCardForm.Miss> rowEntry : visitingCardEntry.getValue().entrySet()) {
                try {
                    Date date = new SimpleDateFormat("dd.MM.yyyy").parse(rowEntry.getKey());
                    VisitingCardForm.Miss miss = rowEntry.getValue();
                    VisitingCard visitingCard = new VisitingCard();
                    visitingCard.setId(miss.getId());
                    visitingCard.setStudent(student);
                    visitingCard.setDate(date);
                    visitingCard.setHourMiss(miss.getHour());
                    visitingCard.setMissType(MissType.getByRus(miss.getMissType()));
                    cardsService.saveVisitingCard(visitingCard);
                } catch (ParseException e) {}
            }
        }

        response.setViewName("redirect:/cards/");
        return response;
    }

    private void updateModel(ModelAndView modelAndView) {
        modelAndView.addObject("months", Month.values());
        modelAndView.addObject("missTypes", MissType.getRusList());
    }

    private ReportCardForm.Row reportCardToRow(List<ReportCard> reportCardList) {
        ReportCardForm.Row row = new ReportCardForm.Row();
        for (ReportCard reportCard : reportCardList) {
            ReportCardForm.Rate rate = new ReportCardForm.Rate();
            rate.setId(reportCard.getId());
            rate.setRate(reportCard.getRate());
            cacheDisciplineMap.put(reportCard.getDiscipline().getDisciplineName(), reportCard.getDiscipline());
            row.put(reportCard.getDiscipline().getDisciplineName(), rate);
        }
        return row;
    }
}
