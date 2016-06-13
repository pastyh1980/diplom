package com.github.vkpeb.service;

import com.github.vkpeb.model.ParentMeetings;

import java.util.List;

/**
 * Created by pasty on 01.06.2016.
 */
public interface ParentMeetingService {

    List<ParentMeetings> getAll();
    ParentMeetings getMeetingById(Long id);
    ParentMeetings saveParentMeeting(ParentMeetings parentMeetings);
    void deleteParentMeeting(Long id);
}
