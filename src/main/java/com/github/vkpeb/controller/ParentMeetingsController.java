package com.github.vkpeb.controller;

import com.github.vkpeb.model.Boss;
import com.github.vkpeb.model.ParentMeetings;
import com.github.vkpeb.model.Student;
import com.github.vkpeb.model.form.MeetingForm;
import com.github.vkpeb.service.ParentMeetingService;
import com.github.vkpeb.service.StudService;
import com.github.vkpeb.util.ReportGeneratorUtil;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pasty on 01.06.2016.
 */
@Controller
@RequestMapping("/meet")
public class ParentMeetingsController {

    @Autowired
    private ParentMeetingService parentMeetingService;

    @Autowired
    private StudService studService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView meetingList() {
        ModelAndView response = new ModelAndView();

        List<ParentMeetings> parentMeetingsList = parentMeetingService.getAll();

        List<MeetingForm> parentMeetingForms = new ArrayList<>();

        for (ParentMeetings meetings : parentMeetingsList) {
            MeetingForm form = new MeetingForm();
            synchMeetingFormWithMeeting(meetings, form);
            parentMeetingForms.add(form);
        }

        response.addObject("parentMeetingsList", parentMeetingForms);
        response.setViewName("meetlist");
        return response;
    }

    @RequestMapping(value = "/newmeet", method = RequestMethod.GET)
    public ModelAndView newMeeting() {
        ModelAndView response = new ModelAndView();

        MeetingForm meetingForm = new MeetingForm();

        response.addObject("meetingForm", meetingForm);
        response.setViewName("meetform");
        return response;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView meetingEdit(@PathVariable("id") Long id) {
        ModelAndView response = new ModelAndView();

        ParentMeetings parentMeetings = parentMeetingService.getMeetingById(id);
        MeetingForm meetingForm = new MeetingForm();
        synchMeetingFormWithMeeting(parentMeetings, meetingForm);

        response.addObject("meetingForm", meetingForm);
        response.setViewName("meetform");
        return response;
    }

    @RequestMapping(value = "/{id}/report", method = RequestMethod.GET)
    public ModelAndView generateReport(@PathVariable("id") Long id, HttpServletResponse response) throws Docx4JException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment;filename=meeting.docx");

        ParentMeetings parentMeetings = parentMeetingService.getMeetingById(id);
        List<Student> studentList = studService.getAll();
        Boss boss = studentList.get(0).getGroup().getBoss();

        WordprocessingMLPackage file = ReportGeneratorUtil.generateMeetingReport(parentMeetings, studentList, boss);
        file.save(response.getOutputStream());

        modelAndView.setViewName("redirect:/meet/");
        return modelAndView;
    }

    @RequestMapping(value = "/savemeeting", method = RequestMethod.POST)
    public ModelAndView saveMeeting(@ModelAttribute("meetingForm") MeetingForm meetingForm) {
        ModelAndView response = new ModelAndView();

        ParentMeetings parentMeetings = new ParentMeetings();
        synchMeetingFormWithMeeting(parentMeetings, meetingForm);

        parentMeetingService.saveParentMeeting(parentMeetings);

        response.setViewName("redirect:/meet/");
        return response;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView saveMeeting(@PathVariable("id") Long id) {
        ModelAndView response = new ModelAndView();

        parentMeetingService.deleteParentMeeting(id);

        response.setViewName("redirect:/meet/");
        return response;
    }

    private void synchMeetingFormWithMeeting(ParentMeetings meeting, MeetingForm meetingForm) {
        if (meeting.getTheme() == null) {
            meeting.setId(meetingForm.getId());
            meeting.setTheme(meetingForm.getTheme());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String dateStr = meetingForm.getDate() + " " + meetingForm.getTime();
            try {
                meeting.setDate(format.parse(dateStr));
            } catch (ParseException e) {
                meeting.setDate(new Date());
            }
        } else {
            meetingForm.setId(meeting.getId());
            meetingForm.setTheme(meeting.getTheme());
            meetingForm.setDate(new SimpleDateFormat("dd.MM.yyyy").format(meeting.getDate()));
            meetingForm.setTime(new SimpleDateFormat("HH:mm").format(meeting.getDate()));
        }
    }
}
