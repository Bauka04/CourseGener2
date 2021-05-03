package com.example.demo.repositories;

import com.example.demo.entities.Schedule;
import com.example.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserIs(Users users);
    List<Schedule> findAllByUserIsAndTitleIs(Users users, String title);
}
