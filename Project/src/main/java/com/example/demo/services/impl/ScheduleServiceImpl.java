package com.example.demo.services.impl;


import com.example.demo.entities.Schedule;
import com.example.demo.entities.Users;

import com.example.demo.repositories.ScheduleRepository;

import com.example.demo.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getSchedulesByUser(Users user) {
        return scheduleRepository.findAllByUserIs(user);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByUserAndTitle(Users users, String title) {
        return scheduleRepository.findAllByUserIsAndTitleIs(users, title);
    }


}