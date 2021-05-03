package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private Time start_time;

    @Column(name = "end_time")
    private Time end_time;

    @Column(name = "description")
    private String description;

    @Column(name = "bg_color")
    private String bg_color;

    @Column(name = "text_color")
    private String text_color;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Days> days;

    @ManyToOne(fetch = FetchType.EAGER)
    private Schedule schedule;
}
