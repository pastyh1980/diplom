package com.github.vkpeb.dao;

import com.github.vkpeb.model.Order;
import com.github.vkpeb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pasty on 26.05.2016.
 */
public interface OrderDao extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.student = :stud")
    List<Order> getStudentsOrders(@Param("stud")Student student);
}
