package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.DisciplineDao;
import com.github.vkpeb.model.Discipline;
import com.github.vkpeb.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pasty on 17.05.2016.
 */
@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineDao disciplineDao;

    @Override
    public Discipline addDiscipline(Discipline discipline) {
        Discipline tempDiscipline = disciplineDao.getDisciplineByName(discipline.getDisciplineName());
        if (tempDiscipline != null) discipline.setId(tempDiscipline.getId());
        return disciplineDao.saveAndFlush(discipline);
    }

    @Override
    public Discipline getDisciplineById(Long id) {
        return disciplineDao.getOne(id);
    }

    @Override
    public Discipline getDisciplineByName(String name) {
        return disciplineDao.getDisciplineByName(name);
    }

    @Override
    public List<Discipline> getAll() {
        return disciplineDao.findAll(new Sort(Sort.Direction.ASC, "disciplineName"));
    }
}
