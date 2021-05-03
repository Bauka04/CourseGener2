package com.example.demo.services;

import com.example.demo.entities.Activity;
import com.example.demo.entities.Days;
import com.example.demo.entities.Schedule;
import java.util.List;

public interface ActivityService {
    List<Activity> getActvitiesByScheduleIs(Schedule schedule);
    Activity saveActivity(Activity activity);
    Activity getActivityById(Long id);
    Activity addActivity(Activity activity);
    void deleteActivity(Activity activity);

    Days getDayById(Long id);

}