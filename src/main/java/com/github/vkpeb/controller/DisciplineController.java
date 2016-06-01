package com.github.vkpeb.controller;

import com.github.vkpeb.model.Discipline;
import com.github.vkpeb.model.form.DisciplineForm;
import com.github.vkpeb.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by pasty on 17.05.2016.
 */
@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView disciplineList() {
        ModelAndView response = new ModelAndView();

        List<Discipline> disciplineList = disciplineService.getAll();

        response.addObject("disciplineList", disciplineList);
        response.setViewName("disciplinelist");
        return response;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newDiscipline() {
        ModelAndView response = new ModelAndView();
        response.addObject("discipline", new Discipline());
        response.setViewName("disciplineform");
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveDiscipline(@ModelAttribute("discipline") Discipline discipline) {
        disciplineService.addDiscipline(discipline);
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/discipline/");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView editDiscipline(@PathVariable("id") Long id) {
        ModelAndView response = new ModelAndView();

        Discipline discipline = disciplineService.getDisciplineById(id);

        DisciplineForm disciplineForm = new DisciplineForm();
        synchDisciplineFormWithDiscipline(discipline, disciplineForm);

        response.addObject("discipline", discipline);
        response.setViewName("disciplineform");
        return response;
    }

    private void synchDisciplineFormWithDiscipline(Discipline discipline, DisciplineForm disciplineForm) {
        if (discipline.getDisciplineName() == null) {
            discipline.setId(disciplineForm.getId());
            discipline.setDisciplineName(disciplineForm.getDisciplineName());
            discipline.setPrepod(disciplineForm.getPrepod());
        } else {
            disciplineForm.setId(discipline.getId());
            disciplineForm.setDisciplineName(discipline.getDisciplineName());
            disciplineForm.setPrepod(discipline.getPrepod());
        }
    }
}
