package com.github.vkpeb.service;

import com.github.vkpeb.model.ClassMeeting;
import com.github.vkpeb.model.Event;
import com.github.vkpeb.model.EventMember;
import com.github.vkpeb.model.IndividualWork;

import java.util.List;

/**
 * Created by pasty on 12.06.2016.
 */
public interface EventsService {

    ClassMeeting getClassMeeting(long id);
    List<ClassMeeting> getAllClassMeetings();
    ClassMeeting saveClassMeeting(ClassMeeting classMeeting);

    IndividualWork getIndividualWork(long id);
    List<IndividualWork> getAllIndividualWork();
    IndividualWork saveIndividualWork(IndividualWork individualWork);

    Event getEvent(long id);
    List<Event> getAllEvents();
    Event saveEvent(Event event);

    List<EventMember> getEventMembers(Event event);
    EventMember saveEventMember(EventMember eventMember);
}
