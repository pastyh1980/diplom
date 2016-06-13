package com.github.vkpeb.controller;

import com.github.vkpeb.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pasty on 13.06.2016.
 */
@Controller
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    /*@RequestMapping(value = "/classmeet", method = RequestMethod.GET)
    public ModelAndView classMeetingList() {
        ModelAndView response = new ModelAndView();
    }*/
}
