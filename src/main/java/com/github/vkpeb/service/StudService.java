package com.github.vkpeb.service;

import com.github.vkpeb.model.Group;
import com.github.vkpeb.model.Order;
import com.github.vkpeb.model.Parent;
import com.github.vkpeb.model.Student;

import java.util.List;

/**
 * Created by pasty on 27.04.2016.
 */
public interface StudService {

    List<Student> getAll();

    List<Parent> getParents(Student student);

    Student getStudentById(long id);

    Parent saveStudentWithParent(Parent parent);

    Group getDefaultGroup();

    Order saveOrder(Order order);

    List<Order> getStudentsOrders(Student student);
}
