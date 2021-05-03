package com.example.demo.rest;


import com.example.demo.dto.ScheduleRequest;
import com.example.demo.entities.Schedule;
import com.example.demo.entities.Users;
import com.example.demo.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(value = "/schedule")
public class ScheduleRestController {

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping(value = "/all")
    public ResponseEntity<?> allSchedules() {
        Users user = getUser();
        List<Schedule> allSchedules = scheduleService.getSchedulesByUser(user);
        if (!allSchedules.isEmpty()) {
            List<ScheduleRequest> allSchedulesDTO = new ArrayList<>();
            for (int i = 0; i < allSchedules.size(); i++) {
                ScheduleRequest scheduleRequest = new ScheduleRequest();
                scheduleRequest.setId(allSchedules.get(i).getId().toString());
                scheduleRequest.setTitle(allSchedules.get(i).getTitle());
                scheduleRequest.setBg_color(allSchedules.get(i).getColor());
                scheduleRequest.setBg_image(allSchedules.get(i).getImageSrc());
                allSchedulesDTO.add(scheduleRequest);
            }
            return new ResponseEntity<>(allSchedulesDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.ok().body("No schedules");
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequest request) {
        Users user = getUser();
        List<Schedule> scheduleCheck = scheduleService.getSchedulesByUserAndTitle(user, request.getTitle());
        if (scheduleCheck.isEmpty()) {
            Schedule schedule = new Schedule(null, request.getTitle(), request.getBg_color(), request.getBg_image(), user);
            scheduleService.addSchedule(schedule);
            return ResponseEntity.ok().body("Added");
        }
        else{
            return ResponseEntity.status(409).body("Schedule is same title is already exists");
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleRequest request) {
        Users user = getUser();
        System.out.println(user.getFull_name());
        List<Schedule> scheduleCheck = scheduleService.getSchedulesByUserAndTitle(user, request.getTitle());
        if (scheduleCheck.isEmpty() || scheduleCheck.get(0).getId().toString().equals(request.getId())) {
            Schedule schedule = scheduleService.getScheduleById(Long.parseLong(request.getId()));
            schedule.setTitle(request.getTitle());
            schedule.setColor(request.getBg_color());
            schedule.setImageSrc(request.getBg_image());
            scheduleService.saveSchedule(schedule);

            return ResponseEntity.ok().body("Updated");
        }
        else{
            return ResponseEntity.status(409).body("Schedule is same title is already exists");
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name = "id") String id) {
        Schedule schedule = scheduleService.getScheduleById(Long.parseLong(id));
        if(schedule!=null){
            System.out.println(schedule.getColor());
            scheduleService.deleteSchedule(schedule);

            return ResponseEntity.ok().body("Deleted");
        }
        else{
            return ResponseEntity.status(409).body("Wrong id");
        }
    }


    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }
}
