package com.github.vkpeb.controller;

import com.github.vkpeb.model.Auth;
import com.github.vkpeb.model.Order;
import com.github.vkpeb.model.Parent;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.enumer.*;
import com.github.vkpeb.model.form.OrderForm;
import com.github.vkpeb.model.form.ParentForm;
import com.github.vkpeb.model.form.StudentForm;
import com.github.vkpeb.service.StudService;
import com.github.vkpeb.util.AuthUtil;
import com.github.vkpeb.validator.StudFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pasty on 27.04.2016.
 */
@Controller
@RequestMapping("/stud")
public class StudController {

    @Autowired
    private StudService studService;

    @Autowired
    private StudFormValidator studFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(studFormValidator);
    }

    @RequestMapping(value = "/liststud", method = RequestMethod.GET)
    public ModelAndView listStud() {
        ModelAndView response = new ModelAndView();

        List<Student> students = studService.getAll();

        response.addObject("students", students);

        response.setViewName("studlist");

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView studDetails(@PathVariable("id") long id) {
        ModelAndView response = new ModelAndView();

        Student student = studService.getStudentById(id);

        if (student == null) {
            response.setViewName("redirect:/stud/liststud");
            return response;
        }

        List<Parent> parents = studService.getParents(student);

        StudentForm studentForm = new StudentForm();
        studentForm.setParents(new ArrayList<>());

        synchStudWithStudForm(student, studentForm);

        for (Parent parent : parents) {
            ParentForm parentForm = new ParentForm();
            synchParentWithParentForm(parent, parentForm);
            studentForm.getParents().add(parentForm);
        }

        List<Order> orders = studService.getStudentsOrders(student);
        studentForm.setOrders(new ArrayList<>());

        for (Order order : orders) {
            OrderForm orderForm = new OrderForm();
            synchOrderWithOrderForm(order, orderForm);
            studentForm.getOrders().add(orderForm);
        }

        response.addObject("studForm", studentForm);

        response.setViewName("studdetails");

        return response;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editStud(@PathVariable("id") long id) {
        ModelAndView response = new ModelAndView();

        Student student = studService.getStudentById(id);

        if (student == null) {
            response.setViewName("redirect:/stud/liststud");
            return response;
        }

        List<Parent> parents = studService.getParents(student);

        StudentForm studentForm = new StudentForm();
        studentForm.setParents(new ArrayList<>());

        synchStudWithStudForm(student, studentForm);

        for (Parent parent : parents) {
            ParentForm parentForm = new ParentForm();
            synchParentWithParentForm(parent, parentForm);
            studentForm.getParents().add(parentForm);
        }

        List<Order> orders = studService.getStudentsOrders(student);
        studentForm.setOrders(new ArrayList<>());

        for (Order order : orders) {
            OrderForm orderForm = new OrderForm();
            synchOrderWithOrderForm(order, orderForm);
            studentForm.getOrders().add(orderForm);
        }

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");

        return response;
    }

    @RequestMapping(value = "/newstud", method = RequestMethod.GET)
    public ModelAndView newStud(){
        ModelAndView response = new ModelAndView();

        StudentForm studentForm = new StudentForm();
        studentForm.setParents(new ArrayList<>());
        studentForm.getParents().add(new ParentForm());
        studentForm.setOrders(new ArrayList<>());
        studentForm.getOrders().add(new OrderForm());

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");

        return response;
    }

    @RequestMapping(value = "/addstud", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveStud(@ModelAttribute("studForm") @Validated StudentForm studentForm,  BindingResult result) {
        ModelAndView response = new ModelAndView();

        if (result.hasErrors()) {
            response.addObject("studForm", studentForm);
            updateModel(response);
            response.setViewName("studform");
            return response;
        }

        Student student = new Student();

        synchStudWithStudForm(student, studentForm);

        Auth studAuth = new Auth();
        studAuth.setLogin(AuthUtil.createLoginByName(student.getFamily(), student.getName()));
        studAuth.setPasswd(AuthUtil.generatePassword());
        studAuth.setEnabled(1);
        studAuth.setType(UserType.valueOf(student.getPost() != null ? student.getPost().name() : "STUDENT"));
        student.setAuth(studAuth);
        student.setGroup(studService.getDefaultGroup());

        for (ParentForm parentForm : studentForm.getParents()) {
            Parent parent = new Parent();
            synchParentWithParentForm(parent, parentForm);
            parent.setStudent(student);
            Auth parentAuth = new Auth();
            parentAuth.setLogin(AuthUtil.createLoginByName(parent.getFamily(), parent.getName()));
            parentAuth.setPasswd(AuthUtil.generatePassword());
            parentAuth.setEnabled(1);
            parentAuth.setType(UserType.PARENT);
            parent.setAuth(parentAuth);
            parent = studService.saveStudentWithParent(parent);
            student = parent.getStudent();
        }

        for (OrderForm orderForm : studentForm.getOrders()) {
            Order order = new Order();
            synchOrderWithOrderForm(order, orderForm);
            order.setStudent(student);
            studService.saveOrder(order);
        }

        response.setViewName("redirect:/stud/liststud");

        return response;
    }

    @RequestMapping(value = "/updatestud", method = RequestMethod.POST)
    public ModelAndView updateStud(@ModelAttribute("studForm") @Validated StudentForm studentForm,  BindingResult result) {
        ModelAndView response = new ModelAndView();

        if (result.hasErrors()) {
            response.addObject("studForm", studentForm);
            updateModel(response);
            response.setViewName("studform");
            return response;
        }

        Student student = new Student();

        synchStudWithStudForm(student, studentForm);

        for (ParentForm parentForm : studentForm.getParents()) {
            Parent parent = new Parent();
            synchParentWithParentForm(parent, parentForm);
            studService.saveStudentWithParent(parent);
        }

        response.setViewName("redirect:/stud/liststud");

        return response;
    }

    @RequestMapping(value = "/addparent", method = RequestMethod.POST)
    public ModelAndView addParent(@ModelAttribute("studForm") StudentForm studentForm) {
        ModelAndView response = new ModelAndView();

        if (studentForm.getParents() == null) studentForm.setParents(new ArrayList<>());

        studentForm.getParents().add(new ParentForm());

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");
        return response;
    }

    @RequestMapping(value = "/delparent/{id}", method = RequestMethod.POST)
    public ModelAndView deleteParent(@ModelAttribute("studForm") StudentForm studentForm, @PathVariable("id") int id) {
        ModelAndView response = new ModelAndView();

        int size = studentForm.getParents().size();

        if (id < size)
            studentForm.getParents().remove(id);

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");
        return response;
    }

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public ModelAndView addorder(@ModelAttribute("studForm") StudentForm studentForm) {
        ModelAndView response = new ModelAndView();

        if (studentForm.getOrders() == null) studentForm.setOrders(new ArrayList<>());

        studentForm.getOrders().add(new OrderForm());

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");
        return response;
    }

    @RequestMapping(value = "/delorder/{id}", method = RequestMethod.POST)
    public ModelAndView deleteOrder(@ModelAttribute("studForm") StudentForm studentForm, @PathVariable("id") int id) {
        ModelAndView response = new ModelAndView();

        int size = studentForm.getOrders().size();

        if (id < size)
            studentForm.getOrders().remove(id);

        response.addObject("studForm", studentForm);

        updateModel(response);

        response.setViewName("studform");
        return response;
    }

    private void synchStudWithStudForm(Student student, StudentForm studentForm) {
        if (studentForm.getStudName() == null) {
            studentForm.setId(student.getId());
            studentForm.setStudFamily(student.getFamily());
            studentForm.setStudName(student.getName());
            studentForm.setStudOtchestvo(student.getOtchestvo());
            studentForm.setStudBorn(student.getBorn());
            studentForm.setStudPhone(student.getPhone());
            studentForm.setLivingCondition(student.getLivingCondition().getRus());
            studentForm.setFamilyStatus(student.getFamilyStatus().getRus());
            studentForm.setPost(student.getPost().getRus());
        } else {
            student.setId(studentForm.getId());
            student.setFamily(studentForm.getStudFamily());
            student.setName(studentForm.getStudName());
            student.setOtchestvo(studentForm.getStudOtchestvo());
            student.setBorn(studentForm.getStudBorn());
            student.setPhone(studentForm.getStudPhone());
            student.setLivingCondition(LivingConditions.getByRus(studentForm.getLivingCondition()));
            student.setFamilyStatus(FamilyStatuses.getByRus(studentForm.getFamilyStatus()));
            student.setPost(Posts.getByRus(studentForm.getPost()));
        }
    }

    private void synchParentWithParentForm(Parent parent, ParentForm parentForm) {
        if (parentForm.getFamily() == null) {
            parentForm.setId(parent.getId());
            parentForm.setParentType(parent.getParentType().getRus());
            parentForm.setFamily(parent.getFamily());
            parentForm.setName(parent.getName());
            parentForm.setOtchestvo(parent.getOtchestvo());
            parentForm.setPhone(parent.getPhone());
            parentForm.setWork(parent.getWork());
            parentForm.setEducationType(parent.getEducationType().getRus());
        } else {
            parent.setId(parentForm.getId());
            parent.setFamily(parentForm.getFamily());
            parent.setName(parentForm.getName());
            parent.setOtchestvo(parentForm.getOtchestvo());
            parent.setParentType(ParentType.getByRus(parentForm.getParentType()));
            parent.setPhone(parentForm.getPhone());
            parent.setWork(parentForm.getWork());
            parent.setEducationType(EducationType.getByRus(parentForm.getEducationType()));
        }
    }

    private void synchOrderWithOrderForm(Order order, OrderForm orderForm) {
        if (order.getDate() == null) {
            order.setId(orderForm.getId());
            try {
                order.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(orderForm.getDate()));
            } catch(ParseException e) {
                order.setDate(new Date());
            }
            order.setNumber(orderForm.getNumber());
            order.setOrderType(OrderType.getByRus(orderForm.getOrderType()));
        } else {
            orderForm.setId(order.getId());
            orderForm.setDate(new SimpleDateFormat("dd.MM.yyyy").format(order.getDate()));
            orderForm.setNumber(order.getNumber());
            orderForm.setOrderType(order.getOrderType().getRus());
        }
    }

    private void updateModel(ModelAndView model) {
        model.addObject("livingConditions", LivingConditions.getRusList());
        model.addObject("familyStatuses", FamilyStatuses.getRusList());
        model.addObject("educationType", EducationType.getRusList());
        model.addObject("parentTypes", ParentType.getRusList());
        model.addObject("posts", Posts.getRusList());
        model.addObject("orderTypes", OrderType.getRusList());
    }
}
