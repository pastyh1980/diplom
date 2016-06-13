package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.ClassMettingDao;
import com.github.vkpeb.dao.EventDao;
import com.github.vkpeb.dao.EventMemberDao;
import com.github.vkpeb.dao.IndividualWorkDao;
import com.github.vkpeb.model.ClassMeeting;
import com.github.vkpeb.model.Event;
import com.github.vkpeb.model.EventMember;
import com.github.vkpeb.model.IndividualWork;
import com.github.vkpeb.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pasty on 12.06.2016.
 */
@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private ClassMettingDao classMettingDao;

    @Autowired
    private IndividualWorkDao individualWorkDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private EventMemberDao eventMemberDao;

    @Override
    public ClassMeeting getClassMeeting(long id) {
        return classMettingDao.findOne(id);
    }

    @Override
    public List<ClassMeeting> getAllClassMeetings() {
        return classMettingDao.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    @Override
    public ClassMeeting saveClassMeeting(ClassMeeting classMeeting) {
        return classMettingDao.saveAndFlush(classMeeting);
    }

    @Override
    public IndividualWork getIndividualWork(long id) {
        return individualWorkDao.findOne(id);
    }

    @Override
    public List<IndividualWork> getAllIndividualWork() {
        return individualWorkDao.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    @Override
    public IndividualWork saveIndividualWork(IndividualWork individualWork) {
        return individualWorkDao.saveAndFlush(individualWork);
    }

    @Override
    public Event getEvent(long id) {
        return eventDao.findOne(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    @Override
    public Event saveEvent(Event event) {
        return eventDao.saveAndFlush(event);
    }

    @Override
    public List<EventMember> getEventMembers(Event event) {
        return eventMemberDao.getEventMembers(event);
    }

    @Override
    public EventMember saveEventMember(EventMember eventMember) {
        return eventMemberDao.saveAndFlush(eventMember);
    }
}
