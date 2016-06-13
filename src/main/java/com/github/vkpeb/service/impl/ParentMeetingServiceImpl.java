package com.github.vkpeb.service.impl;

import com.github.vkpeb.dao.ParentMeetingDao;
import com.github.vkpeb.model.ParentMeetings;
import com.github.vkpeb.service.ParentMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pasty on 01.06.2016.
 */
@Service
public class ParentMeetingServiceImpl implements ParentMeetingService {

    @Autowired
    private ParentMeetingDao parentMeetingDao;

    @Override
    public List<ParentMeetings> getAll() {
        return parentMeetingDao.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    @Override
    public ParentMeetings getMeetingById(Long id) {
        return parentMeetingDao.findOne(id);
    }

    @Override
    public ParentMeetings saveParentMeeting(ParentMeetings parentMeetings) {
        return parentMeetingDao.saveAndFlush(parentMeetings);
    }

    @Override
    public void deleteParentMeeting(Long id) {
        parentMeetingDao.delete(id);
    }
}
