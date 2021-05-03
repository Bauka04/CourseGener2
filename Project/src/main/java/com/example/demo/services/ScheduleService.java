package com.example.demo.services;

import com.example.demo.entities.Schedule;
import com.example.demo.entities.Users;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedulesByUser(Users user);
    Schedule saveSchedule(Schedule schedule);
    Schedule getScheduleById(Long id);
    Schedule addSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
    List<Schedule> getSchedulesByUserAndTitle(Users users, String title);

}