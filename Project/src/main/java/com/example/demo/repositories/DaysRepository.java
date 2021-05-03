package com.example.demo.repositories;

import com.example.demo.entities.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DaysRepository extends JpaRepository<Days, Long> {


}
