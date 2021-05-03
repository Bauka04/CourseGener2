package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest implements Serializable {

    private static final long serialVersionUID = 11187119L;

    private String id;
    private String name;
    private String start_time;
    private String end_time;
    private String bg_color;
    private String text_color;
    private String description;
    private String days;
    private String schedule_id;
}
