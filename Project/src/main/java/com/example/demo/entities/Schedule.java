package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "bg_color")
    private String color;

    @Column(name = "bg_image")
    private String imageSrc;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;


}
