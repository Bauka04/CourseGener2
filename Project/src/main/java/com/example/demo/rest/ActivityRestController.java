package com.example.demo.rest;

import com.example.demo.dto.ActivityRequest;
import com.example.demo.entities.Activity;
import com.example.demo.entities.Days;
import com.example.demo.entities.Schedule;
import com.example.demo.services.ActivityService;
import com.example.demo.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/activity")
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getActivities(@PathVariable(name = "id") String id) {
        Schedule schedule = scheduleService.getScheduleById(Long.parseLong(id));
        List<Activity> activities = activityService.getActvitiesByScheduleIs(schedule);
        if (!activities.isEmpty()) {
            List<ActivityRequest> activitiesDTO = new ArrayList<>();
            for (int i = 0; i < activities.size(); i++) {
                ActivityRequest activityDTO = new ActivityRequest();
                activityDTO.setId(activities.get(i).getId().toString());
                activityDTO.setName(activities.get(i).getName());
                activityDTO.setBg_color(activities.get(i).getBg_color());
                String daysDTO = "";
                for (Days day : activities.get(i).getDays()) {
                    daysDTO += day.getId().toString();
                }
                activityDTO.setDays(daysDTO);
                activityDTO.setText_color(activities.get(i).getText_color());
                activityDTO.setDescription(activities.get(i).getDescription());
                activityDTO.setStart_time(activities.get(i).getStart_time().toString());
                activityDTO.setEnd_time(activities.get(i).getEnd_time().toString());
                activityDTO.setSchedule_id(id);
                activitiesDTO.add(activityDTO);
            }
            return new ResponseEntity<>(activitiesDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body("No activities");
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addActivity(@RequestBody ActivityRequest request) {
        Schedule schedule = scheduleService.getScheduleById(Long.parseLong(request.getSchedule_id()));
        List<Days> daysList = new ArrayList<>();
        for (int i = 0; i < request.getDays().length(); i++) {
            char a = request.getDays().charAt(i);
            Long b = Long.parseLong(Character.toString(a));
            Days day = activityService.getDayById(b);
            daysList.add(day);
        }
        Time start_time = Time.valueOf(request.getStart_time());
        Time end_time = Time.valueOf(request.getEnd_time());
        Activity activity = new Activity(null, request.getName(), start_time, end_time, request.getDescription(), request.getBg_color(),
                request.getText_color(), daysList, schedule);
        activityService.addActivity(activity);

        return ResponseEntity.ok().body("Added");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateActivity(@RequestBody ActivityRequest request) {
        Activity activity = activityService.getActivityById(Long.parseLong(request.getId()));
        List<Days> daysList = new ArrayList<>();
        for (int i = 0; i < request.getDays().length(); i++) {
            char a = request.getDays().charAt(i);
            Long b = Long.parseLong(Character.toString(a));
            Days day = activityService.getDayById(b);
            daysList.add(day);
        }
        Time start_time = Time.valueOf(request.getStart_time());
        Time end_time = Time.valueOf(request.getEnd_time());

        activity.setName(request.getName());
        activity.setBg_color(request.getBg_color());
        activity.setDays(daysList);
        activity.setText_color(request.getText_color());
        activity.setStart_time(start_time);
        activity.setEnd_time(end_time);
        activity.setDescription(request.getDescription());

        activityService.saveActivity(activity);

        return ResponseEntity.ok().body("Updated");

    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable(name = "id") String id) {
        Activity activity = activityService.getActivityById(Long.parseLong(id));
        if (activity != null) {
            activityService.deleteActivity(activity);
            return ResponseEntity.ok().body("Deleted");
        } else {
            return ResponseEntity.status(409).body("Wrong id");
        }
    }
}
