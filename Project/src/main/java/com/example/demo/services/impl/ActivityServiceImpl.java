package com.example.demo.services.impl;


import com.example.demo.entities.Activity;
import com.example.demo.entities.Days;
import com.example.demo.entities.Schedule;
import com.example.demo.entities.Users;
import com.example.demo.repositories.ActivityRepository;
import com.example.demo.repositories.DaysRepository;
import com.example.demo.repositories.ScheduleRepository;
import com.example.demo.services.ActivityService;
import com.example.demo.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private DaysRepository daysRepository;

    @Override
    public List<Activity> getActvitiesByScheduleIs(Schedule schedule) {
        return activityRepository.findAllByScheduleIs(schedule);
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).get();
    }

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Activity activity) {
        activityRepository.delete(activity);
    }

    @Override
    public Days getDayById(Long id) {
        return daysRepository.getOne(id);
    }
}