package com.example.demo.repositories;

import com.example.demo.entities.Activity;
import com.example.demo.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByScheduleIs(Schedule schedule);

}
