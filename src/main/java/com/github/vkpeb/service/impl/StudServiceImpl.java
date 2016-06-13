package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.GroupDao;
import com.github.vkpeb.dao.OrderDao;
import com.github.vkpeb.dao.ParentDao;
import com.github.vkpeb.dao.StudDao;
import com.github.vkpeb.model.Group;
import com.github.vkpeb.model.Order;
import com.github.vkpeb.model.Parent;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.service.StudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pasty on 27.04.2016.
 */
@Service
public class StudServiceImpl implements StudService{

    @Autowired
    private StudDao studDao;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Student> getAll() {
        return studDao.findAll(new Sort(Sort.Direction.ASC, "family"));
    }

    @Override
    public List<Parent> getParents(Student student) {
        return parentDao.getParentsByStudents(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studDao.findOne(id);
    }

    @Override
    @Transactional
    public Parent saveStudentWithParent(Parent parent) {
        Student student = studDao.saveAndFlush(parent.getStudent());
        parent.setStudent(student);
        return parentDao.saveAndFlush(parent);
    }

    @Override
    public Group getDefaultGroup() {
        List<Group> groupList = groupDao.findAll();

        if (groupList != null && !groupList.isEmpty()) return groupList.get(0);

        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderDao.saveAndFlush(order);
    }

    @Override
    public List<Order> getStudentsOrders(Student student) {
        return orderDao.getStudentsOrders(student);
    }
}
