package com.github.vkpeb.service;

import com.github.vkpeb.model.Discipline;

import java.util.List;

/**
 * Created by pasty on 17.05.2016.
 */
public interface DisciplineService {

    Discipline addDiscipline(Discipline discipline);
    Discipline getDisciplineById(Long id);
    Discipline getDisciplineByName(String name);
    List<Discipline> getAll();
}
